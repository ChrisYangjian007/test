package com.dalian.sea.parameter;


import com.dalian.sea.model.ZsProduceTask;
import com.dalian.sea.model.zsProduceTaskDetail;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 陈逸文 on 2018/3/22.
 */
@Data
public class PZsProduceTaskDetail extends zsProduceTaskDetail {

    private String  produceTaskNo;

    private Long goodsTypeId;

    private String goodsType;

    private ZsProduceTask  produceTask;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 产品状态
     */
    private String productStatusName;

    /**
     * 剩余库存
     */
    private BigDecimal resWeight;

}
