package com.dalian.sea.service;

import com.dalian.sea.model.SaLeaveStock;
import com.dalian.sea.parameter.PSaLeaveStock;
import com.dalian.sea.parameter.SaLeaveStockDetailPara;
import com.dalian.sea.parameter.YhLeaveStock;
import com.dalian.sea.parameter.YhLeaveStockDetail;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/28.
 */
public interface SaLeaveStockService {

    /**
     * 通过生产任务获取出库信息
     * @param id
     * @return
     */
    List<PSaLeaveStock> getLeaveStockByProduceTask(Long id);

    /**
     * 新建出库单
     *
     * @param yhLeaveStock
     * @return
     */
    Long InsertLeaveStock(YhLeaveStock yhLeaveStock, Date date, Long userId);

    /**
     * 通过出库编号
     * 获取生产任务编号，生产任务名称
     */
    SaLeaveStock getLeaveStockByLeaveNo(String leaveNo);
}
