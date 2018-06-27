package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_warehouse")
@Data
public class ZsWarehouse implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**
     * 仓库名称
     */
    @Column(name = "c_name")
    private String cName;

    /**
     * 仓库类型 0 无 1 活参库
     */
    @Column(name = "warehouse_type")
    private Integer warehouseType;

    /**
     * 预警天数
     */
    @Column(name = "warning_day")
    private Integer warningDay;

    /**
     * 管理员id
     */
    @Column(name = "manager_id")
    private Long managerId;

    /**
     * 管理员
     */
    private String manager;

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
     * 状态(1-正常,6-删除,2-禁用)
     */
    private Byte status;

    /**
     * 管理员手机号
     */
    private String phone;

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

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "ZsWarehouse{" +
                "warehouseId=" + warehouseId +
                ", cName='" + cName + '\'' +
                ", managerId=" + managerId +
                ", manager='" + manager + '\'' +
                ", remark='" + remark + '\'' +
                ", companyId=" + companyId +
                ", listIndex=" + listIndex +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", createDate=" + createDate +
                ", createUserId=" + createUserId +
                ", updateDate=" + updateDate +
                ", updateUserId=" + updateUserId +
                '}';
    }
}