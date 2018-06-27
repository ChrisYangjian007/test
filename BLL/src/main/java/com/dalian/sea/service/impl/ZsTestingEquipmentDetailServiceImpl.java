package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsTestingEquipmentDetailMapper;
import com.dalian.sea.parameter.PTestingEquipmentDetail;
import com.dalian.sea.service.ZsTestingEquipmentDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/4/3.
 */
@Service("ZsTestingEquipmentDetailService")
public class ZsTestingEquipmentDetailServiceImpl implements ZsTestingEquipmentDetailService{

    @Autowired
    private ZsTestingEquipmentDetailMapper zsTestingEquipmentDetailMapper;

    /**
     * 根据testingEquipmentId和name获取
     * @param pTestingEquipmentDetail
     * @return
     */
    @Override
    public PTestingEquipmentDetail getPTestingEqDetailsByTestingEqIdAndName(PTestingEquipmentDetail pTestingEquipmentDetail) {
        return zsTestingEquipmentDetailMapper.getPTestingEqDetailsByTestingEqIdAndName(pTestingEquipmentDetail);
    }

    /**
     * 通过testingEquipmentDetailId获取
     * @param testingEquipmentDetailId
     * @return
     */
    @Override
    public PTestingEquipmentDetail getPTestingEqDetailsByTestingEquipmentDetailId(Long testingEquipmentDetailId) {
        return zsTestingEquipmentDetailMapper.getPTestingEqDetailsByTestingEquipmentDetailId(testingEquipmentDetailId);
    }
}
