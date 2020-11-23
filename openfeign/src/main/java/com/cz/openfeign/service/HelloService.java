package com.cz.openfeign.service;

import api.UserService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("provider")
public interface HelloService extends UserService {


}