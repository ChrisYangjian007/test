package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.SysProductTypeMapper;
import com.dalian.sea.mapper.ZsCompanyProductMapper;
import com.dalian.sea.model.SysProductType;
import com.dalian.sea.model.ZsCompanyProduct;
import com.dalian.sea.parameter.PZsCompanyProduct;
import com.dalian.sea.service.SysProductTypeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */
@Service("SysProductTypeService")
public class SysProductTypeImpl implements SysProductTypeService{

    @Autowired
    private SysProductTypeMapper sysProductTypeMapper;
    @Autowired
    private ZsCompanyProductMapper zsCompanyProductMapper;

    /**
     * 获取所有产品类型
     * @return
     */
    @Override
    public List<SysProductType> getAllSysProductType() {
        return sysProductTypeMapper.getAllSsysProductType();
    }

    /**
     * 获取产品类型
     * @param sysProductType
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<SysProductType> getSysProductTypeByForGridTable(SysProductType sysProductType, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        return sysProductTypeMapper.getSysProductTypeByForGridTable(sysProductType);
    }

    /**
     * 通过id获取产品类型
     * @param id
     * @return
     */
    @Override
    public SysProductType getSysproductTypeById(Long id) {
        return sysProductTypeMapper.getSysproductTypeById(id);
    }

    /**
     * 获取最大顺序数
     * @return
     */
    @Override
    public SysProductType getSysProductTypeMaxListIndex() {
        return sysProductTypeMapper.getSysProductTypeMaxListIndex();
    }

    /**parentId获取产品类型
     * 通过
     * @return
     */
    @Override
    public List<SysProductType> getSysProductTypeByParentId(Long id) {
        return sysProductTypeMapper.getSysProductTypeByParentId(id);
    }

    /**
     * 添加产品类型
     * @param sysProductType
     * @return
     */
    @Override
    public Boolean addSysProductType(SysProductType sysProductType) {
        Boolean boo = false;
        Integer result = sysProductTypeMapper.addSysProductType(sysProductType);
        if(0 < result){
            boo = true ;
        }
        return boo;
    }

    /**
     * 通过parentId和cName获取产品类型
     * @param sysProductType
     * @return
     */
    @Override
    public SysProductType getSysProductTypeByParentIdAndCName(SysProductType sysProductType) {
        return sysProductTypeMapper.getSysProductTypeByParentIdAndCName(sysProductType);
    }

    /**
     * 修改产品类型
     * @param sysProductType
     * @return
     */
    @Override
    public Boolean updateSysProductTypeById(SysProductType sysProductType) {
        Boolean boo = false ;
        Integer result = sysProductTypeMapper.updateSysProductTypeById(sysProductType);
        if(0 < result){
            boo = true ;
        }
        return boo;
    }

    /**
     * 修改产品类型
     * @param sysProductType
     * @return
     */
    @Override
    public Boolean updateSysProductType(SysProductType sysProductType) {
        Boolean boo = false ;
        Integer result = sysProductTypeMapper.updateSysProductType(sysProductType);
        if(0<result){
            boo = true ;
        }
        return boo;
    }

    /**
     * 根据aLevel获取产品类型
     * @param aLevel
     * @return
     */
    @Override
    public List<SysProductType> getSysProductTypeByALevel(Integer aLevel) {
        return sysProductTypeMapper.getSysProductTypeByALevel(aLevel);
    }

    /**
     * 删除产品类型
     * @param id
     * @return
     */
    @Override
    public Boolean deleteProductTypeById(Long id) {
        Boolean boo = false;
        Integer result = sysProductTypeMapper.deleteSysProductTypeById(id);
        if(0<result){
            boo = true;
        }
        return boo;
    }

    /**
     * 通过首级名称、父级名称和自身名称获取
     * @param companyProduct
     * @return
     */
    @Override
    public SysProductType getSysProductTypeByNameAndParentName(PZsCompanyProduct companyProduct) {
        return sysProductTypeMapper.getSysProductTypeByNameAndParentName(companyProduct);
    }

    /**
     * 根据productTypeId获取自己和下级的全部产品类型
     * @param id
     * @return
     */
    @Override
    public List<SysProductType> getAllSysProductTypeById(Long id) {
        return sysProductTypeMapper.getAllSysProductTypeById(id);
    }

    /**
     * 批量删除
     * @param productTypeList
     * @return
     */
    @Override
    public Boolean deleteProductTypeByList(List<SysProductType> productTypeList) {
        Boolean boo = false;
        Integer result = sysProductTypeMapper.deleteProductTypeByList(productTypeList);
        if(0<result){
            boo = true ;
        }
        return boo;
    }
}
