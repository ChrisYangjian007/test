package com.dalian.sea.config.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ShiroRequestUrl
 *
 * @author xintao
 * @date 2017/12/21
 */
public class ShiroRequestUrl extends AccessControlFilter {

    /**
     * INDEX地址
     */
    private static final String INDEX = "/index.htm";
    /**
     * 403
     */
    private static final String Forbidden = "/error/403.htm";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        Subject user = SecurityUtils.getSubject();
        String url = getPathWithinApplication(request);
        if (url.endsWith(".htm")) {
            if (user.getPrincipal() == null || StringUtils.endsWithAny(url,"downGenerateZip.htm","index.htm","/details.htm","/news.htm","/kitchen.htm","/check.htm","/check.htm","/product.htm","/culture.htm","403.htm","404.htm","500.htm","login.htm","logout.htm","AccordionPage.htm","config.htm","Modal.htm","IFrame.htm","downOrder.htm")) {
                return true;
            }
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            Set<String> urlSet=shiroUser.getUrlSet();
            for (String s : urlSet) {
                if (url.endsWith(s)) {
                    return true;
                }
            }
        }else {
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject user = SecurityUtils.getSubject();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!user.isAuthenticated()) {
            response.sendRedirect(request.getContextPath() + INDEX);
        }else {
            response.sendRedirect(request.getContextPath() + Forbidden);
        }
        return false;
    }

}
