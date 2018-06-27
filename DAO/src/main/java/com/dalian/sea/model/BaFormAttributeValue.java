package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "ba_form_attribute_value")
@Data
public class BaFormAttributeValue implements Serializable {
    /**
     * 工艺详情主键
     */
    @Id
    @Column(name = "form_attribute_value_id")
    private Long formAttributeValueId;

    /**
     * 生产任务主键
     */
    @Column(name = "produce_task_id")
    private Long produceTaskId;

    /**
     * 工艺详情主键
     */
    @Column(name = "work_process_id")
    private Long workProcessId;

    /**
     * 参数Json
     */
    @Column(name = "object_parameter_json")
    private String objectParameterJson;

    /**
     * 日志记录
     */
    @Column(name = "logger_json")
    private String loggerJson;

    /**
     * 操作类型(1-操作记录,2-审核记录,3-巡检记录)
     */
    @Column(name = "handle_type")
    private Byte handleType;

    /**
     * 类型顺序
     */
    @Column(name = "type_index")
    private Integer typeIndex;

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
        sb.append(", workProcessId=").append(workProcessId);
        sb.append(", objectParameterJson=").append(objectParameterJson);
        sb.append(", loggerJson=").append(loggerJson);
        sb.append(", handleType=").append(handleType);
        sb.append(", typeIndex=").append(typeIndex);
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