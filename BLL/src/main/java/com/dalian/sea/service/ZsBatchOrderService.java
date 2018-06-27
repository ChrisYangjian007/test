package com.dalian.sea.service;

import com.dalian.sea.model.BoxOrderTemp;
import com.dalian.sea.parameter.PZsBatchOrder;
import com.dalian.sea.model.ZsBatchOrder;

import java.util.List;

/**
 *
 * @author 杨建
 * @date 2018/6/25
 */
public interface ZsBatchOrderService {
    /**批量添加
     * @param zsBatchOrders
     */
    void add(List<ZsBatchOrder> zsBatchOrders);

    /**查询未被打包的订单
     * @return
     */
    List<BoxOrderTemp> selectBoxOrderTempListByNotPack();

    /**批量更新已打包
     * @param ids
     */
    void  updateListBoxOrderTem(List<Long> ids);

    /**添加和更新
     * @param zsBatchOrders
     * @param ids
     */
    void  updateAndInsertBatchOrder(List<ZsBatchOrder> zsBatchOrders,List<Long> ids);

    List<PZsBatchOrder> selectBatchOrderListGropBy(ZsBatchOrder batchOrder, Integer page, Integer row );
}
