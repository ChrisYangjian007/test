package com.dalian.sea.service;

import com.dalian.sea.model.ZsWorkFlow;
import com.dalian.sea.parameter.PZsWorkFlow;

import java.util.List;

/**
 * Created by TONE on 2018/2/8.
 */
public interface ZsWorkFlowService {

    /**
     * 根据父级id获取工艺流程
     * @param parentId
     * @return
     */
    List<ZsWorkFlow> getWorkFlowByParentId(Long parentId);

    /**
     *
     * 是否有工艺详情
     * @param workFlowId
     * @return
     */
    List<ZsWorkFlow> getProduceTaskByWorkFlow(Long workFlowId);

    /**
     * 根据id获取工艺流程
     * @param id
     * @return
     */
    PZsWorkFlow getWorkFlowById(Long id);

    /**
     * 获取最大顺序
     * @return
     */
    ZsWorkFlow getWorkFlowMaxListIndex();

    /**
     * 添加工艺流程
     * @param zsWorkFlow
     * @return
     */
    Long addZsWorkFlow(ZsWorkFlow zsWorkFlow);

    /**
     * 修改工艺流程
     * @param zsWorkFlow
     * @return
     */
    Boolean updateWorkFlow(ZsWorkFlow zsWorkFlow);

    /**
     * 获取所有工艺流程
     * @return
     */
    List<ZsWorkFlow> getAllWorkFlow();

    /**
     * 通过parentId和cName获取工艺流程
     * @param zsWorkFlow
     * @return
     */
    PZsWorkFlow getWorkFlowByParentIdAndName(ZsWorkFlow zsWorkFlow);

    /**
     * 删除
     * @param zsWorkFlow
     * @return
     */
    Boolean deleteWorkFlow(ZsWorkFlow zsWorkFlow);

    /**
     * 通过produceTaskId获取工艺流程
     * @param produceTaskId
     * @return
     */
    ZsWorkFlow getWorkFlowByProduceTaskId(Long produceTaskId);

    /**
     * 通过id获取工艺名称
     * @param workFlowId
     * @return
     */
    String getWorkFlowNameById(Long workFlowId);
}
