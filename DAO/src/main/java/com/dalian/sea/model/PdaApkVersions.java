package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "pda_apk_versions")
@Data
public class PdaApkVersions implements Serializable {
    /**
     * appID
     */
    @Id
    @Column(name = "app_id")
    private Long appId;

    /**
     * app名字
     */
    @Column(name = "app_name")
    private String appName;

    /**
     * App地址
     */
    @Column(name = "app_url")
    private String appUrl;

    /**
     * 服务器地址
     */
    @Column(name = "service_url")
    private String serviceUrl;

    /**
     * app版本
     */
    @Column(name = "app_versions")
    private String appVersions;

    /**
     * app版本Code
     */
    @Column(name = "app_versions_code")
    private String appVersionsCode;

    /**
     * 版本备注
     */
    @Column(name = "versions_remarks")
    private String versionsRemarks;

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
     * 创建人ID
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 更新人ID
     */
    @Column(name = "update_user_id")
    private Long updateUserId;

    /**
     * 是否删除 1 正常 6 删除(默认1)
     */
    @Column(name = "is_delete")
    private Byte isDelete;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", appId=").append(appId);
        sb.append(", appName=").append(appName);
        sb.append(", appUrl=").append(appUrl);
        sb.append(", serviceUrl=").append(serviceUrl);
        sb.append(", appVersions=").append(appVersions);
        sb.append(", appVersionsCode=").append(appVersionsCode);
        sb.append(", versionsRemarks=").append(versionsRemarks);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}