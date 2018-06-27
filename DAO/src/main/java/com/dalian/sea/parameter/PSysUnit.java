package com.dalian.sea.parameter;

import com.dalian.sea.model.SysUnit;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/30.
 */
@Data
public class PSysUnit extends SysUnit implements Serializable {

    private Integer level;
    private Boolean isLeaf;
    private String parent;
    private Boolean loaded;
    private Boolean expanded;
    private Boolean icon;


}
