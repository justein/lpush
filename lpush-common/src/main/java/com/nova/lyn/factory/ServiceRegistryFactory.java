package com.nova.lyn.factory;

import com.nova.lyn.service.ServiceRegistry;
import com.nova.lyn.spi.SPILoader;


public interface ServiceRegistryFactory extends Factory<ServiceRegistry> {
    static ServiceRegistry create(){return SPILoader.load(ServiceRegistryFactory.class).get();}
}
