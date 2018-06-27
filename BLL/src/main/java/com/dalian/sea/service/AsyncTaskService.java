package com.dalian.sea.service;

import com.dalian.sea.json.Json;
import com.dalian.sea.model.*;
import com.dalian.sea.parameter.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * @author wxt60
 * @date 2018/1/30
 */
public interface AsyncTaskService {
    Future<String> asyncInvokeReturnFuture(int i) throws InterruptedException;


    /**
     * 产品导入
     * @param objects
     * @param lists
     * @param userId
     * @param size
     * @param index
     * @return
     * @throws InterruptedException
     */
    Future<List<Json>> productImport(List<List<Object>> objects,List<List<Object>> lists,int size,int index, Long userId) throws InterruptedException ;

    /**产品同步
     * @param objects
     * @return
     */
    Future<Boolean>  productSynchronization(List<ProductInfo> objects);





    /**订单更新
     * @param lists
     * @param lists1
     * @return
     */
    List<Future<Boolean>> orderAndDetailSynchronization(List<List<OrderInfo>> lists,List<List<OrderDetailInfo>> lists1);

    /**上传订单信息
     * @param orderList
     * @param orderTempList
     * @param errorList
     * @param rom
     * @return
     */
    Boolean importOrder( List<SaOrder> orderList,List<PBoxOrderTemp> orderTempList,List<SaError> errorList,String rom) ;






    /**
     * @param orderList
     */
   void insertBoxOrderByBatch(List<BoxOrder> orderList);

    /**  采集信息上传
     * @param zsBatch
     * @param batchTempList
     * @param errorList
     * @param rom
     * @return
     */
    Boolean importBatchByList(ZsBatch zsBatch, List<BoxBatchTemp> batchTempList, List<SaError> errorList, String rom);
}
