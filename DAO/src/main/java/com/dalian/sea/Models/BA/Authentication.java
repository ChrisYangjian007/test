package com.dalian.sea.Models.BA;
import Models.Annotations.ColumnAttribute;
import Models.Annotations.DBAttribute;

import java.io.Serializable;
import java.util.Date;

@DBAttribute(Name = "BA_Authentication")
public class Authentication implements Serializable {

    @ColumnAttribute(Name = "AuthenticationID" ,Description = "主键",IsPrimaryKey = true )
    private Integer _authenticationid;
    public Integer getAuthenticationID() {
        return _authenticationid;
    }
    public void setAuthenticationID(Integer authenticationid) {
        this._authenticationid = authenticationid;
    }

    @ColumnAttribute(Name = "AuthenticationNo" )
    private String _authenticationno;
    public String getAuthenticationNo() {
        return _authenticationno;
    }
    public void setAuthenticationNo(String authenticationno) {
        this._authenticationno = authenticationno;
    }

    @ColumnAttribute(Name = "ProductName" )
    private String _productname;
    public String getProductName() {
        return _productname;
    }
    public void setProductName(String productname) {
        this._productname = productname;
    }

    @ColumnAttribute(Name = "Turnout" )
    private String _turnout;
    public String getTurnout() {
        return _turnout;
    }
    public void setTurnout(String turnout) {
        this._turnout = turnout;
    }

    @ColumnAttribute(Name = "OrganName" )
    private String _organname;
    public String getOrganName() {
        return _organname;
    }
    public void setOrganName(String organname) {
        this._organname = organname;
    }

    @ColumnAttribute(Name = "AuthenticationDate" )
    private Date _authenticationdate;
    public Date getAuthenticationDate() {
        return _authenticationdate;
    }
    public void setAuthenticationDate(Date authenticationdate) {
        this._authenticationdate = authenticationdate;
    }

    @ColumnAttribute(Name = "StartDate" )
    private Date _startdate;
    public Date getStartDate() {
        return _startdate;
    }
    public void setStartDate(Date startdate) {
        this._startdate = startdate;
    }

    @ColumnAttribute(Name = "EndDate" )
    private Date _enddate;
    public Date getEndDate() {
        return _enddate;
    }
    public void setEndDate(Date enddate) {
        this._enddate = enddate;
    }

    @ColumnAttribute(Name = "CreatedUserID" )
    private Integer _createduserid;
    public Integer getCreatedUserID() {
        return _createduserid;
    }
    public void setCreatedUserID(Integer createduserid) {
        this._createduserid = createduserid;
    }

    @ColumnAttribute(Name = "CreatedDate" )
    private Date _createddate;
    public Date getCreatedDate() {
        return _createddate;
    }
    public void setCreatedDate(Date createddate) {
        this._createddate = createddate;
    }

    @ColumnAttribute(Name = "UpdatedUserID" )
    private Integer _updateduserid;
    public Integer getUpdatedUserID() {
        return _updateduserid;
    }
    public void setUpdatedUserID(Integer updateduserid) {
        this._updateduserid = updateduserid;
    }

    @ColumnAttribute(Name = "UpdatedDate" )
    private Date _updateddate;
    public Date getUpdatedDate() {
        return _updateddate;
    }
    public void setUpdatedDate(Date updateddate) {
        this._updateddate = updateddate;
    }

    @ColumnAttribute(Name = "Status" )
    private Integer _status;
    public Integer getStatus() {
        return _status;
    }
    public void setStatus(Integer status) {
        this._status = status;
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