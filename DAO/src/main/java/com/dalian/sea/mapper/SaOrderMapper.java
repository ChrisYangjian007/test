package com.dalian.sea.mapper;

import com.dalian.sea.model.SaOrder;
import com.dalian.sea.parameter.PSaOrder;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 杨建
 */
public interface SaOrderMapper extends Mapper<SaOrder> {
    /**
     * 查询订单列表
     *
     * @param order
     * @return
     */
    List<PSaOrder> selectListOrderByOrder(PSaOrder order);

    /**
     * 根据订货编号
     *
     * @param order
     * @return
     */
    PSaOrder getOrderByOrderName(PSaOrder order);

    /**
     * 添加
     *
     * @param order
     * @return
     */
    Integer insertOrderSelective(SaOrder order);

    /**
     * 修改
     *
     * @param order
     * @return
     */
    Integer updateByOrderSelective(SaOrder order);

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    Integer updateOrderByList(List<SaOrder> list);


    /**
     * 获取今日订单任务
     *
     * @return
     */
    List<PSaOrder> getTodayOrder();


    /**
     * 获取今日订单总数
     * @return
     */
    Integer todayOrderCount();


    /**
     * 今日已完成订单数量
     * @return
     */
    Integer todayFinishedCount();
}