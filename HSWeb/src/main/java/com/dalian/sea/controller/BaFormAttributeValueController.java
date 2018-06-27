package com.dalian.sea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.ClientIP;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.AttributeLogJson;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.BaFormAttributeValue;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.BaFormAttributeValueService;
import com.dalian.sea.service.SysSysLogService;
import com.dalian.sea.service.ZsProduceTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * BaFormAttributeValueController
 *
 * @author TONE
 * @date 2018/2/8
 */
@Slf4j
@Controller
@RequestMapping(value = "/formAttributeValue")
public class BaFormAttributeValueController {

    @Autowired
    private BaFormAttributeValueService baFormAttributeValueService;
    @Autowired
    private SysSysLogService sysSysLogService;
    @Autowired
    private ZsProduceTaskService zsProduceTaskService;

    /**
     * 修改value
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateFormAttributeValue.json")
    @ResponseBody
    public Object updateFormAttributeValue(HttpServletRequest request,String test){
        Json json = new Json();
        try {
            JSONObject jsonObject = JSONObject.parseObject(test);
            PFormValueData pFormValueData = jsonObject.toJavaObject(PFormValueData.class);
            Type type = new TypeReference<List<AttributeLogJson>>() {}.getType();
            if(null!=pFormValueData.getPBaFormAttributeValueList()&&0!=pFormValueData.getPBaFormAttributeValueList().size()){
                PZsProduceTask pZsProduceTask = zsProduceTaskService.getProduceTaskByDetailId(pFormValueData.getProduceTaskId());
                List<PBaFormAttributeValue> addFormAttributeValueList = new ArrayList<>();
                List<PBaFormAttributeValue> updFormAttributeValueList = new ArrayList<>();
                Subject user = SecurityUtils.getSubject();
                ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                for(PBaFormAttributeValue pBaFormAttributeValue: pFormValueData.getPBaFormAttributeValueList()){
                    if(null!=pBaFormAttributeValue.getAttributeValueDataList()&&0!=pBaFormAttributeValue.getAttributeValueDataList().size()){
                        String strJson = JSON.toJSONString(pBaFormAttributeValue.getAttributeValueDataList());
                        pBaFormAttributeValue.setObjectParameterJson(strJson);
                        if(null!=pBaFormAttributeValue.getFormAttributeValueId()&&!"".equals(pBaFormAttributeValue.getFormAttributeValueId())){
                            List<AttributeLogJson> attributeLogJsonList = new ArrayList<>();
                            if(null!=pBaFormAttributeValue.getLoggerJson()&&!"".equals(pBaFormAttributeValue.getLoggerJson())){
                                attributeLogJsonList.addAll(JSON.parseObject(pBaFormAttributeValue.getLoggerJson(),type));
                            }
                            AttributeLogJson attributeLogJson = new AttributeLogJson();
                            attributeLogJson.setUserId(shiroUser.getId());
                            attributeLogJson.setCreateDate(new Date());
                            String name = "";
                            if(1==pBaFormAttributeValue.getHandleType()){
                                name = "操作记录";
                            }
                            if(2==pBaFormAttributeValue.getHandleType()){
                                name = "审核记录";
                            }
                            if(3==pBaFormAttributeValue.getHandleType()){
                                name = "巡检记录";
                            }
                            attributeLogJson.setContext("修改-"+pBaFormAttributeValue.getWorkProcessName()+"-"+name);
                            attributeLogJsonList.add(attributeLogJson);
                            String loggerJson = JSON.toJSONString(attributeLogJsonList);
                            pBaFormAttributeValue.setLoggerJson(loggerJson);
                            pBaFormAttributeValue.setUpdateUserId(shiroUser.getId());
                            updFormAttributeValueList.add(pBaFormAttributeValue);
                        }else {
                            List<AttributeLogJson> attributeLogJsonList = new ArrayList<>();
                            if(null!=pBaFormAttributeValue.getLoggerJson()&&!"".equals(pBaFormAttributeValue.getLoggerJson())){
                                attributeLogJsonList.addAll(JSON.parseObject(pBaFormAttributeValue.getLoggerJson(),type));
                            }
                            if(null!=pBaFormAttributeValue.getAttributeValueDataList()&&0!=pBaFormAttributeValue.getAttributeValueDataList().size()){
                            AttributeLogJson attributeLogJson = new AttributeLogJson();
                            attributeLogJson.setUserId(shiroUser.getId());
                            attributeLogJson.setCreateDate(new Date());
                            String name = "";
                            if(1==pBaFormAttributeValue.getHandleType()){
                                name = "操作记录";
                            }
                            if(2==pBaFormAttributeValue.getHandleType()){
                                name = "审核记录";
                            }
                            if(3==pBaFormAttributeValue.getHandleType()){
                                name = "巡检记录";
                            }
                            attributeLogJson.setContext("新增-"+pBaFormAttributeValue.getWorkProcessName()+"-"+name);
                            attributeLogJsonList.add(attributeLogJson);
                            String loggerJson = JSON.toJSONString(attributeLogJsonList);
                            pBaFormAttributeValue.setLoggerJson(loggerJson);
                            pBaFormAttributeValue.setCreateUserId(shiroUser.getId());
                            addFormAttributeValueList.add(pBaFormAttributeValue);
                            }
                        }
                    }
                }
                String remark = "";
                if(null!=addFormAttributeValueList&&0!=addFormAttributeValueList.size()){
                    remark += "添加了"+addFormAttributeValueList.size()+"条工序详情";
                }
                if(null!=updFormAttributeValueList&&0!=updFormAttributeValueList.size()){
                    remark += "修改了"+updFormAttributeValueList.size()+"条工序详情";
                }
                if(baFormAttributeValueService.addAndUpdateBaFormAttributeValue(addFormAttributeValueList,updFormAttributeValueList)){
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("编辑工序详情");
                    sysLog.setRemark("编辑("+remark+")工序详情");
                    sysLog.setResourceId(pFormValueData.getResourceId());
                    sysLog.setCreatedUserId(shiroUser.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("编辑完成");
                    json.setSuccess(true);
                }else {
                    json.setMsg("编辑失败");
                }
            }else {
                json.setMsg("数据异常，无法编辑");
            }
        }catch (Exception e){
            json.setMsg("服务器异常！");
        }
        return json;
    }
}
