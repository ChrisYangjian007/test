package com.dalian.sea.mapper;

import com.dalian.sea.model.zsProduceTaskDetail;
import com.dalian.sea.parameter.PZsProduceTaskDetail;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author YH
 */
public interface zsProduceTaskDetailMapper extends Mapper<zsProduceTaskDetail> {

    /**
     * 根据生产任务id
     * 获取生产任务对应产品明细
     *
     * @param produceTaskId
     * @return
     */
    List<zsProduceTaskDetail> getDetailByProduceTaskId(Long produceTaskId);

    /**
     * 新建生产任务对应产品明细
     *
     * @param zsProduceTaskDetail
     * @param date
     * @param userId
     * @return
     */
    Long newProduceTaskDetail(@Param("zs") zsProduceTaskDetail zsProduceTaskDetail, @Param("date") Date date, @Param("userId") Long userId);

    /**
     * 根据生产任务编号查询
     *
     * @param pZsProduceTaskDetail
     * @return
     */
    List<PZsProduceTaskDetail> getProduceTaskDetailByProduceTaskNo(PZsProduceTaskDetail pZsProduceTaskDetail);

    /**
     * 根据批次号查询
     *
     * @param pZsProduceTaskDetail
     * @return
     */
    List<PZsProduceTaskDetail> getProduceTaskDetailByBatchNo(PZsProduceTaskDetail pZsProduceTaskDetail);

    /**
     * 多条件查询
     * 生产任务对应产品明细
     *
     * @param zsProduceTaskDetail
     * @return
     */
    PZsProduceTaskDetail selectByZsProduceTaskDetail(@Param("zs") zsProduceTaskDetail zsProduceTaskDetail);

    /**
     * 修改
     * 生产任务对应产品详情
     * 添加数量
     *
     * @param zsweight
     * @param produceTaskDetailId
     * @param date
     * @param userId
     * @return
     */
    Integer updateProduceTaskDetail(@Param("zsweight") BigDecimal zsweight, @Param("produceTaskDetailId") Long produceTaskDetailId, @Param("date") Date date, @Param("userId") Long userId);

    /**
     * 根据生产任务Id查询
     *
     * @param produceTaskId
     * @param ids
     * @return
     */
    List<PZsProduceTaskDetail> getProduceTaskDetailByProduceTaskId(@Param("produceTaskId") Long produceTaskId, @Param("ids") List<Long> ids);
}