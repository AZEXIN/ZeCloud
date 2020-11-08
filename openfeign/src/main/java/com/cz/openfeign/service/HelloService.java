package com.cz.openfeign.service;

import api.UserService;
import comm.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("provider")
public interface HelloService extends UserService {


}