package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BaResourceMapper;
import com.dalian.sea.mapper.BaRolesMapper;
import com.dalian.sea.mapper.BaUserMapper;
import com.dalian.sea.mapper.BaUserRoleMapper;
import com.dalian.sea.model.BaResource;
import com.dalian.sea.model.BaRoles;
import com.dalian.sea.model.BaUser;
import com.dalian.sea.model.BaUserRole;
import com.dalian.sea.parameter.PBaResource;
import com.dalian.sea.parameter.PBaUser;
import com.dalian.sea.parameter.PBaUserRole;
import com.dalian.sea.service.BaUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * BaUserServiceImpl
 *
 * @author xintao
 * @date 2018/1/8
 */
@Service("BaUserService")
public class BaUserServiceImpl implements BaUserService {
    @Autowired
    private BaUserMapper baUserMapper;
    @Autowired
    private BaUserRoleMapper baUserRoleMapper;
    @Autowired
    private BaRolesMapper baRolesMapper;
    @Autowired
    private BaResourceMapper baResourceMapper;

    /**
     * 获取用户根据用户类型
     * @param userType
     * @return
     */
    @Override
    public List<BaUser> getBaUserByUserType(int userType,Long companyId) {
        return baUserMapper.getBaUserByUserType(userType,companyId);
    }

    /**
     * 获取用户
     * @param baUser
     * @return
     */
    @Override
    public PBaUser getBaUserBy(BaUser baUser) {
        return baUserMapper.getBaUserBy(baUser);
    }

    /**
     * api登陆
     * @param baUser
     * @return
     */
    @Override
    public PBaUser loginFromPDA(PBaUser baUser) {
        List<BaRoles> roles = baRolesMapper.getBaRolesByUserId(baUser.getUserId(),1);
        if (null!=roles && 0!=roles.size() && 1==roles.size()){
            baUser.setRolePhoneId(roles.get(0).getRoleId());
            baUser.setRolePhoneName(roles.get(0).getCName());
            List<PBaResource> resources = baResourceMapper.getBaResourceByRoleId(roles.get(0).getRoleId(),1);
            if (null!=resources && 0!=resources.size()){
                baUser.setUserResource(resources);
                baUserMapper.updateUserLoginDate(baUser.getUserId());
            }else {
                baUser = null;
            }
        }else {
            baUser = null;
        }
        return baUser;
    }

    /**
     * 获取用户
     * @param userId
     * @return
     */
    @Override
    public PBaUser getBaUserByUserId(Long userId) {
        PBaUser pBaUser=baUserMapper.getBaUserByUserId(userId);
        List<PBaUserRole> baUserRoleList = baUserRoleMapper.getUserRoleByUserId(userId);
        if(null!=baUserRoleList&&0!=baUserRoleList.size()){
            String roleName = "";
            for(PBaUserRole pBaUserRole:baUserRoleList){
                roleName += pBaUserRole.getBaRoles().getCName() + " ";
            }
            pBaUser.setRoleName(roleName);
        }
        return pBaUser;
    }

    /**
     * 根据角色ID获取用户
     * @param roleId
     * @return
     */
    @Override
    public List<PBaUser> getBaUserByRoleId(Long roleId) {
        return baUserMapper.getBaUserByRoleId(roleId);
    }

    /**
     * 根据角色ID获取用户
     * @param roleId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PBaUser> getBaUserByRoleIdAndPage(Long roleId, int page, int rows) {
        PageHelper.startPage(page,rows);
        return baUserMapper.getBaUserByRoleId(roleId);
    }

    /**
     * 根据获取用户
     * @param baUser
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PBaUser> getBaUserByJqGrid(PBaUser baUser, int page, int rows) {
        PageHelper.startPage(page,rows);
        List<PBaUser> baUserList = baUserMapper.getBaUserByJqGrid(baUser);
        if (null!=baUserList&&0!=baUserList.size()) {
            List<PBaUserRole> pBaUserRoleList = baUserRoleMapper.getPUserRoleByUserList(baUserList);
            if (null != pBaUserRoleList && 0 != pBaUserRoleList.size()) {
                for (PBaUser baUser1 : baUserList) {
                    String roleName = "";
                    for (PBaUserRole baUserRole : pBaUserRoleList) {
                        if (baUser1.getUserId().equals(baUserRole.getUserId())) {
                            roleName += baUserRole.getBaRoles().getCName() + " ";
                        }
                    }
                    baUser1.setRoleName(roleName);
                }
            }
        }
        return baUserList;
    }

    /**
     * 添加用户
     * @param baUser
     * @return
     */
    @Override
    public Long addBaUser(BaUser baUser) {
        Long id = null;
        Integer integer = baUserMapper.addBaUser(baUser);
        if (0!=integer){
            id=baUser.getUserId();
        }
        return id;
    }

    /**
     * 修改用户登陆次数和时间
     * @param userId
     * @return
     */
    @Override
    public void updateUserLoginDate(Long userId) {
        baUserMapper.updateUserLoginDate(userId);
    }

    /**
     * 获取所有用户
     * @return
     */
    @Override
    public List<BaUser> getAllUser() {
        return baUserMapper.getAllUser();
    }

    /**
     * 获取日志所有用户
     * @return
     */
    @Override
    public List<BaUser> getAllUserBySysLog(Long companyId) {
        return baUserMapper.getAllUserBySysLog(companyId);
    }

    /**
     * 修改用户信息
     * @param baUser
     * @return
     */
    @Override
    @Transactional
    public Boolean updateUserById(BaUser baUser,Long roleId1,Long roleId2,Long roleId3,Long roleId4) {
        Boolean boo = false ;
        try {
                //将用户原来的角色删除
                BaUserRole userRole = new BaUserRole();
                userRole.setUserId(baUser.getUserId());
                userRole.setUpdateUserId(baUser.getUpdateUserId());
                Integer result1 = baUserRoleMapper.deleteBaUserRoleByUserId(userRole);
            if(1==baUser.getUserType()){
                BaRoles baRoles = baRolesMapper.getBaRolesById(roleId1);
                if(null != baRoles){
                    baUser.setCompanyId(baRoles.getCompanyId());
                }
                BaUserRole baUserRole = new BaUserRole();
                baUserRole.setUserId(baUser.getUserId());
                baUserRole.setRoleId(roleId1);
                baUserRole.setCreateUserId(baUser.getCreateUserId());
                Integer integer = baUserRoleMapper.addBaUserRole(baUserRole);
                Integer result = baUserMapper.updateUserById(baUser);
                if(0<result&&0<integer&&0<result1){
                    boo = true ;
                }else {
                    throw new Exception("rollBack");
                }
            }else if(2==baUser.getUserType()){
                BaRoles baRoles = baRolesMapper.getBaRolesById(roleId2);
                if(null != baRoles){
                    baUser.setCompanyId(baRoles.getCompanyId());
                }
                BaUserRole baUserRole = new BaUserRole();
                baUserRole.setUserId(baUser.getUserId());
                baUserRole.setRoleId(roleId2);
                baUserRole.setCreateUserId(baUser.getCreateUserId());
                Integer integer = baUserRoleMapper.addBaUserRole(baUserRole);
                Integer result = baUserMapper.updateUserById(baUser);
                if(0<result&&0<integer&&0<result1){
                    boo = true ;
                }else {
                    throw new Exception("rollBack");
                }
            }else {
                BaRoles baRoles1 = baRolesMapper.getBaRolesById(roleId3);
                BaRoles baRoles2 = baRolesMapper.getBaRolesById(roleId4);
                if(null != baRoles1){
                    baUser.setCompanyId(baRoles1.getCompanyId());
                }
                BaUserRole baUserRole1 = new BaUserRole();
                BaUserRole baUserRole2 = new BaUserRole();
                baUserRole1.setUserId(baUser.getUserId());
                baUserRole2.setUserId(baUser.getUserId());
                baUserRole1.setRoleId(roleId3);
                baUserRole2.setRoleId(roleId4);
                baUserRole1.setCreateUserId(baUser.getCreateUserId());
                baUserRole2.setCreateUserId(baUser.getCreateUserId());
                Integer integer1 = baUserRoleMapper.addBaUserRole(baUserRole1);
                Integer integer2 = baUserRoleMapper.addBaUserRole(baUserRole2);
                Integer result = baUserMapper.updateUserById(baUser);
                if(0<result&&0<integer1&&0<integer2&&0<result1){
                    boo = true ;
                }else {
                    throw new Exception("rollBack");
                }
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

    /**
     * 重置密码
     * @param baUser
     * @return
     */
    @Override
    public Boolean resetPassword(BaUser baUser) {
        Boolean boo = false;
        Integer result = baUserMapper.resetPassword(baUser);
        if(0<result){
            boo = true ;
        }
        return boo;
    }

    /**
     * 添加用户
     * @param baUser
     * @param roleId1
     * @param roleId2
     * @param roleId3
     * @param roleId4
     * @return
     */
    @Override
    @Transactional
    public Boolean addUser(BaUser baUser, Long roleId1, Long roleId2, Long roleId3, Long roleId4) {
        Boolean boo = false ;
        Integer integer1= null ;
        Integer integer2 = null ;
        Integer integer3 = null;
        try {
            if(1==baUser.getUserType()){
                BaRoles baRoles = baRolesMapper.getBaRolesById(roleId1);
                if(null != baRoles){
                    baUser.setCompanyId(baRoles.getCompanyId());
                    BaUser user = baUserMapper.getMaxListIndex();
                    if(null != user){
                        baUser.setListIndex(user.getListIndex()+1);
                    }else {
                        baUser.setListIndex(1);
                    }
                    integer1 = baUserMapper.addBaUser(baUser);
                    BaUserRole baUserRole = new BaUserRole();
                    baUserRole.setRoleId(baRoles.getRoleId());
                    baUserRole.setUserId(baUser.getUserId());
                    baUserRole.setCreateUserId(baUser.getCreateUserId());
                    integer2 = baUserRoleMapper.addBaUserRole(baUserRole);
                }
                if(0 != integer1 && 0 != integer2){
                    boo = true ;
                }else {
                    throw new Exception("rollBack");
                }
            }else if(2==baUser.getUserType()){
                BaRoles baRoles = baRolesMapper.getBaRolesById(roleId2);
                if(null != baRoles){
                    baUser.setCompanyId(baRoles.getCompanyId());
                    BaUser user = baUserMapper.getMaxListIndex();
                    if(null != user){
                        baUser.setListIndex(user.getListIndex()+1);
                    }else {
                        baUser.setListIndex(1);
                    }
                    integer1 = baUserMapper.addBaUser(baUser);
                    BaUserRole baUserRole = new BaUserRole();
                    baUserRole.setRoleId(baRoles.getRoleId());
                    baUserRole.setUserId(baUser.getUserId());
                    baUserRole.setCreateUserId(baUser.getCreateUserId());
                    integer2 = baUserRoleMapper.addBaUserRole(baUserRole);
                }
                if(0 != integer1 && 0 != integer2){
                    boo = true ;
                }else {
                    throw new Exception("rollBack");
                }
            }else {
                BaRoles baRoles1 = baRolesMapper.getBaRolesById(roleId3);
                BaRoles baRoles2 = baRolesMapper.getBaRolesById(roleId4);
                if(null != baRoles1 && null !=baRoles2){
                    baUser.setCompanyId(baRoles1.getCompanyId());
                    BaUser user = baUserMapper.getMaxListIndex();
                    if(null != user){
                        baUser.setListIndex(user.getListIndex()+1);
                    }else {
                        baUser.setListIndex(1);
                    }
                    integer1 = baUserMapper.addBaUser(baUser);
                    BaUserRole baUserRole1 = new BaUserRole();
                    BaUserRole baUserRole2 = new BaUserRole();
                    baUserRole1.setRoleId(baRoles1.getRoleId());
                    baUserRole2.setRoleId(baRoles2.getRoleId());
                    baUserRole1.setUserId(baUser.getUserId());
                    baUserRole2.setUserId(baUser.getUserId());
                    baUserRole1.setCreateUserId(baUser.getCreateUserId());
                    baUserRole2.setCreateUserId(baUser.getCreateUserId());
                    integer2 = baUserRoleMapper.addBaUserRole(baUserRole1);
                    integer3 = baUserRoleMapper.addBaUserRole(baUserRole2);
                }
                if(0 != integer1 && 0 != integer2 && 0 != integer3){
                    boo = true ;
                }else {
                    throw new Exception("rollBack");
                }
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

    /**
     * 通过登录名查找用户
     * @param baUser
     * @return
     */
    @Override
    public BaUser getBaUserByAccount(BaUser baUser) {
        return baUserMapper.getBaUserByAccount(baUser);
    }

    /**
     * 通过id获取用户信息
     * @param id
     * @return
     */
    @Override
    public PBaUser getPBaUserById(Long id) {
        return baUserMapper.getPBaUserById(id);
    }

    /**
     * 删除用户
     * @param baUser
     * @return
     */
    @Override
    @Transactional
    public Boolean deleteUserById(BaUser baUser) {
        Boolean boo = false;
        try{
            if(null != baUser) {
                BaUserRole baUserRole = new BaUserRole();
                baUserRole.setUserId(baUser.getUserId());
                baUserRole.setUpdateUserId(baUser.getUpdateUserId());
                Integer integer = baUserRoleMapper.deleteBaUserRoleByUserId(baUserRole);
                Integer result = baUserMapper.deleteUserById(baUser);
                if (0<integer&&0 < result) {
                    boo = true;
                }else {
                    throw new Exception("rollBack");
                }
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }
}
