package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.*;
import com.dalian.sea.model.BaDataDictionaryDetails;
import com.dalian.sea.model.BoxBatchTemp;
import com.dalian.sea.model.SaError;
import com.dalian.sea.model.SysProductType;
import com.dalian.sea.parameter.BatchEnterStockDetail;
import com.dalian.sea.parameter.PZsBatch;
import com.dalian.sea.parameter.PZsCompanyProduct;
import com.dalian.sea.service.ZsBatchService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author 杨建
 * @date 2018/3/28
 */
@Service("batchService")
@Transactional(rollbackFor = Exception.class)
public class ZsBatchServiceImpl implements ZsBatchService {
    @Autowired
    private ZsBatchMapper batchMapper;

    @Autowired
    private BoxBatchTempMapper boxBatchTempMapper;
    @Autowired
    private SaErrorMapper errorMapper;

    @Autowired
    private SysProductTypeMapper sysProductTypeMapper;

    @Autowired
    private BaDataDictionaryDetailsMapper baDataDictionaryDetailsMapper;


    @Override
    public List<PZsBatch> selectZsBatchByBatch(PZsBatch batch, Integer page, Integer row) {
        PageHelper.startPage(page, row);
        return batchMapper.getBatchListByBatch(batch);
    }

    @Override
    @Async
    public Future<Boolean> insertBoxBatchByBatch(List<BoxBatchTemp> boxBatchTempList) {
        if (boxBatchTempList.size() > 0) {
            boxBatchTempMapper.insertBoxBatchTempByBatch(boxBatchTempList);
        }
        return new AsyncResult<Boolean>(true);
    }

    @Override
    @Async
    public void insertErrorByBatch(List<SaError> errorList) {
        if (errorList.size() > 0) {
            errorMapper.insertErrorByBatch(errorList);
        }
    }

    @Override
    public PZsBatch getBatchByBatch(PZsBatch batch) {
        return batchMapper.selectBatchByBatchUnique(batch);
    }

    @Override
    public List<PZsBatch> selectZsBatchByBatch1(PZsBatch batch) {
        return batchMapper.selectListBatchByBatch(batch);
    }

    /**
     * 根据id获取
     *
     * @param batchId
     * @return
     */
    @Override
    public PZsBatch getPBatchById(Long batchId) {
        return batchMapper.getPBatchById(batchId);
    }

    @Override
    public BatchEnterStockDetail getEnterStockDetailByBatchId(Long batchId) {
        SysProductType sys;
        BatchEnterStockDetail besd = new BatchEnterStockDetail();
        try {
            //根据批次号获取批次详情
            PZsBatch pZsBatch = batchMapper.getPBatchById(batchId);
            if (null != pZsBatch) {
                PZsCompanyProduct companyProduct = new PZsCompanyProduct();
                companyProduct.setProductLine(pZsBatch.getProductLine());
                companyProduct.setProductCategory(pZsBatch.getProductBigType());
                companyProduct.setProductTypeName(pZsBatch.getProductSmallType());
                //获取货物类型id,名称
                String goodsType = pZsBatch.getProductCategory();
                BaDataDictionaryDetails baDataDictionaryDetailsByDataNameAndDataDetailsName = baDataDictionaryDetailsMapper.getBaDataDictionaryDetailsByDataNameAndDataDetailsName("货物类型", goodsType);
                besd.setGoodsTypeId(baDataDictionaryDetailsByDataNameAndDataDetailsName.getDataDictionaryDetailsId());
                besd.setGoodsType(goodsType);
                //获取产品小类的id和名称
                sys = sysProductTypeMapper.getSysProductTypeByNameAndParentName(companyProduct);
                if (null != sys) {
                    besd.setProductId(sys.getProductTypeId());
                    besd.setProductName(sys.getCName());
                }
                //获取产品状态id,名称
                BaDataDictionaryDetails baDataDictionaryDetails = baDataDictionaryDetailsMapper.getBaDataDictionaryDetailsByDataNameAndDataDetailsName("产品状态", "成品");
                if (null != baDataDictionaryDetails) {
                    besd.setProductStatusId(baDataDictionaryDetails.getDataDictionaryDetailsId());
                    besd.setProductStatus("成品");
                } else {
                    besd.setProductStatus("--");
                }
                //入库规格
                besd.setProductSpec(pZsBatch.getProductFormat());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return besd;
    }

    @Override
    public List<PZsBatch> selectZsBatchByBatchIsPack(PZsBatch batch, Integer page, Integer row) {
        PageHelper.startPage(page, row);
        List<PZsBatch> pZsBatches = batchMapper.selectZsBatchByBatchIsPack(batch);
        if (pZsBatches.size()==1){
            if (pZsBatches.get(0).getBatchCode()!=null){
                return pZsBatches;
            }else {
                return  null;
            }
        }
        return pZsBatches;
    }

}
