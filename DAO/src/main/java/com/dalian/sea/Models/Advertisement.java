package com.dalian.sea.Models;

import java.util.Date;

//@DBAttribute(Name = "Ldw_Advertisement")
public class Advertisement {

//    @ColumnAttribute(Name = "id",Description = "主键",IsPrimaryKey = true)
    private Long id;
//    @ColumnAttribute(Name = "advertisement_name",Description = "名称")
    private String advertisementName;
//    @ColumnAttribute(Name = "advertisement_url",Description = "URL")
    private String advertisementUrl;
//    @ColumnAttribute(Name = "location",Description = "公司主键")
    private String location;
//    @ColumnAttribute(Name = "create_user_id",Description = "公司主键")
    private Long createUserId;
//    @ColumnAttribute(Name = "update_user_id",Description = "公司主键")
    private Long updateUserId;
//    @ColumnAttribute(Name = "create_date",Description = "创建时间")
    private Date createDate;
//    @ColumnAttribute(Name = "update_date",Description = "公司主键")
    private Date updateDate;
//    @ColumnAttribute(Name = "status",Description = "公司主键")
    private Integer status;
//    @ColumnAttribute(Name = "is_delete",Description = "公司主键")
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdvertisementName() {
        return advertisementName;
    }

    public void setAdvertisementName(String advertisementName) {
        this.advertisementName = advertisementName == null ? null : advertisementName.trim();
    }

    public String getAdvertisementUrl() {
        return advertisementUrl;
    }

    public void setAdvertisementUrl(String advertisementUrl) {
        this.advertisementUrl = advertisementUrl == null ? null : advertisementUrl.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}