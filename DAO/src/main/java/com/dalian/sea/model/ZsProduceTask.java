package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_produce_task")
@Data
public class ZsProduceTask implements Serializable {
    /**
     * 生产任务ID
     */
    @Id
    @Column(name = "produce_task_id")
    private Long produceTaskId;

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
     * 排序码
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 状态(1-正常,6-删除,2-禁用,7-进行中，8-已结束)
     */
    private Byte status;

    /**
     * 生产任务名称
     */
    @Column(name = "produce_task_name")
    private String produceTaskName;

    /**
     * 生产任务编号
     */
    @Column(name = "produce_task_no")
    private String produceTaskNo;

    /**
     * 生产任务状态（1-进行中 2-已结束）
     */
    @Column(name = "produce_task_status")
    private Byte produceTaskStatus;
    /**
     * 当前操作工序id
     */
    @Column(name = "work_process_id")
    private Long workProcessId;

    /**
     * 当前工序检查人
     */
    @Column(name = "check_user_id")
    private Long checkUserId;

    /**
     * 当前工序审核状态0 未提交 1 已提交 2 已审核
     */
    @Column(name = "check_status")
    private Byte checkStatus;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "ZsProduceTask{" +
                "produceTaskId=" + produceTaskId +
                ", createDate=" + createDate +
                ", createUserId=" + createUserId +
                ", updateDate=" + updateDate +
                ", updateUserId=" + updateUserId +
                ", listIndex=" + listIndex +
                ", status=" + status +
                ", produceTaskName='" + produceTaskName + '\'' +
                ", produceTaskNo='" + produceTaskNo + '\'' +
                ", workProcessId=" + workProcessId +
                ", checkUserId=" + checkUserId +
                ", checkStatus=" + checkStatus +
                '}';
    }
}