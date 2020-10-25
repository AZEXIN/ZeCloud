package com.cz.hystrix.command;

import com.cz.hystrix.service.UserService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import comm.model.User;

import java.util.List;

/**
 * @author cz
 * @since 2020/10/25
 */
public class UserBatchCommand extends HystrixCommand<List<User>> {

    private List<Long> ids;
    private UserService userService;

    public UserBatchCommand(List<Long> ids,UserService userService) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("bachCommand")).andCommandKey(HystrixCommandKey.Factory.asKey("bachKey")));
        this.ids=ids;
        this.userService=userService;
    }


    @Override
    protected List<User> run() throws Exception {
        return userService.getUserByIds(ids);
    }
}
