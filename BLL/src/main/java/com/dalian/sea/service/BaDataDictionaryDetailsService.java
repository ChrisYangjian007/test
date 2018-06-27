package com.dalian.sea.service;

import com.dalian.sea.model.BaDataDictionaryDetails;
import com.dalian.sea.parameter.PBaDataDictionaryDetails;

import java.util.List;

/**
 * BaDataDictionaryDetailsService
 *
 * @author xintao
 * @date 2018/1/18
 */
public interface BaDataDictionaryDetailsService {


    /**
     * 根据数据字典编码获取
     * @param code
     * @return
     */
    List<PBaDataDictionaryDetails> getBaDataDictionaryDetailsByCode(String code);

    /**
     * 根据数据字典ID获取
     * @param dataDictionaryId
     * @param page
     * @param rows
     * @return
     */
    List<PBaDataDictionaryDetails> getBaDataDictionaryDetailsByDataDictionaryId(Long dataDictionaryId, int page, int rows);


    /**
     * 获取数据字典详情表最大顺序
     * @return
     */
    BaDataDictionaryDetails getBaDataDictionaryDetailsMaxListIndex();

    /**
     * 根据名字和数据字典id获取详情
     * @param baDataDictionaryDetails
     * @return
     */
    BaDataDictionaryDetails getBaDataDictionaryDetailsByNameAndDataDictionaryId(BaDataDictionaryDetails baDataDictionaryDetails);

    /**
     * 添加数据字典详情
     * @param baDataDictionaryDetails
     * @return
     */
    Boolean addBaDataDictionaryDetails(BaDataDictionaryDetails baDataDictionaryDetails);

    /**
     * 通过id获取数据字典详情
     * @param id
     * @return
     */
    BaDataDictionaryDetails getBaDataDictionaryDetailsById(Long id);

    /**
     * 修改数据字典详情
     * @param baDataDictionaryDetails
     * @return
     */
    Boolean updateBaDataDictionaryDetailsById(BaDataDictionaryDetails baDataDictionaryDetails);


    /**
     * 删除
     * @param baDataDictionaryDetails
     * @return
     */
    Boolean deleteBaDataDictionaryDetailsById(BaDataDictionaryDetails baDataDictionaryDetails);

    /**
     * 通过dataDictionaryId删除
     * @param id
     * @return
     */
    Boolean deleteBaDataDictionaryDetailsByDictionaryId(Long id);

    /**
     *  根据字典名称和详情名称获取详情
     * @param dataName
     * @param detailsName
     * @return
     */
    BaDataDictionaryDetails getBaDataDictionaryDetailsByDataNameAndDataDetailsName(String dataName,String detailsName);

    /**
     * 根据数据字典code和c_name获取数据字典详情
     * @param code
     * @param name
     * @return
     */
    BaDataDictionaryDetails getDataDictionaryDetailsByDictionaryCodeAndName(String code ,String name);

    /**
     * 根据数据字典ID获取详情
     */
    List<PBaDataDictionaryDetails> getBaDataDictionaryDetailsByDataDictionaryIdWithOutPage(Long dataDictionaryId);
}
