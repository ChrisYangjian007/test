package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsWorkProcess;
import com.dalian.sea.parameter.PWorkProcessPDA;
import com.dalian.sea.parameter.PZsWorkProcess;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsWorkProcessMapper extends Mapper<PZsWorkProcess> {

    /**
     * 根据ID获取
     * @param workProcessId
     * @return
     */
    PWorkProcessPDA getWorkProcessById(Long workProcessId);

    /**
     * 根据workFlowID和名称获取
     * @param workProcess
     * @return
     */
    PZsWorkProcess getWorkProcessByWorkFlowIdAndName(PZsWorkProcess workProcess);

    /**
     * 根据ID获取
     * @param workProcessId
     * @return
     */
    PZsWorkProcess getZsWorkProcessByWorkProcessId(Long workProcessId);

    /**
     * 根据工艺ID获取
     * @param workFlowId
     * @return
     */
    List<PZsWorkProcess> getZsWorkProcessByWorkFlowId(Long workFlowId);

    /**
     * 根据工艺ID获取
     * @param workFlowId
     * @return
     */
    List<PWorkProcessPDA> getZsWorkProcessByWorkFlowIdForPDA(Long workFlowId);

    /**
     * 根据生产任务获取工序
     * @param id
     * @return
     */
    List<PZsWorkProcess> getWorkProcessByProduceTask(Long id);

    /**
     * 获取生产任务的工序及工序详情
     * @param id
     * @return
     */
    List<PZsWorkProcess> getWorkProcessFromAttributeValueAndByProduceTask(Long id);

    /**
     * 添加
     * @param workProcess
     * @return
     */
    Integer addWorkProcess(PZsWorkProcess workProcess);

    /**
     * 修改
     * @param workProcess
     * @return
     */
    Integer updateWorkProcess(PZsWorkProcess workProcess);


    /**
     * 获取
     * @return
     */
    List<PZsWorkProcess> getZsWorkProcessAll();

    /**
     * 通过id删除工序
     * @param zsWorkProcess
     * @return
     */
    Integer deleteWorkProcessById(ZsWorkProcess zsWorkProcess);

    /**
     *通过workFlowId获取工序
     * @param workFlowId
     * @return
     */
    List<ZsWorkProcess> getWorkProcessByWorkFlowId(Long workFlowId);

    /**
     *通过workFlowId获取工序（增加工序Tittle）
     * @param workFlowId
     * @return
     */
    List<ZsWorkProcess> getWorkProcessByWorkFlowIdForStock(Long workFlowId);

    /**
     * 通过workFlowId获取工艺和value
     * @param workFlowId
     * @return
     */
    List<PZsWorkProcess> getZsWorkProcessAndValueByWorkFlowId(Long workFlowId);

    /**
     *通过produceTaskId获取工序和工序字段
     * @param produceTaskId
     * @return
     */
    List<PZsWorkProcess> getWorkProcessAndAllPFormAttributeByProduceTaskId(Long produceTaskId);

    /**
     * 通过produceTaskId获取工序和工序字段对应值
     * @param produceTaskId
     * @return
     */
    List<PZsWorkProcess> getPWorkProcessAndPFromAttributeValueAndByProduceTask(Long produceTaskId);

    /**
     * 通过produceTaskId获取所有工序和工序字段对应值
     * @param produceTaskId
     * @return
     */
    List<PZsWorkProcess> getAllPWorkProcessAndPFromAttributeValueAndByProduceTask(Long produceTaskId);

    /**
     * 增加工序：获取工艺信息
     * @return
     */
    List<PZsWorkProcess> getAllWorkProcessByFormAttribute();

    /**
     *获取工序以及对应handleType最大value
     * @param produceTaskId
     * @return
     */
    List<PZsWorkProcess> getZsWorkProcessAndMaxHandleTypeValueByProduceTaskId(Long produceTaskId);

    /**
     * 通过生产任务编号获取未删除并且有字段的工序
     * @param produceTaskId
     * @return
     */
    List<ZsWorkProcess> getNotDeleteAndHaveFieldsWorkProcessByProduceTaskId(Long produceTaskId);

    /**
     * 通过生产任务编号获取全部并且有字段的工序
     * @param produceTaskId
     * @return
     */
    List<ZsWorkProcess> getAllAndHaveFieldsWorkProcessByProduceTaskId(Long produceTaskId);
}