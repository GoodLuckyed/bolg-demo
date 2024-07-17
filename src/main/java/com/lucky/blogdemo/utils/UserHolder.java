package com.lucky.blogdemo.utils;

import com.lucky.blogdemo.model.user.UserVo;

/**
 * @author lucky
 * @date 2024/7/17
 * @description
 */
public class UserHolder {
    private static final ThreadLocal<UserVo> tl = new ThreadLocal<>();

    public static void saveUser(UserVo userVo) {
        tl.set(userVo);
    }

    public static UserVo getUser() {
        return tl.get();
    }

    public static void removeUser() {
        tl.remove();
    }
}
