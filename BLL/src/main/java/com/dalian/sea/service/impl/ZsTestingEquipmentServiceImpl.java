package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsTestingEquipmentMapper;
import com.dalian.sea.parameter.PTestingEquipment;
import com.dalian.sea.service.ZsTestingEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */
@Service("ZsTestingEquipmentService")
public class ZsTestingEquipmentServiceImpl implements ZsTestingEquipmentService{

    @Autowired
    private ZsTestingEquipmentMapper zsTestingEquipmentMapper;

    /**
     * 通过id获取
     * @param testingEquipmentId
     * @return
     */
    @Override
    public PTestingEquipment getTestingEquipmentByTestingEquipmentId(Long testingEquipmentId) {
        return zsTestingEquipmentMapper.getTestingEquipmentById(testingEquipmentId);
    }

    /**
     * 通过dataDictionaryIDetailsId获取
     * @param dataDictionaryIDetailsId
     * @return
     */
    @Override
    public PTestingEquipment getPTestingEquipmentByDataDictionaryDetailsId(Long dataDictionaryIDetailsId) {
        return zsTestingEquipmentMapper.getPTestingEquipmentByDataDictionaryDetailsId(dataDictionaryIDetailsId);
    }

    /**
     * 获取全部
     * @return
     */
    @Override
    public List<PTestingEquipment> getAllTestingEquipment() {
        return zsTestingEquipmentMapper.getTestingEquipment();
    }


}
