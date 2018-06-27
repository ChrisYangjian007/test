package com.dalian.sea.service;

import com.dalian.sea.json.Json;
import com.dalian.sea.model.SaLeaveStockDetail;
import com.dalian.sea.model.ZsQrCode;
import com.dalian.sea.parameter.SaLeaveStockDetailPara;
import com.dalian.sea.parameter.SeaCucumberProcedure;
import com.dalian.sea.parameter.YhLeaveStock;

import java.util.List;

/**
 * Created by Administrator on 2018/3/1.
 */
public interface SaLeaveStockDetailService {
    /**
     * 出库记录
     * 表格数据
     *
     * @return
     */
    List<SaLeaveStockDetailPara> getAllLeaveStockDetail(SaLeaveStockDetailPara saLeaveStockDetailPara, int page, int rows, Long userId, Long companyId);

    /**
     * 获取损耗记录表格数据
     *
     * @param saLeaveStockDetailPara
     * @param page
     * @param rows
     * @return
     */
    List<SaLeaveStockDetailPara> getAllLossDetail(SaLeaveStockDetailPara saLeaveStockDetailPara, int page, int rows, Long userId, Long companyId);

    /**
     * 获取出库记录货物类型
     *
     * @param userId
     * @param companyId
     * @return
     */
    List<SaLeaveStockDetailPara> getStockIsMaterial(Long userId, Long companyId);

    /**
     * 获取损耗记录货物类型
     * @param userId
     * @param companyId
     * @return
     */
    List<SaLeaveStockDetailPara> getLossIsMaterial(Long userId, Long companyId);

    /**
     * 获取出库记录产品状态
     */
    List<SaLeaveStockDetailPara> getStockProductStatus(Long userId, Long companyId);

    /**
     * 获取损耗记录产品状态
     *
     * @return
     */
    List<SaLeaveStockDetailPara> getLossProductStatus(Long userId, Long companyId);

    /**
     * 通过produceTaskId获取出库记录
     *
     * @param produceTaskId
     * @return
     */
    List<SaLeaveStockDetailPara> getLeaveStockDetailByProduceId(Long produceTaskId);


    /**
     * 新建出库
     *
     * @param yhLeaveStock
     * @param userId
     * @return
     */
    Json newLeaveStock(YhLeaveStock yhLeaveStock, Long userId, String userName, List<ZsQrCode> qrCodes);

    /**
     * 通过货物类型(非损耗)
     * 获取产品
     *
     * @param isMaterial
     * @return
     */
    List<SaLeaveStockDetail> getReturnProduct(Long isMaterial);

    /**
     * 通过产品id(非损耗)
     * 获取规格
     *
     * @param productId
     * @return
     */
    List<SaLeaveStockDetail> getReturnProductSpecName(Long productId);

    /**
     * 通过货物类型和产品联动
     * 获取规格
     *
     * @param saLeaveStockDetailPara
     * @return
     */
    List<SaLeaveStockDetailPara> getSpec(SaLeaveStockDetailPara saLeaveStockDetailPara);

    /**
     * 通过货物类型(损耗)
     * 获取产品
     *
     * @param isMaterial
     * @return
     */
    List<SaLeaveStockDetail> getLossProduct(Long isMaterial);

    /**
     * (损耗)
     * 通过货物类型和产品联动
     * 获取规格
     *
     * @param saLeaveStockDetailPara
     * @return
     */
    List<SaLeaveStockDetailPara> getLossSpec(SaLeaveStockDetailPara saLeaveStockDetailPara);

    /**
     * 增加工序
     *
     * @param seaCucumberProcedure
     * @param userId
     * @param userName
     * @return
     */
    Json addProcedureAndFormAttributeValue(SeaCucumberProcedure seaCucumberProcedure, Long userId, String userName);

    /**
     * 根据出库详情id
     * 获取出库记录打印
     *
     * @param saLeaveStockDetailPara
     * @return
     */
    YhLeaveStock getLeaveStockDetailObj(SaLeaveStockDetailPara saLeaveStockDetailPara);
}

