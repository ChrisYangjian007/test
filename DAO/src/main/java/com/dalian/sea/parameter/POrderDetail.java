package com.dalian.sea.parameter;

import com.dalian.sea.model.SaOrderDetail;

/**
 *
 * @author 杨建
 * @date 2018/3/25
 */
public class POrderDetail extends SaOrderDetail {
    private String startDate;
    private String endDate;
    private String boxCode;//箱码
    private String expressForm;//快递单

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

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public String getExpressForm() {
        return expressForm;
    }

    public void setExpressForm(String expressForm) {
        this.expressForm = expressForm;
    }
}
