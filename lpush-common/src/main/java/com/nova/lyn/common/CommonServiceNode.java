package com.nova.lyn.common;

import com.nova.lyn.service.ServiceNode;

import java.util.Map;

/***
 * @ClassName: CommonServiceNode
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/12 上午10:23
 * @version : V1.0
 */
public class CommonServiceNode implements ServiceNode {

    private String host;

    private int port;

    private Map<String, Object> attrs;

    private transient String name;

    private transient String nodeId;

    private transient boolean persistent;

    @Override
    public String serviceName() {
        return null;
    }

    @Override
    public String nodeId() {
        return null;
    }

    @Override
    public String getHost() {
        return null;
    }

    @Override
    public Integer getPort() {
        return null;
    }

    @Override
    public boolean isPersistant() {
        return false;
    }

    @Override
    public <T> T getAddr() {
        return null;
    }

    @Override
    public String getHostAndPort() {
        return null;
    }

    @Override
    public String getNodePath() {
        return null;
    }
}
