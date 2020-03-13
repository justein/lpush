package com.nova.lyn.zk;

import com.nova.lyn.factory.ServiceRegistryFactory;
import com.nova.lyn.service.ServiceRegistry;
import com.nova.lyn.spi.SPI;

/***
 * @ClassName: ZkRegistryFactory
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/5 上午10:27
 * @version : V1.0
 */
@SPI(order = 1)
public final class ZkRegistryFactory implements ServiceRegistryFactory {
    @Override
    public ServiceRegistry get() {
        return ZKRegistryAndDiscoveryFactory.INSTANCE;
    }
}
