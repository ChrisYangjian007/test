package com.dalian.sea.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.json.Json;
import com.dalian.sea.mapper.*;
import com.dalian.sea.model.*;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.ZsProduceTaskService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.management.Attribute;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/26.
 */
@Service("ZsProduceTaskService")
@Slf4j
public class ZsProduceTaskServiceImpl implements ZsProduceTaskService{

    @Autowired
    private ZsProduceTaskMapper zsProduceTaskMapper;
    @Autowired
    private ZsTaskDetailValueMapper zsTaskDetailValueMapper;
    @Autowired
    private ZsQrCodeMapper zsQrCodeMapper;
    @Autowired
    private ZsQrCodeUseMapper zsQrCodeUseMapper;
    @Autowired
    private BaUserMapper baUserMapper;

    /**
     * 获取进行中生产任务
     * @param pZsProduceTask
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PZsProduceTask> getProcessProduceTask(PZsProduceTask pZsProduceTask, Integer page, Integer rows) {
            PageHelper.startPage(page,rows);
            return zsProduceTaskMapper.getProcessProduceTask(pZsProduceTask);
    }

    /**
     * 获取已结束生产任务
     * @param pZsProduceTask
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PZsProduceTask> getEndProduceTask(PZsProduceTask pZsProduceTask, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        return zsProduceTaskMapper.getEndProduceTask(pZsProduceTask);
    }

    /**
     * 根据id获取生产任务
     * @param id
     * @return
     */
    @Override
    public PZsProduceTask getProduceTaskByDetailId(Long id) {
        return zsProduceTaskMapper.getProduceTaskByDetailId(id);
    }

    /**
     * 根据id获取生产任务
     * @param id
     * @return
     */
    @Override
    public PProduceTaskPDA getProduceTaskByIdForPDA(Long id) {
        return zsProduceTaskMapper.getProduceTaskByIdForPDA(id);
    }

    /**
     * 通过produce_task_id删除生产任务
     * @param zsProduceTask
     * @return
     */
    @Override
    @Transactional
    public Boolean deleteProduceTaskById(ZsProduceTask zsProduceTask) {
        Boolean boo = false;
        Boolean boo1 = false;
        Boolean boo2 = false;
        Boolean boo3 = false;
        Boolean boo4 = false;
        try {
            Integer result1 = zsProduceTaskMapper.deleteProduceTaskById(zsProduceTask);
            if(0<result1){
                boo1 = true ;
            }
            List<ZsTaskDetailValue> taskDetailValueList = zsTaskDetailValueMapper.getTaskDetailValueByProduceTask(zsProduceTask.getProduceTaskId());
            if(null == taskDetailValueList && 0 ==taskDetailValueList.size()){
                boo2 = true ;
            }else {
                Integer result2 = zsTaskDetailValueMapper.deleteTaskDetailValueByProduceTaskId(zsProduceTask.getProduceTaskId());
                if(0<result2){
                    boo2 = true ;
                }
            }
            List<ZsQrCode> zsQrCodeList = zsQrCodeMapper.getQrCodeByProduceTaskId(zsProduceTask.getProduceTaskId());
            if(null!=zsQrCodeList&&0!=zsQrCodeList.size()){
                Integer result3 =  zsQrCodeMapper.unBindCode(zsProduceTask.getProduceTaskId(), zsProduceTask.getUpdateUserId());
                if(0<result3){
                    boo3 = true;
                }
            }else {
                boo3 = true;
            }
            List<PZsQrCodeUse> zsQrCodeUseList = zsQrCodeUseMapper.getQrCodeUseByProDuceTaskId(zsProduceTask.getProduceTaskId());
            if(null!=zsQrCodeUseList&&0!=zsQrCodeUseList.size()){
                BaUser user = baUserMapper.getBaUserByUserId(zsProduceTask.getUpdateUserId());
                Integer result4 =  zsQrCodeUseMapper.unBindCode(zsProduceTask.getProduceTaskId(), zsProduceTask.getUpdateUserId(), user.getCName());
                if(0<result4){
                    boo4 = true;
                }
            }else {
                boo4 = true;
            }
            if(boo1 && boo2 && boo3 && boo4){
                boo = true ;
            }else {
                throw new  Exception("rollBack");
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

    @Override
    public ZsProduceTask getProduceTaskByProduceTaskNo(String produceTaskNo) {
        ZsProduceTask produceTask =zsProduceTaskMapper.getProduceTaskByProduceTaskNo(produceTaskNo);
        return produceTask;
    }

    /**
     * 通过操作类型获取生产任务详情
     * colType(1-产看 2-操作 3-审核 4-巡检)
     * @param cloType
     * @return
     */
    @Override
    public List<PZsProduceTask> getPZsProduceTaskByColTypeForPDA(Integer cloType,Long userId,int page,int rows) {
        PageHelper.startPage(page,rows);
        List<PZsProduceTask> produceTaskList = new ArrayList<>();
        if(4==cloType){
            produceTaskList =  zsProduceTaskMapper.getPZsProduceTaskByCollType();
        }else {
            if(3==cloType){
                Type type = new TypeReference<List<AttributeValueData>>() {}.getType();
                List<PZsProduceTask> taskList = zsProduceTaskMapper.getPZsProduceTaskByColTypeThree();
                if(null!=taskList&&0!=taskList.size()){
                    for(PZsProduceTask pZsProduceTask:taskList){
                        if(null!=pZsProduceTask.getFormAttributeValueList()&&0!=pZsProduceTask.getFormAttributeValueList().size()){
                            BaFormAttributeValue formAttributeValue = new BaFormAttributeValue();
                            //遍历formAttributeValue获取最大typeIndex的value
                            for(BaFormAttributeValue baFormAttributeValue:pZsProduceTask.getFormAttributeValueList()){
                                if(baFormAttributeValue.getHandleType()==1){
                                    if (null!=formAttributeValue.getTypeIndex()) {
                                        if (baFormAttributeValue.getTypeIndex() > formAttributeValue.getTypeIndex()) {
                                            formAttributeValue = baFormAttributeValue;
                                        }
                                    }else {
                                        formAttributeValue = baFormAttributeValue;
                                    }
                                }
                            }
                            //找formAttributeValue的value与userId、attributeId与formAttribute的id匹配的值
                            List<AttributeValueData> valueDataList = JSON.parseObject(formAttributeValue.getObjectParameterJson(),type);
                            if(null!=valueDataList&&0!=valueDataList.size()){
                                for(AttributeValueData attributeValueData:valueDataList){
                                    if(attributeValueData.getAttributeId().equals(pZsProduceTask.getFormAttribute().getFormAttributeId())&&attributeValueData.getValue().equals(userId+"")){
                                        produceTaskList.add(pZsProduceTask);
                                    }
                                }
                            }
                        }
                    }
                }
            }else {
                produceTaskList=zsProduceTaskMapper.getPZsProduceTaskByColType(cloType);
            }
        }
        return produceTaskList;
    }

    /**
     * 通过操作类型获取生产任务详情
     * colType(1-产看 2-操作 3-审核 4-巡检)
     * @param cloType
     * @return
     */
    @Override
    public Json getPZsProduceTaskByColTypeForPDAByCode(String code,Integer cloType,Long userId) {
        Json json = new Json();
        PZsProduceTask produceTask;
        if(3==cloType){
            Type type = new TypeReference<List<AttributeValueData>>() {}.getType();
            produceTask = zsProduceTaskMapper.getPZsProduceTaskByColTypeThreeForPDAByCode(code);
            if(null!=produceTask){
                if(null!=produceTask.getFormAttributeValueList()&&0!=produceTask.getFormAttributeValueList().size()){
                    BaFormAttributeValue formAttributeValue = new BaFormAttributeValue();
                    //遍历formAttributeValue获取最大typeIndex的value
                    for(BaFormAttributeValue baFormAttributeValue:produceTask.getFormAttributeValueList()){
                        if(baFormAttributeValue.getHandleType()==1){
                            if(baFormAttributeValue.getTypeIndex()>formAttributeValue.getTypeIndex()){
                                formAttributeValue=baFormAttributeValue;
                            }
                        }
                    }
                    //找formAttributeValue的value与userId、attributeId与formAttribute的id匹配的值
                    List<AttributeValueData> valueDataList = JSON.parseObject(formAttributeValue.getObjectParameterJson(),type);
                    if(null!=valueDataList&&0!=valueDataList.size()){
                        for(AttributeValueData attributeValueData:valueDataList){
                            if(attributeValueData.getAttributeId().equals(produceTask.getFormAttribute().getFormAttributeId())&&attributeValueData.getValue().equals(userId)){
                                json.setObj(produceTask);
                                json.setSuccess(true);
                            }else {
                                json.setMsg("无审核该生产任务权利！");
                            }
                        }
                    }
                }
            }else {
                json.setMsg("查询暂无");
            }
        }else {
            produceTask = zsProduceTaskMapper.getPZsProduceTaskByColTypeForPDAByCode(cloType,code);
            if (null!=produceTask){
                json.setObj(produceTask);
                json.setSuccess(true);
            }else {
                json.setMsg("查询暂无");
            }
        }
        return json;
    }

    /**
     * 通过id修改produceTaskStatus为已结束
     * @param produceTaskId
     * @param updateUserId
     * @return
     */
    @Override
    @Transactional
    public Boolean updateTaskStatusById(Long produceTaskId, Long updateUserId) {
        Boolean boo = false;
        try {
            Integer result = zsProduceTaskMapper.updateTaskStatusById(produceTaskId, updateUserId);
            if (0 < result) {
                BaUser user = baUserMapper.getBaUserByUserId(updateUserId);
                zsQrCodeMapper.unBindCode(produceTaskId, updateUserId);
                zsQrCodeUseMapper.unBindCode(produceTaskId, updateUserId, user.getCName());
                boo = true;
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

    /**
     *获取今日生产任务
     * @return
     */
    @Override
    public List<PZsProduceTask> getTodayProduceTask() {
        List<PZsProduceTask> taskList = new ArrayList<>();
        List<PZsProduceTask> produceTaskList = zsProduceTaskMapper.getTodayProduceTask();
        if(null!=produceTaskList&&0!=produceTaskList.size()){
            for(PZsProduceTask pZsProduceTask:produceTaskList){
                List<PZsWorkProcess> pZsWorkProcessList = pZsProduceTask.getPZsWorkProcessList();
                if(null!=pZsWorkProcessList&&0!=pZsWorkProcessList.size()){
                    //生产任务的有字段所有工序
                    Integer l = pZsWorkProcessList.size();
                    Double f =0.00;
                    //将生产任务下的工序和工序详情匹配
                    for(PZsWorkProcess pZsWorkProcess:pZsWorkProcessList){
                        if(null!=pZsProduceTask.getFormAttributeValueList()&&0!=pZsProduceTask.getFormAttributeValueList().size()){
                            for(BaFormAttributeValue baFormAttributeValue:pZsProduceTask.getFormAttributeValueList()){
                                if(pZsWorkProcess.getWorkProcessId().equals(baFormAttributeValue.getWorkProcessId())){
                                    pZsWorkProcess.getFormAttributeValueList().add(baFormAttributeValue);
                                }
                            }
                        }
                    }
                    //当该工序的详情存在是，表明该工序已经完成 f+1
                    for(PZsWorkProcess pZsWorkProcess:pZsWorkProcessList){
                        if(null!=pZsWorkProcess.getFormAttributeValueList()&&0!=pZsWorkProcess.getFormAttributeValueList().size()){
                            f++;
                        }
                    }
                    //计算已经完成的工序占总工序的百分比
                    Double percent = f/(l*1.0);
                    pZsProduceTask.setPercent(new BigDecimal(percent).setScale(4,BigDecimal.ROUND_HALF_UP));
                }
            }
        }
        //限制5条以内
        if(null!=produceTaskList&&0!=produceTaskList.size()){
            if(5>produceTaskList.size()){
                for(int i=0;i<produceTaskList.size();i++){
                    taskList.add(produceTaskList.get(i));
                }
            }else {
                for(int i=0;i<5;i++){
                    taskList.add(produceTaskList.get(i));
                }
            }
        }
        return taskList;
    }

    @Override
    public List<PZsProduceTask> selectProduceTaskList(PZsProduceTask pZsProduceTask) {
        return zsProduceTaskMapper.getProcessProduceTask(pZsProduceTask);
    }

    @Override
    public Byte getProduceTaskStatusById(Long produceTaskId) {
        return zsProduceTaskMapper.getProduceTaskStatusById(produceTaskId);
    }


    @Override
    public ZsProduceTask getProduceTaskByNo(String no) {
        return zsProduceTaskMapper.getProduceTaskByNo(no);
    }}
