package com.dalian.sea.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalian.sea.json.ImageJson;
import com.dalian.sea.model.*;

import com.dalian.sea.parameter.*;
import com.dalian.sea.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

/**
 * 预览页面
 * Created by Administrator on 2018/3/4.
 */
@Controller
@RequestMapping(value = "/PreviewPage")
public class PreviewPageContorller {
    @Autowired
    private PreviewPageService previewPageService;
    @Autowired
    private ZsProductionProcessService zsProductionProcessService;
    @Autowired
    private ZsProductionProcessDetailService zsProductionProcessDetailService;
    @Autowired
    private ZsBatchService zsBatchService;
    @Autowired
    private SysProductTypeService sysProductTypeService;
    @Autowired
    private ZsCertificateManageService zsCertificateManageService;
    @Autowired
    private TagSweepService tagSweepService;
    @Autowired
    private SaOrderDetailService saOrderDetailService;

    /**
     * 企业介绍
     * @return
     */
    @RequestMapping(value = "culture.htm")
    public  String Culture(HttpServletRequest request){
        PBaCompany pBaCompany = previewPageService.CompanyIntroduction();
        request.setAttribute("pBaCompany",pBaCompany);
        List<PZsCompanyCulture> companyCultureList = previewPageService.getCompanyCultureList();
        request.setAttribute("companyCultureList",companyCultureList);
        return "PreviewPage/culture";
    }
    /**
     * 企业新闻
     * @return
     */
    @RequestMapping(value = "news.htm")
    public String News(HttpServletRequest request){

        List<ZsCorporateNews> corporateNewsBy = previewPageService.getCorporateNewsBy();

        request.setAttribute("corporateNewsBy",corporateNewsBy);
        return "PreviewPage/news";
    }
    /***
     * 晓芹厨房
     * @return
     */
    @RequestMapping(value = "kitchen.htm")
    public String Kitchen(HttpServletRequest request){
        List<PXqKitchen> xqKitchen = previewPageService.getXqKitchen();
        for (PXqKitchen Kitchen:xqKitchen) {
            Type type = new TypeReference<List<ImageJson>>() {}.getType();
            List<ImageJson> imageList = JSON.parseObject(Kitchen.getImages(), type);
            Kitchen.setImagesJson(imageList);
        }
        request.setAttribute("xqKitchen",xqKitchen);
        return  "PreviewPage/kitchen";
    }
    /***
     * 晓芹厨房详细信息
     * @return
     */
    @RequestMapping(value = "oxtail.htm")
    public String Oxtail(HttpServletRequest request,Long Id){
        PXqKitchen xqKitchenById = previewPageService.getXqKitchenById(Id);
        if(null!=xqKitchenById){
            if(null!=xqKitchenById.getImages()){
                Type type = new TypeReference<List<ImageJson>>() {}.getType();
                List<ImageJson> imageList = JSON.parseObject(xqKitchenById.getImages(), type);
                Iterator<ImageJson> iterator = imageList.iterator();
                while(iterator.hasNext()){
                    ImageJson imageJson = iterator.next();
                    if("".equals(imageJson.getImageUrl())){
                        iterator.remove();
                    }
                }
                if(null!=imageList&&0!=imageList.size()){
                    xqKitchenById.setImagesJson(imageList);
                }
            }
            request.setAttribute("xqKitchenById",xqKitchenById);
        }
        return  "PreviewPage/second/oxtail";
    }

    /***
     * 检测实验
     * @param request
     * @return
     */
    @RequestMapping(value = "check.htm")
    public String check(HttpServletRequest request){
        List<PTestingEquipment> ptestingEquipment = previewPageService.getTestingEquipment();
        List<PTestingEquipmentDetail> testingEquipmentDetail = previewPageService.getTestingEquipmentDetail();
        request.setAttribute("ptestingEquipment",ptestingEquipment);
        request.setAttribute("testingEquipmentDetail",testingEquipmentDetail);
        return  "PreviewPage/check";
    }
    /***
     * 生产信息
     * @return
     */
    @RequestMapping(value = "product.htm")
    public String product(HttpServletRequest request,Long id){
        if(id!=null){
            List<ZsProductionInformation> productionInformation = previewPageService.getProductionInformation();
            request.setAttribute("productionInformation",productionInformation);
            ZsProductionProcess zsProductionProcess = zsProductionProcessService.getProductionProcessById(id);
            if(null!=zsProductionProcess){
                request.setAttribute("zsProductionProcess",zsProductionProcess);
            }
            List<ZsProductionProcessDetail> zsProductionProcessDetails = zsProductionProcessDetailService.getProdutionProcessDetailByProductionProcessId(id);
            if(null!=zsProductionProcessDetails&&0!=zsProductionProcessDetails.size()){
                request.setAttribute("zsProductionProcessDetails",zsProductionProcessDetails);
            }
        }
        return  "PreviewPage/product";
    }
    /***
     * 产品信息
     * @return
     */
    @RequestMapping(value = "details.htm")
    public String Details(HttpServletRequest request, Long batchId, String qrCode){
        if(null!=batchId){
            PZsBatch pZsBatch = zsBatchService.getPBatchById(batchId);
            if(null!=pZsBatch){
                request.setAttribute("pZsBatch",pZsBatch);
                PZsCompanyProduct pZsCompanyProduct = new PZsCompanyProduct();
                pZsCompanyProduct.setProductLine(pZsBatch.getProductLine());
                pZsCompanyProduct.setProductCategory(pZsBatch.getProductBigType());
                pZsCompanyProduct.setProductTypeName(pZsBatch.getProductSmallType());
                SysProductType sysProductType = sysProductTypeService.getSysProductTypeByNameAndParentName(pZsCompanyProduct);
                if(null!=sysProductType){
                PZsCertificateManage pZsCertificateManage = zsCertificateManageService.getCertificateManageBySmallProductTypeId(sysProductType.getProductTypeId());
                    if(null!=pZsCertificateManage){
                        request.setAttribute("pZsCertificateManage",pZsCertificateManage);
                    }
                }
                Integer count = tagSweepService.getSweepCountByBatchNo(pZsBatch.getBatchCode());
                request.setAttribute("count",count);
            }
        }
        PSaOrderDetail pSaOrderDetail = saOrderDetailService.getOrderAndOrderDetailByQrCode(qrCode);
        request.setAttribute("pSaOrderDetail",pSaOrderDetail);
        PBaCompany pBaCompany = previewPageService.CompanyIntroduction();
        if(null!=pBaCompany){
            if(null!=pBaCompany.getSeaAreaImages()&&!"".equals(pBaCompany.getSeaAreaImages())){
                Type type = new TypeReference<List<ImageJson>>() {}.getType();
                List<ImageJson> imageList = JSON.parseObject(pBaCompany.getSeaAreaImages(), type);
                Iterator<ImageJson> iterator = imageList.iterator();
                while(iterator.hasNext()){
                    ImageJson imageJson = iterator.next();
                    if("".equals(imageJson.getImageUrl())){
                        iterator.remove();
                    }
                }
                if(null!=imageList&&0!=imageList.size()){
                    pBaCompany.setSeaAreaImagesJson(imageList);
                }
                request.setAttribute("pBaCompany",pBaCompany);
            }
        }
        return "PreviewPage/details";
    }

}
