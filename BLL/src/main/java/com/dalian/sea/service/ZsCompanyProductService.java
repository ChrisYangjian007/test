package com.dalian.sea.service;

import com.dalian.sea.json.Json;
import com.dalian.sea.model.SysProductType;
import com.dalian.sea.model.ZsCompanyProduct;
import com.dalian.sea.parameter.PZsCompanyProduct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ZsCompanyProductService
 *
 * @author xintao
 * @date 2018/1/23
 */
public interface ZsCompanyProductService {

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
     * 获取已有类别
     *
     * @return
     */
    List<PZsCompanyProduct> getTypeForApi(int page, int rows);

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
     * 根据类型、名称和规格获取
     *
     * @param companyProduct
     * @return
     */
    List<PZsCompanyProduct> getProductByTypeAndNameAndSpecificationForApi(PZsCompanyProduct companyProduct, int page, int rows);

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
     * @param page
     * @param rows
     * @return
     */
    List<PZsCompanyProduct> getZsCompanyProductBy(PZsCompanyProduct companyProduct, int page, int rows);

    /**
     * 根据编码、名称和规格获取
     *
     * @param companyProduct
     * @return
     */
    PZsCompanyProduct getZsCompanyProductByNoAndNameAndSpec(PZsCompanyProduct companyProduct);

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
    Long addZsCompanyProduct(ZsCompanyProduct companyProduct);

    /**
     * 修改
     *
     * @param companyProduct
     * @return
     */
    Boolean updateZsCompanyProduct(ZsCompanyProduct companyProduct);

    /**
     * 修改
     *
     * @param companyProduct
     * @return
     */
    Boolean updateZsCompanyProductBy(ZsCompanyProduct companyProduct);

    /**
     * 删除
     *
     * @param companyProduct
     * @return
     */
    Boolean deleteZsCompanyProduct(ZsCompanyProduct companyProduct);

    /**
     * 产品导出
     *
     * @param request
     * @param response
     * @return
     */
    Boolean productExport(HttpServletRequest request, HttpServletResponse response);

    /**
     * 通过productTypeId获取产品
     *
     * @param productTypeList
     * @return
     */
    List<ZsCompanyProduct> getZsCompanyProductByProductTypeList(List<SysProductType> productTypeList);

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
     * 产品导入判断
     *
     * @param lists
     * @param userId
     * @return
     */
    List<Json> productImport(List<List<Object>> lists, Long userId);

    /**
     * 添加
     *
     * @param companyProduct
     * @return
     */
    Boolean addZsCompanyProductList(List<PZsCompanyProduct> companyProduct);

    /**
     * 根据获取产品信息列表
     *
     * @param companyProduct
     * @return
     */
    List<PZsCompanyProduct> getZsCompanyProductList(PZsCompanyProduct companyProduct);

    /**
     * 根据产品大类Id
     * 获取规格
     *
     * @param goodsTypeId
     * @param id
     * @return
     */
    List<PZsCompanyProduct> getSpecByProductCategory(Long goodsTypeId, Long id);

    /**
     * 根据产品大类Id
     * 获取单位
     *
     * @param goodsTypeId
     * @param id
     * @return
     */
    List<PZsCompanyProduct> getUnitByProductCategory(Long goodsTypeId, Long id);
}
