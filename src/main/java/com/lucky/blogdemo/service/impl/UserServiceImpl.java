package com.lucky.blogdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.blogdemo.model.entity.User;
import com.lucky.blogdemo.service.UserService;
import com.lucky.blogdemo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author lucky
* @description 针对表【user(用户信息表)】的数据库操作Service实现
* @createDate 2024-07-17 15:55:19
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}




