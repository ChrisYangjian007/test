package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsWorkFlowMapper;
import com.dalian.sea.model.ZsWorkFlow;
import com.dalian.sea.parameter.PZsWorkFlow;
import com.dalian.sea.service.ZsWorkFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TONE on 2018/2/8.
 */
@Service("ZsWorkFlowService")
public class ZsWorkFlowServiceImpl implements ZsWorkFlowService {

    @Autowired
    private ZsWorkFlowMapper zsWorkFlowMapper;

    /**
     * 根据父级id获取工艺流程
     *
     * @param parentId
     * @return
     */
    @Override
    public List<ZsWorkFlow> getWorkFlowByParentId(Long parentId) {
        return zsWorkFlowMapper.getWorkFlowByParentId(parentId);
    }

    @Override
    public List<ZsWorkFlow> getProduceTaskByWorkFlow(Long workFlowId) {
        return zsWorkFlowMapper.getProduceTaskByWorkFlow(workFlowId);
    }


    /**
     * 根据id获取工艺流程
     *
     * @param id
     * @return
     */
    @Override
    public PZsWorkFlow getWorkFlowById(Long id) {
        return zsWorkFlowMapper.getWorkFlowById(id);
    }

    /**
     * 获取最大顺序
     *
     * @return
     */
    @Override
    public ZsWorkFlow getWorkFlowMaxListIndex() {
        return zsWorkFlowMapper.getWorkFlowMaxListIndex();
    }

    /**
     * 添加工艺流程
     *
     * @param zsWorkFlow
     * @return
     */
    @Override
    public Long addZsWorkFlow(ZsWorkFlow zsWorkFlow) {
        Long id = null;
        Integer result = zsWorkFlowMapper.addZsWorkFlow(zsWorkFlow);
        if (0 < result) {
            id = zsWorkFlow.getWorkFlowId();
        }
        return id;
    }

    /**
     * 修改工艺流程
     *
     * @param zsWorkFlow
     * @return
     */
    @Override
    public Boolean updateWorkFlow(ZsWorkFlow zsWorkFlow) {
        Boolean boo = false;
        Integer result = zsWorkFlowMapper.updateWorkFlow(zsWorkFlow);
        if (0 < result) {
            boo = true;
        }
        return boo;
    }

    /**
     * 获取所有工艺流程
     *
     * @return
     */
    @Override
    public List<ZsWorkFlow> getAllWorkFlow() {
        return zsWorkFlowMapper.getAllWorkFlow();
    }

    /**
     * 通过parentId和cName获取工艺流程
     *
     * @param zsWorkFlow
     * @return
     */
    @Override
    public PZsWorkFlow getWorkFlowByParentIdAndName(ZsWorkFlow zsWorkFlow) {
        return zsWorkFlowMapper.getWorkFlowByParentIdAndName(zsWorkFlow);
    }

    /**
     * 删除
     *
     * @param zsWorkFlow
     * @return
     */
    @Override
    public Boolean deleteWorkFlow(ZsWorkFlow zsWorkFlow) {
        Boolean boo = false;
        Integer result = zsWorkFlowMapper.deleteWorkFlow(zsWorkFlow);
        if (0 < result) {
            boo = true;
        }
        return boo;
    }

    /**
     * 通过produceTaskId获取工艺流程
     *
     * @param produceTaskId
     * @return
     */
    @Override
    public ZsWorkFlow getWorkFlowByProduceTaskId(Long produceTaskId) {
        return zsWorkFlowMapper.getWorkFlowByProduceTaskId(produceTaskId);
    }

    @Override
    public String getWorkFlowNameById(Long workFlowId) {
        return zsWorkFlowMapper.getWorkFlowNameById(workFlowId);
    }
}
