package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BaDataDictionaryDetailsMapper;
import com.dalian.sea.mapper.ZsWarehouseMapper;
import com.dalian.sea.model.BaDataDictionaryDetails;
import com.dalian.sea.model.Ireport;
import com.dalian.sea.model.PuEnterStockDetail;
import com.dalian.sea.model.ZsWarehouse;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.IreportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * IreportServiceImpl
 *
 * @author TONE
 * @date 2018/4/3.
 */
@Service("IreportService")
public class IreportServiceImpl implements IreportService {

    @Autowired
    private ZsWarehouseMapper zsWarehouseMapper;

    @Autowired
    private BaDataDictionaryDetailsMapper dataDictionaryDetailsMapper;

    /**
     * 入库单
     * @param stockAndStock
     * @param userName
     * @return
     */
    @Override
    public List<Ireport> receiveStoragePdf(PEnterStockAndStock stockAndStock,String userName) {
        List<ZsWarehouse> warehouseList = zsWarehouseMapper.getAllWarehouse();
        List<PBaDataDictionaryDetails> isMaterialList = dataDictionaryDetailsMapper.getBaDataDictionaryDetailsByCode("0102010000");
        List<PBaDataDictionaryDetails> productStatusList = dataDictionaryDetailsMapper.getBaDataDictionaryDetailsByCode("0102060000");
        List<Ireport> ireportList = new ArrayList<>();
        Ireport ireport;
        int i=1;
        for (PuEnterStockDetail stockDetail:stockAndStock.getEnterStockDetailList()){
            ireport = new Ireport();
            ireport.setTitle("大连晓芹食品有限公司入库单");
            ireport.setTitleOne(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(stockAndStock.getEnterStock().getEnterDate()));
            ireport.setTitleTwo(stockAndStock.getEnterStock().getEnterNo());
            ireport.setListIndex(String.valueOf(i));i++;
            ireport.setListOne(stockDetail.getBatchNo());
            for (PBaDataDictionaryDetails dictionaryDetails:isMaterialList){
                if (dictionaryDetails.getDataDictionaryDetailsId().equals((long)stockDetail.getIsMaterial())){
                    ireport.setListTwo(dictionaryDetails.getCName());
                    break;
                }
            }
            ireport.setListThree(stockDetail.getProductName());
            for (PBaDataDictionaryDetails dictionaryDetails:productStatusList){
                if (dictionaryDetails.getDataDictionaryDetailsId().equals((long)stockDetail.getProductStatus())){
                    ireport.setListFour(dictionaryDetails.getCName());
                    break;
                }
            }
            ireport.setListFive(stockDetail.getProductSpecName());
            ireport.setListSix(stockDetail.getInWeight()+"");
            ireport.setListSeven(stockDetail.getUnitName());
            for (ZsWarehouse warehouse:warehouseList){
                if (warehouse.getWarehouseId().equals(stockDetail.getWarehouseId())){
                    ireport.setListEight(warehouse.getCName());
                    break;
                }
            }
            ireport.setListNine(stockDetail.getRemark());
            ireport.setListSize(stockAndStock.getEnterStockDetailList().size()+"");
            ireport.setFooterOne(userName);
            ireport.setFooterTwo(stockAndStock.getEnterStock().getEnterPerson());
            ireport.setFooterThree(stockAndStock.getEnterStock().getBrokerage());
            ireport.setFooterFour(stockAndStock.getEnterStock().getRemark());
            ireportList.add(ireport);
        }
        return ireportList;
    }

    /**
     * 返库单
     * @param stockAndStock
     * @param userName
     * @return
     */
    @Override
    public List<Ireport> receiveDepotPdf(PEnterStockAndStock stockAndStock, String userName) {
        List<ZsWarehouse> warehouseList = zsWarehouseMapper.getAllWarehouse();
        List<PBaDataDictionaryDetails> isMaterialList = dataDictionaryDetailsMapper.getBaDataDictionaryDetailsByCode("0102010000");
        List<PBaDataDictionaryDetails> productStatusList = dataDictionaryDetailsMapper.getBaDataDictionaryDetailsByCode("0102060000");
        List<Ireport> ireportList = new ArrayList<>();
        Ireport ireport;
        int i=1;
        for (PZsStock stock:stockAndStock.getStockList()){
            ireport = new Ireport();
            ireport.setTitle("大连晓芹食品有限公司返库单");
            ireport.setTitleOne(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(stockAndStock.getEnterStock().getEnterDate()));
            ireport.setTitleTwo(stockAndStock.getEnterStock().getEnterNo());
            ireport.setListIndex(String.valueOf(i));i++;
            ireport.setListOne(stock.getBatchNo());
            for (PBaDataDictionaryDetails dictionaryDetails:isMaterialList){
                if (dictionaryDetails.getDataDictionaryDetailsId().equals((long)stock.getIsMaterial())){
                    ireport.setListTwo(dictionaryDetails.getCName());
                    break;
                }
            }
            ireport.setListThree(stock.getProductName());
            for (PBaDataDictionaryDetails dictionaryDetails:productStatusList){
                if (dictionaryDetails.getDataDictionaryDetailsId().equals((long)stock.getProductStatus())){
                    ireport.setListFour(dictionaryDetails.getCName());
                    break;
                }
            }
            ireport.setListFive(stock.getProductSpecName());
            ireport.setListSix(stock.getInWeight()+"");
            ireport.setListSeven(stock.getUnitName());
            for (ZsWarehouse warehouse:warehouseList){
                if (warehouse.getWarehouseId().equals(stock.getWarehouseId())){
                    ireport.setListEight(warehouse.getCName());
                    break;
                }
            }
            ireport.setListNine(stock.getRemark());
            ireport.setListSize(stockAndStock.getEnterStockDetailList().size()+"");
            ireport.setFooterOne(userName);
            ireport.setFooterTwo(stockAndStock.getEnterStock().getEnterPerson());
            ireport.setFooterThree(stockAndStock.getEnterStock().getBrokerage());
            ireport.setFooterFour(stockAndStock.getEnterStock().getRemark());
            ireportList.add(ireport);
        }
        return ireportList;
    }

    /**
     * 出库单
     * @param leaveStock
     * @param userName
     * @return
     */
    @Override
    public List<Ireport> receiveOutGoingPdf(YhLeaveStock leaveStock, String userName) {
        List<ZsWarehouse> warehouseList = zsWarehouseMapper.getAllWarehouse();
        List<PBaDataDictionaryDetails> isMaterialList = dataDictionaryDetailsMapper.getBaDataDictionaryDetailsByCode("0102010000");
        List<Ireport> ireportList = new ArrayList<>();
        Ireport ireport;
        int i=1;
        for (YhLeaveStockDetail leaveStockDetail:leaveStock.getSaLeaveStockDetails()){
            ireport = new Ireport();
            ireport.setTitle("大连晓芹食品有限公司出库单");
            ireport.setTitleOne(leaveStock.getProduceTaskName());
            ireport.setTitleTwo(leaveStock.getProduceTaskNo());
            ireport.setTitleThree(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(leaveStock.getLeaveDate()));
            ireport.setTitleFour(leaveStock.getLeaveNo());
            ireport.setListIndex(String.valueOf(i));i++;
            ireport.setListOne(leaveStockDetail.getBatchNo());
            for (PBaDataDictionaryDetails dictionaryDetails:isMaterialList){
                if (dictionaryDetails.getDataDictionaryDetailsId().equals((long)leaveStockDetail.getIsMaterial())){
                    ireport.setListTwo(dictionaryDetails.getCName());
                    break;
                }
            }
            for (ZsWarehouse warehouse:warehouseList){
                if (warehouse.getWarehouseId().equals(leaveStockDetail.getWarehouseId())){
                    ireport.setListThree(warehouse.getCName());
                    break;
                }
            }
            ireport.setListFour(leaveStockDetail.getProductName());
            ireport.setListFive(leaveStockDetail.getProductSpecName());
            ireport.setListSix(leaveStockDetail.getOutWeight()+"");
            ireport.setListSeven(leaveStockDetail.getUnitName());
            ireport.setListEight(leaveStockDetail.getRemark());
            ireport.setListSize(leaveStock.getSaLeaveStockDetails().size()+"");
            ireport.setFooterOne(userName);
            ireport.setFooterTwo(leaveStock.getLeavePerson());
            ireport.setFooterThree(leaveStock.getBrokerage());
            ireport.setFooterFour(leaveStock.getRemark());
            ireportList.add(ireport);
        }
        return ireportList;
    }
}
