package com.dalian.sea.mapper;

import com.dalian.sea.model.BaDataDictionaryDetails;
import com.dalian.sea.parameter.PBaDataDictionaryDetails;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaDataDictionaryDetailsMapper extends Mapper<BaDataDictionaryDetails> {

    /**
     * 根据数据字典编码获取
     * @param code
     * @return
     */
    List<PBaDataDictionaryDetails> getBaDataDictionaryDetailsByCode(String code);


    /**
     * 根据数据字典ID获取
     * @param dataDictionaryId
     * @return
     */
    List<PBaDataDictionaryDetails> getBaDataDictionaryDetailsByDataDictionaryId(Long dataDictionaryId);

    /**
     * 获取数据字典详情最大顺序
     * @return
     */
    BaDataDictionaryDetails getBaDataDictionaryDetailsMaxListIndex();

    /**
     *根据名字和数据字典id获取详情
     * @param baDataDictionaryDetails
     * @return
     */
    BaDataDictionaryDetails getBaDataDictionaryDetailsByNameAndDataDictionaryId(BaDataDictionaryDetails baDataDictionaryDetails);

    /**
     * 添加数据字典详情
     * @param baDataDictionaryDetails
     * @return
     */
    Integer addBaDataDictionaryDetails(BaDataDictionaryDetails baDataDictionaryDetails);

    /**
     * 修改数据字典详情
     * @param baDataDictionaryDetails
     * @return
     */
    Integer updateBaDataDictionaryDetails(BaDataDictionaryDetails baDataDictionaryDetails);

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
    Integer updateBaDataDictionaryDetailsById(BaDataDictionaryDetails baDataDictionaryDetails);

    /**
     * 删除
     * @param baDataDictionaryDetails
     * @return
     */
    Integer deleteBaDataDictionaryDetailsById(BaDataDictionaryDetails baDataDictionaryDetails);

    /**
     * 通过dataDictionaryId删除
     * @param id
     * @return
     */
    Integer deleteBaDataDictionaryDetailsByDictionaryId(Long id);

    /**
     *  根据字典名称和详情名称获取详情
     * @param dataName
     * @param detailsName
     * @return
     */
    BaDataDictionaryDetails getBaDataDictionaryDetailsByDataNameAndDataDetailsName(@Param("dataName") String dataName,@Param("detailsName") String detailsName);

    /**
     * 根据数据字典code和c_name获取数据字典详情
     * @param code
     * @param name
     * @return
     */
    BaDataDictionaryDetails getDataDictionaryDetailsByDictionaryCodeAndName(@Param("code")String code, @Param("name")String name);
}