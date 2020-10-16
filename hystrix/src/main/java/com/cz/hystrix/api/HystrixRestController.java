package com.cz.hystrix.api;

import com.cz.hystrix.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cz
 * @since 2020/10/16
 */
@RestController
public class HystrixRestController {

    @Autowired
    private HystrixService hystrixService;

    @GetMapping("hystrix")
    public String getHello(){
        return hystrixService.getHello();
    }

}
