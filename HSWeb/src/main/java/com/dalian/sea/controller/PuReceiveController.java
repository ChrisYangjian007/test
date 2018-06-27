package com.dalian.sea.controller;

import com.dalian.sea.ClientIP;
import com.dalian.sea.DTO.TreeJsonDTO;
import com.dalian.sea.common.HandleHelper;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.PuReceive;
import com.dalian.sea.model.SysSysLog;
import com.dalian.sea.model.SysUnit;
import com.dalian.sea.model.ZsEnterprise;
import com.dalian.sea.parameter.PZsCompanyProduct;
import com.dalian.sea.parameter.PuReceiveDetailPa;
import com.dalian.sea.parameter.PuReceiveDetaildPara;
import com.dalian.sea.parameter.PuReceivePara;
import com.dalian.sea.service.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 收货管理
 *
 * @author 杨文波
 * @date 2018/1/23
 */
@Controller
@RequestMapping(value = "/receiveManagement")
@Slf4j
public class PuReceiveController extends LayoutRazor {

    @Autowired
    private PuReceiveDetailService puReceiveDetailService;

    @Autowired
    private BaDataDictionaryDetailsService dataDictionaryDetailsService;

    @Autowired
    private ZsEnterpriseService zsEnterpriseService;

    @Autowired
    private PuReceiveService puReceiveService;

    @Autowired
    private ZsCompanyProductService zsCompanyProductService;

    @Autowired
    private PuReceiveTestService puReceiveTestService;

    @Autowired
    private SysUnitService sysUnitService;

    @Autowired
    private SysSysLogService sysSysLogService;

    /**
     * 收货单
     */
    @RequestMapping(value = "/receipt.htm")
    public String goToReceipt(HttpServletRequest request) {
        log.info(request.getRequestURI());
        return freeMarkerIndexResult("receive/Receipt.ftl", request);
        //return "receive/Receipt";
    }

    /**
     * 收货单，进行中
     *
     * @param puReceive
     * @param page      第几页
     * @param rows      每页容量
     * @return 表格数据
     * @see com.dalian.sea.json.JqGridParam
     */
    @RequestMapping("/getReceiptProcessing.json")
    @ResponseBody
    public Object getReceiptProcessing(PuReceiveDetaildPara puReceive, Integer page, Integer rows, HttpServletRequest request) {
        puReceive.setMethod(new Byte("1"));
        List<PuReceiveDetaildPara> puReceives = puReceiveDetailService.getReceiveCarryOut(puReceive, page, rows);
        PageInfo<PuReceiveDetaildPara> pageInfo = new PageInfo<>(puReceives);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), puReceives);
    }

    /**
     * 收货单，已完成
     *
     * @param puReceive
     * @param page      第几页
     * @param rows      每页容量
     * @return 表格数据
     * @see com.dalian.sea.json.JqGridParam
     */
    @RequestMapping("/getReceiptCarryOut.json")
    @ResponseBody
    public Object getReceiptCarryOut(PuReceiveDetaildPara puReceive, Integer page, Integer rows, HttpServletRequest request) {
        puReceive.setMethod(new Byte("2"));
        List<PuReceiveDetaildPara> puReceives = puReceiveDetailService.getReceiveCarryOut(puReceive, page, rows);
        PageInfo<PuReceiveDetaildPara> pageInfo = new PageInfo<>(puReceives);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), puReceives);
    }

    /**
     * 根据名称获取数据字典下的货物类型
     *
     * @return
     */
    @RequestMapping("/getGoodsType.json")
    @ResponseBody
    public List<PZsCompanyProduct> getGoodsType() {
        List<PZsCompanyProduct> zsCompanyProducts = zsCompanyProductService.getType();
        return zsCompanyProducts;
    }

    /**
     * 根据货物类型获取产品
     *
     * @param pZsCompanyProduct
     * @return
     */
    @RequestMapping("/getProductByType.json")
    @ResponseBody
    public Json getProductByType(PZsCompanyProduct pZsCompanyProduct) {
        Json json = new Json();
        List<PZsCompanyProduct> zsCompanyProducts = zsCompanyProductService.getProductByTypeAndNameAndSpecification(pZsCompanyProduct);
        if (null != zsCompanyProducts && zsCompanyProducts.size() > 0) {
            json.setSuccess(true);
            json.setObj(zsCompanyProducts);
        } else {
            json.setMsg("该类型相关产品查询暂无！");
        }
        return json;
    }

    /**
     * 获取已有的产品规格
     *
     * @param pZsCompanyProduct
     * @return
     */
    @RequestMapping("/getProductSpecName.json")
    @ResponseBody
    public Json getProductSpecName(PZsCompanyProduct pZsCompanyProduct) {
        Json json = new Json();
        List<PZsCompanyProduct> zsCompanyProducts = zsCompanyProductService.getProductByTypeAndNameAndSpecification(pZsCompanyProduct);
        if (null != zsCompanyProducts && zsCompanyProducts.size() > 0) {
            json.setSuccess(true);
            json.setObj(zsCompanyProducts);
        } else {
            json.setMsg("该类型相关产品规格查询暂无！");
        }
        return json;
    }

    /**
     * 新建收货单
     */
    @RequestMapping("/addReceiveModal.htm")
    public String addReceiveModal(HttpServletRequest request) {
        log.info(request.getRequestURI());
        List<ZsEnterprise> enterprises = zsEnterpriseService.getEnterprise();
        if (null != enterprises && enterprises.size() > 0) {
            request.setAttribute("enterprises", enterprises);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date now = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("NO.SH");
        sb.append(sdf.format(now));
        sb.append(new Random().nextInt(99));
        request.setAttribute("receiveNode", sb.toString());
        return "receive/addReceiveModal";
    }

    /**
     * 编辑
     */
    @RequestMapping("/updateReceiveModal.htm")
    public String updateReceiveModal(HttpServletRequest request, String receiveDetailIds, Long resourceId) {
        request.setAttribute("resourceId", resourceId);
        String[] strs = receiveDetailIds.split(",");
        List<Long> idList = new ArrayList<>();
        for (String s : strs) {
            Long id = Long.parseLong(s);
            idList.add(id);
        }
        List<PuReceiveDetaildPara> puReceiveDetaildParaList = puReceiveDetailService.getReceiveByReceiveDetailId(idList);
        request.setAttribute("puReceiveDetaildParaList", puReceiveDetaildParaList);
        List<ZsEnterprise> enterprises = zsEnterpriseService.getEnterprise();
        if (null != enterprises && enterprises.size() > 0) {
            request.setAttribute("enterprises", enterprises);
        }
        List<SysUnit> unitList = sysUnitService.getSysUnitNoZero();
        if (null != unitList && 0 != unitList.size()) {
            request.setAttribute("units", unitList);
        }
        return "receive/updateReceiveModal";
    }

    /**
     * 修改确认
     */
    @RequestMapping("/updateReceive.json")
    @ResponseBody
    public Object updateReceive(@RequestBody List<PuReceiveDetailPa> puReceiveDetailPas, HttpServletRequest request) {
        Json json = new Json();
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        Boolean res = puReceiveDetailService.editReceive(puReceiveDetailPas, user.getId());
        if (res) {
            Long resourceId = puReceiveDetailPas.get(0).getResourceId();
            SysSysLog sysLog = new SysSysLog();
            String ip = ClientIP.getClientIP(request);
            sysLog.setIpAddress(ip);
            sysLog.setCName("编辑收货单");
            PuReceive receiveByReceiveId = puReceiveService.getReceiveByReceiveId(puReceiveDetailPas.get(0).getReceiveId());
            sysLog.setRemark("编辑了收货编号为(" + receiveByReceiveId.getReceiveNo() + ")的记录");
            sysLog.setResourceId(resourceId);
            sysLog.setCreatedUserId(user.getId());
            sysSysLogService.addSysSysLog(sysLog);
            json.setSuccess(true);
            json.setMsg("修改成功");
        } else {
            json.setMsg("修改失败");
        }
        return json;
    }

    /**
     * 报送检验
     */
    @RequestMapping("/checkReceive.json")
    @ResponseBody
    public Object checkReceiveModal(@RequestBody List<PuReceiveDetailPa> puReceiveDetails, HttpServletRequest request) {
        Json json = new Json();
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        Boolean res = puReceiveTestService.checkReceiveDetail(puReceiveDetails);
        if (res) {
            Long resourceId = puReceiveDetails.get(0).getResourceId();
            SysSysLog sysLog = new SysSysLog();
            String ip = ClientIP.getClientIP(request);
            sysLog.setIpAddress(ip);
            sysLog.setCName("报送检验");
            if (1 == puReceiveDetails.size()) {
                PuReceive receiveByReceiveId = puReceiveService.getReceiveByReceiveId(puReceiveDetails.get(0).getReceiveId());
                sysLog.setRemark("报送了收货编号为(" + receiveByReceiveId.getReceiveNo() + ")的记录");
            } else {
                sysLog.setRemark("报送了多条记录");
            }
            sysLog.setResourceId(resourceId);
            sysLog.setCreatedUserId(user.getId());
            sysSysLogService.addSysSysLog(sysLog);
            json.setMsg("报送成功");
            json.setSuccess(true);
        } else {
            json.setMsg("报送失败！请选择未报送的记录");
        }
        return json;
    }

    /**
     * 跳转退货页面
     *
     * @return
     */
    @RequestMapping("/returnGoodsModal.htm")
    public String returnGoodsModal(HttpServletRequest request, String receiveDetailIds, Long resourceId) {
        request.setAttribute("resourceId", resourceId);
        String[] strs = receiveDetailIds.split(",");
        List<Long> idList = new ArrayList<>();
        for (String s : strs) {
            Long id = Long.parseLong(s);
            idList.add(id);
        }
        List<PuReceiveDetaildPara> puReceiveDetaildParaList = puReceiveDetailService.getReceiveByReceiveDetailId(idList);
        if (puReceiveDetaildParaList.size() > 0) {
            request.setAttribute("puReceiveDetaildParaList", puReceiveDetaildParaList);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date now = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("NO.TH");
        sb.append(sdf.format(now));
        sb.append(new Random().nextInt(99));
        request.setAttribute("receiveNode", sb.toString());
        return "receive/returnGoodsModal";
    }

    /**
     * 确认退货
     *
     * @param puReceiveDetailPas
     * @return
     */
    @RequestMapping("/returnGoods.json")
    @ResponseBody
    public Object returnGoods(@RequestBody List<PuReceiveDetailPa> puReceiveDetailPas, HttpServletRequest request) {
        Json json = new Json();
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        Json res = puReceiveDetailService.returnGoods(puReceiveDetailPas, user.getId());
        if (res.isSuccess()) {
            String idStr = res.getObj().toString();
            json.setObj(idStr);
            Long resourceId = puReceiveDetailPas.get(0).getResourceId();
            SysSysLog sysLog = new SysSysLog();
            String ip = ClientIP.getClientIP(request);
            sysLog.setIpAddress(ip);
            sysLog.setCName("退货");
            if (1 == puReceiveDetailPas.size()) {
                PuReceive receiveByReceiveId = puReceiveService.getReceiveByReceiveId(puReceiveDetailPas.get(0).getReceiveId());
                sysLog.setRemark("退货了收货编号为(" + receiveByReceiveId.getReceiveNo() + ")的记录");
            } else {
                sysLog.setRemark("退货了多条记录");
            }
            sysLog.setResourceId(resourceId);
            sysLog.setCreatedUserId(user.getId());
            sysSysLogService.addSysSysLog(sysLog);
            json.setMsg("退货成功");
            json.setSuccess(true);
        } else {
            json.setMsg("退货失败");
        }
        return json;
    }

    /**
     * 查看检验报告
     *
     * @param receiveDetailId
     * @return
     */
    @RequestMapping("/checkReportModal.json")
    @ResponseBody
    public Json checkReport(Long receiveDetailId) {
        Json json = new Json();
        String str = puReceiveTestService.getImageById(receiveDetailId);
        if (null != str && "" != str) {
            json.setSuccess(true);
            json.setObj(str);
        } else {
            json.setMsg("未上传检验报告");
        }
        return json;
    }

    /**
     * 跳转数据导出页面
     *
     * @return
     */
    @RequestMapping("/exportDataModal.htm")
    public String exportDataModal() {
        return "receive/exportDataModal";
    }

    /**
     * 数据导出
     * 进行中
     */
    @RequestMapping("/exportData.json")
    @ResponseBody
    public Object exportDate(PuReceiveDetaildPara puReceive, HttpServletRequest request, HttpServletResponse response, Long resourceId, Integer[] arr) {
        Json json = new Json();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Subject subject = SecurityUtils.getSubject();
            ShiroUser user = (ShiroUser) subject.getPrincipal();
            Boolean res = puReceiveDetailService.exportData(puReceive, request, response, arr);
            if (res) {
                SysSysLog sysLog = new SysSysLog();
                String ip = ClientIP.getClientIP(request);
                sysLog.setIpAddress(ip);
                sysLog.setCName("收货单数据导出");
                sysLog.setRemark("收货单数据导出(" + format.format(puReceive.getBeginTime()) + "——" + format.format(puReceive.getEndTime()) + ")");
                sysLog.setResourceId(resourceId);
                sysLog.setCreatedUserId(user.getId());
                sysSysLogService.addSysSysLog(sysLog);
                json.setMsg("数据导出成功");
                json.setSuccess(true);
            } else {
                json.setMsg("数据导出失败");
            }
        } catch (Exception e) {
            json.setObj(e);
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 删除
     */
    @RequestMapping("/deleteReceive.json")
    @ResponseBody
    public Object deleteReceive(@RequestBody List<PuReceiveDetailPa> receiveDetails, HttpServletRequest request) {
        Json json = new Json();
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        Boolean res = puReceiveService.deleteReceiveById(receiveDetails, user.getId());
        if (res) {
            Long resourceId = receiveDetails.get(0).getResourceId();
            SysSysLog sysLog = new SysSysLog();
            String ip = ClientIP.getClientIP(request);
            sysLog.setIpAddress(ip);
            sysLog.setCName("删除");
            if (1 == receiveDetails.size()) {
                //根据detailId查退货编号
                String returnNo = puReceiveDetailService.getByReceiveDetailId(receiveDetails.get(0).getReceiveDetailId()).getReturnNo();
                if (null != returnNo) {
                    sysLog.setRemark("删除了退货编号为(" + returnNo + ")的记录");
                } else {
                    PuReceive receiveByReceiveId = puReceiveService.getReceiveByReceiveId(receiveDetails.get(0).getReceiveId());
                    sysLog.setRemark("删除了收货编号为(" + receiveByReceiveId.getReceiveNo() + ")的记录");
                }
            } else {
                sysLog.setRemark("删除了多条记录");
            }
            sysLog.setResourceId(resourceId);
            sysLog.setCreatedUserId(user.getId());
            sysSysLogService.addSysSysLog(sysLog);
            json.setMsg("删除成功");
            json.setSuccess(true);
        } else {
            json.setMsg("删除失败");
        }
        return json;
    }

    /**
     * 检验室
     */
    @RequestMapping(value = "/laboratory.htm")
    public String goToLaboratory(HttpServletRequest request) {
        log.info(request.getRequestURI());
        return "receive/Laboratory";
    }

    @PostMapping(value = "/insertReceive.json")
    @ResponseBody
    public Json insertReceive(HttpServletRequest request, @RequestBody PuReceivePara puReceivePara, Long resourceId) {
        Json json = new Json();
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        Long res = puReceiveDetailService.insertReceiveDetail(puReceivePara, user.getId());
        if (null != res) {
            String detailsId = puReceiveDetailService.getDetailForInsertReceive(res);
            if (null != detailsId) {
                json.setSuccess(true);
                json.setMsg("插入成功");
                json.setObj(detailsId);
            } else {
                json.setSuccess(true);
                json.setMsg("插入成功");
                json.setObj("");
            }
            SysSysLog sysLog = new SysSysLog();
            String ip = ClientIP.getClientIP(request);
            sysLog.setIpAddress(ip);
            sysLog.setCName("新建收货单");
            sysLog.setResourceId(resourceId);
            sysLog.setCreatedUserId(user.getId());
            sysLog.setRemark("新建收货单：" + puReceivePara.getReceiveNo());
            sysSysLogService.addSysSysLog(sysLog);
        } else {
            json.setMsg("插入失败");
        }
        return json;
    }

    /**
     * 获取供应商
     *
     * @return
     */
    @RequestMapping("/getEnterprise.json")
    @ResponseBody
    public Json getEnterprise() {
        Json json = new Json();
        List<ZsEnterprise> enterprises = zsEnterpriseService.getEnterprise();
        if (null != enterprises && enterprises.size() > 0) {
            json.setSuccess(true);
            json.setObj(enterprises);
        } else {
            json.setMsg("查询暂无");
        }
        return json;
    }

    /**
     * 获取单位
     *
     * @return
     */
    @RequestMapping("/getUnitName.json")
    @ResponseBody
    public Object getUnitName() {
        List<TreeJsonDTO> treeList = new ArrayList<>();
        try {
            List<SysUnit> unitList = sysUnitService.getSysUnitNoZero();
            if (null != unitList && 0 != unitList.size()) {
                for (SysUnit item : unitList) {
                    TreeJsonDTO tree = new TreeJsonDTO();
                    tree.setId(String.valueOf(item.getUnitId()));
                    tree.setText(item.getCName());
                    tree.setValue(String.valueOf(item.getUnitId()));
                    tree.setAttribute("alevel");
                    tree.setAttributeValue(String.valueOf(item.getALevel()));
                    tree.setShowcheck(false);
                    tree.setIsexpand(true);
                    tree.setComplete(true);
                    tree.setParentId(String.valueOf(item.getParentId()));
                    tree.setHasChildren(unitList.stream().filter(t -> Objects.equals(t.getParentId(), item.getUnitId())).count() > 0);
                    tree.setImg(tree.getHasChildren() ? "/images/Icon16/molecule.png" : "/images/Icon16/hostname.png");
                    treeList.add(tree);
                }
            }
        } catch (Exception e) {
            log.info(String.valueOf(e));
        }
        return HandleHelper.TreeToJson2(treeList, "1");
    }

}
