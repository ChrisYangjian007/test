package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsWorkFlow;
import com.dalian.sea.parameter.PZsWorkFlow;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsWorkFlowMapper extends Mapper<ZsWorkFlow> {

    /**
     * 根据父级id获取工艺流程
     * @param parnetId
     * @return
     */
    List<ZsWorkFlow> getWorkFlowByParentId(Long parnetId);

    /**
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
     *添加工艺流程
     * @param zsWorkFlow
     * @return
     */
    Integer addZsWorkFlow(ZsWorkFlow zsWorkFlow);

    /**
     * 修改工艺流程
     * @param zsWorkFlow
     * @return
     */
    Integer updateWorkFlow(ZsWorkFlow zsWorkFlow);

    /**
     * 获取所有工艺流程
     * @return
     */
    List<ZsWorkFlow> getAllWorkFlow();

    /**
     *通过parentId和cName获取工艺流程
     * @param zsWorkFlow
     * @return
     */
    PZsWorkFlow getWorkFlowByParentIdAndName(ZsWorkFlow zsWorkFlow);

    /**
     * 删除
     * @param zsWorkFlow
     * @return
     */
    Integer deleteWorkFlow(ZsWorkFlow zsWorkFlow);

    /**
     *通过produceTaskId获取工艺流程
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