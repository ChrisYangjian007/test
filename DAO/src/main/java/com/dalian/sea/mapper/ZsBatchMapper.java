package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsBatch;
import com.dalian.sea.parameter.PZsBatch;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsBatchMapper extends Mapper<ZsBatch> {
    /**
     * 批次添加
     *
     * @param batch
     * @return
     */
    Integer insertBatchSelective(ZsBatch batch);

    /**
     * 根据任务编号查询批次
     *
     * @param batch
     * @return
     */
    PZsBatch selectBatchByBatchUnique(PZsBatch batch);

    /**
     * 列表查询
     *
     * @param batch
     * @return
     */
    List<PZsBatch> selectListBatchByBatch(PZsBatch batch);

    /**
     * 当月绑定量
     *
     * @return 当月绑定量
     */
    Integer monthLabelNumber();

    /**
     * 总绑定量
     *
     * @return 总绑定量
     */
    Integer totalLabelNumber();

    /**
     * 根据日期查询绑定量
     *
     * @param date
     * @return
     */
    Integer getBindCount(String date);


    /**
     * 根据年月
     * 查询绑定量
     *
     * @param date
     * @return
     */
    Integer getBindByYearMonth(String date);

    /**
     * 根据id获取
     *
     * @param batchId
     * @return
     */
    PZsBatch getPBatchById(Long batchId);

    List<PZsBatch> getBatchListByBatch(PZsBatch batch);

    /**查询已打包
     * @param batch
     * @return
     */
    List<PZsBatch> selectZsBatchByBatchIsPack(PZsBatch batch);
}