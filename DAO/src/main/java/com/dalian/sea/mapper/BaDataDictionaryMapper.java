package com.dalian.sea.mapper;

import com.dalian.sea.model.BaDataDictionary;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaDataDictionaryMapper extends Mapper<BaDataDictionary> {
    /**
     * 获取所有
     * @return
     */
    List<BaDataDictionary> getAllBaDataDictionary();

    /**
     * 根据父级ID获取
     * @param pId
     * @return
     */
    List<BaDataDictionary> getBaDataDictionaryByPId(Long pId);

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
    Integer addBaDataDictionary(BaDataDictionary baDataDictionary);

    /**
     * 修改字典目录
     * @param baDataDictionary
     * @return
     */
    Integer updateBaDataDictionary(BaDataDictionary baDataDictionary);

    /**
     * 修改字典目录部分字段
     * @param baDataDictionary
     * @return
     */
    Integer updateBaDataDictionaryById(BaDataDictionary baDataDictionary);

    /**
     * 删除字典目录
     * @param baDataDictionary
     * @return
     */
    Integer deleteBaDataDictionary(BaDataDictionary baDataDictionary);

    /**
     * 获取最大顺序数
     * @return
     */
    BaDataDictionary getBaDataDictionaryMaxListIndex();

    /**
     *根据编号获取数据字典
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
    BaDataDictionary getDataDictionaryByPCodeAndName(@Param("code")String code, @Param("name")String name);
}