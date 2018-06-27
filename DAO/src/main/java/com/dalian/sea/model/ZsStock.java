package com.dalian.sea.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * 库存表
 * */
@Table(name = "zs_stock")
@Data
public class ZsStock implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "stock_id")
    private Long stockId;

    /**
     * 相关ID
     */
    @Column(name = "related_id")
    private Long relatedId;

    /**
     * 农资是否是消耗品
     */
    @Column(name = "is_expend")
    private Byte isExpend;

    /**
     * 剩余重量
     */
    @Column(name = "weight")
    private BigDecimal weight;

    /**
     * 入库重量
     */
    @Column(name = "in_weight")
    private BigDecimal inWeight;

    /**
     * 出库重量
     */
    @Column(name = "out_weight")
    private BigDecimal outWeight;

    /**
     * 产品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 产品名
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 公司主键
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 排序码
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 状态(1-正常,6-删除,2-禁用)
     */
    private Byte status;

    /**
     * 是否原料(2-半成品,0-成品,1-农资,3-辅料,5-包材)
     */
    @Column(name = "is_material")
    private Long isMaterial;

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
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 操作人员ID
     */
    @Column(name = "create_user_id")
    private Long createUserId;

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
     * 批次号
     */
    @Column(name = "batch_no")
    private String batchNo;

    /**
     * 产品状态(1-新鲜)
     */
    @Column(name = "product_status")
    private Long productStatus;

    /**
     * 规格名称
     */
    @Column(name = "product_spec_name")
    private String productSpecName;

    /**
     * 规格ID
     */
    @Column(name = "product_spec_id")
    private Long productSpecId;

    /**
     * 仓库ID
     */
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**
     * 时间预警
     */
    @Column(name = "time_warn")
    private Integer timeWarn;

    /**
     * 库存预警
     */
    @Column(name = "stock_warn")
    private Integer stockWarn;


    /**
     * 备注
     */
    private String remark;

    /**
     * 剩余状态
     */
    @Column(name = "rest_status")
    private Byte restStatus;

    /**
     * 最新入库时间
     */
    @Column(name = "in_create_date")
    private Date inCreateDate;

    private static final long serialVersionUID = 1L;

}