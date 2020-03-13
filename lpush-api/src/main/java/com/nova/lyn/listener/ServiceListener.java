package com.nova.lyn.listener;

import com.nova.lyn.service.ServiceNode;

/***
 * @ClassName: ServiceListener
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/5 上午11:05
 * @version : V1.0
 */
public interface ServiceListener {

    void onServiceAdded(String path, ServiceNode serviceNode);
    void onServiceUpdated(String path, ServiceNode serviceNode);
    void onServiceRemoved(String path, ServiceNode serviceNode);
}
