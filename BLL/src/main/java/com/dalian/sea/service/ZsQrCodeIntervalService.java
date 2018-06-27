package com.dalian.sea.service;

import com.dalian.sea.model.ZsQrCode;
import com.dalian.sea.model.ZsQrCodeInterval;
import com.dalian.sea.parameter.PZsQrCode;
import com.dalian.sea.parameter.PZsQrCodeInterval;

import java.util.List;

/**
 * ZsQrCodeIntervalService
 *
 * @author TONE
 * @date 2018/3/19.
 */
public interface ZsQrCodeIntervalService {

    /**
     * 根据情况获取二维码编码间隔
     * @param qrCode
     * @param page
     * @param rows
     * @return
     */
    List<PZsQrCodeInterval> getQrCodeIntervalByQrCode(PZsQrCode qrCode, int page, int rows);

    /**
     * 获取最大编码二维码
     * @return
     */
    PZsQrCodeInterval getQrCodeIntervalForNew();

    /**
     * 根据ID获取二维码编码间隔
     * @param intervalId
     * @return
     */
    PZsQrCodeInterval getQrCodeIntervalByIntervalId(Long intervalId);

    /**
     * 删除
     * @param qrCodeInterval
     * @return
     */
    Boolean deleteQrCodeIntervalBy(PZsQrCodeInterval qrCodeInterval);





}
