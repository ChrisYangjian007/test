package com.dalian.sea.mapper;

import com.dalian.sea.model.BoxOrder;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BoxOrderMapper extends Mapper<BoxOrder> {

    /**批量插入
     * @param list
     * @return
     */
    Integer insertBoxOrderByBatch(List<BoxOrder> list);

    /**
     * 根据箱码获取
     * @param boxCode
     * @return
     */
    BoxOrder getBoxOrderByBoxCode(String boxCode);

    /**
     * 根据订单编码获取
     * @param orderCode
     * @return
     */
    List<BoxOrder> getBoxOrderByOrderCodeForPDA(String orderCode);


    Integer updateBoxOrderByOrder(BoxOrder boxOrder);

    BoxOrder  getBoxCodeByOrderDetailCode(String orderDetailCode);
}