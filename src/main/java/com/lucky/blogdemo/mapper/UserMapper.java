package com.lucky.blogdemo.mapper;

import com.lucky.blogdemo.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lucky
* @description 针对表【user(用户信息表)】的数据库操作Mapper
* @createDate 2024-07-17 15:55:19
* @Entity com.lucky.blogdemo.model.entity.User
*/

public interface UserMapper extends BaseMapper<User> {

}




