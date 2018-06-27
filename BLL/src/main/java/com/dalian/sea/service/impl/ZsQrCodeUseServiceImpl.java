package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsQrCodeUseMapper;
import com.dalian.sea.parameter.PZsQrCodeUse;
import com.dalian.sea.service.ZsQrCodeUseService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ZsQrCodeUseServiceImpl
 *
 * @author TONE
 * @date 2018/3/19.
 */
@Service("ZsQrCodeUseService")
public class ZsQrCodeUseServiceImpl implements ZsQrCodeUseService {
    @Autowired
    private ZsQrCodeUseMapper zsQrCodeUseMapper;


    /**
     * 根据二维码ID获取使用情况
     * @param qrCodeId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PZsQrCodeUse> getQrCodeUseByQrCodeId(Long qrCodeId, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        return zsQrCodeUseMapper.getQrCodeUseByQrCodeId(qrCodeId);
    }
}
