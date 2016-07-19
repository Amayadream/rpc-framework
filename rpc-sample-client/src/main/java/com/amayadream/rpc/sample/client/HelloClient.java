package com.amayadream.rpc.sample.client;

import com.amayadream.rpc.client.RpcProxy;
import com.amayadream.rpc.sample.api.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author :  Amayadream
 * @date :  2016.07.19 20:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class HelloClient {

    @Autowired
    private RpcProxy rpcProxy;

    @Test
    public void test(){
        HelloService helloService = rpcProxy.create(HelloService.class);
        String result = helloService.hello("World");
        System.out.println(result);

        HelloService helloService2 = rpcProxy.create(HelloService.class, "sample.hello2");
        String result2 = helloService2.hello("世界");
        System.out.println(result2);
    }

}
