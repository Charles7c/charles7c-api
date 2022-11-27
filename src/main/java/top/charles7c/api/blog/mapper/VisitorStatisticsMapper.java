package top.charles7c.api.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.charles7c.api.blog.pojo.domain.VisitorStatistics;

/**
 * 访客统计Mapper
 *
 * @author Charles7capp
 * @since 2022/7/28 23:50
 */
@Mapper
public interface VisitorStatisticsMapper extends BaseMapper<VisitorStatistics> {

}