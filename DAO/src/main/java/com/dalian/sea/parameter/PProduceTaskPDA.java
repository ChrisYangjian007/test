package com.dalian.sea.parameter;

import com.dalian.sea.model.ZsProduceTask;
import com.dalian.sea.model.ZsWorkFlow;
import lombok.Data;

import java.util.List;

/**
 * PProduceTaskPDA
 *
 * @author TONE
 * @date 2018/3/14.
 */
@Data
public class PProduceTaskPDA extends ZsProduceTask {

    /**
     * 出库详情
     */
    private List<SaLeaveStockDetailPara> saLeaveStockDetailParaList;

    /**
     * 工艺
     */
    private List<PWorkProcessPDA> workProcessPDAList;

    /**
     * 工艺
     */
    private ZsWorkFlow workFlow;


}
