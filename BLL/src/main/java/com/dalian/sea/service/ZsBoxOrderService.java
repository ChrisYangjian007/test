package com.dalian.sea.service;

import com.dalian.sea.model.BoxOrder;
import com.dalian.sea.parameter.PSaOrderPDA;

import java.util.List;

/**
 * ZsBoxOrder
 *
 * @author TONE
 * @date 2018/3/28.
 */
public interface ZsBoxOrderService {

    /**
     * 根据箱码获取
     * @param boxCode
     * @return
     */
    BoxOrder getBoxOrderByBoxCode(String boxCode);

    /**
     * 根据箱码获取
     * @param boxOrder
     * @return
     */
    PSaOrderPDA getBoxOrderByBoxCodeForPDA(BoxOrder boxOrder);

    /**
     * 根据订单编码获取
     * @param orderCode
     * @return
     */
    List<BoxOrder> getBoxOrderByOrderCodeForPDA(String orderCode,Integer page,Integer rows);

    /**
     * 根据箱码获取
     * @param boxOrderList
     * @return
     */
    List<PSaOrderPDA> getBoxOrderByBoxCodeListForPDA(List<BoxOrder> boxOrderList);


}
