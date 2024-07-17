package com.lucky.blogdemo.model.article;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lucky
 * @date 2024/7/17
 * @description
 */
@Data
public class PostAddRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;
}
