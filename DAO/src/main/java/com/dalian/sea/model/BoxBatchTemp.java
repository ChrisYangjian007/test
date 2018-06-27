package com.dalian.sea.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_box_batch_temp")
public class BoxBatchTemp implements Serializable {
    @Id
    @Column(name = "box_batch_temp_id")
    private Integer boxBatchTempId;

    @Column(name = "create_date")
    private Date createDate;

    /**
     * 二维码
     */
    @Column(name = "qr_code")
    private String qrCode;

    /**
     * 箱码
     */
    @Column(name = "box_code")
    private String boxCode;

    /**
     * 标签规则
     */
    @Column(name = "qr_rule_name")
    private String qrRuleName;

    /**
     * 明码
     */
    @Column(name = "qr_clear_code")
    private String qrClearCode;

    /**
     * 批次码
     */
    @Column(name = "batch_code")
    private String batchCode;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    @Column(name = "operation_time")
    private String operationTime;

    private Byte status;

    /**
     * 随机码
     */
    private String random;

    private static final long serialVersionUID = 1L;

    /**
     * @return box_batch_temp_id
     */
    public Integer getBoxBatchTempId() {
        return boxBatchTempId;
    }

    /**
     * @param boxBatchTempId
     */
    public void setBoxBatchTempId(Integer boxBatchTempId) {
        this.boxBatchTempId = boxBatchTempId;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取二维码
     *
     * @return qr_code - 二维码
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * 设置二维码
     *
     * @param qrCode 二维码
     */
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * 获取箱码
     *
     * @return box_code - 箱码
     */
    public String getBoxCode() {
        return boxCode;
    }

    /**
     * 设置箱码
     *
     * @param boxCode 箱码
     */
    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    /**
     * 获取标签规则
     *
     * @return qr_rule_name - 标签规则
     */
    public String getQrRuleName() {
        return qrRuleName;
    }

    /**
     * 设置标签规则
     *
     * @param qrRuleName 标签规则
     */
    public void setQrRuleName(String qrRuleName) {
        this.qrRuleName = qrRuleName;
    }

    /**
     * 获取明码
     *
     * @return qr_clear_code - 明码
     */
    public String getQrClearCode() {
        return qrClearCode;
    }

    /**
     * 设置明码
     *
     * @param qrClearCode 明码
     */
    public void setQrClearCode(String qrClearCode) {
        this.qrClearCode = qrClearCode;
    }

    /**
     * 获取批次码
     *
     * @return batch_code - 批次码
     */
    public String getBatchCode() {
        return batchCode;
    }

    /**
     * 设置批次码
     *
     * @param batchCode 批次码
     */
    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    /**
     * 获取操作人
     *
     * @return operator - 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作人
     *
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取操作时间
     *
     * @return operation_time - 操作时间
     */
    public String getOperationTime() {
        return operationTime;
    }

    /**
     * 设置操作时间
     *
     * @param operationTime 操作时间
     */
    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    /**
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取随机码
     *
     * @return random - 随机码
     */
    public String getRandom() {
        return random;
    }

    /**
     * 设置随机码
     *
     * @param random 随机码
     */
    public void setRandom(String random) {
        this.random = random;
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
        BoxBatchTemp other = (BoxBatchTemp) that;
        return (this.getBoxBatchTempId() == null ? other.getBoxBatchTempId() == null : this.getBoxBatchTempId().equals(other.getBoxBatchTempId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getQrCode() == null ? other.getQrCode() == null : this.getQrCode().equals(other.getQrCode()))
            && (this.getBoxCode() == null ? other.getBoxCode() == null : this.getBoxCode().equals(other.getBoxCode()))
            && (this.getQrRuleName() == null ? other.getQrRuleName() == null : this.getQrRuleName().equals(other.getQrRuleName()))
            && (this.getQrClearCode() == null ? other.getQrClearCode() == null : this.getQrClearCode().equals(other.getQrClearCode()))
            && (this.getBatchCode() == null ? other.getBatchCode() == null : this.getBatchCode().equals(other.getBatchCode()))
            && (this.getOperator() == null ? other.getOperator() == null : this.getOperator().equals(other.getOperator()))
            && (this.getOperationTime() == null ? other.getOperationTime() == null : this.getOperationTime().equals(other.getOperationTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRandom() == null ? other.getRandom() == null : this.getRandom().equals(other.getRandom()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBoxBatchTempId() == null) ? 0 : getBoxBatchTempId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getQrCode() == null) ? 0 : getQrCode().hashCode());
        result = prime * result + ((getBoxCode() == null) ? 0 : getBoxCode().hashCode());
        result = prime * result + ((getQrRuleName() == null) ? 0 : getQrRuleName().hashCode());
        result = prime * result + ((getQrClearCode() == null) ? 0 : getQrClearCode().hashCode());
        result = prime * result + ((getBatchCode() == null) ? 0 : getBatchCode().hashCode());
        result = prime * result + ((getOperator() == null) ? 0 : getOperator().hashCode());
        result = prime * result + ((getOperationTime() == null) ? 0 : getOperationTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRandom() == null) ? 0 : getRandom().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", boxBatchTempId=").append(boxBatchTempId);
        sb.append(", createDate=").append(createDate);
        sb.append(", qrCode=").append(qrCode);
        sb.append(", boxCode=").append(boxCode);
        sb.append(", qrRuleName=").append(qrRuleName);
        sb.append(", qrClearCode=").append(qrClearCode);
        sb.append(", batchCode=").append(batchCode);
        sb.append(", operator=").append(operator);
        sb.append(", operationTime=").append(operationTime);
        sb.append(", status=").append(status);
        sb.append(", random=").append(random);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}