package com.cz.openfeign.controller;

import com.cz.openfeign.service.HelloService;
import comm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("hello")
    public String getHello(@RequestParam("name") String name){

        User user = new User();
        user.setId(1L);
        user.setName("cz");
        helloService.addUser(user);

        helloService.deleteUser(1L);
        try {
            helloService.getByName(URLEncoder.encode("程泽","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return helloService.getHello(name);
    }
}
