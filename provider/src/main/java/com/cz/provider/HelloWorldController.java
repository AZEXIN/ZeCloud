package com.cz.provider;

import com.cz.provider.service.HelloWordServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author cz
 * @since 2020/10/16
 */
@RestController
public class HelloWorldController {

    @Resource
    private HelloWordServiceImpl helloWordService;
    @Value("${server.port}")
    private String port;
    @GetMapping("/hello")
    public String getHello(){
        return helloWordService.getHello()+port;
    }
}
