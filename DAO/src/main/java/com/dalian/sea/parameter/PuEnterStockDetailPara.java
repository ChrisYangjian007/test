package com.dalian.sea.parameter;

import com.dalian.sea.model.BaDataDictionaryDetails;
import com.dalian.sea.model.PuEnterStock;
import com.dalian.sea.model.PuEnterStockDetail;
import com.dalian.sea.model.ZsWarehouse;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author YH
 */
@Data
public class PuEnterStockDetailPara extends PuEnterStockDetail {

    private PuEnterStock puEnterStock;

    private BaDataDictionaryDetails baDataDictionaryDetails;

    private BaDataDictionaryDetails baDataDictionaryDetailsTwo;

    private ZsWarehouse zsWarehouse;

    private BigDecimal materialWeightSum;//原料数量

    private BigDecimal inWeightSum =new BigDecimal("0.00");//新增入库总数量

   /**
     * 货物类型id
     */
    private Long isMaterial;


    /**
     * 产品状态id
     */
    private Long productStatus;


    /**
     * 入库申请人
     */
    private String enterPerson;

    /**
     * 经办人
     */
    private String brokerage;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    /**
     * 截止时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 货物类型名称
     *
     * @return
     */
    private String materialName;

    /**
     * 产品名称
     */
    private String productStatusName;

    /**
     * 产品大类ID
     */
    private Long productTypeId;

    /**
     * 产品大类名称
     */
    private String productTypeName;

    /**
     * 货物类型数组
     */
    private Long[] materialArray;

    /**
     * 产品大类数组
     */
    private Long[] productTypeArray;

    /**
     * 产品小类数组
     */
    private Long[] productIdArray;

    /**
     * 规格数组
     */
    private String[] productSpecArray;

}
