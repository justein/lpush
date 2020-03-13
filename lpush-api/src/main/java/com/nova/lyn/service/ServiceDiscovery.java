package com.nova.lyn.service;

import com.nova.lyn.listener.ServiceListener;

import java.util.List;

/***
 * @ClassName: ServiceDiscovery
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/5 上午11:03
 * @version : V1.0
 */
public interface ServiceDiscovery extends Service {

    List<ServiceNode> lookup(String path);

    void subscribe(String path, ServiceListener serviceListener);

    void unsubscribe(String path, ServiceListener serviceListener);
}
