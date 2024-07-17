package com.lucky.blogdemo.model.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lucky
 * @date 2024/7/17
 * @description
 */
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

}
