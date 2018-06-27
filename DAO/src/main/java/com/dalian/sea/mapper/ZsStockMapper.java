package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsStock;
import com.dalian.sea.parameter.PZsStock;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * 库存表
 */
public interface ZsStockMapper extends Mapper<ZsStock> {
    /**
     * 加载到表格
     *
     * @param stock
     * @return
     */
    List<PZsStock> getZsStockByGrid(PZsStock stock);

    /**
     * 获取仓库名称
     * @param ids
     * @return
     */
    List<PZsStock> getWarehouse(@Param("ids") List<Long> ids);

    /**
     * 根据仓库id获取货物类型
     *
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
     *
     * @param pZsStock
     * @return
     */
    List<PZsStock> getProductStatus(PZsStock pZsStock);

    /**
     * 根据仓库id,货物类型id,产品id
     * 获取规格名称
     *
     * @param zsStock
     * @return
     */
    List<PZsStock> getProductSpecForStock(PZsStock zsStock);
    /**
     * 根据仓库id,货物类型id,产品id,产品状态id
     * 获取入库规格
     *
     * @param pZsStock
     * @return
     */
    List<PZsStock> getProductSpec(PZsStock pZsStock);

    /**
     * 根据仓库id,货物id,产品id,产品状态id,入库规格id
     * 获取批次号
     *
     * @param pZsStock
     * @return
     */
    List<PZsStock> getBatchNo(PZsStock pZsStock);

    /**
     * 根据仓库id,货物id,产品id,产品状态id,入库规格id,批次号
     * 获取库存和单位
     *
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
     * 查询库存记录
     *
     * @param pZsStock
     * @return
     */
    PZsStock getOneZsStock(PZsStock pZsStock);

    /**
     * 修改库存
     *
     * @param weight
     * @param outWeight
     * @param stockId
     * @return
     */
    Integer updateWeight(@Param("weight") BigDecimal weight, @Param("outWeight") BigDecimal outWeight, @Param("stockId") Long stockId);

    /**
     * 新建入库
     *
     * @param stock
     * @return
     */
    Integer insertZsStock(ZsStock stock);

    /**
     * 多条件查询库存
     *
     * @param stock
     * @return
     */
    List<ZsStock> getZsStockByMuchOption(ZsStock stock);

    /**
     * 入库时修改库存
     *
     * @param stock
     * @return
     */
    Integer updateWeightByInsert(ZsStock stock);

    /**
     * 根据id获取库存记录
     *
     * @param stockId
     * @return
     */
    PZsStock getStockById(Long stockId);

    /**
     * 修改剩余状态
     * 为无剩余
     *
     * @param stockId
     * @return
     */
    Integer updateRestStatus(Long stockId);

    /**
     * 根据库存ID数组查询
     * @param stockIDs
     * @return
     */
    List<PZsStock> getStockByStockIDs(String[] stockIDs);

    /**
     * 根据库存ID数组查询(除活参库)
     * @param stockIDs
     * @return
     */
    List<PZsStock> getStockByStockIds(String[] stockIDs);

    /**
     * 活参编辑
     * @param stock
     * @return
     */
    Integer updateStockBySeaCucumber(PZsStock stock);

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
     * 加载报表统计列表
     *
     * @param stock
     * @return
     */
    List<PZsStock> getReportCountListFromStock(PZsStock stock);

    /**
     * 根据货物类型获取入库详情已有的的产品大类
     *
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
     * 获取库存预警和时间预警总数
     */
    PZsStock getTimeAndStockWaringCount(Long warehouseId);

    /**
     * 根据仓库 idList
     * 获取库存
     * @param ids
     * @return
     */
    List<PZsStock> getWarehouseByIds(@Param("ids") List<Long> ids);
}