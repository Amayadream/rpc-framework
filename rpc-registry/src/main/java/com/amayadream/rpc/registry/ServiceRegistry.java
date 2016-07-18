package com.amayadream.rpc.registry;

/**
 * 服务注册接口
 * @author :  Amayadream
 * @date :  2016.07.18 22:28
 */
public interface ServiceRegistry {

    /**
     * 注册服务名称与服务地址
     * @param serviceName
     * @param serviceAddress
     */
    void register(String serviceName, String serviceAddress);

}
