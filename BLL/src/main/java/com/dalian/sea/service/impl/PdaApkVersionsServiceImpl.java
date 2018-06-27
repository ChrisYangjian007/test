package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.PdaApkVersionsMapper;
import com.dalian.sea.model.PdaApkVersions;
import com.dalian.sea.service.PdaApkVersionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PdaApkVersionsServiceImpl
 *
 * @author TONE
 * @date 2018/4/4.
 */
@Service("PdaApkVersionsService")
public class PdaApkVersionsServiceImpl implements PdaApkVersionsService {
    @Autowired
    private PdaApkVersionsMapper pdaApkVersionsMapper;


    /**
     * 获取指定APP
     * @param id
     * @return
     */
    @Override
    public PdaApkVersions getPdaApkVersionsById(Long id) {
        return pdaApkVersionsMapper.getPdaApkVersionsById(id);
    }

    /**
     * 根据名称获取所有APP版本
     * @param appName
     * @return
     */
    @Override
    public List<PdaApkVersions> getAllPdaApkVersionsByAppName(String appName) {
        return pdaApkVersionsMapper.getAllPdaApkVersionsByAppName(appName);
    }

    /**
     * 根据情况获取所有APP版本
     * @param pdaApkVersions
     * @return
     */
    @Override
    public List<PdaApkVersions> getAllPdaApkVersionsBy(PdaApkVersions pdaApkVersions) {
        return pdaApkVersionsMapper.getAllPdaApkVersionsBy(pdaApkVersions);
    }

    /**
     * 根据情况获取最新版本
     * @param pdaApkVersions
     * @return
     */
    @Override
    public PdaApkVersions getNewPdaApkVersionsBy(PdaApkVersions pdaApkVersions) {
        return pdaApkVersionsMapper.getNewPdaApkVersionsBy(pdaApkVersions);
    }

    /**
     * 添加App
     * @param pdaApkVersions
     * @return
     */
    @Override
    public Boolean addPdaApkVersions(PdaApkVersions pdaApkVersions) {
        Boolean boo =false;
        Integer integer = pdaApkVersionsMapper.addPdaApkVersions(pdaApkVersions);
        if (0!=integer){
            boo = true;
        }
        return boo;
    }
}
