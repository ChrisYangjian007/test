package com.dalian.sea.service;

import com.dalian.sea.model.BaDataDictionary;

import java.util.List;

/**
 * BaDataDictionaryService
 *
 * @author xintao
 * @date 2018/1/17
 */
public interface BaDataDictionaryService {

    /**
     * 获取所有
     * @return
     */
    List<BaDataDictionary> getAllBaDataDictionary();

    /**
     * 根据父级ID获取
     * @param pId
     * @param page
     * @param rows
     * @return
     */
    List<BaDataDictionary> getBaDataDictionaryByPId(Long pId,int page,int rows);

    /**
     * 根据父级ID和名称获取
     * @param baDataDictionary
     * @return
     */
    BaDataDictionary getBaDataDictionaryByPIdAndName(BaDataDictionary baDataDictionary);

    /**
     * 根据ID获取
     * @param dataDictionaryId
     * @return
     */
    BaDataDictionary getBaDataDictionaryById(Long dataDictionaryId);

    /**
     * 添加字典目录
     * @param baDataDictionary
     * @return
     */
    Long addBaDataDictionary(BaDataDictionary baDataDictionary);

    /**
     * 修改字典目录
     * @param baDataDictionary
     * @return
     */
    Boolean updateBaDataDictionary(BaDataDictionary baDataDictionary);

    /**
     * 修改字典部分字段目录
     * @param baDataDictionary
     * @return
     */
    Boolean updateBaDataDictionaryById(BaDataDictionary baDataDictionary);

    /**
     * 删除字典目录
     * @param baDataDictionary
     * @return
     */
    Boolean deleteBaDataDictionary(BaDataDictionary baDataDictionary);

    /**
     * 获取最大顺序数
     * @return
     */
    BaDataDictionary getBaDataDictionaryMaxListIndex();


    /**
     * 根据编号获取数据字典
     * @param code
     * @return
     */
    BaDataDictionary getDataDictionaryByCode(String code);

    /**
     * 根据父级code和c_name获取数据字典
     * @param code
     * @param name
     * @return
     */
    BaDataDictionary getDataDictionaryByPCodeAndName(String code,String name);
}
