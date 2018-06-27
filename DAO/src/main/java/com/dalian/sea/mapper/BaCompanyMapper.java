package com.dalian.sea.mapper;

import com.dalian.sea.model.BaCompany;
import com.dalian.sea.parameter.PBaCompany;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaCompanyMapper extends Mapper<BaCompany> {

    /**
     * 根据父级ID获取子集
     * @param pId
     * @return
     */
    List<PBaCompany> getBaCompanyByPId(Long pId);
    /**
     * 预览页面公司介绍
     * @param
     * @return
     */
    PBaCompany getCompanyIntroduction();

    /**
     * 根据id获取公司
     * @param id
     * @return
     */
    PBaCompany getBaCompanyById(Long id);

    /**
     * 添加内容
     * @param company
     * @return
     */
    Integer addContent(PBaCompany company);

    /**
     * 修改
     * @param company
     * @return
     */
    Integer updateBaCompany(PBaCompany company);

}