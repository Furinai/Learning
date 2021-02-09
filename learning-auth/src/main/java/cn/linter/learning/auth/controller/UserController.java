package cn.linter.learning.auth.controller;

import cn.linter.learning.auth.client.UserClient;
import cn.linter.learning.auth.entity.User;
import cn.linter.learning.common.entity.Result;
import cn.linter.learning.common.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 *
 * @author wangxiaoyang
 * @since 2021/1/21
 */
@RestController
@RequestMapping("oauth/user")
public class UserController {

    private final UserClient userClient;

    public UserController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping
    public Result<User> getUser(@RequestHeader("Authorization") String token) {
        String username = JwtUtil.getUsername(token);
        Result<User> result = userClient.queryUser(username);
        result.getData().setPassword(null);
        return result;
    }

    @PostMapping
    public Result<?> registerUser(@RequestBody User user) {
        return userClient.createUser(user);
    }

}