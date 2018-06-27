package com.dalian.sea.DTO;

import java.util.Date;

/**
 * Created by Administrator on 2017-10-20.
 */
/// <summary>
/// 实体地址表
/// </summary>
public class AddressDTO {
    /// <summary>
    /// 地址代码
    /// </summary>
    private String areaName;
    /// <summary>
    /// 地址名称
    /// </summary>
    private String cName;
    /// <summary>
    /// 地址用途类型：10 通用20 收货30 发货40 结算
    /// </summary>
    private int addressRoleType;
    /// <summary>
    /// 地区代码
    /// </summary>
    private int areaID;
    /// <summary>
    /// 接收人
    /// </summary>
    private String recipient;
    /// <summary>
    /// 邮政编码
    /// </summary>
    private String zipCode;
    /// <summary>
    /// 是否主要地址
    /// </summary>
    private int isPrimary;
    /// <summary>
    /// 手机
    /// </summary>
    private String phone;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getAddressRoleType() {
        return addressRoleType;
    }

    public void setAddressRoleType(int addressRoleType) {
        this.addressRoleType = addressRoleType;
    }

    public int getAreaID() {
        return areaID;
    }

    public void setAreaID(int areaID) {
        this.areaID = areaID;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(int isPrimary) {
        this.isPrimary = isPrimary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAreaFlag() {
        return areaFlag;
    }

    public void setAreaFlag(String areaFlag) {
        this.areaFlag = areaFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCreatedUserID() {
        return createdUserID;
    }

    public void setCreatedUserID(int createdUserID) {
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

    public int getUpdatedUserID() {
        return updatedUserID;
    }

    public void setUpdatedUserID(int updatedUserID) {
        this.updatedUserID = updatedUserID;
    }

    public String getUpdatedUserName() {
        return updatedUserName;
    }

    public void setUpdatedUserName(String updatedUserName) {
        this.updatedUserName = updatedUserName;
    }

    public int getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public int getCountyID() {
        return countyID;
    }

    public void setCountyID(int countyID) {
        this.countyID = countyID;
    }

    public int getListIndex() {
        return listIndex;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    /// <summary>
    /// 地址层次
    /// </summary>
    private String areaFlag;
    /// <summary>
    /// 地址主键
    /// </summary>
    private int id;
    /// <summary>
    /// 状态(1-正常,6-删除,2-禁用)
    /// </summary>
    private int status;
    /// <summary>
    /// 创建时间
    /// </summary>
    private Date createdDate;
    /// <summary>
    /// 操作人员ID
    /// </summary>
    private int createdUserID;
    /// <summary>
    /// 创建用户
    /// </summary>
    private String createdUserName;
    /// <summary>
    /// 更新时间
    /// </summary>
    private Date updatedDate;
    /// <summary>
    /// 修改用户主键
    /// </summary>
    private int updatedUserID;
    /// <summary>
    /// 修改用户
    /// </summary>
    private String updatedUserName;
    /// <summary>
    /// 地址主键
    /// </summary>
    private int provinceID;
    /// <summary>
    /// 地址主键
    /// </summary>
    private int cityID;
    /// <summary>
    /// 地址主键
    /// </summary>
    private int countyID;
    /// <summary>
    /// 排序
    /// </summary>
    private int listIndex;
    /// <summary>
    /// 备注
    /// </summary>
    private String remark;
    /// <summary>
    /// 公司主键
    /// </summary>
    private int companyID;
}