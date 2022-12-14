package top.charles7c.api.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentInfo;
import cn.hutool.http.useragent.UserAgentUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 浏览器工具类
 *
 * @author Charles7c
 * @since 2022/1/14 22:21
 */
public class BrowserUtil {

    /**
     * 获取浏览器及其版本信息
     *
     * @param request 请求信息
     * @return 浏览器及其版本信息
     */
    public static String getBrowser(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String userAgentHeader = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgentUtil.parse(userAgentHeader);
        if (UserAgentInfo.NameUnknown.equals(userAgent.getBrowser().getName())) {
            return userAgentHeader;
        }
        return StrUtil.format("{} {}", userAgent.getBrowser().getName(), userAgent.getVersion());
    }

}
