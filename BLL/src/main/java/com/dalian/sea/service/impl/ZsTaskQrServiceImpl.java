package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsTaskQrMapper;
import com.dalian.sea.model.ZsTaskQr;
import com.dalian.sea.service.ZsTaskQrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 杨文波
 * @date 2018/3/8
 */
@Service
@Slf4j
public class ZsTaskQrServiceImpl implements ZsTaskQrService{
    @Autowired
    private ZsTaskQrMapper zsTaskQrMapper;


    @Override
    public boolean insertTaskQr(ZsTaskQr zsTaskQr) {
        boolean res =false;
        try {
            Integer a = zsTaskQrMapper.insertTaskQr(zsTaskQr);
            if (a>0){
                res=true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return res;
    }

    @Override
    public ZsTaskQr getTaskQrByCode(Long codeId) {
        return zsTaskQrMapper.getTaskQrByCode(codeId);
    }

    /**
     * 通过produceTaskId获取编码
     * @param produceTaskId
     * @return
     */
    @Override
    public ZsTaskQr getTaskQrByProduceTaskId(Long produceTaskId) {
        return zsTaskQrMapper.getTaskQrByProduceTaskId(produceTaskId);
    }
}
