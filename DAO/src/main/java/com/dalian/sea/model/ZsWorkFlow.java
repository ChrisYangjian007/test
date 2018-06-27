package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_work_flow")
@Data
public class ZsWorkFlow implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "work_flow_id")
    private Long workFlowId;

    /**
     * 公司主键
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    @Column(name = "c_name")
    private String cName;

    /**
     * 分类
     */
    private Byte category;

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
     * 第一层ID
     */
    @Column(name = "first_id")
    private Long firstId;

    /**
     * 树形结构
     */
    @Column(name = "is_tree")
    private Byte isTree;

    /**
     * 排序
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 备注
     */
    private String remark;

    /**
     * 层标识
     */
    private String flage;

    /**
     * 标识层记录名称
     */
    @Column(name = "flage_name")
    private String flageName;

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
     * 状态（1-正常，2-禁用，3-删除）
     */
    private Byte status;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", workFlowId=").append(workFlowId);
        sb.append(", companyId=").append(companyId);
        sb.append(", code=").append(code);
        sb.append(", cName=").append(cName);
        sb.append(", category=").append(category);
        sb.append(", aLevel=").append(aLevel);
        sb.append(", parentId=").append(parentId);
        sb.append(", firstId=").append(firstId);
        sb.append(", isTree=").append(isTree);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", remark=").append(remark);
        sb.append(", flage=").append(flage);
        sb.append(", flageName=").append(flageName);
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