package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "ba_roles")
@Data
public class BaRoles implements Serializable {
    /**
     * 主键id
     */
    @Id
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 公司主键
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 角色分类 0 PC 1 PDA
     */
    private Byte category;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色名称
     */
    @Column(name = "c_name")
    private String cName;

    /**
     * 	备注
     */
    private String remark;

    /**
     * 排序码
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 角色等级(1-市级 2-区级 3-镇级)
     */
    @Column(name = "roles_level")
    private Integer rolesLevel;

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
        sb.append(", roleId=").append(roleId);
        sb.append(", companyId=").append(companyId);
        sb.append(", category=").append(category);
        sb.append(", code=").append(code);
        sb.append(", cName=").append(cName);
        sb.append(", remark=").append(remark);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", rolesLevel=").append(rolesLevel);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", createData=").append(createDate);
        sb.append(", updateData=").append(updateDate);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}