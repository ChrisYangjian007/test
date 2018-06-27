package com.dalian.sea.service;

import com.dalian.sea.model.ZsSchedule;
import com.dalian.sea.parameter.ZsSchedulePara;

import java.util.List;

/**
 * @author YH
 */
public interface ZsScheduleService {

    /**
     * 新建生产计划
     *
     * @param zsSchedules
     * @param userId
     * @return
     */
    Boolean addSchedule(List<ZsSchedule> zsSchedules, Long userId,String userName);

    /**
     * 查询当月是否有生产计划
     *
     * @return
     */
    Boolean duringMonthSchedule();

    /**
     * 生产计划历史表格
     *
     * @param page
     * @param rows
     * @return
     */
    List<ZsSchedulePara> getAllSchedule(int page, int rows);

    /**
     * 当月生产计划
     *
     * @return
     */
    List<ZsSchedulePara> getMonthSchedule();

    /**
     * 修改当月生产计划
     * @param list
     * @return
     */
    Boolean updateMonthSchedule(List<ZsSchedule> list,Long userId);
}
