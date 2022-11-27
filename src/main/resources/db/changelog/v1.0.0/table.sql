-- liquibase formatted sql

-- changeset charles7c_add_table:20221127-1
CREATE TABLE IF NOT EXISTS `blog_article_view`  (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `article_id` varchar(255) NOT NULL COMMENT '文章ID',
    `visitor_ip` varchar(255) NOT NULL COMMENT '访客IP',
    `address` varchar(255) DEFAULT NULL COMMENT 'IP归属地',
    `browser` varchar(255) DEFAULT NULL COMMENT '浏览器',
    `pageUrl` varchar(255) DEFAULT NULL COMMENT '页面URL',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '文章阅读数';

CREATE TABLE IF NOT EXISTS `blog_page_view`  (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `pageUrl` varchar(255) NOT NULL COMMENT '页面URL',
    `visitor_ip` varchar(255) NOT NULL COMMENT '访客IP',
    `address` varchar(255) DEFAULT NULL COMMENT 'IP归属地',
    `browser` varchar(255) DEFAULT NULL COMMENT '浏览器',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '页面浏览量';