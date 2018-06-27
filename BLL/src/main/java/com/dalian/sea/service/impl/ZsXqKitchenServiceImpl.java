package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsXqKitchenMapper;
import com.dalian.sea.parameter.PXqKitchen;
import com.dalian.sea.service.ZsXqKitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/4/17.
 */
@Service("ZsXqKitchenService")
public class ZsXqKitchenServiceImpl implements ZsXqKitchenService {

    @Autowired
    private ZsXqKitchenMapper zsXqKitchenMapper;

    /**
     * 根据id获取
     * @param kitchenId
     * @return
     */
    @Override
    public PXqKitchen getPXqKitchenById(Long kitchenId) {
        return zsXqKitchenMapper.getXqKitchenById(kitchenId);
    }
}
