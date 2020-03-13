package com.nova.lyn.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

/***
 * @ClassName: LPushConfig
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/5 下午5:51
 * @version : V1.0
 */
public interface LPushConfig {

    Config config = load();

    static Config load() {
        /**扫描加载默认的配置文件*/
        Config config = ConfigFactory.load();
        String customConfigPath = "lp.conf";

        if (config.hasPath(customConfigPath)) {
            File file = new File(config.getString(customConfigPath));
            if (file.exists()) {
                Config customConfig = ConfigFactory.parseFile(file);
                /**加载自定义文件覆盖掉默认的配置文件*/
                config.withFallback(customConfig);
            }

        }
        return config;
    }

    interface lp {

        Config cfg = LPushConfig.config.getObject("lp").toConfig();
        String log_dir = cfg.getString("log-dir");
        String log_level = cfg.getString("log-level");
        String log_conf_path = cfg.getString("log-conf-path");
        interface zk {

            Config cfg = lp.cfg.getObject("zk").toConfig();
            int sessionTimeoutMs = (int) cfg.getDuration("sessionTimeoutMs", TimeUnit.MILLISECONDS);
            String watchPath = cfg.getString("watch-path");
            int connectionTimeoutMs = (int) cfg.getDuration("connectionTimeoutMs", TimeUnit.MILLISECONDS);
            String namespace = cfg.getString("namespace");

            String digest = cfg.getString("digest");

            String server_address = cfg.getString("server-address");

            interface retry {

                Config cfg = zk.cfg.getObject("retry").toConfig();

                int maxRetries = cfg.getInt("maxRetries");

                int baseSleepTimeMs = (int) cfg.getDuration("baseSleepTimeMs", TimeUnit.MILLISECONDS);

                int maxSleepMs = (int) cfg.getDuration("maxSleepMs", TimeUnit.MILLISECONDS);
            }
        }

    }

}
