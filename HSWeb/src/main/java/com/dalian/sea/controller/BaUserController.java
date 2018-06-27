package com.dalian.sea.controller;

import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.BaCompany;
import com.dalian.sea.model.BaRoles;
import com.dalian.sea.model.BaUser;
import com.dalian.sea.parameter.PBaCompany;
import com.dalian.sea.parameter.PBaUser;
import com.dalian.sea.service.BaCompanyService;
import com.dalian.sea.service.BaRolesService;
import com.dalian.sea.service.BaUserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * BaUserController
 *
 * @author xintao
 * @date 2018/1/15
 */
@Controller
@Slf4j
@RequestMapping(value = "/user")
public class BaUserController extends LayoutRazor {
    @Autowired
    private BaUserService baUserService;
    @Autowired
    private BaCompanyService baCompanyService;
    @Autowired
    private BaRolesService baRolesService;

    /**
     * 用户管理
     * @return
     */
    @RequestMapping(value = "/baUserManage.htm")
    public String baUserManage(HttpServletRequest request){
        List<BaRoles> baRolesList = baRolesService.getAllBaRoles();
        if(null != baRolesList && 0 != baRolesList.size()){
            request.setAttribute("baRolesList",baRolesList);
        }
        return freeMarkerIndexResult("user/baUserManage.ftl",request);
    }

    /**
     * 添加用户Modal
     * @return
     */
    @RequestMapping(value = "/addUserModal.htm")
    public String addUserModal(HttpServletRequest request){
        List<BaRoles> baRolesList = baRolesService.getAllBaRoles();
        List<BaRoles> pcRolesList = new ArrayList<>();
        List<BaRoles> phoneRoleList = new ArrayList<>();
        if(null != baRolesList && 0 != baRolesList.size()){
            request.setAttribute("baRolesList",baRolesList);
            for(BaRoles baRoles : baRolesList){
                if(0==baRoles.getCategory()){
                    pcRolesList.add(baRoles);
                }else if(1==baRoles.getCategory()){
                    phoneRoleList.add(baRoles);
                }
            }
            request.setAttribute("pcRolesList",pcRolesList);
            request.setAttribute("phoneRoleList",phoneRoleList);
        }
        return "/user/addUserModal";
    }


    /**
     * 修改用户Modal
     * @return
     */
    @RequestMapping(value = "/updateUserModal.htm")
    public String updateUserModal(HttpServletRequest request,Long id){
        PBaUser baUser = baUserService.getPBaUserById(id);
        if (null!=baUser){
            if(null != baUser.getRolesList() && 0 != baUser.getRolesList().size()){
                for (BaRoles roles : baUser.getRolesList()){
                    if(0==roles.getCategory()){
                        baUser.setRolePCId(roles.getRoleId());
                        baUser.setRolePCName(roles.getCName());
                    }else {
                        baUser.setRolePhoneId(roles.getRoleId());
                        baUser.setRolePhoneName(roles.getCName());
                    }
                }
                if(null==baUser.getRolePCId()||"".equals(baUser.getRolePCId())){
                    baUser.setRolePCId(new Long("0"));
                }
                if(null==baUser.getRolePhoneId()||"".equals(baUser.getRolePhoneId())){
                    baUser.setRolePhoneId(new Long("0"));
                }
            }
            request.setAttribute("baUser",baUser);
        }
        List<BaRoles> baRolesList = baRolesService.getAllBaRoles();
        List<BaRoles> pcRolesList = new ArrayList<>();
        List<BaRoles> phoneRoleList = new ArrayList<>();
        if(null != baRolesList && 0 != baRolesList.size()){
            request.setAttribute("baRolesList",baRolesList);
            for(BaRoles baRoles : baRolesList){
                if(0==baRoles.getCategory()){
                    pcRolesList.add(baRoles);
                }else if(1==baRoles.getCategory()){
                    phoneRoleList.add(baRoles);
                }
            }
            request.setAttribute("pcRolesList",pcRolesList);
            request.setAttribute("phoneRoleList",phoneRoleList);
        }
        return "/user/updateUserModal";
    }

    /**
     * 用户管理
     * @param request
     * @param user
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request, PBaUser user, Integer page, Integer rows) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        user.setCompanyId(shiroUser.getCompanyId());
        List<PBaUser> baRolesList = baUserService.getBaUserByJqGrid(user,page,rows);
        PageInfo<PBaUser> pageInfo = new PageInfo<>(baRolesList);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),baRolesList);
    }


    /**
     * 查找角色名
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/getRoleName.json")
    @ResponseBody
    public Object getRoleName(HttpServletRequest request,Long id){
        Json json = new Json();
        try {
            PBaUser baUser = baUserService.getBaUserByUserId(id);
            if(null != baUser){
                json.setMsg(baUser.getRoleName());
                json.setSuccess(true);
            }else {
                json.setMsg("无权限");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }


    /**
     * 后台添加用户
     * @param request
     * @param baUser
     * @return
     */
    @RequestMapping(value = "/addUser.json")
    @ResponseBody
    public Object addUser(HttpServletRequest request,BaUser baUser,Long roleId1,Long roleId2,Long roleId3,Long roleId4){
        Json json = new Json();
        try {
            BaUser baUser1 = baUserService.getBaUserByAccount(baUser);
            if(null == baUser1){
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                baUser.setCreateUserId(shiroUser.getId());
                String algorithmName = "md5";
                int hashIterations = 2;
                String newPassword = new SimpleHash(algorithmName, baUser.getPassword(),  ByteSource.Util.bytes(baUser.getAccount()), hashIterations).toHex();
                baUser.setPassword(newPassword);
                if(baUserService.addUser(baUser,roleId1,roleId2,roleId3,roleId4)){
                    json.setMsg("添加成功");
                    json.setSuccess(true);
                }else {
                    json.setMsg("添加失败");
                }
            }else {
                json.setMsg("登录名重复");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }


    /**
     * 修改用户信息
     * @param request
     * @param baUser
     * @return
     */
    @RequestMapping(value = "/updateUser.json")
    @ResponseBody
    public Object updateUser(HttpServletRequest request,BaUser baUser,Long roleId1,Long roleId2,Long roleId3,Long roleId4){
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            baUser.setUpdateUserId(shiroUser.getId());
             if(baUserService.updateUserById(baUser,roleId1,roleId2,roleId3,roleId4)){
                json.setMsg("修改成功");
                json.setSuccess(true);
            }else {
                json.setMsg("修改失败");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 重置密码1
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/resetPasswordModal.htm")
    public String resetPasswordModal(HttpServletRequest request,Long id){
        if(null != id){
            BaUser baUser = baUserService.getBaUserByUserId(id);
            if(null != baUser){
                request.setAttribute("baUser",baUser);
            }
        }
        return "user/resetPasswordModal";
    }

    /**
     * 重置密码2
     * @param request
     * @param baUser
     * @return
     */
    @RequestMapping(value = "/resetPassword.json")
    @ResponseBody
    public Object resetPassword(HttpServletRequest request,BaUser baUser){
        Json json = new Json();
        try {
            BaUser user1 = baUserService.getPBaUserById(baUser.getUserId());
            String algorithmName = "md5";
            int hashIterations = 2;
            String newPassword = new SimpleHash(algorithmName, baUser.getPassword(),  ByteSource.Util.bytes(baUser.getAccount()), hashIterations).toHex();
            if (!newPassword.equals(user1.getPassword())) {
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                baUser.setUpdateUserId(shiroUser.getId());
                baUser.setPassword(newPassword);
                if (baUserService.resetPassword(baUser)) {
                    if (shiroUser.getId().equals(baUser.getUserId())) {
                        json.setObj(1);
                    } else {
                        json.setObj(0);
                    }
                    json.setMsg("重置成功");
                    json.setSuccess(true);
                } else {
                    json.setMsg("重置失败");
                }
            }else {
                json.setMsg("密码不能与上一次一致");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 删除用户
     * @return
     */
    @RequestMapping(value = "/deleteUser.json")
    @ResponseBody
    public Object deleteUser(HttpServletRequest request,Long id){
        Json json = new Json();
        try {
                BaUser baUser = baUserService.getBaUserByUserId(id);
                if(null != baUser){
                    Subject user = SecurityUtils.getSubject();
                    ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                    baUser.setUpdateUserId(shiroUser.getId());
                    if(baUserService.deleteUserById(baUser)){
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

    @RequestMapping(value = "/userDetailModal.htm")
    public String userDetailModal(HttpServletRequest request,Long id){
        if(null != id){
            PBaUser baUser = baUserService.getBaUserByUserId(id);
            if(null != baUser){
                request.setAttribute("baUser",baUser);
            }
        }
        return "user/userDetailModal";
    }
}
