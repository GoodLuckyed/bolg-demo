package com.lucky.blogdemo.interceptor;

import com.lucky.blogdemo.common.ErrorCode;
import com.lucky.blogdemo.exception.BusinessException;
import com.lucky.blogdemo.model.entity.User;
import com.lucky.blogdemo.model.user.UserVo;
import com.lucky.blogdemo.utils.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lucky
 * @date 2024/7/17
 * @description
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的token
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR,"未登录");
        }
        // 根据token获取用户信息
        User user = (User) request.getSession().getAttribute(token);
        if (user == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        UserHolder.saveUser(userVo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserHolder.removeUser();
    }
}
