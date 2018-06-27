package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "ba_form_attribute")
@Data
public class BaFormAttribute implements Serializable {
    /**
     * 表单附加属性主键
     */
    @Id
    @Column(name = "form_attribute_id")
    private Long formAttributeId;

    /**
     * 模块主键
     */
    @Column(name = "module_id")
    private Long moduleId;

    /**
     * 属性名称(自定义列名)
     */
    @Column(name = "property_name")
    private String propertyName;

    /**
     * 控件名称(命名)
     */
    @Column(name = "control_name")
    private String controlName;

    /**
     * 控件类型(1-文本框,2-下拉框,3-日期框,4-标签,5-多行文本)
     */
    @Column(name = "control_type")
    private Byte controlType;

    /**
     * 限制条件 1 不能为空 2 只能输入数字 3 数字、字母 4 无限制 5 默认当前用户
     */
    @Column(name = "restrictive_conditions")
    private Integer restrictiveConditions;

    /**
     * 控件样式
     */
    @Column(name = "control_style")
    private String controlStyle;

    /**
     * 控件验证
     */
    @Column(name = "control_validator")
    private String controlValidator;

    /**
     * 输入长度
     */
    @Column(name = "import_length")
    private Integer importLength;

    /**
     * 默认值
     */
    @Column(name = "default_value")
    private String defaultValue;

    /**
     * 自定义属性
     */
    @Column(name = "attributes_property")
    private String attributesProperty;

    /**
     * 控件数据源类型(1-数据字典,0-固定 2-PDA用户)
     */
    @Column(name = "data_source_type")
    private Byte dataSourceType;

    /**
     * 控件数据源(数据据字典编码)
     */
    @Column(name = "data_source_code")
    private String dataSourceCode;

    /**
     * 控件数据源(数据据字典名称)
     */
    @Column(name = "data_source")
    private String dataSource;

    /**
     * 合并列
     */
    @Column(name = "control_colspan")
    private String controlColspan;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序码
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 工艺ID
     */
    @Column(name = "work_process_id")
    private Long workProcessId;

    /**
     * 操作类型(1-操作记录,2-审核记录,3-巡检记录)
     */
    @Column(name = "handle_type")
    private Byte handleType;

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
        sb.append(", formAttributeId=").append(formAttributeId);
        sb.append(", moduleId=").append(moduleId);
        sb.append(", propertyName=").append(propertyName);
        sb.append(", controlName=").append(controlName);
        sb.append(", controlType=").append(controlType);
        sb.append(", restrictiveConditions=").append(restrictiveConditions);
        sb.append(", controlStyle=").append(controlStyle);
        sb.append(", controlValidator=").append(controlValidator);
        sb.append(", importLength=").append(importLength);
        sb.append(", defaultValue=").append(defaultValue);
        sb.append(", attributesProperty=").append(attributesProperty);
        sb.append(", dataSourceType=").append(dataSourceType);
        sb.append(", dataSourceCode=").append(dataSourceCode);
        sb.append(", dataSource=").append(dataSource);
        sb.append(", controlColspan=").append(controlColspan);
        sb.append(", remark=").append(remark);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", workProcessId=").append(workProcessId);
        sb.append(", handleType=").append(handleType);
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