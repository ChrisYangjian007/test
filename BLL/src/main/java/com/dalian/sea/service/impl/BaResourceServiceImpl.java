package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BaResourceMapper;
import com.dalian.sea.model.BaResource;
import com.dalian.sea.parameter.PBaResource;
import com.dalian.sea.parameter.ZTreeEncapsulation;
import com.dalian.sea.service.BaResourceService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * BaResourceServiceImpl
 *
 * @author xintao
 * @date 2018/1/9
 */
@Service("BaResourceService")
public class BaResourceServiceImpl implements BaResourceService {
    @Autowired
    private BaResourceMapper baResourceMapper;

    /**
     * 获取排序最大的资源
     * @return
     */
    @Override
    public BaResource getBaResourceByMaxListIndex() {
        return baResourceMapper.getBaResourceByMaxListIndex();
    }

    /**
     * 获取所有资源
     * @return
     */
    @Override
    public List<BaResource> getAllBaResource() {
        return baResourceMapper.getAllBaResource();
    }

    /**
     * 获取所有资源根据日志
     * @return
     */
    @Override
    public List<BaResource> getAllBaResourceBySysLog(Long companyId) {
        return baResourceMapper.getAllBaResourceBySysLog(companyId);
    }

    /**
     * 获取对应类型的资源
     * @return
     */
    @Override
    public List<BaResource> getAllBaResourceByResourceType(Long companyId,int resourceType) {
        return baResourceMapper.getAllBaResourceByResourceType(companyId,resourceType);
    }

    /**
     * 获取角色资源
     * @return
     */
    @Override
    public List<BaResource> getUserRoleTree(Long companyId,Long roleId,int resourceType) {
        List<BaResource> baResources = baResourceMapper.getAllBaResourceByResourceType(companyId,resourceType);
        List<BaResource> baResourceList = baResourceMapper.getAllBaResourceByRoleId(companyId,roleId,resourceType);
        List<Long> list = new ArrayList<Long>();
        for (BaResource resource : baResourceList){
            list.add(resource.getResourceId());
        }
        for (BaResource resource : baResources){
            if (list.contains(resource.getResourceId())){
                resource.setIsExpand((byte) 1);
            }else {
                resource.setIsExpand((byte) 0);
            }
        }
        return baResources;
    }

    /**
     * 导航
     * @param userId
     * @return
     */
    @Override
    public List<BaResource> getMenuByUserId(Long userId) {
        return baResourceMapper.getMenuByUserId(userId);
    }

    /**
     * 获取用户所有资源
     * @param userId
     * @return
     */
    @Override
    public List<BaResource> getAllBaResourceByUserId(Long userId,int resourceType) {
        return baResourceMapper.getAllBaResourceByUserId(userId,resourceType);
    }

    /**
     * 根据父级ID获取子级
     * @param pid
     * @return
     */
    @Override
    public List<BaResource> getAllBaResourceByPid(Long pid,int page,int rows) {
        PageHelper.startPage(page,rows);
        return baResourceMapper.getAllBaResourceByPid(pid);
    }

    /**
     * 根据情况获取子级
     * @param baResource
     * @return
     */
    @Override
    public List<BaResource> getBaResourceBy(BaResource baResource,int page,int rows) {
        PageHelper.startPage(page,rows);
        return baResourceMapper.getBaResourceBy(baResource);
    }

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    @Override
    public BaResource getBaResourceById(Long id) {
        return baResourceMapper.getBaResourceById(id);
    }

    /**
     * 判断同级同名
     * @param baResource
     * @return
     */
    @Override
    public BaResource getBaResourceByNameAndPid(BaResource baResource) {
        return baResourceMapper.getBaResourceByNameAndPid(baResource);
    }

    /**
     * 判断访问、权限路径
     * @param baResource
     * @return
     */
    @Override
    public BaResource getBaResourceByLocation(BaResource baResource) {
        return baResourceMapper.getBaResourceByLocation(baResource);
    }

    /**
     * 根据角色ID获取资源
     * @param roleId
     * @param resourceType
     * @return
     */
    @Override
    public List<BaResource> getAllBaResourceByRoleId(Long companyId,Long roleId,int resourceType) {
        return baResourceMapper.getAllBaResourceByRoleId(companyId,roleId,resourceType);
    }

    /**
     * 手机调用
     * @param roleId
     * @param resourceType
     * @return
     */
    @Override
    public List<PBaResource> getBaResourceByRoleId(Long roleId, int resourceType) {
        return baResourceMapper.getBaResourceByRoleId(roleId,resourceType);
    }

    /**
     * 添加资源
     * @param baResource
     * @return
     */
    @Override
    public Long addBaResource(BaResource baResource) {
        Long resourceId = null;
        Integer integer = baResourceMapper.addBaResource(baResource);
        if (0!=integer){
            resourceId = baResource.getResourceId();
        }
        return resourceId;
    }

    /**
     * 修改资源
     * @param baResource
     * @return
     */
    @Override
    public Boolean updateBaResource(BaResource baResource) {
        Boolean boo = false;
        Integer integer = baResourceMapper.updateBaResource(baResource);
        if (0!=integer){
            boo = true;
        }
        return boo;
    }

    /**
     * 删除资源
     * @param baResource
     * @return
     */
    @Override
    public Boolean deleteBaResource(BaResource baResource) {
        Boolean boo = false;
        Integer integer = baResourceMapper.deleteBaResource(baResource);
        if (0!=integer){
            boo = true;
        }
        return boo;
    }

    /**
     * 修改资源部分信息
     * @param baResource
     * @return
     */
    @Override
    public Boolean updateBaResourceById(BaResource baResource) {
        Boolean boo = false;
        Integer integer = baResourceMapper.updateBaResourceById(baResource);
        if (0!=integer){
            boo = true;
        }
        return boo;
    }

    /**
     * 通过编码获取资源
     * @param code
     * @return
     */
    @Override
    public BaResource getResourceByCode(String code) {
        return baResourceMapper.getResourceByCode(code);
    }
}
