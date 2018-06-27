package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_qr_code_use")
@Data
public class ZsQrCodeUse implements Serializable {
    /**
     * 使用记录ID
     */
    @Id
    @Column(name = "qr_code_use_id")
    private Long qrCodeUseId;

    /**
     * 二维码ID
     */
    @Column(name = "qr_code_id")
    private Long qrCodeId;

    /**
     * 生产任务ID
     */
    @Column(name = "produce_task_id")
    private Long produceTaskId;

    /**
     * 生产任务编码
     */
    @Column(name = "produce_task_code")
    private String produceTaskCode;

    /**
     * 生产任务名称
     */
    @Column(name = "produce_task_name")
    private String produceTaskName;

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
     * 解绑时间
     */
    @Column(name = "un_bind_date")
    private Date unBindDate;

    /**
     * 解绑人ID
     */
    @Column(name = "un_bind_user_id")
    private Long unBindUserId;

    /**
     * 解绑人名称
     */
    @Column(name = "un_bind_user_name")
    private String unBindUserName;

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
        sb.append(", qrCodeUseId=").append(qrCodeUseId);
        sb.append(", produceTaskId=").append(produceTaskId);
        sb.append(", produceTaskCode=").append(produceTaskCode);
        sb.append(", produceTaskName=").append(produceTaskName);
        sb.append(", bindDate=").append(bindDate);
        sb.append(", bindUserId=").append(bindUserId);
        sb.append(", bindUserName=").append(bindUserName);
        sb.append(", unBindDate=").append(unBindDate);
        sb.append(", unBindUserId=").append(unBindUserId);
        sb.append(", unBindUserName=").append(unBindUserName);
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