package com.dalian.sea.parameter;

import com.dalian.sea.model.BaDataDictionaryDetails;
import com.dalian.sea.model.SaLeaveStock;
import com.dalian.sea.model.SaLeaveStockDetail;
import com.dalian.sea.model.ZsWarehouse;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author YH
 */
@Data
public class SaLeaveStockDetailPara extends SaLeaveStockDetail {

    private SaLeaveStock saLeaveStock;

    private BaDataDictionaryDetails baDataDictionaryDetails;

    private BaDataDictionaryDetails baDataDictionaryDetailsTwo;

    private ZsWarehouse zsWarehouse;
    /**
     * 货物类型id
     */
    private Long isMaterial;

    /**
     * 产品状态id
     */
    private Long productStatus;

    /**
     * 出库库申请人
     */
    private String leavePerson;

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
     */
    private String materialName;

    /**
     * 产品状态名称
     */
    private String productStatusName;

    /**
     * 1:出库记录
     * 2:损耗记录
     */
    private Byte method;


}
