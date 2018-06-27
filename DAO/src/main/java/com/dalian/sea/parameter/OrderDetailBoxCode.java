package com.dalian.sea.parameter;

import java.util.List;

/**
 *
 * @author 杨建
 * @date 2018/4/23
 */
public class OrderDetailBoxCode {
    private String boxCode;
    private String orderDetailString;
    private String orderName;
    private String boxQrString;
    private List<OrderDetailNum>  orderDetailList;

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }


    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderDetailString() {
        return orderDetailString;
    }

    public void setOrderDetailString(String orderDetailString) {
        this.orderDetailString = orderDetailString;
    }

    public List<OrderDetailNum> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailNum> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public String getBoxQrString() {
        return boxQrString;
    }

    public void setBoxQrString(String boxQrString) {
        this.boxQrString = boxQrString;
    }
}
