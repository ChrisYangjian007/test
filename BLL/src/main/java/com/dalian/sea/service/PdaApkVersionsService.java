package com.dalian.sea.service;

import com.dalian.sea.model.PdaApkVersions;

import java.util.List;

/**
 * PdaApkVersionsService
 *
 * @author TONE
 * @date 2018/4/4.
 */
public interface PdaApkVersionsService {
    /**
     * 获取指定APP
     * @param id
     * @return
     */
    PdaApkVersions getPdaApkVersionsById(Long id);

    /**
     * 根据名称获取所有APP版本
     * @param appName
     * @return
     */
    List<PdaApkVersions> getAllPdaApkVersionsByAppName(String appName);

    /**
     * 根据情况获取所有APP版本
     * @param pdaApkVersions
     * @return
     */
    List<PdaApkVersions> getAllPdaApkVersionsBy(PdaApkVersions pdaApkVersions);

    /**
     * 根据情况获取最新版本
     * @param pdaApkVersions
     * @return
     */
    PdaApkVersions getNewPdaApkVersionsBy(PdaApkVersions pdaApkVersions);

    /**
     * 添加App
     * @param pdaApkVersions
     * @return
     */
    Boolean addPdaApkVersions(PdaApkVersions pdaApkVersions);
}
