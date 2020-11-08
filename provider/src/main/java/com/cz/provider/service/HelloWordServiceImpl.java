package com.cz.provider.service;

import com.google.common.collect.Lists;
import comm.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cz
 * @since 2020/10/16
 */
@Service
public class HelloWordServiceImpl {

    public String getHello(){
        return "Hello world";
    }

    public List<User> getUser(String ids){
        String[] idArray=ids.split(",");
        List<User> users=Lists.newArrayList();
        for (String item : idArray){
            User user=new User();
            user.setId(Long.valueOf(item));
            user.setName("cz"+item);
            users.add(user);
        }
        return users;
    }


}
