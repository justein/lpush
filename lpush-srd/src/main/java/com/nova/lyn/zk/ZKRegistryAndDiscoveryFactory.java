package com.nova.lyn.zk;

import com.nova.lyn.listener.ServiceListener;
import com.nova.lyn.service.BaseService;
import com.nova.lyn.service.ServiceDiscovery;
import com.nova.lyn.service.ServiceNode;
import com.nova.lyn.service.ServiceRegistry;

import java.util.List;

/***
 * @ClassName: ZKRegistryAndDiscoveryFactory
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/5 下午4:47
 * @version : V1.0
 */
public class ZKRegistryAndDiscoveryFactory extends BaseService implements ServiceDiscovery, ServiceRegistry {

    public static final ZKRegistryAndDiscoveryFactory INSTANCE = new ZKRegistryAndDiscoveryFactory();



    @Override
    public List<ServiceNode> lookup(String path) {
        return null;
    }

    @Override
    public void subscribe(String path, ServiceListener serviceListener) {

    }

    @Override
    public void unsubscribe(String path, ServiceListener serviceListener) {

    }

    @Override
    public void register(ServiceNode serviceNode) {

    }

    @Override
    public void unregister(ServiceNode serviceNode) {

    }
}
