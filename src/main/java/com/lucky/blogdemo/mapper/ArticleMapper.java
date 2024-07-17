package com.lucky.blogdemo.mapper;

import com.lucky.blogdemo.model.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lucky
* @description 针对表【article(文章信息表)】的数据库操作Mapper
* @createDate 2024-07-17 15:54:23
* @Entity com.lucky.blogdemo.model.entity.Article
*/
public interface ArticleMapper extends BaseMapper<Article> {

}




