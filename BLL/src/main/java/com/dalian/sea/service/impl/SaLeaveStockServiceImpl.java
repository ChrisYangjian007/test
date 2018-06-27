package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.SaLeaveStockMapper;
import com.dalian.sea.model.SaLeaveStock;
import com.dalian.sea.parameter.PSaLeaveStock;
import com.dalian.sea.parameter.SaLeaveStockDetailPara;
import com.dalian.sea.parameter.YhLeaveStock;
import com.dalian.sea.parameter.YhLeaveStockDetail;
import com.dalian.sea.service.SaLeaveStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/28.
 */
@Service("SaLeaveService")
public class SaLeaveStockServiceImpl implements SaLeaveStockService{

    @Autowired
    private SaLeaveStockMapper saLeaveStockMapper;

    /**
     * 通过生产任务获取出库信息
     * @param id
     * @return
     */
    @Override
    public List<PSaLeaveStock> getLeaveStockByProduceTask(Long id) {
        return saLeaveStockMapper.getLeaveStockByProduceTask(id);
    }

    @Override
    public Long InsertLeaveStock(YhLeaveStock yhLeaveStock, Date date, Long userId) {
        return saLeaveStockMapper.InsertLeaveStock(yhLeaveStock,date,userId);
    }

    @Override
    public SaLeaveStock getLeaveStockByLeaveNo(String leaveNo) {
        return saLeaveStockMapper.getLeaveStockByLeaveNo(leaveNo);
    }
}
