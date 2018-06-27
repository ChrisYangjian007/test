package com.dalian.sea.mapper;

import com.dalian.sea.model.BaUser;
import com.dalian.sea.parameter.PBaUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaUserMapper extends Mapper<BaUser> {

    /**
     * 获取用户根据用户类型
     * @param userType
     * @return
     */
    List<BaUser> getBaUserByUserType(@Param("userType") int userType,@Param("companyId") Long companyId);

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
    PBaUser login(BaUser baUser);

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
     * 根据获取用户
     * @param baUser
     * @return
     */
    List<PBaUser> getBaUserByJqGrid(PBaUser baUser);

    /**
     * 添加用户
     * @param baUser
     * @return
     */
    Integer addBaUser(BaUser baUser);

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
    List<BaUser> getAllUserBySysLog(@Param("companyId")Long companyId);

    /**
     *修改全部用户信息
     * @param baUser
     * @return
     */
    Integer updateBaUser(BaUser baUser);

    /**
     * 修改用户信息
     * @param baUser
     * @return
     */
    Integer updateUserById(BaUser baUser);

    /**
     * 重置密码
     * @param baUser
     * @return
     */
    Integer resetPassword(BaUser baUser);

    /**
     * 获取最大顺序数
     * @return
     */
    BaUser getMaxListIndex();

    /**
     * 通过登录名查找用户
     * @param baUser
     * @return
     */
    BaUser getBaUserByAccount(BaUser baUser);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    PBaUser getPBaUserById(Long id);

    /**
     * 删除用户
     * @param baUser
     * @return
     */
    Integer deleteUserById(BaUser baUser);
}