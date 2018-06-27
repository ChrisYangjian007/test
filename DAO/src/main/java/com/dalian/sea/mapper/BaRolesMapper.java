package com.dalian.sea.mapper;

import com.dalian.sea.model.BaRoles;
import com.dalian.sea.parameter.PBaRoles;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaRolesMapper extends Mapper<BaRoles> {

    /**
     * 获取所有角色
     * @return
     */
    List<BaRoles> getAllBaRoles();

    /**
     * 根据公司ID获取角色
     * @param companyId
     * @return
     */
    List<PBaRoles> getBaRolesByCompanyId(@Param("companyId") Long companyId);

    /**
     * 获取角色和公司名称
     * @return
     */
    List<PBaRoles> getBaRolesAndCompanyName();

    /**
     * 根据用户ID获取角色
     * @param userId
     * @return
     */
    List<BaRoles> getBaRolesByUserId(@Param("userId") Long userId,@Param("category") int category);

    /**
     * 根据ID获取角色
     * @param id
     * @return
     */
    BaRoles getBaRolesById(Long id);

    /**
     * 根据名称获取角色
     * @param name
     * @return
     */
    BaRoles getBaRolesByName(String name);

    /**
     * 添加角色
     * @param baRoles
     * @return
     */
    Integer addBaRoles(BaRoles baRoles);

    /**
     * 修改角色
     * @param baRoles
     * @return
     */
    Integer updateBaRolesById(BaRoles baRoles);

    /**
     * 删除角色
     * @param baRoles
     * @return
     */
    Integer deleteBaRolesById(BaRoles baRoles);

    /**
     * 获取最大顺序
     * @return
     */
    BaRoles getBaRoleByMaxListIndex();

    /**
     * 通过名字和公司id获取角色
     * @param baRoles
     * @return
     */
    BaRoles getBaRolesByNameAndCompanyId(BaRoles baRoles);

    /**
     * 修改角色部分信息
     * @param baRoles
     * @return
     */
    Integer updateRolesById(BaRoles baRoles);

    /**
     * 通过编码获取角色
     * @param code
     * @return
     */
    BaRoles getRolesByCode(String code);
}