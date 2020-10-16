package com.cz.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author cz
 * @since 2020/10/16
 */
@Service
public class HystrixService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * HystrixCommand   fallbackMethod  表示调用失败，采用error 方法
     * @return
     */
    @HystrixCommand(fallbackMethod = "error")
    public String getHello(){
        String url="";//provider 中的某个接口
        return restTemplate.getForObject("",String.class);
    }

    public String error(){
        // 可以从缓存中获取数据
        return "error";
    }
}
