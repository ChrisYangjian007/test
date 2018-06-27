package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "receive_test_detailed")
@Data
public class ReceiveTestDetailed implements Serializable {
    /**
     * 收货单明细ID
     */
    @Id
    @Column(name = "receive_test_detailed_id")
    private Long receiveTestDetailedId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 创建者ID
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 更新者ID
     */
    @Column(name = "update_user_id")
    private Long updateUserId;

    /**
     * 状态（1-正常，2-禁用，6-删除，5-进行中）
     */
    private Byte status;

    /**
     * 货物类型ID
     */
    @Column(name = "goods_type_id")
    private Long goodsTypeId;

    /**
     * 产品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 收货单明细ID
     */
    @Column(name = "receive_detail_id")
    private Long receiveDetailId;

    /**
     * goodsType
     */
    @Column(name = "goods_type")
    private String goodsType;

    /**
     * batchNo
     */
    @Column(name = "batch_no")
    private String batchNo;

    /**
     * productName
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * productSpecName
     */
    @Column(name = "product_spec_name")
    private String productSpecName;

    /**
     * weight
     */
    private BigDecimal weight;

    /**
     * unitName
     */
    @Column(name = "unit_name")
    private String unitName;

    /**
     * 收货单ID
     */
    @Column(name = "receive_id")
    private Long receiveId;

    private static final long serialVersionUID = 1L;
   }