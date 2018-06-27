package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BaUserRoleMapper;
import com.dalian.sea.model.BaUserRole;
import com.dalian.sea.service.BaUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9.
 */
@Service("BaUserRoleService")
public class BaUserRoleServiceImpl implements BaUserRoleService{

    @Autowired
    private BaUserRoleMapper baUserRoleMapper;

    /**
     * 通过roleId获取角色信息
     * @param roleId
     * @return
     */
    @Override
    public List<BaUserRole> getUserRoleByRoleId(Long roleId) {
        return baUserRoleMapper.getUserRoleByRoleId(roleId);
    }
}
