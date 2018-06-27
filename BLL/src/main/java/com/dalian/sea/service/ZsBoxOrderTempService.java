package com.dalian.sea.service;

import com.dalian.sea.model.SaError;
import com.dalian.sea.parameter.PBoxOrderTemp;

import java.util.List;
import java.util.concurrent.Future;

/**
 * ZsBoxOrderTempService
 *
 * @author TONE
 * @date 2018/3/28.
 */
public interface ZsBoxOrderTempService {
    Future<Boolean>  insertBoxOrderListByBatch(List<PBoxOrderTemp> orderTempList);
    Future<Boolean> insertErrorListByBatch(List<SaError> errorList);
}
