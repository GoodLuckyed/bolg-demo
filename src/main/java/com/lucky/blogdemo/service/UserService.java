package com.lucky.blogdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.blogdemo.model.entity.User;
import com.lucky.blogdemo.model.user.UserLoginRequest;
import com.lucky.blogdemo.model.user.UserRegisterRequest;
import jakarta.servlet.http.HttpServletRequest;


/**
* @author lucky
* @description 针对表【user(用户信息表)】的数据库操作Service
* @createDate 2024-07-17 15:55:19
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userRegisterRequest
     * @return
     */
    long register(UserRegisterRequest userRegisterRequest);


    /**
     * 用户登录
     * @param userLoginRequest
     * @return
     */
    String login(UserLoginRequest userLoginRequest, HttpServletRequest request);
}
