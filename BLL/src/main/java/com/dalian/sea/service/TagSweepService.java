package com.dalian.sea.service;

import com.dalian.sea.model.TagSweep;
import com.dalian.sea.parameter.PTagSweep;
import com.dalian.sea.parameter.ScanAreaDistribution;
import com.dalian.sea.parameter.TagSweepPara;

import java.util.List;

/**
 * @author 杨建
 * @date 2018/3/15
 */
public interface TagSweepService {
    /**
     * 查询使用记录
     *
     * @param tagSweep
     * @return
     */
    List<PTagSweep> selectTagSweepList(PTagSweep tagSweep, Integer page, Integer rows);

    /**
     * 获取总扫码量
     *
     * @return
     */
    Integer getSweepTotal();

    /**
     * 获取当月扫码量
     *
     * @return
     */
    Integer getMonthSweep();

    /**
     * 扫码异常总量
     *
     * @return
     */
    Integer sweepExceptionTotal();

    /**
     * 今日扫码异常
     *
     * @return
     */
    Integer todaySweepException();

    /**
     * 扫码地区分布图
     *
     * @return
     */
    List<ScanAreaDistribution> getScanAreaDistribution();

    /**
     * 本周扫码量统计图
     *
     * @return
     */
    List<Integer> weekSweep();

    /**
     * 本月扫码量统计图
     *
     * @return
     */
    List<Integer> monthSweep();


    /**
     * 本年扫码量统计图
     *
     * @return
     */
    List<Integer> yearSweep();

    /**
     * 获取扫码区域分布详情
     * @return
     * @param page
     * @param rows
     */
    List<TagSweepPara> getGridTable(int page,int rows);

    /**
     *根据batchNo获取次数
     * @param batchNo
     * @return
     */
    Integer getSweepCountByBatchNo(String batchNo);

    /**
     * 添加扫码记录
     * @param tagSweep
     * @return
     */
    Boolean addSweep(TagSweep tagSweep);
}
