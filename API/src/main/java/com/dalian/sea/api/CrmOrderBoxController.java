package com.dalian.sea.api;

import com.dalian.sea.json.Json;
import com.dalian.sea.model.BoxOrder;
import com.dalian.sea.model.ZsExpress;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.SaOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 杨建 on 2018/4/23.
 */
@RestController
@RequestMapping("/crmOrder")
public class CrmOrderBoxController {
    @Autowired
    private SaOrderDetailService orderDetailService;
    /**
     * 获取订单明细绑定的二维码
     */
    @GetMapping("/getQrOrderDetail")
    public Object getQrOrderDetail(PSaOrder order) {
        Json json = new Json();
        try {
            List<OrderDetailQr> list = orderDetailService.selectOrderQrBy(order);
            json.setMsg("获取成功");
            json.setSuccess(true);
            json.setObj(list);
        }catch (Exception e){
            json.setMsg("获取失败");
        }

        return json;
    }
    /**
     * 获取订单明细绑定的箱码
     */
    @GetMapping("/getBoxCodeOrderDetail")
    public Object getBoxCodeOrderDetail(PSaOrder order) {
        Json json = new Json();
        try {
            List<OrderDetailBoxCode> list = orderDetailService.selectOrderBoxBy(order);
            json.setMsg("获取成功");
            json.setSuccess(true);
            json.setObj(list);
        }catch (Exception e){
            json.setMsg("获取失败");
        }
        return json;
    }
}
