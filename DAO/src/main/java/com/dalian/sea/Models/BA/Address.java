package com.dalian.sea.Models.BA;

import Models.Annotations.ColumnAttribute;
import Models.Annotations.DBAttribute;

import java.io.Serializable;
import java.util.Date;

@DBAttribute(Name = "BA_Address")
public class Address implements Serializable {

    @ColumnAttribute(Name = "AreaName" )
    private String _areaname;
    public String getAreaName() {
        return _areaname;
    }
    public void setAreaName(String areaname) {
        this._areaname = areaname;
    }

    @ColumnAttribute(Name = "AddressName" )
    private String _addressname;
    public String getAddressName() {
        return _addressname;
    }
    public void setAddressName(String addressname) {
        this._addressname = addressname;
    }

    @ColumnAttribute(Name = "AddressRoleType" )
    private Integer _addressroletype;
    public Integer getAddressRoleType() {
        return _addressroletype;
    }
    public void setAddressRoleType(Integer addressroletype) {
        this._addressroletype = addressroletype;
    }

    @ColumnAttribute(Name = "AreaID" )
    private Integer _areaid;
    public Integer getAreaID() {
        return _areaid;
    }
    public void setAreaID(Integer areaid) {
        this._areaid = areaid;
    }

    @ColumnAttribute(Name = "Recipient" )
    private String _recipient;
    public String getRecipient() {
        return _recipient;
    }
    public void setRecipient(String recipient) {
        this._recipient = recipient;
    }

    @ColumnAttribute(Name = "ZipCode" )
    private String _zipcode;
    public String getZipCode() {
        return _zipcode;
    }
    public void setZipCode(String zipcode) {
        this._zipcode = zipcode;
    }

    @ColumnAttribute(Name = "IsPrimary" )
    private Integer _isprimary;
    public Integer getIsPrimary() {
        return _isprimary;
    }
    public void setIsPrimary(Integer isprimary) {
        this._isprimary = isprimary;
    }

    @ColumnAttribute(Name = "Phone" )
    private String _phone;
    public String getPhone() {
        return _phone;
    }
    public void setPhone(String phone) {
        this._phone = phone;
    }

    @ColumnAttribute(Name = "AreaFlag" )
    private String _areaflag;
    public String getAreaFlag() {
        return _areaflag;
    }
    public void setAreaFlag(String areaflag) {
        this._areaflag = areaflag;
    }

    @ColumnAttribute(Name = "AddressID" ,Description = "主键",IsPrimaryKey = true )
    private Integer _addressid;
    public Integer getAddressID() {
        return _addressid;
    }
    public void setAddressID(Integer addressid) {
        this._addressid = addressid;
    }

    @ColumnAttribute(Name = "Status" )
    private Integer _status;
    public Integer getStatus() {
        return _status;
    }
    public void setStatus(Integer status) {
        this._status = status;
    }

    @ColumnAttribute(Name = "CreatedDate" )
    private Date _createddate;
    public Date getCreatedDate() {
        return _createddate;
    }
    public void setCreatedDate(Date createddate) {
        this._createddate = createddate;
    }

    @ColumnAttribute(Name = "CreatedUserID" )
    private Integer _createduserid;
    public Integer getCreatedUserID() {
        return _createduserid;
    }
    public void setCreatedUserID(Integer createduserid) {
        this._createduserid = createduserid;
    }

    @ColumnAttribute(Name = "CreatedUserName" )
    private String _createdusername;
    public String getCreatedUserName() {
        return _createdusername;
    }
    public void setCreatedUserName(String createdusername) {
        this._createdusername = createdusername;
    }

    @ColumnAttribute(Name = "UpdatedDate" )
    private Date _updateddate;
    public Date getUpdatedDate() {
        return _updateddate;
    }
    public void setUpdatedDate(Date updateddate) {
        this._updateddate = updateddate;
    }

    @ColumnAttribute(Name = "UpdatedUserID" )
    private Integer _updateduserid;
    public Integer getUpdatedUserID() {
        return _updateduserid;
    }
    public void setUpdatedUserID(Integer updateduserid) {
        this._updateduserid = updateduserid;
    }

    @ColumnAttribute(Name = "UpdatedUserName" )
    private String _updatedusername;
    public String getUpdatedUserName() {
        return _updatedusername;
    }
    public void setUpdatedUserName(String updatedusername) {
        this._updatedusername = updatedusername;
    }

    @ColumnAttribute(Name = "ListIndex" )
    private Integer _listindex;
    public Integer getListIndex() {
        return _listindex;
    }
    public void setListIndex(Integer listindex) {
        this._listindex = listindex;
    }

    @ColumnAttribute(Name = "Remark" )
    private String _remark;
    public String getRemark() {
        return _remark;
    }
    public void setRemark(String remark) {
        this._remark = remark;
    }

    @ColumnAttribute(Name = "CompanyID" )
    private Integer _companyid;
    public Integer getCompanyID() {
        return _companyid;
    }
    public void setCompanyID(Integer companyid) {
        this._companyid = companyid;
    }
}