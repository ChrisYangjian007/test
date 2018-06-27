package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsProductionProcessDetailMapper;
import com.dalian.sea.mapper.ZsProductionProcessMapper;
import com.dalian.sea.model.ZsProductionProcess;
import com.dalian.sea.model.ZsProductionProcessDetail;
import com.dalian.sea.parameter.PZsProductionProcess;
import com.dalian.sea.parameter.PZsProductionProcessDetail;
import com.dalian.sea.service.ZsProductionProcessService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */
@Service("ZsProductionProcessService")
public class ZsProductionProcessServiceImpl implements ZsProductionProcessService {

    @Autowired
    private ZsProductionProcessMapper zsProductionProcessMapper;
    @Autowired
    private ZsProductionProcessDetailMapper zsProductionProcessDetailMapper;

    /**
     * 通过id获取生产信息
     * @param id
     * @return
     */
    @Override
    public ZsProductionProcess getProductionProcessById(Long id) {
        return zsProductionProcessMapper.getProductionProcessById(id);
    }

    /**
     * 获取全部生产过程
     * @return
     */
    @Override
    public List<ZsProductionProcess> getAllProductionProcess() {
        return zsProductionProcessMapper.getAllProductionProcess();
    }

    /**
     * 获取最大编号
     * @return
     */
    @Override
    public ZsProductionProcess getMaxId() {
        return zsProductionProcessMapper.getMaxId();
    }

    /**
     * 添加zsProductionProcess
     * @param zsProductionProcess
     * @return
     */
    @Override
    public Boolean addZsProductionProcess(ZsProductionProcess zsProductionProcess) {
        Boolean boo = false;
        Integer result = zsProductionProcessMapper.addZsProductionProcess(zsProductionProcess);
        if(0<result){
            boo = true;
        }
        return boo;
    }

    /**
     * 批量添加生产过程
     * @param productionProcessList
     * @return
     */
    @Override
    public Boolean addZsProductionProcessByList(List<ZsProductionProcess> productionProcessList) {
        Boolean boo = false;
        Integer result = zsProductionProcessMapper.addZsProductionProcessByList(productionProcessList);
        if(0<result){
            boo = true ;
        }
        return boo;
    }

    /**
     * 通过名字获取
     * @param productionProcessName
     * @return
     */
    @Override
    public ZsProductionProcess getProductionProcessByName(String productionProcessName) {
        return zsProductionProcessMapper.getProductionProcessByName(productionProcessName);
    }

    /**
     * 生产过程管理
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PZsProductionProcess> getAllProductionProcessForGrid(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        return zsProductionProcessMapper.getAllProductionProcessForGrid();
    }

    /**
     * 修改生产过程
     * @param zsProductionProcess
     * @return
     */
    @Override
    public Boolean updateProductionProcess(ZsProductionProcess zsProductionProcess) {
        Boolean boo = false;
        Integer result = zsProductionProcessMapper.updateProductionProcess(zsProductionProcess);
        if(0<result){
            boo = true;
        }
        return boo;
    }

    /**
     * 删除生产过程
     * @param zsProductionProcess
     * @return
     */
    @Override
    @Transactional
    public Boolean deleteProductionProcess(ZsProductionProcess zsProductionProcess) {
        Boolean boo = false;
        try {
            Integer res = zsProductionProcessMapper.deleteProductionProcess(zsProductionProcess);
            Integer result = 0;
            List<PZsProductionProcessDetail> productionProcessDetailList = zsProductionProcessDetailMapper.getPProdutionProcessDetailByProductionProcessId(zsProductionProcess.getProductionProcessId());
            if(null!=productionProcessDetailList&&0!=productionProcessDetailList.size()){
                result=  zsProductionProcessDetailMapper.deleteProductionProcessDetailByProductionProcessId(zsProductionProcess);
            }else {
                result =1;
            }
            if(0<res&&0<result){
                boo = true;
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

}
