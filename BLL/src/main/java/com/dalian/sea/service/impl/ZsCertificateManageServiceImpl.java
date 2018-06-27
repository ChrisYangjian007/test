package com.dalian.sea.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.json.ImageJson;
import com.dalian.sea.mapper.ZsCertificateManageMapper;
import com.dalian.sea.model.ZsCertificateManage;
import com.dalian.sea.model.ZsProductionProcess;
import com.dalian.sea.parameter.PZsCertificateManage;
import com.dalian.sea.service.ZsCertificateManageService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */
@Service("ZsCertificateManageService")
public class ZsCertificateManageServiceImpl implements ZsCertificateManageService{

    @Autowired
    private ZsCertificateManageMapper zsCertificateManageMapper;

    /**
     * 证书管理
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PZsCertificateManage> getCertificateManageForGrid(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<PZsCertificateManage> zsCertificateManageList = zsCertificateManageMapper.getCertificateManageForGrid();
        if(null!=zsCertificateManageList&&0!=zsCertificateManageList.size()){
            for(PZsCertificateManage zsCertificateManage: zsCertificateManageList){
                if(null!=zsCertificateManage.getCertificateImage()){
                    Type type = new TypeReference<List<ImageJson>>() {}.getType();
                    List<ImageJson> imageJsonList = JSON.parseObject(zsCertificateManage.getCertificateImage(),type);
                    if(null!=imageJsonList&&0!=imageJsonList.size()){
                        Iterator<ImageJson> iterator = imageJsonList.iterator();
                        while(iterator.hasNext()){
                            ImageJson imageJson = iterator.next();
                            if("".equals(imageJson.getImageUrl())){
                                iterator.remove();
                            }
                        }
                        if(null!=imageJsonList&&0!=imageJsonList.size()){
                            zsCertificateManage.setIsHaveIamge(1);
                        }else {
                            zsCertificateManage.setIsHaveIamge(2);
                        }
                    }else {
                        zsCertificateManage.setIsHaveIamge(2);
                    }
                }else {
                    zsCertificateManage.setIsHaveIamge(2);
                }
            }
        }
        return zsCertificateManageList;
    }

    /**
     * 添加证书
     * @param zsCertificateManage
     * @return
     */
    @Override
    public Boolean addCertificateManage(ZsCertificateManage zsCertificateManage) {
        Boolean boo = false;
        Integer result = zsCertificateManageMapper.addCertificateManage(zsCertificateManage);
        if(0<result){
            boo = true;
        }
        return boo;
    }

    /**
     * 删除证书
     * @param zsCertificateManage
     * @return
     */
    @Override
    public Boolean deleteCertificateManageById(ZsCertificateManage zsCertificateManage) {
        Boolean boo = false;
        Integer result = zsCertificateManageMapper.deleteCertificateManageById(zsCertificateManage);
        if(0<result){
            boo = true;
        }
        return boo;
    }

    /**
     * 通过smallProductTypeID和productionLicense获取
     * @param zsCertificateManage
     * @return
     */
    @Override
    public ZsCertificateManage getCertificateManageBySProductTypeAndName(ZsCertificateManage zsCertificateManage) {
        return zsCertificateManageMapper.getCertificateManageBySProductTypeAndName(zsCertificateManage);
    }

    /**
     * 通过certificateManageId获取
     * @param certificateManageId
     * @return
     */
    @Override
    public PZsCertificateManage getCertificateManageByCertificateManageId(Long certificateManageId) {
        return zsCertificateManageMapper.getCertificateManageByCertificateManageId(certificateManageId);
    }

    /**
     * 修改证书
     * @param zsCertificateManage
     * @return
     */
    @Override
    public Boolean updateCertificateManage(ZsCertificateManage zsCertificateManage) {
        Boolean boo = false;
        Integer result = zsCertificateManageMapper.updateCertificateManage(zsCertificateManage);
        if(0<result){
            boo = true ;
        }
        return boo;
    }

    /**
     * 增加内容
     * @param zsCertificateManage
     * @return
     */
    @Override
    public Boolean addCertificateContent(ZsCertificateManage zsCertificateManage) {
        Boolean boo = false;
        Integer result = zsCertificateManageMapper.addCertificateContent(zsCertificateManage);
        if(0<result){
            boo = true;
        }
        return boo;
    }

    /**
     * 通过smallProductTypeId获取
     * @param smallProductTypeId
     * @return
     */
    @Override
    public PZsCertificateManage getCertificateManageBySmallProductTypeId(Long smallProductTypeId) {
        PZsCertificateManage pZsCertificateManage = zsCertificateManageMapper.getCertificateManageBySmallProductTypeId(smallProductTypeId);
        if(null!=pZsCertificateManage){
            Type type = new TypeReference<List<ImageJson>>() {}.getType();
            List<ImageJson> imageJsonList = JSON.parseObject(pZsCertificateManage.getCertificateImage(),type);
            if(null!=imageJsonList&&0!=imageJsonList.size()){
                Iterator<ImageJson> iterator = imageJsonList.iterator();
                while(iterator.hasNext()){
                    ImageJson imageJson = iterator.next();
                    if("".equals(imageJson.getImageUrl())){
                        iterator.remove();
                    }
                }
                if(null!=imageJsonList&&0!=imageJsonList.size()){
                    pZsCertificateManage.setImageJsonList(imageJsonList);
                }
            }
        }
        return pZsCertificateManage;
    }
}
