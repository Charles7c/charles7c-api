package top.charles7c.api.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.charles7c.api.blog.pojo.domain.BlogArticleView;

/**
 * 文章阅读数 Mapper
 *
 * @author Charles7c
 * @since 2022/11/27 22:03
 */
@Mapper
public interface BlogArticleViewMapper extends BaseMapper<BlogArticleView> {

}