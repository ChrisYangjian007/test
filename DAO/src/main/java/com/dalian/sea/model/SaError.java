package com.dalian.sea.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sa_error")
public class SaError implements Serializable {
    @Id
    @Column(name = "error_id")
    private Long errorId;

    @Column(name = "error_code")
    private String errorCode;

    @Column(name = "error_reason")
    private String errorReason;

    @Column(name = "error_date")
    private String errorDate;

    @Column(name = "create_date")
    private Date createDate;

    private Byte status;

    @Column(name = "create_user_name")
    private String createUserName;

    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 类型
     */
    private String type;

    private static final long serialVersionUID = 1L;

    /**
     * @return error_id
     */
    public Long getErrorId() {
        return errorId;
    }

    /**
     * @param errorId
     */
    public void setErrorId(Long errorId) {
        this.errorId = errorId;
    }

    /**
     * @return error_code
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return error_reason
     */
    public String getErrorReason() {
        return errorReason;
    }

    /**
     * @param errorReason
     */
    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    /**
     * @return error_date
     */
    public String getErrorDate() {
        return errorDate;
    }

    /**
     * @param errorDate
     */
    public void setErrorDate(String errorDate) {
        this.errorDate = errorDate;
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
     * @return create_user_name
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * @param createUserName
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * @return create_user_id
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * @param createUserId
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
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
        SaError other = (SaError) that;
        return (this.getErrorId() == null ? other.getErrorId() == null : this.getErrorId().equals(other.getErrorId()))
            && (this.getErrorCode() == null ? other.getErrorCode() == null : this.getErrorCode().equals(other.getErrorCode()))
            && (this.getErrorReason() == null ? other.getErrorReason() == null : this.getErrorReason().equals(other.getErrorReason()))
            && (this.getErrorDate() == null ? other.getErrorDate() == null : this.getErrorDate().equals(other.getErrorDate()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUserName() == null ? other.getCreateUserName() == null : this.getCreateUserName().equals(other.getCreateUserName()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getErrorId() == null) ? 0 : getErrorId().hashCode());
        result = prime * result + ((getErrorCode() == null) ? 0 : getErrorCode().hashCode());
        result = prime * result + ((getErrorReason() == null) ? 0 : getErrorReason().hashCode());
        result = prime * result + ((getErrorDate() == null) ? 0 : getErrorDate().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUserName() == null) ? 0 : getCreateUserName().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", errorId=").append(errorId);
        sb.append(", errorCode=").append(errorCode);
        sb.append(", errorReason=").append(errorReason);
        sb.append(", errorDate=").append(errorDate);
        sb.append(", createDate=").append(createDate);
        sb.append(", status=").append(status);
        sb.append(", createUserName=").append(createUserName);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}