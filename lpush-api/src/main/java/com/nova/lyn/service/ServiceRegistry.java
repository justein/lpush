package com.nova.lyn.service;

/***
 * @ClassName: ServiceRegistry
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/5 上午9:59
 * @version : V1.0
 */
public interface ServiceRegistry extends Service {

    void register(ServiceNode serviceNode);

    void unregister(ServiceNode serviceNode);

}
