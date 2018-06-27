package com.dalian.sea.parameter;

import com.dalian.sea.model.ZsSchedule;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author YH
 */
@Data
public class ZsSchedulePara extends ZsSchedule {

    /**
     * 任务完成百分比
     */
    private BigDecimal percent = new BigDecimal(0.0000).setScale(4, BigDecimal.ROUND_HALF_UP);

    /**
     * 已完成数量
     */
    private BigDecimal completeCount;
}
