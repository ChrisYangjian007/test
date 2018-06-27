package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BoxOrderTempMapper;
import com.dalian.sea.mapper.SaErrorMapper;
import com.dalian.sea.model.SaError;
import com.dalian.sea.parameter.PBoxOrderTemp;
import com.dalian.sea.service.ZsBoxOrderTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

/**
 * ZsBoxOrderTempServiceImpl
 *
 * @author TONE
 * @date 2018/3/28.
 */
@Service("ZsBoxOrderTempService")
public class ZsBoxOrderTempServiceImpl implements ZsBoxOrderTempService {
    @Autowired
    private BoxOrderTempMapper boxOrderTempMapper;
    @Autowired
    private SaErrorMapper errorMapper;

    @Override
    @Async
    public Future<Boolean> insertBoxOrderListByBatch(List<PBoxOrderTemp> orderTempList) {
        if (orderTempList.size() > 0) {
            boxOrderTempMapper.insertBoxOrderTempByBatch(orderTempList);
        }
        return new AsyncResult<Boolean>(true);
    }

    @Override
    @Async
    public Future<Boolean> insertErrorListByBatch(List<SaError> errorList) {
        if (errorList.size() > 0) {
            errorMapper.insertErrorByBatch(errorList);
        }
        return new AsyncResult<Boolean>(true);
    }
}
