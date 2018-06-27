package com.dalian.sea.parameter;

import com.dalian.sea.model.BaFormAttributeValue;
import com.dalian.sea.model.ZsWorkProcess;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * PProduceTask
 *
 * @author TONE
 * @date 2018/3/13.
 */
@Data
public class PWorkProcessPDA extends ZsWorkProcess implements Serializable {

    /**
     * 出库详情
     */
    private List<SaLeaveStockDetailPara> saLeaveStockDetailParaList;

    private String createUserName;

    private String updateUserName;

    private PZsProduceTask produceTask;

    /**
     * 操作
     */
    private List<PBaFormAttribute> operation;

    /**
     * 操作
     */
    private BaFormAttributeValue operationAttributeValue;
    /**
     * 操作
     */
    private List<AttributeValueData> operationValueDataList;
    /**
     * 审核
     */
    private List<PBaFormAttribute> auditor;
    /**
     * 审核
     */
    private BaFormAttributeValue auditorAttributeValue;
    /**
     * 审核
     */
    private List<AttributeValueData> auditorValueDataList;
    /**
     * 巡检
     */
    private List<PBaFormAttribute> inspection;
    /**
     * 巡检
     */
    private BaFormAttributeValue inspectionAttributeValue;
    /**
     * 巡检
     */
    private List<AttributeValueData> inspectionValueDataList;

}
