package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sa_leave_stock_detail")
@Data
public class SaLeaveStockDetail implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "leave_stock_detail_id")
    private Long leaveStockDetailId;

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
     * 出库重量
     */
    @Column(name = "out_weight")
    private BigDecimal outWeight;

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
     * 批次号
     */
    @Column(name = "batch_no")
    private String batchNo;

    /**
     * 出库单id
     */
    @Column(name = "leave_id")
    private Long leaveId;

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
     * 相关ID
     */
    @Column(name = "related_id")
    private Long relatedId;

    /**
     * 1-采购,2-销售,3-入库,4-出库,5-生产任务
     */
    @Column(name = "related_type")
    private Byte relatedType;

    /**
     *损耗id
     */
    @Column(name="loss")
    private String loss;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", leaveStockDetailId=").append(leaveStockDetailId);
        sb.append(", createDate=").append(createDate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", companyId=").append(companyId);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", outWeight=").append(outWeight);
        sb.append(", unitId=").append(unitId);
        sb.append(", unitName=").append(unitName);
        sb.append(", productSpecName=").append(productSpecName);
        sb.append(", productSpecId=").append(productSpecId);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", leaveId=").append(leaveId);
        sb.append(", isMaterial=").append(isMaterial);
        sb.append(", productStatus=").append(productStatus);
        sb.append(", relatedId=").append(relatedId);
        sb.append(", relatedType=").append(relatedType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}