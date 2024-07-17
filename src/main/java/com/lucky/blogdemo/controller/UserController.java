package com.lucky.blogdemo.controller;

import com.lucky.blogdemo.common.BaseResponse;
import com.lucky.blogdemo.common.ErrorCode;
import com.lucky.blogdemo.common.ResultUtils;
import com.lucky.blogdemo.exception.BusinessException;
import com.lucky.blogdemo.model.user.UserLoginRequest;
import com.lucky.blogdemo.model.user.UserRegisterRequest;
import com.lucky.blogdemo.model.user.UserVo;
import com.lucky.blogdemo.service.UserService;
import com.lucky.blogdemo.utils.UserHolder;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author lucky
 * @date 2024/7/17
 * @description
 */

@RestController
@RequestMapping("/auth")
public class UserController {

    @Resource
    private UserService userService;

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long result = userService.register(userRegisterRequest);
        return ResultUtils.success(result);
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public BaseResponse<String> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if (userLoginRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String result = userService.login(userLoginRequest,request);
        return ResultUtils.success(result);
    }

    @Operation(summary = "获取当前用户信息")
    @GetMapping("/me")
    public BaseResponse<UserVo> getUserInfo(){
        UserVo user = UserHolder.getUser();
        if (user == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(user);
    }
}
