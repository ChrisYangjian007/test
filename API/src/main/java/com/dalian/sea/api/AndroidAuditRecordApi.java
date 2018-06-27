package com.dalian.sea.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.BaDataDictionaryDetails;
import com.dalian.sea.model.BaFormAttributeValue;
import com.dalian.sea.model.BaUser;
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
 * AndroidAuditRecordApi
 *
 * @author TONE
 * @date 2018/3/13.
 */
@Slf4j
@RestController
@RequestMapping("/auditRecord")
public class AndroidAuditRecordApi {
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


    @PostMapping("/auditRecord.json")
    public Object auditRecord(Long produceTaskId,Long companyId){
        Json json = new Json();
        try {
            if (null!=produceTaskId) {
                PZsProduceTask produceTask = zsProduceTaskService.getProduceTaskByDetailId(produceTaskId);
                if (null!=produceTask){
                    if (null!=produceTask.getWorkProcessId()){
                        if (1==produceTask.getCheckStatus()){
                            PWorkProcessPDA task =zsWorkProcessService.getWorkProcessById(produceTask.getWorkProcessId());
                            if (null!=task) {
                                task.setProduceTask(produceTask);
                                List<SaLeaveStockDetailPara> paraList = saLeaveStockDetailService.getLeaveStockDetailByProduceId(produceTaskId);
                                task.setSaLeaveStockDetailParaList(paraList);
                                task.setInspection(new ArrayList<>());
                                BaFormAttributeValue attributeValue = baFormAttributeValueService.getBaFormAttributeValueByTaskAndProcessAndType(produceTaskId, produceTask.getWorkProcessId(), 1);
                                if (null != attributeValue) {
                                    List<BaUser> userList = baUserService.getBaUserByUserType(1,companyId);
                                    Type type = new TypeReference<List<AttributeValueData>>() {}.getType();
                                    List<AttributeValueData> attributeValueData = JSON.parseObject(attributeValue.getObjectParameterJson(), type);
                                    task.setOperationAttributeValue(attributeValue);
                                    task.setOperationValueDataList(attributeValueData);
                                    for (PBaFormAttribute attribute : task.getOperation()) {
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
                                        for (AttributeValueData valueData : attributeValueData) {
                                            if (valueData.getAttributeId().equals(attribute.getFormAttributeId())) {
                                                if (1 == attribute.getDataSourceType()) {
                                                    if (null==valueData.getValue()||"".equals(valueData.getValue())) {
                                                        valueData.setName("");
                                                    }else {
                                                        Long value = new Long((String) valueData.getValue());
                                                        BaDataDictionaryDetails details = detailsService.getBaDataDictionaryDetailsById(value);
                                                        if(null!=details) {
                                                            valueData.setName(details.getCName());
                                                        }else {
                                                            valueData.setName(valueData.getValue());
                                                        }
                                                    }
                                                } else if (2 == attribute.getDataSourceType() || 5 == attribute.getRestrictiveConditions()) {
                                                    if (null==valueData.getValue()||"".equals(valueData.getValue())) {
                                                        valueData.setName("");
                                                    }else {
                                                        Long value = new Long((String) valueData.getValue());
                                                        PBaUser user = baUserService.getBaUserByUserId(value);
                                                        if(null!=user) {
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
                                    for (PBaFormAttribute attribute : task.getAuditor()) {
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
                                    json.setMsg("查询成功");
                                    json.setObj(task);
                                } else {
                                    json.setMsg("该生产任务当前工序操作有误，请联系管理员！");
                                }
                            }else {
                                json.setMsg("该生产任务当前工序不存在！");
                            }
                        }else if (2==produceTask.getCheckStatus()){
                            json.setMsg("该生产任务当前工序已审核！");
                        }else {
                            json.setMsg("该生产任务当前工序未操作！");
                        }
                    }else {
                        json.setMsg("该生产任务当前工序为空！");
                    }
                }else {
                    json.setMsg("当前生产任务编号数据有误！");
                }
            }else {
                json.setMsg("无生产任务编号，无法获取数据！");
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }


    @PostMapping("/insertAuditRecord.json")
    public Object insertAttributeValue(PBaFormAttributeValue attributeValue){
        Json json = new Json();
        try {
            if (null!=attributeValue.getCreateUserId()) {
                PBaUser user = baUserService.getBaUserByUserId(attributeValue.getCreateUserId());
                if (null != user) {
                    if (null != attributeValue.getProduceTaskId()) {
                        PZsProduceTask produceTask = zsProduceTaskService.getProduceTaskByDetailId(attributeValue.getProduceTaskId());
                        if (null != produceTask) {
                            if (null != attributeValue.getWorkProcessId()) {
                                PZsWorkProcess workProcess = zsWorkProcessService.getZsWorkProcessByWorkProcessId(attributeValue.getWorkProcessId());
                                if (null != workProcess) {
                                    attributeValue.setHandleType((byte) 1);
                                    PBaFormAttributeValue formAttributeValue = baFormAttributeValueService.getBaFormAttributeValueByTaskAndProcessAndTypeAndIndex(attributeValue);
                                    if (null!=formAttributeValue) {
                                        attributeValue.setHandleType((byte) 2);
                                        PBaFormAttributeValue attribute = baFormAttributeValueService.getBaFormAttributeValueByTaskAndProcessAndTypeAndIndex(attributeValue);
                                        if (null==attribute) {
                                            Boolean boo;
                                            if (null == attributeValue.getObjectParameterJson() || "".equals(attributeValue.getObjectParameterJson())) {
                                                attributeValue.setObjectParameterJson(attributeValue.getObjectParameterJsonTwo());
                                                boo = baFormAttributeValueService.addOrUpdateBaFormAttributeValue(null, attributeValue,workProcess.getCName());
                                            } else {
                                                formAttributeValue.setObjectParameterJson(attributeValue.getObjectParameterJson());
                                                attributeValue.setObjectParameterJson(attributeValue.getObjectParameterJsonTwo());
                                                boo = baFormAttributeValueService.addOrUpdateBaFormAttributeValue(formAttributeValue, attributeValue,workProcess.getCName());
                                            }
                                            if (boo) {
                                                json.setMsg("成功");
                                                json.setSuccess(true);
                                            } else {
                                                json.setMsg("失败");
                                            }
                                        } else {
                                            json.setMsg("当前编号的工序审核记录已有！");
                                        }
                                    } else {
                                        json.setMsg("当前编号的工序操作记录未添加！");
                                    }
                                } else {
                                    json.setMsg("当前编号的工序数据有误！");
                                }
                            } else {
                                json.setMsg("工序编号无，无法添加信息");
                            }
                        } else {
                            json.setMsg("当前编号的生产任务数据有误！");
                        }
                    } else {
                        json.setMsg("生产任务编号无，无法添加信息");
                    }
                } else {
                    json.setMsg("当前编号的用户数据有误！");
                }
            } else {
                json.setMsg("用户编号无，无法添加信息");
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }


}
