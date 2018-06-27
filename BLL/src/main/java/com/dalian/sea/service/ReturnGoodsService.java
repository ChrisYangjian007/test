package com.dalian.sea.service;

import com.dalian.sea.model.ReturnGoods;
import com.dalian.sea.parameter.ReturnGoodsPara;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author YH
 */
public interface ReturnGoodsService {

    /**
     * 返货记录详情
     * @param returnGoodsPara
     * @param page
     * @param rows
     * @return
     */
    List<ReturnGoodsPara> getAllReturnGoodsDetail(ReturnGoodsPara returnGoodsPara, int page, int rows);

    /**
     * 获取返货数据货物类型
     * @return
     */
    List<ReturnGoodsPara> getIsMaterial();

    /**
     * 新建返货记录
     * @param returnGoods
     * @param userId
     * @return
     */
    Boolean newReturnGoods(ReturnGoods returnGoods,Long userId);

    /**
     * 数据导出
     * @param returnGoodsDetailId
     * @param request
     * @param response
     * @return
     */
    Boolean exportReturnGoods(Long returnGoodsDetailId, HttpServletRequest request, HttpServletResponse response);

    /**
     * 通过货物类型
     * 获取产品
     * @param isMaterial
     * @return
     */
    List<ReturnGoods> getReturnGoodsProduct(Long isMaterial);

    /**
     * 联动获取
     * 规格
     * @param returnGoodsPara
     * @return
     */
    List<ReturnGoods> getProductSpec(ReturnGoodsPara returnGoodsPara);

    /**
     * 通过id查询返货记录
     * @param returnGoodsDetailId
     */
    ReturnGoods selectById(Long returnGoodsDetailId);
}
