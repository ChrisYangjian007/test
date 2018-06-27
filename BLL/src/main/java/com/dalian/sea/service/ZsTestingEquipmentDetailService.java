package com.dalian.sea.service;

import com.dalian.sea.parameter.PTestingEquipment;
import com.dalian.sea.parameter.PTestingEquipmentDetail;

/**
 * Created by Administrator on 2018/4/3.
 */
public interface ZsTestingEquipmentDetailService {

    /**
     * 根据testingEquipmentId和name获取
     * @param pTestingEquipmentDetail
     * @return
     */
    PTestingEquipmentDetail getPTestingEqDetailsByTestingEqIdAndName(PTestingEquipmentDetail pTestingEquipmentDetail);

    /**
     * 通过testingEquipmentDetailId获取
     * @param testingEquipmentDetailId
     * @return
     */
    PTestingEquipmentDetail getPTestingEqDetailsByTestingEquipmentDetailId(Long testingEquipmentDetailId);
}
