package com.dalian.sea.service;

import com.dalian.sea.model.BaFormAttributeValue;

import java.util.List;

import com.dalian.sea.parameter.PBaFormAttributeValue;

/**
 * BaFormAttributeValueService
 *
 * @author TONE
 * @date 2018/2/8
 */
public interface BaFormAttributeValueService {

    /**
     * 通过工序id获取
     * @param workProcessId
     * @return
     */
    List<PBaFormAttributeValue> getBaFormAttributeValueByWorkProcessId(Long workProcessId);

    /**
     *通过生产任务id获取所有审核数据
     * @param produceTaskId
     * @param handleType
     * @return
     */
    List<PBaFormAttributeValue> getBaFormAttributeValueByWorkProcessIdForPDA(Long produceTaskId,int handleType);

    /**
     * 添加
     * @param baFormAttributeValue
     * @return
     */
    Long addBaFormAttributeValue(PBaFormAttributeValue baFormAttributeValue,String name);

    /**
     * 添加或修改
     * @param auditor
     * @return
     */
    Boolean addOrUpdateBaFormAttributeValue(PBaFormAttributeValue operation,PBaFormAttributeValue auditor,String name);

    /**
     * 获取根据生产任务、工序和类型
     * @param produceTaskId
     * @param workProcessId
     * @param handleType
     * @return
     */
    PBaFormAttributeValue getBaFormAttributeValueByTaskAndProcessAndType(Long produceTaskId,Long workProcessId,int handleType);

    /**
     * 通过produceTaskId获取value
     * @param produceTaskId
     * @return
     */
    List<PBaFormAttributeValue> getFormAttributeValueByProduceTaskId(Long produceTaskId);
    /**
     * 通过produceTaskId获取value
     * @param produceTaskId
     * @return
     */
    List<PBaFormAttributeValue> getFormAttributeValueByProduceTaskIdForPDA(Long produceTaskId,Integer page,Integer rows);

    /**
     * 通过produceTaskId、Type获取value
     * @param produceTaskId
     * @return
     */
    List<PBaFormAttributeValue> getBaFormAttributeValueByTaskAndType(Long produceTaskId,int handleType);

    /**
     * 获取根据生产任务、工序和类型
     * @param attributeValue
     * @return
     */
    PBaFormAttributeValue getBaFormAttributeValueByTaskAndProcessAndTypeAndIndex(PBaFormAttributeValue attributeValue);


    /**
     * 批量修改value
     * @param formAttributeValueList
     * @return
     */
    Boolean updateFormAttributeValue(List<PBaFormAttributeValue> formAttributeValueList);

    /**
     * 编辑工序详情
     * @param addFormAttributeValueList
     * @param updFormAttributeValueList
     * @return
     */
    Boolean addAndUpdateBaFormAttributeValue(List<PBaFormAttributeValue> addFormAttributeValueList,List<PBaFormAttributeValue> updFormAttributeValueList);
}
