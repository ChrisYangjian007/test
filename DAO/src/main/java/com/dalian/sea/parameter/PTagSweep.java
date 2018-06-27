package com.dalian.sea.parameter;

import com.dalian.sea.model.TagSweep;

import java.io.Serializable;

/**
 * Created by 杨建 on 2018/3/15.
 */
public class PTagSweep extends TagSweep implements Serializable {
    private String startCode; //开始码段
    private String endCode; //结束码段
    private String  batchCount;//扫码次数

    public String getStartCode() {
        return startCode;
    }

    public void setStartCode(String startCode) {
        this.startCode = startCode;
    }

    public String getEndCode() {
        return endCode;
    }

    public void setEndCode(String endCode) {
        this.endCode = endCode;
    }

    public String getBatchCount() {
        return batchCount;
    }

    public void setBatchCount(String batchCount) {
        this.batchCount = batchCount;
    }
}
