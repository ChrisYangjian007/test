package com.dalian.sea.service;

import com.dalian.sea.model.BaRoles;
import com.dalian.sea.parameter.PBaRoles;

import java.util.List;

/**
 * BaRolesService
 *
 * @author xintao
 * @date 2018/1/15
 */
public interface BaRolesService {

    /**
     * 根据公司ID获取角色
     * @param companyId
     * @param page
     * @param rows
     * @return
     */
    List<PBaRoles> getBaRolesByCompanyId(Long companyId,Integer page, Integer rows);

    /**
     * 获取角色和公司名称
     * @return
     */
    List<PBaRoles> getBaRolesAndCompanyName();

    /**
     * 根据ID获取角色
     * @param id
     * @return
     */
    BaRoles getBaRolesById(Long id);
    /**
     * 获取最大顺序
     * @return
     */
    BaRoles getBaRoleByMaxListIndex();


    /**
     * 更改用户权限
     * @param id
     * @return
     */
    Boolean updateUserRoles(Long roleId,String[] id);


    /**
     * 添加角色
     * @param baRoles
     * @return
     */
    Boolean addBaRoles(BaRoles baRoles);

    /**
     * 通过名字和公司id获取角色
     * @return
     */
    BaRoles getBaRolesByNameAndCompanyId(BaRoles baRoles);

    /**
     * 通过id修改角色部分信息
     * @param baRoles
     * @return
     */
    Boolean updateBaRolesById(BaRoles baRoles);

    /**
     * 删除角色
     * @param baRoles
     * @return
     */
    Boolean deleteBaRolesById(BaRoles baRoles);

    /**
     * 获取全部角色信息
     * @return
     */
    List<BaRoles> getAllBaRoles();

    /**
     * 通过编码获取角色
     * @param code
     * @return
     */
    BaRoles getRolesByCode(String code);
}
