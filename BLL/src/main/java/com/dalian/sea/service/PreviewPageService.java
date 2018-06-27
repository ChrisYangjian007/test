package com.dalian.sea.service;

import com.dalian.sea.model.*;
import com.dalian.sea.parameter.*;

import java.util.List;

/**
 * Created by Administrator on 2018/3/5.
 */
public interface PreviewPageService {
    /***
     * 企业介绍
     * @return
     */
    PBaCompany CompanyIntroduction();
    /***
     * 预览页面企业文化
     * @return
     */
    List<PZsCompanyCulture> getCompanyCultureList();
    /***
     * 企业新闻
     * @param
     * @return
     */
    List<ZsCorporateNews> getCorporateNewsBy();

    /***
     * 晓芹厨房
     * @return
     */
    List<PXqKitchen> getXqKitchen();
    /***
     * 晓芹厨房根据ID查询
     * @return
     */
    PXqKitchen getXqKitchenById(Long kitchenId);
    /**
     * 查询检验室内容
     * @return
     */
    List<PTestingEquipment> getTestingEquipment();
    /***
     * 检验室详细内容
     * @return
     */
    List<PTestingEquipmentDetail> getTestingEquipmentDetail();
    /***
     * 预览页生产关键控制点
     * @return
     */
    List<ZsProductionInformation> getProductionInformation();

}
