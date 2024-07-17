# Spring Boot 博客系统

##### 需求分析

- 做一个简单的后台博客系统，包含用户模块和文章模块。
- 用户模块包含：注册、登录、获取用户信息
- 文章模块包含：创建、查询、修改、删除

##### 技术选型

后台：

- Java 17
- Spring Boot 3.0.2
- Mysql
- MyBatis
- MyBatis-Plus
- knife4j
- Redis
- hutool

##### 数据库表设计

用户表：

```sql
create table `blog-demo`.user
(
    user_id       bigint auto_increment comment '用户id'
        primary key,
    username      varchar(128)                       not null comment '用户名',
    password      varchar(128)                       not null comment '用户密码',
    email         varchar(64)                        null comment '邮箱',
    created       datetime default CURRENT_TIMESTAMP null comment '创建时间',
    last_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete      tinyint  default 0                 null comment '是否删除'
)
    comment '用户信息表';


```



文章表：

```sql
create table `blog-demo`.article
(
    post_id       bigint auto_increment comment '文章id'
        primary key,
    title         varchar(128)                       not null comment '文章标题',
    content       varchar(1024)                      not null comment '文章内容',
    user_id       bigint                             not null comment '作者id',
    created       datetime default CURRENT_TIMESTAMP null comment '创建时间',
    last_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete      tinyint  default 0                 null comment '是否删除'
)
    comment '文章信息表';


```

##### 功能开发

- 初始化项目，引入相关依赖
- 自定义通用返回对象、错误码、业务异常类、封装全局异常处理器。
    - 引入knife4j，方便接口测试

1. 用户模块

    - 注册：校验参数、密码加密
    - 登录：校验用户名和密码、登录成功生成token令牌 ==》存放在服务器内存中 / 或者redis中
    - 获取个人信息 =》 从ThreadLocal中获取

   **添加登录校验拦截器**

2. 文章模块

    - 创建文章：校验参数、需要登录
    - 获取某个用户的文章列表：分页查询，添加分页拦截器
    - 获取单篇文章详情：根据id查询
    - 更新文章：校验登录用户和文章的作者是否是同一个人
    - 删除文章：同上

##### 测试
使用postman和knife4j接口文档进行简单测试
   