package com.dalian.sea.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.json.ImageJson;
import com.dalian.sea.mapper.ZsCompanyCultureMapper;
import com.dalian.sea.parameter.PZsCompanyCulture;
import com.dalian.sea.service.ZsCompanyCultureService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 */
@Service("ZsCompanyCultureService")
public class ZsCompanyCultureServiceImpl implements ZsCompanyCultureService{

    @Autowired
    private ZsCompanyCultureMapper zsCompanyCultureMapper;

    /**
     * 通过id获取
     * @param id
     * @return
     */
    @Override
    public PZsCompanyCulture getPZsCompanyCultuerById(Long id) {
        return zsCompanyCultureMapper.getPZsCompanyCultuerById(id);
    }

    /**
     * 企业文化管理
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PZsCompanyCulture> getPCompanyCultureList(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<PZsCompanyCulture> zsCompanyCultureList =zsCompanyCultureMapper.getPCompanyCultureList();
        if(null!=zsCompanyCultureList&&0!=zsCompanyCultureList.size()){
            Type type = new TypeReference<List<ImageJson>>() {}.getType();
            for(PZsCompanyCulture zsCompanyCulture: zsCompanyCultureList){
                if(null==zsCompanyCulture.getImage()){
                    zsCompanyCulture.setIsHaveIamge(2);
                }else {
                    List<ImageJson> imageJsonList = JSON.parseObject(zsCompanyCulture.getImage(), type);
                    if(null!=imageJsonList&&0!=imageJsonList.size()){
                        Iterator<ImageJson> iterator = imageJsonList.iterator();
                        while(iterator.hasNext()){
                            ImageJson imageJson = iterator.next();
                            if("".equals(imageJson.getImageUrl())){
                                iterator.remove();
                            }
                        }
                        if(null!=imageJsonList&&0!=imageJsonList.size()){
                            zsCompanyCulture.setIsHaveIamge(1);
                        }else {
                            zsCompanyCulture.setIsHaveIamge(2);
                        }
                    }else {
                        zsCompanyCulture.setIsHaveIamge(2);
                    }
                }
            }
        }
        return  zsCompanyCultureList;
    }
}
