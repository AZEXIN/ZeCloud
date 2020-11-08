package api;

import comm.model.User;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

public interface UserService {

    @GetMapping("hello")
    String getHello(@RequestParam("name") String name);

    @PostMapping("user")
    void addUser(@RequestBody User User);

    @DeleteMapping("{id}")
    void deleteUser(@PathVariable("id") Long userId);

    @GetMapping("user")
    void getByName(@RequestHeader("name") String name) throws UnsupportedEncodingException;
}
