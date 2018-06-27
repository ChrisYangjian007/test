package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsProductionInformation;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsProductionInformationMapper extends Mapper<ZsProductionInformation> {
    /***
     * 预览页生产关键控制点
     * @return
     */
    List<ZsProductionInformation> getProductionInformation();

    /***
     * 新增生产控制点
     * @param productionInformation
     * @return
     */
    Integer addProductionInformation(ZsProductionInformation productionInformation);

    /***
     * 根据id查询生产控制点信息
     * @param productionInformationId
     * @return
     */
    ZsProductionInformation getProductionInformationById(Long productionInformationId);

    /***
     * 修改生产控制点
     * @param productionInformation
     * @return
     */
    Integer updateProductionInformation(ZsProductionInformation productionInformation);

    /***
     * 删除生产控制点
     * @param
     * @return
     */
    Integer delectProductionInformation(ZsProductionInformation productionInformation);

    /**
     * 通过productionInformationId获取
     * @param productionInformationId
     * @return
     */
    ZsProductionInformation getProductionInformationByProductionInformationId(Long productionInformationId);
}