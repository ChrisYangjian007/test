package com.dalian.sea.controller;

import com.dalian.sea.model.*;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.ZsBoxOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 杨建 on 2018/5/8.
 */
@Controller
@RequestMapping(value = "/box")
public class ViewBoxController {
    @Autowired
    private ZsBoxOrderService boxOrderService;
    /**
     * 主页面
     * @param
     * @return
     */
    @RequestMapping(value = "/{boxCode}")
    public String Index(HttpServletRequest request,@PathVariable(name = "boxCode")String boxCode){
        PSaOrderPDA saOrderPDA = new PSaOrderPDA();
        if (null!=boxCode&&!"".equals(boxCode)) {
            BoxOrder boxOrder = boxOrderService.getBoxOrderByBoxCode(boxCode);
            if (null != boxOrder) {
                saOrderPDA = boxOrderService.getBoxOrderByBoxCodeForPDA(boxOrder);
                saOrderPDA.setBoxOrder(boxOrder);
                request.setAttribute("boxOrder", boxOrder);
            }
        }
        request.setAttribute("saOrderPDA",saOrderPDA);
        return "box/box";
    }
}
