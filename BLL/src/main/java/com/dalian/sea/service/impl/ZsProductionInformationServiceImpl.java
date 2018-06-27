package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsProductionInformationMapper;
import com.dalian.sea.model.ZsProductionInformation;
import com.dalian.sea.service.ZsProductionInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/4/4.
 */
@Service("ZsProductionInformationService")
public class ZsProductionInformationServiceImpl implements ZsProductionInformationService{

    @Autowired
    private ZsProductionInformationMapper zsProductionInformationMapper;

    /**
     * 通过productionInformationId获取
     * @param productionInformationId
     * @return
     */
    @Override
    public ZsProductionInformation getProductionInformationByProductionInformationId(Long productionInformationId) {
        return zsProductionInformationMapper.getProductionInformationByProductionInformationId(productionInformationId);
    }
}
