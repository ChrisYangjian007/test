package com.dalian.sea.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 收货单明细
 *
 * @author 杨文波
 * @date 2018/01/25
 */
@Table(name = "pu_receive_detail")
@Data
public class PuReceiveDetail implements Serializable {
    /**
     * 收货单明细ID
     */
    @Id
    @Column(name = "receive_detail_id")
    private Long receiveDetailId;

    /**
     * 批次号
     */
    @Column(name = "batch_no")
    private String batchNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
     * 产品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 货物类型ID
     */
    @Column(name = "goods_type_id")
    private Long goodsTypeId;

    /**
     * 货物类型
     */
    @Column(name = "goods_type")
    private String goodsType;

    /**
     * 规格名称
     */
    @Column(name = "product_spec_name")
    private String productSpecName;

    /**
     * 规格ID
     */
    /*@Column(name = "product_spec_id")
    private Long productSpecId;*/

    /**
     * 产品状态(1-新鲜)
     */
    @Column(name = "product_status")
    private Byte productStatus;

    /**
     * 单位ID
     */
    @Column(name = "unit_id")
    private Long unitId;

    /**
     * 单位名称
     */
    @Column(name = "unit_name")
    private String unitName;

    /**
     * 重量
     */
    private BigDecimal weight;

    /**
     * 货物状态(1-待处理,2-已退货,3-已入库，4-未打印）
     */
    @Column(name = "receipt_status")
    private Byte receiptStatus;

    /**
     * 检验结果（1-待检验，2-合格，3-不合格）默认为待检验
     */
    @Column(name = "test_result")
    private Byte testResult;

    /**
     * 收货单ID
     */
    @Column(name = "receive_id")
    private Long receiveId;

    /**
     * 退货原因
     */
    @Column(name = "return_reason")
    private String returnReason;

    /**
     * 处理方案
     */
    @Column(name = "dispose_plan")
    private String disposePlan;

    /**
     * 经手人
     */
    @Column(name = "handler")
    private String handler;

    /**
     * 退货时间
     */
    @Column(name = "return_date")
    private Date returnDate;

    /**
     * 备注2
     */
    @Column(name = "note")
    private String note;

    /**
     * 退货编号
     */
    @Column(name = "return_no")
    private String returnNo;

    /**
     * 原始id
     */
    @Column(name="original_id")
    private Long originalId;

    /**
     * 1:未打印,2:已打印
     * */
    @Column(name = "is_print")
    private Byte isPrint;

    /**
     * 批次二维码号
     * */
    @Column(name = "qrcode")
    private String qrcode;

    private static final long serialVersionUID = 1L;


}