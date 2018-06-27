package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BoxOrderMapper;
import com.dalian.sea.mapper.SaOrderDetailMapper;
import com.dalian.sea.model.BoxOrder;
import com.dalian.sea.model.SaOrderDetail;
import com.dalian.sea.parameter.PSaOrderDetailPDA;
import com.dalian.sea.parameter.PSaOrderPDA;
import com.dalian.sea.service.ZsBoxOrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ZsBoxOrderServiceImpl
 *
 * @author TONE
 * @date 2018/3/28.
 */
@Service("ZsBoxOrderService")
public class ZsBoxOrderServiceImpl implements ZsBoxOrderService {
    @Autowired
    private BoxOrderMapper boxOrderMapper;
    @Autowired
    private SaOrderDetailMapper orderDetailMapper;


    /**
     * 根据箱码获取
     * @param boxCode
     * @return
     */
    @Override
    public BoxOrder getBoxOrderByBoxCode(String boxCode) {
        return boxOrderMapper.getBoxOrderByBoxCode(boxCode);
    }

    /**
     * 根据箱码获取
     * @param boxOrder
     * @return
     */
    @Override
    public PSaOrderPDA getBoxOrderByBoxCodeForPDA(BoxOrder boxOrder) {
        PSaOrderPDA orderPDA = new PSaOrderPDA();
        orderPDA.setBoxOrder(boxOrder);
        String[] detailCode = boxOrder.getOrderDetailCode().split(",");
        List<PSaOrderDetailPDA> orderDetailList = orderDetailMapper.getSaOrderDetailByDetailCodeArray(detailCode);
        if (null!=orderDetailList&&0!=orderDetailList.size()){
            for (PSaOrderDetailPDA orderDetailPDA:orderDetailList){
                for (String str:detailCode){
                    if (str.equals(orderDetailPDA.getOrderDetailName())){
                        orderDetailPDA.setNumber(orderDetailPDA.getNumber()+1);
                    }
                }
            }
            orderPDA.setOrderDetailPDAList(orderDetailList);
        }
        return orderPDA;
    }

    /**
     * 根据订单编码获取
     * @param orderCode
     * @return
     */
    @Override
    public List<BoxOrder> getBoxOrderByOrderCodeForPDA(String orderCode,Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        return boxOrderMapper.getBoxOrderByOrderCodeForPDA(orderCode);
    }

    /**
     * 根据箱码获取
     * @param boxOrderList
     * @return
     */
    @Override
    public List<PSaOrderPDA> getBoxOrderByBoxCodeListForPDA(List<BoxOrder> boxOrderList) {
        List<PSaOrderPDA> saOrderPDAList = new ArrayList<>();
        for (BoxOrder boxOrder:boxOrderList) {
            PSaOrderPDA orderPDA = new PSaOrderPDA();
            orderPDA.setBoxOrder(boxOrder);
            String[] detailCode = boxOrder.getOrderDetailCode().split(",");
            List<PSaOrderDetailPDA> orderDetailList = orderDetailMapper.getSaOrderDetailByDetailCodeArray(detailCode);
            if (null != orderDetailList && 0 != orderDetailList.size()) {
                for (PSaOrderDetailPDA orderDetailPDA : orderDetailList) {
                    for (String str : detailCode) {
                        if (str.equals(orderDetailPDA.getOrderDetailName())) {
                            orderDetailPDA.setNumber(orderDetailPDA.getNumber() + 1);
                        }
                    }
                }
                orderPDA.setOrderDetailPDAList(orderDetailList);
            }
            saOrderPDAList.add(orderPDA);
        }
        return saOrderPDAList;
    }
}
