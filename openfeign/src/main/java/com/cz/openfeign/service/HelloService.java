package com.cz.openfeign.service;

import api.UserService;
import com.cz.openfeign.fback.HelloServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider",fallback = HelloServiceFallBack.class)
public interface HelloService extends UserService {


}