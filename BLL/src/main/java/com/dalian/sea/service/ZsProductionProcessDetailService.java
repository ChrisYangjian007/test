package com.dalian.sea.service;

import com.dalian.sea.model.ZsProductionProcess;
import com.dalian.sea.model.ZsProductionProcessDetail;
import com.dalian.sea.parameter.PZsProductionProcessDetail;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */
public interface ZsProductionProcessDetailService {

    /**
     * 通过productionProcessId获取生产过程详情
     * @param productionProcessId
     * @return
     */
    List<ZsProductionProcessDetail> getProdutionProcessDetailByProductionProcessId(Long productionProcessId);

    /**
     *通过productionProcessId获取生产过程详情继承类
     * @param productionProcessId
     * @return
     */
    List<PZsProductionProcessDetail> getPProdutionProcessDetailByProductionProcessId(Long productionProcessId);

    /**
     * 生产过程详情
     * @param page
     * @param rows
     * @return
     */
    List<PZsProductionProcessDetail> getAllProductionProcessDetailForGrid(Integer page, Integer rows);

    /**
     * 添加生产过程详情
     * @param zsProductionProcessDetail
     * @return
     */
    Boolean addProductionProcessDetail(ZsProductionProcessDetail zsProductionProcessDetail);

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
    Boolean updateProductionProcessDetail(ZsProductionProcessDetail zsProductionProcessDetail);

    /**
     * 删除生产过程详情
     * @param zsProductionProcessDetail
     * @return
     */
    Boolean deleteProductionProcessDetail(ZsProductionProcessDetail zsProductionProcessDetail);

    /**
     * 通过productionProcessId和name获取
     * @param zsProductionProcessDetail
     * @return
     */
    ZsProductionProcessDetail getProductionProcessDetailByProductionProcessIdAndName(ZsProductionProcessDetail zsProductionProcessDetail);
}
