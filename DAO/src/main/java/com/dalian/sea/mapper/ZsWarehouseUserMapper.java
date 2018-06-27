package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsWarehouse;
import com.dalian.sea.model.ZsWarehouseUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsWarehouseUserMapper extends Mapper<ZsWarehouseUser> {

    /**
     * 批量添加
     *
     * @param warehouseUserList
     * @return
     */
    Integer addWarehouseUserList(@Param("warehouseUserList") List<ZsWarehouseUser> warehouseUserList);

    /**
     * 通过warehouseId获取
     *
     * @param warehouseId
     * @return
     */
    List<ZsWarehouseUser> getWarehouseUserByWarehouseId(Long warehouseId);

    /**
     * 通过warehouseId删除
     *
     * @param zsWarehouse
     * @return
     */
    Integer deleteByWarehouseId(ZsWarehouse zsWarehouse);

    /**
     * 根据用户id
     * 获取仓库id
     *
     * @param userId
     * @return warehouseId
     */
    List<Long> getWarehouseIdByUserId(Long userId);

}