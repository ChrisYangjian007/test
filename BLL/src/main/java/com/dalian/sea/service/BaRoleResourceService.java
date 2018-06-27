package com.dalian.sea.service;

/**
 * BaRoleResourceService
 *
 * @author xintao
 * @date 2018/1/16
 */
public interface BaRoleResourceService {

    /**
     * 根据角色添加资源
     * @param roleId
     * @param createUserId
     * @param resourceId
     * @return
     */
    Boolean addBaRoleResourceByRoleId(Long roleId,Long createUserId,Long[] resourceId);




}
