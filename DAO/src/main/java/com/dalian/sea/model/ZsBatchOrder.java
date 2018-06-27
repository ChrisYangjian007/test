package com.dalian.sea.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_batch_order")
public class ZsBatchOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单编号
     */
    @Column(name = "order_name")
    private String orderName;

    /**
     * 所属门店
     */
    @Column(name = "order_ssmd")
    private String orderSsmd;

    /**
     * 经销商
     */
    @Column(name = "order_jxs")
    private String orderJxs;

    /**
     * 批次id
     */
    @Column(name = "batch_id")
    private Long batchId;

    /**
     * 二维码
     */
    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "create_date")
    private Date createDate;

    private Byte status;

    private static final long serialVersionUID = 1L;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订单编号
     *
     * @return order_name - 订单编号
     */
    public String getOrderName() {
        return orderName;
    }

    /**
     * 设置订单编号
     *
     * @param orderName 订单编号
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    /**
     * 获取所属门店
     *
     * @return order_ssmd - 所属门店
     */
    public String getOrderSsmd() {
        return orderSsmd;
    }

    /**
     * 设置所属门店
     *
     * @param orderSsmd 所属门店
     */
    public void setOrderSsmd(String orderSsmd) {
        this.orderSsmd = orderSsmd;
    }

    /**
     * 获取经销商
     *
     * @return order_jxs - 经销商
     */
    public String getOrderJxs() {
        return orderJxs;
    }

    /**
     * 设置经销商
     *
     * @param orderJxs 经销商
     */
    public void setOrderJxs(String orderJxs) {
        this.orderJxs = orderJxs;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
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
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

}