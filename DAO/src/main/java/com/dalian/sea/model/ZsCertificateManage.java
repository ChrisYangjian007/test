package com.dalian.sea.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_certificate_manage")
@Data
public class ZsCertificateManage implements Serializable {
    /**
     * 证书管理编号
     */
    @Id
    @Column(name = "certificate_manage_id")
    private Long certificateManageId;

    /**
     * 产品大类id
     */
    @Column(name = "big_product_type_id")
    private Long bigProductTypeId;

    /**
     * 产品小类id
     */
    @Column(name = "small_product_type_id")
    private Long smallProductTypeId;

    /**
     * 生产许可证
     */
    @Column(name = "production_license")
    private String productionLicense;

    /**
     * 产品生产过程编号
     */
    @Column(name = "production_process_id")
    private Long productionProcessId;

    /**
     * 产品标准号
     */
    @Column(name = "product_standards")
    private String productStandards;

    /**
     * 检验结果(1-合格 2-不合格）
     */
    @Column(name = "test_result")
    private Byte testResult;

    /**
     * 到期日期
     */
    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    /**
     * 证书图片（最多10张）
     */
    @Column(name = "certificate_image")
    private String certificateImage;

    /**
     * 增加内容的标题
     */
    @Column(name = "content_title")
    private String contentTitle;

    /**
     * 增加的内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 创建者id
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 更新者id
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
     * 状态（1-正常 2-禁用 6-删除）
     */
    private Byte status;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "ZsCertificateManage{" +
                "certificateManageId=" + certificateManageId +
                ", bigProductTypeId=" + bigProductTypeId +
                ", smallProductTypeId=" + smallProductTypeId +
                ", productionLicense='" + productionLicense + '\'' +
                ", productionProcessId=" + productionProcessId +
                ", productStandards='" + productStandards + '\'' +
                ", testResult=" + testResult +
                ", endDate=" + endDate +
                ", certificateImage='" + certificateImage + '\'' +
                ", contentTitle='" + contentTitle + '\'' +
                ", content='" + content + '\'' +
                ", createUserId=" + createUserId +
                ", updateUserId=" + updateUserId +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", status=" + status +
                '}';
    }
}