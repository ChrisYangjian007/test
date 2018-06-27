package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BaFormAttributeMapper;
import com.dalian.sea.mapper.ZsWorkProcessMapper;
import com.dalian.sea.model.ZsWorkProcess;
import com.dalian.sea.parameter.PBaFormAttribute;
import com.dalian.sea.parameter.PProduceTaskPDA;
import com.dalian.sea.parameter.PWorkProcessPDA;
import com.dalian.sea.parameter.PZsWorkProcess;
import com.dalian.sea.service.ZsWorkProcessService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TONE on 2018/2/8.
 */
@Service
public class ZsWorkProcessServiceImpl implements ZsWorkProcessService {
    @Autowired
    private ZsWorkProcessMapper zsWorkProcessMapper;
    @Autowired
    private BaFormAttributeMapper baFormAttributeMapper;


    /**
     * 根据ID获取
     * @param workProcessId
     * @return
     */
    @Override
    public PWorkProcessPDA getWorkProcessById(Long workProcessId) {
        return zsWorkProcessMapper.getWorkProcessById(workProcessId);
    }

    /**
     * 根据workFlowID和名称获取
     * @param workProcess
     * @return
     */
    @Override
    public PZsWorkProcess getWorkProcessByWorkFlowIdAndName(PZsWorkProcess workProcess) {
        return zsWorkProcessMapper.getWorkProcessByWorkFlowIdAndName(workProcess);
    }

    /**
     * 根据ID获取
     * @param workProcessId
     * @return
     */
    @Override
    public PZsWorkProcess getZsWorkProcessByWorkProcessId(Long workProcessId) {
        return zsWorkProcessMapper.getZsWorkProcessByWorkProcessId(workProcessId);
    }

    /**
     * 根据工艺ID获取
     * @param workFlowId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PZsWorkProcess> getZsWorkProcessByWorkFlowId(Long workFlowId, int page, int rows) {
        PageHelper.startPage(page,rows);
        return zsWorkProcessMapper.getZsWorkProcessByWorkFlowId(workFlowId);
    }

    /**
     * 根据工艺ID获取
     * @param workFlowId
     * @return
     */
    @Override
    public List<PZsWorkProcess> getZsWorkProcessByWorkFlowId(Long workFlowId) {
        return zsWorkProcessMapper.getZsWorkProcessByWorkFlowId(workFlowId);
    }

    /**
     * 根据工艺ID获取
     * @param workFlowId
     * @return
     */
    @Override
    public List<PWorkProcessPDA> getZsWorkProcessByWorkFlowIdForPDA(Long workFlowId) {
        return zsWorkProcessMapper.getZsWorkProcessByWorkFlowIdForPDA(workFlowId);
    }

    /**
     * 根据生产任务获取工序
     * @param id
     * @return
     */
    @Override
    public List<PZsWorkProcess> getWorkProcessByProduceTask(Long id) {
        return zsWorkProcessMapper.getWorkProcessByProduceTask(id);
    }

    /**
     * 获取生产任务的工序及工序详情
     * @param id
     * @return
     */
    @Override
    public List<PZsWorkProcess> getWorkProcessFromAttributeValueAndByProduceTask(Long id) {
        return zsWorkProcessMapper.getWorkProcessFromAttributeValueAndByProduceTask(id);
    }

    /**
     * 添加工艺详情
     * @param workProcess
     * @return
     */
    @Override
    @Transactional
    public Long addWorkProcess(PZsWorkProcess workProcess) {
        Long id = null;
        try{
            Integer integer = zsWorkProcessMapper.addWorkProcess(workProcess);
            if (0!=integer){
                id =workProcess.getWorkProcessId();
                if (1==workProcess.getIsWorkshopRecord()) {
                    if (null!=workProcess.getTableField1()&&0!=workProcess.getTableField1().size()) {
                        integer = baFormAttributeMapper.addBaFormAttributeList(workProcess.getTableField1(), id, workProcess.getCreateUserId());
                    }
                    if (0 != integer) {
                        if (null != workProcess.getTableField2() && 0 != workProcess.getTableField2().size()) {
                            integer = baFormAttributeMapper.addBaFormAttributeList(workProcess.getTableField2(), id, workProcess.getCreateUserId());
                        }
                        if (0 != integer) {
                            if (null != workProcess.getTableField3() && 0 != workProcess.getTableField3().size()) {
                                integer = baFormAttributeMapper.addBaFormAttributeList(workProcess.getTableField3(), id, workProcess.getCreateUserId());
                                if (0 == integer) {
                                    id = null;
                                }
                            }
                        }else {
                            id = null;
                        }
                    }else {
                        id = null;
                    }
                }
            }
        }catch (Exception e){
            id = null;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return id;
    }

    /**
     * 修改工艺详情
     * @param workProcess
     * @return
     */
    @Override
    @Transactional
    public Boolean updateWorkProcess(PZsWorkProcess workProcess) {
        Boolean boo = false;
        try {
            Integer integer = zsWorkProcessMapper.updateWorkProcess(workProcess);
            if (0!=integer) {
                if (1==workProcess.getIsWorkshopRecord()) {
                    List<PBaFormAttribute> addPBaFormAttribute = new ArrayList<>();
                    List<PBaFormAttribute> updatePBaFormAttribute = new ArrayList<>();
                    for (int i = 0; i < workProcess.getTableField1().size(); i++) {
                        if (null != workProcess.getTableField1().get(i).getFormAttributeId()) {
                            updatePBaFormAttribute.add(workProcess.getTableField1().get(i));
                        } else {
                            addPBaFormAttribute.add(workProcess.getTableField1().get(i));
                        }
                    }
                    for (int i = 0; i < workProcess.getTableField2().size(); i++) {
                        if (null != workProcess.getTableField2().get(i).getFormAttributeId()) {
                            updatePBaFormAttribute.add(workProcess.getTableField2().get(i));
                        } else {
                            addPBaFormAttribute.add(workProcess.getTableField2().get(i));
                        }
                    }
                    for (int i = 0; i < workProcess.getTableField3().size(); i++) {
                        if (null != workProcess.getTableField3().get(i).getFormAttributeId()) {
                            updatePBaFormAttribute.add(workProcess.getTableField3().get(i));
                        } else {
                            addPBaFormAttribute.add(workProcess.getTableField3().get(i));
                        }
                    }
                    if (0 != updatePBaFormAttribute.size()) {
                        baFormAttributeMapper.deleteBaFormAttributeList(updatePBaFormAttribute, workProcess.getWorkProcessId(), workProcess.getUpdateUserId());
                        integer = baFormAttributeMapper.updateBaFormAttributeList(updatePBaFormAttribute, workProcess.getUpdateUserId());
                    }
                    if (0 != integer) {
                        if (0 != addPBaFormAttribute.size()) {
                            integer = baFormAttributeMapper.addBaFormAttributeList(addPBaFormAttribute, workProcess.getWorkProcessId(), workProcess.getUpdateUserId());
                            if (0 != integer) {
                                boo = true;
                            }
                        }else {
                            boo = true;
                        }
                    }
                }else {
                    boo = true;
                }
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

    @Override
    public List<PZsWorkProcess> getZsWorkProcessAll() {
        return zsWorkProcessMapper.getZsWorkProcessAll();
    }

    /**
     * 通过id删除工序
     * @param zsWorkProcess
     * @return
     */
    @Override
    @Transactional
    public Boolean deleteWorkProcessById(ZsWorkProcess zsWorkProcess) {
        Boolean boo = false;
        try {
            Integer result = zsWorkProcessMapper.deleteWorkProcessById(zsWorkProcess);
            if(0!=result){
                if (1==zsWorkProcess.getIsWorkshopRecord()) {
                    result = baFormAttributeMapper.deleteFormAttributeByWorkProcessId(zsWorkProcess);
                }
                if(0!=result) {
                    boo = true;
                }
            }else {
                throw new Exception("rollBack");
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

    /**
     * 通过workFlowId获取工序
     * @param workFlowId
     * @return
     */
    @Override
    public List<ZsWorkProcess> getWorkProcessByWorkFlowId(Long workFlowId) {
        return zsWorkProcessMapper.getWorkProcessByWorkFlowId(workFlowId);
    }

    @Override
    public List<ZsWorkProcess> getWorkProcessByWorkFlowIdForStock(Long workFlowId) {
        return zsWorkProcessMapper.getWorkProcessByWorkFlowIdForStock(workFlowId);
    }

    /**
     * 通过workFlowId获取工艺和value
     * @param workFlowId
     * @return
     */
    @Override
    public List<PZsWorkProcess> getZsWorkProcessAndValueByWorkFlowId(Long workFlowId) {
        return zsWorkProcessMapper.getZsWorkProcessAndValueByWorkFlowId(workFlowId);
    }

    /**
     * 通过produceTaskId获取工序和工序字段
     * @param produceTaskId
     * @return
     */
    @Override
    public List<PZsWorkProcess> getWorkProcessAndAllPFormAttributeByProduceTaskId(Long produceTaskId) {
        return zsWorkProcessMapper.getWorkProcessAndAllPFormAttributeByProduceTaskId(produceTaskId);
    }

    /**
     * 通过produceTaskId获取工序和工序字段对应值
     * @param produceTaskId
     * @return
     */
    @Override
    public List<PZsWorkProcess> getPWorkProcessAndPFromAttributeValueAndByProduceTask(Long produceTaskId) {
        return zsWorkProcessMapper.getPWorkProcessAndPFromAttributeValueAndByProduceTask(produceTaskId);
    }

    /**
     * 通过produceTaskId获取所有工序和工序字段对应值
     * @param produceTaskId
     * @return
     */
    @Override
    public List<PZsWorkProcess> getAllPWorkProcessAndPFromAttributeValueAndByProduceTask(Long produceTaskId) {
        List<PZsWorkProcess> zsWorkProcessList = new ArrayList<>();
        List<PZsWorkProcess> pZsWorkProcessList= zsWorkProcessMapper.getAllPWorkProcessAndPFromAttributeValueAndByProduceTask(produceTaskId);
        if(null!=pZsWorkProcessList&&0!=pZsWorkProcessList.size()){
            for(PZsWorkProcess pZsWorkProcess: pZsWorkProcessList){
                if(null!=pZsWorkProcess.getPBaFormAttributeValueList()&&0!=pZsWorkProcess.getPBaFormAttributeValueList().size()||1==pZsWorkProcess.getStatus()){
                    zsWorkProcessList.add(pZsWorkProcess);
                }
            }
        }
        return zsWorkProcessList;
    }

    @Override
    public List<PZsWorkProcess> getAllWorkProcessByFormAttribute() {
        return zsWorkProcessMapper.getAllWorkProcessByFormAttribute();
    }

    /**
     * 获取工序以及对应handleType最大value
     * @param produceTaskId
     * @return
     */
    @Override
    public List<PZsWorkProcess> getZsWorkProcessAndMaxHandleTypeValueByProduceTaskId(Long produceTaskId) {
        return zsWorkProcessMapper.getZsWorkProcessAndMaxHandleTypeValueByProduceTaskId(produceTaskId);
    }

    /**
     * 通过生产任务编号获取未删除并且有字段的工序
     * @param produceTaskId
     * @return
     */
    @Override
    public List<ZsWorkProcess> getNotDeleteAndHaveFieldsWorkProcessByProduceTaskId(Long produceTaskId) {
        return zsWorkProcessMapper.getNotDeleteAndHaveFieldsWorkProcessByProduceTaskId(produceTaskId);
    }

    /**
     * 通过生产任务编号获取全部并且有字段的工序
     * @param produceTaskId
     * @return
     */
    @Override
    public List<ZsWorkProcess> getAllAndHaveFieldsWorkProcessByProduceTaskId(Long produceTaskId) {
        return zsWorkProcessMapper.getAllAndHaveFieldsWorkProcessByProduceTaskId(produceTaskId);
    }
}
