package com.dalian.sea.service;

import com.dalian.sea.model.ZsCompanyCulture;
import com.dalian.sea.model.ZsCorporateNews;
import com.dalian.sea.model.ZsProductionInformation;
import com.dalian.sea.parameter.PTestingEquipment;
import com.dalian.sea.parameter.PTestingEquipmentDetail;
import com.dalian.sea.parameter.PXqKitchen;

import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */
public interface TraceabilityService {
    /***
     * 企业新闻显示数据
     * @param
     * @return
     */
    List<ZsCorporateNews> getCorporateNewsBy(Integer page, Integer rows);

    /***
     * 新增企业新闻
     * @param corporateNews
     * @return
     */
    Boolean addCorporateNews(ZsCorporateNews corporateNews);

    /***
     * 根据id查询信息
     * @param corporateNewsId
     * @return
     */
    ZsCorporateNews getCorporateNewsById(Long corporateNewsId);
    /***
     * 修改企业新闻
     * @param corporateNews
     * @return
     */
    Boolean updateCorporateNews(ZsCorporateNews corporateNews);

    /***
     * 新增内容
     * @param
     * @return
     */
    Boolean updateAddCorporateNews(ZsCorporateNews corporateNews);
    /***
     * 删除新闻
     * @param
     * @return
     */
    Boolean delectCorporateNews(ZsCorporateNews corporateNews);

    /***
     * 企业文化详细信息
     * @return
     */
    List<ZsCompanyCulture> getCompanyCultureList(Integer page, Integer rows);
    /***
     * 新增企业文化
     * @return
     */
    Boolean addCompanyCulture(ZsCompanyCulture companyCulture);
    /***
     * 根据id查询企业文化
     * @param companyCultureId
     * @return
     */
    ZsCompanyCulture getCompanyCultureById(Long companyCultureId);
    /***
     * 修改企业文化
     * @param companyCulture
     * @return
     */
    Boolean updateCompanyCultureById(ZsCompanyCulture companyCulture);
    /***
     * 删除企业文化
     * @param companyCulture
     * @return
     */
    Boolean delectCompanyCulture(ZsCompanyCulture companyCulture);
    /***
     * 生产关键控制点
     * @return
     */
    List<ZsProductionInformation> getProductionInformation(Integer page, Integer rows);
    /***
     * 新增生产控制点
     * @param productionInformation
     * @return
     */
    Boolean addProductionInformation(ZsProductionInformation productionInformation);
    /***
     * 根据id查询生产控制点信息
     * @param productionInformationId
     * @return
     */
    ZsProductionInformation getProductionInformationById(Long productionInformationId);
    /***
     * 修改生产控制点
     * @param productionInformation
     * @return
     */
    Boolean updateProductionInformation(ZsProductionInformation productionInformation);
    /***
     * 删除生产控制点
     * @param
     * @return
     */
    Boolean delectProductionInformation(ZsProductionInformation productionInformation);
    /***
     * 查询所有晓芹厨房
     * @return
     */
    List<PXqKitchen> getXqKitchen(Integer page, Integer rows);
    /***
     * 新增晓芹厨房
     * @param pXqKitchen
     * @return
     */
    Boolean addXqKitchen(PXqKitchen pXqKitchen);

    /***
     * 修改晓芹厨房
     * @param pXqKitchen
     * @return
     */
    Boolean updateXqKitchen(PXqKitchen pXqKitchen);

    /***
     * 删除晓芹厨房
     * @param pXqKitchen
     * @return
     */
    Boolean delectXqKitchen(PXqKitchen pXqKitchen);
    /***
     * 根据ID查询
     * @return
     */
    PXqKitchen getXqKitchenById(Long kitchenId);
    /***
     * 查询检验室内容
     * @return
     */
    List<PTestingEquipment> getTestingEquipment(Integer page, Integer rows);
    /***
     * 检验室详细内容
     * @return
     */
    List<PTestingEquipmentDetail> getTestingEquipmentDetail(Integer page, Integer rows);
    /***
     * 查询所有检验室
     */
    List<PTestingEquipment> getDictionary();
    /***
     * 新增检测设备
     * @param pTestingEquipment
     * @return
     */
    Boolean addTestingEquipment(PTestingEquipment pTestingEquipment);
    /***
     * 修改检测设备
     * @param pTestingEquipment
     * @return
     */
    Boolean updateTestingEquipment(PTestingEquipment pTestingEquipment);
    /***
     * 根据id查询检测设备信息
     * @param testingEquipmentId
     * @return
     */
    PTestingEquipment getTestingEquipmentById(Long testingEquipmentId);
    /***
     * 删除检测设备信息
     * @param
     * @return
     */
    Boolean delectTestingEquipment(PTestingEquipment pTestingEquipment);
    /***
     * 新增检验室详细内容
     * @param pTestingEquipmentDetail
     * @return
     */
    Boolean addTestingEquipmentDetail(PTestingEquipmentDetail pTestingEquipmentDetail);
    /***
     * 查询现有检验室
     * @return
     */
    List<PTestingEquipment> getEquipment();
    /***
     * 根据id查询检验室详细信息
     * @param testingEquipmentDetailId
     * @return
     */
    PTestingEquipmentDetail getTestingEquipmentDetailById(Long testingEquipmentDetailId);

    /***
     * 修改检验室详细信息
     * @param pTestingEquipmentDetail
     * @return
     */
    Boolean updateTestingEquipmentDetail(PTestingEquipmentDetail pTestingEquipmentDetail);

    /***
     * 删除检验室详细
     * @param pTestingEquipmentDetail
     * @return
     */
    Boolean delectTestingEquipmentDetail(PTestingEquipmentDetail pTestingEquipmentDetail);

}
