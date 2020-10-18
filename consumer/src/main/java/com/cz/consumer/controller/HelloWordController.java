package com.cz.consumer.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cz
 * @since 2020/10/16
 */
@RestController
public class HelloWordController {

    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("hello")
    public String getHelloWord(){
        String url="http://provider/hello";
        return restTemplate.getForObject(url,String.class);

    }
}
