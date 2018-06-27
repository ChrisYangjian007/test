package com.dalian.sea.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 退库单
 * */
@Table(name = "pu_back_stock")
public class PuBackStock implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "back_stock_id")
    private Long backStockId;

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
     * 退货时间
     */
    @Column(name = "back_date")
    private Date backDate;

    /**
     * 仓库ID
     */
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**
     * 相关ID
     */
    @Column(name = "related_id")
    private Long relatedId;

    /**
     * 1-采购,2-销售,3-入库,4-出库,10-收货退货,11-仓库退货
     */
    @Column(name = "related_type")
    private Byte relatedType;

    /**
     * 供应商ID
     */
    @Column(name = "enterprise_id")
    private Long enterpriseId;

    /**
     * 供应商名称
     */
    @Column(name = "enterprise_name")
    private String enterpriseName;

    /**
     * 退货原因
     */
    @Column(name = "return_reason")
    private String returnReason;

    /**
     * 处理方案
     */
    @Column(name = "processing_scheme")
    private String processingScheme;

    /**
     * 经手人
     */
    private String brokerage;

    /**
     * 退货编号
     */
    @Column(name = "back_no")
    private String backNo;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return back_stock_id - 主键
     */
    public Long getBackStockId() {
        return backStockId;
    }

    /**
     * 设置主键
     *
     * @param backStockId 主键
     */
    public void setBackStockId(Long backStockId) {
        this.backStockId = backStockId;
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
     * 获取退货时间
     *
     * @return back_date - 退货时间
     */
    public Date getBackDate() {
        return backDate;
    }

    /**
     * 设置退货时间
     *
     * @param backDate 退货时间
     */
    public void setBackDate(Date backDate) {
        this.backDate = backDate;
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
     * 获取1-采购,2-销售,3-入库,4-出库,10-收货退货,11-仓库退货
     *
     * @return related_type - 1-采购,2-销售,3-入库,4-出库,10-收货退货,11-仓库退货
     */
    public Byte getRelatedType() {
        return relatedType;
    }

    /**
     * 设置1-采购,2-销售,3-入库,4-出库,10-收货退货,11-仓库退货
     *
     * @param relatedType 1-采购,2-销售,3-入库,4-出库,10-收货退货,11-仓库退货
     */
    public void setRelatedType(Byte relatedType) {
        this.relatedType = relatedType;
    }

    /**
     * 获取供应商ID
     *
     * @return enterprise_id - 供应商ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 设置供应商ID
     *
     * @param enterpriseId 供应商ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 获取供应商名称
     *
     * @return enterprise_name - 供应商名称
     */
    public String getEnterpriseName() {
        return enterpriseName;
    }

    /**
     * 设置供应商名称
     *
     * @param enterpriseName 供应商名称
     */
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    /**
     * 获取退货原因
     *
     * @return return_reason - 退货原因
     */
    public String getReturnReason() {
        return returnReason;
    }

    /**
     * 设置退货原因
     *
     * @param returnReason 退货原因
     */
    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    /**
     * 获取处理方案
     *
     * @return processing_scheme - 处理方案
     */
    public String getProcessingScheme() {
        return processingScheme;
    }

    /**
     * 设置处理方案
     *
     * @param processingScheme 处理方案
     */
    public void setProcessingScheme(String processingScheme) {
        this.processingScheme = processingScheme;
    }

    /**
     * 获取经手人
     *
     * @return brokerage - 经手人
     */
    public String getBrokerage() {
        return brokerage;
    }

    /**
     * 设置经手人
     *
     * @param brokerage 经手人
     */
    public void setBrokerage(String brokerage) {
        this.brokerage = brokerage;
    }

    /**
     * 获取退货编号
     *
     * @return back_no - 退货编号
     */
    public String getBackNo() {
        return backNo;
    }

    /**
     * 设置退货编号
     *
     * @param backNo 退货编号
     */
    public void setBackNo(String backNo) {
        this.backNo = backNo;
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
        PuBackStock other = (PuBackStock) that;
        return (this.getBackStockId() == null ? other.getBackStockId() == null : this.getBackStockId().equals(other.getBackStockId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getListIndex() == null ? other.getListIndex() == null : this.getListIndex().equals(other.getListIndex()))
            && (this.getBackDate() == null ? other.getBackDate() == null : this.getBackDate().equals(other.getBackDate()))
            && (this.getWarehouseId() == null ? other.getWarehouseId() == null : this.getWarehouseId().equals(other.getWarehouseId()))
            && (this.getRelatedId() == null ? other.getRelatedId() == null : this.getRelatedId().equals(other.getRelatedId()))
            && (this.getRelatedType() == null ? other.getRelatedType() == null : this.getRelatedType().equals(other.getRelatedType()))
            && (this.getEnterpriseId() == null ? other.getEnterpriseId() == null : this.getEnterpriseId().equals(other.getEnterpriseId()))
            && (this.getEnterpriseName() == null ? other.getEnterpriseName() == null : this.getEnterpriseName().equals(other.getEnterpriseName()))
            && (this.getReturnReason() == null ? other.getReturnReason() == null : this.getReturnReason().equals(other.getReturnReason()))
            && (this.getProcessingScheme() == null ? other.getProcessingScheme() == null : this.getProcessingScheme().equals(other.getProcessingScheme()))
            && (this.getBrokerage() == null ? other.getBrokerage() == null : this.getBrokerage().equals(other.getBrokerage()))
            && (this.getBackNo() == null ? other.getBackNo() == null : this.getBackNo().equals(other.getBackNo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBackStockId() == null) ? 0 : getBackStockId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getListIndex() == null) ? 0 : getListIndex().hashCode());
        result = prime * result + ((getBackDate() == null) ? 0 : getBackDate().hashCode());
        result = prime * result + ((getWarehouseId() == null) ? 0 : getWarehouseId().hashCode());
        result = prime * result + ((getRelatedId() == null) ? 0 : getRelatedId().hashCode());
        result = prime * result + ((getRelatedType() == null) ? 0 : getRelatedType().hashCode());
        result = prime * result + ((getEnterpriseId() == null) ? 0 : getEnterpriseId().hashCode());
        result = prime * result + ((getEnterpriseName() == null) ? 0 : getEnterpriseName().hashCode());
        result = prime * result + ((getReturnReason() == null) ? 0 : getReturnReason().hashCode());
        result = prime * result + ((getProcessingScheme() == null) ? 0 : getProcessingScheme().hashCode());
        result = prime * result + ((getBrokerage() == null) ? 0 : getBrokerage().hashCode());
        result = prime * result + ((getBackNo() == null) ? 0 : getBackNo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", backStockId=").append(backStockId);
        sb.append(", createDate=").append(createDate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", companyId=").append(companyId);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", backDate=").append(backDate);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", relatedId=").append(relatedId);
        sb.append(", relatedType=").append(relatedType);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", enterpriseName=").append(enterpriseName);
        sb.append(", returnReason=").append(returnReason);
        sb.append(", processingScheme=").append(processingScheme);
        sb.append(", brokerage=").append(brokerage);
        sb.append(", backNo=").append(backNo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}