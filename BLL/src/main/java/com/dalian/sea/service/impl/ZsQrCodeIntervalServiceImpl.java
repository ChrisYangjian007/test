package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsQrCodeIntervalMapper;
import com.dalian.sea.model.ZsQrCode;
import com.dalian.sea.model.ZsQrCodeInterval;
import com.dalian.sea.parameter.PZsQrCode;
import com.dalian.sea.parameter.PZsQrCodeInterval;
import com.dalian.sea.service.ZsQrCodeIntervalService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ZsQrCodeIntervalServiceImpl
 *
 * @author TONE
 * @date 2018/3/19.
 */
@Service("ZsQrCodeIntervalService")
public class ZsQrCodeIntervalServiceImpl implements ZsQrCodeIntervalService {
    @Autowired
    private ZsQrCodeIntervalMapper zsQrCodeIntervalMapper;


    /**
     * 根据情况获取二维码编码间隔
     * @param qrCode
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PZsQrCodeInterval> getQrCodeIntervalByQrCode(PZsQrCode qrCode, int page, int rows) {
        PageHelper.startPage(page,rows);
        return zsQrCodeIntervalMapper.getQrCodeIntervalByQrCode(qrCode);
    }

    /**
     * 获取最大编码二维码
     * @return
     */
    @Override
    public PZsQrCodeInterval getQrCodeIntervalForNew() {
        return zsQrCodeIntervalMapper.getQrCodeIntervalForNew();
    }

    /**
     * 根据ID获取二维码编码间隔
     * @param intervalId
     * @return
     */
    @Override
    public PZsQrCodeInterval getQrCodeIntervalByIntervalId(Long intervalId) {
        return zsQrCodeIntervalMapper.getQrCodeIntervalByIntervalId(intervalId);
    }

    /**
     * 删除
     * @param qrCodeInterval
     * @return
     */
    @Override
    public Boolean deleteQrCodeIntervalBy(PZsQrCodeInterval qrCodeInterval) {
        Boolean boo = false;
        Integer integer = zsQrCodeIntervalMapper.deleteQrCodeIntervalBy(qrCodeInterval);
        if (0!=integer){
            boo = true;
        }
        return boo;
    }
}
