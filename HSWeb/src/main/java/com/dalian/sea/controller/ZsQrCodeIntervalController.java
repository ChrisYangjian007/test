package com.dalian.sea.controller;

import com.dalian.sea.ClientIP;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.model.ZsQrCode;
import com.dalian.sea.model.ZsQrCodeInterval;
import com.dalian.sea.parameter.PZsQrCode;
import com.dalian.sea.parameter.PZsQrCodeInterval;
import com.dalian.sea.service.SysSysLogService;
import com.dalian.sea.service.ZsQrCodeIntervalService;
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
 * ZsQrCodeIntervalController
 *
 * @author TONE
 * @date 2018/3/19.
 */
@Slf4j
@Controller
@RequestMapping(value = "/qrCodeInterval")
public class ZsQrCodeIntervalController extends LayoutRazor{
    @Autowired
    private ZsQrCodeIntervalService qrCodeIntervalService;
    @Autowired
    private SysSysLogService sysSysLogService;




    /**
     * 二维码编码管理
     * @param request
     * @param qrCode
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request, PZsQrCode qrCode, Integer page, Integer rows) {
        List<PZsQrCodeInterval> qrCodeIntervals = qrCodeIntervalService.getQrCodeIntervalByQrCode(qrCode,page,rows);
        PageInfo<PZsQrCodeInterval> pageInfo = new PageInfo<>(qrCodeIntervals);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),qrCodeIntervals);
    }


    /**
     * 修改单位
     * @return
     */
    @RequestMapping(value = "/deleteQrCodeIntervalBy.json")
    @ResponseBody
    public Object deleteQrCodeIntervalBy(HttpServletRequest request,PZsQrCodeInterval qrCodeInterval,Long resourceId){
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            qrCodeInterval.setUpdateUserId(shiroUser.getId());
            PZsQrCodeInterval codeInterval = qrCodeIntervalService.getQrCodeIntervalByIntervalId(qrCodeInterval.getIntervalId());
            if (null!=codeInterval) {
                Boolean boo = qrCodeIntervalService.deleteQrCodeIntervalBy(qrCodeInterval);
                if (boo) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("删除生成编码记录");
                    sysLog.setRemark("删除编码(" + codeInterval.getStartCode() + "-" + codeInterval.getEndCode() + ")生成记录");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("删除成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("删除失败");
                }
            }else {
                json.setMsg("当前编码数据有误，无法删除！");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }




}
