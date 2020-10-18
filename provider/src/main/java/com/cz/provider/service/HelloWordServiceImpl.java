package com.cz.provider.service;

import org.springframework.stereotype.Service;

/**
 * @author cz
 * @since 2020/10/16
 */
@Service
public class HelloWordServiceImpl {

    public String getHello(){
        return "Hello world";
    }
}
