package com.dalian.sea.mapper;

import com.dalian.sea.model.SysProductType;
import com.dalian.sea.parameter.PZsCompanyProduct;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysProductTypeMapper extends Mapper<SysProductType> {

    /**
     * 根据aLevel获取产品类型
     * @param aLevel
     * @return
     */
    SysProductType getSysProductTypeByNameAndParentIdAndALevel(@Param("name") String name,@Param("parentId") Long parentId,@Param("aLevel") Integer aLevel);

    /**
     * 获取全部产品类型
     * @return
     */
    List<SysProductType> getAllSsysProductType();

    /**
     * 获取产品类型
     * @return
     */
    List<SysProductType> getSysProductTypeByForGridTable(SysProductType sysProductType);

    /**
     * 通过id获取产品类型
     * @param id
     * @return
     */
    SysProductType getSysproductTypeById(Long id);

    /**
     * 获取最大顺序
     * @return
     */
    SysProductType getSysProductTypeMaxListIndex();

    /**
     * 通过parentId获取产品类型
     * @param id
     * @return
     */
    List<SysProductType> getSysProductTypeByParentId(Long id);

    /**
     * 添加产品类型
     * @param sysProductType
     * @return
     */
    Integer addSysProductType(SysProductType sysProductType);

    /**
     * 通过parentId和cName获取产品类型
     * @param sysProductType
     * @return
     */
    SysProductType getSysProductTypeByParentIdAndCName(SysProductType sysProductType);

    /**
     * 修改产品类型
     * @param sysProductType
     * @return
     */
    Integer updateSysProductTypeById(SysProductType sysProductType);

    /**
     * 修改产品类型
     * @param sysProductType
     * @return
     */
    Integer updateSysProductType(SysProductType sysProductType);

    /**
     * 根据aLevel获取产品类型
     * @param aLevel
     * @return
     */
    List<SysProductType> getSysProductTypeByALevel(Integer aLevel);

    /**
     * 通过首级名称、父级名称和自身名称获取
     * @param companyProduct
     * @return
     */
    SysProductType getSysProductTypeByNameAndParentName(PZsCompanyProduct companyProduct);

    /**
     * 删除
     *@param id
     * @return
     */
    Integer deleteSysProductTypeById(Long id);

    /**
     * 根据productTypeId获取自己和下级的全部产品类型
     * @param id
     * @return
     */
    List<SysProductType> getAllSysProductTypeById(Long id);

    /**
     * 批量删除
     * @param productTypeList
     * @return
     */
    Integer deleteProductTypeByList(@Param("productTypeList")List<SysProductType> productTypeList);
}