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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author :  Amayadream
 * @date :  2016.07.19 20:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class HelloClient4 {

    @Autowired
    private RpcProxy rpcProxy;

    @Test
    public void test(){
        int threadNum = 10;
        int loopCount = 100;

        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        final CountDownLatch latch = new CountDownLatch(loopCount);

        try {
            long start = System.currentTimeMillis();

            for (int i = 0; i < loopCount; i++) {
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        HelloService helloService = rpcProxy.create(HelloService.class);
                        String result = helloService.hello("World");
                        System.out.println(result);
                        latch.countDown();
                    }
                });
            }
            latch.await();

            long time = System.currentTimeMillis() - start;
            System.out.println("thread: " + threadNum);
            System.out.println("loop: " + loopCount);
            System.out.println("time: " + time + "ms");
            System.out.println("tps: " + (double) loopCount / ((double) time / 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

}
