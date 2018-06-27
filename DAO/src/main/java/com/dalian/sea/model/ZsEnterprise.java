package com.dalian.sea.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_enterprise")
@Data
public class ZsEnterprise implements Serializable {
    /**
     * 公司主键
     */
    @Id
    @Column(name = "enterprise_id")
    private Long enterpriseId;

    /**
     * 公司名称
     */
    @Column(name = "c_name")
    private String cName;

    /**
     * 公司简称
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 5-供应商,6-客户
     */
    private Byte category;

    /**
     *供应商类型(1-原料供应 2-其他供应)
     */
    @Column(name = "enterprise_type")
    private Byte enterpriseType;

    /**
     *营业执照
     */
    @Column(name = "business_license_image")
    private String businessLicenseImage;

    /**
     * 营业执照有效期
     */
    @Column(name = "business_license_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date businessLicenseDate;

    /**
     * 生产许可证
     */
    @Column(name = "production_license_image")
    private String productionLicenseImage;

    /**
     * 生产许可证有效期
     */
    @Column(name = "production_license_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productionLicenseDate;

    /**
     * 其他图片
     */
    @Column(name = "other_license_image")
    private String otherLicenseImage;

    /**
     * 其他有效期
     */
    @Column(name = "other_license_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date otherLicenseDate;

    /**
     * 法定代表人
     */
    @Column(name = "corporate_rep")
    private String corporateRep;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 邮编
     */
    private String zipcode;

    /**
     * 网址
     */
    @Column(name = "web_url")
    private String webUrl;

    /**
     * 备注
     */
    private String remark;

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
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 地址主键
     */
    @Column(name = "area_id")
    private Long areaId;

    /**
     * 地址名称
     */
    @Column(name = "area_name")
    private String areaName;

    /**
     * 公司性质(1-国有企业,2-集体企业,3-联营企业,4-股份合作制企业,5-私营企业,6-个体户,7-合伙企业,8-有限责任公司,9-股份有限公司,10-外资企业)
     */
    @Column(name = "company_nature")
    private Byte companyNature;

    /**
     * 营业范围
     */
    @Column(name = "business_scope")
    private String businessScope;

    /**
     * 税务登记号
     */
    @Column(name = "tax_no")
    private String taxNo;

    /**
     * 税务登记日期
     */
    @Column(name = "tax_reg_date")
    private Date taxRegDate;

    /**
     * 机构代码
     */
    @Column(name = "org_code")
    private String orgCode;

    /**
     * 组织机构代码证号
     */
    @Column(name = "biz_reg_code")
    private String bizRegCode;

    /**
     * 企业法人身份证号
     */
    @Column(name = "corporate_rep_id_no")
    private String corporateRepIdNo;

    /**
     * 营业执照号
     */
    @Column(name = "biz_license_no")
    private String bizLicenseNo;

    /**
     * 公司ID
     */
    @Column(name = "company_id")
    private Long companyId;

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
        return "ZsEnterprise{" +
                "enterpriseId=" + enterpriseId +
                ", cName='" + cName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", category=" + category +
                ", enterpriseType=" + enterpriseType +
                ", businessLicenseImage='" + businessLicenseImage + '\'' +
                ", businessLicenseDate=" + businessLicenseDate +
                ", productionLicenseImage='" + productionLicenseImage + '\'' +
                ", productionLicenseDate=" + productionLicenseDate +
                ", otherLicenseImage='" + otherLicenseImage + '\'' +
                ", otherLicenseDate=" + otherLicenseDate +
                ", corporateRep='" + corporateRep + '\'' +
                ", contact='" + contact + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", remark='" + remark + '\'' +
                ", listIndex=" + listIndex +
                ", status=" + status +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", companyNature=" + companyNature +
                ", businessScope='" + businessScope + '\'' +
                ", taxNo='" + taxNo + '\'' +
                ", taxRegDate=" + taxRegDate +
                ", orgCode='" + orgCode + '\'' +
                ", bizRegCode='" + bizRegCode + '\'' +
                ", corporateRepIdNo='" + corporateRepIdNo + '\'' +
                ", bizLicenseNo='" + bizLicenseNo + '\'' +
                ", companyId=" + companyId +
                ", createDate=" + createDate +
                ", createUserId=" + createUserId +
                ", updateDate=" + updateDate +
                ", updateUserId=" + updateUserId +
                '}';
    }
}