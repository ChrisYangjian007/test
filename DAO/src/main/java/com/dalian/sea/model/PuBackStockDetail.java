package com.dalian.sea.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * 退库单明细
 * */
@Table(name = "pu_back_stock_detail")
public class PuBackStockDetail implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "back_stock_detail_id")
    private Long backStockDetailId;

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
     * 是否原料(2-半成品,0-成品,1-农资)
     */
    @Column(name = "is_material")
    private Byte isMaterial;

    /**
     * 产品状态(1-新鲜)
     */
    @Column(name = "product_status")
    private Byte productStatus;

    /**
     * 重量
     */
    private BigDecimal weight;

    /**
     * 批次号
     */
    @Column(name = "batch_no")
    private String batchNo;

    /**
     * 货物状态(1-检验中,2-待检验,3-已退货,4-退货中,5-已入库,6-加工中,7-不合格)
     */
    @Column(name = "receipt_status")
    private Byte receiptStatus;

    /**
     * 退库主键
     */
    @Column(name = "back_stock_id")
    private Long backStockId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return back_stock_detail_id - 主键
     */
    public Long getBackStockDetailId() {
        return backStockDetailId;
    }

    /**
     * 设置主键
     *
     * @param backStockDetailId 主键
     */
    public void setBackStockDetailId(Long backStockDetailId) {
        this.backStockDetailId = backStockDetailId;
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
     * 获取更新时间
     *
     * @return update_date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取修改用户主键
     *
     * @return update_user_id - 修改用户主键
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 设置修改用户主键
     *
     * @param updateUserId 修改用户主键
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
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

    /**
     * 获取公司ID
     *
     * @return company_id - 公司ID
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司ID
     *
     * @param companyId 公司ID
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取排序
     *
     * @return list_index - 排序
     */
    public Integer getListIndex() {
        return listIndex;
    }

    /**
     * 设置排序
     *
     * @param listIndex 排序
     */
    public void setListIndex(Integer listIndex) {
        this.listIndex = listIndex;
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
     * 获取重量
     *
     * @return weight - 重量
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * 设置重量
     *
     * @param weight 重量
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
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
     * 获取货物状态(1-检验中,2-待检验,3-已退货,4-退货中,5-已入库,6-加工中,7-不合格)
     *
     * @return receipt_status - 货物状态(1-检验中,2-待检验,3-已退货,4-退货中,5-已入库,6-加工中,7-不合格)
     */
    public Byte getReceiptStatus() {
        return receiptStatus;
    }

    /**
     * 设置货物状态(1-检验中,2-待检验,3-已退货,4-退货中,5-已入库,6-加工中,7-不合格)
     *
     * @param receiptStatus 货物状态(1-检验中,2-待检验,3-已退货,4-退货中,5-已入库,6-加工中,7-不合格)
     */
    public void setReceiptStatus(Byte receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    /**
     * 获取退库主键
     *
     * @return back_stock_id - 退库主键
     */
    public Long getBackStockId() {
        return backStockId;
    }

    /**
     * 设置退库主键
     *
     * @param backStockId 退库主键
     */
    public void setBackStockId(Long backStockId) {
        this.backStockId = backStockId;
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
        PuBackStockDetail other = (PuBackStockDetail) that;
        return (this.getBackStockDetailId() == null ? other.getBackStockDetailId() == null : this.getBackStockDetailId().equals(other.getBackStockDetailId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getListIndex() == null ? other.getListIndex() == null : this.getListIndex().equals(other.getListIndex()))
            && (this.getUnitId() == null ? other.getUnitId() == null : this.getUnitId().equals(other.getUnitId()))
            && (this.getUnitName() == null ? other.getUnitName() == null : this.getUnitName().equals(other.getUnitName()))
            && (this.getProductSpecName() == null ? other.getProductSpecName() == null : this.getProductSpecName().equals(other.getProductSpecName()))
            && (this.getProductSpecId() == null ? other.getProductSpecId() == null : this.getProductSpecId().equals(other.getProductSpecId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getWarehouseId() == null ? other.getWarehouseId() == null : this.getWarehouseId().equals(other.getWarehouseId()))
            && (this.getIsMaterial() == null ? other.getIsMaterial() == null : this.getIsMaterial().equals(other.getIsMaterial()))
            && (this.getProductStatus() == null ? other.getProductStatus() == null : this.getProductStatus().equals(other.getProductStatus()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
            && (this.getBatchNo() == null ? other.getBatchNo() == null : this.getBatchNo().equals(other.getBatchNo()))
            && (this.getReceiptStatus() == null ? other.getReceiptStatus() == null : this.getReceiptStatus().equals(other.getReceiptStatus()))
            && (this.getBackStockId() == null ? other.getBackStockId() == null : this.getBackStockId().equals(other.getBackStockId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBackStockDetailId() == null) ? 0 : getBackStockDetailId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getListIndex() == null) ? 0 : getListIndex().hashCode());
        result = prime * result + ((getUnitId() == null) ? 0 : getUnitId().hashCode());
        result = prime * result + ((getUnitName() == null) ? 0 : getUnitName().hashCode());
        result = prime * result + ((getProductSpecName() == null) ? 0 : getProductSpecName().hashCode());
        result = prime * result + ((getProductSpecId() == null) ? 0 : getProductSpecId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getWarehouseId() == null) ? 0 : getWarehouseId().hashCode());
        result = prime * result + ((getIsMaterial() == null) ? 0 : getIsMaterial().hashCode());
        result = prime * result + ((getProductStatus() == null) ? 0 : getProductStatus().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getBatchNo() == null) ? 0 : getBatchNo().hashCode());
        result = prime * result + ((getReceiptStatus() == null) ? 0 : getReceiptStatus().hashCode());
        result = prime * result + ((getBackStockId() == null) ? 0 : getBackStockId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", backStockDetailId=").append(backStockDetailId);
        sb.append(", createDate=").append(createDate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", companyId=").append(companyId);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", unitId=").append(unitId);
        sb.append(", unitName=").append(unitName);
        sb.append(", productSpecName=").append(productSpecName);
        sb.append(", productSpecId=").append(productSpecId);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", isMaterial=").append(isMaterial);
        sb.append(", productStatus=").append(productStatus);
        sb.append(", weight=").append(weight);
        sb.append(", batchNo=").append(batchNo);
        sb.append(", receiptStatus=").append(receiptStatus);
        sb.append(", backStockId=").append(backStockId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}