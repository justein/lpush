package com.nova.lyn.base;

import com.nova.lyn.factory.ServiceRegistryFactory;
import com.nova.lyn.log.Logs;
import com.nova.lyn.service.ServiceRegistry;
import sun.rmi.runtime.Log;

/***
 * @ClassName: ServiceRegistryBoot
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/12 上午9:48
 * @version : V1.0
 */
public class ServiceRegistryBoot extends BootService {
    @Override
    public void start() {
        Logs.Console.info("init zk registry service and waiting for connect...");
        ServiceRegistryFactory.create().syncStart();
        startNext();
    }

    @Override
    public void stop() {
        stopNext();
        Logs.Console.info("stop the zk registry service...");
        ServiceRegistryFactory.create().syncStop();
    }
}
