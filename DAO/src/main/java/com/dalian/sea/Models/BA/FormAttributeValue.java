package com.dalian.sea.Models.BA;
import Models.Annotations.ColumnAttribute;
import Models.Annotations.DBAttribute;

import java.io.Serializable;

@DBAttribute(Name = "BA_FormAttributeValue")
public class FormAttributeValue implements Serializable {

    @ColumnAttribute(Name = "AttributeValueID" ,Description = "主键",IsPrimaryKey = true )
    private Integer _attributevalueid;
    public Integer getAttributeValueID() {
        return _attributevalueid;
    }
    public void setAttributeValueID(Integer attributevalueid) {
        this._attributevalueid = attributevalueid;
    }

    @ColumnAttribute(Name = "Category" )
    private Integer _category;
    public Integer getCategory() {
        return _category;
    }
    public void setCategory(Integer category) {
        this._category = category;
    }

    @ColumnAttribute(Name = "ModuleID" )
    private Integer _moduleid;
    public Integer getModuleID() {
        return _moduleid;
    }
    public void setModuleID(Integer moduleid) {
        this._moduleid = moduleid;
    }

    @ColumnAttribute(Name = "ObjectID" )
    private Integer _objectid;
    public Integer getObjectID() {
        return _objectid;
    }
    public void setObjectID(Integer objectid) {
        this._objectid = objectid;
    }

    @ColumnAttribute(Name = "ObjectParameterJson" )
    private String _objectparameterjson;
    public String getObjectParameterJson() {
        return _objectparameterjson;
    }
    public void setObjectParameterJson(String objectparameterjson) {
        this._objectparameterjson = objectparameterjson;
    }
}