package com.dalian.sea.Models.BA;

import Models.Annotations.ColumnAttribute;
import Models.Annotations.DBAttribute;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@DBAttribute(Name = "BA_Express")
public class Express implements Serializable {

    @ColumnAttribute(Name = "ExpressID" ,Description = "主键",IsPrimaryKey = true )
    private Integer _expressid;
    public Integer getExpressID() {
        return _expressid;
    }
    public void setExpressID(Integer expressid) {
        this._expressid = expressid;
    }

    @ColumnAttribute(Name = "Phone" )
    private String _phone;
    public String getPhone() {
        return _phone;
    }
    public void setPhone(String phone) {
        this._phone = phone;
    }

    @ColumnAttribute(Name = "CompanyName" )
    private String _companyname;
    public String getCompanyName() {
        return _companyname;
    }
    public void setCompanyName(String companyname) {
        this._companyname = companyname;
    }

    @ColumnAttribute(Name = "AreaID" )
    private String _areaid;
    public String getAreaID() {
        return _areaid;
    }
    public void setAreaID(String areaid) {
        this._areaid = areaid;
    }

    @ColumnAttribute(Name = "IsFile" )
    private Integer _isfile;
    public Integer getIsFile() {
        return _isfile;
    }
    public void setIsFile(Integer isfile) {
        this._isfile = isfile;
    }

    @ColumnAttribute(Name = "FirstWeight" )
    private Integer _firstweight;
    public Integer getFirstWeight() {
        return _firstweight;
    }
    public void setFirstWeight(Integer firstweight) {
        this._firstweight = firstweight;
    }

    @ColumnAttribute(Name = "SecondWeight" )
    private Integer _secondweight;
    public Integer getSecondWeight() {
        return _secondweight;
    }
    public void setSecondWeight(Integer secondweight) {
        this._secondweight = secondweight;
    }

    @ColumnAttribute(Name = "UnitID" )
    private Integer _unitid;
    public Integer getUnitID() {
        return _unitid;
    }
    public void setUnitID(Integer unitid) {
        this._unitid = unitid;
    }

    @ColumnAttribute(Name = "UnitName" )
    private String _unitname;
    public String getUnitName() {
        return _unitname;
    }
    public void setUnitName(String unitname) {
        this._unitname = unitname;
    }

    @ColumnAttribute(Name = "FirstRMB" )
    private BigDecimal _firstrmb;
    public BigDecimal getFirstRMB() {
        return _firstrmb;
    }
    public void setFirstRMB(BigDecimal firstrmb) {
        this._firstrmb = firstrmb;
    }

    @ColumnAttribute(Name = "SecondRMB" )
    private BigDecimal _secondrmb;
    public BigDecimal getSecondRMB() {
        return _secondrmb;
    }
    public void setSecondRMB(BigDecimal secondrmb) {
        this._secondrmb = secondrmb;
    }

    @ColumnAttribute(Name = "CompanyID" )
    private Integer _companyid;
    public Integer getCompanyID() {
        return _companyid;
    }
    public void setCompanyID(Integer companyid) {
        this._companyid = companyid;
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

    @ColumnAttribute(Name = "Remark" )
    private String _remark;
    public String getRemark() {
        return _remark;
    }
    public void setRemark(String remark) {
        this._remark = remark;
    }

    @ColumnAttribute(Name = "AreaName" )
    private String _areaname;
    public String getAreaName() {
        return _areaname;
    }
    public void setAreaName(String areaname) {
        this._areaname = areaname;
    }
}