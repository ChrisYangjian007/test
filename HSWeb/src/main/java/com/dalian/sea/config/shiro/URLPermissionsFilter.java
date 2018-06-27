package com.dalian.sea.config.shiro;

import com.dalian.sea.service.BaUserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author 杨文波
 * @date 2017/12/11
 */
@Component
public class URLPermissionsFilter extends PermissionsAuthorizationFilter {

    @Autowired
    private BaUserService baUserService;

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws IOException {
        String curUrl = getRequestUrl(request);
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null || StringUtils.endsWithAny(curUrl, ".js", ".css", ".json")
                || StringUtils.endsWithAny(curUrl, ".jpg", ".png", ".gif", ".jpeg")
                || StringUtils.endsWithAny(curUrl, "/403.htm","/index.htm","/login.htm")) {
            return true;
        }
//        List<String> urls = seaUserService.findPermissionUrl(subject.getPrincipal().toString());
//        return urls.contains(curUrl);
        return true;
    }

    /**
     * 获取当前URL+Parameter
     *
     * @author lance
     * @since 2014年12月18日 下午3:09:26
     * @param request 拦截请求request
     * @return 返回完整URL
     */
    private String getRequestUrl(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        String queryString = req.getQueryString();

        queryString = StringUtils.isBlank(queryString) ? "" : "?" + queryString;
        return req.getRequestURI() + queryString;
    }
}