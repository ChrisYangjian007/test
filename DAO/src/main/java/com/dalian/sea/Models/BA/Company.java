package com.dalian.sea.Models.BA;

import Models.Annotations.ColumnAttribute;
import Models.Annotations.DBAttribute;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@DBAttribute(Name = "BA_Company")
public class Company implements Serializable {

    @ColumnAttribute(Name = "CompanyID" ,Description = "主键",IsPrimaryKey = true )
    private Integer _companyid;
    public Integer getCompanyID() {
        return _companyid;
    }
    public void setCompanyID(Integer companyid) {
        this._companyid = companyid;
    }

    @ColumnAttribute(Name = "ParentID" )
    private Integer _parentid;
    public Integer getParentID() {
        return _parentid;
    }
    public void setParentID(Integer parentid) {
        this._parentid = parentid;
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

    @ColumnAttribute(Name = "ShortName" )
    private String _shortname;
    public String getShortName() {
        return _shortname;
    }
    public void setShortName(String shortname) {
        this._shortname = shortname;
    }

    @ColumnAttribute(Name = "IndustryType" )
    private String _industrytype;
    public String getIndustryType() {
        return _industrytype;
    }
    public void setIndustryType(String industrytype) {
        this._industrytype = industrytype;
    }

    @ColumnAttribute(Name = "CorporateRep" )
    private String _corporaterep;
    public String getCorporateRep() {
        return _corporaterep;
    }
    public void setCorporateRep(String corporaterep) {
        this._corporaterep = corporaterep;
    }

    @ColumnAttribute(Name = "Contact" )
    private String _contact;
    public String getContact() {
        return _contact;
    }
    public void setContact(String contact) {
        this._contact = contact;
    }

    @ColumnAttribute(Name = "Phone" )
    private String _phone;
    public String getPhone() {
        return _phone;
    }
    public void setPhone(String phone) {
        this._phone = phone;
    }

    @ColumnAttribute(Name = "Fax" )
    private String _fax;
    public String getFax() {
        return _fax;
    }
    public void setFax(String fax) {
        this._fax = fax;
    }

    @ColumnAttribute(Name = "Email" )
    private String _email;
    public String getEmail() {
        return _email;
    }
    public void setEmail(String email) {
        this._email = email;
    }

    @ColumnAttribute(Name = "Address" )
    private String _address;
    public String getAddress() {
        return _address;
    }
    public void setAddress(String address) {
        this._address = address;
    }

    @ColumnAttribute(Name = "AccountInfo" )
    private String _accountinfo;
    public String getAccountInfo() {
        return _accountinfo;
    }
    public void setAccountInfo(String accountinfo) {
        this._accountinfo = accountinfo;
    }

    @ColumnAttribute(Name = "Postalcode" )
    private String _postalcode;
    public String getPostalcode() {
        return _postalcode;
    }
    public void setPostalcode(String postalcode) {
        this._postalcode = postalcode;
    }

    @ColumnAttribute(Name = "WebUrl" )
    private String _weburl;
    public String getWebUrl() {
        return _weburl;
    }
    public void setWebUrl(String weburl) {
        this._weburl = weburl;
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

    @ColumnAttribute(Name = "Longitude" )
    private BigDecimal _longitude;
    public BigDecimal getLongitude() {
        return _longitude;
    }
    public void setLongitude(BigDecimal longitude) {
        this._longitude = longitude;
    }

    @ColumnAttribute(Name = "Latitude" )
    private BigDecimal _latitude;
    public BigDecimal getLatitude() {
        return _latitude;
    }
    public void setLatitude(BigDecimal latitude) {
        this._latitude = latitude;
    }

    @ColumnAttribute(Name = "AreaID" )
    private Integer _areaid;
    public Integer getAreaID() {
        return _areaid;
    }
    public void setAreaID(Integer areaid) {
        this._areaid = areaid;
    }

    @ColumnAttribute(Name = "AreaName" )
    private String _areaname;
    public String getAreaName() {
        return _areaname;
    }
    public void setAreaName(String areaname) {
        this._areaname = areaname;
    }

    @ColumnAttribute(Name = "CompanyNature" )
    private Integer _companynature;
    public Integer getCompanyNature() {
        return _companynature;
    }
    public void setCompanyNature(Integer companynature) {
        this._companynature = companynature;
    }

    @ColumnAttribute(Name = "RegisterFund" )
    private BigDecimal _registerfund;
    public BigDecimal getRegisterFund() {
        return _registerfund;
    }
    public void setRegisterFund(BigDecimal registerfund) {
        this._registerfund = registerfund;
    }

    @ColumnAttribute(Name = "BusinessScope" )
    private String _businessscope;
    public String getBusinessScope() {
        return _businessscope;
    }
    public void setBusinessScope(String businessscope) {
        this._businessscope = businessscope;
    }

    @ColumnAttribute(Name = "TaxNo" )
    private String _taxno;
    public String getTaxNo() {
        return _taxno;
    }
    public void setTaxNo(String taxno) {
        this._taxno = taxno;
    }

    @ColumnAttribute(Name = "TaxRegDate" )
    private Date _taxregdate;
    public Date getTaxRegDate() {
        return _taxregdate;
    }
    public void setTaxRegDate(Date taxregdate) {
        this._taxregdate = taxregdate;
    }

    @ColumnAttribute(Name = "OrgCode" )
    private String _orgcode;
    public String getOrgCode() {
        return _orgcode;
    }
    public void setOrgCode(String orgcode) {
        this._orgcode = orgcode;
    }

    @ColumnAttribute(Name = "BizRegCode" )
    private String _bizregcode;
    public String getBizRegCode() {
        return _bizregcode;
    }
    public void setBizRegCode(String bizregcode) {
        this._bizregcode = bizregcode;
    }

    @ColumnAttribute(Name = "CorporateRepIDNo" )
    private String _corporaterepidno;
    public String getCorporateRepIDNo() {
        return _corporaterepidno;
    }
    public void setCorporateRepIDNo(String corporaterepidno) {
        this._corporaterepidno = corporaterepidno;
    }

    @ColumnAttribute(Name = "BizLicenseNo" )
    private String _bizlicenseno;
    public String getBizLicenseNo() {
        return _bizlicenseno;
    }
    public void setBizLicenseNo(String bizlicenseno) {
        this._bizlicenseno = bizlicenseno;
    }

    @ColumnAttribute(Name = "ProductTypeIDs" )
    private String _producttypeids;
    public String getProductTypeIDs() {
        return _producttypeids;
    }
    public void setProductTypeIDs(String producttypeids) {
        this._producttypeids = producttypeids;
    }

    @ColumnAttribute(Name = "InputsIDs" )
    private String _inputsids;
    public String getInputsIDs() {
        return _inputsids;
    }
    public void setInputsIDs(String inputsids) {
        this._inputsids = inputsids;
    }

    @ColumnAttribute(Name = "AuthenticationType" )
    private Integer _authenticationtype;
    public Integer getAuthenticationType() {
        return _authenticationtype;
    }
    public void setAuthenticationType(Integer authenticationtype) {
        this._authenticationtype = authenticationtype;
    }

    @ColumnAttribute(Name = "AreaParentID" )
    private Integer _areaparentid;
    public Integer getAreaParentID() {
        return _areaparentid;
    }
    public void setAreaParentID(Integer areaparentid) {
        this._areaparentid = areaparentid;
    }

    @ColumnAttribute(Name = "CheckStatus" )
    private Integer _checkstatus;
    public Integer getCheckStatus() {
        return _checkstatus;
    }
    public void setCheckStatus(Integer checkstatus) {
        this._checkstatus = checkstatus;
    }

    @ColumnAttribute(Name = "PatrolSum" )
    private Integer _patrolsum;
    public Integer getPatrolSum() {
        return _patrolsum;
    }
    public void setPatrolSum(Integer patrolsum) {
        this._patrolsum = patrolsum;
    }

    @ColumnAttribute(Name = "CompanyPhone" )
    private String _companyphone;
    public String getCompanyPhone() {
        return _companyphone;
    }
    public void setCompanyPhone(String companyphone) {
        this._companyphone = companyphone;
    }

    @ColumnAttribute(Name = "AddressID" )
    private Integer _addressid;
    public Integer getAddressID() {
        return _addressid;
    }
    public void setAddressID(Integer addressid) {
        this._addressid = addressid;
    }

    @ColumnAttribute(Name = "AddressName" )
    private String _addressname;
    public String getAddressName() {
        return _addressname;
    }
    public void setAddressName(String addressname) {
        this._addressname = addressname;
    }

    @ColumnAttribute(Name = "EstablishDate" )
    private Date _establishdate;
    public Date getEstablishDate() {
        return _establishdate;
    }
    public void setEstablishDate(Date establishdate) {
        this._establishdate = establishdate;
    }

    @ColumnAttribute(Name = "OperatingPeriodDate" )
    private Date _operatingperioddate;
    public Date getOperatingPeriodDate() {
        return _operatingperioddate;
    }
    public void setOperatingPeriodDate(Date operatingperioddate) {
        this._operatingperioddate = operatingperioddate;
    }

    @ColumnAttribute(Name = "LssueDate" )
    private Date _lssuedate;
    public Date getLssueDate() {
        return _lssuedate;
    }
    public void setLssueDate(Date lssuedate) {
        this._lssuedate = lssuedate;
    }

    @ColumnAttribute(Name = "ProductionArea" )
    private BigDecimal _productionarea;
    public BigDecimal getProductionArea() {
        return _productionarea;
    }
    public void setProductionArea(BigDecimal productionarea) {
        this._productionarea = productionarea;
    }
}