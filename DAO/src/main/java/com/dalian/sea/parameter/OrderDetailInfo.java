package com.dalian.sea.parameter;

/**
 *
 * @author 杨建
 * @date 2018/3/25
 */
public class OrderDetailInfo {
    private String name;//明细编号
    private String dhsqccname;//订货申请编号
    private String cpbmccname;//产品编码
    private String cpmc;//产品名称
    private String gg;//规格
    private String dw;//单位
    private String sl;//数量
    private String cbj;//成本价
    private String zk;//折扣
    private String bz;//备注
    private String createdate;//创建时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDhsqccname() {
        return dhsqccname;
    }

    public void setDhsqccname(String dhsqccname) {
        this.dhsqccname = dhsqccname;
    }

    public String getCpbmccname() {
        return cpbmccname;
    }

    public void setCpbmccname(String cpbmccname) {
        this.cpbmccname = cpbmccname;
    }

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getCbj() {
        return cbj;
    }

    public void setCbj(String cbj) {
        this.cbj = cbj;
    }

    public String getZk() {
        return zk;
    }

    public void setZk(String zk) {
        this.zk = zk;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
