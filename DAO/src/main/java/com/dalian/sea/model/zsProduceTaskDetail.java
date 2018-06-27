package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * @author YH
 */
@Table(name = "zs_produce_task_detail")
@Data
public class zsProduceTaskDetail implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "produce_task_detail_id")
    private Long produceTaskDetailId;

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
     * 状态(1-正常,6-删除,2-禁用)
     */
    private Byte status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 公司ID
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 排序
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 重量
     */
    private BigDecimal weight;

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
     * 仓库ID
     */
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**
     * 生产任务id
     */
    @Column(name = "produce_task_id")
    private Long produceTaskId;

    /**
     * 是否原料(2-半成品,0-成品,1-农资)
     */
    @Column(name = "is_material")
    private Long isMaterial;

    /**
     * 产品状态(1-新鲜)
     */
    @Column(name = "product_status")
    private Long productStatus;

    /**
     * 库存id
     */
    @Column(name="stock_id")
    private Long stockId;

    private static final long serialVersionUID = 1L;
}