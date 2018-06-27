package com.dalian.sea.parameter;

import java.util.List;

/**
 *
 * @author 杨建
 * @date 2018/3/23
 */
public class OrderResult {
    private boolean result;
    private List<OrderInfo> data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<OrderInfo> getData() {
        return data;
    }

    public void setData(List<OrderInfo> data) {
        this.data = data;
    }
}
