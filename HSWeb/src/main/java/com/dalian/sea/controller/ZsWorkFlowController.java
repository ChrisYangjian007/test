package com.dalian.sea.controller;

import com.dalian.sea.ClientIP;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.model.ZsWorkFlow;
import com.dalian.sea.model.ZsWorkProcess;
import com.dalian.sea.parameter.PZsWorkFlow;
import com.dalian.sea.parameter.ZTreeEncapsulation;
import com.dalian.sea.service.SysSysLogService;
import com.dalian.sea.service.ZsWorkFlowService;
import com.dalian.sea.service.ZsWorkProcessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * BaCompanyController
 *
 * @author TONE
 * @date 2018/2/8.
 */
@Slf4j
@Controller
@RequestMapping(value = "/workFlow")
public class ZsWorkFlowController extends LayoutRazor {

    @Autowired
    private ZsWorkFlowService zsWorkFlowService;
    @Autowired
    private ZsWorkProcessService zsWorkProcessService;
    @Autowired
    private SysSysLogService sysSysLogService;

    /**
     * 工艺管理
     * @return
     */
    @RequestMapping(value = "/workFlowManage.htm")
    public String workFlowManage(HttpServletRequest request){
        return freeMarkerIndexResult("workFlow/workFlowManage.ftl",request);
    }

    /**
     * 添加工艺
     * @return
     */
    @RequestMapping(value = "/addWorkFlowModal.htm")
    public String addWorkFlowModal(HttpServletRequest request,Long id,Long resourceId){
        request.setAttribute("resourceId", resourceId);
        if (null != id) {
            PZsWorkFlow workFlow = zsWorkFlowService.getWorkFlowById(id);
            if (null != workFlow) {
                request.setAttribute("workFlow", workFlow);
            }
        }
        return "workFlow/addWorkFlowModal";
    }

    /**
     * 修改工艺
     * @return
     */
    @RequestMapping(value = "/updateWorkFlowModal.htm")
    public String updateWorkFlowModal(HttpServletRequest request,Long id,Long resourceId){
        request.setAttribute("resourceId", resourceId);
        if (null != id) {
            PZsWorkFlow workFlow = zsWorkFlowService.getWorkFlowById(id);
            if (null != workFlow) {
                request.setAttribute("workFlow", workFlow);
            }
        }
        return "workFlow/updateWorkFlowModal";
    }


    /**
     * 工艺管理
     * @param workFlow
     * @return
     */
    @RequestMapping(value = "/getZsWorkFlowForZTree.json")
    @ResponseBody
    public Object getZsWorkFlowForZTree(ZsWorkFlow workFlow){
        Json json = new Json();
        try {
            List<ZsWorkFlow> workFlowList;
            if (null!=workFlow.getWorkFlowId()){
                workFlowList = zsWorkFlowService.getWorkFlowByParentId(workFlow.getWorkFlowId());
            }else {
                workFlowList = zsWorkFlowService.getWorkFlowByParentId((long) 0);
            }
            List<ZTreeEncapsulation> encapsulationList = new ArrayList<>();
            for (ZsWorkFlow flow:workFlowList){
                ZTreeEncapsulation encapsulation=new ZTreeEncapsulation();
                encapsulation.setId(flow.getWorkFlowId());
                encapsulation.setPId(flow.getParentId());
                encapsulation.setName(flow.getCName());
                encapsulation.setText(flow.getCName());
                if (4>flow.getALevel()){
                    encapsulation.setIsParent(true);
                }
                encapsulationList.add(encapsulation);
            }
            json.setObj(encapsulationList);
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("查询失败");
        }
        return json;
    }



    /**
     * 添加工艺
     * @param request
     * @param workFlow
     * @return
     */
    @RequestMapping(value = "/addWorkFlow.json")
    @ResponseBody
    public Object addWorkFlow(HttpServletRequest request,ZsWorkFlow workFlow,Long resourceId) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            PZsWorkFlow flow = zsWorkFlowService.getWorkFlowByParentIdAndName(workFlow);
            if (null==flow){
                flow = zsWorkFlowService.getWorkFlowById(workFlow.getParentId());
                workFlow.setCreateUserId(shiroUser.getId());
                workFlow.setFirstId(flow.getFirstId());
                workFlow.setCompanyId((long) 1);
                workFlow.setRemark(workFlow.getCName());
                Long id = zsWorkFlowService.addZsWorkFlow(workFlow);
                if (null!=id){
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("添加工艺");
                    sysLog.setRemark("添加("+workFlow.getCName()+")工艺");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("添加成功");
                    json.setSuccess(true);
                }else {
                    json.setMsg("添加失败");
                }
            }else {
                json.setMsg("当前父级下已有该资源");
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 修改工艺
     * @param request
     * @param workFlow
     * @return
     */
    @RequestMapping(value = "/updateWorkFlow.json")
    @ResponseBody
    public Object updateWorkFlow(HttpServletRequest request,ZsWorkFlow workFlow,Long resourceId) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            PZsWorkFlow flow = zsWorkFlowService.getWorkFlowById(workFlow.getWorkFlowId());
            if (null!=flow) {
                workFlow.setUpdateUserId(shiroUser.getId());
                Boolean boo = zsWorkFlowService.updateWorkFlow(workFlow);
                if (boo) {
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("修改工艺");
                    sysLog.setRemark("修改("+workFlow.getCName()+")工艺");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("修改成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("修改失败");
                }
            }else {
                json.setMsg("无当前编号数据，无法删除");
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 删除工艺
     * @param request
     * @param workFlow
     * @return
     */
    @RequestMapping(value = "/deleteWorkFlow.json")
    @ResponseBody
    public Object deleteWorkFlow(HttpServletRequest request,PZsWorkFlow workFlow,Long resourceId) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            workFlow = zsWorkFlowService.getWorkFlowById(workFlow.getWorkFlowId());
            if (null!=workFlow) {
                List<ZsWorkFlow> workFlowList = zsWorkFlowService.getWorkFlowByParentId(workFlow.getWorkFlowId());
                if(null!=workFlowList&&0!=workFlowList.size()){
                    json.setMsg("当前流程下有子级，无法删除");
                }else {
                    List<ZsWorkProcess> workProcessList = zsWorkProcessService.getWorkProcessByWorkFlowId(workFlow.getWorkFlowId());
                    if(null!=workProcessList&&0!=workProcessList.size()){
                        json.setMsg("当前流程下存在工艺，无法删除");
                    }else {
                        if (0!=workFlow.getALevel()) {
                            workFlow.setUpdateUserId(shiroUser.getId());
                            Boolean boo = zsWorkFlowService.deleteWorkFlow(workFlow);
                            if (boo) {
                                SysSysLog sysLog = new SysSysLog();
                                String ip = ClientIP.getClientIP(request);
                                sysLog.setIpAddress(ip);
                                sysLog.setCName("删除工艺");
                                sysLog.setRemark("删除("+workFlow.getCName()+")工艺");
                                sysLog.setResourceId(resourceId);
                                sysLog.setCreatedUserId(shiroUser.getId());
                                sysSysLogService.addSysSysLog(sysLog);
                                json.setMsg("删除成功");
                                json.setSuccess(true);
                            } else {
                                json.setMsg("删除失败");
                            }
                        }else {
                            json.setMsg("顶级无法删除,要删除请联系管理员");
                        }
                    }
                }
            }else {
                json.setMsg("无当前编号数据，无法删除");
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }



}
