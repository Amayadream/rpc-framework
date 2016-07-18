package com.amayadream.rpc.registry.zookeeper;

import com.amayadream.rpc.registry.ServiceRegistry;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于ZooKeeper的服务注册接口实现
 * @author :  Amayadream
 * @date :  2016.07.18 22:33
 */
public class ZooKeeperServiceRegistry implements ServiceRegistry {

    private static final Logger logger = LoggerFactory.getLogger(ZooKeeperServiceRegistry.class);

    private final ZkClient zkClient;

    public ZooKeeperServiceRegistry(String zkAddress) {
        //创建ZooKeeper客户端
        zkClient = new ZkClient(zkAddress, Constant.ZK_SESSION_TIMEOUT, Constant.ZK_CONNECTION_TIMEOUT);
        logger.debug("connect zookeeper");
    }

    @Override
    public void register(String serviceName, String serviceAddress) {
        //创建registry节点(持久)
        String registryPath = Constant.ZK_REGISTRY_PATH;
        if (!zkClient.exists(registryPath)) {
            zkClient.createPersistent(registryPath);
            logger.debug("create registry node: {}", registryPath);
        }
        //创建service节点(持久)
        String servicePath = registryPath + "/" + serviceName;
        if (!zkClient.exists(servicePath)) {
            zkClient.createPersistent(servicePath);
            logger.debug("create service node: {}", servicePath);
        }
        //创建address节点(临时)
        String addressPath = servicePath + "/address-";
        String addressNode = zkClient.createEphemeralSequential(addressPath, serviceAddress);
        logger.debug("create address node: {}", addressNode);
    }
}
