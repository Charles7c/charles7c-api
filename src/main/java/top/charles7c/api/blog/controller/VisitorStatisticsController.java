package top.charles7c.api.blog.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.charles7c.api.blog.pojo.vo.R;
import top.charles7c.api.blog.service.VisitorStatisticsService;
import top.charles7c.api.config.exception.BadRequestException;
import top.charles7c.api.enums.SystemCodeEnum;

/**
 * 访客统计 API
 *
 * @author Charles7c
 * @since 2022/7/28 23:08
 */
@Tag(name = "访客统计 API")
@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class VisitorStatisticsController {

    private final VisitorStatisticsService visitorStatisticsService;

    @Operation(summary = "记录页面浏览量")
    @Parameter(name = "pageUrl", description = "页面URL", in = ParameterIn.QUERY)
    @GetMapping("/pv")
    public R getPv(@RequestParam String pageUrl) {
        visitorStatisticsService.getPv(pageUrl);
        return R.ok(1);
    }

    @Operation(summary = "查询文章阅读数")
    @Parameter(name = "articleId", description = "文章ID", in = ParameterIn.PATH)
    @Parameter(name = "pageUrl", description = "页面URL", in = ParameterIn.QUERY)
    @GetMapping("/article/view/{articleId}")
    public R<Long> getArticleViewCount(@PathVariable String articleId, @RequestParam String pageUrl) {
        if (StrUtil.isBlank(pageUrl) || !(HttpUtil.isHttp(pageUrl) || HttpUtil.isHttps(pageUrl))) {
            throw new BadRequestException(SystemCodeEnum.PARAM_VALID_ERROR.getMsg());
        }
        return R.ok(visitorStatisticsService.getArticleViewCount(articleId, pageUrl));
    }
}
