package com.dalian.sea.service.impl;

import com.dalian.sea.ClientIP;
import com.dalian.sea.mapper.BaAreaIpMapper;
import com.dalian.sea.mapper.BaCompanyMapper;
import com.dalian.sea.mapper.SysSysLogMapper;
import com.dalian.sea.model.BaAreaIp;
import com.dalian.sea.model.BaCompany;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.parameter.PBaCompany;
import com.dalian.sea.service.BaCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * BaCompanyServiceImpl
 *
 * @author xintao
 * @date 2018/1/15
 */
@Service("BaCompanyService")
public class BaCompanyServiceImpl implements BaCompanyService {
    @Autowired
    private BaCompanyMapper baCompanyMapper;
    @Autowired
    private BaAreaIpMapper baAreaIpMapper;
    @Autowired
    private SysSysLogMapper sysSysLogMapper;


    /**
     * 根据父级ID获取子集
     * @param pId
     * @return
     */
    @Override
    public List<PBaCompany> getBaCompanyByPId(Long pId) {
        return baCompanyMapper.getBaCompanyByPId(pId);
    }

    /**
     * 根据id获取公司
     * @param id
     * @return
     */
    @Override
    public PBaCompany getBaCompanyById(Long id) {
        return baCompanyMapper.getBaCompanyById(id);
    }

    /**
     * 添加内容
     * @param company
     * @return
     */
    @Override
    public Boolean addContent(PBaCompany company) {
        Boolean boo = false;
        Integer integer = baCompanyMapper.addContent(company);
        if (0!=integer){
            boo = true;
        }
        return boo;
    }

    /**
     * 修改
     * @param company
     * @return
     */
    @Override
    public Boolean updateBaCompany(PBaCompany company) {
        Boolean boo = false;
        Integer integer = baCompanyMapper.updateBaCompany(company);
        if (0 != integer) {
            boo = true;
        }
        return boo;
    }
}
