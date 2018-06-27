package com.dalian.sea.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * 库存表明细
 * */
@Table(name = "zs_stock_detail")
public class ZsStockDetail implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "stock_detail_id")
    private Long stockDetailId;

    /**
     * 库存ID
     */
    @Column(name = "stock_id")
    private Long stockId;

    /**
     * 仓库ID
     */
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**
     * 农资是否是消耗品
     */
    @Column(name = "is_expend")
    private Byte isExpend;

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
     * 仓库名称
     */
    @Column(name = "warehouse_name")
    private String warehouseName;

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
     * 金额
     */
    private BigDecimal amount;

    /**
     * 相关ID
     */
    @Column(name = "related_id")
    private Long relatedId;

    /**
     * 1-采购,2-销售,3-入库,4-出库
     */
    @Column(name = "related_type")
    private Byte relatedType;

    /**
     * 是否原料(2-半成品,0-成品,1-农资)
     */
    @Column(name = "is_material")
    private Byte isMaterial;

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
     * 批次号
     */
    @Column(name = "batch_no")
    private String batchNo;

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
     * 产品状态(1-新鲜)
     */
    @Column(name = "product_status")
    private Byte productStatus;

    /**
     * 剩余状态(0-无剩余,1-还有剩余)
     */
    @Column(name = "rest_status")
    private Byte restStatus;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return stock_detail_id - 主键
     */
    public Long getStockDetailId() {
        return stockDetailId;
    }

    /**
     * 设置主键
     *
     * @param stockDetailId 主键
     */
    public void setStockDetailId(Long stockDetailId) {
        this.stockDetailId = stockDetailId;
    }

    /**
     * 获取库存ID
     *
     * @return stock_id - 库存ID
     */
    public Long getStockId() {
        return stockId;
    }

    /**
     * 设置库存ID
     *
     * @param stockId 库存ID
     */
    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    /**
     * 获取仓库ID
     *
     * @return warehouse_id - 仓库ID
     */
    public Long getWarehouseId() {
        return warehouseId;
    }

    /**
     * 设置仓库ID
     *
     * @param warehouseId 仓库ID
     */
    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * 获取农资是否是消耗品
     *
     * @return is_expend - 农资是否是消耗品
     */
    public Byte getIsExpend() {
        return isExpend;
    }

    /**
     * 设置农资是否是消耗品
     *
     * @param isExpend 农资是否是消耗品
     */
    public void setIsExpend(Byte isExpend) {
        this.isExpend = isExpend;
    }

    /**
     * 获取入库重量
     *
     * @return in_weight - 入库重量
     */
    public BigDecimal getInWeight() {
        return inWeight;
    }

    /**
     * 设置入库重量
     *
     * @param inWeight 入库重量
     */
    public void setInWeight(BigDecimal inWeight) {
        this.inWeight = inWeight;
    }

    /**
     * 获取出库重量
     *
     * @return out_weight - 出库重量
     */
    public BigDecimal getOutWeight() {
        return outWeight;
    }

    /**
     * 设置出库重量
     *
     * @param outWeight 出库重量
     */
    public void setOutWeight(BigDecimal outWeight) {
        this.outWeight = outWeight;
    }

    /**
     * 获取仓库名称
     *
     * @return warehouse_name - 仓库名称
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * 设置仓库名称
     *
     * @param warehouseName 仓库名称
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    /**
     * 获取公司主键
     *
     * @return company_id - 公司主键
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司主键
     *
     * @param companyId 公司主键
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取排序码
     *
     * @return list_index - 排序码
     */
    public Integer getListIndex() {
        return listIndex;
    }

    /**
     * 设置排序码
     *
     * @param listIndex 排序码
     */
    public void setListIndex(Integer listIndex) {
        this.listIndex = listIndex;
    }

    /**
     * 获取状态(1-正常,6-删除,2-禁用)
     *
     * @return status - 状态(1-正常,6-删除,2-禁用)
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态(1-正常,6-删除,2-禁用)
     *
     * @param status 状态(1-正常,6-删除,2-禁用)
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取金额
     *
     * @return amount - 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置金额
     *
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取相关ID
     *
     * @return related_id - 相关ID
     */
    public Long getRelatedId() {
        return relatedId;
    }

    /**
     * 设置相关ID
     *
     * @param relatedId 相关ID
     */
    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    /**
     * 获取1-采购,2-销售,3-入库,4-出库
     *
     * @return related_type - 1-采购,2-销售,3-入库,4-出库
     */
    public Byte getRelatedType() {
        return relatedType;
    }

    /**
     * 设置1-采购,2-销售,3-入库,4-出库
     *
     * @param relatedType 1-采购,2-销售,3-入库,4-出库
     */
    public void setRelatedType(Byte relatedType) {
        this.relatedType = relatedType;
    }

    /**
     * 获取是否原料(2-半成品,0-成品,1-农资)
     *
     * @return is_material - 是否原料(2-半成品,0-成品,1-农资)
     */
    public Byte getIsMaterial() {
        return isMaterial;
    }

    /**
     * 设置是否原料(2-半成品,0-成品,1-农资)
     *
     * @param isMaterial 是否原料(2-半成品,0-成品,1-农资)
     */
    public void setIsMaterial(Byte isMaterial) {
        this.isMaterial = isMaterial;
    }

    /**
     * 获取单位ID
     *
     * @return unit_id - 单位ID
     */
    public Long getUnitId() {
        return unitId;
    }

    /**
     * 设置单位ID
     *
     * @param unitId 单位ID
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    /**
     * 获取单位名称
     *
     * @return unit_name - 单位名称
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 设置单位名称
     *
     * @param unitName 单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * 获取产品ID
     *
     * @return product_id - 产品ID
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置产品ID
     *
     * @param productId 产品ID
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取产品名称
     *
     * @return product_name - 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     *
     * @param productName 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取操作人员ID
     *
     * @return create_user_id - 操作人员ID
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置操作人员ID
     *
     * @param createUserId 操作人员ID
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取批次号
     *
     * @return batch_no - 批次号
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * 设置批次号
     *
     * @param batchNo 批次号
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * 获取规格名称
     *
     * @return product_spec_name - 规格名称
     */
    public String getProductSpecName() {
        return productSpecName;
    }

    /**
     * 设置规格名称
     *
     * @param productSpecName 规格名称
     */
    public void setProductSpecName(String productSpecName) {
        this.productSpecName = productSpecName;
    }

    /**
     * 获取规格ID
     *
     * @return product_spec_id - 规格ID
     */
    public Long getProductSpecId() {
        return productSpecId;
    }

    /**
     * 设置规格ID
     *
     * @param productSpecId 规格ID
     */
    public void setProductSpecId(Long productSpecId) {
        this.productSpecId = productSpecId;
    }

    /**
     * 获取产品状态(1-新鲜)
     *
     * @return product_status - 产品状态(1-新鲜)
     */
    public Byte getProductStatus() {
        return productStatus;
    }

    /**
     * 设置产品状态(1-新鲜)
     *
     * @param productStatus 产品状态(1-新鲜)
     */
    public void setProductStatus(Byte productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * 获取剩余状态(0-无剩余,1-还有剩余)
     *
     * @return rest_status - 剩余状态(0-无剩余,1-还有剩余)
     */
    public Byte getRestStatus() {
        return restStatus;
    }

    /**
     * 设置剩余状态(0-无剩余,1-还有剩余)
     *
     * @param restStatus 剩余状态(0-无剩余,1-还有剩余)
     */
    public void setRestStatus(Byte restStatus) {
        this.restStatus = restStatus;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ZsStockDetail other = (ZsStockDetail) that;
        return (this.getStockDetailId() == null ? other.getStockDetailId() == null : this.getStockDetailId().equals(other.getStockDetailId()))
            && (this.getStockId() == null ? other.getStockId() == null : this.getStockId().equals(other.getStockId()))
            && (this.getWarehouseId() == null ? other.getWarehouseId() == null : this.getWarehouseId().equals(other.getWarehouseId()))
            && (this.getIsExpend() == null ? other.getIsExpend() == null : this.getIsExpend().equals(other.getIsExpend()))
            && (this.getInWeight() == null ? other.getInWeight() == null : this.getInWeight().equals(other.getInWeight()))
            && (this.getOutWeight() == null ? other.getOutWeight() == null : this.getOutWeight().equals(other.getOutWeight()))
            && (this.getWarehouseName() == null ? other.getWarehouseName() == null : this.getWarehouseName().equals(other.getWarehouseName()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getListIndex() == null ? other.getListIndex() == null : this.getListIndex().equals(other.getListIndex()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getRelatedId() == null ? other.getRelatedId() == null : this.getRelatedId().equals(other.getRelatedId()))
            && (this.getRelatedType() == null ? other.getRelatedType() == null : this.getRelatedType().equals(other.getRelatedType()))
            && (this.getIsMaterial() == null ? other.getIsMaterial() == null : this.getIsMaterial().equals(other.getIsMaterial()))
            && (this.getUnitId() == null ? other.getUnitId() == null : this.getUnitId().equals(other.getUnitId()))
            && (this.getUnitName() == null ? other.getUnitName() == null : this.getUnitName().equals(other.getUnitName()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getBatchNo() == null ? other.getBatchNo() == null : this.getBatchNo().equals(other.getBatchNo()))
            && (this.getProductSpecName() == null ? other.getProductSpecName() == null : this.getProductSpecName().equals(other.getProductSpecName()))
            && (this.getProductSpecId() == null ? other.getProductSpecId() == null : this.getProductSpecId().equals(other.getProductSpecId()))
            && (this.getProductStatus() == null ? other.getProductStatus() == null : this.getProductStatus().equals(other.getProductStatus()))
            && (this.getRestStatus() == null ? other.getRestStatus() == null : this.getRestStatus().equals(other.getRestStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStockDetailId() == null) ? 0 : getStockDetailId().hashCode());
        result = prime * result + ((getStockId() == null) ? 0 : getStockId().hashCode());
        result = prime * result + ((getWarehouseId() == null) ? 0 : getWarehouseId().hashCode());
        result = prime * result + ((getIsExpend() == null) ? 0 : getIsExpend().hashCode());
        result = prime * result + ((getInWeight() == null) ? 0 : getInWeight().hashCode());
        result = prime * result + ((getOutWeight() == null) ? 0 : getOutWeight().hashCode());
        result = prime * result + ((getWarehouseName() == null) ? 0 : getWarehouseName().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getListIndex() == null) ? 0 : getListIndex().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getRelatedId() == null) ? 0 : getRelatedId().hashCode());
        result = prime * result + ((getRelatedType() == null) ? 0 : getRelatedType().hashCode());
        result = prime * result + ((getIsMaterial() == null) ? 0 : getIsMaterial().hashCode());
        result = prime * result + ((getUnitId() == null) ? 0 : getUnitId().hashCode());
        result = prime * result + ((getUnitName() == null) ? 0 : getUnitName().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getBatchNo() == null) ? 0 : getBatchNo().hashCode());
        result = prime * result + ((getProductSpecName() == null) ? 0 : getProductSpecName().hashCode());
        result = prime * result + ((getProductSpecId() == null) ? 0 : getProductSpecId().hashCode());
        result = prime * result + ((getProductStatus() == null) ? 0 : getProductStatus().hashCode());
        result = prime * result + ((getRestStatus() == null) ? 0 : getRestStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stockDetailId=").append(stockDetailId);
        sb.append(", stockId=").append(stockId);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", isExpend=").append(isExpend);
        sb.append(", inWeight=").append(inWeight);
        sb.append(", outWeight=").append(outWeight);
        sb.append(", warehouseName=").append(warehouseName);
        sb.append(", companyId=").append(companyId);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", status=").append(status);
        sb.append(", amount=").append(amount);
        sb.append(", relatedId=").append(relatedId);
        sb.append(", relatedType=").append(relatedType);
        sb.append(", isMaterial=").append(isMaterial);
        sb.append(", unitId=").append(unitId);
        sb.append(", unitName=").append(unitName);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", createDate=").append(createDate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", batchNo=").append(batchNo);
        sb.append(", productSpecName=").append(productSpecName);
        sb.append(", productSpecId=").append(productSpecId);
        sb.append(", productStatus=").append(productStatus);
        sb.append(", restStatus=").append(restStatus);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}