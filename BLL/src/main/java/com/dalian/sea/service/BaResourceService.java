package com.dalian.sea.service;

import com.dalian.sea.model.BaResource;
import com.dalian.sea.parameter.PBaResource;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * BaResourceService
 *
 * @author xintao
 * @date 2018/1/9
 */
public interface BaResourceService {

    /**
     * 获取排序最大的资源
     * @return
     */
    BaResource getBaResourceByMaxListIndex();

    /**
     * 获取所有资源
     * @return
     */
    List<BaResource> getAllBaResource();

    /**
     * 获取所有资源根据日志
     * @return
     */
    List<BaResource> getAllBaResourceBySysLog(Long companyId);

    /**
     * 获取对应类型的资源
     * @return
     */
    List<BaResource> getAllBaResourceByResourceType(Long companyId,int resourceType);

    /**
     * 获取角色资源
     * @return
     */
    List<BaResource> getUserRoleTree(Long companyId,Long roleId,int resourceType);

    /**
     * 导航
     * @param userId
     * @return
     */
    List<BaResource> getMenuByUserId(Long userId);

    /**
     * 获取用户所有资源
     * @param userId
     * @param resourceType
     * @return
     */
    List<BaResource> getAllBaResourceByUserId(Long userId,int resourceType);

    /**
     * 根据父级ID获取子级
     * @param pid
     * @param page
     * @param rows
     * @return
     */
    List<BaResource> getAllBaResourceByPid(Long pid,int page,int rows);

    /**
     * 根据情况获取子级
     * @param baResource
     * @param page
     * @param rows
     * @return
     */
    List<BaResource> getBaResourceBy(BaResource baResource,int page,int rows);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    BaResource getBaResourceById(Long id);

    /**
     * 判断同级同名
     * @param baResource
     * @return
     */
    BaResource getBaResourceByNameAndPid(BaResource baResource);

    /**
     * 判断访问、权限路径
     * @param baResource
     * @return
     */
    BaResource getBaResourceByLocation(BaResource baResource);

    /**
     * 根据角色ID获取资源
     * @param roleId
     * @param resourceType
     * @return
     */
    List<BaResource> getAllBaResourceByRoleId(Long companyId,Long roleId,int resourceType);

    /**
     * 手机调用
     * @param roleId
     * @param resourceType
     * @return
     */
    List<PBaResource> getBaResourceByRoleId(Long roleId, int resourceType);

    /**
     * 添加资源
     * @param baResource
     * @return
     */
    Long addBaResource(BaResource baResource);

    /**
     * 修改资源
     * @param baResource
     * @return
     */
    Boolean updateBaResource(BaResource baResource);

    /**
     * 删除资源
     * @param baResource
     * @return
     */
    Boolean deleteBaResource(BaResource baResource);

    /**
     * 修改资源部分信息
     * @param baResource
     * @return
     */
    Boolean updateBaResourceById(BaResource baResource);

    /**
     * 通过编码获取资源
     * @param code
     * @return
     */
    BaResource getResourceByCode(String code);
}
