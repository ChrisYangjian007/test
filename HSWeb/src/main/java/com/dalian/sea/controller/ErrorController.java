package com.dalian.sea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * ErrorController
 *
 * @author TONE
 * @date 2018/4/23.
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/403.htm")
    public String error403(HttpServletRequest request) {
        return "error/403";
    }

    @RequestMapping("/404.htm")
    public String error404(HttpServletRequest request) {
        return "error/404";
    }

    @RequestMapping("/500.htm")
    public String error500(HttpServletRequest request) {
        return "error/500";
    }




}
