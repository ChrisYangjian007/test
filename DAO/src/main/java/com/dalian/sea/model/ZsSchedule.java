package com.dalian.sea.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author YH
 */
@Table(name = "zs_schedule")
@Data
public class ZsSchedule implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "schedule_id")
    private Long scheduleId;

    /**
     * 状态(1-正常,6-删除,2-禁用)
     */
    private Byte status;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 创建用户
     */
    @Column(name = "create_user")
    private String createUser;

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
     * 排序
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 产品大类id
     */
    @Column(name = "product_type_id")
    private Long productTypeId;

    /**
     * 产品大类名称
     */
    @Column(name = "product_type")
    private String productType;

    /**
     * 规格
     */
    @Column(name = "product_spec")
    private String productSpec;

    /**
     * 数量
     */
    private BigDecimal amount;

    /**
     * 单位id
     */
    @Column(name = "unit_id")
    private Long unitId;

    /**
     * 单位名称
     */
    @Column(name = "unit_name")
    private String unitName;

    private static final long serialVersionUID = 1L;
}