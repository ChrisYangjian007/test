package com.dalian.sea.service;

/**
 * Created by Administrator on 2018/3/5.
 */
public interface ZsTaskDetailValueService {

    /**
     * 通过produceTaskId
     * 查workFlowId
     *
     * @param produceTaskId
     * @return
     */
    Long getWorkFlowId(Long produceTaskId);
}
