package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*", "null"})//跨域
@RestController
public class userC {
    @Autowired
    UserMapper userMapper;

    @PostMapping ("/login")
    public Result<User> Logining(@RequestBody User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername()).last("limit 1");
        Long i = userMapper.selectCount(queryWrapper);
        if(i == 1) {
            //判断密码是否正确
            QueryWrapper<User> qWrapper = new QueryWrapper<>();
            qWrapper.eq("password", user.getPassword());
            User u = userMapper.selectOne(qWrapper);
            if(u == null) {
                return new Result<>(false, "用户不存在或密码错误", null);
            }else {
                return new Result<>(true, "登录成功", null);
            }
        }else {
            return new Result<>(false, "用户不存在或密码错误", null);
        }
    }
}
