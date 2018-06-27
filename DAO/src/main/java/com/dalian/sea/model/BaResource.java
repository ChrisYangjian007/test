package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "ba_resource")
@Data
public class BaResource implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "resource_id")
    private Long resourceId;

    /**
     * 资源名称
     */
    @Column(name = "c_name")
    private String cName;

    /**
     * 资源类别 0 (PC端) 1 (手机端)
     */
    @Column(name = "resource_type")
    private Byte resourceType;

    /**
     * 资源图标
     */
    private String icon;

    /**
     * 访问地址
     */
    private String location;

    /**
     * 手机地址
     */
    @Column(name = "mobile_location")
    private String mobileLocation;

    /**
     * 手机图标
     */
    @Column(name = "mobile_icon")
    private String mobileIcon;

    /**
     * 资源介绍
     */
    private String remark;

    /**
     * 排序
     */
    @Column(name = "list_index")
    private Integer listIndex;

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
     * 是否展开 0 关闭 1 展开
     */
    @Column(name = "is_expand")
    private Byte isExpand;

    /**
     * 分类(1-目录,2-页面,3-按钮,4-右键按钮,5-字段列)
     */
    private Integer category;

    /**
     * 编码
     */
    private String code;

    /**
     * 层标识
     */
    private String flag;

    /**
     * 层标识记录名称
     */
    @Column(name = "flag_name")
    private String flagName;

    /**
     * 目标(iframe,click)
     */
    private String target;

    /**
     * js事件方法
     */
    @Column(name = "js_event")
    private String jsEvent;

    /**
     * 分开线
     */
    private Byte split;

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
        sb.append(", resourceId=").append(resourceId);
        sb.append(", cName=").append(cName);
        sb.append(", resourceType=").append(resourceType);
        sb.append(", icon=").append(icon);
        sb.append(", location=").append(location);
        sb.append(", mobileLocation=").append(mobileLocation);
        sb.append(", mobileIcon=").append(mobileIcon);
        sb.append(", remark=").append(remark);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", aLevel=").append(aLevel);
        sb.append(", parentId=").append(parentId);
        sb.append(", firstId=").append(firstId);
        sb.append(", isExpand=").append(isExpand);
        sb.append(", category=").append(category);
        sb.append(", code=").append(code);
        sb.append(", flag=").append(flag);
        sb.append(", flagName=").append(flagName);
        sb.append(", target=").append(target);
        sb.append(", jsEvent=").append(jsEvent);
        sb.append(", split=").append(split);
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