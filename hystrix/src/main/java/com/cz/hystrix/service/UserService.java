package com.cz.hystrix.service;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import comm.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author cz
 * @since 2020/10/25
 */
@Service
public class UserService {

    @Resource
    private RestTemplate restTemplate;

    @HystrixCommand
    public List<User> getUserByIds(List<Long> ids){
        String url="http://provider/user/{1}";
        User[] user=restTemplate.getForObject(url,User[].class, StringUtils.join(ids,","));
        return Arrays.asList(user);

    }

    /**
     * 通过注解的方式，请求等待时间200ms
     * @param l
     * @return
     */
    @HystrixCollapser(batchMethod ="getUserByIds",collapserProperties = {@HystrixProperty(name="timerDelayInMilliseconds",value ="200")})
    public Future<User> getUserById(Long l) {
        return null;
    }
}
