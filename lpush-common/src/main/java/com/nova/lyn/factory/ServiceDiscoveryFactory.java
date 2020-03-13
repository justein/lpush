package com.nova.lyn.factory;

import com.nova.lyn.service.ServiceDiscovery;
import com.nova.lyn.spi.SPI;
import com.nova.lyn.spi.SPILoader;

/***
 * @ClassName: ServiceDiscoveryFactory
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/5 下午4:42
 * @version : V1.0
 */

public interface ServiceDiscoveryFactory extends Factory<ServiceDiscovery>{

    static ServiceDiscovery create(){return SPILoader.load(ServiceDiscoveryFactory.class).get();}

}
