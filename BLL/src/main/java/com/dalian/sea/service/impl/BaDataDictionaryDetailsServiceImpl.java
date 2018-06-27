package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BaDataDictionaryDetailsMapper;
import com.dalian.sea.model.BaDataDictionary;
import com.dalian.sea.model.BaDataDictionaryDetails;
import com.dalian.sea.parameter.PBaDataDictionaryDetails;
import com.dalian.sea.service.BaDataDictionaryDetailsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BaDataDictionaryDetailsServiceImpl
 *
 * @author xintao
 * @date 2018/1/18
 */
@Service("BaDataDictionaryDetailsService")
public class BaDataDictionaryDetailsServiceImpl implements BaDataDictionaryDetailsService {

    @Autowired
    private BaDataDictionaryDetailsMapper baDataDictionaryDetailsMapper;


    /**
     * 根据数据字典编码获取
     * @param code
     * @return
     */
    @Override
    public List<PBaDataDictionaryDetails> getBaDataDictionaryDetailsByCode(String code) {
        return baDataDictionaryDetailsMapper.getBaDataDictionaryDetailsByCode(code);
    }

    /**
     * 根据数据字典ID获取
     * @param dataDictionaryId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PBaDataDictionaryDetails> getBaDataDictionaryDetailsByDataDictionaryId(Long dataDictionaryId, int page, int rows) {
        PageHelper.startPage(page,rows);
        return baDataDictionaryDetailsMapper.getBaDataDictionaryDetailsByDataDictionaryId(dataDictionaryId);
    }

    /**
     * 获取数据字典详情表最大顺序
     * @return
     */
    @Override
    public BaDataDictionaryDetails getBaDataDictionaryDetailsMaxListIndex() {
        return baDataDictionaryDetailsMapper.getBaDataDictionaryDetailsMaxListIndex();
    }

    /**
     *根据名字和数据字典id获取详情
     * @param baDataDictionaryDetails
     * @return
     */
    @Override
    public BaDataDictionaryDetails getBaDataDictionaryDetailsByNameAndDataDictionaryId(BaDataDictionaryDetails baDataDictionaryDetails) {
        return baDataDictionaryDetailsMapper.getBaDataDictionaryDetailsByNameAndDataDictionaryId(baDataDictionaryDetails);
    }

    /**
     * 添加数据字典详情
     * @param baDataDictionaryDetails
     * @return
     */
    @Override
    public Boolean addBaDataDictionaryDetails(BaDataDictionaryDetails baDataDictionaryDetails) {
        Boolean boo = false;
        Integer result = baDataDictionaryDetailsMapper.addBaDataDictionaryDetails(baDataDictionaryDetails);
        if(0<result){
            boo = true;
        }
        return boo;
    }

    /**
     * 通过id获取数据字典详情
     * @param id
     * @return
     */
    @Override
    public BaDataDictionaryDetails getBaDataDictionaryDetailsById(Long id) {
        return baDataDictionaryDetailsMapper.getBaDataDictionaryDetailsById(id);
    }

    /**
     * 修改数据字典详情
     * @param baDataDictionaryDetails
     * @return
     */
    @Override
    public Boolean updateBaDataDictionaryDetailsById(BaDataDictionaryDetails baDataDictionaryDetails) {

        Boolean boo = false;
        Integer result = baDataDictionaryDetailsMapper.updateBaDataDictionaryDetailsById(baDataDictionaryDetails);
        if(0 < result){
            boo = true ;
        }
        return boo;
    }

    /**
     * 删除
     * @param baDataDictionaryDetails
     * @return
     */
    @Override
    public Boolean deleteBaDataDictionaryDetailsById(BaDataDictionaryDetails baDataDictionaryDetails) {
        Boolean boo = false;
        Integer result = baDataDictionaryDetailsMapper.deleteBaDataDictionaryDetailsById(baDataDictionaryDetails);
        if(0 < result){
            boo = true ;
        }
        return boo;
    }

    /**
     *通过dataDictionaryId删除
     * @param id
     * @return
     */
    @Override
    public Boolean deleteBaDataDictionaryDetailsByDictionaryId(Long id) {
        Boolean boo = false;
        Integer result = baDataDictionaryDetailsMapper.deleteBaDataDictionaryDetailsByDictionaryId(id);
        if (0 < result) {
            boo = true;
        }

        return boo;
    }

    /**
     *  根据字典名称和详情名称获取详情
     * @param dataName
     * @param detailsName
     * @return
     */
    @Override
    public BaDataDictionaryDetails getBaDataDictionaryDetailsByDataNameAndDataDetailsName(String dataName, String detailsName) {
        return baDataDictionaryDetailsMapper.getBaDataDictionaryDetailsByDataNameAndDataDetailsName(dataName,detailsName);
    }

    /**
     * 根据数据字典code和c_name获取数据字典详情
     * @param code
     * @param name
     * @return
     */
    @Override
    public BaDataDictionaryDetails getDataDictionaryDetailsByDictionaryCodeAndName(String code, String name) {
        return baDataDictionaryDetailsMapper.getDataDictionaryDetailsByDictionaryCodeAndName(code,name);
    }

    @Override
    public List<PBaDataDictionaryDetails> getBaDataDictionaryDetailsByDataDictionaryIdWithOutPage(Long dataDictionaryId) {
        return baDataDictionaryDetailsMapper.getBaDataDictionaryDetailsByDataDictionaryId(dataDictionaryId);
    }
}
