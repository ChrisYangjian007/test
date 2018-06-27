package com.dalian.sea.parameter;

import com.dalian.sea.model.ZsBatchOrder;

/**
 * Created by 杨建 on 2018/6/26.
 */
public class PZsBatchOrder extends ZsBatchOrder {
    private Integer packNumber;

    public Integer getPackNumber() {
        return packNumber;
    }

    public void setPackNumber(Integer packNumber) {
        this.packNumber = packNumber;
    }
}
