package com.dalian.sea.service;

import com.dalian.sea.parameter.PEnterStockAndStock;
import com.dalian.sea.parameter.PuEnterStockDetailPara;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2018/2/28.
 */
public interface PuEnterStockDetailService {

    /**
     * 入库记录表格数据
     *
     * @return
     */
    List<PuEnterStockDetailPara> getAllEnterStockDetail(PuEnterStockDetailPara puEnterStockDetailPara, int page, int rows,Long userId,Long companyId);

    /**
     * 获取入库记录货物类型
     * @param userId
     * @param companyId
     * @return
     */
    List<PuEnterStockDetailPara> getStockIsMaterial(Long userId,Long companyId);

    /**
     * 获取入库记录产品状态
     */
    List<PuEnterStockDetailPara> getStockProductStatus(Long userId,Long companyId);

    /**
     * 通过produceTaskId获取入库详情信息
     * @param produceTaskId
     * @return
     */
    List<PuEnterStockDetailPara> getEnterStockDetailByProduceTaskId(Long produceTaskId);

    /**
     * 根据货物类型获取产品
     * @param isMaterial
     * @return
     */
    List<PuEnterStockDetailPara> getProductByIsMaterial(Long isMaterial);

    /**
     * 根据货物类型，产品
     * 联动获取规格
     * @param puEnterStockDetailPara
     * @return
     */
    List<PuEnterStockDetailPara> getSpec(PuEnterStockDetailPara puEnterStockDetailPara);

    /**
     * 数据导出
     * @param request
     * @param response
     * @return
     */
    Boolean exportData(HttpServletRequest request, HttpServletResponse response,Long userId,Integer[] arr,Long companyId);

    /**
     * 通过入库详情id
     * 获取入库记录
     */
    PEnterStockAndStock getEnterStockObj(PuEnterStockDetailPara PuEnterStockDetailPara);


    /**
     * 报表统计加载到表格
     * @param puEnterStockDetailPara
     * @param page
     * @param rows
     * @return
     */
    List<PuEnterStockDetailPara> getReportCountList(PuEnterStockDetailPara puEnterStockDetailPara,Integer page,Integer rows);

    /**
     * 获取入库详情表已有的货物类型
     * @param enterStockDetailPara
     * @return
     */
    List<PuEnterStockDetailPara> getIsMaterialOptionForReportCount(PuEnterStockDetailPara enterStockDetailPara);

    /**
     * 根据货物类型获取入库详情已有的的产品大类
     * @param array
     * @return
     */
    List<PuEnterStockDetailPara> getProductTypeListByEnterDetail(Long[] array);

    /**
     * 根据货物类型、产品大类ID查询产品小类
     * @param enterStockDetailPara
     * @return
     */
    List<PuEnterStockDetailPara> getProductIdListByEnterDetail(PuEnterStockDetailPara enterStockDetailPara);

    /**
     * 根据货物类型、产品大类、产品小类查询规格名称
     * @param enterStockDetailPara
     * @return
     */
    List<PuEnterStockDetailPara> getProductSpecNameByEnterDetail(PuEnterStockDetailPara enterStockDetailPara);

    /**
     * 根据货物类型、产品大类、产品小类、规格名称查询单位
     * @param enterStockDetailPara
     * @return
     */
    List<PuEnterStockDetailPara> getUnitIdByEnterDetail(PuEnterStockDetailPara enterStockDetailPara);

    /**
     * 报表统计数据导出
     * @param request
     * @param response
     * @return
     */
    Boolean reportCountExport(HttpServletRequest request, HttpServletResponse response,PuEnterStockDetailPara puEnterStockDetailPara);
}
