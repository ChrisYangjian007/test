package com.dalian.sea.parameter;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author YH
 */
@Data
public class YhStock implements Serializable {
    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 货物类型id
     */
    private Long goodsTypeId;

    /**
     * 货物类型名称
     */
    private String goodsType;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 规格
     */
    private String productSpecName;

    /**
     *原料数量
     */
    private BigDecimal materialWeight;

    /**
     * 单位id
     */
    private Long unitId;

    /**
     * 单位名称
     */
    private String unitName;
}
