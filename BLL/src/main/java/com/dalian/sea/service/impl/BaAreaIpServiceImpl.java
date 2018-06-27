package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BaAreaIpMapper;
import com.dalian.sea.model.BaAreaIp;
import com.dalian.sea.service.BaAreaIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BaAreaIpServiceImpl
 *
 * @author TONE
 * @date 2018/4/8.
 */
@Service("BaAreaIpService")
public class BaAreaIpServiceImpl implements BaAreaIpService {
    @Autowired
    private BaAreaIpMapper baAreaIpMapper;


    /**
     * 根据Ip查询
     * @param ip
     * @return
     */
    @Override
    public BaAreaIp getAreaIpByIp(String ip) {
        return baAreaIpMapper.getAreaIpByIp(ip);
    }
}
