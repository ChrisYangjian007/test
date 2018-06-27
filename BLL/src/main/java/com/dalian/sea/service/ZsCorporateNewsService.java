package com.dalian.sea.service;

import com.dalian.sea.model.ZsCorporateNews;

/**
 * Created by Administrator on 2018/4/3.
 */
public interface ZsCorporateNewsService {

    /**
     * 通过id获取
     * @param id
     * @return
     */
    ZsCorporateNews getCorporateNewsById(Long id);
}
