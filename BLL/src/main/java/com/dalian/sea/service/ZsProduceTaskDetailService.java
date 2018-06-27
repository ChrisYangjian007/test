package com.dalian.sea.service;

import com.dalian.sea.parameter.PZsProduceTaskDetail;

import java.util.List;

/**
 * Created by 陈逸文 on 2018/3/22.
 */
public interface ZsProduceTaskDetailService {
    /**
     * 根据生产任务编号查询
     *
     * @param pZsProduceTaskDetail
     * @return
     */
    List<PZsProduceTaskDetail> getProduceTaskDetailByProduceTaskNo(PZsProduceTaskDetail pZsProduceTaskDetail);

    /**
     * 根据批次号查询
     *
     * @param pZsProduceTaskDetail
     * @return
     */
    List<PZsProduceTaskDetail> getProduceTaskDetailByBatchNo(PZsProduceTaskDetail pZsProduceTaskDetail);

    /**
     * 根据生产任务Id查询
     *
     * @param produceTaskId
     * @param userId
     * @param companyId
     * @return
     */
    List<PZsProduceTaskDetail> getProduceTaskDetailByProduceTaskId(Long produceTaskId, Long userId, Long companyId);

    /**
     * 根据生产任务Id查询是否有产品
     *
     * @param produceTaskId
     * @return
     */
    Boolean TaskDetailByProduceTaskId(Long produceTaskId);
}
