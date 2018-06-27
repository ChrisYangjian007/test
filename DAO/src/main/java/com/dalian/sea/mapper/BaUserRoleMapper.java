package com.dalian.sea.mapper;

import com.dalian.sea.model.BaUser;
import com.dalian.sea.model.BaUserRole;
import com.dalian.sea.parameter.PBaUser;
import com.dalian.sea.parameter.PBaUserRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaUserRoleMapper extends Mapper<BaUserRole> {

    /**
     * 获取用户所有身份
     * @param userId
     * @return
     */
    BaUserRole getAllBaUserRoleByUserId(Long userId);

    /**
     * 获取用户身份
     * @param baUserRole
     * @return
     */
    BaUserRole getBaUserRoleBy(BaUserRole baUserRole);

    /**
     * 添加用户身份
     * @param baUserRole
     * @return
     */
    Integer addBaUserRole(BaUserRole baUserRole);

    /**
     * 修改用户身份
     * @param baUserRole
     * @return
     */
    Integer updateBaUserRole(BaUserRole baUserRole);

    /**
     * 删除用户身份
     * @param baUserRole
     * @return
     */
    Integer deleteBaUserRole(BaUserRole baUserRole);

    /**
     * 通过用户id删除用户身份
     * @return
     */
    Integer deleteBaUserRoleByUserId(BaUserRole baUserRole);

    /**
     * 通过用户集合获取角色名称
     * @param baUserList
     * @return
     */
    List<PBaUserRole> getPUserRoleByUserList(@Param("baUserList")List<PBaUser> baUserList);

    /**
     * 通过用户id获取角色信息
     * @param userId
     * @return
     */
    List<PBaUserRole> getUserRoleByUserId(Long userId);

    /**
     * 通过roleId获取角色信息
     * @param roleId
     * @return
     */
    List<BaUserRole> getUserRoleByRoleId(Long roleId);
}