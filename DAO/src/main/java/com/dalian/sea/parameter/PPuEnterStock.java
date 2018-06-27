package com.dalian.sea.parameter;

import com.dalian.sea.model.PuEnterStock;
import com.dalian.sea.model.PuEnterStockDetail;
import com.dalian.sea.model.ZsWarehouse;
import lombok.Data;

/**
 * Created by Administrator on 2018/3/1.
 */
@Data
public class PPuEnterStock extends PuEnterStock{

    /**
     * 入库详情
     */
    private PuEnterStockDetail puEnterStockDetail;

    /**
     *仓库信息
     */
    private ZsWarehouse zsWarehouse;

    /**
     * 入库仓库名称
     */
    private String warehouseName;
}
