package com.nova.lyn.log;

import com.nova.lyn.config.LPushConfig;
import com.typesafe.config.ConfigRenderOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * @ClassName: Logs
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/10 下午1:38
 * @version : V1.0
 */
public interface Logs {

    boolean logInitFlag = init();

    static boolean init() {
        if (logInitFlag) return true;
        System.setProperty("log.home",LPushConfig.lp.log_dir);
        System.setProperty("log.root.level", LPushConfig.lp.log_level);
        System.setProperty("logback.configurationFile",LPushConfig.lp.log_conf_path);

        LoggerFactory.getLogger("console")
                .info(LPushConfig.lp.cfg.root().render(ConfigRenderOptions.concise().setFormatted(true)));
        return true;
    }

    Logger Console = LoggerFactory.getLogger("console"),

    CONN = LoggerFactory.getLogger("lpush.conn.log"),

    MONITOR = LoggerFactory.getLogger("lpush.monitor.log"),

    PUSH = LoggerFactory.getLogger("lpush.push.log"),

    HB = LoggerFactory.getLogger("lpush.heartbeat.log"),

    CACHE = LoggerFactory.getLogger("lpush.cache.log"),

    RSD = LoggerFactory.getLogger("lpush.srd.log"),

    HTTP = LoggerFactory.getLogger("lpush.http.log"),

    PROFILE = LoggerFactory.getLogger("lpush.profile.log");
}
