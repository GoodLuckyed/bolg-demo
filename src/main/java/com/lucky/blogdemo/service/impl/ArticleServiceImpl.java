package com.lucky.blogdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.blogdemo.common.ErrorCode;
import com.lucky.blogdemo.exception.BusinessException;
import com.lucky.blogdemo.mapper.ArticleMapper;
import com.lucky.blogdemo.model.article.PostAddRequest;
import com.lucky.blogdemo.model.entity.Article;
import com.lucky.blogdemo.model.user.UserVo;
import com.lucky.blogdemo.service.ArticleService;
import com.lucky.blogdemo.utils.UserHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author lucky
* @description 针对表【article(文章信息表)】的数据库操作Service实现
* @createDate 2024-07-17 15:54:23
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{

    /**
     * 创建文章
     * @param postAddRequest
     * @return
     */
    @Override
    public boolean addPost(PostAddRequest postAddRequest) {
        String title = postAddRequest.getTitle();
        String content = postAddRequest.getContent();
        if (StringUtils.isAnyBlank(title,content)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //获取登录的用户
        UserVo user = UserHolder.getUser();
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setUserId(user.getUserId());
        boolean result = this.save(article);
        if (!result){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return true;
    }

    /**
     * 更新文章
     * @param postId
     * @param postAddRequest
     * @return
     */
    @Override
    public boolean updatePost(Long postId, PostAddRequest postAddRequest) {
        Article article = this.getById(postId);
        if (article == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        String title = postAddRequest.getTitle();
        String content = postAddRequest.getContent();
        if (StringUtils.isNotBlank(title)){
            article.setTitle(title);
        }
        if (StringUtils.isNotBlank(content)){
            article.setContent(content);
        }
        // 权限判断
        UserVo user = UserHolder.getUser();
        if (!article.getUserId().equals(user.getUserId())){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean update = this.updateById(article);
        if (!update){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return update;
    }
}




