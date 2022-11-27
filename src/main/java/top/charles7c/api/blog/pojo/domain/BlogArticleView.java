package top.charles7c.api.blog.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章阅读数
 *
 * @author Charles7c
 * @since 2022/11/27 21:56
 */
@Data
@TableName("blog_article_view")
@Accessors(chain = true)
@ToString(callSuper = true)
public class BlogArticleView implements Serializable {

    /** 主键 */
    @TableId
    private Long id;
    /** 文章ID */
    private String articleId;
    /** 访客IP */
    private String visitorIp;
    /** IP归属地 */
    private String address;
    /** 浏览器 */
    private String browser;
    /** 页面URL */
    private String pageUrl;
    /** 创建时间 */
    private Date createTime;
}