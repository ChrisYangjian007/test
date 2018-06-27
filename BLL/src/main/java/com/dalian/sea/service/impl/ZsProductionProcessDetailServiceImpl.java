package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.ZsProductionProcessDetailMapper;
import com.dalian.sea.model.ZsProductionProcess;
import com.dalian.sea.model.ZsProductionProcessDetail;
import com.dalian.sea.parameter.PZsProductionProcessDetail;
import com.dalian.sea.service.ZsProductionProcessDetailService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */
@Service("ZsProductionProcessDetailService")
public class ZsProductionProcessDetailServiceImpl implements ZsProductionProcessDetailService{

    @Autowired
    private ZsProductionProcessDetailMapper zsProductionProcessDetailMapper;

    /**
     * 通过productionProcessId获取生产过程详情
     * @param productionProcessId
     * @return
     */
    @Override
    public List<ZsProductionProcessDetail> getProdutionProcessDetailByProductionProcessId(Long productionProcessId) {
        return zsProductionProcessDetailMapper.getProdutionProcessDetailByProductionProcessId(productionProcessId);
    }

    /**
     * 通过productionProcessId获取生产过程详情继承类
     * @param productionProcessId
     * @return
     */
    @Override
    public List<PZsProductionProcessDetail> getPProdutionProcessDetailByProductionProcessId(Long productionProcessId) {
        return zsProductionProcessDetailMapper.getPProdutionProcessDetailByProductionProcessId(productionProcessId);
    }

    /**
     * 生产过程详情
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PZsProductionProcessDetail> getAllProductionProcessDetailForGrid(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        return zsProductionProcessDetailMapper.getAllProductionProcessDetailForGrid();
    }

    /**
     * 添加生产过程详情
     * @param zsProductionProcessDetail
     * @return
     */
    @Override
    public Boolean addProductionProcessDetail(ZsProductionProcessDetail zsProductionProcessDetail) {
        Boolean boo = false;
        Integer result = zsProductionProcessDetailMapper.addProductionProcessDetail(zsProductionProcessDetail);
        if(0<result){
            boo = true;
        }
        return boo;
    }

    /**
     * 根据id获取
     * @param productionProcessDetailId
     * @return
     */
    @Override
    public ZsProductionProcessDetail getProductionProcessDetailById(Long productionProcessDetailId) {
        return zsProductionProcessDetailMapper.getProductionProcessDetailById(productionProcessDetailId);
    }

    /**
     * 修改生产过程详情
     * @param zsProductionProcessDetail
     * @return
     */
    @Override
    public Boolean updateProductionProcessDetail(ZsProductionProcessDetail zsProductionProcessDetail) {
        Boolean boo = false;
        Integer result = zsProductionProcessDetailMapper.updateProductionProcessDetail(zsProductionProcessDetail);
        if(0<result){
            boo = true ;
        }
        return boo;
    }

    /**
     * 删除生产过程详情
     * @param zsProductionProcessDetail
     * @return
     */
    @Override
    public Boolean deleteProductionProcessDetail(ZsProductionProcessDetail zsProductionProcessDetail) {
        Boolean boo = false;
        Integer result = zsProductionProcessDetailMapper.deleteProductionProcessDetail(zsProductionProcessDetail);
        if(0<result){
            boo = true ;
        }
        return boo;
    }

    /**
     * 通过productionProcessId和name获取
     * @param zsProductionProcessDetail
     * @return
     */
    @Override
    public ZsProductionProcessDetail getProductionProcessDetailByProductionProcessIdAndName(ZsProductionProcessDetail zsProductionProcessDetail) {
        return zsProductionProcessDetailMapper.getProductionProcessDetailByProductionProcessIdAndName(zsProductionProcessDetail);
    }
}
