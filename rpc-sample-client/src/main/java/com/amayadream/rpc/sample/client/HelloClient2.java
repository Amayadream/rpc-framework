package com.amayadream.rpc.sample.client;

import com.amayadream.rpc.client.RpcProxy;
import com.amayadream.rpc.sample.api.HelloService;
import com.amayadream.rpc.sample.api.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author :  Amayadream
 * @date :  2016.07.19 20:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class HelloClient2 {

    @Autowired
    private RpcProxy rpcProxy;

    @Test
    public void test(){
        HelloService helloService = rpcProxy.create(HelloService.class);
        String result = helloService.hello(new Person("Yong", "Huang"));
        System.out.println(result);
    }

}
