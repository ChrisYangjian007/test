package com.dalian.sea.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * layout布局控制
 * @author 杨文波
 * @date 2017/11/30
 */
@Aspect
public class MyAspectj {

    @Value("${myLayout.contentPath}")
    private String contentPath;

    @Value("${myLayout.decoratorPath}")
    private String decoratorPath;

    @Value("${myLayout.excludedPaths}")
    private String excludedPaths;

    @Autowired
    DataSourceTransactionManager transactionManager;

    @Pointcut("within(@org.springframework.stereotype.Controller com.dalian.sea.controller.*)")
    public void page(){}

    @Around("page()")
    public Object boforeGoToPage(ProceedingJoinPoint pj) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        Object res = pj.proceed();
        if (res instanceof String || res instanceof ModelAndView){
            if (url.endsWith(contentPath)){
                Object result = res;
                String[] i = excludedPaths.split(",");
                for (String path:i){
                    if (url.endsWith(path)){
                        return res;
                    }
                }
                return setInclude(request,res);
            }else {
                return res;
            }
        }else {
            return res;
        }
    }

    public Object setInclude(HttpServletRequest request,Object res){
        if (res instanceof ModelAndView){
            String page = ((ModelAndView) res).getViewName();
            if (page.substring(0,9).equals("redirect:")){
                return res;
            }
            setPage(request,page);
            ((ModelAndView) res).setViewName(decoratorPath);
        }else {
            String page = (String) res;
            if (page.substring(0,9).equals("redirect:")){
                return res;
            }
            setPage(request,page);
            res = decoratorPath;
        }
        return res;
    }

    public void setPage(HttpServletRequest request,String page){
        if (null==page||page.equals("")){
            request.setAttribute("page","/layout/noSetPage.ftl");
        }else {
            request.setAttribute("page", generateUrl(page));
        }
    }

    public String generateUrl(String page){
        StringBuffer sb = new StringBuffer();
        if (page.substring(0, 1).equals("/")) {
            sb.append(page);
            sb.append(".ftl");
        } else {
            sb.append("/");
            sb.append(page);
            sb.append(".ftl");
        }
        return sb.toString();
    }
}
