package com.dalian.sea.service;


import com.dalian.sea.parameter.*;

import java.util.List;
import java.util.concurrent.Future;

/**
 *
 * @author 杨建
 * @date 2018/3/25
 */
public interface SaOrderDetailService {

    /** 明细列表查询
     * @param orderDetail
     * @param page
     * @param rows
     * @return
     */
    List<POrderDetail> selectListOrderDetailByOrder(POrderDetail orderDetail, Integer page, Integer rows);

    /**下载订单明细
     * @param orderDetail
     * @return
     */
    List<POrderDetail> selectListOrderDetailDown(POrderDetail orderDetail);

    /**根据二维码查看明细
     * @param orderDetail
     * @return
     */
    POrderDetail  selectOrderDetailByQrCode(POrderDetail orderDetail);

    /**统计当日数量
     * @return
     */
    List<POrderDetail> getOrderDetailCountToday();
    /**统计当日数量
     * @return
     */
    List<POrderDetail> getOrderDetailCountbyday(POrderDetail orderDetail,Integer page, Integer rows);
    Future<Boolean> updateOrderdetailByBatch(List<POrderDetail> orderDetailList);

    /** 订单明细更新
     * @param objects
     * @return
     */
    Future<Boolean>  orderDetailSynchronization(List<OrderDetailInfo> objects);

    /**
     * 通过二维码获取
     * @param qrCode
     * @return
     */
    PSaOrderDetail getOrderAndOrderDetailByQrCode(String qrCode);
     /**
     *  获取已打包的订单明细二维码
     * */
    List<OrderDetailQr> selectOrderQrBy(PSaOrder saOrder);

    /**
    *  获取已打包的订单明细箱码
    * */
    List<OrderDetailBoxCode> selectOrderBoxBy(PSaOrder saOrder);

    List<POrderDetail>  selectOrderDetailByOrderList(List<String> list);
}
