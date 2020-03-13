package com.nova.lyn.zk;

import com.nova.lyn.factory.ServiceDiscoveryFactory;
import com.nova.lyn.service.ServiceDiscovery;
import com.nova.lyn.spi.SPI;

/***
 * @ClassName: ZKDiscoveryFactory
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/5 下午4:49
 * @version : V1.0
 */
@SPI(order = 1)
public final class ZKDiscoveryFactory implements ServiceDiscoveryFactory {
    @Override
    public ServiceDiscovery get() {
        return ZKRegistryAndDiscoveryFactory.INSTANCE;
    }
}
