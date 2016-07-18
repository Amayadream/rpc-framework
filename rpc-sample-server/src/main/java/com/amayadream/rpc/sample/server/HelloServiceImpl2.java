package com.amayadream.rpc.sample.server;

import com.amayadream.rpc.sample.api.HelloService;
import com.amayadream.rpc.sample.api.Person;
import com.amayadream.rpc.server.RpcService;

/**
 * @author :  Amayadream
 * @date :  2016.07.18 23:10
 */
@RpcService(value = HelloService.class, version = "sample.hello2")
public class HelloServiceImpl2 implements HelloService {
    @Override
    public String hello(String name) {
        return "你好! " + name;
    }

    @Override
    public String hello(Person person) {
        return "你好! " + person.getFirstName() + " " + person.getLastName();
    }

}
