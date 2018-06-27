package com.dalian.sea.service;

import com.dalian.sea.parameter.PPuEnterStock;

import java.util.List;

/**
 * Created by Administrator on 2018/2/28.
 */
public interface PuEnterStockService {


    /**
     * 通过生产任务获取入库信息
     * @param id
     * @return
     */
    List<PPuEnterStock> getEnterStockByProduceTask(Long id);
}
