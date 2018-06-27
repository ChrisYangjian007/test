package com.dalian.sea.service;

import com.dalian.sea.model.ZsExpress;

import java.util.List;

/**
 * ZsExpressService
 *
 * @author TONE
 * @date 2018/3/11.
 */
public interface ZsExpressService {

    /**
     * 根据情况获取
     * @param express
     * @return
     */
    List<ZsExpress> getZsExpressByZsExpress(ZsExpress express,int page,int rows);

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
    Long addZsExpress(ZsExpress express);

    /**
     * 修改快递单
     * @param express
     * @return
     */
    Boolean updateZsExpress(ZsExpress express);

    /**
     * 删除快递单
     * @param express
     * @return
     */
    Boolean deleteZsExpress(ZsExpress express);
}
