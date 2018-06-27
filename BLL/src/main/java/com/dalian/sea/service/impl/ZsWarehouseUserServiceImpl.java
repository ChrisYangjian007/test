package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsWarehouseUserMapper;
import com.dalian.sea.model.ZsWarehouseUser;
import com.dalian.sea.service.ZsWarehouseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/4/27.
 */
@Service("ZsWarehouseUserService")
public class ZsWarehouseUserServiceImpl implements ZsWarehouseUserService{

    @Autowired
    private ZsWarehouseUserMapper zsWarehouseUserMapper;

    /**
     * 通过warehouseId获取
     * @param warehouseId
     * @return
     */
    @Override
    public List<ZsWarehouseUser> getWarehouseUserByWarehouseId(Long warehouseId) {
        return zsWarehouseUserMapper.getWarehouseUserByWarehouseId(warehouseId);
    }
}
