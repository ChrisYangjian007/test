package com.dalian.sea.model;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * 收货单
 * */
@Table(name = "pu_receive")
public class PuReceive implements Serializable {
    /**
     * 收货单ID
     */
    @Id
    @Column(name = "receive_id")
    private Long receiveId;

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
     * 是否已入库(1-入库,0-未入库)
     */
    @Column(name = "is_storage")
    private Byte isStorage;

    /**
     * 收货编号
     */
    @Column(name = "receive_no")
    private String receiveNo;

    /**
     * 发货人
     */
    @Column(name = "deliver_name")
    private String deliverName;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 收货时间
     */
    @Column(name = "deliver_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deliverDate;

    /**
     * 总重量
     */
    @Column(name = "total_weight")
    private BigDecimal totalWeight;

    /**
     * 备注1
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
     * 状态（1-正常，2-禁用，6-删除）
     */
    private Byte status;

    private static final long serialVersionUID = 1L;

    /**
     * 获取收货单ID
     *
     * @return receive_id - 收货单ID
     */
    public Long getReceiveId() {
        return receiveId;
    }

    /**
     * 设置收货单ID
     *
     * @param receiveId 收货单ID
     */
    public void setReceiveId(Long receiveId) {
        this.receiveId = receiveId;
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
     * 获取是否已入库(1-入库,0-未入库)
     *
     * @return is_storage - 是否已入库(1-入库,0-未入库)
     */
    public Byte getIsStorage() {
        return isStorage;
    }

    /**
     * 设置是否已入库(1-入库,0-未入库)
     *
     * @param isStorage 是否已入库(1-入库,0-未入库)
     */
    public void setIsStorage(Byte isStorage) {
        this.isStorage = isStorage;
    }

    /**
     * 获取收货编号
     *
     * @return receive_no - 收货编号
     */
    public String getReceiveNo() {
        return receiveNo;
    }

    /**
     * 设置收货编号
     *
     * @param receiveNo 收货编号
     */
    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    /**
     * 获取发货人
     *
     * @return deliver_name - 发货人
     */
    public String getDeliverName() {
        return deliverName;
    }

    /**
     * 设置发货人
     *
     * @param deliverName 发货人
     */
    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    /**
     * 获取收货人
     *
     * @return consignee - 收货人
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * 设置收货人
     *
     * @param consignee 收货人
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    /**
     * 获取收货时间
     *
     * @return deliver_date - 收货时间
     */
    public Date getDeliverDate() {
        return deliverDate;
    }

    /**
     * 设置收货时间
     *
     * @param deliverDate 收货时间
     */
    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    /**
     * 获取总重量
     *
     * @return total_weight - 总重量
     */
    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    /**
     * 设置总重量
     *
     * @param totalWeight 总重量
     */
    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
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
     * 获取创建者ID
     *
     * @return create_user_id - 创建者ID
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建者ID
     *
     * @param createUserId 创建者ID
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
     * 获取更新者ID
     *
     * @return update_user_id - 更新者ID
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 设置更新者ID
     *
     * @param updateUserId 更新者ID
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取状态（1-正常，2-禁用，3-删除）
     *
     * @return status - 状态（1-正常，2-禁用，3-删除）
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态（1-正常，2-禁用，3-删除）
     *
     * @param status 状态（1-正常，2-禁用，3-删除）
     */
    public void setStatus(Byte status) {
        this.status = status;
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
        PuReceive other = (PuReceive) that;
        return (this.getReceiveId() == null ? other.getReceiveId() == null : this.getReceiveId().equals(other.getReceiveId()))
            && (this.getEnterpriseId() == null ? other.getEnterpriseId() == null : this.getEnterpriseId().equals(other.getEnterpriseId()))
            && (this.getEnterpriseName() == null ? other.getEnterpriseName() == null : this.getEnterpriseName().equals(other.getEnterpriseName()))
            && (this.getIsStorage() == null ? other.getIsStorage() == null : this.getIsStorage().equals(other.getIsStorage()))
            && (this.getReceiveNo() == null ? other.getReceiveNo() == null : this.getReceiveNo().equals(other.getReceiveNo()))
            && (this.getDeliverName() == null ? other.getDeliverName() == null : this.getDeliverName().equals(other.getDeliverName()))
            && (this.getConsignee() == null ? other.getConsignee() == null : this.getConsignee().equals(other.getConsignee()))
            && (this.getDeliverDate() == null ? other.getDeliverDate() == null : this.getDeliverDate().equals(other.getDeliverDate()))
            && (this.getTotalWeight() == null ? other.getTotalWeight() == null : this.getTotalWeight().equals(other.getTotalWeight()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getListIndex() == null ? other.getListIndex() == null : this.getListIndex().equals(other.getListIndex()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReceiveId() == null) ? 0 : getReceiveId().hashCode());
        result = prime * result + ((getEnterpriseId() == null) ? 0 : getEnterpriseId().hashCode());
        result = prime * result + ((getEnterpriseName() == null) ? 0 : getEnterpriseName().hashCode());
        result = prime * result + ((getIsStorage() == null) ? 0 : getIsStorage().hashCode());
        result = prime * result + ((getReceiveNo() == null) ? 0 : getReceiveNo().hashCode());
        result = prime * result + ((getDeliverName() == null) ? 0 : getDeliverName().hashCode());
        result = prime * result + ((getConsignee() == null) ? 0 : getConsignee().hashCode());
        result = prime * result + ((getDeliverDate() == null) ? 0 : getDeliverDate().hashCode());
        result = prime * result + ((getTotalWeight() == null) ? 0 : getTotalWeight().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getListIndex() == null) ? 0 : getListIndex().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", receiveId=").append(receiveId);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", enterpriseName=").append(enterpriseName);
        sb.append(", isStorage=").append(isStorage);
        sb.append(", receiveNo=").append(receiveNo);
        sb.append(", deliverName=").append(deliverName);
        sb.append(", consignee=").append(consignee);
        sb.append(", deliverDate=").append(deliverDate);
        sb.append(", totalWeight=").append(totalWeight);
        sb.append(", remark=").append(remark);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", createDate=").append(createDate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}