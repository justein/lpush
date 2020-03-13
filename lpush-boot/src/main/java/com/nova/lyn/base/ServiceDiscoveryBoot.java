package com.nova.lyn.base;

import com.nova.lyn.factory.ServiceDiscoveryFactory;
import com.nova.lyn.log.Logs;

/***
 * @ClassName: ServiceDiscoveryBoot
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/12 上午10:07
 * @version : V1.0
 */
public class ServiceDiscoveryBoot extends BootService {

    @Override
    public void start() {
        Logs.Console.info("init zk discovery service and waiting register...");
        ServiceDiscoveryFactory.create().syncStart();
        startNext();
    }

    @Override
    public void stop() {
        stopNext();
        Logs.Console.info("stop the zk discovery service...");
        ServiceDiscoveryFactory.create().syncStop();
    }
}
