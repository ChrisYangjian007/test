package com.dalian.sea.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.json.ImageJson;
import com.dalian.sea.mapper.SysUnitMapper;
import com.dalian.sea.mapper.ZsEnterpriseMapper;
import com.dalian.sea.model.ZsEnterprise;
import com.dalian.sea.model.ZsWarehouse;
import com.dalian.sea.parameter.PZsEnterprise;
import com.dalian.sea.parameter.PZsWarehouse;
import com.dalian.sea.service.ZsEnterpriseService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2018/2/2.
 */
@Service(value="ZsEnterpriseService")
public class ZsEnterpriseServiceImpl implements ZsEnterpriseService {
    @Autowired
    private ZsEnterpriseMapper zsEnterpriseMapper;
    @Override
    public List<PZsEnterprise> getEnterpriseForGrid(ZsEnterprise zsEnterprise, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<PZsEnterprise> zsEnterpriseList =zsEnterpriseMapper.getEnterpriseForGrid(zsEnterprise);
        if(null!=zsEnterpriseList&&0!=zsEnterpriseList.size()){
            Type type = new TypeReference<List<ImageJson>>() {}.getType();
            for(PZsEnterprise pZsEnterprise: zsEnterpriseList){
                if(null!=pZsEnterprise.getOtherLicenseImage()&&!"".equals(pZsEnterprise.getOtherLicenseImage())){
                    List<ImageJson> imageJsonList = JSON.parseObject(pZsEnterprise.getOtherLicenseImage(),type);
                    if(null!=imageJsonList&&0!=imageJsonList.size()){
                        Iterator<ImageJson> iterator = imageJsonList.iterator();
                        while (iterator.hasNext()){
                            ImageJson imageJson = iterator.next();
                            if(null==imageJson.getImageUrl()||"".equals(imageJson.getImageUrl())){
                                iterator.remove();
                            }
                        }
                        pZsEnterprise.setOtherLicenseImageList(imageJsonList);
                    }
                }
            }
        }
        return  zsEnterpriseList;
    }

    @Override
    public List<ZsEnterprise> getZsEnterpriseByName(ZsEnterprise zsEnterprise,Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        return  zsEnterpriseMapper.getZsEnterpriseByName(zsEnterprise);

    }

    @Override
    public Boolean addZsEnterprise(ZsEnterprise zsEnterprise) {
        Boolean bl = false;
        Integer in = zsEnterpriseMapper.addZsEnterprise(zsEnterprise);

        if(0<in){
            bl=true;
        }
        return bl;
    }

    @Override
    public ZsEnterprise getzsEnterpriseListIndex() {
        return null;
    }

    @Override
    public ZsEnterprise getzsEnterpriseById(Long enterpriseId) {
        return  zsEnterpriseMapper.getzsEnterpriseById(enterpriseId);
    }

    @Override
    public Boolean updateZsEnterprise(ZsEnterprise zsEnterprise) {
        Boolean bl = false;
        Integer in = zsEnterpriseMapper.updateZsEnterprise(zsEnterprise);
        if(0<in){
            bl=true;
        }
        return bl;
    }

    @Override
    public Boolean deleteZsEnterpriseById(ZsEnterprise zsEnterprise) {
        Boolean bl = false;
        Integer in = zsEnterpriseMapper.deleteZsEnterpriseById(zsEnterprise);
        if(0<in){
            bl=true;
        }
        return bl;
    }

    @Override
    public List<ZsEnterprise> getEnterprise() {
        List<ZsEnterprise> list = zsEnterpriseMapper.getEnterprise();
        return list;
    }

    @Override
    public List<ZsEnterprise> getEnterpriseForApi(Integer page, Integer rows,String name) {
        PageHelper.startPage(page,rows);
        List<ZsEnterprise> list = zsEnterpriseMapper.getEnterpriseForApi(name);
        return list;
    }

    /**
     * 通过id获取PZsEnterprise
     * @param enterpriseId
     * @return
     */
    @Override
    public PZsEnterprise getPEnterPriseById(Long enterpriseId) {
        PZsEnterprise pZsEnterprise = zsEnterpriseMapper.getPEnterPriseById(enterpriseId);
        if(null!=pZsEnterprise){
            if(null!=pZsEnterprise.getOtherLicenseImage()&&!"".equals(pZsEnterprise.getOtherLicenseImage())){
                Type type = new TypeReference<List<ImageJson>>() {}.getType();
                List<ImageJson> imageJsonList = JSON.parseObject(pZsEnterprise.getOtherLicenseImage(),type);
                if(null!=imageJsonList&&0!=imageJsonList.size()){
                    pZsEnterprise.setOtherLicenseImageList(imageJsonList);
                }else {
                    ImageJson imageJson = new ImageJson();
                    imageJson.setImageName("");
                    imageJson.setImageUrl("");
                    imageJsonList.add(imageJson);
                    imageJsonList.add(imageJson);
                    imageJsonList.add(imageJson);
                    pZsEnterprise.setOtherLicenseImageList(imageJsonList);
                }
            }
        }
        return pZsEnterprise;
    }

    /**
     * 查看图片
     * @param enterpriseId
     * @return
     */
    @Override
    public PZsEnterprise PEnterPriseForImageById(Long enterpriseId) {
        PZsEnterprise pZsEnterprise = zsEnterpriseMapper.getPEnterPriseById(enterpriseId);
        if(null!=pZsEnterprise){
            if(null!=pZsEnterprise.getOtherLicenseImage()&&!"".equals(pZsEnterprise.getOtherLicenseImage())){
                Type type = new TypeReference<List<ImageJson>>() {}.getType();
                List<ImageJson> imageJsonList = JSON.parseObject(pZsEnterprise.getOtherLicenseImage(),type);
                if(null!=imageJsonList&&0!=imageJsonList.size()){
                   Iterator<ImageJson> iterator = imageJsonList.iterator();
                   while (iterator.hasNext()){
                       ImageJson imageJson = iterator.next();
                       if("".equals(imageJson.getImageUrl())){
                           iterator.remove();
                       }
                   }
                }
                pZsEnterprise.setOtherLicenseImageList(imageJsonList);
            }
        }
        return pZsEnterprise;
    }

}
