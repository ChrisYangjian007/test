package com.dalian.sea.mapper;

import com.dalian.sea.model.SysProductType;
import com.dalian.sea.model.ZsCompanyProduct;
import com.dalian.sea.parameter.PZsCompanyProduct;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsCompanyProductMapper extends Mapper<ZsCompanyProduct> {

    /**
     * 获取已有产品线
     *
     * @return
     */
    List<PZsCompanyProduct> getProductLine();

    /**
     * 获取已有类别
     *
     * @return
     */
    List<PZsCompanyProduct> getType();

    /**
     * 获取已有名称
     *
     * @return
     */
    List<PZsCompanyProduct> getProductName();

    /**
     * 获取已有规格
     *
     * @return
     */
    List<PZsCompanyProduct> getSpecification();

    /**
     * 根据类型、名称和规格获取
     *
     * @param companyProduct
     * @return
     */
    List<PZsCompanyProduct> getProductByTypeAndNameAndSpecification(PZsCompanyProduct companyProduct);

    /**
     * 根据类型获取产品线
     *
     * @param companyProduct
     * @return
     */
    List<PZsCompanyProduct> getProductLineByType(PZsCompanyProduct companyProduct);

    /**
     * 根据情况获取
     *
     * @param companyProduct
     * @return
     */
    List<PZsCompanyProduct> getZsCompanyProductByProductType(PZsCompanyProduct companyProduct);

    /**
     * 根据情况获取
     *
     * @param companyProduct
     * @return
     */
    List<PZsCompanyProduct> getZsCompanyProductBy(PZsCompanyProduct companyProduct);

    /**
     * 根据编码、名称和规格获取
     *
     * @param companyProduct
     * @return
     */
    PZsCompanyProduct getZsCompanyProductByNoAndNameAndSpec(PZsCompanyProduct companyProduct);

    /**
     * 根据编码
     *
     * @param companyProduct
     * @return
     */
    PZsCompanyProduct getCompanyProductByNo(PZsCompanyProduct companyProduct);

    /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
    PZsCompanyProduct getZsCompanyProductById(Long id);

    /**
     * 添加
     *
     * @param companyProduct
     * @return
     */
    Integer addZsCompanyProduct(ZsCompanyProduct companyProduct);

    /**
     * 修改
     *
     * @param companyProduct
     * @return
     */
    Integer updateZsCompanyProduct(ZsCompanyProduct companyProduct);

    /**
     * 修改
     *
     * @param companyProduct
     * @return
     */
    Integer updateZsCompanyProductBy(ZsCompanyProduct companyProduct);

    /**
     * 删除
     *
     * @param companyProduct
     * @return
     */
    Integer deleteZsCompanyProduct(ZsCompanyProduct companyProduct);


    /**
     * 通过产品类型id获取产品
     *
     * @return
     */
    List<ZsCompanyProduct> getZsCompanyProductByProductTypeList(@Param("productTypeList") List<SysProductType> productTypeList);

    /**
     * 通过数据字典类型获取
     *
     * @param type
     * @return
     */
    List<ZsCompanyProduct> getByType(Long type);

    /**
     * 获取已有的规格
     *
     * @param type
     * @return List<String>
     */
    List<String> getProductSpecName(Long type);

    /**
     * 添加
     *
     * @param companyProduct
     * @return
     */
    Integer addZsCompanyProductList(@Param("companyProduct") List<PZsCompanyProduct> companyProduct);

    /**
     * 货品
     * 根据产品大类Id
     * 获取规格
     *
     * @param id
     * @param goodsTypeId
     * @return
     */
    List<PZsCompanyProduct> getSpecByProductCategory(@Param("goodsTypeId") Long goodsTypeId, @Param("id") Long id);

    /**
     * 货品
     * 根据产品大类Id
     * 获取单位
     *
     * @param id
     * @param goodsTypeId
     * @return
     */
    List<PZsCompanyProduct> getUnitByProductCategory(@Param("goodsTypeId") Long goodsTypeId, @Param("id") Long id);


    /**
     * 货品
     * 根据产品大类、产品线
     * 获取产品小类名称
     *
     * @param id
     * @param goodsTypeId
     * @return
     */
    List<PZsCompanyProduct> getProductByProductCategory(@Param("goodsTypeId") Long goodsTypeId, @Param("id") Long id);

}