package top.charles7c.api.blog.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 页面浏览量
 *
 * @author Charles7c
 * @since 2022/11/27 21:56
 */
@Data
@TableName("blog_page_view")
@Accessors(chain = true)
@ToString(callSuper = true)
public class BlogPageView implements Serializable {

    /** 主键 */
    @TableId
    private Long id;
    /** 页面URL */
    private String pageUrl;
    /** 访客IP */
    private String visitorIp;
    /** IP归属地 */
    private String address;
    /** 浏览器 */
    private String browser;
    /** 创建时间 */
    private Date createTime;
}