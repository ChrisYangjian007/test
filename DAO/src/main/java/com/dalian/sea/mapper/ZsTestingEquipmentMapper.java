package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsTestingEquipment;
import com.dalian.sea.parameter.PTestingEquipment;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsTestingEquipmentMapper extends Mapper<ZsTestingEquipment> {
    /***
     * 查询检验室内容
     * @return
     */
    List<PTestingEquipment> getTestingEquipment();
    /***
     * 查询所有检验室
     */
    List<PTestingEquipment> getDictionary();

    /***
     * 新增检测设备
     * @param pTestingEquipment
     * @return
     */
    Integer addTestingEquipment(PTestingEquipment pTestingEquipment);

    /***
     * 修改检测设备
     * @param pTestingEquipment
     * @return
     */
    Integer updateTestingEquipment(PTestingEquipment pTestingEquipment);

    /***
     * 根据id查询检测设备信息
     * @param testingEquipmentId
     * @return
     */
    PTestingEquipment getTestingEquipmentById(Long testingEquipmentId);

    /***
     * 删除检测设备信息
     * @param
     * @return
     */
    Integer delectTestingEquipment(PTestingEquipment pTestingEquipment);

    /***
     * 查询现有检验室
     * @return
     */
    List<PTestingEquipment> getEquipment();

    /**
     *通过dataDictionaryIDetailsId获取
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