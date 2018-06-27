package com.dalian.sea.mapper;

import com.dalian.sea.model.BaRoleResource;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface BaRoleResourceMapper extends Mapper<BaRoleResource> {

    /**
     * 添加角色资源
     * @param baRoleResource
     * @return
     */
    Integer addBaRoleResource(BaRoleResource baRoleResource);

    /**
     * 修改角色资源
     * @param baRoleResource
     * @return
     */
    Integer updateBaRoleResource(BaRoleResource baRoleResource);

    /**
     * 删除角色资源
     * @param baRoleResource
     * @return
     */
    Integer deleteBaRoleResource(BaRoleResource baRoleResource);

    /**
     * 根据角色删除所有资源
     * @param roleId
     * @return
     */
    Integer deleteBaRoleResourceByRoleId(Long roleId);
    /**
     * 根据角色添加资源
     * @param roleId
     * @param createUserId
     * @param resourceId
     * @return
     */
    Integer addBaRoleResourceByRoleId(@Param("roleId") Long roleId,@Param("createUserId") Long createUserId,@Param("resourceId") Long[] resourceId);

}