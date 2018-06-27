package com.dalian.sea.mapper;

import com.dalian.sea.model.SaLeaveStock;
import com.dalian.sea.parameter.PSaLeaveStock;
import com.dalian.sea.parameter.YhLeaveStock;
import com.dalian.sea.parameter.YhLeaveStockDetail;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface SaLeaveStockMapper extends Mapper<SaLeaveStock> {

    /**
     * 通过生产任务获取出库信息
     *
     * @param id
     * @return
     */
    List<PSaLeaveStock> getLeaveStockByProduceTask(Long id);


    /**
     * 通过出库编号
     * 获取生产任务编号，生产任务名称
     */
    SaLeaveStock getLeaveStockByLeaveNo(String leaveNo);


    /**
     * 新建出库单
     *
     * @param yhLeaveStock
     *@param date
     * @param userId
     * @return
     */
    Long InsertLeaveStock(@Param("yh") YhLeaveStock yhLeaveStock , @Param("date") Date date, @Param("userId") Long userId);
}