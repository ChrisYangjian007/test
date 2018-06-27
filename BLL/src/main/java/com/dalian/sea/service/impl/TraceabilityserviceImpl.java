package com.dalian.sea.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.json.ImageJson;
import com.dalian.sea.mapper.*;
import com.dalian.sea.model.ZsCompanyCulture;
import com.dalian.sea.model.ZsCorporateNews;
import com.dalian.sea.model.ZsProductionInformation;
import com.dalian.sea.parameter.PTestingEquipment;
import com.dalian.sea.parameter.PTestingEquipmentDetail;
import com.dalian.sea.parameter.PXqKitchen;
import com.dalian.sea.service.TraceabilityService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */
@Service("TraceabilityService")
@Slf4j
public class TraceabilityserviceImpl implements TraceabilityService {

    @Autowired
    private ZsCorporateNewsMapper corporateNewsMapper;
    @Autowired
    private ZsCompanyCultureMapper companyCultureMapper;
    @Autowired
    private ZsProductionInformationMapper productionInformationMapper;
    @Autowired
    private ZsXqKitchenMapper xqKitchenMapper;
    @Autowired
    private ZsTestingEquipmentMapper testingEquipmentMapper;
    @Autowired
    private ZsTestingEquipmentDetailMapper testingEquipmentDetailMapper;
    /***
     * 企业新闻显示数据
     * @param
     * @return
     */
    @Override
    public List<ZsCorporateNews> getCorporateNewsBy(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return corporateNewsMapper.getCorporateNewsBy();
    }
    /***
     * 新增企业新闻
     * @param corporateNews
     * @return
     */
    @Override
    public Boolean addCorporateNews(ZsCorporateNews corporateNews) {
        Boolean b = false;
        try {
            Integer cd = corporateNewsMapper.addCorporateNews(corporateNews);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 根据id查询信息
     * @param corporateNewsId
     * @return
     */
    @Override
    public ZsCorporateNews getCorporateNewsById(Long corporateNewsId) {
        return corporateNewsMapper.getCorporateNewsById(corporateNewsId);
    }

    /***
     * 修改
     * @param corporateNews
     * @return
     */
    @Override
    public Boolean updateCorporateNews(ZsCorporateNews corporateNews) {
        Boolean b = false;
        try {
            Integer cd = corporateNewsMapper.updateCorporateNews(corporateNews);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 新增内容
     * @param
     * @return
     */
    @Override
    public Boolean updateAddCorporateNews(ZsCorporateNews corporateNews) {

        Boolean b = false;
        try {
            Integer cd = corporateNewsMapper.updateAddCorporateNews(corporateNews);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 删除新闻
     * @param
     * @return
     */
    @Override
    public Boolean delectCorporateNews(ZsCorporateNews corporateNews) {
        Boolean b = false;
        try {
            Integer cd = corporateNewsMapper.delectCorporateNews(corporateNews);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 企业文化详细信息
     * @return
     */
    @Override
    public List<ZsCompanyCulture> getCompanyCultureList(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return companyCultureMapper.getCompanyCultureList();
    }
    /***
     * 新增企业文化
     * @return
     */
    @Override
    public Boolean addCompanyCulture(ZsCompanyCulture companyCulture) {
        Boolean b =false;
        try {
            Integer cd = companyCultureMapper.addCompanyCulture(companyCulture);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 根据id查询企业文化
     * @param companyCultureId
     * @return
     */
    @Override
    public ZsCompanyCulture getCompanyCultureById(Long companyCultureId) {
        return companyCultureMapper.getCompanyCultureById(companyCultureId);
    }
    /***
     * 修改企业文化
     * @param companyCulture
     * @return
     */
    @Override
    public Boolean updateCompanyCultureById(ZsCompanyCulture companyCulture) {
        Boolean b =false;
        try {
            Integer cd = companyCultureMapper.updateCompanyCulture(companyCulture);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 删除企业文化
     * @param companyCulture
     * @return
     */
    @Override
    public Boolean delectCompanyCulture(ZsCompanyCulture companyCulture) {
        Boolean b =false;
        try {
            Integer cd = companyCultureMapper.delectCompanyCulture(companyCulture);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 生产关键控制点
     * @return
     */
    @Override
    public List<ZsProductionInformation> getProductionInformation(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return productionInformationMapper.getProductionInformation();
    }
    /***
     * 新增生产控制点
     * @param productionInformation
     * @return
     */
    @Override
    public Boolean addProductionInformation(ZsProductionInformation productionInformation) {
        Boolean b =false;
        try {
            Integer cd = productionInformationMapper.addProductionInformation(productionInformation);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 根据id查询生产控制点信息
     * @param productionInformationId
     * @return
     */
    @Override
    public ZsProductionInformation getProductionInformationById(Long productionInformationId) {
        return productionInformationMapper.getProductionInformationById(productionInformationId);
    }
    /***
     * 修改生产控制点
     * @param productionInformation
     * @return
     */
    @Override
    public Boolean updateProductionInformation(ZsProductionInformation productionInformation) {
        Boolean b =false;
        try {
            Integer cd = productionInformationMapper.updateProductionInformation(productionInformation);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 删除生产控制点
     * @param
     * @return
     */
    @Override
    public Boolean delectProductionInformation(ZsProductionInformation productionInformation) {
        Boolean b =false;
        try {
            Integer cd = productionInformationMapper.delectProductionInformation(productionInformation);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 查询所有晓芹厨房
     * @return
     */
    @Override
    public List<PXqKitchen> getXqKitchen(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<PXqKitchen> kitchenList = xqKitchenMapper.getXqKitchen();
        if(null!=kitchenList&&0!=kitchenList.size()){
            Type type = new TypeReference<List<ImageJson>>() {}.getType();
            for(PXqKitchen pXqKitchen: kitchenList){
                if(null!=pXqKitchen.getImages()&&!"".equals(pXqKitchen.getImages())){
                    List<ImageJson> imageJsonList = JSON.parseObject(pXqKitchen.getImages(),type);
                    Iterator<ImageJson> iterator = imageJsonList.iterator();
                    while(iterator.hasNext()){
                        ImageJson imageJson = iterator.next();
                        if("".equals(imageJson.getImageUrl())){
                            iterator.remove();
                        }
                    }
                    if(null!=imageJsonList&&0!=imageJsonList.size()){
                        pXqKitchen.setIsHaveImage(1);
                    }else {
                        pXqKitchen.setIsHaveImage(2);
                    }
                }else{
                    pXqKitchen.setIsHaveImage(2);
                }
            }
        }
        return kitchenList;
    }
    /***
     * 新增晓芹厨房
     * @param pXqKitchen
     * @return
     */
    @Override
    public Boolean addXqKitchen(PXqKitchen pXqKitchen) {
        Boolean b =false;
        try {
            Integer cd = xqKitchenMapper.addXqKitchen(pXqKitchen);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 修改晓芹厨房
     * @param pXqKitchen
     * @return
     */
    @Override
    public Boolean updateXqKitchen(PXqKitchen pXqKitchen) {
        Boolean b =false;
        try {
            Integer cd = xqKitchenMapper.updateXqKitchen(pXqKitchen);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 删除晓芹厨房
     * @param pXqKitchen
     * @return
     */
    @Override
    public Boolean delectXqKitchen(PXqKitchen pXqKitchen) {
        Boolean b =false;
        try {
            Integer cd = xqKitchenMapper.delectXqKitchen(pXqKitchen);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 根据ID查询
     * @return
     */
    @Override
    public PXqKitchen getXqKitchenById(Long kitchenId) {
        return xqKitchenMapper.getXqKitchenById(kitchenId);
    }
    /***
     * 查询检验室内容
     * @return
     */
    @Override
    public List<PTestingEquipment> getTestingEquipment(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return testingEquipmentMapper.getTestingEquipment();
    }
    /***
     * 检验室详细内容
     * @return
     */
    @Override
    public List<PTestingEquipmentDetail> getTestingEquipmentDetail(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return testingEquipmentDetailMapper.getTestingEquipmentDetail();
    }

    /***
     * 查询所有检验室
     */
    @Override
    public List<PTestingEquipment> getDictionary() {
        return testingEquipmentMapper.getDictionary();
    }
    /***
     * 新增检测设备
     * @param pTestingEquipment
     * @return
     */
    @Override
    public Boolean addTestingEquipment(PTestingEquipment pTestingEquipment) {
        Boolean b =false;
        try {
            Integer cd = testingEquipmentMapper.addTestingEquipment(pTestingEquipment);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 修改检测设备
     * @param pTestingEquipment
     * @return
     */
    @Override
    public Boolean updateTestingEquipment(PTestingEquipment pTestingEquipment) {
        Boolean b =false;
        try {
            Integer cd = testingEquipmentMapper.updateTestingEquipment(pTestingEquipment);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 根据id查询检测设备信息
     * @param testingEquipmentId
     * @return
     */
    @Override
    public PTestingEquipment getTestingEquipmentById(Long testingEquipmentId) {
        return testingEquipmentMapper.getTestingEquipmentById(testingEquipmentId);
    }
    /***
     * 删除检测设备信息
     * @param
     * @return
     */
    @Override
    @Transactional
    public Boolean delectTestingEquipment(PTestingEquipment pTestingEquipment) {
        Boolean b =false;
        try {
            Integer result = 0 ;
            Integer cd = testingEquipmentMapper.delectTestingEquipment(pTestingEquipment);
            List<PTestingEquipmentDetail> equipmentDetailList = testingEquipmentDetailMapper.getPtestingEqDetailsByTestingEqId(pTestingEquipment.getTestingEquipmentId());
            if(null!=equipmentDetailList&&0!=equipmentDetailList.size()){
                result = testingEquipmentDetailMapper.deleteTestingEqDetailByTestingEqId(pTestingEquipment);
            }else {
                result = 1;
            }
            if (0 < cd &&0 < result) {
                b = true;
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return b;
    }
    /***
     * 新增检验室详细内容
     * @param pTestingEquipmentDetail
     * @return
     */
    @Override
    public Boolean addTestingEquipmentDetail(PTestingEquipmentDetail pTestingEquipmentDetail) {
        Boolean b =false;
        try {
            Integer cd = testingEquipmentDetailMapper.addTestingEquipmentDetail(pTestingEquipmentDetail);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 查询现有检验室
     * @return
     */
    @Override
    public List<PTestingEquipment> getEquipment() {
        return testingEquipmentMapper.getEquipment();
    }
    /***
     * 根据id查询检验室详细信息
     * @param testingEquipmentDetailId
     * @return
     */
    @Override
    public PTestingEquipmentDetail getTestingEquipmentDetailById(Long testingEquipmentDetailId) {
        return testingEquipmentDetailMapper.getTestingEquipmentDetailById(testingEquipmentDetailId);
    }
    /***
     * 修改检验室详细信息
     * @param pTestingEquipmentDetail
     * @return
     */
    @Override
    public Boolean updateTestingEquipmentDetail(PTestingEquipmentDetail pTestingEquipmentDetail) {
        Boolean b =false;
        try {
            Integer cd = testingEquipmentDetailMapper.updateTestingEquipmentDetail(pTestingEquipmentDetail);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
    /***
     * 删除检验室详细
     * @param pTestingEquipmentDetail
     * @return
     */
    @Override
    public Boolean delectTestingEquipmentDetail(PTestingEquipmentDetail pTestingEquipmentDetail) {
        Boolean b =false;
        try {
            Integer cd = testingEquipmentDetailMapper.delectTestingEquipmentDetail(pTestingEquipmentDetail);
            if (cd >0) {
                b = true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return b;
    }
}
