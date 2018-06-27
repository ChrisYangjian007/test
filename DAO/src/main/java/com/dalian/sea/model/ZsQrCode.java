package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_qr_code")
@Data
public class ZsQrCode implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "qr_code_id")
    private Long qrCodeId;

    /**
     * 二维码编号
     */
    @Column(name = "qr_code")
    private String qrCode;

    /**
     * 使用次数
     */
    @Column(name = "use_number")
    private Integer useNumber;

    /**
     * 使用状态 1 未绑定 2 已绑定
     */
    @Column(name = "use_status")
    private Byte useStatus;

    /**
     * 当前生产任务ID
     */
    @Column(name = "current_produce_task_id")
    private Long currentProduceTaskId;

    /**
     * 当前生产任务编码
     */
    @Column(name = "current_produce_task_code")
    private String currentProduceTaskCode;

    /**
     * 当前生产任务名称
     */
    @Column(name = "current_produce_task_name")
    private String currentProduceTaskName;

    /**
     * 绑定时间
     */
    @Column(name = "bind_date")
    private Date bindDate;

    /**
     * 绑定人ID
     */
    @Column(name = "bind_user_id")
    private Long bindUserId;

    /**
     * 绑定人名称
     */
    @Column(name = "bind_user_name")
    private String bindUserName;

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
     * 状态(1-正常,6-删除,2-禁用)
     */
    private Byte status;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", qrCodeId=").append(qrCodeId);
        sb.append(", qrCode=").append(qrCode);
        sb.append(", useNumber=").append(useNumber);
        sb.append(", useStatus=").append(useStatus);
        sb.append(", currentProduceTaskId=").append(currentProduceTaskId);
        sb.append(", currentProduceTaskCode=").append(currentProduceTaskCode);
        sb.append(", currentProduceTaskName=").append(currentProduceTaskName);
        sb.append(", bindDate=").append(bindDate);
        sb.append(", bindUserId=").append(bindUserId);
        sb.append(", bindUserName=").append(bindUserName);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}