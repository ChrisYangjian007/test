package com.dalian.sea.parameter;

import com.dalian.sea.model.ZsBatch;

import java.util.Date;

/**
 *
 * @author 杨建
 * @date 2018/3/28
 */
public class PZsBatch extends ZsBatch {

    /**
     * 出库申请人
     * */
    private String leavePerson;
    /**
     * 出库时间
     * */
    private Date  leaveDate;
    /**
     * 打包数量
    * */
    private Integer packNumber;
    /**
     * 未打包数量
     * */
    private  Integer notPackNumber;
    /**
     * 最新打包时间
     * */
    private  Date lastPackTime;
    public String getLeavePerson() {
        return leavePerson;
    }

    public void setLeavePerson(String leavePerson) {
        this.leavePerson = leavePerson;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Integer getPackNumber() {
        return packNumber;
    }

    public void setPackNumber(Integer packNumber) {
        this.packNumber = packNumber;
    }

    public Integer getNotPackNumber() {
        return notPackNumber;
    }

    public void setNotPackNumber(Integer notPackNumber) {
        this.notPackNumber = notPackNumber;
    }

    public Date getLastPackTime() {
        return lastPackTime;
    }

    public void setLastPackTime(Date lastPackTime) {
        this.lastPackTime = lastPackTime;
    }
}
