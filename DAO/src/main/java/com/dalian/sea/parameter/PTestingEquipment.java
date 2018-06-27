package com.dalian.sea.parameter;

import com.dalian.sea.model.ZsTestingEquipment;
import lombok.Data;

/**
 * Created by Administrator on 2018/3/8.
 */
@Data
public class PTestingEquipment extends ZsTestingEquipment {
    /***
     * c_name数字字典名称
     */
    private String testCName;
}
