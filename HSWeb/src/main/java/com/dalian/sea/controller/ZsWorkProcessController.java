package com.dalian.sea.controller;

import com.dalian.sea.ClientIP;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.mapper.BaFormAttributeMapper;
import com.dalian.sea.mapper.BaFormAttributeValueMapper;
import com.dalian.sea.model.*;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ZsWorkProcessController
 *
 * @author TONE
 * @date 2018/2/8.
 */
@Slf4j
@Controller
@RequestMapping(value = "/workProcess")
public class ZsWorkProcessController {

    @Autowired
    private ZsWorkProcessService workProcessService;
    @Autowired
    private ZsWorkFlowService zsWorkFlowService;
    @Autowired
    private BaDataDictionaryService dataDictionaryService;
    @Autowired
    private BaDataDictionaryDetailsService dataDictionaryDetailsService;
    @Autowired
    private BaFormAttributeService baFormAttributeService;
    @Autowired
    private BaFormAttributeValueService baFormAttributeValueService;
    @Autowired
    private SysSysLogService sysSysLogService;


    /**
     * 添加工艺
     * @return
     */
    @RequestMapping(value = "/addWorkProcessModal.htm")
    public String addWorkProcessModal(HttpServletRequest request,Long id,Long resourceId){
        request.setAttribute("resourceId", resourceId);
        if (null != id) {
            PZsWorkFlow workFlow = zsWorkFlowService.getWorkFlowById(id);
            if (null != workFlow) {
                request.setAttribute("workFlow", workFlow);
            }
        }
        return "workFlow/addWorkProcessModal";
    }

    /**
     * 修改工艺
     * @return
     */
    @RequestMapping(value = "/updateWorkProcessModal.htm")
    public String updateWorkProcessModal(HttpServletRequest request,Long id,Long resourceId){
        request.setAttribute("resourceId", resourceId);
        if (null != id) {
            PZsWorkProcess workProcess = workProcessService.getZsWorkProcessByWorkProcessId(id);
            if (null != workProcess) {
                PZsWorkFlow workFlow = zsWorkFlowService.getWorkFlowById(workProcess.getWorkFlowId());
                if (null != workFlow) {
                    request.setAttribute("workFlow", workFlow);
                }
                List<PBaFormAttribute> tableField = baFormAttributeService.getBaFormAttributeListByWorkProcessIdAndHandleType(workProcess.getWorkProcessId(),1);
                if (null!=tableField&&0!=tableField.size()){
                    workProcess.setTableField1(tableField);
                }
                tableField = baFormAttributeService.getBaFormAttributeListByWorkProcessIdAndHandleType(workProcess.getWorkProcessId(),2);
                if (null!=tableField&&0!=tableField.size()){
                    workProcess.setTableField2(tableField);
                }
                tableField = baFormAttributeService.getBaFormAttributeListByWorkProcessIdAndHandleType(workProcess.getWorkProcessId(),3);
                if (null!=tableField&&0!=tableField.size()){
                    workProcess.setTableField3(tableField);
                }
                request.setAttribute("workProcess", workProcess);
            }
        }
        return "workFlow/updateWorkProcessModal";
    }

    /**
     * 获取工艺名称(数据字典)
     * @return
     */
    @RequestMapping(value = "/workProcessNameModal.htm")
    public String workProcessNameModal(HttpServletRequest request){
        List<PBaDataDictionaryDetails> dataDictionaryDetailsList = dataDictionaryDetailsService.getBaDataDictionaryDetailsByCode("0102030100");
        if (null!=dataDictionaryDetailsList&&0!=dataDictionaryDetailsList.size()){
            request.setAttribute("semiFinished",dataDictionaryDetailsList);
        }
        dataDictionaryDetailsList = dataDictionaryDetailsService.getBaDataDictionaryDetailsByCode("0102030200");
        if (null!=dataDictionaryDetailsList&&0!=dataDictionaryDetailsList.size()){
            request.setAttribute("finished",dataDictionaryDetailsList);
        }
        return "workFlow/workProcessNameModal";
    }

    /**
     * 根据数据字典ID获取
     * @param request
     * @param workFlowId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request, Long workFlowId, Integer page, Integer rows) {
        List<PZsWorkProcess> workProcessList = workProcessService.getZsWorkProcessByWorkFlowId(workFlowId,page,rows);
        PageInfo<PZsWorkProcess> pageInfo = new PageInfo<>(workProcessList);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),workProcessList);
    }



    /**
     * 添加工序
     * @param workProcess
     * @return
     */
    @RequestMapping(value = "/addWorkProcess.json")
    @ResponseBody
    public Object addWorkProcess(HttpServletRequest request,@RequestBody PZsWorkProcess workProcess) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            PZsWorkProcess process = workProcessService.getWorkProcessByWorkFlowIdAndName(workProcess);
            if (null==process) {
                workProcess.setCreateUserId(shiroUser.getId());
                Long id = workProcessService.addWorkProcess(workProcess);
                if (null != id) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("添加工序");
                    sysLog.setRemark("添加("+workProcess.getCName()+")工序");
                    sysLog.setResourceId(workProcess.getResourceId());
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("添加成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("添加失败");
                }
            }else {
                json.setMsg("当前工艺下已有该工序！");
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 修改工序
     * @param workProcess
     * @return
     */
    @RequestMapping(value = "/updateWorkProcess.json")
    @ResponseBody
    public Object updateWorkProcess(HttpServletRequest request,@RequestBody PZsWorkProcess workProcess) {
        Json json = new Json();
        try {
            PZsWorkProcess process = workProcessService.getWorkProcessByWorkFlowIdAndName(workProcess);
            if (null==process) {
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                workProcess.setUpdateUserId(shiroUser.getId());
                Boolean boo = workProcessService.updateWorkProcess(workProcess);
                if (boo) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("修改工序");
                    sysLog.setRemark("修改("+workProcess.getCName()+")工序");
                    sysLog.setResourceId(workProcess.getResourceId());
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("修改成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("修改失败");
                }
            }else {
                json.setMsg("当前工艺下已有该工序！");
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 查看工序字段
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/workProcessDetailModal.htm")
    public String workProcessDetailModal(HttpServletRequest request, Long id){
        if (null != id) {
            PZsWorkProcess workProcess = workProcessService.getZsWorkProcessByWorkProcessId(id);
            if (null != workProcess) {
                PZsWorkFlow workFlow = zsWorkFlowService.getWorkFlowById(workProcess.getWorkFlowId());
                if (null != workFlow) {
                    request.setAttribute("workFlow", workFlow);
                }
                List<PBaFormAttribute> tableField = baFormAttributeService.getBaFormAttributeListByWorkProcessIdAndHandleType(workProcess.getWorkProcessId(),1);
                if (null!=tableField&&0!=tableField.size()){
                    workProcess.setTableField1(tableField);
                }
                tableField = baFormAttributeService.getBaFormAttributeListByWorkProcessIdAndHandleType(workProcess.getWorkProcessId(),2);
                if (null!=tableField&&0!=tableField.size()){
                    workProcess.setTableField2(tableField);
                }
                tableField = baFormAttributeService.getBaFormAttributeListByWorkProcessIdAndHandleType(workProcess.getWorkProcessId(),3);
                if (null!=tableField&&0!=tableField.size()){
                    workProcess.setTableField3(tableField);
                }
                request.setAttribute("workProcess", workProcess);
            }
        }
        return "workFlow/workProcessDetailModal";
    }

    /**
     *删除工序
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteWorkProcess.json")
    @ResponseBody
    public Object deleteWorkProcess(HttpServletRequest request,Long id,Long resourceId){
        Json json = new Json();
        try {
            ZsWorkProcess zsWorkProcess = workProcessService.getZsWorkProcessByWorkProcessId(id);
            if(null != zsWorkProcess){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                zsWorkProcess.setUpdateUserId(shiroUser.getId());
                if(workProcessService.deleteWorkProcessById(zsWorkProcess)){
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("删除工序");
                    sysLog.setRemark("删除("+zsWorkProcess.getCName()+")工序");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("删除成功");
                    json.setSuccess(true);
                }else {
                    json.setMsg("删除失败");
                }
            }else {
                json.setMsg("该工艺不存在，无法删除");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

}
