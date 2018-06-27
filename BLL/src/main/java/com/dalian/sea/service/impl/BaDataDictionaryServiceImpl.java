package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BaDataDictionaryDetailsMapper;
import com.dalian.sea.mapper.BaDataDictionaryMapper;
import com.dalian.sea.model.BaDataDictionary;
import com.dalian.sea.model.BaDataDictionaryDetails;
import com.dalian.sea.service.BaDataDictionaryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * BaDataDictionaryServiceImpl
 *
 * @author xintao
 * @date 2018/1/17
 */
@Service("BaDataDictionaryService")
public class BaDataDictionaryServiceImpl implements BaDataDictionaryService {
    @Autowired
    private BaDataDictionaryMapper baDataDictionaryMapper;
    @Autowired
    private BaDataDictionaryDetailsMapper baDataDictionaryDetailsMapper;

    /**
     * 获取所有
     * @return
     */
    @Override
    public List<BaDataDictionary> getAllBaDataDictionary() {
        return baDataDictionaryMapper.getAllBaDataDictionary();
    }

    /**
     * 根据父级ID获取
     * @param pId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<BaDataDictionary> getBaDataDictionaryByPId(Long pId,int page,int rows) {
        PageHelper.startPage(page,rows);
        return baDataDictionaryMapper.getBaDataDictionaryByPId(pId);
    }

    /**
     * 根据父级ID和名称获取
     * @param baDataDictionary
     * @return
     */
    @Override
    public BaDataDictionary getBaDataDictionaryByPIdAndName(BaDataDictionary baDataDictionary) {
        return baDataDictionaryMapper.getBaDataDictionaryByPIdAndName(baDataDictionary);
    }

    /**
     * 根据ID获取
     * @param dataDictionaryId
     * @return
     */
    @Override
    public BaDataDictionary getBaDataDictionaryById(Long dataDictionaryId) {
        return baDataDictionaryMapper.getBaDataDictionaryById(dataDictionaryId);
    }

    /**
     * 添加字典目录
     * @param baDataDictionary
     * @return
     */
    @Override
    public Long addBaDataDictionary(BaDataDictionary baDataDictionary) {
        Long id = null;
        Integer integer = baDataDictionaryMapper.addBaDataDictionary(baDataDictionary);
        if (0!=integer){
            id = baDataDictionary.getDataDictionaryId();
        }
        return id;
    }

    /**
     * 修改字典目录
     * @param baDataDictionary
     * @return
     */
    @Override
    public Boolean updateBaDataDictionary(BaDataDictionary baDataDictionary) {
        Boolean boo = false;
        Integer integer = baDataDictionaryMapper.updateBaDataDictionary(baDataDictionary);
        if (0!=integer){
            boo = true;
        }
        return boo;
    }

    /**
     * 修改字典部分字段目录
     * @param baDataDictionary
     * @return
     */
    @Override
    public Boolean updateBaDataDictionaryById(BaDataDictionary baDataDictionary) {
        Boolean boo = false;
        Integer integer = baDataDictionaryMapper.updateBaDataDictionaryById(baDataDictionary);
        if (0!=integer){
            boo = true;
        }
        return boo;
    }

    /**
     * 删除字典目录
     * @param baDataDictionary
     * @return
     */
    @Override
    @Transactional
    public Boolean deleteBaDataDictionary(BaDataDictionary baDataDictionary) {
        Boolean boo = false;
        try {
            Integer integer = baDataDictionaryMapper.deleteBaDataDictionary(baDataDictionary);
            if (0!=integer){
                baDataDictionaryDetailsMapper.deleteBaDataDictionaryDetailsByDictionaryId(baDataDictionary.getDataDictionaryId());
                boo = true;
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

    /**
     * 获取最大顺序数
     * @return
     */
    @Override
    public BaDataDictionary getBaDataDictionaryMaxListIndex() {
        return baDataDictionaryMapper.getBaDataDictionaryMaxListIndex();
    }

    /**
     * 根据编号获取数据字典
     * @param code
     * @return
     */
    @Override
    public BaDataDictionary getDataDictionaryByCode(String code) {
        return baDataDictionaryMapper.getDataDictionaryByCode(code);
    }

    /**
     * 根据父级code和c_name获取数据字典
     * @param code
     * @param name
     * @return
     */
    @Override
    public BaDataDictionary getDataDictionaryByPCodeAndName(String code, String name) {
        return baDataDictionaryMapper.getDataDictionaryByPCodeAndName(code,name);
    }

}
