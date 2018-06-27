package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_unit")
@Data
public class SysUnit implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "unit_id")
    private Long unitId;

    /**
     * 单位名称
     */
    @Column(name = "c_name")
    private String cName;

    /**
     * 值
     */
    private Integer value;

    /**
     * 值列表(1,2,3)
     */
    @Column(name = "value_list")
    private String valueList;

    /**
     * 备注
     */
    private String remark;

    /**
     * 公司主键
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 排序码
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 层标识(1,2)记录树ID
     */
    private String flag;

    /**
     * 层标识记录名称
     */
    @Column(name = "flag_name")
    private String flagName;

    /**
     * 所在层
     */
    @Column(name = "a_level")
    private Integer aLevel;

    /**
     * 上一层ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 第1层ID
     */
    @Column(name = "first_id")
    private Long firstId;

    /**
     * 创建用户主键
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
     * 修改时间
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
        sb.append(", unitId=").append(unitId);
        sb.append(", cName=").append(cName);
        sb.append(", value=").append(value);
        sb.append(", valueList=").append(valueList);
        sb.append(", remark=").append(remark);
        sb.append(", companyId=").append(companyId);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", flag=").append(flag);
        sb.append(", flagName=").append(flagName);
        sb.append(", aLevel=").append(aLevel);
        sb.append(", parentId=").append(parentId);
        sb.append(", firstId=").append(firstId);
        sb.append(", createdUserId=").append(createUserId);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", createdDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}