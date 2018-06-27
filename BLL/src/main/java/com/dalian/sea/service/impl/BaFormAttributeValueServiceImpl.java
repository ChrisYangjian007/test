package com.dalian.sea.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.json.AttributeLogJson;
import com.dalian.sea.mapper.BaFormAttributeValueMapper;
import com.dalian.sea.mapper.BaUserMapper;
import com.dalian.sea.mapper.ZsProduceTaskMapper;
import com.dalian.sea.model.BaFormAttributeValue;
import com.dalian.sea.model.ZsProduceTask;
import com.dalian.sea.parameter.AttributeValueData;
import com.dalian.sea.parameter.PBaFormAttributeValue;
import com.dalian.sea.parameter.PBaUser;
import com.dalian.sea.service.BaFormAttributeValueService;
import com.dalian.sea.service.BaUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * BaFormAttributeValueServiceImpl
 *
 * @author TONE
 * @date 2018/2/8
 */
@Service("BaFormAttributeValueService")
public class BaFormAttributeValueServiceImpl implements BaFormAttributeValueService {

    @Autowired
    private BaFormAttributeValueMapper baFormAttributeValueMapper;

    @Autowired
    private ZsProduceTaskMapper zsProduceTaskMapper;

    /**
     * 通过工序id获取
     * @param workProcessId
     * @return
     */
    @Override
    public List<PBaFormAttributeValue> getBaFormAttributeValueByWorkProcessId(Long workProcessId) {
        return baFormAttributeValueMapper.getBaFormAttributeValueByWorkProcessId(workProcessId);
    }

    /**
     *通过生产任务id获取所有审核数据
     * @param produceTaskId
     * @param handleType
     * @return
     */
    @Override
    public List<PBaFormAttributeValue> getBaFormAttributeValueByWorkProcessIdForPDA(Long produceTaskId,int handleType) {
        return baFormAttributeValueMapper.getBaFormAttributeValueByWorkProcessIdForPDA(produceTaskId,handleType);
    }

    /**
     * 添加
     * @param baFormAttributeValue
     * @param name
     * @return
     */
    @Override
    @Transactional
    public Long addBaFormAttributeValue(PBaFormAttributeValue baFormAttributeValue,String name) {
        Long id =null;
        try {
            List<AttributeLogJson> logJsonList = new ArrayList<>();
            AttributeLogJson logJson = new AttributeLogJson();
            logJson.setUserId(baFormAttributeValue.getCreateUserId());
            logJson.setCreateDate(new Date());
            if (1==baFormAttributeValue.getHandleType()) {
                PBaFormAttributeValue attributeValue = baFormAttributeValueMapper.getBaFormAttributeValueByTaskAndProcessAndType(baFormAttributeValue.getProduceTaskId(), baFormAttributeValue.getWorkProcessId(), baFormAttributeValue.getHandleType());
                if (null != attributeValue) {
                    baFormAttributeValue.setTypeIndex(attributeValue.getTypeIndex() + 1);
                } else {
                    baFormAttributeValue.setTypeIndex(1);
                }
                logJson.setContext("新增-"+name+"-操作记录信息！");
            }else if (3==baFormAttributeValue.getHandleType()){
                logJson.setContext("新增-"+name+"-巡检记录信息！");
            }
            logJsonList.add(logJson);
            String jsonText = JSON.toJSONString(logJsonList,true);
            baFormAttributeValue.setLoggerJson(jsonText);
            Integer integer = baFormAttributeValueMapper.addBaFormAttributeValue(baFormAttributeValue);
            if (0!=integer){
                if (1==baFormAttributeValue.getHandleType()) {
                    ZsProduceTask produceTask = new ZsProduceTask();
                    produceTask.setProduceTaskId(baFormAttributeValue.getProduceTaskId());
                    produceTask.setWorkProcessId(baFormAttributeValue.getWorkProcessId());
                    produceTask.setCheckUserId(baFormAttributeValue.getCreateUserId());
                    produceTask.setCheckStatus((byte) 1);
                    integer = zsProduceTaskMapper.updateProduceTask(produceTask);
                }
                if (0 != integer) {
                    id = baFormAttributeValue.getFormAttributeValueId();
                }
            }else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return id;
    }

    /**
     * 添加或修改
     * @param auditor
     * @return
     */
    @Override
    @Transactional
    public Boolean addOrUpdateBaFormAttributeValue(PBaFormAttributeValue operation,PBaFormAttributeValue auditor,String name) {
        Boolean boo = false;
        try{
            if (null!=operation){
                Type type = new TypeReference<List<AttributeLogJson>>() {}.getType();
                List<AttributeLogJson> attributeLogJsonList = JSON.parseObject(operation.getLoggerJson(), type);
                AttributeLogJson logJson = new AttributeLogJson();
                logJson.setUserId(auditor.getCreateUserId());
                logJson.setContext("修改-"+name+"-操作记录信息！");
                logJson.setCreateDate(new Date());
                attributeLogJsonList.add(logJson);
                String jsonText = JSON.toJSONString(attributeLogJsonList,true);
                operation.setLoggerJson(jsonText);
                operation.setUpdateUserId(auditor.getCreateUserId());
                Integer integer = baFormAttributeValueMapper.updateBaFormAttributeValue(operation);
                if (0!=integer){
                    List<AttributeLogJson> logJsonList = new ArrayList<>();
                    logJson = new AttributeLogJson();
                    logJson.setUserId(auditor.getCreateUserId());
                    logJson.setContext("新增-"+name+"-审核记录信息！");
                    logJson.setCreateDate(new Date());
                    logJsonList.add(logJson);
                    jsonText = JSON.toJSONString(logJsonList,true);
                    auditor.setLoggerJson(jsonText);
                    integer = baFormAttributeValueMapper.addBaFormAttributeValue(auditor);
                    if (0!=integer){
                        ZsProduceTask produceTask = new ZsProduceTask();
                        produceTask.setProduceTaskId(auditor.getProduceTaskId());
                        produceTask.setWorkProcessId(auditor.getWorkProcessId());
                        produceTask.setCheckUserId(auditor.getCreateUserId());
                        produceTask.setCheckStatus((byte) 2);
                        integer = zsProduceTaskMapper.updateProduceTask(produceTask);
                        if (0!=integer){
                            boo = true;
                        }else {
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        }
                    }else {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    }
                }else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
            }else {
                auditor.setObjectParameterJson(auditor.getObjectParameterJsonTwo());
                List<AttributeLogJson> logJsonList = new ArrayList<>();
                AttributeLogJson logJson = new AttributeLogJson();
                logJson.setUserId(auditor.getCreateUserId());
                logJson.setContext("新增-"+name+"-审核记录信息！");
                logJson.setCreateDate(new Date());
                logJsonList.add(logJson);
                String jsonText = JSON.toJSONString(logJsonList,true);
                auditor.setLoggerJson(jsonText);
                Integer integer = baFormAttributeValueMapper.addBaFormAttributeValue(auditor);
                if (0!=integer){
                    ZsProduceTask produceTask = new ZsProduceTask();
                    produceTask.setProduceTaskId(auditor.getProduceTaskId());
                    produceTask.setWorkProcessId(auditor.getWorkProcessId());
                    produceTask.setCheckUserId(auditor.getCreateUserId());
                    produceTask.setCheckStatus((byte) 2);
                    integer = zsProduceTaskMapper.updateProduceTask(produceTask);
                    if (0!=integer){
                        boo = true;
                    }else {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    }
                }else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

    /**
     * 通过produceTaskId获取value
     * @param produceTaskId
     * @return
     */
    @Override
    public List<PBaFormAttributeValue> getFormAttributeValueByProduceTaskId(Long produceTaskId) {
        return baFormAttributeValueMapper.getFormAttributeValueByProduceTaskId(produceTaskId);
    }

    @Override
    public List<PBaFormAttributeValue> getFormAttributeValueByProduceTaskIdForPDA(Long produceTaskId, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        return baFormAttributeValueMapper.getFormAttributeValueByProduceTaskId(produceTaskId);
    }

    /**
     * 通过produceTaskId、Type获取value
     * @param produceTaskId
     * @return
     */
    @Override
    public List<PBaFormAttributeValue> getBaFormAttributeValueByTaskAndType(Long produceTaskId, int handleType) {
        return baFormAttributeValueMapper.getBaFormAttributeValueByTaskAndType(produceTaskId,handleType);
    }

    /**
     * 获取根据生产任务、工序和类型
     * @param produceTaskId
     * @param workProcessId
     * @param handleType
     * @return
     */
    @Override
    public PBaFormAttributeValue getBaFormAttributeValueByTaskAndProcessAndType(Long produceTaskId, Long workProcessId, int handleType) {
        return baFormAttributeValueMapper.getBaFormAttributeValueByTaskAndProcessAndType(produceTaskId,workProcessId,handleType);
    }

    /**
     * 获取根据生产任务、工序和类型
     * @param attributeValue
     * @return
     */
    @Override
    public PBaFormAttributeValue getBaFormAttributeValueByTaskAndProcessAndTypeAndIndex(PBaFormAttributeValue attributeValue) {
        return baFormAttributeValueMapper.getBaFormAttributeValueByTaskAndProcessAndTypeAndIndex(attributeValue);
    }

    /**
     * 批量修改value
     * @param formAttributeValueList
     * @return
     */
    @Override
    public Boolean updateFormAttributeValue(List<PBaFormAttributeValue> formAttributeValueList) {
        Boolean boo = false;
        Integer result = baFormAttributeValueMapper.updateFormAttributeValue(formAttributeValueList);
        if(0<result){
            boo = true ;
        }
        return boo;
    }

    /**
     * 编辑工序详情
     * @param addFormAttributeValueList
     * @param updFormAttributeValueList
     * @return
     */
    @Override
    @Transactional
    public Boolean addAndUpdateBaFormAttributeValue(List<PBaFormAttributeValue> addFormAttributeValueList, List<PBaFormAttributeValue> updFormAttributeValueList) {
        Boolean boo = false;
        try {
            Integer addRes = 0 ;
            Integer updRes = 0;
            if(null!=addFormAttributeValueList&&0!=addFormAttributeValueList.size()){
                 addRes =baFormAttributeValueMapper.addBaFormAttributeValueList(addFormAttributeValueList);
            }else {
                addRes = 1;
            }
            if(null!=updFormAttributeValueList&&0!=updFormAttributeValueList.size()){
                updRes = baFormAttributeValueMapper.updateFormAttributeValue(updFormAttributeValueList);
            }else {
                updRes = 1;
            }
            if(0<addRes&&0<updRes){
                boo = true;
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }
}
