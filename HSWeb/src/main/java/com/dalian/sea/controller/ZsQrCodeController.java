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
import com.dalian.sea.service.ZsQrCodeService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ZsQrCodeController
 *
 * @author TONE
 * @date 2018/3/19.
 */
@Slf4j
@Controller
@RequestMapping(value = "/qrCode")
public class ZsQrCodeController extends LayoutRazor{
    @Autowired
    private ZsQrCodeService qrCodeService;
    @Autowired
    private ZsQrCodeIntervalService qrCodeIntervalService;
    @Autowired
    private SysSysLogService sysSysLogService;


    /**
     * 二维码编码管理
     * @param request
     * @return
     */
    @RequestMapping(value = "/qrCodeManage.htm")
    public String qrCodeManage(HttpServletRequest request){
        return freeMarkerIndexResult("qrCode/qrCodeManage.ftl",request);
    }

    /**
     * 二维码编码管理
     * @param request
     * @return
     */
    @RequestMapping(value = "/qrCodeDetailModal.htm")
    public String qrCodeDetailModal(HttpServletRequest request,Long intervalId){
        PZsQrCodeInterval qrCodeInterval = qrCodeIntervalService.getQrCodeIntervalByIntervalId(intervalId);
        if (null!=qrCodeInterval){
            request.setAttribute("qrCodeInterval",qrCodeInterval);
        }
        return "qrCode/qrCodeDetailModal";
    }

    /**
     * 二维码编码明细
     * @param request
     * @param intervalId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request, Long intervalId, Integer page, Integer rows) {
        List<PZsQrCode> qrCodeList = qrCodeService.getQrCodeByIntervalId(intervalId,page,rows);
        PageInfo<PZsQrCode> pageInfo = new PageInfo<>(qrCodeList);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),qrCodeList);
    }



    /**
     * 下载区间内的编码
     * @param request
     * @return
     */
    @RequestMapping(value = "/downQRCode.json")
    @ResponseBody
    public Object downQRCode(HttpServletRequest request, HttpServletResponse response,Long intervalId,Long resourceId) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            PZsQrCodeInterval qrCodeInterval = qrCodeIntervalService.getQrCodeIntervalByIntervalId(intervalId);
            if (null!=qrCodeInterval) {
                qrCodeInterval.setUpdateUserId(shiroUser.getId());
                Boolean boo = qrCodeService.downQRCode(request, response, qrCodeInterval);
                if (boo) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("下载编码");
                    sysLog.setRemark("下载("+qrCodeInterval.getStartCode()+"-"+qrCodeInterval.getEndCode()+")编码");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("下载成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("下载失败");
                }
            }else {
                json.setMsg("该编码数据有误！");
            }
        }catch (Exception e){
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 添加编码
     * @param qrCodeInterval
     * @return
     */
    @RequestMapping(value = "/addQRCode.json")
    @ResponseBody
    public Object addQRCode(HttpServletRequest request,PZsQrCodeInterval qrCodeInterval,Long resourceId) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            qrCodeInterval.setCreateUserId(shiroUser.getId());
            PZsQrCodeInterval interval = qrCodeIntervalService.getQrCodeIntervalForNew();
            Long end;
            if (null!=interval){
                end = Long.valueOf(interval.getEndCode())+qrCodeInterval.getIntervalNumber();
            }else {
                end = qrCodeInterval.getIntervalNumber();
            }
            if (new Long("9999999999")>=end){
                Boolean boo = qrCodeService.addQRCode(qrCodeInterval);
                if (boo) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("添加编码");
                    sysLog.setRemark("添加("+qrCodeInterval.getIntervalNumber()+"枚)编码");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("编码生成成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("编码生成失败");
                }
            }else {
                json.setMsg("生成量超过限额！");
            }
        }catch (Exception e){
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }

}
