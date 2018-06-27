package com.dalian.sea.parameter;

import com.dalian.sea.model.BaUser;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/27.
 */
@Data
public class PUserWarehouse  extends BaUser implements Serializable{

    private Integer isSelected = 0;
}
