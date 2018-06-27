package com.dalian.sea.service;

import com.dalian.sea.model.ZsEnterprise;
import com.dalian.sea.model.ZsWarehouse;
import com.dalian.sea.parameter.PZsEnterprise;
import com.dalian.sea.parameter.PZsWarehouse;

import java.util.List;

/**
 * Created by Administrator on 2018/2/2.
 */
public interface ZsEnterpriseService {
    /**
     * 加载到表格
     * @param zsEnterprise
     * @return
     */
    List<PZsEnterprise> getEnterpriseForGrid(ZsEnterprise zsEnterprise, Integer page, Integer rows);

    /**
     * 通过名字获取供应商
     * @param zsEnterprise
     * @return
     */
    List<ZsEnterprise> getZsEnterpriseByName(ZsEnterprise zsEnterprise,Integer page, Integer rows);

    /**
     * 添加
     * @param zsEnterprise
     * @return
     */
    Boolean addZsEnterprise(ZsEnterprise zsEnterprise);

    /**
     * 获取最大顺序数
     * @return
     */
    ZsEnterprise getzsEnterpriseListIndex();

    /**
     * 通过id获取仓库信息
     * @param
     * @return
     */
    ZsEnterprise getzsEnterpriseById(Long enterpriseId);

    /**
     * 修改
     * @param zsEnterprise
     * @return
     */
    Boolean updateZsEnterprise(ZsEnterprise zsEnterprise);

    /**
     * 删除
     * @param
     * @return
     */
    Boolean deleteZsEnterpriseById(ZsEnterprise zsEnterprise);

    /**
     * 获取供应商
     * @return
     */
    List<ZsEnterprise> getEnterprise();

    /**
     * 获取供应商
     * @return
     */
    List<ZsEnterprise> getEnterpriseForApi(Integer page, Integer rows,String name);

    /**
     * 通过id获取PZsEnterprise
     * @param enterpriseId
     * @return
     */
    PZsEnterprise getPEnterPriseById(Long enterpriseId);

    /**
     * 查看图片
     * @param enterpriseId
     * @return
     */
    PZsEnterprise PEnterPriseForImageById(Long enterpriseId);

}
