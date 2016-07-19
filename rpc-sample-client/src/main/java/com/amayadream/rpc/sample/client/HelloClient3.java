package com.amayadream.rpc.sample.client;

import com.amayadream.rpc.client.RpcProxy;
import com.amayadream.rpc.sample.api.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author :  Amayadream
 * @date :  2016.07.19 20:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class HelloClient3 {

    @Autowired
    private RpcProxy rpcProxy;

    @Test
    public void test(){
        int loopCount = 100;

        long start = System.currentTimeMillis();

        HelloService helloService = rpcProxy.create(HelloService.class);
        for (int i = 0; i < loopCount; i++) {
            String result = helloService.hello("World");
            System.out.println(result);
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("loop: " + loopCount);
        System.out.println("time: " + time + "ms");
        System.out.println("tps: " + (double) loopCount / ((double) time / 1000));
    }

}
