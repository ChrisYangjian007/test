package com.dalian.sea.mapper;

import com.dalian.sea.model.BaFormAttributeValue;
import com.dalian.sea.parameter.PBaFormAttributeValue;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaFormAttributeValueMapper extends Mapper<BaFormAttributeValue> {

    /**
     * 添加
     * @param baFormAttributeValue
     * @return
     */
    Integer addBaFormAttributeValue(PBaFormAttributeValue baFormAttributeValue);

    /**
     * 修改
     * @param baFormAttributeValue
     * @return
     */
    Integer updateBaFormAttributeValue(PBaFormAttributeValue baFormAttributeValue);

    /**
     *通过生产任务id获取所有审核数据
     * @param produceTaskId
     * @param handleType
     * @return
     */
    List<PBaFormAttributeValue> getBaFormAttributeValueByWorkProcessIdForPDA(@Param("produceTaskId") Long produceTaskId,@Param("handleType") int handleType);

    /**
     *通过工序id获取
     * @param workProcessId
     * @return
     */
    List<PBaFormAttributeValue> getBaFormAttributeValueByWorkProcessId(Long workProcessId);

    /**
     * 获取根据生产任务、工序和类型
     * @param produceTaskId
     * @param workProcessId
     * @param handleType
     * @return
     */
    PBaFormAttributeValue getBaFormAttributeValueByTaskAndProcessAndType(@Param("produceTaskId") Long produceTaskId,@Param("workProcessId") Long workProcessId,@Param("handleType") int handleType);

    /**
     * 获取根据生产任务、工序和类型
     * @param attributeValue
     * @return
     */
    PBaFormAttributeValue getBaFormAttributeValueByTaskAndProcessAndTypeAndIndex(PBaFormAttributeValue attributeValue);


    /**
     * 通过produceTaskId获取value
     * @param produceTaskId
     * @return
     */
    List<PBaFormAttributeValue> getFormAttributeValueByProduceTaskId(Long produceTaskId);

    /**
     * 通过produceTaskId、Type获取value
     * @param produceTaskId
     * @return
     */
    List<PBaFormAttributeValue> getBaFormAttributeValueByTaskAndType(@Param("produceTaskId") Long produceTaskId,@Param("handleType") int handleType);

    /**
     * 批量修改value
     * @param formAttributeValueList
     * @return
     */
    Integer updateFormAttributeValue(@Param("formAttributeValueList")List<PBaFormAttributeValue> formAttributeValueList);

    /**
     * 批量添加
     * @param addFormAttributeValueList
     * @return
     */
    Integer addBaFormAttributeValueList(@Param("addFormAttributeValueList")List<PBaFormAttributeValue> addFormAttributeValueList);
}