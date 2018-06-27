package com.dalian.sea.controller;

import com.alibaba.fastjson.JSON;
import com.dalian.sea.ClientIP;
import com.dalian.sea.DTO.TreeJsonDTO;
import com.dalian.sea.common.HandleHelper;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.model.SysUnit;
import com.dalian.sea.parameter.PSysUnit;
import com.dalian.sea.service.SysSysLogService;
import com.dalian.sea.service.SysUnitService;
import com.github.pagehelper.PageInfo;
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
import java.util.Objects;
import java.util.Set;

/**
 * SysUnitController
 *
 * @author xintao
 * @date 2018/1/17
 */
@Slf4j
@Controller
@RequestMapping(value = "/sysUnit")
public class SysUnitController extends LayoutRazor{
    @Autowired
    private SysUnitService sysUnitService;
    @Autowired
    private SysSysLogService sysSysLogService;


    /**
     * 单位tree
     * @param request
     * @return
     */
    @RequestMapping(value = "/TreeJson.json")
    @ResponseBody
    public Object TreeJson(HttpServletRequest request) {
        List<TreeJsonDTO> treeList = new ArrayList<>();
        try {
            List<SysUnit> unitList = sysUnitService.getSysUnitNoZero();
            if (null!=unitList && 0!=unitList.size()) {
                for (SysUnit item : unitList) {
                    TreeJsonDTO tree = new TreeJsonDTO();
                    tree.setId(String.valueOf(item.getUnitId()));
                    tree.setText(item.getCName());
                    tree.setValue(String.valueOf(item.getUnitId()));
                    tree.setAttribute("alevel");
                    tree.setAttributeValue(String.valueOf(item.getALevel()));
                    tree.setShowcheck(false);
                    tree.setIsexpand(true);
                    tree.setComplete(true);
                    tree.setParentId(String.valueOf(item.getParentId()));
                    tree.setHasChildren(unitList.stream().filter(t -> Objects.equals(t.getParentId(), item.getUnitId())).count() > 0);
                    tree.setImg(tree.getHasChildren()? "/images/Icon16/molecule.png" : "/images/Icon16/hostname.png");
                    treeList.add(tree);
                }
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
        }
        return HandleHelper.TreeToJson2(treeList, "1");
    }

    /**
     * 单位管理
     * @param request
     * @return
     */
    @RequestMapping(value = "/unitManage.htm")
    public String sysUnitManage(HttpServletRequest request){
        return freeMarkerIndexResult("unit/unitManage.ftl",request);
    }

    /**
     * 单位表格
     * @param request
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request){
      List<PSysUnit> pSysUnitList = new ArrayList<>();
        List<SysUnit> unitList = sysUnitService.getSysUnitNoZero();
        List<Long>  pIds = new ArrayList<>();
        if(null != unitList && 0 != unitList.size()){
            for(SysUnit sysUnit: unitList){
                PSysUnit pSysUnit = new PSysUnit();
                pSysUnit.setUnitId(sysUnit.getUnitId());
                pSysUnit.setCName(sysUnit.getCName());
                pSysUnit.setValue(sysUnit.getValue());
                pSysUnit.setRemark(sysUnit.getRemark());
                pSysUnit.setExpanded(true);
                pSysUnit.setParent(String.valueOf(sysUnit.getParentId()));
                pSysUnit.setLoaded(true);
                pSysUnit.setIsLeaf(unitList.stream().filter(t -> Objects.equals(t.getParentId(), sysUnit.getUnitId())).count() <= 0);
                pSysUnit.setLevel(sysUnit.getALevel());
                pSysUnitList.add(pSysUnit);
                pIds.add(sysUnit.getParentId());
            }
        }
        return  pSysUnitList;
    }


    /**
     * 添加单位
     * @param request
     * @param sysUnit
     * @return
     */
    @RequestMapping(value = "/addSysUnit.json")
    @ResponseBody
    public Object addSysUnit(HttpServletRequest request,SysUnit sysUnit,Long resourceId){
        Json json = new Json();
        try {
            SysUnit unit = sysUnitService.getSysUnitByParentIdAndName(sysUnit);
            if(null == unit){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                sysUnit.setCreateUserId(shiroUser.getId());
                sysUnit.setCompanyId(new Long("1"));
                SysUnit listIndex = sysUnitService.getSysUnitMaxListIndex();
                if(null != listIndex){
                    sysUnit.setListIndex(listIndex.getListIndex()+1);
                }else {
                    sysUnit.setListIndex(1);
                }
                if(null != sysUnit.getParentId() && 0 != sysUnit.getParentId()){
                    SysUnit parent = sysUnitService.getSysUnitById(sysUnit.getParentId());
                    sysUnit.setALevel(parent.getALevel()+1);
                    sysUnit.setFirstId(parent.getFirstId());
                }else {
                    sysUnit.setParentId(new Long("0"));
                    sysUnit.setFirstId(new Long("0"));
                }
                if(sysUnitService.addSysUnit(sysUnit)){
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("添加单位");
                    sysLog.setRemark("添加("+sysUnit.getCName()+")单位");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("添加成功");
                    json.setSuccess(true);
                }else {
                    json.setMsg("添加失败");
                }
            }else {
                json.setMsg("同一等级下已经有此单位");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }


    /**
     * 修改单位
     * @return
     */
    @RequestMapping(value = "/updateSysUnit.json")
    @ResponseBody
    public Object updateSysUnit(HttpServletRequest request,SysUnit sysUnit,Long resourceId){
        Json json = new Json();
        try {
            SysUnit unit = sysUnitService.getSysUnitByParentIdAndName(sysUnit);
            if(null == unit || unit.getUnitId().equals(sysUnit.getUnitId())){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                sysUnit.setUpdateUserId(shiroUser.getId());
                if(sysUnitService.updateSysUnitById(sysUnit)){
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("修改单位");
                    sysLog.setRemark("修改("+sysUnit.getCName()+")单位");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("修改成功");
                    json.setSuccess(true);
                }else {
                    json.setMsg("修改失败");
                }
            }else {
                json.setMsg("同一等级下已经有此单位");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 添加单位modal
     * @param request
     * @return
     */
    @RequestMapping(value = "/addSysUnitModal.htm")
    public String addSysUnitModal(HttpServletRequest request,Long resourceId){
        request.setAttribute("resourceId",resourceId);
        return "unit/addUnitModal";
    }

    /**
     * 修改modal
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateSysUnitModal.htm")
    public String updateSysUnitModal(HttpServletRequest request,Long id,Long resourceId){
        request.setAttribute("resourceId",resourceId);
        if( null != id){
            SysUnit sysUnit = sysUnitService.getSysUnitById(id);
            if(null != sysUnit){
                request.setAttribute("sysUnit",sysUnit);
                if(null != sysUnit.getParentId() && 0 != sysUnit.getParentId()){
                    SysUnit parent = sysUnitService.getSysUnitById(sysUnit.getParentId());
                    if(null != parent){
                        request.setAttribute("parent",parent);
                    }
                }
                if(null != sysUnit.getFirstId() && 0 != sysUnit.getFirstId()){
                    SysUnit first = sysUnitService.getSysUnitById(sysUnit.getFirstId());
                    if(null != first){
                        request.setAttribute("first",first);
                    }
                }
            }
        }
        return "unit/updateUnitModal";
    }

    /**
     * 获取全部的单位信息并显示到父级单位
     * @param request
     * @return
     */
    @RequestMapping(value = "/TreeJsonForParent.json")
    @ResponseBody
    public Object TreeJsonForParent(HttpServletRequest request){
        List<TreeJsonDTO> treeList = new ArrayList<>();
        try {
            List<SysUnit> unitList = sysUnitService.getAllSysUnit();
            if (null!=unitList && 0!=unitList.size()) {
                for (SysUnit item : unitList) {
                    TreeJsonDTO tree = new TreeJsonDTO();
                    tree.setId(String.valueOf(item.getUnitId()));
                    tree.setText(item.getCName());
                    tree.setValue(String.valueOf(item.getUnitId()));
                    tree.setAttribute("alevel");
                    tree.setAttributeValue(String.valueOf(item.getALevel()));
                    tree.setShowcheck(false);
                    tree.setIsexpand(true);
                    tree.setComplete(true);
                    tree.setParentId(String.valueOf(item.getParentId()));
                    tree.setHasChildren(unitList.stream().filter(t -> Objects.equals(t.getParentId(), item.getUnitId())).count() > 0);
                    tree.setImg(tree.getHasChildren()? "/images/Icon16/molecule.png" : "/images/Icon16/hostname.png");
                    treeList.add(tree);
                }
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
        }
        return HandleHelper.TreeToJson2(treeList, "0");
    }
}
