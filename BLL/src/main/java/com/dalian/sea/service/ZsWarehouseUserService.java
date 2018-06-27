package com.dalian.sea.service;

import com.dalian.sea.model.ZsWarehouseUser;

import java.util.List;

/**
 * Created by Administrator on 2018/4/27.
 */
public interface ZsWarehouseUserService {

    /**
     * 通过warehouseId获取
     * @param warehouseId
     * @return
     */
    List<ZsWarehouseUser> getWarehouseUserByWarehouseId(Long warehouseId);
}
