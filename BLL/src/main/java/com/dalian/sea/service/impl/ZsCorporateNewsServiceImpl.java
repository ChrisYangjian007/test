package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsCorporateNewsMapper;
import com.dalian.sea.model.ZsCorporateNews;
import com.dalian.sea.service.ZsCorporateNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/4/3.
 */
@Service("ZsCorporateNewsService")
public class ZsCorporateNewsServiceImpl implements ZsCorporateNewsService{

    @Autowired
    private ZsCorporateNewsMapper zsCorporateNewsMapper;

    /**
     * 通过id获取
     * @param id
     * @return
     */
    @Override
    public ZsCorporateNews getCorporateNewsById(Long id) {
        return zsCorporateNewsMapper.getCorporateNewsById(id);
    }
}
