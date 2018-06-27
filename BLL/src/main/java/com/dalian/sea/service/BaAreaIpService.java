package com.dalian.sea.service;

import com.dalian.sea.model.BaAreaIp;

/**
 * BaAreaIpService
 *
 * @author TONE
 * @date 2018/4/8.
 */
public interface BaAreaIpService {
    /**
     * 根据Ip查询
     * @param ip
     * @return
     */
    BaAreaIp getAreaIpByIp(String ip);

}
