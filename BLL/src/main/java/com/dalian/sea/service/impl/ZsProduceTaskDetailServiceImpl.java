package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsWarehouseMapper;
import com.dalian.sea.mapper.ZsWarehouseUserMapper;
import com.dalian.sea.mapper.zsProduceTaskDetailMapper;
import com.dalian.sea.model.ZsWarehouse;
import com.dalian.sea.model.zsProduceTaskDetail;
import com.dalian.sea.parameter.PZsProduceTaskDetail;
import com.dalian.sea.service.ZsProduceTaskDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈逸文 on 2018/3/22.
 */
@Service
@Slf4j
public class ZsProduceTaskDetailServiceImpl implements ZsProduceTaskDetailService {

    @Autowired
    private zsProduceTaskDetailMapper produceTaskDetailMapper;

    @Autowired
    private ZsWarehouseUserMapper zsWarehouseUserMapper;

    @Autowired
    private ZsWarehouseMapper zsWarehouseMapper;

    @Override
    public List<PZsProduceTaskDetail> getProduceTaskDetailByProduceTaskNo(PZsProduceTaskDetail pZsProduceTaskDetail) {
        return produceTaskDetailMapper.getProduceTaskDetailByProduceTaskNo(pZsProduceTaskDetail);
    }

    @Override
    public List<PZsProduceTaskDetail> getProduceTaskDetailByBatchNo(PZsProduceTaskDetail pZsProduceTaskDetail) {
        return produceTaskDetailMapper.getProduceTaskDetailByBatchNo(pZsProduceTaskDetail);
    }

    @Override
    public List<PZsProduceTaskDetail> getProduceTaskDetailByProduceTaskId(Long produceTaskId, Long userId, Long companyId) {
        List<Long> ids = new ArrayList<>();
        //获取当前用户管理的仓库id
        if (companyId != 0) {
            ids = zsWarehouseUserMapper.getWarehouseIdByUserId(userId);
        } else {
            List<ZsWarehouse> list = zsWarehouseMapper.getAllWarehouse();
            for (ZsWarehouse zsWarehouse : list) {
                ids.add(zsWarehouse.getWarehouseId());
            }
        }
        return produceTaskDetailMapper.getProduceTaskDetailByProduceTaskId(produceTaskId, ids);
    }

    @Override
    public Boolean TaskDetailByProduceTaskId(Long produceTaskId) {
        List<zsProduceTaskDetail> list = produceTaskDetailMapper.getDetailByProduceTaskId(produceTaskId);
        if (0 == list.size() || null == list) {
            return true;
        } else {
            return false;
        }
    }
}
