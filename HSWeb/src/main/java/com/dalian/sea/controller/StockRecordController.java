package com.dalian.sea.controller;

import com.alibaba.fastjson.JSON;
import com.dalian.sea.ClientIP;
import com.dalian.sea.FileUpload.OSSClientUtil;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.ImageJson;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.ReturnGoods;
import com.dalian.sea.model.SaLeaveStockDetail;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.model.ZsWarehouse;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YH
 * @date 2018/2/24
 */
@Slf4j
@Controller
@RequestMapping(value = "/stockRecord")
public class StockRecordController extends LayoutRazor {

    @Autowired
    private PuEnterStockDetailService puEnterStockDetailService;

    @Autowired
    private SaLeaveStockDetailService saLeaveStockDetailService;

    @Autowired
    private ZsWarehouseService zsWarehouseService;

    @Autowired
    private ReturnGoodsService returnGoodsService;

    @Autowired
    private OSSClientUtil ossClientUtil;

    @Autowired
    private SysSysLogService sysSysLogService;

    /**
     * 出入库记录freeMark
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/stockRecordManage.htm")
    public String stockRecordManage(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        List<ZsWarehouse> warehouseList = zsWarehouseService.getAllWarehouse(user.getId(), user.getCompanyId());
        request.setAttribute("warehouseList", warehouseList);
        return freeMarkerIndexResult("stockRecord/stockRecordManage.ftl", request);
    }

    /**
     * 所有表
     * 获取产品状态
     */
    @RequestMapping("/getProductStatus.json")
    @ResponseBody
    public Object getProductStatus(Integer num) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        Json json = new Json();
        try {
            switch (num) {
                case 1:
                    List<PuEnterStockDetailPara> stockProductStatus = puEnterStockDetailService.getStockProductStatus(user.getId(), user.getCompanyId());
                    if (null != stockProductStatus && stockProductStatus.size() != 0) {
                        json.setSuccess(true);
                        json.setObj(stockProductStatus);
                    }
                    break;
                case 2:
                    List<SaLeaveStockDetailPara> stockProductStatus1 = saLeaveStockDetailService.getStockProductStatus(user.getId(), user.getCompanyId());
                    if (null != stockProductStatus1 && stockProductStatus1.size() != 0) {
                        json.setSuccess(true);
                        json.setObj(stockProductStatus1);
                    }
                    break;
                case 3:
                    List<SaLeaveStockDetailPara> stockProductStatus2 = saLeaveStockDetailService.getLossProductStatus(user.getId(), user.getCompanyId());
                    if (null != stockProductStatus2 && stockProductStatus2.size() != 0) {
                        json.setSuccess(true);
                        json.setObj(stockProductStatus2);
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 所有表
     * 获取货物类型
     *
     * @param num
     * @return
     */
    @RequestMapping("/getGoodsType.json")
    @ResponseBody
    public Object getGoodsType(Integer num) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        Json json = new Json();
        try {
            switch (num) {
                case 1:
                    List<PuEnterStockDetailPara> list = puEnterStockDetailService.getStockIsMaterial(user.getId(), user.getCompanyId());
                    if (null != list && list.size() != 0) {
                        json.setSuccess(true);
                        json.setObj(list);
                    }
                    break;
                case 2:
                    List<SaLeaveStockDetailPara> list2 = saLeaveStockDetailService.getStockIsMaterial(user.getId(), user.getCompanyId());
                    if (null != list2 && list2.size() != 0) {
                        json.setSuccess(true);
                        json.setObj(list2);
                    }
                    break;
                case 3:
                    List<SaLeaveStockDetailPara> list3 = saLeaveStockDetailService.getLossIsMaterial(user.getId(), user.getCompanyId());
                    if (null != list3 && list3.size() != 0) {
                        json.setSuccess(true);
                        json.setObj(list3);
                    }
                    break;
                case 4:
                    List<ReturnGoodsPara> list4 = returnGoodsService.getIsMaterial();
                    if (null != list4 && list4.size() != 0) {
                        json.setSuccess(true);
                        json.setObj(list4);
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 入库表
     * 根据货物类型
     * 获取入库记录产品
     *
     * @param isMaterial
     * @return
     */
    @RequestMapping("/getEnterStockProductList.json")
    @ResponseBody
    public Json getEnterStockProductList(Long isMaterial) {
        Json json = new Json();
        try {
            List<PuEnterStockDetailPara> productByIsMaterial = puEnterStockDetailService.getProductByIsMaterial(isMaterial);
            if (null != productByIsMaterial && productByIsMaterial.size() != 0) {
                json.setSuccess(true);
                json.setObj(productByIsMaterial);
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 入库表
     * 根据货物类型
     * 获取入库记录产品
     *
     * @param puEnterStockDetailPara
     * @return
     */
    @RequestMapping("/getEnterStockSpecList.json")
    @ResponseBody
    public Json getEnterStockSpecList(PuEnterStockDetailPara puEnterStockDetailPara) {
        Json json = new Json();
        try {
            List<PuEnterStockDetailPara> spec = puEnterStockDetailService.getSpec(puEnterStockDetailPara);
            if (null != spec && spec.size() != 0) {
                json.setObj(spec);
                json.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();

            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 出库表
     * 根据货物类型
     * 获取入库记录产品
     *
     * @param isMaterial
     * @return
     */
    @RequestMapping("/getLeaveStockProductList.json")
    @ResponseBody
    public Json getLeaveStockProductList(Long isMaterial) {
        Json json = new Json();
        try {
            List<SaLeaveStockDetail> list = saLeaveStockDetailService.getReturnProduct(isMaterial);
            if (null != list && list.size() != 0) {
                json.setSuccess(true);
                json.setObj(list);
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 出库
     * 根据货物类型，产品
     * 获取规格
     *
     * @param saLeaveStockDetailPara
     * @return
     */
    @RequestMapping("/getLeaveStockSpecList.json")
    @ResponseBody
    public Json getLeaveStockSpecList(SaLeaveStockDetailPara saLeaveStockDetailPara) {
        Json json = new Json();
        try {
            List<SaLeaveStockDetailPara> list = saLeaveStockDetailService.getSpec(saLeaveStockDetailPara);
            if (null != list && list.size() != 0) {
                json.setObj(list);
                json.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 出库表
     * 根据货物类型
     * 获取入库记录产品
     *
     * @param isMaterial
     * @return
     */
    @RequestMapping("/getLossStockProductList.json")
    @ResponseBody
    public Json getLossStockProductList(Long isMaterial) {
        Json json = new Json();
        try {
            List<SaLeaveStockDetail> list = saLeaveStockDetailService.getLossProduct(isMaterial);
            if (null != list && list.size() != 0) {
                json.setSuccess(true);
                json.setObj(list);
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 损耗
     * 根据货物类型，产品
     * 获取规格
     *
     * @param saLeaveStockDetailPara
     * @return
     */
    @RequestMapping("/getLossStockSpecList.json")
    @ResponseBody
    public Json getLossStockSpecList(SaLeaveStockDetailPara saLeaveStockDetailPara) {
        Json json = new Json();
        try {
            List<SaLeaveStockDetailPara> list = saLeaveStockDetailService.getLossSpec(saLeaveStockDetailPara);
            if (null != list && list.size() != 0) {
                json.setObj(list);
                json.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 返货记录表
     * 根据货物类型
     * 获取产品列表
     */
    @RequestMapping("/getReturnGoodsProductList.json")
    @ResponseBody
    public Json getReturnGoodsProductList(Long isMaterial) {
        Json json = new Json();
        List<ReturnGoods> list = returnGoodsService.getReturnGoodsProduct(isMaterial);
        if (null != list) {
            json.setSuccess(true);
            json.setObj(list);
        }
        return json;
    }

    /**
     * 联动获取
     * 规格
     */
    @RequestMapping("/getReturnGoodsSpecList.json")
    @ResponseBody
    public Json getReturnGoodsSpecList(ReturnGoodsPara returnGoodsPara) {
        Json json = new Json();
        List<ReturnGoods> list = returnGoodsService.getProductSpec(returnGoodsPara);
        if (null != list) {
            json.setSuccess(true);
            json.setObj(list);
        }
        return json;
    }

    /**
     * 入库记录表格
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/getAllEnterStockDetail.json")
    @ResponseBody
    public Object getAllEnterStockRecord(PuEnterStockDetailPara puEnterStockDetailPara, Integer page, Integer rows) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        List<PuEnterStockDetailPara> puEnterStockDetailParas = puEnterStockDetailService.getAllEnterStockDetail(puEnterStockDetailPara, page, rows, user.getId(), user.getCompanyId());
        PageInfo<PuEnterStockDetailPara> pageInfo = new PageInfo<>(puEnterStockDetailParas);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), puEnterStockDetailParas);
    }

    /**
     * 出库记录表格
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/getAllLeaveStockDetail.json")
    @ResponseBody
    public Object getAllLeaveStockRecord(SaLeaveStockDetailPara saLeaveStockDetailPara, Integer page, Integer rows) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        saLeaveStockDetailPara.setMethod(new Byte("1"));
        List<SaLeaveStockDetailPara> saLeaveStockDetailParas = saLeaveStockDetailService.getAllLeaveStockDetail(saLeaveStockDetailPara, page, rows, user.getId(), user.getCompanyId());
        PageInfo<SaLeaveStockDetailPara> pageInfo = new PageInfo<>(saLeaveStockDetailParas);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), saLeaveStockDetailParas);
    }

    /**
     * 损耗记录表格
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/getAllLossRecord.json")
    @ResponseBody
    public Object getAllLossRecord(SaLeaveStockDetailPara saLeaveStockDetailPara, Integer page, Integer rows) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        saLeaveStockDetailPara.setMethod(new Byte("2"));
        List<SaLeaveStockDetailPara> saLeaveStockDetailParas = saLeaveStockDetailService.getAllLossDetail(saLeaveStockDetailPara, page, rows, user.getId(), user.getCompanyId());
        PageInfo<SaLeaveStockDetailPara> pageInfo = new PageInfo<>(saLeaveStockDetailParas);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), saLeaveStockDetailParas);
    }

    /**
     * 返货记录表格
     * getAllReturnGoodsRecord
     */
    @RequestMapping("/getAllReturnGoodsRecord.json")
    @ResponseBody
    public Object getAllReturnGoodsRecord(ReturnGoodsPara returnGoodsPara, Integer page, Integer rows) {
        List<ReturnGoodsPara> returnGoodsParaList = returnGoodsService.getAllReturnGoodsDetail(returnGoodsPara, page, rows);
        PageInfo<ReturnGoodsPara> paraPageInfo = new PageInfo<>(returnGoodsParaList);
        return new JqGridParam(paraPageInfo.getPageNum(), paraPageInfo.getPageSize(), paraPageInfo.getTotal(), paraPageInfo.getPages(), returnGoodsParaList);
    }

    /**
     * 返货登记
     * 获取出库记录货物类型（非损耗）
     *
     * @return
     */
    @RequestMapping("/returnManageModal.htm")
    public String returnManage(HttpServletRequest request, Long resourceId) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        request.setAttribute("resourceId", resourceId);
        List<SaLeaveStockDetailPara> list = saLeaveStockDetailService.getStockIsMaterial(user.getId(), user.getCompanyId());
        request.setAttribute("returnGoodsType", list);
        return "stockRecord/returnManageModal";
    }

    /**
     * 联动通过返货货物类型
     * 查询产品
     */
    @RequestMapping("/getReturnProduct.json")
    @ResponseBody
    public Object getReturnProduct(Long isMaterial) {
        Json json = new Json();
        List<SaLeaveStockDetail> list = saLeaveStockDetailService.getReturnProduct(isMaterial);
        if (0 != list.size() && null != list) {
            json.setObj(list);
            json.setSuccess(true);
        } else {
            json.setMsg("无数据");
        }
        return json;
    }

    /**
     * 联动通过返货产品id
     * 查询产品
     */
    @RequestMapping("/getReturnProductSpecName.json")
    @ResponseBody
    public Object getReturnProductSpecName(Long productId) {
        Json json = new Json();
        List<SaLeaveStockDetail> list = saLeaveStockDetailService.getReturnProductSpecName(productId);
        if (0 != list.size() && null != list) {
            json.setObj(list);
            json.setSuccess(true);
        } else {
            json.setObj("服务器异常");
        }
        return json;
    }

    /**
     * /exportStockRecord.json
     * 数据导出
     */
    @RequestMapping("/exportStockRecord.json")
    @ResponseBody
    public Object exportStockRecord(HttpServletRequest request, HttpServletResponse response, Long resourceId, Integer[] arr) {
        Json json = new Json();
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        try {
            Boolean boo = puEnterStockDetailService.exportData(request, response, user.getId(), arr, user.getCompanyId());
            if (!boo) {
                json.setMsg("导出失败");
            } else {
                json.setSuccess(true);
                json.setMsg("导出成功");
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("出库记录-入库记录数据导出");
                sysLog.setRemark("出库记录-入库记录数据导出");
                sysLog.setResourceId(resourceId);
                sysLog.setCreatedUserId(user.getId());
                sysSysLogService.addSysSysLog(sysLog);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return json;
    }

    /**
     * 返货数据导出
     */
    @RequestMapping("/returnGoodsExport.json")
    @ResponseBody
    public Object returnGoodsExport(Long returnGoodsDetailId, HttpServletRequest request, HttpServletResponse response, Long resourceId) {
        Json json = new Json();
        Boolean res = returnGoodsService.exportReturnGoods(returnGoodsDetailId, request, response);
        if (res) {
            ReturnGoods re = returnGoodsService.selectById(returnGoodsDetailId);
            json.setSuccess(true);
            json.setMsg("导出成功");
            Subject subject = SecurityUtils.getSubject();
            ShiroUser user = (ShiroUser) subject.getPrincipal();
            SysSysLog sysLog = new SysSysLog();
            String ip = ClientIP.getClientIP(request);
            sysLog.setIpAddress(ip);
            sysLog.setCName("返货数据导出");
            sysLog.setRemark("返货数据导出,导出产品(" + re.getProductName() + ")");
            sysLog.setResourceId(resourceId);
            sysLog.setCreatedUserId(user.getId());
            sysSysLogService.addSysSysLog(sysLog);
        } else {
            json.setMsg("导出失败");
        }
        return json;
    }

    /**
     * /subReturnGoodsRecord.json
     * 提交返货记录登记
     */
    @RequestMapping("/subReturnGoodsRecord.json")
    @ResponseBody
    public Object subReturnGoodsRecord(HttpServletRequest request, ReturnGoodsPara returnGoods, MultipartFile images1, MultipartFile images2, MultipartFile images3, MultipartFile images4, MultipartFile images5, MultipartFile images6) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            returnGoods.setOperator(shiroUser.getName());
            List<ImageJson> imageJsonList = new ArrayList<>();
            if (null != images1) {
                String fileName = ossClientUtil.uploadImg2Oss(images1);
                if ("上传图片大小不能超过2M！".equals(fileName)) {
                    json.setMsg("上传图片大小不能超过2M！");
                    return json;
                }
                String url = ossClientUtil.getImgUrl(fileName);
                ImageJson imageJson = new ImageJson();
                imageJson.setImageName(fileName);
                imageJson.setImageUrl(url);
                imageJsonList.add(imageJson);
            }
            if (null != images2) {
                String fileName = ossClientUtil.uploadImg2Oss(images2);
                if ("上传图片大小不能超过2M！".equals(fileName)) {
                    json.setMsg("上传图片大小不能超过2M！");
                    return json;
                }
                String url = ossClientUtil.getImgUrl(fileName);
                ImageJson imageJson = new ImageJson();
                imageJson.setImageName(fileName);
                imageJson.setImageUrl(url);
                imageJsonList.add(imageJson);
            }
            if (null != images3) {
                String fileName = ossClientUtil.uploadImg2Oss(images3);
                if ("上传图片大小不能超过2M！".equals(fileName)) {
                    json.setMsg("上传图片大小不能超过2M！");
                    return json;
                }
                String url = ossClientUtil.getImgUrl(fileName);
                ImageJson imageJson = new ImageJson();
                imageJson.setImageName(fileName);
                imageJson.setImageUrl(url);
                imageJsonList.add(imageJson);
            }
            if (null != images4) {
                String fileName = ossClientUtil.uploadImg2Oss(images4);
                if ("上传图片大小不能超过2M！".equals(fileName)) {
                    json.setMsg("上传图片大小不能超过2M！");
                    return json;
                }
                String url = ossClientUtil.getImgUrl(fileName);
                ImageJson imageJson = new ImageJson();
                imageJson.setImageName(fileName);
                imageJson.setImageUrl(url);
                imageJsonList.add(imageJson);
            }
            if (null != images5) {
                String fileName = ossClientUtil.uploadImg2Oss(images5);
                if ("上传图片大小不能超过2M！".equals(fileName)) {
                    json.setMsg("上传图片大小不能超过2M！");
                    return json;
                }
                String url = ossClientUtil.getImgUrl(fileName);
                ImageJson imageJson = new ImageJson();
                imageJson.setImageName(fileName);
                imageJson.setImageUrl(url);
                imageJsonList.add(imageJson);
            }
            if (null != images6) {
                String fileName = ossClientUtil.uploadImg2Oss(images6);
                if ("上传图片大小不能超过2M！".equals(fileName)) {
                    json.setMsg("上传图片大小不能超过2M！");
                    return json;
                }
                String url = ossClientUtil.getImgUrl(fileName);
                ImageJson imageJson = new ImageJson();
                imageJson.setImageName(fileName);
                imageJson.setImageUrl(url);
                imageJsonList.add(imageJson);
            }
            if (imageJsonList.size() == 0) {
                returnGoods.setIamges("");
            } else {
                if (imageJsonList.size() < 6) {
                    for (ImageJson im : imageJsonList) {
                        if (null == im) {
                            ImageJson imageJson = new ImageJson();
                            imageJson.setImageName("");
                            imageJson.setImageUrl("");
                            imageJsonList.add(imageJson);
                        }
                    }
                }
                String jsonText = JSON.toJSONString(imageJsonList, true);
                returnGoods.setIamges(jsonText);
            }
            Boolean res = returnGoodsService.newReturnGoods(returnGoods, shiroUser.getId());
            if (res) {
                Long resourceId = returnGoods.getResourceId();
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("新建返货记录");
                sysLog.setRemark("新建返货记录,返货产品(" + returnGoods.getProductName() + ")");
                sysLog.setResourceId(resourceId);
                sysLog.setCreatedUserId(shiroUser.getId());
                sysSysLogService.addSysSysLog(sysLog);
                json.setMsg("新建成功");
                json.setSuccess(true);
            } else {
                json.setMsg("新建失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取入库(返库)打印记录
     *
     * @param puEnterStockDetailPara
     * @return
     */
    @RequestMapping("/getDetail.json")
    @ResponseBody
    public Json getDetail(PuEnterStockDetailPara puEnterStockDetailPara) {
        Json json = new Json();
        PEnterStockAndStock enterStockObj = puEnterStockDetailService.getEnterStockObj(puEnterStockDetailPara);
        if (null != enterStockObj) {
            json.setObj(enterStockObj);
            json.setSuccess(true);
        }
        return json;
    }

    /**
     * 获取返库打印记录
     *
     * @param saLeaveStockDetailPara
     * @return
     */
    @RequestMapping("/getLeaveStockPrint.json")
    @ResponseBody
    public Json getLeaveStockPrint(SaLeaveStockDetailPara saLeaveStockDetailPara) {
        Json json = new Json();
        YhLeaveStock leaveStockDetailObj = saLeaveStockDetailService.getLeaveStockDetailObj(saLeaveStockDetailPara);
        if (null != leaveStockDetailObj) {
            json.setObj(leaveStockDetailObj);
            json.setSuccess(true);
        }
        return json;
    }

}
