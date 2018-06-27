package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BaFormAttributeMapper;
import com.dalian.sea.model.BaFormAttribute;
import com.dalian.sea.parameter.PBaFormAttribute;
import com.dalian.sea.service.BaFormAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BaFormAttributeServiceImpl
 *
 * @author TONE
 * @date 2018/2/8
 */
@Service
public class BaFormAttributeServiceImpl implements BaFormAttributeService {

    @Autowired
    private BaFormAttributeMapper baFormAttributeMapper;


    /**
     *根据工艺详情ID和类别获取
     * @param workProcessId
     * @param handleType
     * @return
     */
    @Override
    public List<PBaFormAttribute> getBaFormAttributeListByWorkProcessIdAndHandleType(Long workProcessId,Integer handleType) {
        return baFormAttributeMapper.getBaFormAttributeListByWorkProcessIdAndHandleType(workProcessId,handleType);
    }

    /**
     * 根据工艺id获取
     * @param workProcessId
     * @return
     */
    @Override
    public List<PBaFormAttribute> getBaFormAttributeListByWorkProcessId(Long workProcessId) {
        return baFormAttributeMapper.getBaFormAttributeListByWorkProcessId(workProcessId);
    }

    /**
     * 获取生产任务下所有工艺的所有字段
     * @param produceTaskId
     * @return
     */
    @Override
    public List<PBaFormAttribute> getFormAttributeByProduceTaskId(Long produceTaskId) {
        return baFormAttributeMapper.getFormAttributeByProduceTaskId(produceTaskId);
    }

    /**
     * 获取该工序下的默认当前用户的操作人、审核人、巡检人
     * @param workProcessId
     * @return
     */
    @Override
    public List<PBaFormAttribute> getDefaultFormAttributeByWorkProcessIdAndName(Long workProcessId) {
        return baFormAttributeMapper.getDefaultFormAttributeByWorkProcessIdAndName(workProcessId);
    }
}
