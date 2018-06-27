package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BaRoleResourceMapper;
import com.dalian.sea.service.BaRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * BaRoleResourceServiceImpl
 *
 * @author xintao
 * @date 2018/1/16
 */
@Service("BaRoleResourceService")
public class BaRoleResourceServiceImpl implements BaRoleResourceService {
    @Autowired
    private BaRoleResourceMapper baRoleResourceMapper;


    /**
     * 根据角色添加资源
     * @param roleId
     * @param createUserId
     * @param resourceId
     * @return
     */
    @Override
    @Transactional
    public Boolean addBaRoleResourceByRoleId(Long roleId, Long createUserId, Long[] resourceId) {
        Boolean boo = false;
        try {
            baRoleResourceMapper.deleteBaRoleResourceByRoleId(roleId);
            Integer integer = baRoleResourceMapper.addBaRoleResourceByRoleId(roleId,createUserId,resourceId);
            if (0!=integer) {
                boo = true;
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }
}
