package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.PuReceiveDetailMapper;
import com.dalian.sea.mapper.PuReceiveMapper;
import com.dalian.sea.mapper.PuReceiveTestMapper;
import com.dalian.sea.model.PuReceive;
import com.dalian.sea.model.PuReceiveDetail;
import com.dalian.sea.parameter.PuReceiveApi;
import com.dalian.sea.parameter.PuReceiveDetailPa;
import com.dalian.sea.parameter.PuReceiveDetaildPara;
import com.dalian.sea.service.PuReceiveService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 杨文波
 * @date 2018/1/24
 */
@Service
@Slf4j
public class PuReceiveServiceImpl implements PuReceiveService {

    @Autowired
    private PuReceiveMapper puReceiveMapper;

    @Autowired
    private PuReceiveDetailMapper puReceiveDetailMapper;

    @Autowired
    private PuReceiveTestMapper puReceiveTestMapper;

    @Override
    public List<PuReceive> getReceiveCarryOut(PuReceive puReceive, int page, int rows) {
        List<PuReceive> puReceives = null;
        try {
            PageHelper.startPage(page, rows);
            puReceives = puReceiveMapper.getReceiveCarryOut(puReceive);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return puReceives;
    }

    /**
     * 根据receiveId删除收货单
     * 要先判断有没有明细
     *
     * @param puReceiveDetails
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public Boolean deleteReceiveById(List<PuReceiveDetailPa> puReceiveDetails, Long userId) {
        Boolean res = false;
        try {
            for (PuReceiveDetailPa puReceiveDetail : puReceiveDetails) {
                puReceiveDetailMapper.deleteReceiveDetailById(puReceiveDetail, new Date(), userId);
            }
            List<PuReceiveDetail> details = puReceiveDetailMapper.getDetailByReceiveId(puReceiveDetails.get(0).getReceiveId());
            if (null!=details&&!details.isEmpty()){
                res=true;
            }else {
                puReceiveMapper.deleteReceiveById(puReceiveDetails.get(0).getReceiveId(), userId, new Date());
                res=true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return res;
    }

    @Override
    public PuReceive getReceiveByReceiveNo(PuReceive receive) {
        PuReceive puReceiveList =puReceiveMapper.getReceiveByReceiveNo(receive);
        return puReceiveList;
    }

    @Override
    public PuReceive getReceiveByReceiveId(Long receiveId) {
        PuReceive puReceive = puReceiveMapper.getReceiveByReceiveId(receiveId);
        return puReceive;
    }

    @Override
    public PuReceiveApi getReceiveByReceiveNo(String receiveNo) {
        PuReceiveApi puReceive = puReceiveMapper.getReceiveByReceiveNoForApi(receiveNo);
        return puReceive;
    }

    @Override
    public PuReceiveApi getReceiveByReceiveNoForIsPrint(String receiveNo,Integer isPrint) {
        PuReceiveApi puReceive = puReceiveMapper.getReceiveByReceiveNoForIsPrint(receiveNo,isPrint);
        return puReceive;
    }

    @Override
    public boolean updateReceiveById(PuReceive puReceive) {
        boolean result = false;
        Integer res = puReceiveMapper.updateReceiveById(puReceive);
        if (res>0){
            result=true;
        }
        return result;
    }

    @Override
    public List<PuReceive> getIsPrintReceiveList(Byte isPrint,int page,int size) {
        PageHelper.startPage(page,size);
        List<PuReceive> puReceives = puReceiveMapper.getIsPrintReceiveList(isPrint);
        return puReceives;
    }

}
