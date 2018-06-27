package com.dalian.sea.mapper;

import com.dalian.sea.model.SaError;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SaErrorMapper extends Mapper<SaError> {
    /**批量插入
     * @param errorList
     * @return
     */
  Integer insertErrorByBatch(List<SaError> errorList);
}