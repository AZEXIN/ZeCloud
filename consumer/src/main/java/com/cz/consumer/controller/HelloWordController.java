package com.cz.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author cz
 * @since 2020/10/16
 */
@RestController
public class HelloWordController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("hello")
    public String getHelloWord(){
        String url="http://provider/hello";
        return restTemplate.getForObject(url,String.class);

    }
}
