package com.dalian.sea.controller;

import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.model.BaResource;
import com.dalian.sea.model.BaUser;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.parameter.PBaUser;
import com.dalian.sea.parameter.PSysLogSys;
import com.dalian.sea.service.BaResourceService;
import com.dalian.sea.service.BaUserService;
import com.dalian.sea.service.SysSysLogService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author YH
 */
@Controller
@RequestMapping("/SysLog")
@Slf4j
public class SysLogController extends LayoutRazor {

    @Autowired
    private SysSysLogService sysSysLogService;

    @Autowired
    private BaUserService baUserService;

    @Autowired
    private BaResourceService baResourceService;

    /**
     * 日志管理
     */
    @RequestMapping("/sysLogManager.htm")
    public String SysLogManage(HttpServletRequest request) {
        Subject user = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
        List<BaUser> baUserList = baUserService.getAllUserBySysLog(shiroUser.getCompanyId());
        if (null != baUserList && 0 != baUserList.size()) {
            request.setAttribute("baUserList", baUserList);
        }
        List<BaResource> baResourceList = baResourceService.getAllBaResourceBySysLog(shiroUser.getCompanyId());
        if (null != baResourceList && 0 != baResourceList.size()) {
            request.setAttribute("baResourceList", baResourceList);
        }
        return freeMarkerIndexResult("sysLog/sysLogManager.ftl", request);
    }

    /**
     * 日志分页jqGrid
     * @param request
     * @param pSysLogSys
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request, PSysLogSys pSysLogSys, Integer page, Integer rows) {
        Subject user = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
        List<PSysLogSys> logRolesList;
        if (new Long("0").equals(shiroUser.getCompanyId())){
            logRolesList = sysSysLogService.getSysSysLogByJqGrid(pSysLogSys,shiroUser.getId(),page,rows);
        }else {
            logRolesList = sysSysLogService.getSysSysLogByJqGrid(pSysLogSys,null,page,rows);
        }
        PageInfo<PSysLogSys> pageInfo = new PageInfo<>(logRolesList);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),logRolesList);
    }

}
