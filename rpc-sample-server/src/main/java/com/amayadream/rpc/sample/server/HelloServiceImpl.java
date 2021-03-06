package com.amayadream.rpc.sample.server;

import com.amayadream.rpc.sample.api.HelloService;
import com.amayadream.rpc.sample.api.Person;
import com.amayadream.rpc.server.RpcService;

/**
 * @author :  Amayadream
 * @date :  2016.07.14 22:09
 */
@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }

    @Override
    public String hello(Person person) {
        return "Hello! " + person.getFirstName() + " " + person.getLastName();
    }

}
