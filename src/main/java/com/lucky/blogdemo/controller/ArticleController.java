package com.lucky.blogdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.blogdemo.common.BaseResponse;
import com.lucky.blogdemo.common.ErrorCode;
import com.lucky.blogdemo.common.ResultUtils;
import com.lucky.blogdemo.exception.BusinessException;
import com.lucky.blogdemo.model.article.PostAddRequest;
import com.lucky.blogdemo.model.entity.Article;
import com.lucky.blogdemo.service.ArticleService;
import com.lucky.blogdemo.utils.UserHolder;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author lucky
 * @date 2024/7/17
 * @description
 */

@RestController
@RequestMapping
public class ArticleController {

    /**
     * 当前页
     */
    private long current = 1;

    /**
     * 每页数量
     */
    private long pageSize = 10;

    @Resource
    private ArticleService articleService;

    @Operation(summary = "创建文章")
    @PostMapping("/posts")
    public BaseResponse<Boolean> addPost(@RequestBody PostAddRequest postAddRequest){
        if (postAddRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = articleService.addPost(postAddRequest);
        return ResultUtils.success(result);
    }

    @Operation(summary = "获取用户文章列表")
    @GetMapping("/posts")
    public BaseResponse<Page<Article>> getPostList(@RequestParam(value = "uid") Long userId){
        if (userId == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getUserId,userId);
        // 按创建时间排序
        queryWrapper.orderByDesc(Article::getCreated);
        Page<Article> articlePage = articleService.page(new Page<>(current, pageSize), queryWrapper);
        return ResultUtils.success(articlePage);
    }

    @Operation(summary = "获取单篇文章详情")
    @GetMapping("/posts/{id}")
    public BaseResponse<Article> getPostById(@PathVariable Long postId){
        if (postId == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Article article = articleService.getById(postId);
        return ResultUtils.success(article);
    }

    @Operation(summary = "更新文章")
    @PutMapping("/posts/{id}")
    public BaseResponse<Boolean> updatePost(@PathVariable Long postId, @RequestBody PostAddRequest postAddRequest){
        if (postId == null || postAddRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = articleService.updatePost(postId,postAddRequest);
        return ResultUtils.success(result);
    }

    @Operation(summary = "删除文章")
    @DeleteMapping("/posts/{id}")
    public BaseResponse<Boolean> delPost(@PathVariable Long postId){
        if (postId == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Article article = articleService.getById(postId);
        if (!article.getUserId().equals(UserHolder.getUser().getUserId())){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean remove = articleService.removeById(postId);
        if (!remove){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(true);
    }
}