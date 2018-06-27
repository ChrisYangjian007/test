package com.dalian.sea.BA;

import com.dalian.sea.Models.Advertisement;
import com.dalian.sea.service.IAdvertisementService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017-08-26.
 */
public class AdvertisementLogic {
    @Resource(name="AdvertisementService")
    private IAdvertisementService advertisementService;
    public List<Advertisement> getAll()
    {
        return advertisementService.getAll();
    }

    private static AdvertisementLogic _item;
    public static AdvertisementLogic Instance() {
        if (_item == null) {
            _item = new AdvertisementLogic();
        }
        return _item;
    }


}
