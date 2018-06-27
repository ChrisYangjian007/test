package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsTestingEquipmentDetail;
import com.dalian.sea.parameter.PTestingEquipment;
import com.dalian.sea.parameter.PTestingEquipmentDetail;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsTestingEquipmentDetailMapper extends Mapper<ZsTestingEquipmentDetail> {
    /***
     * 检验室详细内容
     * @return
     */
    List<PTestingEquipmentDetail> getTestingEquipmentDetail();

    /***
     * 新增检验室详细内容
     * @param pTestingEquipmentDetail
     * @return
     */
    Integer addTestingEquipmentDetail(PTestingEquipmentDetail pTestingEquipmentDetail);

    /***
     * 根据id查询检验室详细信息
     * @param testingEquipmentDetailId
     * @return
     */
    PTestingEquipmentDetail getTestingEquipmentDetailById(Long testingEquipmentDetailId);

    /***
     * 修改检验室详细信息
     * @param pTestingEquipmentDetail
     * @return
     */
    Integer updateTestingEquipmentDetail(PTestingEquipmentDetail pTestingEquipmentDetail);

    /***
     * 删除检验室详细
     * @param pTestingEquipmentDetail
     * @return
     */
    Integer delectTestingEquipmentDetail(PTestingEquipmentDetail pTestingEquipmentDetail);

    /**
     * 根据testingEquipmentId和name获取
     * @param pTestingEquipmentDetail
     * @return
     */
    PTestingEquipmentDetail getPTestingEqDetailsByTestingEqIdAndName(PTestingEquipmentDetail pTestingEquipmentDetail);

    /**
     * 通过testingEquipmentDetailId获取
     * @return
     */
    PTestingEquipmentDetail getPTestingEqDetailsByTestingEquipmentDetailId(Long testingEquipmentDetailId);

    /**
     *根据testingEquipmentId删除
     * @param pTestingEquipment
     * @return
     */
    Integer deleteTestingEqDetailByTestingEqId(PTestingEquipment pTestingEquipment);

    /**
     * 通过testingEquipmentId获取
     * @param pTestingEqId
     * @return
     */
    List<PTestingEquipmentDetail> getPtestingEqDetailsByTestingEqId(Long pTestingEqId);
}