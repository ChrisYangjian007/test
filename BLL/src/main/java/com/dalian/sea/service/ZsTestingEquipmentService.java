package com.dalian.sea.service;

import com.dalian.sea.parameter.PTestingEquipment;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */
public interface ZsTestingEquipmentService {

    /**
     * 通过id获取
     * @param testingEquipmentId
     * @return
     */
    PTestingEquipment getTestingEquipmentByTestingEquipmentId(Long testingEquipmentId);

    /**
     * 通过dataDictionaryIDetailsId获取
     * @param dataDictionaryIDetailsId
     * @return
     */
    PTestingEquipment getPTestingEquipmentByDataDictionaryDetailsId(Long dataDictionaryIDetailsId);

    /**
     * 获取全部
     * @return
     */
    List<PTestingEquipment> getAllTestingEquipment();
}
