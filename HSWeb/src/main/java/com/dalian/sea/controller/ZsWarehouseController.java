package com.dalian.sea.controller;

import com.dalian.sea.ClientIP;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.*;
import com.dalian.sea.parameter.PBaUser;
import com.dalian.sea.parameter.PUserWarehouse;
import com.dalian.sea.parameter.PZsWarehouse;
import com.dalian.sea.service.*;
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

/**
 * Created by Administrator on 2018/1/31.
 */
@Slf4j
@Controller
@RequestMapping(value = "/warehouse")
public class ZsWarehouseController extends LayoutRazor{

    @Autowired
    private ZsWarehouseService zsWarehouseService;
    @Autowired
    private BaUserService baUserService;
    @Autowired
    private BaRolesService baRolesService;

    @Autowired
    private SysSysLogService sysSysLogService;
    @Autowired
    private ZsWarehouseUserService zsWarehouseUserService;

    /**
     * 仓库管理
     * @param request
     * @return
     */
    @RequestMapping(value = "/warehouseManage.htm")
    public String warehouseManage(HttpServletRequest request){
        List<BaUser> baUserList = baUserService.getBaUserByUserType(2,new Long("1"));
        if(null != baUserList && 0 != baUserList.size()){
            request.setAttribute("baUserList",baUserList);
        }
//        List<BaRoles> baRolesList = baRolesService.getAllBaRoles();
//        if(null != baRolesList && 0!= baRolesList.size()){
//            request.setAttribute("baRolesList",baRolesList);
//        }
        return freeMarkerIndexResult("warehouse/warehouseManage.ftl",request);
    }

    /**
     * 加载表格
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request, PZsWarehouse zsWarehouse,Integer page ,Integer rows){
        List<PZsWarehouse> zsWarehouseList = zsWarehouseService.getWareHouseForGrid(zsWarehouse,page,rows);
        PageInfo<PZsWarehouse> pageInfo = new PageInfo<>(zsWarehouseList);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),zsWarehouseList);
    }

    /**
     * 添加modal
     * @param request
     * @return
     */
    @RequestMapping(value = "/addWarehouseModal.htm")
    public String addWarehouseModal(HttpServletRequest request,Long resourceId){
        request.setAttribute("resourceId",resourceId);
        List<BaUser> baUserList = baUserService.getBaUserByUserType(2,new Long("1"));
        if(null != baUserList && 0 != baUserList.size()){
            request.setAttribute("baUserList",baUserList);
        }
        return "warehouse/addWarehouseModal";
    }

    /**
     * 添加
     * @param request
     * @param zsWarehouse
     * @return
     */
    @RequestMapping(value = "/addWarehouse.json")
    @ResponseBody
    public Object addWarehouse(HttpServletRequest request,ZsWarehouse zsWarehouse,Long[] warehouseUserId,Long resourceId){
        Json json = new Json();
        try {
            ZsWarehouse warehouse = zsWarehouseService.getWarehouseByName(zsWarehouse);
            if(null == warehouse){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                zsWarehouse.setCreateUserId(shiroUser.getId());
                if(null != zsWarehouse.getManagerId() && 0 != zsWarehouse.getManagerId()){
                    BaUser baUser = baUserService.getBaUserByUserId(zsWarehouse.getManagerId());
                    if(null != baUser){
                        zsWarehouse.setManager(baUser.getCName());
                        zsWarehouse.setPhone(baUser.getMobile());
                        zsWarehouse.setCompanyId(baUser.getCompanyId());
                    }
                }
                ZsWarehouse house = zsWarehouseService.getWarehouseMaxListIndex();
                if(null != house){
                    zsWarehouse.setListIndex(house.getListIndex()+1);
                }else {
                    zsWarehouse.setListIndex(1);
                }
                if(zsWarehouseService.addWarehouse(zsWarehouse,warehouseUserId)){
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("添加仓库");
                    sysLog.setRemark("添加("+zsWarehouse.getCName()+")仓库");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("添加成功");
                    json.setSuccess(true);
                }else {
                    json.setMsg("添加失败");
                }
            }else {
                json.setMsg("该仓库已经存在");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 修改modal
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateWarehouseModal.htm")
    public String updateWarehouseModal(HttpServletRequest request,Long id,Long resourceId){
        request.setAttribute("resourceId",resourceId);
        if(null != id && 0 != id){
            PZsWarehouse zsWarehouse = zsWarehouseService.getWarehouseById(id);
            if(null != zsWarehouse){
                request.setAttribute("zsWarehouse",zsWarehouse);
            }
            List<ZsWarehouseUser> warehouseUserList = zsWarehouseUserService.getWarehouseUserByWarehouseId(zsWarehouse.getWarehouseId());
            List<BaUser> baUserList = baUserService.getBaUserByUserType(2,new Long("1"));
            List<PUserWarehouse> userList = new ArrayList<>();
            if(null != baUserList && 0 != baUserList.size()){
                 for(BaUser baUser: baUserList){
                    PUserWarehouse pUserWarehouse = new PUserWarehouse();
                    pUserWarehouse.setUserId(baUser.getUserId());
                    pUserWarehouse.setCName(baUser.getCName());
                     if(null!=warehouseUserList&&0!=warehouseUserList.size()){
                        for(ZsWarehouseUser zsWarehouseUser: warehouseUserList){
                            if(baUser.getUserId().equals(zsWarehouseUser.getUserId())){
                                pUserWarehouse.setIsSelected(1);
                                break;
                            }
                        }
                    }
                    userList.add(pUserWarehouse);
                 }
            }
            request.setAttribute("userList",userList);
        }
        return "warehouse/updateWarehouseModal";
    }


    /**
     * 修改
     * @return
     */
    @RequestMapping(value = "/updateWarehouse.json")
    @ResponseBody
    public Object updateWarehouse(HttpServletRequest request,ZsWarehouse zsWarehouse,Long[] updWarehouseUserId,Long resourceId){
        Json json = new Json();
        try {
            ZsWarehouse warehouse = zsWarehouseService.getWarehouseByName(zsWarehouse);
            if(null==warehouse || warehouse.getWarehouseId().equals(zsWarehouse.getWarehouseId())){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                zsWarehouse.setUpdateUserId(shiroUser.getId());
                if(zsWarehouseService.updateZsWarehouseById(zsWarehouse,updWarehouseUserId)){
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("修改仓库");
                    sysLog.setRemark("修改("+zsWarehouse.getCName()+")仓库");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("修改成功");
                    json.setSuccess(true);
                }else {
                    json.setMsg("修改失败");
                }
            }else {
                json.setMsg("该仓库已经存在");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 详情
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/warehouseDetailModal.htm")
    public String warehouseDetailModal(HttpServletRequest request,Long id){
        if(null != id && 0 != id){
            PZsWarehouse zsWarehouse = zsWarehouseService.getWarehouseById(id);
            if(null != zsWarehouse){
                request.setAttribute("zsWarehouse",zsWarehouse);
            }
        }
        return "warehouse/warehouseDetailModal";
    }


    /**
     * 删除仓库
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteWarehouse.json")
    @ResponseBody
    public Object deleteWarehouse(HttpServletRequest request,Long id,Long resourceId){
        Json json  = new Json();
        try {
            ZsWarehouse warehouse = zsWarehouseService.getWarehouseById(id);
            if(null != warehouse){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                warehouse.setUpdateUserId(shiroUser.getId());
                if(zsWarehouseService.deleteZsWarehouseById(warehouse)){
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("删除仓库");
                    sysLog.setRemark("删除("+warehouse.getCName()+")仓库");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("删除成功");
                    json.setSuccess(true);
                }else {
                    json.setMsg("删除失败");
                }
            }else {
                json.setMsg("数据异常");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    @RequestMapping("/getAllZsWarehouseList.json")
    @ResponseBody
   public Json getAllZsWarehouseList(){
        Json json =new Json();
        try {
            Subject user =SecurityUtils.getSubject();
            ShiroUser shiroUser =(ShiroUser)user.getPrincipal();
           List<ZsWarehouse> warehouseList =new ArrayList<>();
           if(shiroUser.getId() !=null) {
               warehouseList =zsWarehouseService.getWareHouseByUserId(shiroUser.getId());
           }
           if(null !=warehouseList&&!warehouseList.isEmpty()){
               json.setObj(warehouseList);
               json.setSuccess(true);
           }else {
               json.setMsg("无仓库信息");
           }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

}
