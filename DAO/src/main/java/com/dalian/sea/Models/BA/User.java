package com.dalian.sea.Models.BA;

import Models.Annotations.ColumnAttribute;
import Models.Annotations.DBAttribute;

import java.io.Serializable;
import java.util.Date;

@DBAttribute(Name = "BA_User")
public class User implements Serializable {
    @ColumnAttribute(Name = "UserID",Description = "主键",IsPrimaryKey = true)
    private Integer userid;

    @ColumnAttribute(Name = "CompanyID")
    private Integer companyid;

    @ColumnAttribute(Name = "DepartmentID")
    private Integer departmentid;

    @ColumnAttribute(Name = "UserAuthorityType")
    private Byte userauthoritytype;

    @ColumnAttribute(Name = "Code")
    private String code;

    @ColumnAttribute(Name = "Account")
    private String account;

    @ColumnAttribute(Name = "Password")
    private String password;

    @ColumnAttribute(Name = "Secretkey")
    private String secretkey;

    @ColumnAttribute(Name = "CName")
    private String cname;

    @ColumnAttribute(Name = "Spell")
    private String spell;

    @ColumnAttribute(Name = "Gender")
    private Byte gender;

    @ColumnAttribute(Name = "Birthday")
    private Date birthday;

    @ColumnAttribute(Name = "Mobile")
    private String mobile;

    @ColumnAttribute(Name = "Telephone")
    private String telephone;

    @ColumnAttribute(Name = "OICQ")
    private String oicq;

    @ColumnAttribute(Name = "Email")
    private String email;

    @ColumnAttribute(Name = "ChangePasswordDate")
    private Date changepassworddate;

    @ColumnAttribute(Name = "OpenID")
    private Integer openid;

    @ColumnAttribute(Name = "LogOnCount")
    private Integer logoncount;

    @ColumnAttribute(Name = "PreviousVisit")
    private Date previousvisit;

    @ColumnAttribute(Name = "LastVisit")
    private Date lastvisit;

    @ColumnAttribute(Name = "Online")
    private Integer online;

    @ColumnAttribute(Name = "Remark")
    private String remark;

    @ColumnAttribute(Name = "ListIndex")
    private Integer listindex;

    @ColumnAttribute(Name = "Status")
    private Integer status;

    @ColumnAttribute(Name = "CreatedDate")
    private Date createddate;

    @ColumnAttribute(Name = "CreatedUserID")
    private Integer createduserid;

    @ColumnAttribute(Name = "CreatedUserName")
    private String createdusername;

    @ColumnAttribute(Name = "UpdatedDate")
    private Date updateddate;

    @ColumnAttribute(Name = "UpdatedUserID")
    private Integer updateduserid;

    @ColumnAttribute(Name = "UpdatedUserName")
    private String updatedusername;

    @ColumnAttribute(Name = "AuthStatus")
    private Byte authstatus;

    @ColumnAttribute(Name = "AreaID")
    private Integer areaid;

    @ColumnAttribute(Name = "WeiXin")
    private String weixin;

    @ColumnAttribute(Name = "IsControl")
    private Integer iscontrol;

    @ColumnAttribute(Name = "HeadImage")
    private String headimage;

    @ColumnAttribute(Name = "ParentID")
    private Integer parentid;
    @ColumnAttribute(Name = "CheckStatus")
    private Integer checkstatus;
    @ColumnAttribute(Name = "Education")
    private String education;
    @ColumnAttribute(Name = "AddressID")
    private Integer addressid;
    @ColumnAttribute(Name = "AddressDetail")
    private String addressdetail;
    @ColumnAttribute(Name = "Residence")
    private String residence;

    private static final long serialVersionUID = 1L;

    /**
     * @return UserID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return CompanyID
     */
    public Integer getCompanyid() {
        return companyid;
    }

    /**
     * @param companyid
     */
    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    /**
     * @return DepartmentID
     */
    public Integer getDepartmentid() {
        return departmentid;
    }

    /**
     * @param departmentid
     */
    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * @return UserAuthorityType
     */
    public Byte getUserauthoritytype() {
        return userauthoritytype;
    }

    /**
     * @param userauthoritytype
     */
    public void setUserauthoritytype(Byte userauthoritytype) {
        this.userauthoritytype = userauthoritytype;
    }

    /**
     * @return Code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return Account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Secretkey
     */
    public String getSecretkey() {
        return secretkey;
    }

    /**
     * @param secretkey
     */
    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    /**
     * @return CName
     */
    public String getCname() {
        return cname;
    }

    /**
     * @param cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     * @return Spell
     */
    public String getSpell() {
        return spell;
    }

    /**
     * @param spell
     */
    public void setSpell(String spell) {
        this.spell = spell;
    }

    /**
     * @return Gender
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * @return Birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return Mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return Telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return OICQ
     */
    public String getOicq() {
        return oicq;
    }

    /**
     * @param oicq
     */
    public void setOicq(String oicq) {
        this.oicq = oicq;
    }

    /**
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return ChangePasswordDate
     */
    public Date getChangepassworddate() {
        return changepassworddate;
    }

    /**
     * @param changepassworddate
     */
    public void setChangepassworddate(Date changepassworddate) {
        this.changepassworddate = changepassworddate;
    }

    /**
     * @return OpenID
     */
    public Integer getOpenid() {
        return openid;
    }

    /**
     * @param openid
     */
    public void setOpenid(Integer openid) {
        this.openid = openid;
    }

    /**
     * @return LogOnCount
     */
    public Integer getLogoncount() {
        return logoncount;
    }

    /**
     * @param logoncount
     */
    public void setLogoncount(Integer logoncount) {
        this.logoncount = logoncount;
    }

    /**
     * @return PreviousVisit
     */
    public Date getPreviousvisit() {
        return previousvisit;
    }

    /**
     * @param previousvisit
     */
    public void setPreviousvisit(Date previousvisit) {
        this.previousvisit = previousvisit;
    }

    /**
     * @return LastVisit
     */
    public Date getLastvisit() {
        return lastvisit;
    }

    /**
     * @param lastvisit
     */
    public void setLastvisit(Date lastvisit) {
        this.lastvisit = lastvisit;
    }

    /**
     * @return Online
     */
    public Integer getOnline() {
        return online;
    }

    /**
     * @param online
     */
    public void setOnline(Integer online) {
        this.online = online;
    }

    /**
     * @return Remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return ListIndex
     */
    public Integer getListindex() {
        return listindex;
    }

    /**
     * @param listindex
     */
    public void setListindex(Integer listindex) {
        this.listindex = listindex;
    }

    /**
     * @return Status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return CreatedDate
     */
    public Date getCreateddate() {
        return createddate;
    }

    /**
     * @param createddate
     */
    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    /**
     * @return CreatedUserID
     */
    public Integer getCreateduserid() {
        return createduserid;
    }

    /**
     * @param createduserid
     */
    public void setCreateduserid(Integer createduserid) {
        this.createduserid = createduserid;
    }

    /**
     * @return CreatedUserName
     */
    public String getCreatedusername() {
        return createdusername;
    }

    /**
     * @param createdusername
     */
    public void setCreatedusername(String createdusername) {
        this.createdusername = createdusername;
    }

    /**
     * @return UpdatedDate
     */
    public Date getUpdateddate() {
        return updateddate;
    }

    /**
     * @param updateddate
     */
    public void setUpdateddate(Date updateddate) {
        this.updateddate = updateddate;
    }

    /**
     * @return UpdatedUserID
     */
    public Integer getUpdateduserid() {
        return updateduserid;
    }

    /**
     * @param updateduserid
     */
    public void setUpdateduserid(Integer updateduserid) {
        this.updateduserid = updateduserid;
    }

    /**
     * @return UpdatedUserName
     */
    public String getUpdatedusername() {
        return updatedusername;
    }

    /**
     * @param updatedusername
     */
    public void setUpdatedusername(String updatedusername) {
        this.updatedusername = updatedusername;
    }

    /**
     * @return AuthStatus
     */
    public Byte getAuthstatus() {
        return authstatus;
    }

    /**
     * @param authstatus
     */
    public void setAuthstatus(Byte authstatus) {
        this.authstatus = authstatus;
    }

    /**
     * @return AreaID
     */
    public Integer getAreaid() {
        return areaid;
    }

    /**
     * @param areaid
     */
    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    /**
     * @return WeiXin
     */
    public String getWeixin() {
        return weixin;
    }

    /**
     * @param weixin
     */
    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    /**
     * @return IsControl
     */
    public Integer getIscontrol() {
        return iscontrol;
    }

    /**
     * @param iscontrol
     */
    public void setIscontrol(Integer iscontrol) {
        this.iscontrol = iscontrol;
    }

    public String getHeadimage() {
        return headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(Integer checkstatus) {
        this.checkstatus = checkstatus;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public String getAddressdetail() {
        return addressdetail;
    }

    public void setAddressdetail(String addressdetail) {
        this.addressdetail = addressdetail;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }
}