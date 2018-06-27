package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BoxOrderTempMapper;
import com.dalian.sea.mapper.ZsBatchOrderMapper;
import com.dalian.sea.model.BoxOrderTemp;
import com.dalian.sea.parameter.PZsBatchOrder;
import com.dalian.sea.model.ZsBatchOrder;
import com.dalian.sea.service.ZsBatchOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 *
 * @author 杨建
 * @date 2018/6/25
 */
@Service
public class ZsBatchOrderServiceImpl implements ZsBatchOrderService {
    @Autowired
    private ZsBatchOrderMapper zsBatchOrderMapper;
    @Autowired
    private BoxOrderTempMapper boxOrderTempMapper;
    @Override
    public void add(List<ZsBatchOrder> zsBatchOrders) {
        zsBatchOrderMapper.insetBatchOrderList(zsBatchOrders);
    }

    @Override
    public List<BoxOrderTemp> selectBoxOrderTempListByNotPack() {
        return boxOrderTempMapper.selectBoxOrderTempListByNotPack();
    }

    @Override
    public void updateListBoxOrderTem(List<Long> ids) {
        boxOrderTempMapper.updateListBoxOrderTem(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAndInsertBatchOrder(List<ZsBatchOrder> zsBatchOrders, List<Long> ids) {
        try {
            boxOrderTempMapper.updateListBoxOrderTem(ids);
            zsBatchOrderMapper.insetBatchOrderList(zsBatchOrders);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

    }

    @Override
    public List<PZsBatchOrder> selectBatchOrderListGropBy(ZsBatchOrder batchOrder, Integer page, Integer row) {
        return zsBatchOrderMapper.selectBatchOrderListGropBy(batchOrder);
    }
}
