package com.dalian.sea.service;

import com.dalian.sea.json.Json;
import com.dalian.sea.model.ZsQrCode;
import com.dalian.sea.model.ZsStock;
import com.dalian.sea.parameter.PEnterStockAndStock;
import com.dalian.sea.parameter.PZsStock;
import com.dalian.sea.parameter.SeaCucumberProcedure;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 陈逸文 on 2018/3/5.
 */
public interface ZsStockService  {
    /**
     * 加载到表格
     * @param stock
     * @return
     */
    List<PZsStock> getZsStockByGrid(PZsStock stock,Integer page,Integer rows);

    /**
     * 获取库存集合
     */
    List<PZsStock> getZsStockList(PZsStock stock);

    /**
     * 获取仓库名称
     * @param userId
     * @param companyId
     * @return
     */
    List<PZsStock> getWarehouse(Long userId,Long companyId);

    /**
     * 根据仓库id获取货物类型
     * @param warehouseId
     * @return
     */
    List<PZsStock> getGoodsTypeByWarehouseId(Long warehouseId);

    /**
     * 根据仓库id和货物类型id
     * 获取产品名称
     *
     * @param pZsStock
     * @return
     */
    List<PZsStock> getProductName(PZsStock pZsStock);


    /**
     * 根据仓库id,货物类型id,产品id
     * 获取产品状态
     * @param pZsStock
     * @return
     */
    List<PZsStock> getProductStatus(PZsStock pZsStock);

    /**
     * 根据仓库id,货物类型id,产品id,产品状态id
     * 获取入库规格
     * @param pZsStock
     * @return
     */
    List<PZsStock> getProductSpec(PZsStock pZsStock);

    /**
     * 根据仓库id,货物id,产品id,产品状态id,入库规格id
     * 获取批次号
     * @param pZsStock
     * @return
     */
    List<PZsStock> getBatchNo(PZsStock pZsStock);

    /**
     * 根据仓库id,货物id,产品id,产品状态id,入库规格id,批次号
     * 获取库存和单位
     * @param pZsStock
     * @return
     */
    PZsStock getUnitAndWeight(PZsStock pZsStock);

    /**
     * 获取仓库货物类型
     */
    List<PZsStock> getStockIsMaterial();

    /**
     * 获取仓库产品状态
     */
    List<PZsStock> getStockProductStatus();

    /**
     * 新建入库
     * @param enterStockAndStock
     * @param id
     * @return
     */
    Json addStockAndEnterStock(PEnterStockAndStock enterStockAndStock, Long id);

    /**
     * 数据导出
     * @param request
     * @param response
     * @return
     */
    Boolean warehouseExport(HttpServletRequest request, HttpServletResponse response,Long userId);

    /**
     * 根据库存ID数组查询
     * @param stockIDs
     * @return
     */
    List<PZsStock> getStockByStockIDs(String[] stockIDs);

    /**
     * 根据库存ID数组查询（除活参库）
     * @param stockIDs
     * @return
     */
    List<PZsStock> getStockByStockIds(String[] stockIDs);
    /**
     * 活参编辑
     * @param stockList
     * @return
     */
    Boolean updateStockBySeaCucumber(List<PZsStock> stockList);

    /**
     * 根据仓库id,货物类型id,产品id
     * 获取规格名称
     *
     * @param zsStock
     * @return
     */
    List<PZsStock> getProductSpecForStock(PZsStock zsStock);

    /**
     * 数据数量
     * @return
     */
    Integer countDetail();

    /**
     * 根据批次号查询库存信息（新建入库联动查询）
     * @param stock
     * @return
     */
    List<PZsStock> getStockListByBatchForEnterStock(PZsStock stock);

    /**
     * 报表统计加载到表格
     * @param stock
     * @param page
     * @param rows
     * @return
     */
    List<PZsStock> getReportCountListFromStock(PZsStock stock,Integer page,Integer rows);

    /**
     * 获取库存表已有的货物类型
     * @param zsStock
     * @return
     */
    List<PZsStock> getIsMaterialOptionForReportCountFromStock(PZsStock zsStock);

    /**
     * 根据货物类型获取库存表已有的的产品大类
     * @param array
     * @return
     */
    List<PZsStock> getProductTypeListFromStock(Long[] array);

    /**
     * 根据货物类型、产品大类ID查询产品小类
     *
     * @param zsStock
     * @return
     */
    List<PZsStock> getProductIdListFromStock(PZsStock zsStock);

    /**
     * 根据货物类型、产品大类、产品小类查询规格名称
     *
     * @param zsStock
     * @return
     */
    List<PZsStock> getProductSpecNameFromStock(PZsStock zsStock);

    /**
     * 根据货物类型、产品大类、产品小类、规格名称查询单位
     *
     * @param zsStock
     * @return
     */
    List<PZsStock> getUnitIdFromStock(PZsStock zsStock);

    /**
     * 报表统计数据导出
     * @param request
     * @param response
     * @return
     */
    Boolean reportCountExport(HttpServletRequest request, HttpServletResponse response,PZsStock zsStock);

}
