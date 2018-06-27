package com.dalian.sea.controller;

import Utils.GenerateUtils;
import com.dalian.sea.ClientIP;
import com.dalian.sea.model.*;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 杨建 on 2018/4/12.
 */
@Controller
@RequestMapping(value = "/p")
public class ViewPageController {
    @Autowired
    private ZsBatchService zsBatchService;
    @Autowired
    private TagRangeService tagRangeService;
    @Autowired
    private TagSweepService tagSweepService;
    @Autowired
    private SaOrderDetailService saOrderDetailService;
    @Autowired
    private SysProductTypeService sysProductTypeService;
    @Autowired
    private ZsCertificateManageService zsCertificateManageService;
    @Autowired
    private ZsProductionProcessService zsProductionProcessService;
    @Autowired
    private ZsProductionProcessDetailService zsProductionProcessDetailService;

    /***
     * 主页面
     * @param
     * @return
     */
    @RequestMapping(value = "/**")
    public String Index(HttpServletRequest request){
        String servletPath = request.getServletPath();
        //截取后十四位
        String substring = servletPath.substring(servletPath.length() - 14, servletPath.length());
        //获取明码
        String clearCode = GenerateUtils.zstoCode(substring);
        //获取标签规则
        String ruleName = substring.substring(0, 4);
        PTagRange range = new PTagRange();
        range.setClearCode(Integer.valueOf(clearCode));
        range.setRuleName(ruleName);
        TagRange tagRangeByRange = tagRangeService.getTagRangeByRange(range);
        TagSweep tagSweep = new TagSweep();
        String ip = ClientIP.getClientIP(request);
        tagSweep.setCreateIp(ip);
        tagSweep.setUpdateIp(ip);
        tagSweep.setRuleName(ruleName);
        tagSweep.setClearCode(Integer.valueOf(clearCode));
        String qrCode = servletPath.substring(servletPath.length() - 18, servletPath.length());
        tagSweep.setQrCode(qrCode);
        if (tagRangeByRange!=null){
            PZsBatch pZsBatch = new PZsBatch();
            pZsBatch.setBatchId(tagRangeByRange.getBatchId());
            PZsBatch batchByBatch = zsBatchService.getBatchByBatch(pZsBatch);
            //获取产品编号
            if(null!=batchByBatch){
            String productCode = batchByBatch.getProductCode();
            request.setAttribute("productCode",productCode);
            request.setAttribute("batchId",batchByBatch.getBatchId());
            tagSweep.setBatchNo(batchByBatch.getBatchCode());
            tagSweep.setProductName(batchByBatch.getProductName());
            tagSweep.setProductSpecName(batchByBatch.getProductFormat());
                PZsCompanyProduct pZsCompanyProduct = new PZsCompanyProduct();
                pZsCompanyProduct.setProductLine(batchByBatch.getProductLine());
                pZsCompanyProduct.setProductCategory(batchByBatch.getProductBigType());
                pZsCompanyProduct.setProductTypeName(batchByBatch.getProductSmallType());
                //获取产品小类
                SysProductType sysProductType = sysProductTypeService.getSysProductTypeByNameAndParentName(pZsCompanyProduct);
                if(null!=sysProductType){
                    PZsCertificateManage pZsCertificateManage = zsCertificateManageService.getCertificateManageBySmallProductTypeId(sysProductType.getProductTypeId());
                    if(null!=pZsCertificateManage){
                        ZsProductionProcess zsProductionProcess = zsProductionProcessService.getProductionProcessById(pZsCertificateManage.getProductionProcessId());
                        if(null!=zsProductionProcess){
                            request.setAttribute("productionProcessId",zsProductionProcess.getProductionProcessId());
                        }
                    }
                }
                PSaOrderDetail pSaOrderDetail = saOrderDetailService.getOrderAndOrderDetailByQrCode(qrCode);
                if(null!=pSaOrderDetail){
                    tagSweep.setOrderDetailCode(pSaOrderDetail.getOrderDetailName());
                    tagSweep.setOrderCode(pSaOrderDetail.getSaOrder().getOrderName());
                }
                //添加扫码记录
                Boolean boo =  tagSweepService.addSweep(tagSweep);
            }
        }
//        PSaOrderDetail pSaOrderDetail = saOrderDetailService.getOrderAndOrderDetailByQrCode(qrCode);
//        if(null!=pSaOrderDetail){
//            tagSweep.setOrderDetailCode(pSaOrderDetail.getOrderDetailName());
//            tagSweep.setOrderCode(pSaOrderDetail.getSaOrder().getOrderName());
//        }
//        //添加扫码记录
//        Boolean boo =  tagSweepService.addSweep(tagSweep);
        request.setAttribute("qrCode",qrCode);
        return "PreviewPage/index";
    }
}
