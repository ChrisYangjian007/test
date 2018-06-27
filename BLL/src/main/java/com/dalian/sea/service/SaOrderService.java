package com.dalian.sea.service;

import com.dalian.sea.model.SaOrder;
import com.dalian.sea.parameter.OrderInfo;
import com.dalian.sea.parameter.PSaOrder;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author 杨建
 * @date 2018/3/23
 */
public interface SaOrderService {

    /**
     * 查询列表
     *
     * @param order
     * @param page
     * @param rows
     * @return
     */
    List<PSaOrder> selectListOrderByOrder(PSaOrder order, Integer page, Integer rows);

    /**
     * 下载列表
     *
     * @param order
     * @return
     */
    List<PSaOrder> selectListOrderdown(PSaOrder order);



    PSaOrder selectSaOrderByName(PSaOrder order);

    /**
     * 删除箱码的二维码
     *
     * @param boxCode
     * @param qrCode
     * @return
     */
    boolean deleteOrderByQrBox(String boxCode, String qrCode);

    /**
     * 添加二维码
     *
     * @param boxCode
     * @param orderCode
     * @param qrCode
     * @return
     */
    String addOrderQrBox(String boxCode, String orderCode, String qrCode);


    /**二维码替换
     * @param boxCode
     * @param orderCode
     * @param qrCode
     * @return
     */
    boolean  updateOrderQrBox(String boxCode, String orderCode, String qrCode);

    /**查询订单列表
     * @param order
     * @return
     */
    List<PSaOrder> selectListOrderByOrder(PSaOrder order);

    /**
     * 获取今日订单任务
     *
     * @return
     */
    List<PSaOrder> getTodayOrder();

    /**
     * 获取今日订单总数
     * @return
     */
    Integer todayOrderCount();

    /**
     * 今日已完成订单数量
     * @return
     */
    Integer todayFinishedCount();
    Future<Boolean>  updateOrderByList(List<SaOrder> orderList);
    /**订单更新
     * @param objects
     * @return
     */
    Future<Boolean>  orderSynchronization(List<OrderInfo> objects);
}
