package com.dalian.sea.service;

import com.dalian.sea.parameter.PZsQrCodeUse;

import java.util.List;

/**
 * ZsQrCodeUseService
 *
 * @author TONE
 * @date 2018/3/19.
 */
public interface ZsQrCodeUseService {

    /**
     * 根据二维码ID获取使用情况
     * @param qrCodeId
     * @param page
     * @param rows
     * @return
     */
    List<PZsQrCodeUse> getQrCodeUseByQrCodeId(Long qrCodeId,Integer page,Integer rows);




}
