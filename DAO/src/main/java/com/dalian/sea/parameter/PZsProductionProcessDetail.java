package com.dalian.sea.parameter;

import com.dalian.sea.json.ImageJson;
import com.dalian.sea.model.ZsProductionProcessDetail;
import lombok.Data;

/**
 * Created by Administrator on 2018/3/26.
 */
@Data
public class PZsProductionProcessDetail extends ZsProductionProcessDetail{

    private ImageJson imageJson;

    /**
     * 生产过程名称
     */
    private String productionProcessName;
}
