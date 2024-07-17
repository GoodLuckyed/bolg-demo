package com.lucky.blogdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.blogdemo.model.article.PostAddRequest;
import com.lucky.blogdemo.model.entity.Article;

/**
* @author lucky
* @description 针对表【article(文章信息表)】的数据库操作Service
* @createDate 2024-07-17 15:54:23
*/
public interface ArticleService extends IService<Article> {

    /**
     * 创建文章
     * @param postAddRequest
     * @return
     */
    boolean addPost(PostAddRequest postAddRequest);

    /**
     * 更新文章
     * @param postId
     * @param postAddRequest
     * @return
     */
    boolean updatePost(Long postId, PostAddRequest postAddRequest);
}
