package com.dalian.sea.parameter;

/**
 *
 * @author 杨建
 * @date 2018/3/23
 */
public class OrderInfo {
    private String name;//订货申请编号
    private String owneridccname;//申请人
    private String dhrq;//订货日期
    private String shr;//收货人
    private String shdz;//收货地址
    private String lxdh;//联系电话
    private String spzt;//审批状态
    private String ssmdccname;//所属门店
    private String jxsccname;//经销商

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwneridccname() {
        return owneridccname;
    }

    public void setOwneridccname(String owneridccname) {
        this.owneridccname = owneridccname;
    }

    public String getDhrq() {
        return dhrq;
    }

    public void setDhrq(String dhrq) {
        this.dhrq = dhrq;
    }

    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    public String getShdz() {
        return shdz;
    }

    public void setShdz(String shdz) {
        this.shdz = shdz;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getSpzt() {
        return spzt;
    }

    public void setSpzt(String spzt) {
        this.spzt = spzt;
    }

    public String getSsmdccname() {
        return ssmdccname;
    }

    public void setSsmdccname(String ssmdccname) {
        this.ssmdccname = ssmdccname;
    }

    public String getJxsccname() {
        return jxsccname;
    }

    public void setJxsccname(String jxsccname) {
        this.jxsccname = jxsccname;
    }
}
