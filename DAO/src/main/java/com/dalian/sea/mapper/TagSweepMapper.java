package com.dalian.sea.mapper;

import com.dalian.sea.model.TagSweep;
import com.dalian.sea.parameter.PTagSweep;
import com.dalian.sea.parameter.TagSweepPara;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TagSweepMapper extends Mapper<TagSweep> {
    List<PTagSweep> selectTagSweepListByTagSweep(PTagSweep tagSweep);

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
     * 扫码地区
     *
     * @return
     */
    List<String> getUpdateArea();

    /**
     * 获取所有扫码记录
     *
     * @return
     */
    List<TagSweep> getAllSweep();

    /**
     * 根据地区
     * 查询扫码记录数量
     *
     * @param updateArea
     * @return
     */
    Integer countByArea(String updateArea);


    /**
     * 根据日期查询记录数(年月日)
     *
     * @param date
     * @return
     */
    Integer getCountByDate(String date);

    /**
     * 根据年月查询记录数
     *
     * @param date
     * @return
     */
    Integer getCountByYearMonth(String date);

    /**
     * 获取扫码区域分布详情
     * @return
     */
    List<TagSweepPara> getGridTable();

    /**
     * 查询二维码使用次数
     * @param qrCode
     * @return
     */
    Integer getCountBycode(String qrCode);

    /**
     * 根据batchNo获取次数
     * @param batchNo
     * @return
     */
    Integer getSweepCountByBatchNo(String batchNo);

    /**
     * 添加记录
     * @param tagSweep
     * @return
     */
    Integer addSweep(TagSweep tagSweep);
}