package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.PuEnterStockMapper;
import com.dalian.sea.parameter.PPuEnterStock;
import com.dalian.sea.service.PuEnterStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/2/28.
 */
@Service("PuEnterStockService")
public class PuEnterStockServiceImpl implements PuEnterStockService{

    @Autowired
    private PuEnterStockMapper puEnterStockMapper;

    /**
     * 通过生产任务获取入库信息
     * @param id
     * @return
     */
    @Override
    public List<PPuEnterStock> getEnterStockByProduceTask(Long id) {
        return puEnterStockMapper.getEnterStockByProduceTask(id);
    }
}
