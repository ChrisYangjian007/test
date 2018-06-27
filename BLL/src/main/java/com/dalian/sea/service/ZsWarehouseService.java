package com.dalian.sea.service;

import com.dalian.sea.model.ZsWarehouse;
import com.dalian.sea.parameter.PZsWarehouse;

import java.util.List;

/**
 * Created by Administrator on 2018/1/31.
 */
public interface ZsWarehouseService {

    /**
     * 加载到表格
     * @param zsWarehouse
     * @param page
     * @param rows
     * @return
     */
    List<PZsWarehouse> getWareHouseForGrid(PZsWarehouse zsWarehouse, Integer page, Integer rows);

    /**
     * 通过名字获取仓库
     * @param zsWarehouse
     * @return
     */
    ZsWarehouse getWarehouseByName(ZsWarehouse zsWarehouse);

    /**
     * 添加
     * @param zsWarehouse
     * @return
     */
    Boolean addWarehouse(ZsWarehouse zsWarehouse,Long[] warehouseUserId);

    /**
     * 获取最大顺序数
     * @return
     */
    ZsWarehouse getWarehouseMaxListIndex();

    /**
     * 通过id获取仓库
     * @param id
     * @return
     */
    PZsWarehouse getWarehouseById(Long id);

    /**
     * 修改
     * @param zsWarehouse
     * @return
     */
    Boolean updateZsWarehouseById(ZsWarehouse zsWarehouse, Long[] updWarehouseUserId);

    /**
     * 删除
     * @param warehouse
     * @return
     */
    Boolean deleteZsWarehouseById(ZsWarehouse warehouse);

    /**
     * 获取仓库信息
     * @param userId
     * @param companyId
     * @return
     */
    List<ZsWarehouse> getAllWarehouse(Long userId,Long companyId);

    /**
     * 根据用户ID查询仓库权限
     * @param userId
     * @return
     */
    List<ZsWarehouse> getWareHouseByUserId(Long userId);
}
