package com.dalian.sea.DTO;

import java.util.Date;

/**
 * Created by Administrator on 2017-10-21.
 */
public class CompanyDTO
{
    /// <summary>
    /// 公司主键
    /// </summary>
    private Integer ID ;
    /// <summary>
    /// 父级主键
    /// </summary>
    private Integer parentID ;
    /// <summary>
    /// 公司分类(0-个体,1-公司,2-集团,3-机构,4-事业单位,5-供应商,6-客户)
    /// </summary>
    private Integer category ;
    /// <summary>
    /// 公司编码
    /// </summary>
    private String code ;
    /// <summary>
    /// 公司名称
    /// </summary>
    private String cName ;
    /// <summary>
    /// 公司简称
    /// </summary>
    private String shortName ;
    /// <summary>
    /// 公司行业类型(用json存储)
    /// </summary>
    private String industryType ;
    /// <summary>
    /// 法定代表人
    /// </summary>
    private String corporateRep ;
    /// <summary>
    /// 联系人
    /// </summary>
    private String contact ;
    /// <summary>
    /// 联系电话
    /// </summary>
    private String phone ;
    /// <summary>
    /// 传真
    /// </summary>
    private String fax ;
    /// <summary>
    /// 电子邮件
    /// </summary>
    private String email ;
    /// <summary>
    /// 详细地址
    /// </summary>
    private String address ;
    /// <summary>
    /// 开户信息
    /// </summary>
    private String accountInfo ;
    /// <summary>
    /// 邮编
    /// </summary>
    private String postalcode ;
    /// <summary>
    /// 网址
    /// </summary>
    private String webUrl ;
    /// <summary>
    /// 备注
    /// </summary>
    private String remark ;
    /// <summary>
    /// 排序码
    /// </summary>
    private Integer listIndex ;
    /// <summary>
    /// 状态(1-正常,6-删除,2-禁用)
    /// </summary>
    private Integer status ;
    /// <summary>
    /// 创建时间
    /// </summary>
    private Date createdDate ;
    /// <summary>
    /// 创建用户主键
    /// </summary>
    private Integer createdUserID ;
    /// <summary>
    /// 创建用户
    /// </summary>
    private String createdUserName ;
    /// <summary>
    /// 修改时间
    /// </summary>
    private Date updatedDate ;
    /// <summary>
    /// 修改用户主键
    /// </summary>
    private Integer updatedUserID ;
    /// <summary>
    /// 修改用户
    /// </summary>
    private String updatedUserName ;
    /// <summary>
    /// 经度
    /// </summary>
    private Double longitude ;
    /// <summary>
    /// 纬度
    /// </summary>
    private Double latitude ;
    /// <summary>
    /// 地址主键
    /// </summary>
    private Integer areaID ;
    /// <summary>
    /// 地址ID以,分开
    /// </summary>
    private String areaName ;
    /// <summary>
    /// 公司性质(1-国有企业,2-集体企业,3-联营企业,4-股份合作制企业,5-私营企业,6-个体户,7-合伙企业,8-有限责任公司,9-股份有限公司,10-外资企业)
    /// </summary>
    private Integer companyNature ;
    /// <summary>
    /// 注册资金
    /// </summary>
    private Double registerFund ;
    /// <summary>
    /// 营业范围
    /// </summary>
    private String businessScope ;
    /// <summary>
    /// 税务登记号
    /// </summary>
    private String taxNo ;
    /// <summary>
    /// 税务登记日期
    /// </summary>
    private Date taxRegDate ;
    /// <summary>
    /// 机构代码
    /// </summary>
    private String orgCode ;
    /// <summary>
    /// 组织机构代码证号
    /// </summary>
    private String bizRegCode ;
    /// <summary>
    /// 企业法人身份证号
    /// </summary>
    private String corporateRepIDNo ;
    /// <summary>
    /// 营业执照号
    /// </summary>
    private String bizLicenseNo ;
    /// <summary>
    /// 地址主键
    /// </summary>
    private Integer provinceID ;
    /// <summary>
    /// 地址主键
    /// </summary>
    private Integer cityID ;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getCorporateRep() {
        return corporateRep;
    }

    public void setCorporateRep(String corporateRep) {
        this.corporateRep = corporateRep;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(String accountInfo) {
        this.accountInfo = accountInfo;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getListIndex() {
        return listIndex;
    }

    public void setListIndex(Integer listIndex) {
        this.listIndex = listIndex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCreatedUserID() {
        return createdUserID;
    }

    public void setCreatedUserID(Integer createdUserID) {
        this.createdUserID = createdUserID;
    }

    public String getCreatedUserName() {
        return createdUserName;
    }

    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getUpdatedUserID() {
        return updatedUserID;
    }

    public void setUpdatedUserID(Integer updatedUserID) {
        this.updatedUserID = updatedUserID;
    }

    public String getUpdatedUserName() {
        return updatedUserName;
    }

    public void setUpdatedUserName(String updatedUserName) {
        this.updatedUserName = updatedUserName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getAreaID() {
        return areaID;
    }

    public void setAreaID(Integer areaID) {
        this.areaID = areaID;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(Integer companyNature) {
        this.companyNature = companyNature;
    }

    public Double getRegisterFund() {
        return registerFund;
    }

    public void setRegisterFund(Double registerFund) {
        this.registerFund = registerFund;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public Date getTaxRegDate() {
        return taxRegDate;
    }

    public void setTaxRegDate(Date taxRegDate) {
        this.taxRegDate = taxRegDate;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getBizRegCode() {
        return bizRegCode;
    }

    public void setBizRegCode(String bizRegCode) {
        this.bizRegCode = bizRegCode;
    }

    public String getCorporateRepIDNo() {
        return corporateRepIDNo;
    }

    public void setCorporateRepIDNo(String corporateRepIDNo) {
        this.corporateRepIDNo = corporateRepIDNo;
    }

    public String getBizLicenseNo() {
        return bizLicenseNo;
    }

    public void setBizLicenseNo(String bizLicenseNo) {
        this.bizLicenseNo = bizLicenseNo;
    }

    public Integer getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(Integer provinceID) {
        this.provinceID = provinceID;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public Integer getCountyID() {
        return countyID;
    }

    public void setCountyID(Integer countyID) {
        this.countyID = countyID;
    }

    public String getProductTypeIDs() {
        return productTypeIDs;
    }

    public void setProductTypeIDs(String productTypeIDs) {
        this.productTypeIDs = productTypeIDs;
    }

    public String getInputsIDs() {
        return inputsIDs;
    }

    public void setInputsIDs(String inputsIDs) {
        this.inputsIDs = inputsIDs;
    }

    /// <summary>
    /// 地址主键
    /// </summary>
    private Integer countyID ;
    /// <summary>
    /// 主营产品类型ID
    /// </summary>
    private String productTypeIDs ;
    /// <summary>
    /// 主要用到的农资ID
    /// </summary>
    private String inputsIDs ;
}
