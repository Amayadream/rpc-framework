package com.amayadream.rpc.sample.api;

/**
 * @author :  Amayadream
 * @date :  2016.07.14 22:07
 */
public interface HelloService {

    String hello(String name);

    String hello(Person person);

}
