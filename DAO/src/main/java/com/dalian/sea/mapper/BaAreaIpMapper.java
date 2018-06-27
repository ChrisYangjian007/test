package com.dalian.sea.mapper;

import com.dalian.sea.model.BaAreaIp;
import tk.mybatis.mapper.common.Mapper;

public interface BaAreaIpMapper extends Mapper<BaAreaIp> {
    /**
     * 根据Ip查询
     * @param ip
     * @return
     */
    BaAreaIp getAreaIpByIp(String ip);
}