-- liquibase formatted sql

-- changeset charles7c_add_column:20221129-1
ALTER TABLE `blog_page_view` ADD COLUMN `referer` varchar(255) DEFAULT NULL COMMENT 'Referer' AFTER `browser`;
ALTER TABLE `blog_article_view` ADD COLUMN `referer` varchar(255) DEFAULT NULL COMMENT 'Referer' AFTER `page_url`;