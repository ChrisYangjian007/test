package com.dalian.sea.mapper;

import com.dalian.sea.model.BaFormAttribute;
import com.dalian.sea.model.ZsWorkProcess;
import com.dalian.sea.parameter.PBaFormAttribute;
import com.dalian.sea.parameter.PZsWorkProcess;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaFormAttributeMapper extends Mapper<PBaFormAttribute> {

    /**
     * 添加List
     * @param formAttributeList
     * @param workProcessId
     * @param userId
     * @return
     */
    Integer addBaFormAttributeList(@Param("formAttributeList") List<PBaFormAttribute> formAttributeList,@Param("workProcessId") Long workProcessId,@Param("userId") Long userId);

    /**
     * 修改List
     * @param formAttributeList
     * @param userId
     * @return
     */
    Integer updateBaFormAttributeList(@Param("formAttributeList") List<PBaFormAttribute> formAttributeList,@Param("userId") Long userId);

    /**
     * 修改List
     * @param formAttributeList
     * @param userId
     * @return
     */
    Integer deleteBaFormAttributeList(@Param("formAttributeList") List<PBaFormAttribute> formAttributeList,@Param("workProcessId") Long workProcessId,@Param("userId") Long userId);

    /**
     * 根据工艺详情ID和类别获取
     * @param workProcessId
     * @param handleType
     * @return
     */
    List<PBaFormAttribute> getBaFormAttributeListByWorkProcessIdAndHandleType(@Param("workProcessId") Long workProcessId,@Param("handleType") Integer handleType);

    /**
     * 根据工艺id获取
     * @param workProcessId
     * @return
     */
    List<PBaFormAttribute> getBaFormAttributeListByWorkProcessId(Long workProcessId);

    /**
     * 通过workProcessId删除
     * @param zsWorkProcess
     * @return
     */
    Integer deleteFormAttributeByWorkProcessId(ZsWorkProcess zsWorkProcess);

    /**
     *获取生产任务下所有工艺的所有字段
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