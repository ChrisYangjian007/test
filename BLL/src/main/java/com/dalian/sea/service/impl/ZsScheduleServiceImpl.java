package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.PuEnterStockDetailMapper;
import com.dalian.sea.mapper.ZsScheduleMapper;
import com.dalian.sea.model.ZsSchedule;
import com.dalian.sea.parameter.ZsSchedulePara;
import com.dalian.sea.service.ZsScheduleService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author YH
 */
@Slf4j
@Service
public class ZsScheduleServiceImpl implements ZsScheduleService {
    @Autowired
    private ZsScheduleMapper zsScheduleMapper;

    @Autowired
    private PuEnterStockDetailMapper puEnterStockDetailMapper;

    @Override
    @Transactional
    public Boolean addSchedule(List<ZsSchedule> zsSchedules, Long userId, String userName) {
        Boolean res = false;
        try {
            Integer count = zsScheduleMapper.addSchedule(zsSchedules, userId, new Date(), userName);
            if (6 == count) {
                res = true;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return res;
    }

    @Override
    public Boolean duringMonthSchedule() {
        Boolean res = false;
        Integer count = zsScheduleMapper.duringMonthSchedule();
        if (0 != count) {
            res = true;
        }
        return res;
    }

    @Transactional
    @Override
    public List<ZsSchedulePara> getAllSchedule(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<ZsSchedulePara> allSchedule = new ArrayList<>();
        try {
            allSchedule = zsScheduleMapper.getAllSchedule();
            for (ZsSchedulePara zs : allSchedule) {
                //获取产品大类id
                Long id = zs.getProductTypeId();
                //获取单位
                String unitName = zs.getUnitName();
                //获取创建时间
                Date date = zs.getCreateDate();
                //已完成量
                BigDecimal weight = puEnterStockDetailMapper.accomplishment(id, date, unitName);
                if (null != weight) {
                    zs.setCompleteCount(weight);
                    BigDecimal amount = zs.getAmount();
                    BigDecimal div = weight.divide(amount);
                    //百分比
                    zs.setPercent(div.setScale(2, BigDecimal.ROUND_HALF_UP));
                } else {
                    zs.setCompleteCount(BigDecimal.valueOf(0));
                    zs.setPercent(BigDecimal.valueOf(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return allSchedule;
    }

    @Transactional
    @Override
    public List<ZsSchedulePara> getMonthSchedule() {
        List<ZsSchedulePara> list = new ArrayList<>();
        try {
            list = zsScheduleMapper.getMonthSchedule();
            for (ZsSchedulePara zs : list) {
                //获取产品大类id
                Long id = zs.getProductTypeId();
                //获取单位
                String unitName = zs.getUnitName();
                //获取创建时间
                Date date = zs.getCreateDate();
                //已完成量
                BigDecimal weight = puEnterStockDetailMapper.accomplishment(id, date, unitName);
                if (null != weight) {
                    zs.setCompleteCount(weight);
                    BigDecimal amount = zs.getAmount();
                    BigDecimal div = weight.divide(amount);
                    //百分比
                    zs.setPercent(div.setScale(4, BigDecimal.ROUND_HALF_UP));
                } else {
                    zs.setCompleteCount(BigDecimal.valueOf(0));
                    zs.setPercent(BigDecimal.valueOf(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return list;
    }

    @Transactional
    @Override
    public Boolean updateMonthSchedule(List<ZsSchedule> list, Long userId) {
        Boolean res = false;
        try {
            Integer count = 0;
            for (ZsSchedule zs : list) {
                Integer num = zsScheduleMapper.updateMonthSchedule(zs, userId);
                if (num > 0) {
                    count++;
                }
            }
            if (6 == count) {
                res = true;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        } catch (Exception e) {
            res = false;
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return res;
    }

}
