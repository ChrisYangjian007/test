package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_box_order_temp")
@Data
public class BoxOrderTemp implements Serializable {
    @Id
    @Column(name = "box_order_temp_id")
    private Long boxOrderTempId;

    /**
     * 箱码
     */
    @Column(name = "box_code")
    private String boxCode;

    /**
     * 垛码
     */
    @Column(name = "heap_code")
    private String heapCode;

    /**
     * 二维码
     */
    @Column(name = "qr_code")
    private String qrCode;

    /**
     * 订单编号
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 明细编号
     */
    @Column(name = "order_detail_code")
    private String orderDetailCode;

    /**
     * 产品编码
     */
    @Column(name = "product_code")
    private String productCode;

    /**
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 产品规格
     */
    @Column(name = "product_format")
    private String productFormat;

    /**
     * 产品单位
     */
    @Column(name = "product_unit")
    private String productUnit;

    /**
     * 标记码
     */
    private String random;

    /**
     * 状态
     */
    private Byte status;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "create_user_id")
    private Long createUserId;

    @Column(name = "create_user_name")
    private String createUserName;
    /**
     * 状态
     */
    private Byte isPack;
    private static final long serialVersionUID = 1L;


}