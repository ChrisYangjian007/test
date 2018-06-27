package com.dalian.sea.parameter;

import lombok.Data;

/**
 * @author YH
 */
@Data
public class BatchEnterStockDetail {
    /**
     * 货物类型id
     */
    private Long goodsTypeId;

    /**
     * 货物类型
     */
    private String goodsType;

    /**
     * 产品小类id
     */
    private Long productId;

    /**
     * 产品小类名称
     */
    private String productName;

    /**
     * 产品状态id
     */
    private Long productStatusId;

    /**
     * 产品状态
     */
    private String productStatus;

    /**
     * 规格
     */
    private String productSpec;

}
