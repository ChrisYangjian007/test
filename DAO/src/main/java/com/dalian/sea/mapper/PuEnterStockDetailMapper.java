package com.dalian.sea.mapper;

import com.dalian.sea.model.PuEnterStockDetail;
import com.dalian.sea.parameter.PuEnterStockDetailPara;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface PuEnterStockDetailMapper extends Mapper<PuEnterStockDetail> {

    /**
     * 入库记录表格数据
     * @param puEnterStockDetailPara
     * @param ids
     *
     * @return
     */
    List<PuEnterStockDetailPara> getAllEnterStockDetail(@Param("pu") PuEnterStockDetailPara puEnterStockDetailPara,@Param("ids") List<Long> ids);

    /**
     * 获取入库记录货物类型
     * @param ids
     * @return
     */
    List<PuEnterStockDetailPara> getStockIsMaterial(@Param("ids") List<Long> ids);

    /**
     * 获取入库记录产品状态
     * @param ids
     * @return
     */
    List<PuEnterStockDetailPara> getStockProductStatus(@Param("ids") List<Long> ids);

    /**
     * 通过produceTaskId获取入库详情信息
     *
     * @param produceTaskId
     * @return
     */
    List<PuEnterStockDetailPara> getEnterStockDetailByProduceTaskId(Long produceTaskId);

    /**
     * 新建入库单
     *
     * @param enterStockDetail
     * @return
     */
    Integer insertPuEnterStockDetail(PuEnterStockDetail enterStockDetail);

    /**
     * 根据货物类型获取产品
     *
     * @param isMaterial
     * @return
     */
    List<PuEnterStockDetailPara> getProductByIsMaterial(Long isMaterial);

    /**
     * 根据货物类型，产品
     * 联动获取规格
     *
     * @param puEnterStockDetailPara
     * @return
     */
    List<PuEnterStockDetailPara> getSpec(PuEnterStockDetailPara puEnterStockDetailPara);

    /**
     * 加载报表统计列表
     *
     * @param enterStockDetailPara
     * @return
     */
    List<PuEnterStockDetailPara> getReportCountList(PuEnterStockDetailPara enterStockDetailPara);


    /**
     * 根据货物类型获取入库详情已有的的产品大类
     *
     * @param array
     * @return
     */
    List<PuEnterStockDetailPara> getProductTypeListByEnterDetail(Long[] array);

    /**
     * 根据货物类型、产品大类ID查询产品小类
     *
     * @param enterStockDetailPara
     * @return
     */
    List<PuEnterStockDetailPara> getProductIdListByEnterDetail(PuEnterStockDetailPara enterStockDetailPara);

    /**
     * 根据货物类型、产品大类、产品小类查询规格名称
     *
     * @param enterStockDetailPara
     * @return
     */
    List<PuEnterStockDetailPara> getProductSpecNameByEnterDetail(PuEnterStockDetailPara enterStockDetailPara);

    /**
     * 根据货物类型、产品大类、产品小类、规格名称查询单位
     *
     * @param enterStockDetailPara
     * @return
     */
    List<PuEnterStockDetailPara> getUnitIdByEnterDetail(PuEnterStockDetailPara enterStockDetailPara);

    /**
     * 货品
     * 根据产品线、产品大类、时间（年月）、单位
     * 查询完成数量
     * @param id       产品大类id
     * @param date     时间
     * @param unitName 单位名称
     * @return
     */
    BigDecimal accomplishment(@Param("id") Long id, @Param("date") Date date, @Param("unitName") String unitName);

    List<PuEnterStockDetailPara> getInWeightSumForStock(PuEnterStockDetailPara enterStockDetailPara);
}