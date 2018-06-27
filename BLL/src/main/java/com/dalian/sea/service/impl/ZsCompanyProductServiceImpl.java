package com.dalian.sea.service.impl;

import com.dalian.sea.ExcelUtil.ExcelUtil;
import com.dalian.sea.json.Json;
import com.dalian.sea.mapper.BaDataDictionaryDetailsMapper;
import com.dalian.sea.mapper.SysProductTypeMapper;
import com.dalian.sea.mapper.SysUnitMapper;
import com.dalian.sea.mapper.ZsCompanyProductMapper;
import com.dalian.sea.model.BaDataDictionaryDetails;
import com.dalian.sea.model.SysProductType;
import com.dalian.sea.model.SysUnit;
import com.dalian.sea.model.ZsCompanyProduct;
import com.dalian.sea.parameter.PZsCompanyProduct;
import com.dalian.sea.service.ZsCompanyProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * ZsCompanyProductServiceImpl
 *
 * @author xintao
 * @date 2018/1/23
 */
@Service("ZsCompanyProductService")
public class ZsCompanyProductServiceImpl implements ZsCompanyProductService {
    @Autowired
    private ZsCompanyProductMapper companyProductMapper;
    @Autowired
    private BaDataDictionaryDetailsMapper dataDictionaryDetailsMapper;
    @Autowired
    private SysProductTypeMapper productTypeMapper;
    @Autowired
    private SysUnitMapper sysUnitMapper;


    /**
     * 获取已有产品线
     *
     * @return
     */
    @Override
    public List<PZsCompanyProduct> getProductLine() {
        return companyProductMapper.getProductLine();
    }

    /**
     * 获取已有类别
     *
     * @return
     */
    @Override
    public List<PZsCompanyProduct> getType() {
        return companyProductMapper.getType();
    }

    /**
     * 获取已有类别
     *
     * @return
     */
    @Override
    public List<PZsCompanyProduct> getTypeForApi(int page, int rows) {
        PageHelper.startPage(page, rows);
        return companyProductMapper.getType();
    }

    /**
     * 获取已有名称
     *
     * @return
     */
    @Override
    public List<PZsCompanyProduct> getProductName() {
        return companyProductMapper.getProductName();
    }

    /**
     * 获取已有规格
     *
     * @return
     */
    @Override
    public List<PZsCompanyProduct> getSpecification() {
        return companyProductMapper.getSpecification();
    }

    /**
     * 根据类型、名称和规格获取
     *
     * @param companyProduct
     * @return
     */
    @Override
    public List<PZsCompanyProduct> getProductByTypeAndNameAndSpecification(PZsCompanyProduct companyProduct) {
        return companyProductMapper.getProductByTypeAndNameAndSpecification(companyProduct);
    }

    /**
     * 根据类型、名称和规格获取
     *
     * @param companyProduct
     * @return
     */
    @Override
    public List<PZsCompanyProduct> getProductByTypeAndNameAndSpecificationForApi(PZsCompanyProduct companyProduct, int page, int rows) {
        PageHelper.startPage(page, rows);
        return companyProductMapper.getProductByTypeAndNameAndSpecification(companyProduct);
    }

    /**
     * 根据类型获取产品线
     *
     * @param companyProduct
     * @return
     */
    @Override
    public List<PZsCompanyProduct> getProductLineByType(PZsCompanyProduct companyProduct) {
        return companyProductMapper.getProductLineByType(companyProduct);
    }

    /**
     * 根据情况获取
     *
     * @param companyProduct
     * @return
     */
    @Override
    public List<PZsCompanyProduct> getZsCompanyProductByProductType(PZsCompanyProduct companyProduct) {
        return companyProductMapper.getZsCompanyProductByProductType(companyProduct);
    }

    /**
     * 根据情况获取
     *
     * @param companyProduct
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PZsCompanyProduct> getZsCompanyProductBy(PZsCompanyProduct companyProduct, int page, int rows) {
        PageHelper.startPage(page, rows);
        return companyProductMapper.getZsCompanyProductBy(companyProduct);
    }

    /**
     * 根据编码、名称和规格获取
     *
     * @param companyProduct
     * @return
     */
    @Override
    public PZsCompanyProduct getZsCompanyProductByNoAndNameAndSpec(PZsCompanyProduct companyProduct) {
        return companyProductMapper.getZsCompanyProductByNoAndNameAndSpec(companyProduct);
    }

    /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
    @Override
    public PZsCompanyProduct getZsCompanyProductById(Long id) {
        return companyProductMapper.getZsCompanyProductById(id);
    }

    /**
     * 添加
     *
     * @param companyProduct
     * @return
     */
    @Override
    public Long addZsCompanyProduct(ZsCompanyProduct companyProduct) {
        Long id = null;
        Integer integer = companyProductMapper.addZsCompanyProduct(companyProduct);
        if (0 != integer) {
            id = companyProduct.getProductId();
        }
        return id;
    }

    /**
     * 修改
     *
     * @param companyProduct
     * @return
     */
    @Override
    public Boolean updateZsCompanyProduct(ZsCompanyProduct companyProduct) {
        Boolean boo = false;
        Integer integer = companyProductMapper.updateZsCompanyProduct(companyProduct);
        if (0 != integer) {
            boo = true;
        }
        return boo;
    }

    /**
     * 修改
     *
     * @param companyProduct
     * @return
     */
    @Override
    public Boolean updateZsCompanyProductBy(ZsCompanyProduct companyProduct) {
        Boolean boo = false;
        Integer integer = companyProductMapper.updateZsCompanyProductBy(companyProduct);
        if (0 != integer) {
            boo = true;
        }
        return boo;
    }

    /**
     * 删除
     *
     * @param companyProduct
     * @return
     */
    @Override
    public Boolean deleteZsCompanyProduct(ZsCompanyProduct companyProduct) {
        Boolean boo = false;
        Integer integer = companyProductMapper.deleteZsCompanyProduct(companyProduct);
        if (0 != integer) {
            boo = true;
        }
        return boo;
    }

    @Override
    @Transactional
    public Boolean productExport(HttpServletRequest request, HttpServletResponse response) {
        Boolean boo = false;
        try {
            List<String> name = new ArrayList<>();
            List<List<?>> view = new ArrayList<>();
            List<String> str = new ArrayList<>();
            List<PZsCompanyProduct> companyProductList = companyProductMapper.getZsCompanyProductBy(null);
            String title = "产品列表";
            name.add("产品编码");
            for (PZsCompanyProduct companyProduct : companyProductList) {
                if (null != companyProduct.getProductNo()) {
                    str.add(companyProduct.getProductNo());
                } else {
                    str.add("");
                }
            }
            view.add(str);

            name.add("产品名称");
            str = new ArrayList<>();
            for (PZsCompanyProduct companyProduct : companyProductList) {
                if (null != companyProduct.getCName()) {
                    str.add(companyProduct.getCName());
                } else {
                    str.add("");
                }
            }
            view.add(str);

            name.add("类别");
            str = new ArrayList<>();
            for (PZsCompanyProduct companyProduct : companyProductList) {
                if (null != companyProduct.getTypeName()) {
                    str.add(companyProduct.getTypeName());
                } else {
                    str.add("");
                }
            }
            view.add(str);

            name.add("产品线");
            str = new ArrayList<>();
            for (PZsCompanyProduct companyProduct : companyProductList) {
                if (null != companyProduct.getProductLine()) {
                    str.add(companyProduct.getProductLine());
                } else {
                    str.add("");
                }
            }
            view.add(str);
            name.add("产品大类");
            str = new ArrayList<>();
            for (PZsCompanyProduct companyProduct : companyProductList) {
                if (null != companyProduct.getProductCategory()) {
                    str.add(companyProduct.getProductCategory());
                } else {
                    str.add("");
                }
            }
            view.add(str);
            name.add("产品小类");
            str = new ArrayList<>();
            for (PZsCompanyProduct companyProduct : companyProductList) {
                if (null != companyProduct.getProductTypeName()) {
                    str.add(companyProduct.getProductTypeName());
                } else {
                    str.add("");
                }
            }
            view.add(str);
            name.add("产品规格");
            str = new ArrayList<>();
            for (PZsCompanyProduct companyProduct : companyProductList) {
                if (null != companyProduct.getProductSpecification()) {
                    str.add(companyProduct.getProductSpecification());
                } else {
                    str.add("");
                }
            }
            view.add(str);
            name.add("单位");
            str = new ArrayList<>();
            for (PZsCompanyProduct companyProduct : companyProductList) {
                if (null != companyProduct.getSysUnitName()) {
                    str.add(companyProduct.getSysUnitName());
                } else {
                    str.add("");
                }
            }
            view.add(str);
            name.add("净重");
            str = new ArrayList<>();
            for (PZsCompanyProduct companyProduct : companyProductList) {
                if (null != companyProduct.getNetWeight()) {
                    str.add(companyProduct.getNetWeight());
                } else {
                    str.add("");
                }
            }
            view.add(str);
            ExcelUtil.xsl(title, name, view, request, response);

            boo = true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

    /**
     * 通过productTypeId获取产品
     *
     * @param productTypeList
     * @return
     */
    @Override
    public List<ZsCompanyProduct> getZsCompanyProductByProductTypeList(List<SysProductType> productTypeList) {
        return companyProductMapper.getZsCompanyProductByProductTypeList(productTypeList);
    }

    @Override
    public List<ZsCompanyProduct> getByType(Long type) {
        return companyProductMapper.getByType(type);
    }

    @Override
    public List<String> getProductSpecName(Long type) {
        return companyProductMapper.getProductSpecName(type);
    }

    @Override
    public List<Json> productImport(List<List<Object>> lists, Long userId) {
        List<Json> jsonList = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            List<Object> object = lists.get(i);
            String str = "";
            String error = "";
            PZsCompanyProduct companyProduct = new PZsCompanyProduct();
            int objectIndex = (i + 2);
            if (7<=object.size()) {
                companyProduct.setProductNo(String.valueOf(object.get(0)));
                if (null != String.valueOf(object.get(1)) && !"".equals(String.valueOf(object.get(1)))) {
                    companyProduct.setCName(String.valueOf(object.get(1)));
                } else {
                    error += "产品名称为空！";
                }
                if (null != String.valueOf(object.get(2)) && !"".equals(String.valueOf(object.get(2)))) {
                    String type = String.valueOf(object.get(2));
                    BaDataDictionaryDetails dataDictionaryDetails = dataDictionaryDetailsMapper.getBaDataDictionaryDetailsByDataNameAndDataDetailsName("货物类型", type);
                    if (null != dataDictionaryDetails) {
                        companyProduct.setType(dataDictionaryDetails.getDataDictionaryDetailsId());
                        companyProduct.setTypeName(type);
                    } else {
                        error += "无 (" + type + ") 类别！";
                    }
                } else {
                    error += "类别为空！";
                }
                String productLine = "";
                if (null != String.valueOf(object.get(3)) && !"".equals(String.valueOf(object.get(3)))) {
                    productLine = String.valueOf(object.get(3));
                    companyProduct.setProductLine(productLine);
                } else {
                    error += "产品线为空！";
                }
                String productCategory = "";
                if (null != String.valueOf(object.get(4)) && !"".equals(String.valueOf(object.get(4)))) {
                    productCategory = String.valueOf(object.get(4));
                    companyProduct.setProductCategory(productCategory);
                } else {
                    error += "产品大类为空！";
                }
                if (null != String.valueOf(object.get(5)) && !"".equals(String.valueOf(object.get(5)))) {
                    String productTypeName = String.valueOf(object.get(5));
                    companyProduct.setProductTypeName(productTypeName);
                    SysProductType productType = productTypeMapper.getSysProductTypeByNameAndParentName(companyProduct);
                    if (null == productType) {
                        error += "无 (" + productLine + "-" + productCategory + "-" + productTypeName + ") 产品类别！";
                    } else {
                        companyProduct.setProductTypeId(productType.getProductTypeId());
                    }
                } else {
                    error += "产品小类为空！";
                }
                companyProduct.setProductSpecification(String.valueOf(object.get(6)));

                if (7 < object.size()) {
                    String unitName = String.valueOf(object.get(7));
                    if (null != unitName && !"".equals(unitName)) {
                        companyProduct.setSysUnitName(unitName);
                        SysUnit unit = sysUnitMapper.getMaxAlevelByName(unitName);
                        if (null == unit) {
                            error += "无 (" + unitName + ") 单位！";
                        } else {
                            companyProduct.setSysUnitId(unit.getUnitId());
                        }
                    }
                    if (8 < object.size()) {
                        companyProduct.setNetWeight(String.valueOf(object.get(8)));
                    } else {
                        companyProduct.setNetWeight(null);
                    }
                } else {
                    companyProduct.setSysUnitId(null);
                    companyProduct.setSysUnitName(null);
                    companyProduct.setNetWeight(null);
                }
                companyProduct.setCreateUserId(userId);
                PZsCompanyProduct product = companyProductMapper.getZsCompanyProductByNoAndNameAndSpec(companyProduct);
                if (null != product) {
                    error += "该产品已存在！";
                }
                for (int t = objectIndex - 1; t < lists.size(); t++) {
                    if (null != object.get(0)) {
                        if (object.get(0).equals(lists.get(t).get(0)) && object.get(1).equals(lists.get(t).get(1)) && object.get(6).equals(lists.get(t).get(6))) {
                            error += "与第 " + (t + 2) + " 行,编码、名称和规格相同！";
                        }
                    } else {
                        if (object.get(1).equals(lists.get(t).get(1)) && object.get(6).equals(lists.get(t).get(6))) {
                            error += "与第 " + (t + 2) + " 行,名称和规格数据相同！";
                        }
                    }
                }
            }else {
                error += "数据有误,请检查(删除,请右键删除行)！";
            }
            Json json = new Json();
            if ("".equals(error)) {
                json.setObj(companyProduct);
                json.setSuccess(true);
            } else {
                if (!"".equals(str)) {
                    str += "，";
                }
                str += "<第 " + objectIndex + " 行>--( ";
                str += error;
                str += " )";
                json.setMsg(str);
            }
            jsonList.add(json);
        }
        return jsonList;
    }

    /**
     * 添加
     *
     * @param companyProduct
     * @return
     */
    @Override
    public Boolean addZsCompanyProductList(List<PZsCompanyProduct> companyProduct) {
        Boolean boo = false;
        Integer integer = companyProductMapper.addZsCompanyProductList(companyProduct);
        if (0 != integer) {
            boo = true;
        }
        return boo;
    }

    @Override
    public List<PZsCompanyProduct> getZsCompanyProductList(PZsCompanyProduct companyProduct) {
        return companyProductMapper.getZsCompanyProductBy(companyProduct);
    }

    @Override
    public List<PZsCompanyProduct> getSpecByProductCategory(Long goodsTypeId, Long id) {
        return companyProductMapper.getSpecByProductCategory(goodsTypeId, id);
    }

    @Override
    public List<PZsCompanyProduct> getUnitByProductCategory(Long goodsTypeId, Long id) {
        return companyProductMapper.getUnitByProductCategory(goodsTypeId, id);
    }


}
