package com.cz.hystrix.api;

import com.cz.hystrix.collapse.UserCollapseCommand;
import com.cz.hystrix.service.HystrixService;
import com.cz.hystrix.service.UserService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import comm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author cz
 * @since 2020/10/16
 */
@RestController
public class HystrixRestController {

    @Autowired
    private HystrixService hystrixService;

    /**
     * 请求缓存
     * @param name
     */
    @GetMapping("hystrix")
    public void getHello(String name){
        HystrixRequestContext context=HystrixRequestContext.initializeContext();
        String str=hystrixService.getHello(name);
        String str2=hystrixService.getHello(name);
        System.out.println("1--"+str);
        System.out.println("2--"+str2);
        context.close();
        //return str;
    }

    /**
     * 请求合并
     */
    @Resource
    private UserService userService;

    /**
     * command 方式
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("users")
    public void  getUsers() throws ExecutionException, InterruptedException {
        HystrixRequestContext context=HystrixRequestContext.initializeContext();
        UserCollapseCommand userCollapseCommand1=new UserCollapseCommand(userService,1L);
        UserCollapseCommand userCollapseCommand2=new UserCollapseCommand(userService,2L);
        UserCollapseCommand userCollapseCommand3=new UserCollapseCommand(userService,3L);
        UserCollapseCommand userCollapseCommand4=new UserCollapseCommand(userService,4L);

        Future<User> future1=userCollapseCommand1.queue();
        Future<User> future2=userCollapseCommand2.queue();
        Future<User> future3=userCollapseCommand3.queue();
        Future<User> future4=userCollapseCommand4.queue();

        User user1=future1.get();
        User user2=future2.get();
        User user3=future3.get();
        User user4=future4.get();
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);
        context.close();
    }

    @GetMapping("users2")
    public void  getUsers2() throws ExecutionException, InterruptedException {
        HystrixRequestContext context=HystrixRequestContext.initializeContext();

        Future<User> future1=userService.getUserById(1L);
        Future<User> future2=userService.getUserById(2L);
        Future<User> future3=userService.getUserById(3L);


        User user1=future1.get();
        User user2=future2.get();
        User user3=future3.get();

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        Thread.sleep(4000);
        Future<User> future4=userService.getUserById(4L);
        User user4=future4.get();
        System.out.println(user4);
        context.close();
    }





}
