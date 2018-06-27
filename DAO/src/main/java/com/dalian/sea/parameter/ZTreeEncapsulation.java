package com.dalian.sea.parameter;


import lombok.Data;

import java.io.Serializable;

/**
 * Created by TAO on 2017/5/9.
 * zTree封装类型
 */
@Data
public class ZTreeEncapsulation implements Serializable {
    private Long id;
    private Long pId;
    private Integer level;
    private String name;
    private String text;
    private String icon;
    private String iconClose;
    private String iconOpen;
    private Boolean open = false;
    private Boolean halfCheck = false;
    private Boolean checked = false;
    private Boolean isParent = false;
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "ZTreeEncapsulation{" +
                "id=" + id +
                ", pId=" + pId +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", icon='" + icon + '\'' +
                ", iconClose='" + iconClose + '\'' +
                ", iconOpen='" + iconOpen + '\'' +
                ", open=" + open +
                ", halfCheck=" + halfCheck +
                ", checked=" + checked +
                ", isParent=" + isParent +
                '}';
    }
}
