package com.dalian.sea.mapper;

import com.dalian.sea.model.BaResource;
import com.dalian.sea.parameter.PBaResource;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaResourceMapper extends Mapper<BaResource> {

    /**
     * 获取排序最大的资源
     * @return
     */
    BaResource getBaResourceByMaxListIndex();

    /**
     * 资源管理
     * @return
     */
    List<BaResource> getAllBaResource();

    /**
     * 获取所有资源根据日志
     * @return
     */
    List<BaResource> getAllBaResourceBySysLog(@Param("companyId") Long companyId);

    /**
     * 获取对应类型的资源
     * @return
     */
    List<BaResource> getAllBaResourceByResourceType(@Param("companyId") Long companyId,@Param("resourceType") int resourceType);

    /**
     * 导航
     * @param userId
     * @return
     */
    List<BaResource> getMenuByUserId(@Param("userId") Long userId);

    /**
     * 获取用户所有资源
     * @param userId
     * @return
     */
    List<BaResource> getAllBaResourceByUserId(@Param("userId") Long userId,@Param("resourceType") int resourceType);

    /**
     * 根据父级ID获取子级
     * @param pid
     * @return
     */
    List<BaResource> getAllBaResourceByPid(Long pid);

    /**
     * 根据情况获取子级
     * @param baResource
     * @return
     */
    List<BaResource> getBaResourceBy(BaResource baResource);

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
    List<BaResource> getAllBaResourceByRoleId(@Param("companyId") Long companyId,@Param("roleId") Long roleId,@Param("resourceType") int resourceType);


    /**
     * 手机调用
     * @param roleId
     * @param resourceType
     * @return
     */
    List<PBaResource> getBaResourceByRoleId(@Param("roleId") Long roleId, @Param("resourceType") int resourceType);

    /**
     * 添加资源
     * @param baResource
     * @return
     */
    Integer addBaResource(BaResource baResource);

    /**
     * 修改资源
     * @param baResource
     * @return
     */
    Integer updateBaResource(BaResource baResource);

    /**
     * 删除资源
     * @param baResource
     * @return
     */
    Integer deleteBaResource(BaResource baResource);

    /**
     * 修改资源
     * @param baResource
     * @return
     */
    Integer updateBaResourceById(BaResource baResource);

    /**
     * 通过编码获取资源
     * @param code
     * @return
     */
    BaResource getResourceByCode(String code);

}