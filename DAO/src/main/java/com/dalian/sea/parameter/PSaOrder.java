package com.dalian.sea.parameter;

import com.dalian.sea.model.SaOrder;

/**
 *
 * @author 杨建
 * @date 2018/3/23
 */
public class PSaOrder extends SaOrder {
    private String orderDate;
    private String startDate;
    private String endDate;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
