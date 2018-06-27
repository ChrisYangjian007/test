package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsTaskDetailValueMapper;
import com.dalian.sea.service.ZsTaskDetailValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/3/5.
 */
@Service("ZsTaskDetailService")
public class ZsTaskDetailValueServiceImpl implements ZsTaskDetailValueService{

    @Autowired
    private ZsTaskDetailValueMapper zsTaskDetailValueMapper;

    @Override
    public Long getWorkFlowId(Long produceTaskId) {
        return zsTaskDetailValueMapper.getWorkFlowId(produceTaskId);
    }
}
