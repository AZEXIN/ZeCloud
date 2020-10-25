package com.cz.hystrix.api;

import com.cz.hystrix.service.HystrixService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cz
 * @since 2020/10/16
 */
@RestController
public class HystrixRestController {

    @Autowired
    private HystrixService hystrixService;

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




}
