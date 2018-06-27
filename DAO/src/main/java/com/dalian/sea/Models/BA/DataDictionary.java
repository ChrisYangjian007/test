package com.dalian.sea.Models.BA;

/**
 * Created by Administrator on 2017-10-20.
 */
import Models.Annotations.ColumnAttribute;
import Models.Annotations.DBAttribute;

import java.io.Serializable;
import java.util.Date;

@DBAttribute(Name = "BA_DataDictionary")
public class DataDictionary implements Serializable {

    @ColumnAttribute(Name = "DataDictionaryID" ,Description = "主键",IsPrimaryKey = true )
    private Integer _datadictionaryid;
    public Integer getDataDictionaryID() {
        return _datadictionaryid;
    }
    public void setDataDictionaryID(Integer datadictionaryid) {
        this._datadictionaryid = datadictionaryid;
    }

    @ColumnAttribute(Name = "CompanyID" )
    private Integer _companyid;
    public Integer getCompanyID() {
        return _companyid;
    }
    public void setCompanyID(Integer companyid) {
        this._companyid = companyid;
    }

    @ColumnAttribute(Name = "IsTree" )
    private Integer _istree;
    public Integer getIsTree() {
        return _istree;
    }
    public void setIsTree(Integer istree) {
        this._istree = istree;
    }

    @ColumnAttribute(Name = "Category" )
    private Integer _category;
    public Integer getCategory() {
        return _category;
    }
    public void setCategory(Integer category) {
        this._category = category;
    }

    @ColumnAttribute(Name = "Code" )
    private String _code;
    public String getCode() {
        return _code;
    }
    public void setCode(String code) {
        this._code = code;
    }

    @ColumnAttribute(Name = "CName" )
    private String _cname;
    public String getCName() {
        return _cname;
    }
    public void setCName(String cname) {
        this._cname = cname;
    }

    @ColumnAttribute(Name = "Remark" )
    private String _remark;
    public String getRemark() {
        return _remark;
    }
    public void setRemark(String remark) {
        this._remark = remark;
    }

    @ColumnAttribute(Name = "ListIndex" )
    private Integer _listindex;
    public Integer getListIndex() {
        return _listindex;
    }
    public void setListIndex(Integer listindex) {
        this._listindex = listindex;
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

    @ColumnAttribute(Name = "Flag" )
    private String _flag;
    public String getFlag() {
        return _flag;
    }
    public void setFlag(String flag) {
        this._flag = flag;
    }

    @ColumnAttribute(Name = "FlagName" )
    private String _flagname;
    public String getFlagName() {
        return _flagname;
    }
    public void setFlagName(String flagname) {
        this._flagname = flagname;
    }

    @ColumnAttribute(Name = "ALevel" )
    private Integer _alevel;
    public Integer getALevel() {
        return _alevel;
    }
    public void setALevel(Integer alevel) {
        this._alevel = alevel;
    }

    @ColumnAttribute(Name = "ParentID" )
    private Integer _parentid;
    public Integer getParentID() {
        return _parentid;
    }
    public void setParentID(Integer parentid) {
        this._parentid = parentid;
    }

    @ColumnAttribute(Name = "FirstID" )
    private Integer _firstid;
    public Integer getFirstID() {
        return _firstid;
    }
    public void setFirstID(Integer firstid) {
        this._firstid = firstid;
    }
}