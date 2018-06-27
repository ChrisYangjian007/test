package com.dalian.sea.parameter;

import com.dalian.sea.model.SaLeaveStockDetail;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author YH
 */
@Data
public class YhLeaveStockDetail extends SaLeaveStockDetail {

    /**
     * 库存数量
     */
    private BigDecimal weight;
}
