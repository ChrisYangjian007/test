package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BaAreaIpMapper;
import com.dalian.sea.mapper.SysSysLogMapper;

import com.dalian.sea.model.BaAreaIp;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.parameter.PSysLogSys;
import com.dalian.sea.service.SysSysLogService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author YH
 */
@Service("SysSysLogService")
@Slf4j
public class SysSysLogServiceImpl implements SysSysLogService {

    @Autowired
    private SysSysLogMapper sysSysLogMapper;
    @Autowired
    private BaAreaIpMapper baAreaIpMapper;

    /**
     * 查
     *
     * @return
     */
    @Override
    public List<SysSysLog> getAllSysSysLog() {
        return sysSysLogMapper.getAllSysSysLog();
    }

    /**
     * 增
     *
     * @return
     */
    @Override
    public Boolean addSysSysLog(SysSysLog sysSysLog) {
        Boolean boo=false;
        try {
            BaAreaIp areaIp = baAreaIpMapper.getAreaIpByIp(sysSysLog.getIpAddress());
            if (null != areaIp) {
                if (null != areaIp.getDistrict()&&!"".equals(areaIp.getDistrict())) {
                    sysSysLog.setIpAddressName(areaIp.getDistrict());
                } else {
                    if (null != areaIp.getCity()&&!"".equals(areaIp.getCity())) {
                        sysSysLog.setIpAddressName(areaIp.getCity());
                    } else {
                        if (null != areaIp.getProvince()&&!"".equals(areaIp.getProvince())) {
                            sysSysLog.setIpAddressName(areaIp.getProvince());
                        } else {
                            if (null != areaIp.getCountry()&&!"".equals(areaIp.getCountry())) {
                                sysSysLog.setIpAddressName(areaIp.getCountry());
                            } else {
                                if (null != areaIp.getContinent()&&!"".equals(areaIp.getContinent())) {
                                    sysSysLog.setIpAddressName(areaIp.getContinent());
                                } else {
                                    sysSysLog.setIpAddressName("--");
                                }
                            }
                        }
                    }
                }
            } else {
                sysSysLog.setIpAddressName("--");
            }
            Integer integer = sysSysLogMapper.addSysSysLog(sysSysLog);
            if (0 != integer) {
                boo = true;
            }
        }catch (Exception e){
            log.error("log插入异常");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;

    }

    /**
     * 改
     *
     * @return
     */
    @Override
    public Boolean updateSysSysLog(SysSysLog sysSysLog) {
        Boolean boo=false;
        Integer integer=sysSysLogMapper.updateSysSysLog(sysSysLog);
        if(0!=integer){
            boo=true;
        }
        return boo;
    }

    /**
     * 删
     *
     * @return
     */
    @Override
    public Boolean deleteSysSysLog(Long sysLogId) {
        Boolean boo=false;
        Integer integer=sysSysLogMapper.deleteSysSysLog(sysLogId);
        if(0!=integer){
            boo=true;
        }
        return boo;
    }

    /**
     * 分页
     * @param pSysLogSys
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PSysLogSys> getSysSysLogByJqGrid(PSysLogSys pSysLogSys,Long userId, int page, int rows) {
        PageHelper.startPage(page,rows);
        return sysSysLogMapper.getSysSysLogByJqGrid(pSysLogSys,userId);
    }
}
