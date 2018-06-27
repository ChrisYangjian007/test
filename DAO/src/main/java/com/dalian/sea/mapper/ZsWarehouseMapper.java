package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsWarehouse;
import com.dalian.sea.parameter.PZsWarehouse;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsWarehouseMapper extends Mapper<ZsWarehouse> {

    /**
     * 加载到表格
     * @param zsWarehouse
     * @return
     */
    List<PZsWarehouse> getWareHouseForGrid(PZsWarehouse zsWarehouse);

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
    Integer addWarehouse(ZsWarehouse zsWarehouse);

    /**
     * 获取最大顺序数
     * @return
     */
    ZsWarehouse getWarehouseMaxListIndex();

    /**
     * 通过id获取仓库信息
     * @param id
     * @return
     */
    PZsWarehouse getWarehouseById(Long id);

    /**
     * 修改
     * @param zsWarehouse
     * @return
     */
    Integer updateZsWarehouseById(ZsWarehouse zsWarehouse);

    /**
     * 修改
     * @param zsWarehouse
     * @return
     */
    Integer updateZsWarehouse(ZsWarehouse zsWarehouse);

    /**
     * 删除
     * @param zsWarehouse
     * @return
     */
    Integer deleteZsWarehouseById(ZsWarehouse zsWarehouse);

    /**
     * 获取所有仓库信息
     * @return
     */
    List<ZsWarehouse> getAllWarehouse();

    /**
     * 根据用户ID查询仓库权限
     * @param userId
     * @return
             */
    List<ZsWarehouse> getWareHouseByUserId(Long userId);

    /**
     * 通过id查询仓库详情
     * @param id
     */
    ZsWarehouse getWarehouseByWarehouseId(Long id);
}