package com.dalian.sea.Models.BA;
import Models.Annotations.ColumnAttribute;
import Models.Annotations.DBAttribute;

import java.io.Serializable;
import java.util.Date;

@DBAttribute(Name = "BA_Shortcuts")
public class Shortcuts implements Serializable {

    @ColumnAttribute(Name = "ShortcutsID" ,Description = "主键",IsPrimaryKey = true )
    private Integer _shortcutsid;
    public Integer getShortcutsID() {
        return _shortcutsid;
    }
    public void setShortcutsID(Integer shortcutsid) {
        this._shortcutsid = shortcutsid;
    }

    @ColumnAttribute(Name = "ModuleID" )
    private Integer _moduleid;
    public Integer getModuleID() {
        return _moduleid;
    }
    public void setModuleID(Integer moduleid) {
        this._moduleid = moduleid;
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
}