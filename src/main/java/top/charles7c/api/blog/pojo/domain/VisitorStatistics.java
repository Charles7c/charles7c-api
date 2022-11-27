package top.charles7c.api.blog.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 访客统计
 *
 * @author Charles7c
 * @since 2022/7/28 23:45
 */
@Data
@TableName("blog_visitor_statistics")
@Accessors(chain = true)
@ToString(callSuper = true)
public class VisitorStatistics implements Serializable {

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
    /** 来源 */
    private String referer;
    /** 创建时间 */
    private Date createTime;
}