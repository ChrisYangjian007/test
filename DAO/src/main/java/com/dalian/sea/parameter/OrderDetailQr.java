package com.dalian.sea.parameter;

/**
 *
 * @author 杨建
 * @date 2018/4/23
 */
public class OrderDetailQr {
    private String   orderName;
    private String  orderDetailName;
    private String  orderDetailQrList;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderDetailName() {
        return orderDetailName;
    }

    public void setOrderDetailName(String orderDetailName) {
        this.orderDetailName = orderDetailName;
    }

    public String getOrderDetailQrList() {
        return orderDetailQrList;
    }

    public void setOrderDetailQrList(String orderDetailQrList) {
        this.orderDetailQrList = orderDetailQrList;
    }
}
