package com.dalian.sea.service.impl;

import org.springframework.stereotype.Service;
import com.dalian.sea.service.IAdvertisementService;
import com.dalian.sea.mapper.AdvertisementMapper;
import com.dalian.sea.Models.Advertisement;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017-08-25.
 */
@Service(value = "AdvertisementService")
public class AdvertisementImpl implements IAdvertisementService {
    @Resource
    private AdvertisementMapper advertisementMapper;

    @Override
    public Advertisement getAdvertisementById(int Id) {
        return advertisementMapper.selectByPrimaryKey(Id);
    }
    @Override
    public List<Advertisement> getAll() {

        return advertisementMapper.getAll();
    }
}
