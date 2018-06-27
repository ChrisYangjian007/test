package com.dalian.sea.controller;

import com.dalian.sea.DTO.TreeJsonDTO;
import com.dalian.sea.common.HandleHelper;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.*;
import com.dalian.sea.parameter.PBaCompany;
import com.dalian.sea.parameter.PBaRoles;

import com.dalian.sea.parameter.PBaUser;
import com.dalian.sea.parameter.ZTreeEncapsulation;
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
import java.util.Objects;

/**
 * BaRolesController
 *
 * @author xintao
 * @date 2018/1/10
 */
@Controller
@Slf4j
@RequestMapping(value = "/roles")
public class BaRolesController extends LayoutRazor{
    @Autowired
    private BaRolesService baRolesService;

    @Autowired
    private BaUserService baUserService;
    @Autowired
    private BaResourceService baResourceService;
    @Autowired
    private BaCompanyService baCompanyService;
    @Autowired
    private BaRoleResourceService baRoleResourceService;
    @Autowired
    private BaUserRoleService baUserRoleService;
    /**
     * 权限管理
     * @return
     */
    @RequestMapping(value = "/baRolesManage.htm")
    public String baRolesManage(HttpServletRequest request){
        return freeMarkerIndexResult("baRoles/baRolesManage.ftl",request);
    }

    /**
     * 角色用户
     * @return
     */
    @RequestMapping(value = "/baRolesUserModal.htm")
    public String baRolesUserModal(HttpServletRequest request,Long id){
        BaRoles roles = baRolesService.getBaRolesById(id);
        if(null!=roles){
            request.setAttribute("role",roles);
            List<PBaUser> userList = baUserService.getBaUserByRoleId(roles.getRoleId());
            if (null!=userList&&0!=userList.size()){
                request.setAttribute("userList",userList);
            }
        }
        return "/baRoles/baRolesUserModal";
    }

    /**
     * 角色用户
     * @return
     */
    @RequestMapping(value = "/userRolesIFrame.htm")
    public String userRolesModal(HttpServletRequest request,Long id){
        BaRoles roles = baRolesService.getBaRolesById(id);
        if(null!=roles){
            request.setAttribute("role",roles);
        }
        return freeMarkerIndexResult("baRoles/userRolesIFrame.ftl",request);
    }

    /**
     *加载角色表格
     * @param request
     * @param companyId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request,Integer page, Integer rows) {
        Subject user = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
        List<PBaRoles> baRolesList = baRolesService.getBaRolesByCompanyId(shiroUser.getCompanyId(),page,rows);
        PageInfo<PBaRoles> pageInfo = new PageInfo<>(baRolesList);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),baRolesList);
    }

    /**
     * 添加modal
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/addRolesModal.htm")
    public String addRoleModal(HttpServletRequest request,Long id){
        if(0 != id && null != id && !("").equals(id)){
            PBaCompany baCompany = baCompanyService.getBaCompanyById(id);
            if(null != baCompany){
                request.setAttribute("baCompany",baCompany);
            }
            BaRoles baRoles = baRolesService.getBaRoleByMaxListIndex();
            if(null != baRoles){
                request.setAttribute("listIndex",baRoles.getListIndex()+1);
            }else {
                request.setAttribute("listIndex",1);
            }
        }
        return "baRoles/addBaRolesModal";
    }

    /**
     * tree
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/userBaRoleTree.json")
    @ResponseBody
    public String userBaRoleTree(HttpServletRequest request,Long id,int type) {
        List<TreeJsonDTO> treeList = new ArrayList<>();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            List<BaResource> baResourceList = baResourceService.getUserRoleTree(shiroUser.getCompanyId(),id,type);
            for(BaResource item : baResourceList){
                TreeJsonDTO tree = new TreeJsonDTO();
                tree.setId(String.valueOf(item.getResourceId()));
                tree.setText(item.getCName());
                tree.setValue(String.valueOf(item.getResourceId()));
                tree.setAttribute("Category");
                tree.setAttributeValue(String.valueOf(item.getCategory()));
                tree.setShowcheck(true);
                if (item.getIsExpand()==1){
                    tree.setCheckstate(2);
                }
                tree.setIsexpand(true);
                tree.setComplete(true);
                tree.setParentId(String.valueOf(item.getParentId()));
                tree.setImg("/images/Icon16/"+item.getIcon());
                tree.setHasChildren(baResourceList.stream().filter(t -> Objects.equals(t.getParentId(), item.getResourceId())).count() > 0);
                treeList.add(tree);
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
        }
        return HandleHelper.TreeToJson2(treeList, "0");
    }

    /**
     * 添加
     * @param request
     * @param baRoles
     * @return
     */
    @RequestMapping(value = "/addBaRoles.json")
    @ResponseBody
    public Object addBaRole(HttpServletRequest request, BaRoles baRoles){
        Json json = new Json();
        try {
            BaRoles r= baRolesService.getRolesByCode(baRoles.getCode());
            if(null==r){
                BaRoles roles = baRolesService.getBaRolesByNameAndCompanyId(baRoles);
                if(null==roles) {
                    Subject user = SecurityUtils.getSubject();
                    ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                    baRoles.setCreateUserId(shiroUser.getId());
                    if (baRolesService.addBaRoles(baRoles)) {
                        json.setSuccess(true);
                        json.setMsg("添加角色成功");
                    } else {
                        json.setMsg("添加失败");
                    }
                }else {
                    json.setMsg("该公司下已有该角色，无法添加");
                }
            }else {
                json.setMsg("角色编码已经存在");
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
    @RequestMapping(value = "/updateRolesModal.htm")
    public String updateRolesModal(HttpServletRequest request, Long id){
        if(null != id && 0 != id && !("").equals(id)){
            BaRoles baRoles = baRolesService.getBaRolesById(id);
            if(null != baRoles){
                request.setAttribute("baRoles",baRoles);
                if(null != baRoles.getCompanyId() && 0 != baRoles.getCompanyId() && !("").equals(baRoles.getCompanyId())){
                    PBaCompany baCompany = baCompanyService.getBaCompanyById(baRoles.getCompanyId());
                    if(null != baCompany){
                        request.setAttribute("baCompany",baCompany);
                    }
                }
            }
        }
        return "baRoles/updateBaRolesModal";
    }

    /**
     * 修改角色信息
     * @param request
     * @param baRoles
     * @return
     */
    @RequestMapping(value = "/updateBaRoles.json")
    @ResponseBody
    public Object updateBaRoles(HttpServletRequest request,BaRoles baRoles){
        Json json = new Json();
        try {
            BaRoles o = baRolesService.getRolesByCode(baRoles.getCode());
            if(null==o || baRoles.getRoleId().equals(o.getRoleId())){
                List<BaUserRole> baUserRoleList = baUserRoleService.getUserRoleByRoleId(baRoles.getRoleId());
                BaRoles b = baRolesService.getBaRolesById(baRoles.getRoleId());
                if(null==baUserRoleList||0==baUserRoleList.size()||baRoles.getCategory().equals(b.getCategory())){
                    BaRoles roles = baRolesService.getBaRolesByNameAndCompanyId(baRoles);
                    if(null == roles || roles.getRoleId().equals(baRoles.getRoleId())){
                        Subject user = SecurityUtils.getSubject();
                        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                        baRoles.setUpdateUserId(shiroUser.getId());
                        if(baRolesService.updateBaRolesById(baRoles)){
                            json.setMsg("修改成功");
                            json.setSuccess(true);
                        }else {
                            json.setMsg("修改失败");
                        }
                    }else {
                        json.setMsg("该公司已有该角色，无法修改");
                    }
                }else {
                    json.setMsg("该角色无法修改角色分类");
                }
            }else {
                json.setMsg("编码已经存在");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 更改用户权限
     * @param request
     * @param list
     * @return
     */
    @RequestMapping(value = "/updateUserRoles.json")
    @ResponseBody
    public Object updateUserRoles(HttpServletRequest request,String[] list,Long roleId) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            Long[] resourceId = new Long[list.length];
            for (int i=0;i<list.length;i++){
                resourceId[i]= Long.valueOf(list[i]);
            }
            Boolean boo = baRoleResourceService.addBaRoleResourceByRoleId(roleId,shiroUser.getId(),resourceId);
            if (boo){
                json.setSuccess(true);
                json.setMsg("更改成功");
            }else {
                json.setMsg("更改失败");
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
            json.setMsg("服务器异常");
        }
        return json;
    }


    /**
     * 删除角色
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteBaRoles.json")
    @ResponseBody
    public Object deleteBaRoles(HttpServletRequest request, Long id){
        Json json = new Json();
        try {
            BaRoles baRoles = baRolesService.getBaRolesById(id);
            if(null != baRoles){
                List<BaUserRole> userRoles = baUserRoleService.getUserRoleByRoleId(id);
                if(null==userRoles||0==userRoles.size()){
                    Subject user = SecurityUtils.getSubject();
                    ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                    baRoles.setUpdateUserId(shiroUser.getId());
                    if(baRolesService.deleteBaRolesById(baRoles)){
                        json.setMsg("删除成功");
                        json.setSuccess(true);
                    }else {
                        json.setMsg("删除失败");
                    }
                }else {
                    json.setMsg("该角色下存在用户，无法删除");
                }
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取角色详情
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/rolesDetailModal.htm")
    public String rolesDetailsModal(HttpServletRequest request, Long id){
        BaRoles baRoles = baRolesService.getBaRolesById(id);
        if(null != baRoles){
            request.setAttribute("baRoles",baRoles);
            if(null != baRoles.getCompanyId() && 0 != baRoles.getCompanyId() && !("").equals(baRoles.getCompanyId())){
                PBaCompany baCompany = baCompanyService.getBaCompanyById(baRoles.getCompanyId());
                if(null != baCompany){
                    request.setAttribute("baCompany",baCompany);
                }
            }
        }
        return "baRoles/rolesDetailModal";
    }


    /**
     * 用户管理Tree
     * @param request
     * @return
     */
    @RequestMapping(value = "/userManageTree.json")
    @ResponseBody
    public String userManageTree(HttpServletRequest request) {
        List<TreeJsonDTO> treeList = new ArrayList<>();
        try {
            List<PBaRoles> baRolesList = baRolesService.getBaRolesAndCompanyName();
            for(PBaRoles item : baRolesList){
                TreeJsonDTO tree = new TreeJsonDTO();
                tree.setId(String.valueOf(item.getRoleId()));
                tree.setText(item.getCompanyName()+"-"+item.getCName());
                tree.setValue(String.valueOf(item.getRoleId()));
                tree.setAttribute("Category");
                tree.setAttributeValue(String.valueOf(item.getCategory()));
                tree.setShowcheck(false);
                tree.setIsexpand(true);
                tree.setComplete(true);
                tree.setParentId("0");
                tree.setImg("/images/Icon16/molecule.png");
                tree.setHasChildren(false);
                treeList.add(tree);
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
        }
        return HandleHelper.TreeToJson2(treeList, "0");
    }




}