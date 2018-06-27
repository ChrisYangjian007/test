package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsProductionProcess;
import com.dalian.sea.model.ZsProductionProcessDetail;
import com.dalian.sea.parameter.PZsProductionProcessDetail;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsProductionProcessDetailMapper extends Mapper<ZsProductionProcessDetail> {

    /**
     * 通过productionProcessId获取生产过程详情
     * @param productionProcessId
     * @return
     */
    List<ZsProductionProcessDetail> getProdutionProcessDetailByProductionProcessId(Long productionProcessId);

    /**
     * 通过productionProcessId获取生产过程详情继承类
     * @param productionProcessId
     * @return
     */
    List<PZsProductionProcessDetail> getPProdutionProcessDetailByProductionProcessId(Long productionProcessId);

    /**
     * 批量添加生产过程详情
     * @param detailList
     * @return
     */
    Integer addProductionProcessDetailByList(@Param("detailList")List<ZsProductionProcessDetail> detailList);

    /**
     * 获取全部生产过程详情
     * @return
     */
    List<PZsProductionProcessDetail> getAllProductionProcessDetailForGrid();

    /**
     * 通过productionProcessId删除
     * @param zsProductionProcess
     * @return
     */
    Integer deleteProductionProcessDetailByProductionProcessId(ZsProductionProcess zsProductionProcess);

    /**
     * 添加生产过程详情
     * @param zsProductionProcessDetail
     * @return
     */
    Integer addProductionProcessDetail(ZsProductionProcessDetail zsProductionProcessDetail);

    /**
     * 根据id获取
     * @param productionProcessDetailId
     * @return
     */
    ZsProductionProcessDetail getProductionProcessDetailById(Long productionProcessDetailId);

    /**
     * 修改生产过程详情
     * @param zsProductionProcessDetail
     * @return
     */
    Integer updateProductionProcessDetail(ZsProductionProcessDetail zsProductionProcessDetail);

    /**
     * 删除生产过程详情
     * @param zsProductionProcessDetail
     * @return
     */
    Integer deleteProductionProcessDetail(ZsProductionProcessDetail zsProductionProcessDetail);

    /**
     *通过productionProcessId和name获取
     * @param zsProductionProcessDetail
     * @return
     */
    ZsProductionProcessDetail getProductionProcessDetailByProductionProcessIdAndName(ZsProductionProcessDetail zsProductionProcessDetail);
}