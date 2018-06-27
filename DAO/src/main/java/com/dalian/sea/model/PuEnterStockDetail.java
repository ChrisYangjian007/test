package com.dalian.sea.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "pu_enter_stock_detail")
@Data
public class PuEnterStockDetail implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "enter_stock_detail_id")
    private Long enterStockDetailId;

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
     * 入库重量
     */
    @Column(name = "in_weight")
    private BigDecimal inWeight;

    /**
     * 入库单位ID
     */
    @Column(name = "unit_id")
    private Long unitId;

    /**
     * 入库单位名称
     */
    @Column(name = "unit_name")
    private String unitName;

    /**
     * 入库规格名称
     */
    @Column(name = "product_spec_name")
    private String productSpecName;

    /**
     * 入库规格ID
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
     * 入库单明细
     */
    @Column(name = "enter_stock_id")
    private Long enterStockId;

    /**
     * 是否原料(2-半成品,0-成品,1-农资)
     */
    @Column(name = "is_material")
    private Long isMaterial;

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
     * 剩余状态
     */
    @Column(name = "rest_status")
    private Byte restStatus;

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
     * 备注
     */
    private String remark;

    /**
     * 操作人员ID
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 修改用户主键
     */
    @Column(name = "update_user_id")
    private Long updateUserId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 状态(1-正常,6-删除,2-禁用)
     */
    @Column(name = "status")
    private Byte status;

    /**
     *入库编号
     */
    @Column(name = "enter_no")
    private String enterNo;

    /**
     * 成品批次号
     */
    @Column(name = "goods_batch_no")
    private String goodsBatchNo;

    /**
     * 规格名称
     */
    @Column(name = "enter_product_spec_name")
    private String enterProductSpecName;

    /**
     * 原料数量
     */
    @Column(name = "material_weight")
    private BigDecimal materialWeight;

    /**
     * 单位ID
     */
    @Column(name = "enter_unit_id")
    private Long enterUnitId;

    /**
     * 单位名称
     */
    @Column(name = "enter_unit_name")
    private String enterUnitName;

    /**
     * 是否入活参库
     */
    @Column(name = "is_sea_cucumber")
    private Byte isSeaCucumber;

    /**
     * 入库箱码
     */
    @Column(name = "box_code")
    private String boxCode;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", enterStockDetailId=").append(enterStockDetailId);
        sb.append(", companyId=").append(companyId);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", inWeight=").append(inWeight);
        sb.append(", unitId=").append(unitId);
        sb.append(", unitName=").append(unitName);
        sb.append(", productSpecName=").append(productSpecName);
        sb.append(", productSpecId=").append(productSpecId);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", enterStockId=").append(enterStockId);
        sb.append(", isMaterial=").append(isMaterial);
        sb.append(", batchNo=").append(batchNo);
        sb.append(", productStatus=").append(productStatus);
        sb.append(", restStatus=").append(restStatus);
        sb.append(", relatedId=").append(relatedId);
        sb.append(", relatedType=").append(relatedType);
        sb.append(", remark=").append(remark);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}