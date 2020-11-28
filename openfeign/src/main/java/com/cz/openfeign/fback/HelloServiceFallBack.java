package com.cz.openfeign.fback;

import com.cz.openfeign.service.HelloService;
import comm.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Component
@RequestMapping("hello")
public class HelloServiceFallBack implements HelloService {
    @Override
    public String getHello(String name) {
        return "get hello error";
    }

    @Override
    public void addUser(User User) {
        System.out.println("add user error");
    }

    @Override
    public void deleteUser(Long userId) {
        System.out.println("delete user error");
    }

    @Override
    public void getByName(String name) throws UnsupportedEncodingException {
        System.out.println("get user by name error");
    }
}
