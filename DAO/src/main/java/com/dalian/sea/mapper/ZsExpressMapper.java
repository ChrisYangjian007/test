package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsExpress;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsExpressMapper extends Mapper<ZsExpress> {

    /**
     * 根据情况获取
     * @param express
     * @return
     */
    List<ZsExpress> getZsExpressByZsExpress(ZsExpress express);

    /**
     * 根据箱码获取
     * @param express
     * @return
     */
    ZsExpress getZsExpressByBoxCode(ZsExpress express);

    /**
     * 根据快递单号获取
     * @param express
     * @return
     */
    ZsExpress getZsExpressByExpressCode(ZsExpress express);

    /**
     * 添加快递单
     * @param express
     * @return
     */
    Integer addZsExpress(ZsExpress express);

    /**
     * 修改快递单
     * @param express
     * @return
     */
    Integer updateZsExpress(ZsExpress express);

    /**
     * 删除快递单
     * @param express
     * @return
     */
    Integer deleteZsExpressBy(ZsExpress express);

    /**
     * 删除快递单
     * @param express
     * @return
     */
    Integer deleteZsExpress(ZsExpress express);

}