package com.dalian.sea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.ClientIP;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.model.Ireport;
import com.dalian.sea.model.PuEnterStock;
import com.dalian.sea.model.PuEnterStockDetail;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.IreportService;
import com.dalian.sea.service.PuReceiveDetailService;
import com.dalian.sea.service.SysSysLogService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 *
 * @author 杨文波
 * @date 2018/3/16
 */
@Controller
@RequestMapping("/jasper")
@Slf4j
public class JasperReportController {
//    private static final String REPORT_NAME = "reportName";
    private final String FILE_FORMAT = "format";
    private final String DATASOURCE = "datasource";

    @Autowired
    private PuReceiveDetailService puReceiveDetailService;
    @Autowired
    private IreportService ireportService;

    @Autowired
    private SysSysLogService sysSysLogService;

    @GetMapping(value = "/downloadPdf/{format}", produces = "application/pdf")
    public ModelAndView getRptByParam(HttpServletRequest request,final ModelMap modelMap, ModelAndView modelAndView, @PathVariable(name = "format")String format, String receiveDetails, Long receive,Long resourceId) {
        String[] receiveDetailIds=receiveDetails.split(",");
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        List<JasperReportData> ls = puReceiveDetailService.getPrintReceivePdf(receiveDetailIds);
        SysSysLog sysLog = new SysSysLog();
        String ip = ClientIP.getClientIP(request);
        sysLog.setIpAddress(ip);
        sysLog.setCName("打印收货单");
        sysLog.setResourceId(resourceId);
        sysLog.setCreatedUserId(user.getId());
        sysLog.setRemark("打印收货单："+ls.get(0).getReceiveNo());
        sysSysLogService.addSysSysLog(sysLog);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ls);
        // connecting to mysql
        modelMap.put(DATASOURCE, dataSource);
        modelMap.put(FILE_FORMAT, format);
        modelAndView = new ModelAndView("report1", modelMap);
        return modelAndView;
    }

    @GetMapping(value = "/receiveReturnPdf/{format}", produces = "application/pdf")
    public ModelAndView receiveReturnPdf(HttpServletRequest request,final ModelMap modelMap, ModelAndView modelAndView,@PathVariable(name = "format")String format,String receiveDetails,Long resourceId) {
        String[] receiveDetailIds=receiveDetails.split(",");
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        List<JasperReturnData> ls = puReceiveDetailService.getPrintReceiveReturn(receiveDetailIds);
        SysSysLog sysLog = new SysSysLog();
        String ip = ClientIP.getClientIP(request);
        sysLog.setIpAddress(ip);
        sysLog.setCName("打印退货单");
        sysLog.setResourceId(resourceId);
        sysLog.setCreatedUserId(user.getId());
        sysLog.setRemark("打印退货单："+ls.get(0).getReturnNo());
        sysSysLogService.addSysSysLog(sysLog);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ls);
        // connecting to mysql
        modelMap.put(DATASOURCE, dataSource);
        modelMap.put(FILE_FORMAT, format);
        modelAndView = new ModelAndView("reportReturn", modelMap);
        return modelAndView;
    }


    @RequestMapping(value = "/receiveStoragePdf/{format}", produces = "application/pdf")
    public ModelAndView receiveStoragePdf(final ModelMap modelMap, ModelAndView modelAndView, @PathVariable(name = "format")String format, String enterStock, Long resourceId, HttpServletRequest request) {
        JSONObject jsonObject = JSONObject.parseObject(enterStock);
        PEnterStockAndStock stockAndStock = jsonObject.toJavaObject(PEnterStockAndStock.class);
        Subject user = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
        List<Ireport> ireportList = ireportService.receiveStoragePdf(stockAndStock,shiroUser.getName());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ireportList);
        // connecting to mysql
        modelMap.put(DATASOURCE, dataSource);
        modelMap.put(FILE_FORMAT, format);
        modelAndView = new ModelAndView("reportStorage", modelMap);
        SysSysLog sysLog = new SysSysLog();
        String ip = ClientIP.getClientIP(request);
        sysLog.setIpAddress(ip);
        sysLog.setCName("打印入库单");
        sysLog.setRemark("打印入库单,单号为("+stockAndStock.getEnterStock().getEnterNo()+")");
        sysLog.setResourceId(resourceId);
        sysLog.setCreatedUserId(shiroUser.getId());
        sysSysLogService.addSysSysLog(sysLog);
        return modelAndView;
    }

    @RequestMapping(value = "/receiveDepotPdf/{format}", produces = "application/pdf")
    public ModelAndView receiveDepotPdf(final ModelMap modelMap, ModelAndView modelAndView,@PathVariable(name = "format")String format,String enterStock,Long resourceId,HttpServletRequest request) {
        JSONObject jsonObject = JSONObject.parseObject(enterStock);
        PEnterStockAndStock stockAndStock = jsonObject.toJavaObject(PEnterStockAndStock.class);
        Subject user = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
        List<Ireport> ireportList = ireportService.receiveDepotPdf(stockAndStock,shiroUser.getName());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ireportList);
        modelMap.put(DATASOURCE, dataSource);
        modelMap.put(FILE_FORMAT, format);
        modelAndView = new ModelAndView("reportDepot", modelMap);
        SysSysLog sysLog = new SysSysLog();
        String ip = ClientIP.getClientIP(request);
        sysLog.setIpAddress(ip);
        sysLog.setCName("打印返库单");
        sysLog.setRemark("打印返库单,单号为("+stockAndStock.getEnterStock().getEnterNo()+")");
        sysLog.setResourceId(resourceId);
        sysLog.setCreatedUserId(shiroUser.getId());
        sysSysLogService.addSysSysLog(sysLog);
        return modelAndView;
    }

    @RequestMapping(value = "/receiveOutGoingPdf/{format}", produces = "application/pdf")
    public ModelAndView receiveOutGoingPdf(final ModelMap modelMap, ModelAndView modelAndView,@PathVariable(name = "format")String format,String leaveStock,Long resourceId,HttpServletRequest request) {
        JSONObject jsonObject = JSONObject.parseObject(leaveStock);
        YhLeaveStock stock = jsonObject.toJavaObject(YhLeaveStock.class);
        Subject user = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
        List<Ireport> ireportList = ireportService.receiveOutGoingPdf(stock,shiroUser.getName());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ireportList);
        modelMap.put(DATASOURCE, dataSource);
        modelMap.put(FILE_FORMAT, format);
        modelAndView = new ModelAndView("reportOutGoing", modelMap);
        SysSysLog sysLog = new SysSysLog();
        String ip = ClientIP.getClientIP(request);
        sysLog.setIpAddress(ip);
        sysLog.setCName("打印出库单");
        sysLog.setRemark("打印出库单,单号为("+stock.getLeaveNo()+")");
        sysLog.setResourceId(resourceId);
        sysLog.setCreatedUserId(shiroUser.getId());
        sysSysLogService.addSysSysLog(sysLog);
        return modelAndView;
    }

}
