package com.dalian.sea.config.quartz;

import Utils.GenerateUtils;
import com.dalian.sea.model.BoxOrderTemp;
import com.dalian.sea.model.TagRange;
import com.dalian.sea.model.ZsBatchOrder;
import com.dalian.sea.parameter.PSaOrder;
import com.dalian.sea.parameter.PTagRange;
import com.dalian.sea.service.SaOrderService;
import com.dalian.sea.service.TagRangeService;
import com.dalian.sea.service.ZsBatchOrderService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 杨建
 * @date 2018/5/15
 */
@Slf4j
@Configuration
@Component // 此注解必加
@EnableScheduling // 此注解必加
public class ScheduleTask {
    @Autowired
    private ZsBatchOrderService zsBatchOrderService;
    @Autowired
    private SaOrderService orderService;
    @Autowired
    private TagRangeService tagRangeService;

    public void importOrderToCrm(){
        List<ZsBatchOrder> zsBatchOrders = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        List<BoxOrderTemp> boxOrderTemps = zsBatchOrderService.selectBoxOrderTempListByNotPack();
        int size = boxOrderTemps.size();
        for (int i = 0; i < size; i++) {
            BoxOrderTemp temp = boxOrderTemps.get(i);
            String substring = temp.getQrCode().substring(temp.getQrCode().length() - 14, temp.getQrCode().length());
            //获取明码
            String clearCode = GenerateUtils.zstoCode(substring);
            //获取标签规则
            String ruleName = substring.substring(0, 4);
            PTagRange range = new PTagRange();
            range.setClearCode(Integer.valueOf(clearCode));
            range.setRuleName(ruleName);
            TagRange tagRange = tagRangeService.getTagRangeByRange(range);
            if (tagRange!=null){
                PSaOrder pSaOrder = new PSaOrder();
                pSaOrder.setOrderName(temp.getOrderCode());
                PSaOrder order = orderService.selectSaOrderByName(pSaOrder);
                if (order!=null){
                    ZsBatchOrder zsBatchOrder = new ZsBatchOrder();
                    zsBatchOrder.setBatchId(tagRange.getBatchId());
                    zsBatchOrder.setCreateDate(temp.getCreateDate());
                    zsBatchOrder.setOrderJxs(order.getOrderJxs());
                    zsBatchOrder.setOrderSsmd(order.getOrderSsmd());
                    zsBatchOrder.setOrderName(order.getOrderName());
                    zsBatchOrder.setQrCode(temp.getQrCode());
                    zsBatchOrders.add(zsBatchOrder);
                    ids.add(temp.getBoxOrderTempId());
                    System.out.println("详情值"+zsBatchOrder.getOrderJxs()+","+zsBatchOrder.getCreateDate()+","+zsBatchOrder.getOrderName()+","+zsBatchOrder.getOrderSsmd()+","+zsBatchOrder.getQrCode()+","+zsBatchOrder.getOrderJxs());
                }
            }

        }
        if (ids.size()>0){
            zsBatchOrderService.updateAndInsertBatchOrder(zsBatchOrders,ids);
        }
        System.out.println("任务跑起来了,当前时间是"+new Date()+zsBatchOrders.size());

    }
}