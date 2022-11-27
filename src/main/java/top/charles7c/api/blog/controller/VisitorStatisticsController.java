package top.charles7c.api.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.charles7c.api.blog.service.VisitorStatisticsService;
import top.charles7c.api.blog.pojo.vo.R;

import javax.annotation.Resource;

/**
 * 访客统计 API
 *
 * @author Charles7c
 * @since 2022/7/28 23:08
 */
@Tag(name = "访客统计 API")
@RestController
@RequestMapping("/blog")
public class VisitorStatisticsController {

    @Resource
    private VisitorStatisticsService visitorStatisticsService;

    @Operation(summary = "获取文章阅读数")
    @Parameter(name = "articleId", description = "文章ID", in = ParameterIn.PATH)
    @GetMapping("/pv/{articleId}")
    public R<Long> getPv(@PathVariable String articleId) {
        return R.ok(visitorStatisticsService.getPv(articleId));
    }
}
