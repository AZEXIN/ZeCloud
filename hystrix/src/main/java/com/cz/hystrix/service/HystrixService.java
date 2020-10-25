package com.cz.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
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
    @CacheResult
    public String getHello(String name){
        String url="http://provider/hello?name={1}";
        return restTemplate.getForObject(url,String.class,name);
    }

    public String error(String name,Throwable t){
        // 可以从缓存中获取数据
        System.out.println(t.getMessage());
        return "error";
    }
}
