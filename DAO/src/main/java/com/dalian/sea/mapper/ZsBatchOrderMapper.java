package com.dalian.sea.mapper;

import com.dalian.sea.parameter.PZsBatchOrder;
import com.dalian.sea.model.ZsBatchOrder;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsBatchOrderMapper extends Mapper<ZsBatchOrder> {
    /**批量插入
     * @param list
     */
    void insetBatchOrderList(List<ZsBatchOrder> list);
    List<PZsBatchOrder> selectBatchOrderListGropBy(ZsBatchOrder batchOrder);
}