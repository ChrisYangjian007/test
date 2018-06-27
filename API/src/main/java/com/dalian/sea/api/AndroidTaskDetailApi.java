package com.dalian.sea.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.json.AttributeLogJson;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.BaDataDictionaryDetails;
import com.dalian.sea.model.BaUser;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * AndroidTaskDetailApi
 *
 * @author TONE
 * @date 2018/3/15.
 */
@Slf4j
@RestController
@RequestMapping("/taskDetail")
public class AndroidTaskDetailApi {
    @Autowired
    private BaFormAttributeValueService baFormAttributeValueService;
    @Autowired
    private ZsWorkProcessService zsWorkProcessService;
    @Autowired
    private BaUserService baUserService;
    @Autowired
    private BaDataDictionaryDetailsService dataDictionaryDetailsService;
    @Autowired
    private SaLeaveStockDetailService saLeaveStockDetailService;


    @PostMapping("/taskDetailHead.json")
    public Object taskDetailHead(Long produceTaskId){
        Json json = new Json();
        try {
            TaskDetailHeadPDA taskDetailHeadPDA = new TaskDetailHeadPDA();
            List<PBaFormAttributeValue> taskDetailHead = baFormAttributeValueService.getBaFormAttributeValueByWorkProcessIdForPDA(produceTaskId,2);
            taskDetailHeadPDA.setTaskDetailHead(taskDetailHead);
            List<SaLeaveStockDetailPara> paraList = saLeaveStockDetailService.getLeaveStockDetailByProduceId(produceTaskId);
            taskDetailHeadPDA.setSaLeaveStockDetailParaList(paraList);
            json.setObj(taskDetailHeadPDA);
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }

    @PostMapping("/taskDetailBody.json")
    public Object taskDetailBody(PBaFormAttributeValue attributeValue){
        Json json = new Json();
        try {
            PWorkProcessPDA task =zsWorkProcessService.getWorkProcessById(attributeValue.getWorkProcessId());
            attributeValue.setHandleType((byte) 1);
            attributeValue = baFormAttributeValueService.getBaFormAttributeValueByTaskAndProcessAndTypeAndIndex(attributeValue);
            Type type = new TypeReference<List<AttributeValueData>>() {}.getType();
            List<AttributeValueData> attributeValueData = JSON.parseObject(attributeValue.getObjectParameterJson(), type);
            task.setOperationAttributeValue(attributeValue);
            task.setOperationValueDataList(attributeValueData);
            for (PBaFormAttribute attribute : task.getOperation()) {
                for (AttributeValueData valueData : task.getOperationValueDataList()) {
                    if (valueData.getAttributeId().equals(attribute.getFormAttributeId())) {
                        if (1 == attribute.getDataSourceType()) {
                            Long value = new Long((String) valueData.getValue());
                            BaDataDictionaryDetails details = dataDictionaryDetailsService.getBaDataDictionaryDetailsById(value);
                            if (null!=details) {
                                valueData.setName(details.getCName());
                            }else {
                                valueData.setName(valueData.getValue());
                            }
                        } else if (2 == attribute.getDataSourceType() || 5 == attribute.getRestrictiveConditions()) {
                            Long value = new Long((String) valueData.getValue());
                            PBaUser user = baUserService.getBaUserByUserId(value);
                            if (null!=user) {
                                valueData.setName(user.getCName());
                            }else {
                                valueData.setName(valueData.getValue());
                            }
                        } else {
                            valueData.setName(valueData.getValue());
                        }
                    }
                }
            }
            attributeValue.setHandleType((byte) 2);
            attributeValue = baFormAttributeValueService.getBaFormAttributeValueByTaskAndProcessAndTypeAndIndex(attributeValue);
            if (null!=attributeValue) {
                attributeValueData = JSON.parseObject(attributeValue.getObjectParameterJson(), type);
                task.setAuditorAttributeValue(attributeValue);
                task.setAuditorValueDataList(attributeValueData);
                for (PBaFormAttribute attribute : task.getAuditor()) {
                    for (AttributeValueData valueData : task.getAuditorValueDataList()) {
                        if (valueData.getAttributeId().equals(attribute.getFormAttributeId())) {
                            if (1 == attribute.getDataSourceType()) {
                                Long value = new Long((String) valueData.getValue());
                                BaDataDictionaryDetails details = dataDictionaryDetailsService.getBaDataDictionaryDetailsById(value);
                                if (null!=details) {
                                    valueData.setName(details.getCName());
                                }else {
                                    valueData.setName(valueData.getValue());
                                }
                            } else if (2 == attribute.getDataSourceType() || 5 == attribute.getRestrictiveConditions()) {
                                Long value = new Long((String) valueData.getValue());
                                PBaUser user = baUserService.getBaUserByUserId(value);
                                if (null!=user) {
                                    valueData.setName(user.getCName());
                                }else {
                                    valueData.setName(valueData.getValue());
                                }
                            } else {
                                valueData.setName(valueData.getValue());
                            }
                        }
                    }
                }
                attributeValue.setHandleType((byte) 3);
                attributeValue = baFormAttributeValueService.getBaFormAttributeValueByTaskAndProcessAndTypeAndIndex(attributeValue);
                if (null!=attributeValue) {
                    attributeValueData = JSON.parseObject(attributeValue.getObjectParameterJson(), type);
                    task.setInspectionAttributeValue(attributeValue);
                    task.setInspectionValueDataList(attributeValueData);
                    for (PBaFormAttribute attribute : task.getInspection()) {
                        for (AttributeValueData valueData : task.getInspectionValueDataList()) {
                            if (valueData.getAttributeId().equals(attribute.getFormAttributeId())) {
                                if (1 == attribute.getDataSourceType()) {
                                    Long value = new Long((String) valueData.getValue());
                                    BaDataDictionaryDetails details = dataDictionaryDetailsService.getBaDataDictionaryDetailsById(value);
                                    if (null!=details) {
                                        valueData.setName(details.getCName());
                                    }else {
                                        valueData.setName(valueData.getValue());
                                    }
                                } else if (2 == attribute.getDataSourceType() || 5 == attribute.getRestrictiveConditions()) {
                                    Long value = new Long((String) valueData.getValue());
                                    PBaUser user = baUserService.getBaUserByUserId(value);
                                    if (null!=user) {
                                        valueData.setName(user.getCName());
                                    }else {
                                        valueData.setName(valueData.getValue());
                                    }
                                } else {
                                    valueData.setName(valueData.getValue());
                                }
                            }
                        }
                    }
                }
            }
            json.setObj(task);
            json.setSuccess(true);
            json.setMsg("成功");
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }

    @PostMapping("/getWorkFlowLog.json")
    public Object getWorkFlowLog(Long produceTaskId,Integer page,Integer rows){
        Json json = new Json();
        try {
            List<AttributeLogJson> attributeLogJsonList = new ArrayList<>();
            Type type = new TypeReference<List<AttributeLogJson>>() {}.getType();
            List<PBaFormAttributeValue> valueList = baFormAttributeValueService.getFormAttributeValueByProduceTaskIdForPDA(produceTaskId,page,rows);
            if (null!=valueList&&0!=valueList.size()){
                for (PBaFormAttributeValue value:valueList){
                    List<AttributeLogJson> logJsonList = JSON.parseObject(value.getLoggerJson(), type);
                    for (AttributeLogJson logJson:logJsonList){
                        PBaUser user = baUserService.getBaUserByUserId(logJson.getUserId());
                        logJson.setUserName(user.getCName());
                    }
                    attributeLogJsonList.addAll(logJsonList);
                }
                PageInfo<PBaFormAttributeValue> pageInfo = new PageInfo<>(valueList);
                json.setObj(new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),attributeLogJsonList));
                json.setMsg("查询成功");
            }else {
                json.setMsg("查询暂无");
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }


}
