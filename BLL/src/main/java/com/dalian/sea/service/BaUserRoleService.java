package com.dalian.sea.service;

import com.dalian.sea.model.BaUserRole;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9.
 */
public interface BaUserRoleService {

    /**
     * 通过roleId获取角色信息
     * @param roleId
     * @return
     */
    List<BaUserRole> getUserRoleByRoleId(Long roleId);
}
