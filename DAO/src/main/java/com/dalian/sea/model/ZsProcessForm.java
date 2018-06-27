package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "zs_process_form")
@Data
public class ZsProcessForm implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "process_form_id")
    private Long processFormId;

    /**
     * 工艺ID
     */
    @Column(name = "work_process_id")
    private Long workProcessId;

    /**
     * 字段ID
     */
    @Column(name = "form_attribute_id")
    private Long formAttributeId;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", processFormId=").append(processFormId);
        sb.append(", workProcessId=").append(workProcessId);
        sb.append(", formAttributeId=").append(formAttributeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}