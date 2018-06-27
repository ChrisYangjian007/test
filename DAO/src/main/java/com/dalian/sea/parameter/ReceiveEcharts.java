package com.dalian.sea.parameter;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author YH
 */
@Data
public class ReceiveEcharts {
    /**
     * 海参List
     */
    List<BigDecimal> hsList;

    /**
     * 鲍鱼List
     */
    List<BigDecimal> byList;

    /**
     * 虾仁List
     */
    List<BigDecimal> xrList;

    /**
     * 包材List
     */
    List<BigDecimal> bcList;
}
