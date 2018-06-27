package com.dalian.sea.parameter;

/**
 *
 * @author 杨建
 * @date 2018/3/21
 */
public class ProductInfo {

    /**
     * dw : 斤
     * createdate : 2017-08-10 15:40:45
     * cpmc : 晓芹促销半干小
     * cpxl : null
     * cpdj : null
     * owneridccname : 李超
     * jxs : 否
     * cpgg : 61-80头/500g
     * cpdm : null
     * jxsghj : 0
     * lastmodifydate : 2017-08-10 15:40:45
     * currency : CNY
     * cpdl : 半干海参
     * id : 0092017142204E4s5RJe
     * lastmodifybyidccname : 李超
     * yqy : true
     * name : 1121012
     * jylsj : 1348
     * lastmodifybyid : 00520175E87022EGrnOB
     * recordtype : null
     * cpxil : 半干海参
     * createbyid : 00520175E87022EGrnOB
     * cpx : 海参
     * ownerid : 00520175E87022EGrnOB
     * CCObjectAPI : product
     * cpzl : 1.0000
     * zbqld : 18
     * zbqlc : 30
     * cppc : C251105
     * recordtypeccname : null
     * lbsaddress : null
     * sxjf : null
     * gml : null
     * lb : 货品
     * createbyidccname : 李超
     * cpbz : null
     */

    private String dw;//单位
    private String cpmc;//产品名称
    private String cpgg;//产品规格
    private String cpdl;//产品大类
    private String name;//产品编码
    private String cpxil;//产品小类
    private String cpx;//产品线
    private String cpzl;//产品重量
    private String zbqld;//保质期(冷冻)
    private String zbqlc;//保质期(冷藏)
    private String cppc;//产品批次
    private String lb;//类别

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }

    public String getCpgg() {
        return cpgg;
    }

    public void setCpgg(String cpgg) {
        this.cpgg = cpgg;
    }

    public String getCpdl() {
        return cpdl;
    }

    public void setCpdl(String cpdl) {
        this.cpdl = cpdl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpxil() {
        return cpxil;
    }

    public void setCpxil(String cpxil) {
        this.cpxil = cpxil;
    }

    public String getCpx() {
        return cpx;
    }

    public void setCpx(String cpx) {
        this.cpx = cpx;
    }

    public String getCpzl() {
        return cpzl;
    }

    public void setCpzl(String cpzl) {
        this.cpzl = cpzl;
    }

    public String getZbqld() {
        return zbqld;
    }

    public void setZbqld(String zbqld) {
        this.zbqld = zbqld;
    }

    public String getZbqlc() {
        return zbqlc;
    }

    public void setZbqlc(String zbqlc) {
        this.zbqlc = zbqlc;
    }

    public String getCppc() {
        return cppc;
    }

    public void setCppc(String cppc) {
        this.cppc = cppc;
    }

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }
}
