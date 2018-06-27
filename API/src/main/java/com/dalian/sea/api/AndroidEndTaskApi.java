package com.dalian.sea.api;

import com.dalian.sea.json.Json;
import com.dalian.sea.model.ZsWorkFlow;
import com.dalian.sea.model.ZsWorkProcess;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.BaFormAttributeValueService;
import com.dalian.sea.service.ZsProduceTaskService;
import com.dalian.sea.service.ZsWorkFlowService;
import com.dalian.sea.service.ZsWorkProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * AndroidEndTaskApi
 *
 * @author TONE
 * @date 2018/3/15.
 */
@Slf4j
@RestController
@RequestMapping("/endTask")
public class AndroidEndTaskApi {
    @Autowired
    private ZsProduceTaskService zsProduceTaskService;
    @Autowired
    private ZsWorkFlowService zsWorkFlowService;
    @Autowired
    private ZsWorkProcessService zsWorkProcessService;
    @Autowired
    private BaFormAttributeValueService baFormAttributeValueService;

    @PostMapping("/isEndTask.json")
    public Object isEndTask(Long produceTaskId){
        Json json = new Json();
        try {
            String error="";
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
                    if ("".equals(error)){
                        error += process.getCName();
                    }else {
                        error += ","+process.getCName();
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
                        if ("".equals(error)) {
                            error += workProcess.getCName();
                        } else {
                            error += "," + workProcess.getCName();
                        }
                    }
                }
            }
            if (!"".equals(error)){
                json.setObj(error);
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常！");
        }
        return json;
    }

    @PostMapping("/endTask.json")
    public Object endTask(Long produceTaskId,Long userId){
        Json json = new Json();
        try {
            Boolean boo = zsProduceTaskService.updateTaskStatusById(produceTaskId,userId);
            if (boo){
                json.setMsg("成功");
            }else {
                json.setMsg("失败");
            }
            json.setSuccess(true);
        }catch (Exception e){
            json.setMsg("服务器异常!");
        }
        return json;
    }




}
