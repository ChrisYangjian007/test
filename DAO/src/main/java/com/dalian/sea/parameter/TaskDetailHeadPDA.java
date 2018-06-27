package com.dalian.sea.parameter;

import lombok.Data;

import java.util.List;

/**
 * TaskDetailHeadPDA
 *
 * @author TONE
 * @date 2018/3/16.
 */
@Data
public class TaskDetailHeadPDA {
    /**
     * 任务详情头部
     */
    private List<PBaFormAttributeValue> taskDetailHead;

    /**
     * 出库详情
     */
    private List<SaLeaveStockDetailPara> saLeaveStockDetailParaList;
}
