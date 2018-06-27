package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_task_detail_value")
@Data
public class ZsTaskDetailValue implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "task_detail_value_id")
    private Long taskDetailValueId;

    /**
     * 生产任务ID
     */
    @Column(name = "produce_task_id")
    private Long produceTaskId;

    /**
     * 任务对应产品id
     */
    @Column(name = "produce_task_detail_id")
    private Long produceTaskDetailId;

    /**
     * 工作流ID
     */
    @Column(name = "work_flow_id")
    private Integer workFlowId;

    /**
     * 工艺ID
     */
    @Column(name = "work_process_id")
    private Integer workProcessId;

    /**
     * 工艺名称
     */
    @Column(name = "work_process_name")
    private String workProcessName;

    /**
     * 参数Json
     */
    @Column(name = "object_parameter_json")
    private String objectParameterJson;

    /**
     * 创建者id
     */
    @Column(name = "create_user_id")
    private Long createUserId;

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
     * 状态（1-正常 6-删除 2-禁用）
     */
    private Byte status;

    private static final long serialVersionUID = 1L;

}