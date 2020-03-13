package com.nova.lyn.service;

public interface ServiceNode {

    String serviceName();

    String nodeId();

    String getHost();

    Integer getPort();

    default boolean isPersistant(){return false;}

    default <T> T getAddr() {return null;}

    default String getHostAndPort() {return getHost()+":"+getPort();}

    default String getNodePath() {return serviceName()+"/"+nodeId();}
}
