package com.dalian.sea.service;

import com.dalian.sea.Models.Advertisement;

import java.util.List;

/**
 * Created by Administrator on 2017-08-25.
 */
public interface IAdvertisementService {
    public Advertisement getAdvertisementById(int Id);
    public List<Advertisement> getAll();
}
