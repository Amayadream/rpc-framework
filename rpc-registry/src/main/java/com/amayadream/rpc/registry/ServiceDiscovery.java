package com.amayadream.rpc.registry;

/**
 * 服务发现接口
 * @author :  Amayadream
 * @date :  2016.07.18 22:30
 */
public interface ServiceDiscovery {

    /**
     * 根据服务名称查找服务地址
     * @param serviceName
     * @return
     */
    String discover(String serviceName);

}
