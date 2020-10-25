package com.cz.hystrix.collapse;

import com.cz.hystrix.command.UserBatchCommand;
import com.cz.hystrix.service.UserService;
import com.google.common.collect.Lists;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import comm.model.User;

import java.util.Collection;
import java.util.List;

/**
 * @author cz
 * @since 2020/10/25
 */
public class UserCollapseCommand extends HystrixCollapser<List<User>,User,Long> {


    private UserService userService;
    private Long id;
    public UserCollapseCommand(UserService userService, Long id) {

        super(HystrixCollapser.Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("UserBatchCommand"))
                .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(200)));
        this.userService=userService;
        this.id=id;
    }


    /**
     * 请求参数
     * @return
     */
    @Override
    public Long getRequestArgument() {
        return id;
    }

    /**
     * 请求合并
     * @param collection
     * @return
     */
    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Long>> collection) {
        List<Long> ids= Lists.newArrayList();
        for (CollapsedRequest<User,Long> request: collection) {
            ids.add(request.getArgument());
        }
        return new UserBatchCommand(ids,userService);
    }

    /**
     * 响应
     * @param users
     * @param collection
     */
    @Override
    protected void mapResponseToRequests(List<User> users, Collection<CollapsedRequest<User, Long>> collection) {
        int count=0;
        for (CollapsedRequest<User,Long> request:collection) {
            request.setResponse(users.get(count++));
        }
    }
}
