package com.dalian.sea.service;

import com.dalian.sea.model.*;
import com.dalian.sea.parameter.BatchEnterStockDetail;
import com.dalian.sea.parameter.PZsBatch;

import java.util.List;
import java.util.concurrent.Future;

/**
 *
 * @author 杨建
 * @date 2018/3/28
 */
public interface ZsBatchService {


    /**查询列表
     * @param batch
     * @param page
     * @param row
     * @return
     */
    List<PZsBatch> selectZsBatchByBatch(PZsBatch batch,Integer page,Integer row);

    /**批量插入
     * @param boxBatchTempList
     */
    Future<Boolean> insertBoxBatchByBatch(List<BoxBatchTemp> boxBatchTempList);

    /**批量插入错误日志
     * @param errorList
     */
     void  insertErrorByBatch(List<SaError> errorList);

    /**获取批次信息
     * @param batch
     * @return
     */
     PZsBatch  getBatchByBatch(PZsBatch batch);


    /**根据批次信息查询批次列表
     * @param batch
     * @return
     */
    List<PZsBatch> selectZsBatchByBatch1(PZsBatch batch);

    /**
     * 根据id获取
     * @param batchId
     * @return
     */
    PZsBatch getPBatchById(Long batchId);

    /**
     * 根据批次id获取入库详情数据
     */
    BatchEnterStockDetail getEnterStockDetailByBatchId(Long batchId);

    /**已打包的批次
     * @param batch
     * @param page
     * @param row
     * @return
     */
    List<PZsBatch> selectZsBatchByBatchIsPack(PZsBatch batch,Integer page,Integer row);
}
