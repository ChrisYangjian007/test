package com.dalian.sea.service;

import com.dalian.sea.model.ZsProductionInformation;

/**
 * Created by Administrator on 2018/4/4.
 */
public interface ZsProductionInformationService {

    /**
     * 通过productionInformationId获取
     * @param productionInformationId
     * @return
     */
    ZsProductionInformation getProductionInformationByProductionInformationId(Long productionInformationId);
}
