package com.dalian.sea.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "return_goods")
@Data
public class ReturnGoods implements Serializable {
    /**
     * 主键
     */
    @Column(name = "return_goods_detail_id")
    private Long returnGoodsDetailId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 创建对象id
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 修改人id
     */
    @Column(name = "update_user_id")
    private Long updateUserId;

    /**
     * 状态(1-正常,6-删除,2-禁用)
     */
    private Byte status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 货物类型id
     */
    @Column(name = "goods_type_id")
    private Byte goodsTypeId;

    /**
     * 货物类型名称
     */
    @Column(name = "goods_type")
    private String goodsType;

    /**
     * 产品id
     */
    @Column(name = "product_id")
    private Long productId;

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
     * 批次号
     */
    @Column(name = "batch_no")
    private String batchNo;

    /**
     * 提货时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "take_date")
    private Date takeDate;

    /**
     * 返货时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "return_date")
    private Date returnDate;

    /**
     * 卡号
     */
    @Column(name = "card_number")
    private String cardNumber;

    /**
     * 顾客地址
     */
    @Column(name = "customer_address")
    private String customerAddress;

    /**
     * 快递单号
     */
    @Column(name = "express_number")
    private String expressNumber;

    /**
     * 顾客姓名
     */
    @Column(name = "customer_name")
    private String customerName;

    /**
     * 不合格情况描述
     */
    @Column(name = "unqualified_description")
    private String unqualifiedDescription;

    /**
     * 检察员一
     */
    @Column(name = "inspector_one")
    private String inspectorOne;

    /**
     * 检查日期一
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "check_date_one")
    private Date checkDateOne;

    /**
     * 处理措施
     */
    @Column(name = "treatment_measures")
    private String treatmentMeasures;

    /**
     * 检察员二
     */
    @Column(name = "inspector_two")
    private String inspectorTwo;

    /**
     * 检查日期二
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "check_date_two")
    private Date checkDateTwo;

    /**
     * 不合格性质
     */
    private String nonconformance;

    /**
     * 处置方式
     */
    @Column(name = "disposal_way")
    private String disposalWay;

    /**
     * 主持人
     */
    private String host;

    /**
     * 参加人员
     */
    private String participants;

    /**
     * 图片
     */
    private String iamges;

    /**
     * 操作人
     */
    private String operator;

    private static final long serialVersionUID = 1L;
}