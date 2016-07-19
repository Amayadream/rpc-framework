package com.amayadream.rpc.sample.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author :  Amayadream
 * @date :  2016.07.18 23:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class RpcBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(RpcBootstrap.class);

    @Test
    public void test(){
        logger.debug("start server");
    }


}
