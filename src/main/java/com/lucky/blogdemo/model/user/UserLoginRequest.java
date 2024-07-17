package com.lucky.blogdemo.model.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lucky
 * @date 2024/7/17
 * @description 登录请求
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
}
