package com.lucky.blogdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.blogdemo.mapper.ArticleMapper;
import com.lucky.blogdemo.model.entity.Article;
import com.lucky.blogdemo.service.ArticleService;
import org.springframework.stereotype.Service;

/**
* @author lucky
* @description 针对表【article(文章信息表)】的数据库操作Service实现
* @createDate 2024-07-17 15:54:23
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{

}




