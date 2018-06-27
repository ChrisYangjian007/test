package com.dalian.sea.DTO;

import java.util.Date;

/**
 * Created by Administrator on 2017-10-19.
 */
public class ModuleDTO {
    /// <summary>
    /// 模块主键
    /// </summary>
    private int ID ;
    /// <summary>
    /// 分类(1-目录,2-页面)
    /// </summary>
    private int category ;
    /// <summary>
    /// 编码
    /// </summary>
    private String code ;
    /// <summary>
    /// 名称
    /// </summary>
    private String cName ;
    /// <summary>
    /// 图标
    /// </summary>
    private String icon ;

    public String getDisposeIcon() {
        return disposeIcon;
    }

    private String disposeIcon = this.icon != null ? "/Content/Images/Icon16/" + this.icon : this.icon;

    /// <summary>
    /// 访问地址
    /// </summary>
    private String location ;
    /// <summary>
    /// 目标
    /// </summary>
    private String target ;
    /// <summary>
    /// 是否展开
    /// </summary>
    private int isExpand ;
    /// <summary>
    /// 是否为动态按钮
    /// </summary>
    private int allowButton ;
    /// <summary>
    /// 是否为动态视图
    /// </summary>
    private int allowView ;
    /// <summary>
    /// 是否为动态表单
    /// </summary>
    private int allowForm ;
    /// <summary>
    /// 访问权限
    /// </summary>
    private int authority ;
    /// <summary>
    /// 数据范围
    /// </summary>
    private int dataScope ;
    /// <summary>
    /// 备注
    /// </summary>
    private String remark ;
    /// <summary>
    /// 排序
    /// </summary>
    private int listIndex ;
    /// <summary>
    /// 状态(1-正常,6-删除,2-禁用)
    /// </summary>
    private int status ;
    /// <summary>
    /// 创建时间
    /// </summary>
    private Date createdDate ;
    /// <summary>
    /// 操作人员ID
    /// </summary>
    private int createdUserID ;
    /// <summary>
    /// 创建用户
    /// </summary>
    private String createdUserName ;
    /// <summary>
    /// 更新时间
    /// </summary>
    private Date updatedDate ;
    /// <summary>
    /// 修改用户主键
    /// </summary>
    private int updatedUserID ;
    /// <summary>
    /// 修改用户
    /// </summary>
    private String updatedUserName ;
    /// <summary>
    /// 层标识
    /// </summary>
    private String flag ;
    /// <summary>
    /// 层标识记录名称
    /// </summary>
    private String flagName ;
    /// <summary>
    /// 所在层
    /// </summary>
    private int aLevel ;
    /// <summary>
    /// 上一层ID
    /// </summary>
    private int parentID ;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getIsExpand() {
        return isExpand;
    }

    public void setIsExpand(int isExpand) {
        this.isExpand = isExpand;
    }

    public int getAllowButton() {
        return allowButton;
    }

    public void setAllowButton(int allowButton) {
        this.allowButton = allowButton;
    }

    public int getAllowView() {
        return allowView;
    }

    public void setAllowView(int allowView) {
        this.allowView = allowView;
    }

    public int getAllowForm() {
        return allowForm;
    }

    public void setAllowForm(int allowForm) {
        this.allowForm = allowForm;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public int getDataScope() {
        return dataScope;
    }

    public void setDataScope(int dataScope) {
        this.dataScope = dataScope;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getListIndex() {
        return listIndex;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    public int getaLevel() {
        return aLevel;
    }

    public void setaLevel(int aLevel) {
        this.aLevel = aLevel;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public int getFirstID() {
        return firstID;
    }

    public void setFirstID(int firstID) {
        this.firstID = firstID;
    }

    /// <summary>
    /// 第1层ID
    /// </summary>
    private int firstID ;
}
