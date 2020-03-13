package com.nova.lyn.zk;

import com.nova.lyn.constants.Constants;
import com.nova.lyn.listener.Listener;
import com.nova.lyn.log.Logs;
import com.nova.lyn.service.BaseService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/***
 * @ClassName: ZKClient
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/10 下午2:31
 * @version : V1.0
 */
public class ZKClient extends BaseService {

    public static ZKClient instance = getInstance();

    private ZKConfig zkConfig;
    private CuratorFramework curatorFramework;
    private TreeCache cache;
    private Map<String, String> ephemeralNodes = new LinkedHashMap<>(4);
    private Map<String, String> ephemeralSequentialNodes = new LinkedHashMap<>(1);


    public synchronized static ZKClient getInstance() {
        return instance == null ? new ZKClient() : instance;
    }

    /**
     * 防止外部new
     */
    private ZKClient() {
    }

    @Override
    public void init() {
        if (curatorFramework != null) return;
        if (zkConfig == null) {
            zkConfig = ZKConfig.build();
        }
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString(zkConfig.getHosts())
                .retryPolicy(new ExponentialBackoffRetry(zkConfig.getBaseSleepTimeMs(),
                        zkConfig.getMaxRetries(), zkConfig.getMaxSleepMs()))
                .namespace(zkConfig.getNamespace());
        if (zkConfig.getConnectionTimeout() > 0) {
            builder.connectionTimeoutMs(zkConfig.getConnectionTimeout());
        }
        if (zkConfig.getSessionTimeout() > 0) {
            builder.sessionTimeoutMs(zkConfig.getSessionTimeout());
        }

        if (zkConfig.getDigest() != null) {
            /*
             * scheme对应于采用哪种方案来进行权限管理，zookeeper实现了一个pluggable的ACL方案，
             * 可以通过扩展scheme，来扩展ACL的机制。
             * zookeeper缺省支持下面几种scheme:
             *
             * world: 默认方式，相当于全世界都能访问; 它下面只有一个id, 叫anyone, world:anyone代表
             * 任何人，zookeeper中对所有人有权限的结点就是属于world:anyone的
             * auth: 代表已经认证通过的用户(cli中可以通过addauth digest user:pwd 来添加当前上下文中
             * 的授权用户); 它不需要id, 只要是通过authentication的user都有权限（zookeeper支持通过
             * kerberos来进行authencation, 也支持username/password形式的authentication)
             * digest: 即用户名:密码这种方式认证，这也是业务系统中最常用的;它对应的id为
             * username:BASE64(SHA1(password))，它需要先通过username:password形式的authentication
             * ip: 使用Ip地址认证;它对应的id为客户机的IP地址，设置的时候可以设置一个ip段，
             * 比如ip:192.168.1.0/16, 表示匹配前16个bit的IP段
             * super: 在这种scheme情况下，对应的id拥有超级权限，可以做任何事情(cdrwa)
             */
            builder.authorization("digest",zkConfig.getDigest().getBytes(Constants.UTF_8));
            builder.aclProvider(new ACLProvider() {
                @Override
                public List<ACL> getDefaultAcl() {
                    return ZooDefs.Ids.CREATOR_ALL_ACL;
                }

                @Override
                public List<ACL> getAclForPath(String s) {
                    return ZooDefs.Ids.CREATOR_ALL_ACL;
                }
            });
        }

        curatorFramework = builder.build();
        Logs.RSD.info("zk client init , config:{} ",zkConfig.toString());

    }

    @Override
    public void start(Listener listener) {
        if (isRunning()) {
            listener.onSuccess();
        } else {
            super.start(listener);
        }
    }

    @Override
    public void stop(Listener listener) {
        if (isRunning()) {
            super.stop(listener);
        } else {
            listener.onSuccess();
        }
    }

    @Override
    protected void doStart(Listener listener) throws Throwable {
        curatorFramework.start();
        Logs.RSD.info("zk client initialize successful,waitting for connect...");
        if (!curatorFramework.blockUntilConnected(1, TimeUnit.MINUTES)) {
            throw new ZKException("zk initialize error, config = " + zkConfig);
        }
        initLocalCache(zkConfig.getWatchPath());
        addConnectionStateListener();
        Logs.RSD.info("zk client start successful ,server lists :{}", zkConfig.getHosts());
        listener.onSuccess(zkConfig.getHosts());
    }


    @Override
    protected void doStop(Listener listener) throws Throwable {
        if (cache != null) cache.close();
        /**线程睡眠一会儿再关闭curator*/
        TimeUnit.MILLISECONDS.sleep(600);
        curatorFramework.close();
        Logs.RSD.info("zk client closed...");
        listener.onSuccess();
    }

    private void initLocalCache(String watchRootPath) throws Exception {
        cache = new TreeCache(curatorFramework, watchRootPath);
        cache.start();
    }

    private void addConnectionStateListener() {
        curatorFramework.getConnectionStateListenable().addListener((cli, newState) -> {
            if (newState == ConnectionState.RECONNECTED) {
                ephemeralNodes.forEach(this::reRegisterEphemeral);
            }
            Logs.RSD.warn("zk connection state changed new state={}, isConnected={}", newState, newState.isConnected());
        });
    }

    private void reRegisterEphemeral(String key, String value) {
        registerEphemeral(key, value, false);
    }

    /**
     * 注册临时数据
     *
     * @param key
     * @param value
     * @param cache
     */
    private void registerEphemeral(String key, String value, boolean cache) {

        try {
            if (isExist(key)) {
                curatorFramework.delete().deletingChildrenIfNeeded().forPath(key);
            }
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(key, value.getBytes(Constants.UTF_8));
            if (cache) ephemeralNodes.put(key, value);

        } catch (Exception e) {
            e.printStackTrace();
            Logs.RSD.error("persistantEphemeral: {},{},{}", key, value, e);
            throw new ZKException("ex");
        }
    }

    /**
     * 判断路径是否存在
     */
    private boolean isExist(String key) {
        try {
            return null != curatorFramework.checkExists().forPath(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
