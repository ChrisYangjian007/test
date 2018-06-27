package com.dalian.sea.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.BaDataDictionaryDetails;
import com.dalian.sea.model.BaFormAttributeValue;
import com.dalian.sea.model.BaUser;
import com.dalian.sea.model.ZsWorkProcess;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * AndroidInspectionApi
 *
 * @author TONE
 * @date 2018/3/14.
 */
@Slf4j
@RestController
@RequestMapping("/inspection")
public class AndroidInspectionApi {
    @Autowired
    private ZsProduceTaskService zsProduceTaskService;
    @Autowired
    private BaUserService baUserService;
    @Autowired
    private BaDataDictionaryDetailsService detailsService;
    @Autowired
    private BaFormAttributeService baFormAttributeService;
    @Autowired
    private BaFormAttributeValueService baFormAttributeValueService;
    @Autowired
    private ZsWorkProcessService zsWorkProcessService;
    @Autowired
    private SaLeaveStockDetailService saLeaveStockDetailService;


    @PostMapping("/getInspectionHead.json")
    public Object getInspectionHead(Long produceTaskId){
        Json json = new Json();
        try {
            TaskDetailHeadPDA taskDetailHeadPDA = new TaskDetailHeadPDA();
            List<PBaFormAttributeValue> inspectionHead = baFormAttributeValueService.getBaFormAttributeValueByWorkProcessIdForPDA(produceTaskId,2);
            taskDetailHeadPDA.setTaskDetailHead(inspectionHead);
            List<SaLeaveStockDetailPara> paraList = saLeaveStockDetailService.getLeaveStockDetailByProduceId(produceTaskId);
            taskDetailHeadPDA.setSaLeaveStockDetailParaList(paraList);
            json.setObj(taskDetailHeadPDA);
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }

    @PostMapping("/getInspectionBody.json")
    public Object getInspectionBody(PBaFormAttributeValue attributeValue,Long companyId){
        Json json = new Json();
        try {
            List<BaUser> userList = baUserService.getBaUserByUserType(1,companyId);
            Type attributeValueDataType = new TypeReference<List<AttributeValueData>>() {}.getType();
            List<AttributeValueData> attributeValueData;
            PWorkProcessPDA task =zsWorkProcessService.getWorkProcessById(attributeValue.getWorkProcessId());
            attributeValue.setHandleType((byte) 1);
            attributeValue = baFormAttributeValueService.getBaFormAttributeValueByTaskAndProcessAndTypeAndIndex(attributeValue);
            if (null!=attributeValue) {
                attributeValueData = JSON.parseObject(attributeValue.getObjectParameterJson(), attributeValueDataType);
                task.setOperationAttributeValue(attributeValue);
                task.setOperationValueDataList(attributeValueData);
                for (PBaFormAttribute attribute : task.getInspection()) {
                    if (1 == attribute.getDataSourceType()) {
                        List<PBaDataDictionaryDetails> dictionaryDetails = detailsService.getBaDataDictionaryDetailsByCode(attribute.getDataSourceCode());
                        if (null != dictionaryDetails && dictionaryDetails.size() > 0) {
                            attribute.setDictionaryDetails(dictionaryDetails);
                        }
                    } else if (2 == attribute.getDataSourceType()) {
                        if (null != userList && userList.size() > 0) {
                            attribute.setPdaUser(userList);
                        }
                    }
                }
                for (PBaFormAttribute attribute : task.getOperation()) {
                    for (AttributeValueData valueData : task.getOperationValueDataList()) {
                        if (valueData.getAttributeId().equals(attribute.getFormAttributeId())) {
                            if (1 == attribute.getDataSourceType()) {
                                if (null == valueData.getValue() || "".equals(valueData.getValue())) {
                                    valueData.setName("");
                                } else {
                                    Long value = new Long((String) valueData.getValue());
                                    BaDataDictionaryDetails details = detailsService.getBaDataDictionaryDetailsById(value);
                                    if (null!=details) {
                                        valueData.setName(details.getCName());
                                    }else {
                                        valueData.setName(valueData.getValue());
                                    }
                                }
                            } else if (2 == attribute.getDataSourceType() || 5 == attribute.getRestrictiveConditions()) {
                                if (null == valueData.getValue() || "".equals(valueData.getValue())) {
                                    valueData.setName("");
                                } else {
                                    Long value = new Long((String) valueData.getValue());
                                    PBaUser user = baUserService.getBaUserByUserId(value);
                                    if (null!=user) {
                                        valueData.setName(user.getCName());
                                    }else {
                                        valueData.setName(valueData.getValue());
                                    }
                                }
                            } else {
                                valueData.setName(valueData.getValue());
                            }
                        }
                    }
                }
                attributeValue.setHandleType((byte) 2);
                attributeValue = baFormAttributeValueService.getBaFormAttributeValueByTaskAndProcessAndTypeAndIndex(attributeValue);
                if (null != attributeValue) {
                    attributeValueData = JSON.parseObject(attributeValue.getObjectParameterJson(), attributeValueDataType);
                    task.setAuditorAttributeValue(attributeValue);
                    task.setAuditorValueDataList(attributeValueData);
                    for (PBaFormAttribute attribute : task.getAuditor()) {
                        for (AttributeValueData valueData : task.getAuditorValueDataList()) {
                            if (valueData.getAttributeId().equals(attribute.getFormAttributeId())) {
                                if (1 == attribute.getDataSourceType()) {
                                    if (null == valueData.getValue() || "".equals(valueData.getValue())) {
                                        valueData.setName("");
                                    } else {
                                        Long value = new Long((String) valueData.getValue());
                                        BaDataDictionaryDetails details = detailsService.getBaDataDictionaryDetailsById(value);
                                        if (null!=details) {
                                            valueData.setName(details.getCName());
                                        }else {
                                            valueData.setName(valueData.getValue());
                                        }
                                    }
                                } else if (2 == attribute.getDataSourceType() || 5 == attribute.getRestrictiveConditions()) {
                                    if (null == valueData.getValue() || "".equals(valueData.getValue())) {
                                        valueData.setName("");
                                    } else {
                                        Long value = new Long((String) valueData.getValue());
                                        PBaUser user = baUserService.getBaUserByUserId(value);
                                        if (null!=user) {
                                            valueData.setName(user.getCName());
                                        }else {
                                            valueData.setName(valueData.getValue());
                                        }
                                    }
                                } else {
                                    valueData.setName(valueData.getValue());
                                }
                            }
                        }
                    }
                    attributeValue.setHandleType((byte) 3);
                    attributeValue = baFormAttributeValueService.getBaFormAttributeValueByTaskAndProcessAndTypeAndIndex(attributeValue);
                    if (null != attributeValue) {
                        attributeValueData = JSON.parseObject(attributeValue.getObjectParameterJson(), attributeValueDataType);
                        task.setInspectionAttributeValue(attributeValue);
                        task.setInspectionValueDataList(attributeValueData);
                        for (PBaFormAttribute attribute : task.getInspection()) {
                            for (AttributeValueData valueData : task.getInspectionValueDataList()) {
                                if (valueData.getAttributeId().equals(attribute.getFormAttributeId())) {
                                    if (1 == attribute.getDataSourceType()) {
                                        Long value = new Long((String) valueData.getValue());
                                        BaDataDictionaryDetails details = detailsService.getBaDataDictionaryDetailsById(value);
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
            }
            json.setSuccess(true);
            json.setObj(task);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }

    @PostMapping("/insertInspection.json")
    public Object insertInspection(PBaFormAttributeValue attributeValue){
        Json json = new Json();
        try {
            attributeValue.setHandleType((byte) 3);
            PBaFormAttributeValue inspection = baFormAttributeValueService.getBaFormAttributeValueByTaskAndProcessAndTypeAndIndex(attributeValue);
            if (null==inspection) {
                PZsWorkProcess workProcess = zsWorkProcessService.getZsWorkProcessByWorkProcessId(attributeValue.getWorkProcessId());
                Long id = baFormAttributeValueService.addBaFormAttributeValue(attributeValue, workProcess.getCName());
                if (null != id) {
                    json.setMsg("成功");
                    json.setObj(id);
                } else {
                    json.setMsg("失败");
                }
            }else {
                json.setMsg("当前工序已添加巡检记录！");
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }



}
