package com.dalian.sea.parameter;

import com.dalian.sea.model.ZsTestingEquipmentDetail;
import lombok.Data;

/**
 * Created by Administrator on 2018/3/8.
 */
@Data
public class PTestingEquipmentDetail extends ZsTestingEquipmentDetail {
    /***
     * c_name数字字典名称
     */
    private String testCName;
}
