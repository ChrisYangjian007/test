package com.dalian.sea.parameter;

import com.dalian.sea.model.SaLeaveStock;
import com.dalian.sea.model.SaLeaveStockDetail;
import com.dalian.sea.model.ZsWarehouse;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/3/1.
 */
@Data
public class PSaLeaveStock extends SaLeaveStock {

    /**
     * 出库详情
     */
    private SaLeaveStockDetail saLeaveStockDetail;

    /**
     * 仓库
     */
    private ZsWarehouse warehouse;

    /**
     * 仓库名称
     */
    private String warehouseName;
}
