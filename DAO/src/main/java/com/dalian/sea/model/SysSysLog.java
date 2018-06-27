package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_sys_log")
@Data
public class SysSysLog implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "sys_log_id")
    private Long sysLogId;

    /**
     * 对象ID
     */
    @Column(name = "object_id")
    private Long objectId;

    /**
     * 日志类型
     */
    @Column(name = "log_type")
    private String logType;

    /**
     * IP地址
     */
    @Column(name = "ip_address")
    private String ipAddress;

    /**
     * 地址名称
     */
    @Column(name = "ip_address_name")
    private String ipAddressName;

    /**
     * 公司ID
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 模块ID
     */
    @Column(name = "resource_id")
    private Long resourceId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 资源名称
     */
    @Column(name = "c_name")
    private String cName;

    /**
     * 创建用户主键
     */
    @Column(name = "created_user_id")
    private Long createdUserId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

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
        sb.append(", sysLogId=").append(sysLogId);
        sb.append(", objectId=").append(objectId);
        sb.append(", logType=").append(logType);
        sb.append(", ipAddress=").append(ipAddress);
        sb.append(", ipAddressName=").append(ipAddressName);
        sb.append(", companyId=").append(companyId);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", remark=").append(remark);
        sb.append(", cName=").append(cName);
        sb.append(", createdUserId=").append(createdUserId);
        sb.append(", createDate=").append(createDate);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}