package com.dalian.sea.service;

import com.dalian.sea.model.BaCompany;
import com.dalian.sea.parameter.PBaCompany;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * BaCompanyService
 *
 * @author xintao
 * @date 2018/1/15
 */
public interface BaCompanyService {

    /**
     * 根据父级ID获取子集
     * @param pId
     * @return
     */
    List<PBaCompany> getBaCompanyByPId(Long pId);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    PBaCompany getBaCompanyById(Long id);

    /**
     * 添加内容
     * @param company
     * @return
     */
    Boolean addContent(PBaCompany company);

    /**
     * 修改
     * @param company
     * @return
     */
    Boolean updateBaCompany(PBaCompany company);

}
