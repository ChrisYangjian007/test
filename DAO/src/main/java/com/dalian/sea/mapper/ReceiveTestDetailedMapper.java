package com.dalian.sea.mapper;

import com.dalian.sea.model.ReceiveTestDetailed;
import tk.mybatis.mapper.common.Mapper;

public interface ReceiveTestDetailedMapper extends Mapper<ReceiveTestDetailed> {
    /**
     * 插入收货单数据
     * @param receiveTestDetailed
     * @return
     */
    Integer addreceiveTestDetailed(ReceiveTestDetailed receiveTestDetailed);
}