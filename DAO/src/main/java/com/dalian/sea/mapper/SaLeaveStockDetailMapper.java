package com.dalian.sea.mapper;

import com.dalian.sea.model.SaLeaveStockDetail;
import com.dalian.sea.parameter.PuEnterStockDetailPara;
import com.dalian.sea.parameter.SaLeaveStockDetailPara;
import com.dalian.sea.parameter.YhLeaveStockDetail;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface SaLeaveStockDetailMapper extends Mapper<SaLeaveStockDetail> {

    /**
     * 获取出库记录表格数据
     * @param saLeaveStockDetailPara
     * @param ids
     * @return
     */
    List<SaLeaveStockDetailPara> getAllSaLeaveStockDetail(@Param("sa") SaLeaveStockDetailPara saLeaveStockDetailPara,@Param("ids") List<Long> ids);

    /**
     * 获取出库记录货物类型
     * @param ids
     * @return
     */
    List<SaLeaveStockDetailPara> getStockIsMaterial(@Param("ids") List<Long> ids);

    /**
     * 获取损耗记录货物类型
     * @param ids
     * @return
     */
    List<SaLeaveStockDetailPara> getLossIsMaterial(@Param("ids") List<Long> ids);

    /**
     * 获取出库记录产品状态
     * @param ids
     * @return
     */
    List<SaLeaveStockDetailPara> getStockProductStatus(@Param("ids") List<Long> ids);

    /**
     * 获取损耗记录产品状态
     * @return
     */
    List<SaLeaveStockDetailPara> getLossProductStatus(@Param("ids") List<Long> ids);

    /**
     * 通过produceTaskId获取出库记录
     * @param produceTaskId
     * @return
     */
    List<SaLeaveStockDetailPara> getLeaveStockDetailByProduceId(Long produceTaskId);

    /**
     * 新建出库单详情
     * @param saLeaveStockDetail
     * @return
     */
    Long InsertLeaveStockDetail(@Param("sa") SaLeaveStockDetail saLeaveStockDetail, @Param("date") Date date,@Param("userId") Long userId,@Param("relatedId") Long relatedId);


    /**
     * 通过货物类型(非损耗)
     * 获取产品
     * @param isMaterial
     * @return
     */
    List<SaLeaveStockDetail> getReturnProduct(Long isMaterial);

    /**
     * (非损耗)
     * 通过货物类型和产品联动
     * 获取规格
     * @return
     * @param saLeaveStockDetailPara
     */
    List<SaLeaveStockDetailPara> getSpec(SaLeaveStockDetailPara saLeaveStockDetailPara);

    /**
     * 通过产品id(非损耗)
     * 获取规格
     * @param productId
     * @return
     */
    List<SaLeaveStockDetail> getReturnProductSpecName(Long productId);

    /**
     * 通过货物类型(损耗)
     * 获取产品
     * @param isMaterial
     * @return
     */
    List<SaLeaveStockDetail> getLossProduct(Long isMaterial);

    /**
     * (损耗)
     * 通过货物类型和产品联动
     * 获取规格
     * @return
     * @param saLeaveStockDetailPara
     */
    List<SaLeaveStockDetailPara> getLossSpec(SaLeaveStockDetailPara saLeaveStockDetailPara);
}