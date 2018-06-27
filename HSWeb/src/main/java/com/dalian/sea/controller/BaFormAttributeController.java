package com.dalian.sea.controller;

import com.dalian.sea.json.Json;
import com.dalian.sea.model.BaFormAttribute;
import com.dalian.sea.parameter.PBaFormAttribute;
import com.dalian.sea.service.BaFormAttributeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * BaFormAttributeController
 *
 * @author TONE
 * @date 2018/2/8
 */
@Slf4j
@Controller
@RequestMapping(value = "/formAttribute")
public class BaFormAttributeController {

    @Autowired
    private BaFormAttributeService baFormAttributeService;

    /**
     * 获取该工序下的默认当前用户的操作人、审核人、巡检人
     * @param workProcessId
     * @return
     */
    @RequestMapping(value = "/getDefaultFormAttributeByWorkProcessIdAndName.json")
    @ResponseBody
    public Object getDefaultFormAttributeByWorkProcessIdAndName(Long workProcessId){
        Json json = new Json();
        try {
            List<PBaFormAttribute> formAttributeList = baFormAttributeService.getBaFormAttributeListByWorkProcessId(workProcessId);
            json.setSuccess(true);
            json.setObj(formAttributeList);
        }catch (Exception e){
            json.setMsg("服务器异常");
     }
     return json;
    }
}
