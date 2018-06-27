package com.dalian.sea.service;

import com.dalian.sea.model.BaFormAttribute;
import com.dalian.sea.parameter.PBaFormAttribute;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * BaFormAttributeService
 *
 * @author TONE
 * @date 2018/2/8
 */
public interface BaFormAttributeService {

    /**
     * 根据工艺详情ID和类别获取
     * @param workProcessId
     * @param handleType
     * @return
     */
    List<PBaFormAttribute> getBaFormAttributeListByWorkProcessIdAndHandleType(Long workProcessId,Integer handleType);

    /**
     * 根据工艺id获取
     * @param workProcessId
     * @return
     */
    List<PBaFormAttribute> getBaFormAttributeListByWorkProcessId(Long workProcessId);

    /**
     * 获取生产任务下所有工艺的所有字段
     * @param produceTaskId
     * @return
     */
    List<PBaFormAttribute> getFormAttributeByProduceTaskId(Long produceTaskId);

    /**
     * 获取该工序下的默认当前用户的操作人、审核人、巡检人
     * @param workProcessId
     * @return
     */
    List<PBaFormAttribute> getDefaultFormAttributeByWorkProcessIdAndName(Long workProcessId);
}
