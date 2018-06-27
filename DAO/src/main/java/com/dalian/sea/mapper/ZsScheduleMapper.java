package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsSchedule;
import com.dalian.sea.parameter.ZsSchedulePara;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author YH
 */
public interface ZsScheduleMapper extends Mapper<ZsSchedule> {

    /**
     * 新建生产计划
     *
     * @param zsSchedules
     * @param userId
     * @param date
     * @param userName
     * @return
     */
    Integer addSchedule(@Param("list") List<ZsSchedule> zsSchedules, @Param("userId") Long userId, @Param("date") Date date, @Param("userName") String userName);

    /**
     * 查询当月生产计划数量
     *
     * @return
     */
    Integer duringMonthSchedule();

    /**
     * 生产计划历史表格
     *
     * @return
     */
    List<ZsSchedulePara> getAllSchedule();


    /**
     * 当月生产计划
     *
     * @return
     */
    List<ZsSchedulePara> getMonthSchedule();

    /**
     * 根据id修改当月生产计划
     * @param zs
     * @param userId
     * @return
     */
    Integer updateMonthSchedule(@Param("zs") ZsSchedule zs,@Param("userId") Long userId);
}