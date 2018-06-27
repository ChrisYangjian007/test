package com.dalian.sea.DTO;
/**
 * Created by Administrator on 2017-10-19.
 */
public class TreeJsonDTO {
    private String parentId ;
    private String id ;
    private String text ;
    private String value ;
    private int checkstate ;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCheckstate() {
        return checkstate;
    }

    public void setCheckstate(int checkstate) {
        this.checkstate = checkstate;
    }

    public Boolean getShowcheck() {
        return showcheck;
    }

    public void setShowcheck(Boolean showcheck) {
        this.showcheck = showcheck;
    }

    public Boolean getIsexpand() {
        return isexpand;
    }

    public void setIsexpand(Boolean isexpand) {
        this.isexpand = isexpand;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getAttributeA() {
        return attributeA;
    }

    public void setAttributeA(String attributeA) {
        this.attributeA = attributeA;
    }

    public String getAttributeValueA() {
        return attributeValueA;
    }

    public void setAttributeValueA(String attributeValueA) {
        this.attributeValueA = attributeValueA;
    }

    private Boolean showcheck ;
    /// <summary>
    /// 是否展开
    /// </summary>
    private Boolean isexpand ;
    private Boolean complete ;
    /// <summary>
    /// 是否有子节点
    /// </summary>
    private Boolean hasChildren ;
    private String img ;
    private String title ;
    /// <summary>
    /// 自定义属性
    /// </summary>
    private String attribute ;
    /// <summary>
    /// 自定义属性值
    /// </summary>
    private String attributeValue ;
    /// <summary>
    /// 自定义属性A
    /// </summary>
    private String attributeA ;
    /// <summary>
    /// 自定义属性值A
    /// </summary>
    private String attributeValueA ;
}
