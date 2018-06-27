package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_box_order")
@Data
public class BoxOrder implements Serializable {
    /**
     * 箱码ID
     */
    @Id
    @Column(name = "box_order_id")
    private Long boxOrderId;

    /**
     * 箱码编号
     */
    @Column(name = "box_code")
    private String boxCode;

    /**
     * 排序

     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 状态(1-正常,6-删除,2-禁用)
     */
    private Byte status;

    /**
     * 垛码
     */
    @Column(name = "heap_code")
    private String heapCode;

    /**
     * 订单编号
     */
    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "create_date")
    private Date createDate;

    /**
     * 订单明细编号
     */
    @Column(name = "order_detail_code")
    private String orderDetailCode;
    /**
     * 二维码
     */
    @Column(name = "order_detail_qr")
    private String orderDetailQr;
    private static final long serialVersionUID = 1L;

}