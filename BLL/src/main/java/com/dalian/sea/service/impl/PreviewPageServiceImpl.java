package com.dalian.sea.service.impl;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.json.ImageJson;
import com.dalian.sea.mapper.*;
import com.dalian.sea.model.*;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.PreviewPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Administrator on 2018/3/5.
 */
@Service("PreviewPageService")
public class PreviewPageServiceImpl implements PreviewPageService {
    @Autowired
    private BaCompanyMapper baCompanyMapper;
    @Autowired
    private ZsCompanyCultureMapper companyCultureMapper;
    @Autowired
    private ZsCorporateNewsMapper corporateNewsMapper;
    @Autowired
    private ZsXqKitchenMapper xqKitchenMapper;
    @Autowired
    private ZsTestingEquipmentMapper testingEquipmentMapper;
    @Autowired
    private ZsTestingEquipmentDetailMapper testingEquipmentDetailMapper;
    @Autowired
    private ZsProductionInformationMapper productionInformationMapper;

    /***
     * 预览页公司介绍
     * @return
     */
    @Override
    public PBaCompany CompanyIntroduction() {

        return baCompanyMapper.getCompanyIntroduction();
    }

    /***
     * 预览页面企业文化
     * @return
     */
    @Override
    public List<PZsCompanyCulture> getCompanyCultureList() {
        List<PZsCompanyCulture> companyCultureList = companyCultureMapper.getPCompanyCultureList();
        if(null!=companyCultureList&&0!=companyCultureList.size()){
            Type type = new TypeReference<List<ImageJson>>() {}.getType();
            for(PZsCompanyCulture zsCompanyCulture: companyCultureList){
                if(null!=zsCompanyCulture.getImage()){
                    List<ImageJson> imageJsonList = JSON.parseObject(zsCompanyCulture.getImage(),type);
                    Iterator<ImageJson> iterator = imageJsonList.iterator();
                    while(iterator.hasNext()){
                        ImageJson imageJson = iterator.next();
                        if("".equals(imageJson.getImageUrl())){
                            iterator.remove();
                        }
                        if(null!=imageJsonList&&0!=imageJsonList.size()){
                            zsCompanyCulture.setImageJsonList(imageJsonList);
                        }
                    }
                }
            }
        }
        return companyCultureList;
    }
    /***
     * 企业新闻分
     * @param
     * @return
     */
    @Override
    public List<ZsCorporateNews> getCorporateNewsBy() {
        return corporateNewsMapper.getCorporateNewsBy();
    }
    /***
     * 晓芹厨房
     * @return
     */
    @Override
    public List<PXqKitchen> getXqKitchen() {
        return xqKitchenMapper.getXqKitchen();
    }
    /***
     * 晓芹厨房根据ID查询
     * @return
     */
    @Override
    public PXqKitchen getXqKitchenById(Long kitchenId) {
        return xqKitchenMapper.getXqKitchenById(kitchenId);
    }
    /**
     * 查询检验室内容
     * @return
     */
    @Override
    public List<PTestingEquipment> getTestingEquipment() {
        return testingEquipmentMapper.getTestingEquipment();
    }
    /***
     * 检验室详细内容
     * @return
     */
    @Override
    public List<PTestingEquipmentDetail> getTestingEquipmentDetail() {
        return testingEquipmentDetailMapper.getTestingEquipmentDetail();
    }
    /***
     * 预览页生产关键控制点
     * @return
     */
    @Override
    public List<ZsProductionInformation> getProductionInformation() {
        return productionInformationMapper.getProductionInformation();
    }



}
