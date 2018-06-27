package com.dalian.sea.parameter;

import com.dalian.sea.model.ZsCompanyProduct;
import lombok.Data;

import java.io.Serializable;

/**
 * PZsCompanyProduct
 *
 * @author xintao
 * @date 2018/1/23
 */
@Data
public class PZsCompanyProduct extends ZsCompanyProduct implements Serializable {

    /**
     * 产品线Id
     */
    private Long productLineId;

    /**
     * 产品线
     */
    private String productLine;

    /**
     * 产品大类Id
     */
    private Long productCategoryId;

    /**
     * 产品大类
     */
    private String productCategory;

    /**
     * 类型名称
     */
    private String typeName;


}
