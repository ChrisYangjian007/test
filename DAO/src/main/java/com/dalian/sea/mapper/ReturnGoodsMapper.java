package com.dalian.sea.mapper;

import com.dalian.sea.model.ReturnGoods;
import com.dalian.sea.parameter.PuReceiveDetaildPara;
import com.dalian.sea.parameter.ReturnGoodsPara;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ReturnGoodsMapper extends Mapper<ReturnGoods> {

    /**
     * 获取返货表格数据
     * @param returnGoodsPara
     * @return
     */
    List<ReturnGoodsPara> getReturnGoodsDetails(ReturnGoodsPara returnGoodsPara);

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
    Integer newReturnGoods(@Param("re") ReturnGoods returnGoods,@Param("userId") Long userId);

    /**
     * 通过id查询返货记录
     * @param returnGoodsDetailId
     */
    ReturnGoods selectById(Long returnGoodsDetailId);


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
}