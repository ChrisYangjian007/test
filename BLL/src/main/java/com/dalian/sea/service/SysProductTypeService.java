package com.dalian.sea.service;

import com.dalian.sea.model.SysProductType;
import com.dalian.sea.parameter.PZsCompanyProduct;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */
public interface SysProductTypeService {

    /**
     *获取所有产品类型
     * @return
     */
    List<SysProductType> getAllSysProductType();

    /**
     *获取产品类型
     * @return
     */
    List<SysProductType> getSysProductTypeByForGridTable(SysProductType sysProductType,Integer page,Integer rows);

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
     * @return
     */
    List<SysProductType> getSysProductTypeByParentId(Long id);

    /**
     * 添加产品类型
     * @param sysProductType
     * @return
     */
    Boolean addSysProductType(SysProductType sysProductType);

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
    Boolean updateSysProductTypeById(SysProductType sysProductType);

    /**
     * 修改产品类型
     * @param sysProductType
     * @return
     */
    Boolean updateSysProductType(SysProductType sysProductType);


    /**
     * 根据aLevel获取产品类型
     * @param aLevel
     * @return
     */
    List<SysProductType> getSysProductTypeByALevel(Integer aLevel);

    /**
     * 删除产品类型
     * @param id
     * @return
     */
    Boolean deleteProductTypeById(Long id);

    /**
     * 通过首级名称、父级名称和自身名称获取
     * @param companyProduct
     * @return
     */
    SysProductType getSysProductTypeByNameAndParentName(PZsCompanyProduct companyProduct);

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
    Boolean deleteProductTypeByList(List<SysProductType> productTypeList);
}
