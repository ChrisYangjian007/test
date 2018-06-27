package com.dalian.sea.api;

import com.dalian.sea.json.Json;
import com.dalian.sea.model.BoxOrder;
import com.dalian.sea.model.ZsExpress;
import com.dalian.sea.parameter.PSaOrderPDA;
import com.dalian.sea.service.SaOrderService;
import com.dalian.sea.service.ZsBoxOrderService;
import com.dalian.sea.service.ZsExpressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * AndroidZsExpressApi
 *
 * @author TONE
 * @date 2018/3/12.
 */
@Slf4j
@RestController
@RequestMapping("/express")
public class AndroidZsExpressApi {

    @Autowired
    private ZsExpressService expressService;
    @Autowired
    private SaOrderService saOrderService;
    @Autowired
    private ZsBoxOrderService boxOrderService;


    /**
     * 新建快递单
     */
    @PostMapping("/addZsExpress.json")
    public Object addZsExpress(ZsExpress express) {
        Json json = new Json();
        try {
            ZsExpress zsExpress = expressService.getZsExpressByBoxCode(express);
            if (null==zsExpress) {
                zsExpress = expressService.getZsExpressByExpressCode(express);
                if (null==zsExpress) {
                    Long id = expressService.addZsExpress(express);
                    if (null != id) {
                        json.setMsg("添加成功");
                        json.setObj(true);
                    } else {
                        json.setMsg("添加失败");
                        json.setObj(false);
                    }
                }else {
                    json.setMsg("当前快递单已绑定箱码");
                    json.setObj(false);
                }
            }else {
                json.setMsg("当前箱码已绑定快递单");
                json.setObj(false);
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }

    /**
     * 获取快递单
     */
    @PostMapping("/getZsExpressByZsExpress.json")
    public Object getZsExpressByZsExpress(ZsExpress express) {
        Json json = new Json();
        try {
            ZsExpress zsExpress = expressService.getZsExpressByExpressCode(express);
            if (null!=zsExpress){
                BoxOrder boxOrder = boxOrderService.getBoxOrderByBoxCode(zsExpress.getBoxCode());
                if (null != boxOrder) {
                    PSaOrderPDA saOrderPDA = boxOrderService.getBoxOrderByBoxCodeForPDA(boxOrder);
                    saOrderPDA.setBoxOrder(boxOrder);
                    json.setObj(saOrderPDA);
                } else {
                    json.setMsg("当前快递单的箱码数据有误！");
                }
                json.setMsg("查询成功");
            }else {
                json.setMsg("无当前查询条件的数据");
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }


}
