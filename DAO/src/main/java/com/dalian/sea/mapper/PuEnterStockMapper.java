package com.dalian.sea.mapper;

import com.dalian.sea.model.PuEnterStock;
import com.dalian.sea.parameter.PPuEnterStock;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PuEnterStockMapper extends Mapper<PuEnterStock> {

    /**
     * 通过生产任务获取入库信息
     * @param id
     * @return
     */
    List<PPuEnterStock> getEnterStockByProduceTask(Long id);

    /**
     * 新建入库详情
     * @param enterStock
     * @return
     */
    Integer insertEnterStock(PuEnterStock enterStock);
}