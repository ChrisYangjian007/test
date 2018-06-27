package com.dalian.sea.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tag_range")
public class TagRange implements Serializable {
    /**
     * 产品ID
     */
    @Id
    @Column(name = "range_id")
    private Long rangeId;

    /**
     * 产品ID
     */
    @Column(name = "start_no")
    private Long startNo;

    /**
     * 用户ID
     */
    @Column(name = "end_no")
    private Long endNo;

    /**
     * 规则名称
     */
    @Column(name = "rule_name")
    private String ruleName;

    /**
     * 批次ID
     */
    @Column(name = "batch_id")
    private Long batchId;

    @Column(name = "create_date")
    private Date createDate;

    private String status;

    @Column(name = "task_code")
    private String taskCode;

    private static final long serialVersionUID = 1L;

    /**
     * 获取产品ID
     *
     * @return range_id - 产品ID
     */
    public Long getRangeId() {
        return rangeId;
    }

    /**
     * 设置产品ID
     *
     * @param rangeId 产品ID
     */
    public void setRangeId(Long rangeId) {
        this.rangeId = rangeId;
    }

    /**
     * 获取产品ID
     *
     * @return start_no - 产品ID
     */
    public Long getStartNo() {
        return startNo;
    }

    /**
     * 设置产品ID
     *
     * @param startNo 产品ID
     */
    public void setStartNo(Long startNo) {
        this.startNo = startNo;
    }

    /**
     * 获取用户ID
     *
     * @return end_no - 用户ID
     */
    public Long getEndNo() {
        return endNo;
    }

    /**
     * 设置用户ID
     *
     * @param endNo 用户ID
     */
    public void setEndNo(Long endNo) {
        this.endNo = endNo;
    }

    /**
     * 获取规则名称
     *
     * @return rule_name - 规则名称
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * 设置规则名称
     *
     * @param ruleName 规则名称
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    /**
     * 获取批次ID
     *
     * @return batch_id - 批次ID
     */
    public Long getBatchId() {
        return batchId;
    }

    /**
     * 设置批次ID
     *
     * @param batchId 批次ID
     */
    public void setBatchId(Long batchId) {
        this.batchId = batchId;
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
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return task_code
     */
    public String getTaskCode() {
        return taskCode;
    }

    /**
     * @param taskCode
     */
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
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
        TagRange other = (TagRange) that;
        return (this.getRangeId() == null ? other.getRangeId() == null : this.getRangeId().equals(other.getRangeId()))
            && (this.getStartNo() == null ? other.getStartNo() == null : this.getStartNo().equals(other.getStartNo()))
            && (this.getEndNo() == null ? other.getEndNo() == null : this.getEndNo().equals(other.getEndNo()))
            && (this.getRuleName() == null ? other.getRuleName() == null : this.getRuleName().equals(other.getRuleName()))
            && (this.getBatchId() == null ? other.getBatchId() == null : this.getBatchId().equals(other.getBatchId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getTaskCode() == null ? other.getTaskCode() == null : this.getTaskCode().equals(other.getTaskCode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRangeId() == null) ? 0 : getRangeId().hashCode());
        result = prime * result + ((getStartNo() == null) ? 0 : getStartNo().hashCode());
        result = prime * result + ((getEndNo() == null) ? 0 : getEndNo().hashCode());
        result = prime * result + ((getRuleName() == null) ? 0 : getRuleName().hashCode());
        result = prime * result + ((getBatchId() == null) ? 0 : getBatchId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getTaskCode() == null) ? 0 : getTaskCode().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rangeId=").append(rangeId);
        sb.append(", startNo=").append(startNo);
        sb.append(", endNo=").append(endNo);
        sb.append(", ruleName=").append(ruleName);
        sb.append(", batchId=").append(batchId);
        sb.append(", createDate=").append(createDate);
        sb.append(", status=").append(status);
        sb.append(", taskCode=").append(taskCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}