-- liquibase formatted sql

-- changeset charles7c_add_table:20220728-1
CREATE TABLE IF NOT EXISTS `blog_visitor_statistics`  (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `article_id` varchar(255) NOT NULL COMMENT '文章ID',
    `visitor_ip` varchar(255) NOT NULL COMMENT '访客IP',
    `address` varchar(255) DEFAULT NULL COMMENT 'IP归属地',
    `browser` varchar(255) DEFAULT NULL COMMENT '浏览器',
    `referer` varchar(255) DEFAULT NULL COMMENT '来源',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '访客统计';