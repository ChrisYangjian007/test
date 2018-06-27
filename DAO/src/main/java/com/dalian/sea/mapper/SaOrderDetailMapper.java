package com.dalian.sea.mapper;

import com.dalian.sea.model.SaOrderDetail;
import com.dalian.sea.parameter.*;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SaOrderDetailMapper extends Mapper<SaOrderDetail> {
    /**查询订单明细列表
     * @param orderDetail
     * @return
     */
    List<POrderDetail> selectListOrderDetailByOrder(POrderDetail orderDetail);

    /**查询订单明细列表
     * @param detailCodes
     * @return
     */
    List<PSaOrderDetailPDA> getSaOrderDetailByDetailCodeArray(@Param("detailCodes") String[] detailCodes);

    /**根据订货明细编号
     * @param orderDetail
     * @return
     */
    POrderDetail getOrderDetailByOrderName(POrderDetail orderDetail);

    /** 添加
     * @param orderDetail
     * @return
     */
    Integer  insertOrderDetailSelective(SaOrderDetail orderDetail);

    /**修改
     * @param orderDetail
     * @return
     */
    Integer  updateByOrderDetailSelective(SaOrderDetail orderDetail);

    /**批量修改
     * @param list
     * @return
     */
    Integer updateOrderDetailByBatch(List<POrderDetail> list);
    /**根据二维码查询明细
     * @param orderDetail
     * @return
     */
    POrderDetail getOrderDetailByQrCode(POrderDetail orderDetail);

    /**当日订单统计
     * @return
     */
    List<POrderDetail> selectListOrderDetailCountToday();
    /**当日订单统计
     * @return
     */
    List<POrderDetail> selectListOrderDetailCountbyday(POrderDetail orderDetail);
    /**
     *通过二维码获取
     * @return
     */
    PSaOrderDetail getOrderAndOrderDetailByQrCode(@Param("qrCode")String qrCode);


    /**crm 获取已经打包的订单明细
     * @param order
     * @return
     */
    List<OrderDetailQr>  selectOrderDetailQrList(PSaOrder order);

    /**crm 获取已经箱码的订单明细
     * @return
     */
    List<OrderDetailBoxCode> selectOrderDetailBoxList(PSaOrder order);

    List<POrderDetail> selectOrderDetailListByOrderList(List<String> list);
}