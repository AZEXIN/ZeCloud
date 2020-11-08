package com.cz.provider;

import api.UserService;
import com.cz.provider.service.HelloWordServiceImpl;
import comm.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author cz
 * @since 2020/10/16
 */
@RestController
public class HelloWorldController implements UserService {

    @Resource
    private HelloWordServiceImpl helloWordService;
    @Value("${server.port}")
    private String port;
    @GetMapping("/hello")
    public String getHello(@RequestParam("name") String name){
        return helloWordService.getHello()+port+"--"+name+"--"+System.currentTimeMillis();
    }

    @GetMapping("user/{ids}")
    public List<User> getUser(@PathVariable("ids") String ids){
        return helloWordService.getUser(ids);
    }


    @PostMapping("user")
    public void addUser(@RequestBody User user){
        System.out.println("添加用户--"+user.toString());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long uerId){
        System.out.println("删除用户"+uerId);
    }

    @GetMapping("/user")
    public void getByName(@RequestHeader("name") String name) throws UnsupportedEncodingException {
        System.out.println("---"+ URLDecoder.decode(name,"UTF-8"));
    }
}
