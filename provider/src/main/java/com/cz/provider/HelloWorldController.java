package com.cz.provider;

import com.cz.provider.service.HelloWordServiceImpl;
import comm.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    public String getHello(@RequestParam("name") String name){
        String str=helloWordService.getHello()+port+"--"+name+"--"+System.currentTimeMillis();
        return str;
    }

    @GetMapping("user/{ids}")
    public List<User> getUser(@PathVariable("ids") String ids){
        return helloWordService.getUser(ids);
    }

}
