package com.dalian.sea.parameter;

import java.util.List;

/**
 * Created by 杨建 on 2018/3/21.
 */
public class DataInfo {
    private boolean result;
    private List<ProductInfo> data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<ProductInfo> getData() {
        return data;
    }

    public void setData(List<ProductInfo> data) {
        this.data = data;
    }
}
