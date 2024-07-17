package com.lucky.blogdemo.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.blogdemo.common.ErrorCode;
import com.lucky.blogdemo.exception.BusinessException;
import com.lucky.blogdemo.mapper.UserMapper;
import com.lucky.blogdemo.model.entity.User;
import com.lucky.blogdemo.model.user.UserLoginRequest;
import com.lucky.blogdemo.model.user.UserRegisterRequest;
import com.lucky.blogdemo.model.user.UserVo;
import com.lucky.blogdemo.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
* @author lucky
* @description 针对表【user(用户信息表)】的数据库操作Service实现
* @createDate 2024-07-17 15:55:19
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    /**
     * 盐值，混淆密码
     */
    public static final String SALT = "lucky";

    @Resource
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param userRegisterRequest
     * @return
     */
    @Override
    public long register(UserRegisterRequest userRegisterRequest) {
        String username = userRegisterRequest.getUsername();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String email = userRegisterRequest.getEmail();
        if (StringUtils.isAnyBlank(username,password)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名或密码不能为空");
        }
        if (username.length() > 20){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名长度太长");
        }
        if (password.length() < 8 || checkPassword.length() < 8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码长度不能小于8位");
        }
        if (StringUtils.isNotBlank(email) && email.length() > 20){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"邮箱长度太长");
        }
        // 密码和校验密码一致
        if (!password.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"两次输入密码不一致");
        }
        // 用户名不能重复
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"用户已存在");
        }

        // 密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((password + SALT).getBytes());

        // 保存到数据库
        User user = new User();
        user.setUsername(username);
        user.setPassword(encryptPassword);
        user.setEmail(email);
        int result = userMapper.insert(user);
        if (result <= 0){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"注册失败");
        }
        return user.getUserId();
    }


    /**
     * 用户登录
     * @param userLoginRequest
     * @return
     */
    @Override
    public String login(UserLoginRequest userLoginRequest, HttpServletRequest request) {
        String username = userLoginRequest.getUsername();
        String password = userLoginRequest.getPassword();
        if (StringUtils.isAnyBlank(username,password)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名或密码不能为空");
        }
        // 密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((password + SALT).getBytes());

        // 查询用户是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username).eq(User::getPassword,encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 敏感信息脱敏（去除密码）
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        // 存储信息到redis记录登录态
        String token = UUID.randomUUID().toString(true);
        request.getSession().setAttribute(token,userVo);
        return token;
    }
}




