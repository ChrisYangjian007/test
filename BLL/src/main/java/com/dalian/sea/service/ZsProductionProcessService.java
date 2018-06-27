package com.dalian.sea.service;

import com.dalian.sea.model.ZsProductionProcess;
import com.dalian.sea.parameter.PZsProductionProcess;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */
public interface ZsProductionProcessService {

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
     * 获取最大编号
     * @return
     */
    ZsProductionProcess getMaxId();

    /**
     * 添加zsProductionProcess
     * @param zsProductionProcess
     * @return
     */
    Boolean addZsProductionProcess(ZsProductionProcess zsProductionProcess);

    /**
     * 批量添加生产过程
     * @param productionProcessList
     * @return
     */
    Boolean addZsProductionProcessByList(List<ZsProductionProcess> productionProcessList);

    /**
     * 通过名字获取
     * @param productionProcessName
     * @return
     */
    ZsProductionProcess getProductionProcessByName(String productionProcessName);

    /**
     * 生产过程管理
     * @param page
     * @param rows
     * @return
     */
    List<PZsProductionProcess> getAllProductionProcessForGrid(Integer page, Integer rows);

    /**
     * 修改生产过程
     * @param zsProductionProcess
     * @return
     */
    Boolean updateProductionProcess(ZsProductionProcess zsProductionProcess);

    /**
     * 删除生产过程
     * @param zsProductionProcess
     * @return
     */
    Boolean deleteProductionProcess(ZsProductionProcess zsProductionProcess);
}
