package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_work_process")
@Data
public class ZsWorkProcess implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "work_process_id")
    private Long workProcessId;

    /**
     * 工作流ID
     */
    @Column(name = "work_flow_id")
    private Long workFlowId;

    /**
     * 名称
     */
    @Column(name = "c_name")
    private String cName;

    /**
     * 名称ID
     */
    @Column(name = "name_id")
    private Long nameId;

    /**
     * 工序字段 0 无 1 有
     */
    @Column(name = "is_workshop_record")
    private Byte isWorkshopRecord;

    /**
     * 检查状态 0 未提交 1 已提交 2 已审核
     */
    @Column(name = "check_state")
    private Integer checkState;

    /**
     * 预先填写记录
     */
    @Column(name = "is_before_record")
    private Byte isBeforeRecord;

    /**
     * 创建者ID
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 更新者ID
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
     * 状态（1-正常，2-禁用，6-删除）
     */
    private Byte status;

    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", workProcessId=").append(workProcessId);
        sb.append(", workFlowId=").append(workFlowId);
        sb.append(", cName=").append(cName);
        sb.append(", nameId=").append(nameId);
        sb.append(", isWorkshopRecord=").append(isWorkshopRecord);
        sb.append(", isBeforeRecord=").append(isBeforeRecord);
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