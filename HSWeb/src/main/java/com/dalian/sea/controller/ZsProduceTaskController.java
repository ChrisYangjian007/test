package com.dalian.sea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.ClientIP;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.ImageJson;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.*;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2018/2/24.
 */
@Slf4j
@Controller
@RequestMapping(value = "/produceTask")
public class ZsProduceTaskController extends LayoutRazor{

    @Autowired
    private ZsProduceTaskService zsProduceTaskService;
    @Autowired
    private BaUserService baUserService;
    @Autowired
    private ZsWarehouseService zsWarehouseService;
    @Autowired
    private PuEnterStockService puEnterStockService;
    @Autowired
    private SaLeaveStockService saLeaveStockService;
    @Autowired
    private ZsWorkProcessService zsWorkProcessService;
    @Autowired
    private ZsWorkFlowService zsWorkFlowService;
    @Autowired
    private SaLeaveStockDetailService saLeaveStockDetailService;
    @Autowired
    private PuEnterStockDetailService puEnterStockDetailService;
    @Autowired
    private BaFormAttributeValueService baFormAttributeValueService;
    @Autowired
    private BaFormAttributeService baFormAttributeService;
    @Autowired
    private BaDataDictionaryDetailsService baDataDictionaryDetailsService;
    @Autowired
    private ZsQrCodeService zsQrCodeService;
    @Autowired
    private SysSysLogService sysSysLogService;

    /**
     * 生产任务管理
     * @param request
     * @return
     */
    @RequestMapping(value = "/produceTaskManage.htm")
    public String productTaskManager(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        List<BaUser> userList = baUserService.getAllUser();
        if(null != userList && 0 != userList.size()){
            request.setAttribute("userList",userList);
        }
        List<ZsWarehouse> warehouseList = zsWarehouseService.getAllWarehouse(user.getId(),user.getCompanyId());
        if(null != warehouseList && 0 != warehouseList.size()){
            request.setAttribute("warehouseList",warehouseList);
        }
        return freeMarkerIndexResult("produceTask/produceTaskManage.ftl",request);
    }

    /**
     * 获取进行中生产任务
     * @param request
     * @param pZsProduceTask
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/GridJson.json")
    @ResponseBody
    public Object GridJson(HttpServletRequest request, PZsProduceTask pZsProduceTask, Integer page, Integer rows){
        List<PZsProduceTask> produceTaskList = zsProduceTaskService.getProcessProduceTask(pZsProduceTask,page,rows);
        PageInfo<PZsProduceTask> pageInfo = new PageInfo<>(produceTaskList);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),produceTaskList);
    }

    /**
     * 获取已结束任务
     * @param request
     * @param pZsProduceTask
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/GridJson2.json")
    @ResponseBody
    public Object GridJson2(HttpServletRequest request, PZsProduceTask pZsProduceTask, Integer page, Integer rows){
        List<PZsProduceTask> produceTaskList = zsProduceTaskService.getEndProduceTask(pZsProduceTask,page,rows);
        PageInfo<PZsProduceTask> pageInfo = new PageInfo<>(produceTaskList);
        return new JqGridParam(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),produceTaskList);
    }

    /**
     * 生产任务详情
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/produceTaskDetailIFrame.htm")
    public String produceTaskDetail(HttpServletRequest request,Long id, Long resourceId){
        request.setAttribute("resourceId",resourceId);
        if(null != id){
            PZsProduceTask pZsProduceTask = zsProduceTaskService.getProduceTaskByDetailId(id);
            //能否产看工序详情 0-不能 1-能
            Integer canValueDetail = 0;
            if(null != pZsProduceTask){
                request.setAttribute("pZsProduceTask",pZsProduceTask);
                List<ZsWorkProcess> workProcessList = zsWorkProcessService.getNotDeleteAndHaveFieldsWorkProcessByProduceTaskId(pZsProduceTask.getProduceTaskId());
                List<PBaFormAttributeValue> formAttributeValueList = baFormAttributeValueService.getFormAttributeValueByProduceTaskId(pZsProduceTask.getProduceTaskId());
                if(null!=workProcessList&&0!=workProcessList.size()||null!=formAttributeValueList&&0!=formAttributeValueList.size()){
                    canValueDetail = 1;
                }
                List<ZsQrCode> zsQrCodeList = zsQrCodeService.getQrCodeByProduceTaskId(id);
                String code = "";
                if(null!=zsQrCodeList&&0!=zsQrCodeList.size()){
                    int i = 1 ;
                    for(ZsQrCode zsQrCode:zsQrCodeList){
                        if(i==zsQrCodeList.size()){
                            code += zsQrCode.getQrCode();
                        }else {
                            code += zsQrCode.getQrCode() + "、";
                        }
                        i++;
                    }
                }
                request.setAttribute("code",code);
                ZsWorkFlow zsWorkFlow = zsWorkFlowService.getWorkFlowByProduceTaskId(id);
                    List<PZsWorkProcess> pZsWorkProcessList = zsWorkProcessService.getZsWorkProcessAndMaxHandleTypeValueByProduceTaskId(pZsProduceTask.getProduceTaskId());
                    if(null!=pZsWorkProcessList&&0!=pZsWorkProcessList.size()){
                        request.setAttribute("pZsWorkProcessList",pZsWorkProcessList);
                    }
                List<SaLeaveStockDetailPara> saLeaveStockDetailParaList = saLeaveStockDetailService.getLeaveStockDetailByProduceId(id);
                if(null!=saLeaveStockDetailParaList&&0!=saLeaveStockDetailParaList.size()){
                    request.setAttribute("saLeaveStockDetailParaList",saLeaveStockDetailParaList);
                }
                List<PuEnterStockDetailPara> puEnterStockDetailParaList = puEnterStockDetailService.getEnterStockDetailByProduceTaskId(id);
                if(null!=puEnterStockDetailParaList&&0!=puEnterStockDetailParaList.size()){
                    request.setAttribute("puEnterStockDetailParaList",puEnterStockDetailParaList);
                }
            }
            request.setAttribute("canValueDetail",canValueDetail);
        }
        return freeMarkerIndexResult("produceTask/produceTaskDetailIFrame.ftl",request);
    }

    /**
     * 工序详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/fromAttributeIFrame.htm")
    public String fromAttributeIFrame(HttpServletRequest request,Long id, Long resourceId){
        request.setAttribute("resourceId", resourceId);
        PZsProduceTask pZsProduceTask = zsProduceTaskService.getProduceTaskByDetailId(id);
        if(null!=pZsProduceTask){
            request.setAttribute("pZsProduceTask",pZsProduceTask);
        }
        //是否有工序 0-无 1-有
        Integer isHaveWorkProcess = 0 ;
        List<ZsWorkProcess> workProcessList = zsWorkProcessService.getNotDeleteAndHaveFieldsWorkProcessByProduceTaskId(pZsProduceTask.getProduceTaskId());
        if(null!=workProcessList&&0!=workProcessList.size()){
            isHaveWorkProcess = 1 ;
        }
        request.setAttribute("isHaveWorkProcess",isHaveWorkProcess);
        //获取生产任务全部工序和value
        List<PZsWorkProcess> pZsWorkProcessList = zsWorkProcessService.getAllPWorkProcessAndPFromAttributeValueAndByProduceTask(id);
        if(null!=pZsWorkProcessList&&0!=pZsWorkProcessList.size()){
            Type type = new TypeReference<List<AttributeValueData>>() {}.getType();
            for(PZsWorkProcess pZsWorkProcess : pZsWorkProcessList){
                if(null!=pZsWorkProcess.getPBaFormAttributeValueList()&&0!=pZsWorkProcess.getPBaFormAttributeValueList().size()){
                    //将value转换成集合 并将value集合按handleType分组
                    for(BaFormAttributeValue baFormAttributeValue :pZsWorkProcess.getPBaFormAttributeValueList()){
                        List<AttributeValueData> attributeValueData = new ArrayList<>();
                        attributeValueData = JSON.parseObject(baFormAttributeValue.getObjectParameterJson(), type);
                        pZsWorkProcess.setValueDataList(attributeValueData);
                        if(null!=attributeValueData&&0!=attributeValueData.size()){
                            if(1==baFormAttributeValue.getHandleType()){
                                pZsWorkProcess.getValueDataList1().addAll(attributeValueData);
                            }else if(2==baFormAttributeValue.getHandleType()){
                                pZsWorkProcess.getValueDataList2().addAll(attributeValueData);
                            }else {
                                pZsWorkProcess.getValueDataList3().addAll(attributeValueData);
                            }
                        }
                    }
                    List<PBaFormAttribute> baFormAttributes = baFormAttributeService.getBaFormAttributeListByWorkProcessId(pZsWorkProcess.getWorkProcessId());
                    if(null!=baFormAttributes&&0!=baFormAttributes.size()){
                        //找到value的对应的名字
                        for(PBaFormAttribute baFormAttribute :baFormAttributes){
                            if(null!=pZsWorkProcess.getValueDataList1()&&0!=pZsWorkProcess.getValueDataList1().size()){
                                for(AttributeValueData valueData:pZsWorkProcess.getValueDataList1()){
                                    if(valueData.getAttributeId().equals(baFormAttribute.getFormAttributeId())){
                                        valueData.setName(baFormAttribute.getPropertyName());
                                        if (1 == baFormAttribute.getDataSourceType()) {
                                            Long value = new Long((String) valueData.getValue());
                                            BaDataDictionaryDetails details = baDataDictionaryDetailsService.getBaDataDictionaryDetailsById(value);
                                            if(null!=details){
                                                valueData.setValue(details.getCName());
                                            }
                                        }else if (2==baFormAttribute.getDataSourceType()||5==baFormAttribute.getRestrictiveConditions()) {
                                            Long value = new Long((String) valueData.getValue());
                                            PBaUser user = baUserService.getBaUserByUserId(value);
                                            if(null!=user){
                                                valueData.setValue(user.getCName());
                                            }
                                        }
                                    }
                                }
                            }
                            if(null!=pZsWorkProcess.getValueDataList2()&&0!=pZsWorkProcess.getValueDataList2().size()){
                                for(AttributeValueData valueData:pZsWorkProcess.getValueDataList2()){
                                    if(valueData.getAttributeId().equals(baFormAttribute.getFormAttributeId())){
                                        valueData.setName(baFormAttribute.getPropertyName());
                                        if (1 == baFormAttribute.getDataSourceType()) {
                                            Long value = new Long((String) valueData.getValue());
                                            BaDataDictionaryDetails details = baDataDictionaryDetailsService.getBaDataDictionaryDetailsById(value);
                                            if(null!=valueData){
                                                valueData.setValue(details.getCName());
                                            }
                                        }else if (2==baFormAttribute.getDataSourceType()||5==baFormAttribute.getRestrictiveConditions()) {
                                            Long value = new Long((String) valueData.getValue());
                                            PBaUser user = baUserService.getBaUserByUserId(value);
                                            if(null!=user){
                                                valueData.setValue(user.getCName());
                                            }
                                        }
                                    }
                                }
                            }
                            if(null!=pZsWorkProcess.getValueDataList3()&&0!=pZsWorkProcess.getValueDataList3().size()){
                                for(AttributeValueData valueData:pZsWorkProcess.getValueDataList3()){
                                    if(valueData.getAttributeId().equals(baFormAttribute.getFormAttributeId())){
                                        valueData.setName(baFormAttribute.getPropertyName());
                                        if (1 == baFormAttribute.getDataSourceType()) {
                                            Long value = new Long((String) valueData.getValue());
                                            BaDataDictionaryDetails details = baDataDictionaryDetailsService.getBaDataDictionaryDetailsById(value);
                                            if(null!=valueData){
                                                valueData.setValue(details.getCName());
                                            }
                                        }else if (2==baFormAttribute.getDataSourceType()||5==baFormAttribute.getRestrictiveConditions()) {
                                            Long value = new Long((String) valueData.getValue());
                                            PBaUser user = baUserService.getBaUserByUserId(value);
                                            if(null!=user){
                                                valueData.setValue(user.getCName());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            request.setAttribute("pZsWorkProcessList",pZsWorkProcessList);
        }
        return freeMarkerIndexResult("produceTask/fromAttributeIFrame.ftl",request);
    }

    /**
     * 删除生产任务
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteProduceTask.json")
    @ResponseBody
    public Object deleteProduceTask(HttpServletRequest request,Long id, Long resourceId){
        Json json = new Json();
        try {
                ZsProduceTask zsProduceTask = zsProduceTaskService.getProduceTaskByDetailId(id);
                if(null != zsProduceTask){
                    Subject user = SecurityUtils.getSubject();
                    ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
                    zsProduceTask.setUpdateUserId(shiroUser.getId());
                    if(zsProduceTaskService.deleteProduceTaskById(zsProduceTask)){
                        SysSysLog sysLog = new SysSysLog();
                        String ip = ClientIP.getClientIP(request);
                        sysLog.setIpAddress(ip);
                        sysLog.setCName("删除生产任务");
                        sysLog.setRemark("删除编号为("+zsProduceTask.getProduceTaskNo()+")生产任务");
                        sysLog.setResourceId(resourceId);
                        sysLog.setCreatedUserId(shiroUser.getId());
                        sysSysLogService.addSysSysLog(sysLog);
                        json.setMsg("删除成功");
                        json.setSuccess(true);
                    }else {
                        json.setMsg("删除失败");
                    }
                }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 编辑工序详情
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateFormAttributeIFrame.htm")
    public String updateFormAttributeIFrame(HttpServletRequest request,Long id, Long resourceId){
        request.setAttribute("resourceId", resourceId);
        Subject user = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
        List<BaUser> baUserList = baUserService.getBaUserByUserType(1,shiroUser.getCompanyId());
        if(null!=baUserList&&0!=baUserList.size()){
            request.setAttribute("baUserList",baUserList);
        }
        request.setAttribute("produceTaskId",id);
        List<PZsWorkProcess> pZsWorkProcessList = zsWorkProcessService.getPWorkProcessAndPFromAttributeValueAndByProduceTask(id);
        List<PBaFormAttribute> baFormAttributeList = baFormAttributeService.getFormAttributeByProduceTaskId(id);
        if(null!=pZsWorkProcessList&&0!=pZsWorkProcessList.size()){
            Type type = new TypeReference<List<AttributeValueData>>() {}.getType();
            for(PZsWorkProcess pZsWorkProcess: pZsWorkProcessList){
                //操作字段
                List<PBaFormAttribute> formAttribute1 = new ArrayList<>();
                //巡检字段
                List<PBaFormAttribute> formAttribute2 = new ArrayList<>();
                //审核字段
                List<PBaFormAttribute> formAttribute3 = new ArrayList<>();
                if(null!=baFormAttributeList&&0!=baFormAttributeList.size()){
                    //获取该工序对应的字段并按照 handleType：1-操作 2-巡检 3-审核 放入不同的集合
                    for(PBaFormAttribute pBaFormAttribute: baFormAttributeList){
                        if(1==pBaFormAttribute.getHandleType()&&pBaFormAttribute.getWorkProcessId().equals(pZsWorkProcess.getWorkProcessId())){
                                formAttribute1.add(pBaFormAttribute);
                        }
                        if(2==pBaFormAttribute.getHandleType()&&pBaFormAttribute.getWorkProcessId().equals(pZsWorkProcess.getWorkProcessId())){
                                formAttribute2.add(pBaFormAttribute);
                        }
                        if(3==pBaFormAttribute.getHandleType()&&pBaFormAttribute.getWorkProcessId().equals(pZsWorkProcess.getWorkProcessId())){
                                formAttribute3.add(pBaFormAttribute);
                        }
                    }
                }
                //将字段集合放入工序
                pZsWorkProcess.setTableField1(formAttribute1);
                pZsWorkProcess.setTableField2(formAttribute2);
                pZsWorkProcess.setTableField3(formAttribute3);
                //操作工序详情
                List<PBaFormAttributeValue> formAttributeValue1 = new ArrayList<>();
                //巡检工序详情
                List<PBaFormAttributeValue> formAttributeValue2 = new ArrayList<>();
                //审核工序详情
                List<PBaFormAttributeValue> formAttributeValue3 = new ArrayList<>();
                if(null!=pZsWorkProcess.getPBaFormAttributeValueList()&&0!=pZsWorkProcess.getPBaFormAttributeValueList().size()){
                    for(PBaFormAttributeValue pBaFormAttributeValue: pZsWorkProcess.getPBaFormAttributeValueList()){
                        //将工序详情按照 handleType：1-操作详情 2-巡检详情 3-审核详情 分别放入不同的集合
                        if(1==pBaFormAttributeValue.getHandleType()){
                            //并将各个详情对应的字段集合放入详情中
                            if(null!=formAttribute1&&0!=formAttribute1.size()){
                                List<PBaFormAttribute> attributes = new ArrayList<>();
                                for(PBaFormAttribute pBaFormAttribute:formAttribute1){
                                    PBaFormAttribute baFormAttribute = new PBaFormAttribute();
                                    baFormAttribute.setFormAttributeId(pBaFormAttribute.getFormAttributeId());
                                    baFormAttribute.setModuleId(pBaFormAttribute.getModuleId());
                                    baFormAttribute.setPropertyName(pBaFormAttribute.getPropertyName());
                                    baFormAttribute.setControlName(pBaFormAttribute.getControlName());
                                    baFormAttribute.setControlType(pBaFormAttribute.getControlType());
                                    baFormAttribute.setRestrictiveConditions(pBaFormAttribute.getRestrictiveConditions());
                                    baFormAttribute.setControlStyle(pBaFormAttribute.getControlStyle());
                                    baFormAttribute.setControlValidator(pBaFormAttribute.getControlValidator());
                                    baFormAttribute.setImportLength(pBaFormAttribute.getImportLength());
                                    baFormAttribute.setDefaultValue(pBaFormAttribute.getDefaultValue());
                                    baFormAttribute.setAttributesProperty(pBaFormAttribute.getAttributesProperty());
                                    baFormAttribute.setDataSourceType(pBaFormAttribute.getDataSourceType());
                                    baFormAttribute.setDataSourceCode(pBaFormAttribute.getDataSourceCode());
                                    baFormAttribute.setDataSource(pBaFormAttribute.getDataSource());
                                    baFormAttribute.setControlColspan(pBaFormAttribute.getControlColspan());
                                    baFormAttribute.setRemark(pBaFormAttribute.getRemark());
                                    baFormAttribute.setListIndex(pBaFormAttribute.getListIndex());
                                    baFormAttribute.setWorkProcessId(pBaFormAttribute.getWorkProcessId());
                                    baFormAttribute.setHandleType(pBaFormAttribute.getHandleType());
                                    baFormAttribute.setStatus(pBaFormAttribute.getStatus());
                                    baFormAttribute.setDictionaryDetails(pBaFormAttribute.getDictionaryDetails());
                                    baFormAttribute.setTypeIndex(pBaFormAttributeValue.getTypeIndex());
                                    attributes.add(baFormAttribute);
                                }
                                pBaFormAttributeValue.setPBaFormAttributeList(attributes);
                            }
                            formAttributeValue1.add(pBaFormAttributeValue);
                        }

                        if(2==pBaFormAttributeValue.getHandleType()){
                            //并将各个详情对应的字段集合放入详情中
                            if(null!=formAttribute2&&0!=formAttribute2.size()){
                                List<PBaFormAttribute> attributes = new ArrayList<>();
                                for(PBaFormAttribute pBaFormAttribute:formAttribute2){
                                    PBaFormAttribute baFormAttribute = new PBaFormAttribute();
                                    baFormAttribute.setFormAttributeId(pBaFormAttribute.getFormAttributeId());
                                    baFormAttribute.setModuleId(pBaFormAttribute.getModuleId());
                                    baFormAttribute.setPropertyName(pBaFormAttribute.getPropertyName());
                                    baFormAttribute.setControlName(pBaFormAttribute.getControlName());
                                    baFormAttribute.setControlType(pBaFormAttribute.getControlType());
                                    baFormAttribute.setRestrictiveConditions(pBaFormAttribute.getRestrictiveConditions());
                                    baFormAttribute.setControlStyle(pBaFormAttribute.getControlStyle());
                                    baFormAttribute.setControlValidator(pBaFormAttribute.getControlValidator());
                                    baFormAttribute.setImportLength(pBaFormAttribute.getImportLength());
                                    baFormAttribute.setDefaultValue(pBaFormAttribute.getDefaultValue());
                                    baFormAttribute.setAttributesProperty(pBaFormAttribute.getAttributesProperty());
                                    baFormAttribute.setDataSourceType(pBaFormAttribute.getDataSourceType());
                                    baFormAttribute.setDataSourceCode(pBaFormAttribute.getDataSourceCode());
                                    baFormAttribute.setDataSource(pBaFormAttribute.getDataSource());
                                    baFormAttribute.setControlColspan(pBaFormAttribute.getControlColspan());
                                    baFormAttribute.setRemark(pBaFormAttribute.getRemark());
                                    baFormAttribute.setListIndex(pBaFormAttribute.getListIndex());
                                    baFormAttribute.setWorkProcessId(pBaFormAttribute.getWorkProcessId());
                                    baFormAttribute.setHandleType(pBaFormAttribute.getHandleType());
                                    baFormAttribute.setStatus(pBaFormAttribute.getStatus());
                                    baFormAttribute.setDictionaryDetails(pBaFormAttribute.getDictionaryDetails());
                                    baFormAttribute.setTypeIndex(pBaFormAttributeValue.getTypeIndex());
                                    attributes.add(baFormAttribute);
                                }
                                pBaFormAttributeValue.setPBaFormAttributeList(attributes);
                            }
                            formAttributeValue2.add(pBaFormAttributeValue);
                        }

                        if(3==pBaFormAttributeValue.getHandleType()){
                            //并将各个详情对应的字段集合放入详情中
                            if(null!=formAttribute3&&0!=formAttribute3.size()){
                                List<PBaFormAttribute> attributes = new ArrayList<>();
                                for(PBaFormAttribute pBaFormAttribute:formAttribute3){
                                    PBaFormAttribute baFormAttribute = new PBaFormAttribute();
                                    baFormAttribute.setFormAttributeId(pBaFormAttribute.getFormAttributeId());
                                    baFormAttribute.setModuleId(pBaFormAttribute.getModuleId());
                                    baFormAttribute.setPropertyName(pBaFormAttribute.getPropertyName());
                                    baFormAttribute.setControlName(pBaFormAttribute.getControlName());
                                    baFormAttribute.setControlType(pBaFormAttribute.getControlType());
                                    baFormAttribute.setRestrictiveConditions(pBaFormAttribute.getRestrictiveConditions());
                                    baFormAttribute.setControlStyle(pBaFormAttribute.getControlStyle());
                                    baFormAttribute.setControlValidator(pBaFormAttribute.getControlValidator());
                                    baFormAttribute.setImportLength(pBaFormAttribute.getImportLength());
                                    baFormAttribute.setDefaultValue(pBaFormAttribute.getDefaultValue());
                                    baFormAttribute.setAttributesProperty(pBaFormAttribute.getAttributesProperty());
                                    baFormAttribute.setDataSourceType(pBaFormAttribute.getDataSourceType());
                                    baFormAttribute.setDataSourceCode(pBaFormAttribute.getDataSourceCode());
                                    baFormAttribute.setDataSource(pBaFormAttribute.getDataSource());
                                    baFormAttribute.setControlColspan(pBaFormAttribute.getControlColspan());
                                    baFormAttribute.setRemark(pBaFormAttribute.getRemark());
                                    baFormAttribute.setListIndex(pBaFormAttribute.getListIndex());
                                    baFormAttribute.setWorkProcessId(pBaFormAttribute.getWorkProcessId());
                                    baFormAttribute.setHandleType(pBaFormAttribute.getHandleType());
                                    baFormAttribute.setStatus(pBaFormAttribute.getStatus());
                                    baFormAttribute.setDictionaryDetails(pBaFormAttribute.getDictionaryDetails());
                                    baFormAttribute.setTypeIndex(pBaFormAttributeValue.getTypeIndex());
                                    attributes.add(baFormAttribute);
                                }
                                pBaFormAttributeValue.setPBaFormAttributeList(attributes);
                            }
                            formAttributeValue3.add(pBaFormAttributeValue);
                        }
                    }
                }
                //将工序详情存入工序
                pZsWorkProcess.setPBaFormAttributeValueList1(formAttributeValue1);
                pZsWorkProcess.setPBaFormAttributeValueList2(formAttributeValue2);
                pZsWorkProcess.setPBaFormAttributeValueList3(formAttributeValue3);


                if(null!=pZsWorkProcess.getPBaFormAttributeValueList1()&&0!=pZsWorkProcess.getPBaFormAttributeValueList1().size()){
                    //将工序详情的value放入对用字段的value
                    for(PBaFormAttributeValue pBaFormAttributeValue: pZsWorkProcess.getPBaFormAttributeValueList1()){
                        if(null!=pBaFormAttributeValue.getObjectParameterJson()){
                            //将工序详情变成value集合
                            List<AttributeValueData> attributeValueDataList = JSON.parseObject(pBaFormAttributeValue.getObjectParameterJson(),type);
                            List<PAttributeValueData> pAttributeValueDataList = new ArrayList<>();
                            if(null!=attributeValueDataList&&0!=attributeValueDataList.size()){
                                for(AttributeValueData attributeValueData: attributeValueDataList){
                                    PAttributeValueData pAttributeValueData = new PAttributeValueData();
                                    pAttributeValueData.setTypeIndex(pBaFormAttributeValue.getTypeIndex());
                                    pAttributeValueData.setAttributeId(attributeValueData.getAttributeId());
                                    pAttributeValueData.setValue(attributeValueData.getValue());
                                    pAttributeValueDataList.add(pAttributeValueData);
                                }
                            }
                            //通过工序详情下的字段id和value集合的attribute匹配，将value集合的value赋给字段的value
                            if(null!=pBaFormAttributeValue.getPBaFormAttributeList()&&0!=pBaFormAttributeValue.getPBaFormAttributeList().size()){
                                for(PBaFormAttribute pBaFormAttribute: pBaFormAttributeValue.getPBaFormAttributeList()){
                                    if(null!=pAttributeValueDataList&&0!=pAttributeValueDataList.size()){
                                        for(PAttributeValueData attributeValueData: pAttributeValueDataList){
                                            if(pBaFormAttribute.getFormAttributeId().equals(attributeValueData.getAttributeId())&& Objects.equals(attributeValueData.getTypeIndex(), pBaFormAttribute.getTypeIndex())){
                                                pBaFormAttribute.setValue(attributeValueData.getValue());
                                                if("审核人".equals(pBaFormAttribute.getPropertyName())){
                                                    Long value = new Long(String.valueOf(attributeValueData.getValue()));
                                                    BaUser baUser = baUserService.getBaUserByUserId(value);
                                                    pZsWorkProcess.setReviewerId(value);
                                                    pZsWorkProcess.setReviewerName(baUser.getCName());
                                                    pBaFormAttribute.setDefaultUserName(baUser.getCName());
                                                }
                                                if(5==pBaFormAttribute.getRestrictiveConditions()){
                                                    Long value = new Long(String.valueOf(attributeValueData.getValue()));
                                                    BaUser baUser = baUserService.getBaUserByUserId(value);
                                                    pBaFormAttribute.setDefaultUserName(baUser.getCName());
                                                }
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


                if(null!=pZsWorkProcess.getPBaFormAttributeValueList2()&&0!=pZsWorkProcess.getPBaFormAttributeValueList2().size()){
                    //将工序详情的value放入对用字段的value
                    for(PBaFormAttributeValue pBaFormAttributeValue: pZsWorkProcess.getPBaFormAttributeValueList2()){
                        if(null!=pBaFormAttributeValue.getObjectParameterJson()){
                            //将工序详情变成value集合
                            List<AttributeValueData> attributeValueDataList = JSON.parseObject(pBaFormAttributeValue.getObjectParameterJson(),type);
                            List<PAttributeValueData> pAttributeValueDataList = new ArrayList<>();
                            if(null!=attributeValueDataList&&0!=attributeValueDataList.size()){
                                for(AttributeValueData attributeValueData: attributeValueDataList){
                                    PAttributeValueData pAttributeValueData = new PAttributeValueData();
                                    pAttributeValueData.setTypeIndex(pBaFormAttributeValue.getTypeIndex());
                                    pAttributeValueData.setAttributeId(attributeValueData.getAttributeId());
                                    pAttributeValueData.setValue(attributeValueData.getValue());
                                    pAttributeValueDataList.add(pAttributeValueData);
                                }
                            }
                            //通过工序详情下的字段id和value集合的attribute匹配，将value集合的value赋给字段的value
                            if(null!=pBaFormAttributeValue.getPBaFormAttributeList()&&0!=pBaFormAttributeValue.getPBaFormAttributeList().size()){
                                for(PBaFormAttribute pBaFormAttribute: pBaFormAttributeValue.getPBaFormAttributeList()){
                                    if(null!=pAttributeValueDataList&&0!=pAttributeValueDataList.size()){
                                        for(PAttributeValueData attributeValueData: pAttributeValueDataList){
                                            if(pBaFormAttribute.getFormAttributeId().equals(attributeValueData.getAttributeId())&& Objects.equals(attributeValueData.getTypeIndex(), pBaFormAttribute.getTypeIndex())){
                                                pBaFormAttribute.setValue(attributeValueData.getValue());
                                                if(5==pBaFormAttribute.getRestrictiveConditions()){
                                                    Long value = new Long(String.valueOf(attributeValueData.getValue()));
                                                    BaUser baUser = baUserService.getBaUserByUserId(value);
                                                    pBaFormAttribute.setDefaultUserName(baUser.getCName());
                                                }
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if(null!=pZsWorkProcess.getPBaFormAttributeValueList3()&&0!=pZsWorkProcess.getPBaFormAttributeValueList3().size()){
                    //将工序详情的value放入对用字段的value
                    for(PBaFormAttributeValue pBaFormAttributeValue: pZsWorkProcess.getPBaFormAttributeValueList3()){
                        if(null!=pBaFormAttributeValue.getObjectParameterJson()){
                            //将工序详情变成value集合
                            List<AttributeValueData> attributeValueDataList = JSON.parseObject(pBaFormAttributeValue.getObjectParameterJson(),type);
                            List<PAttributeValueData> pAttributeValueDataList = new ArrayList<>();
                            if(null!=attributeValueDataList&&0!=attributeValueDataList.size()){
                                for(AttributeValueData attributeValueData: attributeValueDataList){
                                    PAttributeValueData pAttributeValueData = new PAttributeValueData();
                                    pAttributeValueData.setTypeIndex(pBaFormAttributeValue.getTypeIndex());
                                    pAttributeValueData.setAttributeId(attributeValueData.getAttributeId());
                                    pAttributeValueData.setValue(attributeValueData.getValue());
                                    pAttributeValueDataList.add(pAttributeValueData);
                                }
                            }
                            //通过工序详情下的字段id和value集合的attribute匹配，将value集合的value赋给字段的value
                            if(null!=pBaFormAttributeValue.getPBaFormAttributeList()&&0!=pBaFormAttributeValue.getPBaFormAttributeList().size()){
                                for(PBaFormAttribute pBaFormAttribute: pBaFormAttributeValue.getPBaFormAttributeList()){
                                    if(null!=pAttributeValueDataList&&0!=pAttributeValueDataList.size()){
                                        for(PAttributeValueData attributeValueData: pAttributeValueDataList){
                                            if(pBaFormAttribute.getFormAttributeId().equals(attributeValueData.getAttributeId())&& Objects.equals(attributeValueData.getTypeIndex(), pBaFormAttribute.getTypeIndex())){
                                                pBaFormAttribute.setValue(attributeValueData.getValue());
                                                if(5==pBaFormAttribute.getRestrictiveConditions()){
                                                    Long value = new Long(String.valueOf(attributeValueData.getValue()));
                                                    BaUser baUser = baUserService.getBaUserByUserId(value);
                                                    pBaFormAttribute.setDefaultUserName(baUser.getCName());
                                                }
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
            request.setAttribute("pZsWorkProcessList",pZsWorkProcessList);
        }
        return freeMarkerIndexResult("produceTask/updateFormAttributeIFrame.ftl",request);
    }

    /**
     * 结束生产任务
     * @param request
     * @param produceTaskId
     * @return
     */
    @RequestMapping(value = "/endProduceTaskModal.htm")
    public String endProduceTaskModal(HttpServletRequest request, Long produceTaskId){
        String message = "";
        ZsWorkFlow workFlow =zsWorkFlowService.getWorkFlowByProduceTaskId(produceTaskId);
        List<PWorkProcessPDA> workProcessList = zsWorkProcessService.getZsWorkProcessByWorkFlowIdForPDA(workFlow.getWorkFlowId());
        List<PBaFormAttributeValue> operationValue = baFormAttributeValueService.getBaFormAttributeValueByTaskAndType(produceTaskId,1);
        List<PBaFormAttributeValue> auditRecordValue = baFormAttributeValueService.getBaFormAttributeValueByTaskAndType(produceTaskId,2);
        for (PWorkProcessPDA workProcess:workProcessList){
            Boolean boo = false;
            if (null!=operationValue&&0!=operationValue.size()) {
                for (PBaFormAttributeValue operation : operationValue) {
                    if (operation.getWorkProcessId().equals(workProcess.getWorkProcessId())) {
                        boo = true;
                    }
                }
            }
            if (!boo){
                PZsWorkProcess process = zsWorkProcessService.getZsWorkProcessByWorkProcessId(workProcess.getWorkProcessId());
                if ("".equals(message)){
                    message += process.getCName();
                }else {
                    message += "、"+process.getCName();
                }
            }
        }
        if (null!=operationValue&&0!=operationValue.size()) {
            for (PBaFormAttributeValue operation : operationValue) {
                Boolean boo = false;
                for (PBaFormAttributeValue auditRecord : auditRecordValue) {
                    if (operation.getWorkProcessId().equals(auditRecord.getWorkProcessId())
                            && operation.getTypeIndex().equals(auditRecord.getTypeIndex())) {
                        boo = true;
                    }
                }
                if (!boo) {
                    PZsWorkProcess workProcess = zsWorkProcessService.getZsWorkProcessByWorkProcessId(operation.getWorkProcessId());
                    if ("".equals(message)) {
                        message += workProcess.getCName();
                    } else {
                        message += "、" + workProcess.getCName();
                    }
                }
            }
        }
        request.setAttribute("message",message);
        request.setAttribute("produceTaskId",produceTaskId);
        return "produceTask/endProduceTaskModal";
    }

    /**
     * 结束任务
     * @return
     */
    @RequestMapping(value = "endProduceTask.json")
    @ResponseBody
    public Object endProduceTask(HttpServletRequest request, Long produceTaskId){
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            Boolean boo = zsProduceTaskService.updateTaskStatusById(produceTaskId,shiroUser.getId());
            if (boo){
                json.setMsg("成功");
                json.setSuccess(true);
            }else {
                json.setMsg("失败");
            }
        }catch (Exception e){
            json.setMsg("服务器异常");
        }
        return json;
    }
}
