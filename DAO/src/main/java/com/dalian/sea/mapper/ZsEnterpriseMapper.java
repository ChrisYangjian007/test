package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsEnterprise;
import com.dalian.sea.model.ZsWarehouse;
import com.dalian.sea.parameter.PZsEnterprise;
import com.dalian.sea.parameter.PZsWarehouse;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsEnterpriseMapper extends Mapper<ZsEnterprise> {
    /**
     * 加载到表格
     * @param zsEnterprise
     * @return
     */
    List<PZsEnterprise> getEnterpriseForGrid(ZsEnterprise zsEnterprise);

    /**
     * 通过名字获取供应商
     * @param zsEnterprise
     * @return
     */
    List<ZsEnterprise> getZsEnterpriseByName(ZsEnterprise zsEnterprise);

    /**
     * 添加
     * @param zsEnterprise
     * @return
     */
    Integer addZsEnterprise(ZsEnterprise zsEnterprise);

    /**
     * 获取最大顺序数
     * @return
     */
    ZsEnterprise getzsEnterpriseListIndex();

    /**
     * 通过id获取供应商信息
     * @param id
     * @return
     */
    ZsEnterprise getzsEnterpriseById(Long enterpriseId);

    /**
     * 修改
     * @param zsEnterprise
     * @return
     */
    Integer updateZsEnterprise(ZsEnterprise zsEnterprise);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer deleteZsEnterpriseById(ZsEnterprise zsEnterprise);

    /**
     * 获取供应商
     * @return
     */
    List<ZsEnterprise> getEnterprise();

    /**
     * 获取供应商
     * @return
     */
    List<ZsEnterprise> getEnterpriseForApi(@Param("name") String name);

    /**
     *通过id获取PZsEnterprise
     * @param enterpriseId
     * @return
     */
    PZsEnterprise getPEnterPriseById(Long enterpriseId);
}