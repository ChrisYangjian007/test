package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_product_type")
@Data
public class SysProductType implements Serializable {
    /**
     * 产品ID
     */
    @Id
    @Column(name = "product_type_id")
    private Long productTypeId;

    /**
     * 名称
     */
    @Column(name = "c_name")
    private String cName;

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
     * 层标识
     */
    private String flag;

    /**
     * 层标识记录名称
     */
    @Column(name = "flag_name")
    private String flagName;

    /**
     * 备注
     */
    private String memo;

    /**
     * 繁体名称
     */
    @Column(name = "t_name")
    private String tName;

    /**
     * 英文名
     */
    @Column(name = "e_name")
    private String eName;

    /**
     * 拼音全称
     */
    @Column(name = "pin_yin_a")
    private String pinYinA;

    /**
     * 拼音简称
     */
    @Column(name = "pin_yin_s")
    private String pinYinS;

    /**
     * 宜
     */
    private String appropriate;

    /**
     * 忌
     */
    private String avoid;

    /**
     * 是否是农资 0 不是 1 是
     */
    @Column(name = "is_inputs")
    private Byte isInputs;

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
        sb.append(", productTypeId=").append(productTypeId);
        sb.append(", cName=").append(cName);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", aLevel=").append(aLevel);
        sb.append(", parentId=").append(parentId);
        sb.append(", firstId=").append(firstId);
        sb.append(", flag=").append(flag);
        sb.append(", flagName=").append(flagName);
        sb.append(", memo=").append(memo);
        sb.append(", tName=").append(tName);
        sb.append(", eName=").append(eName);
        sb.append(", pinYinA=").append(pinYinA);
        sb.append(", pinYinS=").append(pinYinS);
        sb.append(", appropriate=").append(appropriate);
        sb.append(", avoid=").append(avoid);
        sb.append(", isInputs=").append(isInputs);
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