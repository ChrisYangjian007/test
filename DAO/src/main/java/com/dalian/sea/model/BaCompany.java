package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "ba_company")
@Data
public class BaCompany implements Serializable {
    /**
     * 公司主键
     */
    @Id
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 公司编码
     */
    private String code;

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
     * 公司分类(0-个体,1-公司,2-集团,3-机构,4-事业单位,5-供应商,6-客户,7-合作社,8-监管单位,9-种养殖户)
     */
    private Byte category;

    /**
     * 网址
     */
    @Column(name = "web_url")
    private String webUrl;

    /**
     * 单位电话
     */
    @Column(name = "company_phone")
    private String companyPhone;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 微信号
     */
    @Column(name = "wei_chat")
    private String weiChat;

    /**
     * 微信公众号
     */
    @Column(name = "we_chat_public_number")
    private String weChatPublicNumber;

    /**
     * 海域位置(手动输入)
     */
    @Column(name = "sea_area_position")
    private String seaAreaPosition;

    /**
     * 海域图片(10张)
     */
    @Column(name = "sea_area_images")
    private String seaAreaImages;

    /**
     * 企业简介
     */
    @Column(name = "enterprise_introduction")
    private String enterpriseIntroduction;

    /**
     * 企业图片(10张)
     */
    @Column(name = "enterprise_images")
    private String enterpriseImages;

    /**
     * 企业形象短片
     */
    @Column(name = "enterprise_image_video")
    private String enterpriseImageVideo;

    /**
     * 全景图
     */
    private String panorama;

    /**
     * 检测中心简介
     */
    @Column(name = "detection_center_introduction")
    private String detectionCenterIntroduction;

    /**
     * 检测中心图片(10张)
     */
    @Column(name = "detection_center_images")
    private String detectionCenterImages;

    /**
     * 添加内容(json)
     */
    @Column(name = "add_content")
    private String addContent;

    /**
     * 父级主键
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 开户信息
     */
    @Column(name = "account_info")
    private String accountInfo;

    /**
     * 主体类别
     */
    @Column(name = "industry_type")
    private String industryType;

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
     * 邮编
     */
    private String zipcode;

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
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 管辖区域主键
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
     * 认证类型
     */
    @Column(name = "authentication_type")
    private String authenticationType;

    /**
     * 注册资金
     */
    @Column(name = "register_fund")
    private BigDecimal registerFund;

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
     * 主营产品类型ID
     */
    @Column(name = "product_type_id")
    private Long productTypeId;

    /**
     * 主要用到的农资ID
     */
    @Column(name = "inputs_id")
    private Long inputsId;

    /**
     * 地区所属父类ID
     */
    @Column(name = "area_parent_id")
    private Long areaParentId;

    /**
     * 审核状态
     */
    @Column(name = "check_status")
    private Integer checkStatus;

    /**
     * 所在地址ID
     */
    @Column(name = "address_id")
    private Long addressId;

    /**
     * 所在地址名称
     */
    @Column(name = "address_name")
    private String addressName;

    /**
     * 成立日期
     */
    @Column(name = "establish_date")
    private Date establishDate;

    /**
     * 经营期限
     */
    @Column(name = "operating_period_date")
    private Date operatingPeriodDate;

    /**
     * 核准日期
     */
    @Column(name = "lssue_date")
    private Date lssueDate;

    /**
     * 生产面积
     */
    @Column(name = "production_area")
    private BigDecimal productionArea;

    /**
     * 行业类型（String类型）
     */
    @Column(name = "profession_type")
    private String professionType;

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
        sb.append(", companyId=").append(companyId);
        sb.append(", code=").append(code);
        sb.append(", cName=").append(cName);
        sb.append(", shortName=").append(shortName);
        sb.append(", category=").append(category);
        sb.append(", webUrl=").append(webUrl);
        sb.append(", companyPhone=").append(companyPhone);
        sb.append(", address=").append(address);
        sb.append(", weiChat=").append(weiChat);
        sb.append(", weChatPublicNumber=").append(weChatPublicNumber);
        sb.append(", seaAreaPosition=").append(seaAreaPosition);
        sb.append(", seaAreaImages=").append(seaAreaImages);
        sb.append(", enterpriseIntroduction=").append(enterpriseIntroduction);
        sb.append(", enterpriseImages=").append(enterpriseImages);
        sb.append(", enterpriseImageVideo=").append(enterpriseImageVideo);
        sb.append(", panorama=").append(panorama);
        sb.append(", detectionCenterIntroduction=").append(detectionCenterIntroduction);
        sb.append(", detectionCenterImages=").append(detectionCenterImages);
        sb.append(", addContent=").append(addContent);
        sb.append(", parentId=").append(parentId);
        sb.append(", accountInfo=").append(accountInfo);
        sb.append(", industryType=").append(industryType);
        sb.append(", corporateRep=").append(corporateRep);
        sb.append(", contact=").append(contact);
        sb.append(", phone=").append(phone);
        sb.append(", fax=").append(fax);
        sb.append(", email=").append(email);
        sb.append(", zipcode=").append(zipcode);
        sb.append(", remark=").append(remark);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", areaId=").append(areaId);
        sb.append(", areaName=").append(areaName);
        sb.append(", companyNature=").append(companyNature);
        sb.append(", authenticationType=").append(authenticationType);
        sb.append(", registerFund=").append(registerFund);
        sb.append(", businessScope=").append(businessScope);
        sb.append(", taxNo=").append(taxNo);
        sb.append(", taxRegDate=").append(taxRegDate);
        sb.append(", orgCode=").append(orgCode);
        sb.append(", bizRegCode=").append(bizRegCode);
        sb.append(", corporateRepIdNo=").append(corporateRepIdNo);
        sb.append(", bizLicenseNo=").append(bizLicenseNo);
        sb.append(", productTypeId=").append(productTypeId);
        sb.append(", inputsId=").append(inputsId);
        sb.append(", areaParentId=").append(areaParentId);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", addressId=").append(addressId);
        sb.append(", addressName=").append(addressName);
        sb.append(", establishDate=").append(establishDate);
        sb.append(", operatingPeriodDate=").append(operatingPeriodDate);
        sb.append(", lssueDate=").append(lssueDate);
        sb.append(", productionArea=").append(productionArea);
        sb.append(", professionType=").append(professionType);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}