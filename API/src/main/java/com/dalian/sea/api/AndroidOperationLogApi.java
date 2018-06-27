package com.dalian.sea.api;

import cn.jpush.api.push.PushResult;
import com.alibaba.fastjson.JSON;
import com.dalian.sea.JPushUnit.JPushUtil;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.BaUser;
import com.dalian.sea.model.ZsWorkFlow;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 杨文波
 * @date 2018/3/9
 */
@Slf4j
@RestController
@RequestMapping("/operationLog")
public class AndroidOperationLogApi {

    @Autowired
    private ZsWorkFlowService zsWorkFlowService;

    @Autowired
    private ZsWorkProcessService zsWorkProcessService;

    @Autowired
    private BaFormAttributeService baFormAttributeService;

    @Autowired
    private BaFormAttributeValueService baFormAttributeValueService;

    @Autowired
    private BaDataDictionaryService baDataDictionaryService;

    @Autowired
    private BaDataDictionaryDetailsService detailsService;

    @Autowired
    private ZsTaskDetailValueService zsTaskDetailValueService;

    @Autowired
    private BaUserService baUserService;

    @Autowired
    private ZsProduceTaskService zsProduceTaskService;

    @Autowired
    private SaLeaveStockDetailService saLeaveStockDetailService;

    @PostMapping("/getWorkProcessByWorkFlowId.json")
    public Object getWorkProcessByWorkFlowId(Long produceTaskId,Long companyId){
        Json json = new Json();
        try {
            if (null!=produceTaskId) {
                PProduceTaskPDA produceTask = zsProduceTaskService.getProduceTaskByIdForPDA(produceTaskId);
                if (null!=produceTask) {
                    ZsWorkFlow workFlow = zsWorkFlowService.getWorkFlowByProduceTaskId(produceTaskId);
                    if (null != workFlow) {
                        if (null == produceTask.getCheckStatus() || 0 == produceTask.getCheckStatus() || 2 == produceTask.getCheckStatus()) {
                            produceTask.setWorkFlow(workFlow);
                            List<SaLeaveStockDetailPara> paraList = saLeaveStockDetailService.getLeaveStockDetailByProduceId(produceTaskId);
                            produceTask.setSaLeaveStockDetailParaList(paraList);
                            List<PWorkProcessPDA> paras = zsWorkProcessService.getZsWorkProcessByWorkFlowIdForPDA(workFlow.getWorkFlowId());
                            List<PWorkProcessPDA> logParas = new ArrayList<>();
                            List<PBaFormAttribute> attributes;
                            if (null != paras && paras.size() > 0) {
                                for (PWorkProcessPDA para : paras) {
                                    if (para.getIsWorkshopRecord() == 1) {
                                        List<BaUser> userList = baUserService.getBaUserByUserType(1,companyId);
                                        List<PBaFormAttribute> pBaFormAttributes = baFormAttributeService.getBaFormAttributeListByWorkProcessIdAndHandleType(para.getWorkProcessId(), 1);
                                        if (null != pBaFormAttributes && pBaFormAttributes.size() > 0) {
                                            attributes = new ArrayList<>();
                                            for (PBaFormAttribute attribute : pBaFormAttributes) {
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
                                                attributes.add(attribute);
                                            }
                                            para.setOperation(attributes);
                                            para.setAuditor(new ArrayList<>());
                                            para.setInspection(new ArrayList<>());
                                        }
                                    }
                                    logParas.add(para);
                                }
                                produceTask.setWorkProcessPDAList(logParas);
                                json.setMsg("查询成功");
                                json.setObj(produceTask);
                            } else {
                                json.setMsg("工序查询暂无");
                            }
                        } else {
                            json.setMsg("该生产任务当前工序操作记录已提交！");
                        }
                    } else {
                        json.setMsg("该生产任务的工艺数据有误！");
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

    @PostMapping("/insertAttributeValue.json")
    public Object insertAttributeValue(PBaFormAttributeValue attributeValue,Long userId){
        Json json = new Json();
        try {
            if (null!=userId) {
                if (null != attributeValue.getCreateUserId()) {
                    PBaUser user = baUserService.getBaUserByUserId(attributeValue.getCreateUserId());
                    PBaUser baUser = baUserService.getBaUserByUserId(userId);
                    if (null != user) {
                        if (null != attributeValue.getProduceTaskId()) {
                            PZsProduceTask produceTask = zsProduceTaskService.getProduceTaskByDetailId(attributeValue.getProduceTaskId());
                            if (null != produceTask) {
                                if (1 == produceTask.getProduceTaskStatus()) {
                                    if (1 != produceTask.getCheckStatus()) {
                                        if (null != attributeValue.getWorkProcessId()) {
                                            PZsWorkProcess workProcess = zsWorkProcessService.getZsWorkProcessByWorkProcessId(attributeValue.getWorkProcessId());
                                            if (null != workProcess) {
                                                PZsWorkFlow workFlow = zsWorkFlowService.getWorkFlowById(workProcess.getWorkFlowId());
                                                attributeValue.setHandleType((byte) 1);
                                                Long id = baFormAttributeValueService.addBaFormAttributeValue(attributeValue, workProcess.getCName());
                                                if (null != id) {
                                                    json.setObj(id);
                                                    json.setMsg("添加成功");
                                                    PushResult result = JPushUtil.pushMyMessageToAlias("all", baUser.getAccount(), "{\"title\":\"生产任务提交提醒\",\"content\":\"任务 " + workFlow.getCName() + user.getCName() + "," + "提交 " + workProcess.getCName() + " 工序\",\"value\":\"" + attributeValue.getProduceTaskId()+"\"}");
                                                } else {
                                                    json.setMsg("添加失败");
                                                }
                                            } else {
                                                json.setMsg("当前编号的工序数据有误，请检查后再添加！");
                                            }
                                        } else {
                                            json.setMsg("工序编号无，无法添加信息");
                                        }
                                    } else {
                                        json.setMsg("当前编号的生产任务的工序还未结束！");
                                    }
                                } else {
                                    json.setMsg("当前编号的生产任务已结束");
                                }
                            } else {
                                json.setMsg("当前编号的生产任务数据有误，请检查后再添加！");
                            }
                        } else {
                            json.setMsg("生产任务编号无，无法添加信息");
                        }
                    } else {
                        json.setMsg("当前编号的用户数据有误，请检查后再添加！");
                    }
                } else {
                    json.setMsg("用户编号无，无法添加信息");
                }
            }else {
                json.setMsg("审核人信息有误，无法添加信息！");
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }
}
