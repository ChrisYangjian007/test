package com.dalian.sea.parameter;

import com.dalian.sea.model.SaLeaveStock;
import lombok.Data;

import java.util.List;

/**
 * @author YH
 */
@Data
public class YhLeaveStock extends SaLeaveStock {

    /**
     * 出库单详情List
     */
    List<YhLeaveStockDetail> saLeaveStockDetails;

    /**
     * 工作流ID
     * （选择的生产任务对应的id）
     */
    Long workFlowId;

    /**
     * 资源Id
     */
    Long resourceId;

}
