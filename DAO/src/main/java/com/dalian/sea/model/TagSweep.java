package com.dalian.sea.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tag_sweep")
public class TagSweep implements Serializable {
    /**
     * 产品ID
     */
    @Id
    @Column(name = "sweep_id")
    private Long sweepId;

    /**
     * 订单明细编号
     */
    @Column(name = "order_detail_code")
    private String orderDetailCode;

    /**
     * 订单编号
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 追溯码
     */
    @Column(name = "organic_code")
    private String organicCode;

    /**
     * 扫码人ip
     */
    @Column(name = "create_ip")
    private String createIp;

    /**
     * 更新IP
     */
    @Column(name = "update_ip")
    private String updateIp;

    /**
     * 规则名称
     */
    @Column(name = "rule_name")
    private String ruleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 明码
     */
    @Column(name = "clear_code")
    private Integer clearCode;

    /**
     * 二维码
     */
    @Column(name = "qr_code")
    private String qrCode;

    /**
     * 批次号
     */
    @Column(name = "batch_no")
    private String batchNo;

    /**
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 规格
     */
    @Column(name = "product_spec_name")
    private String productSpecName;

    /**
     * 箱码
     */
    @Column(name = "box_code")
    private String boxCode;

    /**
     * 排序
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 操作人员ID
     */
    @Column(name = "create_user_id")
    private Long createUserId;
    @Column(name = "status")
    private Byte status;
    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 修改用户主键
     */
    @Column(name = "update_user_id")
    private Long updateUserId;

    /**
     * 最近区域
     */
    @Column(name = "update_area")
    private String updateArea;

    private static final long serialVersionUID = 1L;

    /**
     * 获取产品ID
     *
     * @return sweep_id - 产品ID
     */
    public Long getSweepId() {
        return sweepId;
    }

    /**
     * 设置产品ID
     *
     * @param sweepId 产品ID
     */
    public void setSweepId(Long sweepId) {
        this.sweepId = sweepId;
    }

    /**
     * 获取订单明细编号
     *
     * @return order_detail_code - 订单明细编号
     */
    public String getOrderDetailCode() {
        return orderDetailCode;
    }

    /**
     * 设置订单明细编号
     *
     * @param orderDetailCode 订单明细编号
     */
    public void setOrderDetailCode(String orderDetailCode) {
        this.orderDetailCode = orderDetailCode;
    }

    /**
     * 获取订单编号
     *
     * @return order_code - 订单编号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置订单编号
     *
     * @param orderCode 订单编号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 获取追溯码
     *
     * @return organic_code - 追溯码
     */
    public String getOrganicCode() {
        return organicCode;
    }

    /**
     * 设置追溯码
     *
     * @param organicCode 追溯码
     */
    public void setOrganicCode(String organicCode) {
        this.organicCode = organicCode;
    }

    /**
     * 获取扫码人ip
     *
     * @return create_ip - 扫码人ip
     */
    public String getCreateIp() {
        return createIp;
    }

    /**
     * 设置扫码人ip
     *
     * @param createIp 扫码人ip
     */
    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    /**
     * 获取更新IP
     *
     * @return update_ip - 更新IP
     */
    public String getUpdateIp() {
        return updateIp;
    }

    /**
     * 设置更新IP
     *
     * @param updateIp 更新IP
     */
    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp;
    }

    /**
     * 获取规则名称
     *
     * @return rule_name - 规则名称
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * 设置规则名称
     *
     * @param ruleName 规则名称
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取明码
     *
     * @return clear_code - 明码
     */
    public Integer getClearCode() {
        return clearCode;
    }

    /**
     * 设置明码
     *
     * @param clearCode 明码
     */
    public void setClearCode(Integer clearCode) {
        this.clearCode = clearCode;
    }

    /**
     * 获取二维码
     *
     * @return qr_code - 二维码
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * 设置二维码
     *
     * @param qrCode 二维码
     */
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * 获取批次号
     *
     * @return batch_no - 批次号
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * 设置批次号
     *
     * @param batchNo 批次号
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * 获取产品名称
     *
     * @return product_name - 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     *
     * @param productName 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取规格
     *
     * @return product_spec_name - 规格
     */
    public String getProductSpecName() {
        return productSpecName;
    }

    /**
     * 设置规格
     *
     * @param productSpecName 规格
     */
    public void setProductSpecName(String productSpecName) {
        this.productSpecName = productSpecName;
    }

    /**
     * 获取箱码
     *
     * @return box_code - 箱码
     */
    public String getBoxCode() {
        return boxCode;
    }

    /**
     * 设置箱码
     *
     * @param boxCode 箱码
     */
    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    /**
     * 获取排序
     *
     * @return list_index - 排序
     */
    public Integer getListIndex() {
        return listIndex;
    }

    /**
     * 设置排序
     *
     * @param listIndex 排序
     */
    public void setListIndex(Integer listIndex) {
        this.listIndex = listIndex;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取操作人员ID
     *
     * @return create_user_id - 操作人员ID
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置操作人员ID
     *
     * @param createUserId 操作人员ID
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取更新时间
     *
     * @return update_date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取修改用户主键
     *
     * @return update_user_id - 修改用户主键
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 设置修改用户主键
     *
     * @param updateUserId 修改用户主键
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取最近区域
     *
     * @return update_area - 最近区域
     */
    public String getUpdateArea() {
        return updateArea;
    }

    /**
     * 设置最近区域
     *
     * @param updateArea 最近区域
     */
    public void setUpdateArea(String updateArea) {
        this.updateArea = updateArea;
    }


    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}