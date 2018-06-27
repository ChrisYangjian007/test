package com.dalian.sea.controller;

import com.dalian.sea.ClientIP;
import com.dalian.sea.DTO.TreeJsonDTO;
import com.dalian.sea.common.HandleHelper;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.SysProductType;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.model.ZsCompanyProduct;
import com.dalian.sea.service.SysProductTypeService;
import com.dalian.sea.service.SysSysLogService;
import com.dalian.sea.service.ZsCompanyProductService;
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

/**
 * Created by Administrator on 2018/1/23.
 */
@Controller
@Slf4j
@RequestMapping(value = "/productType")
public class SysProductTypeController extends LayoutRazor{

    @Autowired
    private SysProductTypeService sysProductTypeService;
    @Autowired
    private ZsCompanyProductService zsCompanyProductService;
    @Autowired
    private SysSysLogService sysSysLogService;


    /**
     *产品类型管理
     * @param request
     * @return
     */
    @RequestMapping("/productTypeManage.htm")
    public String sysProductTypeManage(HttpServletRequest request){
        return freeMarkerIndexResult("productType/sysProductTypeManage.ftl",request);
    }

    /**
     *产品类型tree
     * @param request
     * @return
     */
    @RequestMapping(value = "/TreeJson.json")
    @ResponseBody
    public String TreeJson(HttpServletRequest request) {
        List<TreeJsonDTO> treeList = new ArrayList<>();
        try {
            List<SysProductType> sysProductTypeList = sysProductTypeService.getAllSysProductType();
            for(SysProductType item : sysProductTypeList){
                TreeJsonDTO tree = new TreeJsonDTO();
                tree.setId(String.valueOf(item.getProductTypeId()));
                tree.setText(item.getCName());
                tree.setValue(String.valueOf(item.getProductTypeId()));
                tree.setAttribute("ALevel");
                tree.setAttributeValue(String.valueOf(item.getALevel()));
                tree.setShowcheck(false);
                tree.setIsexpand(true);
                tree.setComplete(true);
                tree.setParentId(String.valueOf(item.getParentId()));
                if(3==item.getALevel()){
                    tree.setImg("/images/Icon16/document_tag.png");
                }
                tree.setHasChildren(sysProductTypeList.stream().filter(t -> Objects.equals(t.getParentId(), item.getProductTypeId())).count() > 0);
                  treeList.add(tree);
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
        }
        return HandleHelper.TreeToJson2(treeList, "0");
    }

    /**
     *产品类型表格
     * @param request
     * @param sysProductType
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request,SysProductType sysProductType,Integer page,Integer rows){
        List<SysProductType> sysProductTypeList = sysProductTypeService.getSysProductTypeByForGridTable(sysProductType,page,rows);
        PageInfo<SysProductType> pageInfo = new PageInfo<>(sysProductTypeList);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),sysProductTypeList);
    }

    /**
     * 添加产品类型modal
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value ="/addProductTypeModal.htm")
    public String addProductTypeModal(HttpServletRequest request,Long id,Long resourceId){
        request.setAttribute("resourceId",resourceId);
        SysProductType sysProductType = sysProductTypeService.getSysproductTypeById(id);
        if(null != sysProductType){
            request.setAttribute("sysProductType",sysProductType);
            request.setAttribute("aLevel",sysProductType.getALevel()+1);
        }
        SysProductType productType = sysProductTypeService.getSysProductTypeMaxListIndex();
        if(null != productType){
            request.setAttribute("listIndex",productType.getListIndex()+1);
        }else {
            request.setAttribute("listIndex",1);
        }
        return "productType/addProductTypeModal";
    }

    /**
     * 添加产品类型
     * @param request
     * @param sysProductType
     * @return
     */
    @RequestMapping(value = "/addProductType.json")
    @ResponseBody
    public Object addProductType(HttpServletRequest request,SysProductType sysProductType,Long resourceId){
        Json json = new Json();
        try {
            SysProductType productType = sysProductTypeService.getSysProductTypeByParentIdAndCName(sysProductType);
            if(null != productType){
                json.setMsg("该产品类型已经存在");
            }else {
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                sysProductType.setCreateUserId(shiroUser.getId());
                if(sysProductTypeService.addSysProductType(sysProductType)){
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("添加产品类型");
                    sysLog.setRemark("添加("+sysProductType.getCName()+")产品类型");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("添加成功");
                    json.setSuccess(true);
                }else {
                    json.setMsg("添加失败");
                }
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return  json;
    }

    /**
     * 修改产品类型modal
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateProductTypeModal.htm")
    public String updateProductTypeModal(HttpServletRequest request,Long id,Long resourceId){
        request.setAttribute("resourceId",resourceId);
        if(null != id && 0 != id){
            SysProductType sysProductType = sysProductTypeService.getSysproductTypeById(id);
            if(null != sysProductType){
                request.setAttribute("sysProductType",sysProductType);
                if(null != sysProductType.getFirstId()&& 0 != sysProductType.getFirstId()){
                    SysProductType first = sysProductTypeService.getSysproductTypeById(sysProductType.getFirstId());
                    if(null != first){
                        request.setAttribute("first",first);
                    }
                }
                if(null != sysProductType.getParentId() && 0 != sysProductType.getParentId()){
                        SysProductType parent = sysProductTypeService.getSysproductTypeById(sysProductType.getParentId());
                        if(null != parent){
                            request.setAttribute("parent",parent);
                        }
                }
            }
        }
        return "productType/updateProductTypeModal";
    }

    /**
     * 修改产品类型
     * @param request
     * @param sysProductType
     * @return
     */
    @RequestMapping(value = "/updateProductType.json")
    @ResponseBody
    public Object updateProductType(HttpServletRequest request,SysProductType sysProductType,Long resourceId){
        Json json = new Json();
        try {
            SysProductType productType = sysProductTypeService.getSysProductTypeByParentIdAndCName(sysProductType);
            if(null == productType || sysProductType.getProductTypeId().equals(productType.getProductTypeId())){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                sysProductType.setUpdateUserId(shiroUser.getId());
                if(sysProductTypeService.updateSysProductTypeById(sysProductType)){
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("修改产品类型");
                    sysLog.setRemark("修改("+sysProductType.getCName()+")产品类型");
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("修改成功");
                    json.setSuccess(true);
                }else {
                    json.setMsg("修改失败");
                }
            }else {
                json.setMsg("该产品类型已经存在");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 根据父级ID获取子集
     * @param request
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/getSysProductTypeByParentId.json")
    @ResponseBody
    public Object getSysProductTypeByParentId(HttpServletRequest request,Long parentId){
        Json json = new Json();
        try {
            List<SysProductType> productTypeList = sysProductTypeService.getSysProductTypeByParentId(parentId);
            if (null!=productTypeList&&0!=productTypeList.size()){
                json.setObj(productTypeList);
                json.setSuccess(true);
            }else {
                json.setMsg("无数据");
            }
        }catch (Exception e){
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 删除产品类型
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteProductType.json")
    @ResponseBody
    public Object deleteProductType(HttpServletRequest request,Long id,Long resourceId){
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            List<SysProductType> sysProductTypeList = sysProductTypeService.getAllSysProductTypeById(id);
            if(null != sysProductTypeList && 0 != sysProductTypeList.size()){
                List<ZsCompanyProduct> companyProductList = zsCompanyProductService.getZsCompanyProductByProductTypeList(sysProductTypeList);
                if(null != companyProductList && 0 !=companyProductList.size()){
                    json.setMsg("该产品类型下有产品，不能删除");
                }else {
                    if(sysProductTypeService.deleteProductTypeByList(sysProductTypeList)){
                        SysSysLog sysLog = new SysSysLog();
                        String ip = ClientIP.getClientIP(request);
                        sysLog.setIpAddress(ip);
                        sysLog.setCName("删除产品类型");
                        sysLog.setRemark("删除("+sysProductTypeList.get(0).getCName()+")产品类型");
                        sysLog.setResourceId(resourceId);
                        sysLog.setCreatedUserId(shiroUser.getId());
                        sysSysLogService.addSysSysLog(sysLog);
                        json.setMsg("删除成功");
                        json.setSuccess(true);
                    }else {
                        json.setMsg("删除失败");
                    }
                }
            }else {
                json.setMsg("数据异常");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 产品类型详情
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/productTypeDetailModal.htm")
    public String productTypeDetailModal(HttpServletRequest request,Long id){
        if(null != id){
            SysProductType sysProductType = sysProductTypeService.getSysproductTypeById(id);
            if(null != sysProductType){
                request.setAttribute("sysProductType",sysProductType);
                if(null != sysProductType.getParentId()){
                    SysProductType parent = sysProductTypeService.getSysproductTypeById(sysProductType.getParentId());
                    if(null != parent){
                        request.setAttribute("parent",parent);
                    }
                }
                if(null != sysProductType.getFirstId()){
                    SysProductType first = sysProductTypeService.getSysproductTypeById(sysProductType.getFirstId());
                    if(null != first){
                        request.setAttribute("first",first);
                    }
                }
            }
        }
        return "productType/productTypeDetailModal";
    }

}
