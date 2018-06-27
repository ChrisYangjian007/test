package com.dalian.sea.service;

import com.dalian.sea.model.BaUser;
import com.dalian.sea.parameter.PBaUser;

import java.util.List;

/**
 * BaUserService
 *
 * @author xintao
 * @date 2018/1/8
 */
public interface BaUserService {

    /**
     * 获取用户根据用户类型
     * @param userType
     * @return
     */
    List<BaUser> getBaUserByUserType(int userType,Long companyId);

    /**
     * 获取用户
     * @param baUser
     * @return
     */
    PBaUser getBaUserBy(BaUser baUser);

    /**
     * api登陆
     * @param baUser
     * @return
     */
    PBaUser loginFromPDA(PBaUser baUser);

    /**
     * 获取用户
     * @param userId
     * @return
     */
    PBaUser getBaUserByUserId(Long userId);

    /**
     * 根据角色ID获取用户
     * @param roleId
     * @return
     */
    List<PBaUser> getBaUserByRoleId(Long roleId);

    /**
     * 根据角色ID获取用户
     * @param roleId
     * @param page
     * @param rows
     * @return
     */
    List<PBaUser> getBaUserByRoleIdAndPage(Long roleId,int page,int rows);

    /**
     * 根据获取用户
     * @param baUser
     * @param page
     * @param rows
     * @return
     */
    List<PBaUser> getBaUserByJqGrid(PBaUser baUser, int page, int rows);

    /**
     * 添加用户
     * @param baUser
     * @return
     */
    Long addBaUser(BaUser baUser);

    /**
     * 修改用户登陆次数和时间
     * @param userId
     * @return
     */
    void updateUserLoginDate(Long userId);

    /**
     * 获取所有用户
     * @return
     */
    List<BaUser> getAllUser();

    /**
     * 获取日志所有用户
     * @return
     */
    List<BaUser> getAllUserBySysLog(Long companyId);

    /**
     * 修改用户信息
     * @param baUser
     * @return
     */
    Boolean updateUserById(BaUser baUser,Long roleId1,Long roleId2,Long roleId3,Long roleId4);

    /**
     *充值密码
     * @param baUser
     * @return
     */
    Boolean resetPassword(BaUser baUser);

    /**
     * 添加用户
     * @param baUser
     * @param roleId1
     * @param roleId2
     * @param roleId3
     * @param roleId4
     * @return
     */
    Boolean addUser(BaUser baUser, Long roleId1, Long roleId2, Long roleId3, Long roleId4);

    /**
     * 通过登录名查找用户
     * @param baUser
     * @return
     */
    BaUser getBaUserByAccount(BaUser baUser);

    /**
     * 通过id获取用户信息
     * @param id
     * @return
     */
    PBaUser getPBaUserById(Long id);

    /**
     * 删除用户
     * @param baUser
     * @return
     */
    Boolean deleteUserById(BaUser baUser);
}
