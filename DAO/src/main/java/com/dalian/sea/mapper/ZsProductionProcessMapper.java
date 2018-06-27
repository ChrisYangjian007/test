package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsProductionProcess;
import com.dalian.sea.parameter.PZsProductionProcess;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsProductionProcessMapper extends Mapper<ZsProductionProcess> {

    /**
     * 通过id获取生产信息
     * @param id
     * @return
     */
    ZsProductionProcess getProductionProcessById(Long id);

    /**
     * 获取全部生产过程
     * @return
     */
    List<ZsProductionProcess> getAllProductionProcess();

    /**
     *获取最大编号
     * @return
     */
    ZsProductionProcess getMaxId();

    /**
     * 添加
     * @param zsProductionProcess
     * @return
     */
    Integer addZsProductionProcess(ZsProductionProcess zsProductionProcess);

    /**
     * 批量添加生产过程
     * @param productionProcessList
     * @return
     */
    Integer addZsProductionProcessByList(@Param("productionProcessList")List<ZsProductionProcess> productionProcessList);

    /**
     * 通过名字获取
     * @param productionProcessName
     * @return
     */
    ZsProductionProcess getProductionProcessByName(String productionProcessName);

    /**
     * 获取全部
     * @return
     */
    List<PZsProductionProcess> getAllProductionProcessForGrid();

    /**
     * 修改生产过程
     * @param zsProductionProcess
     * @return
     */
    Integer updateProductionProcess(ZsProductionProcess zsProductionProcess);

    /**
     * 删除生产过程
     * @param zsProductionProcess
     * @return
     */
    Integer deleteProductionProcess(ZsProductionProcess zsProductionProcess);
}