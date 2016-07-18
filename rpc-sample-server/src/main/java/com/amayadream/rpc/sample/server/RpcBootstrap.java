package com.amayadream.rpc.sample.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author :  Amayadream
 * @date :  2016.07.18 23:08
 */
public class RpcBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(RpcBootstrap.class);

    public static void main(String[] args) {
        logger.debug("start server");
        new ClassPathXmlApplicationContext("spring.xml");
    }

}
