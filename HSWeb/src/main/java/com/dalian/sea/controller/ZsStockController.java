package com.dalian.sea.controller;

import com.alibaba.fastjson.JSON;
import com.dalian.sea.ClientIP;
import com.dalian.sea.config.shiro.ShiroUser;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.*;
import com.dalian.sea.parameter.*;
import com.dalian.sea.py4j.PinyinConverter;
import com.dalian.sea.service.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/2/24.
 */
@Slf4j
@Controller
@RequestMapping(value = "/stock")
public class ZsStockController extends LayoutRazor {
    @Autowired
    private ZsWarehouseService zsWarehouseService;
    @Autowired
    private ZsStockService zsStockService;
    @Autowired
    private ZsProduceTaskService zsProduceTaskService;
    @Autowired
    private BaDataDictionaryDetailsService baDataDictionaryDetailsService;
    @Autowired
    private PuReceiveDetailService puReceiveDetailService;

    @Autowired
    private SaLeaveStockService saLeaveStockService;

    @Autowired
    private SaLeaveStockDetailService saLeaveStockDetailService;

    @Autowired
    private ZsWorkFlowService zsWorkFlowService;

    @Autowired
    private PinyinConverter pinyinConverter;

    @Autowired
    private ZsTaskDetailValueService zsTaskDetailValueService;

    @Autowired
    private ZsProduceTaskDetailService zsProduceTaskDetailService;

    @Autowired
    private ZsQrCodeService zsQrCodeService;

    @Autowired
    private SysUnitService sysUnitService;

    @Autowired
    private ZsWorkProcessService zsWorkProcessService;

    @Autowired
    private BaFormAttributeService baFormAttributeService;

    @Autowired
    private BaUserService baUserService;

    @Autowired
    private SysSysLogService sysSysLogService;

    /**
     * 库存管理
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/stockManage.htm")
    public String stockManager(HttpServletRequest request, Integer isShowModal) {
        Subject user = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
        if (null == isShowModal) {
            isShowModal = 0;
        }
        request.setAttribute("isShowModal", isShowModal);
        List<ZsWarehouse> warehouseList = new ArrayList<>();
        if (shiroUser.getId() != null) {
            warehouseList = zsWarehouseService.getWareHouseByUserId(shiroUser.getId());
        }
        request.setAttribute("warehouseList", warehouseList);
        return freeMarkerIndexResult("stock/stockManage.ftl", request);
    }

    /**
     * 加载表格
     */
    @RequestMapping("/GridJson.json")
    @ResponseBody
    public Object GridJson(PZsStock pZsStock, Integer page, Integer rows) {
        List<PZsStock> stockList = zsStockService.getZsStockByGrid(pZsStock, page, rows);
        if (stockList != null && !stockList.isEmpty()) {
            for (PZsStock stock : stockList) {
                Long dateLast = stock.getDateLast();
                if (stock.getDateLast() != null) {
                    if (dateLast <= 0) {
                        stock.setIsWarning(new Byte("1"));
                    } else {
                        stock.setIsWarning(new Byte("2"));
                    }
                } else {
                    stock.setIsWarning(new Byte("1"));
                }
                if (stock.getStockWarn() != null) {
                    if (stock.getWeight().intValue() <= stock.getStockWarn()) {
                        stock.setIsStockWarning(new Byte("1"));
                    } else {
                        stock.setIsStockWarning(new Byte("2"));
                    }
                } else {
                    stock.setIsStockWarning(new Byte("2"));
                }
            }
        }
        PageInfo<PZsStock> pageInfo = new PageInfo<>(stockList);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), stockList);
    }

    /**
     * 仓库数据导出
     */
    @RequestMapping("/exportData.json")
    @ResponseBody
    public Object warehouseExportData(HttpServletRequest request, HttpServletResponse response, Long resourceId) {
        Json json = new Json();
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        Boolean res = zsStockService.warehouseExport(request, response, user.getId());
        if (res) {
            SysSysLog sysLog = new SysSysLog();
            String ip = ClientIP.getClientIP(request);
            sysLog.setIpAddress(ip);
            sysLog.setCName("导出库存数据");
            sysLog.setRemark("导出库存数据");
            sysLog.setResourceId(resourceId);
            sysLog.setCreatedUserId(user.getId());
            sysSysLogService.addSysSysLog(sysLog);
            json.setMsg("导出成功");
            json.setSuccess(true);
        } else {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取仓库
     */
    @RequestMapping("/getWarehouse.json")
    @ResponseBody
    public Json getWarehouse() {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        Json json = new Json();
        try {
            List<PZsStock> warehouseList = zsStockService.getWarehouse(user.getId(), user.getCompanyId());
            if (warehouseList != null && !warehouseList.isEmpty()) {
                json.setObj(warehouseList);
                json.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 库存是否有数据（除活参库）
     */
    @RequestMapping("/countDetail.json")
    @ResponseBody
    public Json countDetail() {
        Json json = new Json();
        Integer count = zsStockService.countDetail();
        if (count > 0) {
            json.setSuccess(true);
        } else {
            json.setMsg("库存中暂无产品，无法出库");
        }
        return json;
    }

    /**
     * 通过仓库id获取货物类型
     *
     * @param warehouseId
     * @return
     */
    @RequestMapping("/getGoodsTypeByWarehouseId.json")
    @ResponseBody
    public Object getGoodsTypeByWarehouseId(Long warehouseId) {
        Json json = new Json();
        try {
            List<PZsStock> list = zsStockService.getGoodsTypeByWarehouseId(warehouseId);
            if (null != list && list.size() != 0) {
                json.setObj(list);
                json.setSuccess(true);
            }
        } catch (Exception e) {
            json.setMsg("服务器异常，请刷新");
        }
        return json;
    }

    /**
     * 通过仓库id,货物类型id,获取产品名称
     *
     * @param pZsStock
     * @return
     */
    @RequestMapping("/getProduct.json")
    @ResponseBody
    public Object getProduct(@RequestBody PZsStock pZsStock) {
        Json json = new Json();
        try {
            List<PZsStock> list = zsStockService.getProductName(pZsStock);
            if (null != list && list.size() != 0) {
                json.setObj(list);
                json.setSuccess(true);
            } else {
                json.setMsg("请先选择仓库");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常，请刷新");
        }
        return json;
    }

    /**
     * 库存管理筛选联动：通过仓库id,货物类型id,产品id
     * 获取入库规格
     *
     * @param pZsStock
     * @return
     */
    @RequestMapping("/getProductStatusListForStock.json")
    @ResponseBody
    public Json getProductStatusListForStock(PZsStock pZsStock) {
        Json json = new Json();
        List<PZsStock> list = zsStockService.getProductSpecForStock(pZsStock);
        if (null != list && list.size() != 0) {
            json.setObj(list);
            json.setSuccess(true);
        } else {
            json.setMsg("服务器异常，请刷新");
        }
        return json;
    }

    /**
     * 通过仓库id,货物类型id,产品id
     * 获取产品状态
     *
     * @param pZsStock
     * @return
     */
    @RequestMapping("/getProductStatus.json")
    @ResponseBody
    public Object getProductStatus(@RequestBody PZsStock pZsStock) {
        Json json = new Json();
        try {
            List<PZsStock> list = zsStockService.getProductStatus(pZsStock);
            if (null != list && list.size() != 0) {
                json.setObj(list);
                json.setSuccess(true);
            } else {
                json.setMsg("请先选择货物类型");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常，请刷新");
        }
        return json;
    }

    /**
     * 联动获取规格
     *
     * @param pZsStock
     * @return
     */
    @RequestMapping("/getProductSpec.json")
    @ResponseBody
    public Object getProductSpec(@RequestBody PZsStock pZsStock) {
        Json json = new Json();
        try {
            List<PZsStock> list = zsStockService.getProductSpec(pZsStock);
            if (null != list && list.size() != 0) {
                json.setObj(list);
                json.setSuccess(true);
            } else {
                json.setMsg("请先选择产品名称");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常，请刷新");
        }
        return json;
    }

    /**
     * 联动获取批次号
     *
     * @param pZsStock
     * @return
     */
    @RequestMapping("/getProductBatch.json")
    @ResponseBody
    public Object getProductBatch(@RequestBody PZsStock pZsStock) {
        Json json = new Json();
        try {
            List<PZsStock> list = zsStockService.getBatchNo(pZsStock);
            if (null != list && list.size() != 0) {
                json.setObj(list);
                json.setSuccess(true);
            } else {
                json.setMsg("请先选择产品状态");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常，请刷新");
        }
        return json;
    }

    /**
     * 联动获取单位，库存
     *
     * @param pZsStock
     * @return
     */
    @RequestMapping("/getOthers.json")
    @ResponseBody
    public Object getOthers(@RequestBody PZsStock pZsStock) {
        Json json = new Json();
        try {
            PZsStock p = zsStockService.getUnitAndWeight(pZsStock);
            if (null != p) {
                json.setObj(p);
                json.setSuccess(true);
            } else {
                json.setMsg("请先选择规格");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常，请刷新");
        }
        return json;
    }

    /**
     * 根据出库单编号
     * 获取生产任务编号，名称
     *
     * @param leaveNo
     * @return
     */
    @RequestMapping("/geLeaveRecordByLeaveNo.json")
    @ResponseBody
    public Object getTaskByLeaveNo(String leaveNo) {
        Json json = new Json();
        try {
            SaLeaveStock leaveStockByLeaveNo = saLeaveStockService.getLeaveStockByLeaveNo(leaveNo);
            if (null != leaveStockByLeaveNo) {
                Byte res = zsProduceTaskService.getProduceTaskStatusById(leaveStockByLeaveNo.getProduceTaskId());
                if (1 == res) {
                    json.setSuccess(true);
                    json.setObj(leaveStockByLeaveNo);
                } else {
                    json.setMsg("生产任务已结束");
                }
            } else {
                json.setMsg("编号不存在");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 通过出库编号
     * 搜索出库记录
     * 回显
     */
    @RequestMapping("/showRecord.json")
    @ResponseBody
    public Object showRecord(String leaveNo) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        Json json = new Json();
        try {
            SaLeaveStock leaveStockByLeaveNo = saLeaveStockService.getLeaveStockByLeaveNo(leaveNo);
            if (null != leaveStockByLeaveNo) {
                Long produceTaskId = leaveStockByLeaveNo.getProduceTaskId();
                Byte res = zsProduceTaskService.getProduceTaskStatusById(produceTaskId);
                if (1 == res) {
                    List<PZsProduceTaskDetail> list = zsProduceTaskDetailService.getProduceTaskDetailByProduceTaskId(produceTaskId, user.getId(), user.getCompanyId());
                    json.setObj(list);
                    json.setSuccess(true);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 搜索生产任务编号回显
     */
    @RequestMapping("/showRecordByProduceTaskNoByEnterStock.json")
    @ResponseBody
    public Object showRecordByProduceTaskNoByEnterStock(String produceTaskNo) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        Json json = new Json();
        try {
            //通过生产任务编号获取生产任务
            ZsProduceTask zsProduceTask = zsProduceTaskService.getProduceTaskByNo(produceTaskNo);
            if (null != zsProduceTask) {
                Long produceTaskId = zsProduceTask.getProduceTaskId();
                Byte res = zsProduceTaskService.getProduceTaskStatusById(produceTaskId);
                if (1 == res) {
                    List<PZsProduceTaskDetail> list = zsProduceTaskDetailService.getProduceTaskDetailByProduceTaskId(produceTaskId, user.getId(), user.getCompanyId());
                    json.setObj(list);
                    json.setSuccess(true);
                } else {
                    json.setMsg("该生产任务已结束");
                }
            } else {
                json.setMsg("该编号不存在");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 搜索生产任务编号回显
     */
    @RequestMapping("/showRecordByProduceTaskNo.json")
    @ResponseBody
    public Object showRecordByProduceTaskNo(String produceTaskNo) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        Json json = new Json();
        try {
            //通过生产任务编号获取生产任务
            ZsProduceTask zsProduceTask = zsProduceTaskService.getProduceTaskByNo(produceTaskNo);
            if (null != zsProduceTask) {
                Long produceTaskId = zsProduceTask.getProduceTaskId();
                Byte res = zsProduceTaskService.getProduceTaskStatusById(produceTaskId);
                if (1 == res) {
                    List<PZsProduceTaskDetail> list = zsProduceTaskDetailService.getProduceTaskDetailByProduceTaskId(produceTaskId, user.getId(), user.getCompanyId());
                    json.setObj(list);
                    json.setSuccess(true);
                }
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 根据二维码
     * 回显生产任务
     */
    @RequestMapping("/getProduceTaskByQrCode.json")
    @ResponseBody
    public Object getProduceTaskByQrCode(String qrCode) {
        Json json = new Json();
        try {
            //通过qrCode查produceTask
            ZsQrCode qrCodeByQrCode = zsQrCodeService.getQrCodeByQrCode(qrCode);
            if (null != qrCodeByQrCode) {
                if (null != qrCodeByQrCode.getCurrentProduceTaskId()) {
                    json.setSuccess(true);
                    json.setObj(qrCodeByQrCode);
                } else {
                    json.setMsg("二维码未绑定生产任务");
                }
            } else {
                json.setMsg("编号不存在");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 通过二维码
     * 获取workFlowId
     *
     * @param qrCode
     * @return
     */
    @RequestMapping("/getWorkFlowIdByQrCode.json")
    @ResponseBody
    public Object getWorkFlowIdByQrCode(String qrCode) {
        Json json = new Json();
        try {
            ZsQrCode qrCodeByQrCode = zsQrCodeService.getQrCodeByQrCode(qrCode);
            if (null != qrCodeByQrCode) {
                if (null != qrCodeByQrCode.getCurrentProduceTaskId()) {
                    Long produceTaskId = qrCodeByQrCode.getCurrentProduceTaskId();
                    Long id = zsTaskDetailValueService.getWorkFlowId(produceTaskId);
                    json.setSuccess(true);
                    json.setObj(id);
                }
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 通过二维码回显详情
     */
    @RequestMapping("/showRecordByQrCode.json")
    @ResponseBody
    public Object showRecordByQrCode(String qrCode) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        Json json = new Json();
        try {
            ZsQrCode qrCodeByQrCode = zsQrCodeService.getQrCodeByQrCode(qrCode);
            if (null != qrCodeByQrCode) {
                if (null != qrCodeByQrCode.getCurrentProduceTaskId()) {
                    Long produceTaskId = qrCodeByQrCode.getCurrentProduceTaskId();
                    List<PZsProduceTaskDetail> list = zsProduceTaskDetailService.getProduceTaskDetailByProduceTaskId(produceTaskId, user.getId(), user.getCompanyId());
                    json.setObj(list);
                    json.setSuccess(true);
                }
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 选中回显详情
     */
    @RequestMapping("/showRecordBySelect.json")
    @ResponseBody
    public Object showRecordBySelect(String[] stockIds) {
        Json json = new Json();
        try {
            List<PZsStock> stockList = zsStockService.getStockByStockIds(stockIds);
            if (null != stockList && !stockList.isEmpty()) {
                json.setSuccess(true);
                json.setObj(stockList);
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 活参库选中回显详情
     */
    @RequestMapping("/showRecordBySelectForSeaCucumber.json")
    @ResponseBody
    public Object showRecordBySelectForSeaCucumber(String[] stockIds) {
        Json json = new Json();
        try {
            List<PZsStock> stockList = zsStockService.getStockByStockIDs(stockIds);
            if (null != stockList && !stockList.isEmpty()) {
                json.setSuccess(true);
                json.setObj(stockList);
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 跳转选择生产任务
     *
     * @return
     */
    @RequestMapping("/workProcessNameModal.htm")
    public String workProcessNameModal(HttpServletRequest request) {
        List<ZsWorkFlow> parentOneList = zsWorkFlowService.getWorkFlowByParentId((long) 1);
        request.setAttribute("parentOneList", parentOneList);
        return "stock/productTask";
    }

    /**
     * 获取父级
     *
     * @return
     */
    @RequestMapping("/getParent.json")
    @ResponseBody
    public Object getParent() {
        List<ZsWorkFlow> parentOneList = zsWorkFlowService.getWorkFlowByParentId((long) 1);
        return parentOneList;
    }

    /**
     * 生产任务选择
     *
     * @param parentId
     * @return
     */
    @RequestMapping("/getchildA.json")
    @ResponseBody
    public Object getchildA(Long parentId) {
        List<ZsWorkFlow> list = zsWorkFlowService.getWorkFlowByParentId(parentId);
        return list;
    }

    @RequestMapping("/getchildB.json")
    @ResponseBody
    public Object getchildB(Long parentId) {
        List<ZsWorkFlow> list = zsWorkFlowService.getWorkFlowByParentId(parentId);
        return list;
    }

    @RequestMapping("/getchildC.json")
    @ResponseBody
    public Object getchildC(Long parentId) {
        List<ZsWorkFlow> list = zsWorkFlowService.getWorkFlowByParentId(parentId);
        return list;
    }

    /**
     * 是否有工艺详情
     *
     * @return
     */
    @RequestMapping("/isHasProcess.json")
    @ResponseBody
    public Object isHasProcess(Long workFlowId) {
        Json json = new Json();
        try {
            List<ZsWorkFlow> list = zsWorkFlowService.getProduceTaskByWorkFlow(workFlowId);
            if (list != null && list.size() != 0) {
                json.setSuccess(true);
            } else {
                json.setSuccess(false);
                json.setMsg("该工艺无工艺详情");
            }
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg("服务器异常");
        }
        return json;
    }

    @RequestMapping("/getLoss.json")
    @ResponseBody
    public Object getLoss() {
        Json json = new Json();
        List<PBaDataDictionaryDetails> list = baDataDictionaryDetailsService.getBaDataDictionaryDetailsByDataDictionaryIdWithOutPage(Long.valueOf(19));
        if (null != list && list.size() != 0) {
            json.setObj(list);
            json.setSuccess(true);
        } else {
            json.setMsg("服务器异常，请刷新");
        }
        return json;
    }

    /**
     * 确认新建出库
     *
     * @return
     */
    @RequestMapping("/insertLeaveStock.json")
    @ResponseBody
    public Object insertLeaveStock(@RequestBody YhLeaveStock yhLeaveStock, HttpServletRequest request) {
        Json json = new Json();
        try {
            Subject subject = SecurityUtils.getSubject();
            ShiroUser user = (ShiroUser) subject.getPrincipal();
            List<ZsQrCode> qrCodeList = new ArrayList<>();
            if (null != yhLeaveStock.getProduceTaskTag() && !"".equals(yhLeaveStock.getProduceTaskTag())) {
                String[] strs = yhLeaveStock.getProduceTaskTag().split(",");
                json = zsQrCodeService.isExistenceAndUse(strs);
                qrCodeList = (List<ZsQrCode>) json.getObj();
            } else {
                json.setSuccess(true);
            }
            if (json.isSuccess()) {
                json = saLeaveStockDetailService.newLeaveStock(yhLeaveStock, user.getId(), user.getName(), qrCodeList);
                if (json.isSuccess()) {
                    Long resourceId = yhLeaveStock.getResourceId();
                    SysSysLog sysLog = new SysSysLog();
                    String ip = ClientIP.getClientIP(request);
                    sysLog.setIpAddress(ip);
                    sysLog.setCName("新建出库");
                    sysLog.setRemark("新建出库,出库编号为:" + yhLeaveStock.getLeaveNo());
                    sysLog.setResourceId(resourceId);
                    sysLog.setCreatedUserId(user.getId());
                    sysSysLogService.addSysSysLog(sysLog);
                    json.setMsg("出库成功");
                    json.setSuccess(true);
                    YhLeaveStock yh = (YhLeaveStock) json.getObj();
                    Boolean res = zsProduceTaskDetailService.TaskDetailByProduceTaskId(yh.getProduceTaskId());
                    if (res) {
                        zsProduceTaskService.updateTaskStatusById(yh.getProduceTaskId(), user.getId());
                        SysSysLog sysLog1 = new SysSysLog();
                        sysLog1.setIpAddress(ip);
                        sysLog1.setCName("新建出库");
                        sysLog1.setRemark("解绑二维码:" + yhLeaveStock.getProduceTaskTag() + ";结束生产任务:" + yhLeaveStock.getProduceTaskNo());
                        sysLog1.setResourceId(resourceId);
                        sysLog1.setCreatedUserId(user.getId());
                        sysSysLogService.addSysSysLog(sysLog1);
                    }
                } else {
                    if (null != json.getMsg()) {
                        json.setSuccess(false);
                        json.setMsg("服务器异常！请刷新");
                    } else {
                        json.setSuccess(false);
                        json.setMsg("出库失败,请检查是否填写正确！");
                    }
                }
            }
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg("服务器异常！请刷新");
        }
        return json;
    }

    /**
     * 通过生产任务编号
     * 查workFlowId
     *
     * @return
     */
    @RequestMapping("/getWorkFlowIdByNo.json")
    @ResponseBody
    public Object getWorkFlowId(String produceTaskNo) {
        Json json = new Json();
        ZsProduceTask produceTaskByNo = zsProduceTaskService.getProduceTaskByNo(produceTaskNo);
        if (null != produceTaskByNo) {
            Byte res = zsProduceTaskService.getProduceTaskStatusById(produceTaskByNo.getProduceTaskId());
            if (1 == res) {
                json.setSuccess(true);
                Long id = zsTaskDetailValueService.getWorkFlowId(produceTaskByNo.getProduceTaskId());
                json.setObj(id);
            }
        }
        return json;
    }

    /**
     * 根据出库编号获取workFlowId
     *
     * @param leaveNo
     * @return
     */
    @RequestMapping("/getWorkFlowIdById.json")
    @ResponseBody
    public Object getWorkFlowIdById(String leaveNo) {
        Json json = new Json();
        try {
            SaLeaveStock leaveStock = saLeaveStockService.getLeaveStockByLeaveNo(leaveNo);
            if (null != leaveStock) {
                Long produceTaskId = leaveStock.getProduceTaskId();
                Byte res = zsProduceTaskService.getProduceTaskStatusById(produceTaskId);
                if (1 == res) {
                    Long id = zsTaskDetailValueService.getWorkFlowId(produceTaskId);
                    json.setSuccess(true);
                    json.setObj(id);
                }
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 自动生成生产任务编号
     */
    @RequestMapping("/getProduceTaskNo.json")
    @ResponseBody
    public Object getProduceTaskNo(Long workFlowId) {
        Json json = new Json();
        //数字map
        Map<Character, Character> numMap = new HashMap<>(10);
        numMap.put('0', '零');
        numMap.put('1', '一');
        numMap.put('2', '二');
        numMap.put('3', '三');
        numMap.put('4', '四');
        numMap.put('5', '五');
        numMap.put('6', '六');
        numMap.put('7', '七');
        numMap.put('8', '八');
        numMap.put('9', '九');
        //中文
        String zw = zsWorkFlowService.getWorkFlowNameById(workFlowId);
        //将阿拉伯数字转为中文数字
        char[] numchar = zw.toCharArray();
        for (int i = 0; i < numchar.length; i++) {
            if (numchar[i] >= '0' && numchar[i] <= '9') {
                char a = numMap.get(numchar[i]);
                Arrays.fill(numchar, i, i + 1, a);
            }
        }
        StringBuilder builder = new StringBuilder();
        try {
            //拼音
            String py = pinyinConverter.getPinyin(String.valueOf(numchar));
            char[] chars = py.toCharArray();
            //取大写字母
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (c >= 'A' && c <= 'Z') {
                    builder.append(chars[i]);
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
            Date now = new Date();
            //添加时间
            builder.append(sdf.format(now));
            builder.append(new Random().nextInt(99));
            json.setObj(builder);
            json.setSuccess(true);
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取仓库货物类型
     */
    @RequestMapping("/getStockIsMaterial.json")
    @ResponseBody
    public Json getStockIsMaterial(Long warehouseId) {
        Json json = new Json();
        try {
            List<PZsStock> materialList = zsStockService.getGoodsTypeByWarehouseId(warehouseId);
            if (materialList != null && !materialList.isEmpty()) {
                json.setObj(materialList);
                json.setSuccess(true);
            } else {
                json.setMsg("该仓库没有货物类型");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 获取仓库产品状态
     */
    @RequestMapping("/getStockProductStatus.json")
    @ResponseBody
    public List<PZsStock> getStockProductStatus() {
        List<PZsStock> productStatusList = zsStockService.getStockProductStatus();
        return productStatusList;
    }

    /**
     * ajax获取库存表集合
     *
     * @param stock
     * @return
     */
    @RequestMapping("/getStockList.json")
    @ResponseBody
    public Json getStockList(PZsStock stock) {
        Json json = new Json();
        try {
            List<PZsStock> stockList = zsStockService.getProductName(stock);
            if (null != stockList && !stockList.isEmpty()) {
                json.setObj(stockList);
                json.setSuccess(true);
            } else {
                json.setMsg("该类型产品查询暂无");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    @RequestMapping("/addStockAndRecordModal.htm")
    public String addStockAndRecordModal(HttpServletRequest request) {
        log.info(request.getRequestURI());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date now = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("NO.RK");
        sb.append(sdf.format(now));
        sb.append(new Random().nextInt(99));
        request.setAttribute("receiveNode", sb.toString());
        return "stock/addStockAndRecord";
    }

    /**
     * 出库
     *
     * @param request
     * @return
     */
    @RequestMapping("/leaveStockModal.htm")
    public String leaveStockModal(HttpServletRequest request, Long resourceId) {
        request.setAttribute("resourceId", resourceId);
        log.info(request.getRequestURI());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date now = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("NO.CK");
        sb.append(sdf.format(now));
        sb.append(new Random().nextInt(99));
        request.setAttribute("leaveStockNode", sb.toString());
        return "stock/leaveStockModal";
    }

    /**
     * 根据收货单编号查询
     *
     * @param puReceive
     * @return
     */
    @RequestMapping("/geReceiveByReceiveNo.json")
    @ResponseBody
    public Json geReceiveByReceiveNo(PuReceiveDetaildPara puReceive) {
        Json json = new Json();
        try {
            List<PuReceiveDetaildPara> receiveDetaildParaList = puReceiveDetailService.getByReceiveNoAndReceiptStatus(puReceive);
            List<PuReceiveDetail> receiveDetaildParas =new ArrayList<>();
            if (null != receiveDetaildParaList && !receiveDetaildParaList.isEmpty()) {
                for(PuReceiveDetail receiveDetaildPara:receiveDetaildParaList){
                    Byte receiptStutas =receiveDetaildPara.getReceiptStatus();
                    if(receiptStutas !=null&&receiptStutas.equals(new Byte("1"))){
                        receiveDetaildParas.add(receiveDetaildPara);
                    }
                }
                if(!receiveDetaildParas.isEmpty()){
                    json.setObj(receiveDetaildParas);
                    json.setSuccess(true);
                }else {
                    json.setMsg("该收货单编号已经过处理，请重新输入");
                }
            } else {
                json.setMsg("该收货单编号不存在，请重新输入");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 根据生产任务编号查询
     *
     * @param zsProduceTask
     * @return
     */
    @RequestMapping("/getProduceTaskByProduceTaskNo.json")
    @ResponseBody
    public Json getProduceTaskByProduceTaskNo(PZsProduceTaskDetail zsProduceTask) {
        Json json = new Json();
        try {
            List<PZsProduceTaskDetail> taskDetailList = zsProduceTaskDetailService.getProduceTaskDetailByProduceTaskNo(zsProduceTask);
            if (taskDetailList != null && !taskDetailList.isEmpty()) {
                json.setObj(taskDetailList);
                json.setSuccess(true);
            } else {
                json.setMsg("单号不存在");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 根据生产任务编号查询
     * by yh
     *
     * @param produceTaskNo
     * @return
     */
    @RequestMapping("/getProduceTaskByProduceTaskNoYh.json")
    @ResponseBody
    public Json getProduceTaskByProduceTaskNoYh(String produceTaskNo) {
        Json json = new Json();
        try {
            ZsProduceTask zs = zsProduceTaskService.getProduceTaskByNo(produceTaskNo);
            if (zs != null) {
                Byte res = zsProduceTaskService.getProduceTaskStatusById(zs.getProduceTaskId());
                if (1 == res) {
                    json.setObj(zs);
                    json.setSuccess(true);
                } else {
                    json.setMsg("生产任务已结束");
                }
            } else {
                json.setMsg("编号不存在");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常,请刷新");
        }
        return json;
    }

    /**
     * 新建入库
     *
     * @param enterStockAndStock
     * @return
     */
    @RequestMapping("/addEnterStockAndStock.json")
    @ResponseBody
    public Json addEnterStockAndStock(@RequestBody PEnterStockAndStock enterStockAndStock) {
        Json json = new Json();
        try {
            Subject user = SecurityUtils.getSubject();
            ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
            Long userId = shiroUser.getId();
            json = zsStockService.addStockAndEnterStock(enterStockAndStock, userId);
            if (json.isSuccess()) {
                json.setMsg("新建成功");
            } else {
                json.setMsg("新建失败");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 查询产品状态
     *
     * @param id
     * @return
     */
    @RequestMapping("/getStockIsMaterialByCodeAndName.json")
    @ResponseBody
    public Json getStockIsMaterialByCodeAndName(Long id) {
        Json json = new Json();
        try {
            List<PBaDataDictionaryDetails> dataDictionaryDetails = baDataDictionaryDetailsService.getBaDataDictionaryDetailsByDataDictionaryIdWithOutPage(id);
            if (dataDictionaryDetails != null) {
                json.setSuccess(true);
                json.setObj(dataDictionaryDetails);
            } else {
                json.setMsg("无匹配类型");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 根据批次号查询产品(新建入库)
     *
     * @param receiveDetaildPara
     * @return
     */
    @RequestMapping("/getReceiveDetailByBatchNo.json")
    @ResponseBody
    public Json getReceiveDetailByBatchNo(PuReceiveDetail receiveDetaildPara, HttpServletRequest request) {
        Json json = new Json();
        try {
            List<YhStock> list = new ArrayList<>();
            List<PuReceiveDetail> receiveDetaildParaList = puReceiveDetailService.getGoodsTypeByReceiveBatchNo(receiveDetaildPara);
            if (null != receiveDetaildParaList && receiveDetaildParaList.size() != 0) {
                for (PuReceiveDetail pu : receiveDetaildParaList) {
                    YhStock stock = new YhStock();
                    stock.setBatchNo(pu.getBatchNo());
                    stock.setGoodsType(pu.getGoodsType());
                    stock.setGoodsTypeId(pu.getGoodsTypeId());
                    stock.setMaterialWeight(pu.getWeight());
                    stock.setProductId(pu.getProductId());
                    stock.setProductName(pu.getProductName());
                    stock.setProductSpecName(pu.getProductSpecName());
                    stock.setUnitId(pu.getUnitId());
                    stock.setUnitName(pu.getUnitName());
                    list.add(stock);
                }
            }
            PZsProduceTaskDetail produceTaskDetail = new PZsProduceTaskDetail();
            produceTaskDetail.setBatchNo(receiveDetaildPara.getBatchNo());
            List<PZsProduceTaskDetail> produceTaskDetailList = zsProduceTaskDetailService.getProduceTaskDetailByBatchNo(produceTaskDetail);
            if (null != produceTaskDetailList && produceTaskDetailList.size() != 0) {
                for (PZsProduceTaskDetail pz : produceTaskDetailList) {
                    YhStock stock = new YhStock();
                    stock.setBatchNo(pz.getBatchNo());
                    stock.setGoodsType(pz.getGoodsType());
                    stock.setGoodsTypeId(pz.getIsMaterial());
                    stock.setMaterialWeight(pz.getWeight());
                    stock.setProductId(pz.getProductId());
                    stock.setProductName(pz.getProductName());
                    stock.setProductSpecName(pz.getProductSpecName());
                    stock.setUnitId(pz.getUnitId());
                    stock.setUnitName(pz.getUnitName());
                    list.add(stock);
                }
            }
            PZsStock zsStock = new PZsStock();
            zsStock.setBatchNo(receiveDetaildPara.getBatchNo());
            List<PZsStock> stockList = zsStockService.getStockListByBatchForEnterStock(zsStock);
            if (null != stockList && stockList.size() != 0) {
                for (PZsStock ps : stockList) {
                    YhStock stock = new YhStock();
                    stock.setBatchNo(ps.getBatchNo());
                    stock.setGoodsType(ps.getMaterialName());
                    stock.setGoodsTypeId(ps.getIsMaterial());
                    stock.setMaterialWeight(ps.getWeight());
                    stock.setProductId(ps.getProductId());
                    stock.setProductName(ps.getProductName());
                    stock.setProductSpecName(ps.getProductSpecName());
                    stock.setUnitId(ps.getUnitId());
                    stock.setUnitName(ps.getUnitName());
                    list.add(stock);
                }
            }
            if (list.size() == 0) {
                json.setMsg("该批次未绑定产品");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("yhstockList", list);
                Set<YhStock> set = new TreeSet<YhStock>(new Comparator<YhStock>() {
                    @Override
                    public int compare(YhStock o1, YhStock o2) {
                        if (o1.getGoodsTypeId() > o2.getGoodsTypeId()) {
                            return 1;
                        } else if (o1.getGoodsTypeId() < o2.getGoodsTypeId()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });
                set.addAll(list);
                json.setSuccess(true);
                json.setObj(set);
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 入库产品根据货物类型获取产品
     */
    @RequestMapping("/getReceiveDetailByGoodsType.json")
    @ResponseBody
    public Json getProductByGoodsTypeId(YhStock yhStock, HttpServletRequest request) {
        Json json = new Json();
        try {
            HttpSession session = request.getSession();
            List<YhStock> list = (List<YhStock>) session.getAttribute("yhstockList");
            if (list.size() != 0) {
                List<YhStock> a = new ArrayList<>();
                for (YhStock stock : list) {
                    if (Objects.equals(stock.getGoodsTypeId(), yhStock.getGoodsTypeId()) && Objects.equals(stock.getBatchNo(), yhStock.getBatchNo())) {
                        a.add(stock);
                    }
                }
                Set<YhStock> set = new TreeSet<YhStock>(new Comparator<YhStock>() {
                    @Override
                    public int compare(YhStock o1, YhStock o2) {
                        if (o1.getProductId() > o2.getProductId()) {
                            return 1;
                        } else if (o1.getProductId() < o2.getProductId()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });
                set.addAll(a);
                json.setSuccess(true);
                json.setObj(set);
            } else {
                json.setMsg("服务器异常");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 入库产品根据产品名称获取规格
     */
    @RequestMapping("/getReceiveDetailByProductId.json")
    @ResponseBody
    public Json getReceiveDetailByProductId(YhStock yhStock, HttpServletRequest request) {
        Json json = new Json();
        try {
            HttpSession session = request.getSession();
            List<YhStock> list = (List<YhStock>) session.getAttribute("yhstockList");
            if (list.size() != 0) {
                List<YhStock> a = new ArrayList<>();
                for (YhStock stock : list) {
                    if (Objects.equals(stock.getGoodsTypeId(), yhStock.getGoodsTypeId()) && Objects.equals(stock.getBatchNo(), yhStock.getBatchNo()) && Objects.equals(yhStock.getProductId(), stock.getProductId())) {
                        a.add(stock);
                    }
                }
                Set<YhStock> set = new TreeSet<YhStock>(new Comparator<YhStock>() {
                    @Override
                    public int compare(YhStock o1, YhStock o2) {
                        return o1.getProductSpecName().compareTo(o2.getProductSpecName());
                    }
                });
                set.addAll(a);
                json.setSuccess(true);
                json.setObj(set);
            } else {
                json.setMsg("服务器异常");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 根据规格获取原料数量
     */
    @RequestMapping("/getMaterialWeightBySpec.json")
    @ResponseBody
    public Json getMaterialWeightBySpec(YhStock yhStock, HttpServletRequest request) {
        Json json = new Json();
        try {
            HttpSession session = request.getSession();
            List<YhStock> list = (List<YhStock>) session.getAttribute("yhstockList");
            if (list.size() != 0) {
                List<YhStock> a = new ArrayList<>();
                for (YhStock stock : list) {
                    if (Objects.equals(stock.getGoodsTypeId(), yhStock.getGoodsTypeId()) && Objects.equals(stock.getBatchNo(), yhStock.getBatchNo()) && Objects.equals(yhStock.getProductId(), stock.getProductId()) && Objects.equals(yhStock.getProductSpecName(), stock.getProductSpecName())) {
                        a.add(stock);
                    }
                }
                Set<YhStock> set = new TreeSet<YhStock>(new Comparator<YhStock>() {
                    @Override
                    public int compare(YhStock o1, YhStock o2) {
                        return o1.getMaterialWeight().compareTo(o2.getMaterialWeight());
                    }
                });
                set.addAll(a);
                json.setSuccess(true);
                json.setObj(set);
            } else {
                json.setMsg("服务器异常");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 根据原料数量获取单位
     */
    @RequestMapping("/getUnitByWeight.json")
    @ResponseBody
    public Json getUnitByWeight(YhStock yhStock, HttpServletRequest request) {
        Json json = new Json();
        try {
            HttpSession session = request.getSession();
            List<YhStock> list = (List<YhStock>) session.getAttribute("yhstockList");
            if (list.size() != 0) {
                List<YhStock> a = new ArrayList<>();
                for (YhStock stock : list) {
                    if (Objects.equals(stock.getGoodsTypeId(), yhStock.getGoodsTypeId()) && Objects.equals(stock.getBatchNo(), yhStock.getBatchNo()) && Objects.equals(yhStock.getProductId(), stock.getProductId()) && Objects.equals(yhStock.getProductSpecName(), stock.getProductSpecName()) && yhStock.getMaterialWeight().compareTo(stock.getMaterialWeight()) == 0) {
                        a.add(stock);
                    }
                }
                Set<YhStock> set = new TreeSet<YhStock>(new Comparator<YhStock>() {
                    @Override
                    public int compare(YhStock o1, YhStock o2) {
                        return o1.getUnitId().compareTo(o2.getUnitId());
                    }
                });
                set.addAll(a);
                json.setSuccess(true);
                json.setObj(set);
            } else {
                json.setMsg("服务器异常");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 根据批次号查询产品(成品返库)
     *
     * @param receiveDetaildPara
     * @return
     */
    @RequestMapping("/getReceiveDetailByBatchNoForReback.json")
    @ResponseBody
    public Json getReceiveDetailByBatchNoForReback(PuReceiveDetail receiveDetaildPara, HttpServletRequest request) {
        Json json = new Json();
        try {
            List<YhStock> list = new ArrayList<>();
            PZsProduceTaskDetail produceTaskDetail = new PZsProduceTaskDetail();
            produceTaskDetail.setBatchNo(receiveDetaildPara.getBatchNo());
            List<PZsProduceTaskDetail> produceTaskDetailList = zsProduceTaskDetailService.getProduceTaskDetailByBatchNo(produceTaskDetail);
            if (null != produceTaskDetailList && produceTaskDetailList.size() != 0) {
                for (PZsProduceTaskDetail pz : produceTaskDetailList) {
                    YhStock stock = new YhStock();
                    stock.setBatchNo(pz.getBatchNo());
                    stock.setGoodsType(pz.getGoodsType());
                    stock.setGoodsTypeId(pz.getIsMaterial());
                    stock.setMaterialWeight(pz.getWeight());
                    stock.setProductId(pz.getProductId());
                    stock.setProductName(pz.getProductName());
                    stock.setProductSpecName(pz.getProductSpecName());
                    stock.setUnitId(pz.getUnitId());
                    stock.setUnitName(pz.getUnitName());
                    list.add(stock);
                }
            }
            PZsStock zsStock = new PZsStock();
            zsStock.setBatchNo(receiveDetaildPara.getBatchNo());
            List<PZsStock> stockList = zsStockService.getStockListByBatchForEnterStock(zsStock);
            if (null != stockList && stockList.size() != 0) {
                for (PZsStock ps : stockList) {
                    YhStock stock = new YhStock();
                    stock.setBatchNo(ps.getBatchNo());
                    stock.setGoodsType(ps.getMaterialName());
                    stock.setGoodsTypeId(ps.getIsMaterial());
                    stock.setMaterialWeight(ps.getWeight());
                    stock.setProductId(ps.getProductId());
                    stock.setProductName(ps.getProductName());
                    stock.setProductSpecName(ps.getProductSpecName());
                    stock.setUnitId(ps.getUnitId());
                    stock.setUnitName(ps.getUnitName());
                    list.add(stock);
                }
            }
            if (list.size() == 0) {
                json.setMsg("该批次未绑定产品");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("yhStockList", list);
                Set<YhStock> set = new TreeSet<YhStock>(new Comparator<YhStock>() {
                    @Override
                    public int compare(YhStock o1, YhStock o2) {
                        if (o1.getGoodsTypeId() > o2.getGoodsTypeId()) {
                            return 1;
                        } else if (o1.getGoodsTypeId() < o2.getGoodsTypeId()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });
                set.addAll(list);
                json.setSuccess(true);
                json.setObj(set);
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 根据货物类型获取产品(成品返库)
     */
    @RequestMapping("/getRebackByGoodsType.json")
    @ResponseBody
    public Json getProductByGoodsTypeIdForReback(YhStock yhStock, HttpServletRequest request) {
        Json json = new Json();
        try {
            HttpSession session = request.getSession();
            List<YhStock> list = (List<YhStock>) session.getAttribute("yhStockList");
            if (list.size() != 0) {
                List<YhStock> a = new ArrayList<>();
                for (YhStock stock : list) {
                    if (Objects.equals(stock.getGoodsTypeId(), yhStock.getGoodsTypeId()) && Objects.equals(stock.getBatchNo(), yhStock.getBatchNo())) {
                        a.add(stock);
                    }
                }
                Set<YhStock> set = new TreeSet<YhStock>(new Comparator<YhStock>() {
                    @Override
                    public int compare(YhStock o1, YhStock o2) {
                        if (o1.getProductId() > o2.getProductId()) {
                            return 1;
                        } else if (o1.getProductId() < o2.getProductId()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });
                set.addAll(a);
                json.setSuccess(true);
                json.setObj(set);
            } else {
                json.setMsg("服务器异常");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 根据产品名称获取规格(成品返库)
     */
    @RequestMapping("/getRebackByProductId.json")
    @ResponseBody
    public Json getReceiveDetailByProductIdForReback(YhStock yhStock, HttpServletRequest request) {
        Json json = new Json();
        try {
            HttpSession session = request.getSession();
            List<YhStock> list = (List<YhStock>) session.getAttribute("yhStockList");
            if (list.size() != 0) {
                List<YhStock> a = new ArrayList<>();
                for (YhStock stock : list) {
                    if (Objects.equals(stock.getGoodsTypeId(), yhStock.getGoodsTypeId()) && Objects.equals(stock.getBatchNo(), yhStock.getBatchNo()) && Objects.equals(yhStock.getProductId(), stock.getProductId())) {
                        a.add(stock);
                    }
                }
                Set<YhStock> set = new TreeSet<YhStock>(new Comparator<YhStock>() {
                    @Override
                    public int compare(YhStock o1, YhStock o2) {
                        return o1.getProductSpecName().compareTo(o2.getProductSpecName());
                    }
                });
                set.addAll(a);
                json.setSuccess(true);
                json.setObj(set);
            } else {
                json.setMsg("服务器异常");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 成品返库modal
     *
     * @param request
     * @return
     */
    @RequestMapping("/rebackStockModal.htm")
    public String rebackStockModal(HttpServletRequest request) {
        log.info(request.getRequestURI());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date now = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("NO.FK");
        sb.append(sdf.format(now));
        sb.append(new Random().nextInt(99));
        request.setAttribute("receiveNode", sb.toString());
        return "stock/rebackStockModal";
    }

    @RequestMapping("/updateStockModal.htm")
    public String updateStockModal(HttpServletRequest request, String[] stockIDs) {
        log.info(request.getRequestURI());
        List<PZsStock> stockList = zsStockService.getStockByStockIDs(stockIDs);
        if (null != stockList && !stockList.isEmpty()) {
            request.setAttribute("stockList", stockList);
        }
        List<SysUnit> unitList = sysUnitService.getSysUnitNoZero();
        if (null != unitList && 0 != unitList.size()) {
            request.setAttribute("units", unitList);
        }
        return "stock/updateStockModal";
    }

    //活参编辑（AJAX）
    @RequestMapping("/updateStock.json")
    @ResponseBody
    public Json updateStock(@RequestBody PEnterStockAndStock enterStockAndStock) {
        Json json = new Json();
        try {
            Boolean boo = false;
            boo = zsStockService.updateStockBySeaCucumber(enterStockAndStock.getStockList());
            if (boo) {
                json.setObj(boo);
                json.setSuccess(true);
                json.setMsg("活参编辑修改成功");
            } else {
                json.setMsg("活参编辑修改失败");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 跳转增加工序界面
     *
     * @param request
     * @return
     */
    @RequestMapping("/addProcedureManageIFrame.htm")
    public String addProcedureManageIFrame(HttpServletRequest request, @RequestParam("warehouseId") Long warehouseId, @RequestParam("stockIds") String stockIds) {
        request.setAttribute("stockIds", stockIds);
        List<PZsWorkProcess> workProcessList = zsWorkProcessService.getAllWorkProcessByFormAttribute();
        request.setAttribute("workProcessList", workProcessList);
        ZsWarehouse warehouse = zsWarehouseService.getWarehouseById(warehouseId);
        request.setAttribute("seaCucumberWarehouse", warehouse);
        log.info(request.getRequestURI());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date now = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("NO.CK");
        sb.append(sdf.format(now));
        sb.append(new Random().nextInt(99));
        request.setAttribute("receiveNode", sb.toString());
        return freeMarkerIndexResult("stock/addProcedureManage.ftl", request);
    }

    /**
     * 根据工艺ID和操作类型查询表单附加属性
     *
     * @param workProcessId
     * @param handleType
     * @return
     */
    @RequestMapping("/getFormAttributeListByHandleType.json")
    @ResponseBody
    public Json getFormAttributeListByHandleType(@Param("workProcessId") Long workProcessId, @Param("handleType") Integer handleType) {
        Json json = new Json();
        try {
            List<PBaFormAttribute> formAttributeList
                    = baFormAttributeService.getBaFormAttributeListByWorkProcessIdAndHandleType(workProcessId, handleType);
            if (formAttributeList != null && !formAttributeList.isEmpty()) {
                json.setSuccess(true);
                json.setObj(formAttributeList);
            } else {
                json.setMsg("无对应类别");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    @RequestMapping("/getDataDictionartByCode.json")
    @ResponseBody
    public Json getDataDictionartByCode(String code) {
        Json json = new Json();
        try {
            List<PBaDataDictionaryDetails> dataDictionaryDetailsList = baDataDictionaryDetailsService.getBaDataDictionaryDetailsByCode(code);
            if (dataDictionaryDetailsList != null && !dataDictionaryDetailsList.isEmpty()) {
                json.setObj(dataDictionaryDetailsList);
                json.setSuccess(true);
            } else {
                json.setMsg("无数据字典记录");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 查询用户信息（ajax）
     *
     * @return
     */
    @RequestMapping("/getAllUserInStockManage.json")
    @ResponseBody
    public Json getAllUserInStockManage(Long companyId) {
        Json json = new Json();
        try {
            List<BaUser> userList = baUserService.getBaUserByUserType(1, companyId);
            if (userList != null && !userList.isEmpty()) {
                json.setObj(userList);
                json.setSuccess(true);
            } else {
                json.setObj("无用户信息");
            }
        } catch (Exception e) {
            json.setObj("服务器异常");
        }
        return json;
    }

    /**
     * 打印箱码
     *
     * @param request
     * @param modelAndView
     * @param modelMap
     * @param format
     * @param enterStock   箱码数据
     * @param resourceId   日志
     */
    @RequestMapping(value = "/printBoxLabel/{format}", produces = "application/pdf", method = RequestMethod.POST)
    public ModelAndView printBoxLabel(HttpServletRequest request, final ModelMap modelMap, ModelAndView modelAndView, @PathVariable(name = "format") String format, String enterStock, Long resourceId) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser user = (ShiroUser) subject.getPrincipal();
        BoxLabel boxLabel = JSON.parseObject(enterStock, BoxLabel.class);
        List<BoxLabel> boxLabels = new ArrayList<>();
        if (boxLabel.getInWeight().divideAndRemainder(boxLabel.getEvery())[1].setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(BigDecimal.valueOf(0)) == 1) {
            for (int i = 0; i < boxLabel.getNum(); i++) {
                if (i == boxLabel.getNum() - 1) {
                    BoxLabel label = new BoxLabel(boxLabel.getBatchNo(), boxLabel.getMaterialName(), boxLabel.getProductName(), boxLabel.getProductStatusName(), boxLabel.getProductSpecName(), boxLabel.getInWeight(), boxLabel.getUnitName(), boxLabel.getCreateDate(), boxLabel.getEvery(), boxLabel.getNum(), boxLabel.getQrcode());
                    label.setEvery(boxLabel.getInWeight().divideAndRemainder(boxLabel.getEvery())[1].setScale(2, BigDecimal.ROUND_HALF_UP));
                    label.setQrcode(label.getQrcode() + (i + 1));
                    boxLabels.add(label);
                } else {
                    BoxLabel label = new BoxLabel(boxLabel.getBatchNo(), boxLabel.getMaterialName(), boxLabel.getProductName(), boxLabel.getProductStatusName(), boxLabel.getProductSpecName(), boxLabel.getInWeight(), boxLabel.getUnitName(), boxLabel.getCreateDate(), boxLabel.getEvery(), boxLabel.getNum(), boxLabel.getQrcode());
                    label.setQrcode(label.getQrcode() + (i + 1));
                    boxLabels.add(label);
                }
            }
        } else {
            for (int i = 0; i < boxLabel.getNum(); i++) {
                BoxLabel label = new BoxLabel(boxLabel.getBatchNo(), boxLabel.getMaterialName(), boxLabel.getProductName(), boxLabel.getProductStatusName(), boxLabel.getProductSpecName(), boxLabel.getInWeight(), boxLabel.getUnitName(), boxLabel.getCreateDate(), boxLabel.getEvery(), boxLabel.getNum(), boxLabel.getQrcode());
                label.setQrcode(label.getQrcode() + (i + 1));
                boxLabels.add(label);
            }
        }
        SysSysLog sysLog = new SysSysLog();
        String ip = ClientIP.getClientIP(request);
        sysLog.setIpAddress(ip);
        sysLog.setCName("打印箱码");
        sysLog.setResourceId(resourceId);
        sysLog.setCreatedUserId(user.getId());
        sysLog.setRemark("打印箱码，批次号：" + boxLabel.getBatchNo());
        sysSysLogService.addSysSysLog(sysLog);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(boxLabels);
        // connecting to mysql
        modelMap.put("datasource", dataSource);
        modelMap.put("format", format);
        modelAndView = new ModelAndView("reportBoxLabel", modelMap);
        return modelAndView;
    }

    @RequestMapping("/addProcedureRecord.json")
    @ResponseBody
    public Json addProcedureRecord(@RequestBody SeaCucumberProcedure seaCucumberProcedure) {
        Json json = new Json();
        try {
            Subject subject = SecurityUtils.getSubject();
            ShiroUser user = (ShiroUser) subject.getPrincipal();
            List<PAttributeValueData> attributeValueDataList//参数Json集合
                    = seaCucumberProcedure.getAttributeValueDataList();
            List<PBaFormAttributeValue> formAttributeValueList//表单设置明细键值表集合
                    = seaCucumberProcedure.getFormAttributeValueList();
            if (formAttributeValueList != null && !formAttributeValueList.isEmpty()) {
                for (PBaFormAttributeValue baFormAttributeValue : formAttributeValueList) {
                    Long workProcessId = baFormAttributeValue.getWorkProcessId();
                    Byte handleType = baFormAttributeValue.getHandleType();
                    List<AttributeValueData> dataList = new ArrayList<>();
                    if (attributeValueDataList != null && !attributeValueDataList.isEmpty()) {
                        for (PAttributeValueData data : attributeValueDataList) {
                            if (workProcessId == data.getFormAttributeValueId() && handleType == data.getHandleType()) {
                                AttributeValueData attributeValueData = new AttributeValueData();
                                attributeValueData.setValue(data.getAttribute());
                                attributeValueData.setAttributeId(data.getAttributeValueId());
                                dataList.add(attributeValueData);
                            }
                        }
                    }
                    if (dataList != null && !dataList.isEmpty()) {
                        String string = JSON.toJSONString(dataList);
                        baFormAttributeValue.setObjectParameterJson(string);
                    }
                }
            }
            json = saLeaveStockDetailService.addProcedureAndFormAttributeValue(seaCucumberProcedure, user.getId(), user.getName());
            if (json.isSuccess()) {
                json.setMsg("增加工序成功");
                json.setSuccess(true);
            } else {
                json.setSuccess(false);
                json.setMsg("增加工序成功,请检查是否填写正确！");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 跳转报表统计界面
     *
     * @param request
     * @return
     */
    @RequestMapping("/reportCountManage.htm")
    public String reportCountManage(HttpServletRequest request) {
        return freeMarkerIndexResult("stock/reportCountManage.ftl", request);
    }

    /**
     * 加载报表统计表格
     *
     * @param request
     * @param stock
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/getReportCountList.json")
    @ResponseBody
    public Object getReportCountList(HttpServletRequest request, PZsStock stock, Integer page, Integer rows) {
        List<PZsStock> stockList = zsStockService.getReportCountListFromStock(stock, page, rows);
        PageInfo<PZsStock> pageInfo = new PageInfo<>(stockList);
        return new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), stockList);
    }

    /**
     * 报表统计筛选条件：查询货物类型
     *
     * @param stock
     * @return
     */
    @RequestMapping("/getIsMaterialOption.json")
    @ResponseBody
    public Json getIsMaterialOption(PZsStock stock) {
        Json json = new Json();
        try {
            List<PZsStock> zsStockList = zsStockService.getIsMaterialOptionForReportCountFromStock(stock);
            if (zsStockList != null && !zsStockList.isEmpty()) {
                json.setObj(zsStockList);
                json.setSuccess(true);
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 报表统计筛选：查询产品大类
     *
     * @param array
     * @return
     */
    @RequestMapping("/getProductTypeByStock.json")
    @ResponseBody
    public Json getProductTypeByStock(@RequestParam(name = "array") Long[] array) {
        Json json = new Json();
        try {
            List<PZsStock> stockList = zsStockService.getProductTypeListFromStock(array);
            if (stockList != null && !stockList.isEmpty()) {
                json.setSuccess(true);
                json.setObj(stockList);
            } else {
                json.setMsg("该货物类型没有对应的产品大类");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 报表统计筛选：查询产品小类
     *
     * @param
     * @return
     */
    @RequestMapping("/getProductIdListFromStock.json")
    @ResponseBody
    public Json getProductIdListFromStock
    (@RequestParam(name = "materialArray") Long[] materialArray,
     @RequestParam(name = "productTypeArray") Long[] productTypeArray) {
        Json json = new Json();
        try {
            PZsStock zsStock = new PZsStock();
            zsStock.setMaterialArray(materialArray);
            zsStock.setProductTypeArray(productTypeArray);
            List<PZsStock> zsStockList = zsStockService.getProductIdListFromStock(zsStock);
            if (zsStockList != null && !zsStockList.isEmpty()) {
                json.setSuccess(true);
                json.setObj(zsStockList);
            } else {
                json.setMsg("该产品大类没有对应的产品小类");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 报表统计筛选：查询产品规格
     *
     * @param
     * @return
     */
    @RequestMapping("/getProductSpecNameFromStock.json")
    @ResponseBody
    public Json getProductSpecNameFromStock
    (@RequestParam(name = "materialArray") Long[] materialArray,
     @RequestParam(name = "productTypeArray") Long[] productTypeArray,
     @RequestParam(name = "productIdArray") Long[] productIdArray) {
        Json json = new Json();
        try {
            PZsStock zsStock = new PZsStock();
            zsStock.setMaterialArray(materialArray);
            zsStock.setProductTypeArray(productTypeArray);
            zsStock.setProductIdArray(productIdArray);
            List<PZsStock> stockList = zsStockService.getProductSpecNameFromStock(zsStock);
            if (stockList != null && !stockList.isEmpty()) {
                json.setObj(stockList);
                json.setSuccess(true);
            } else {
                json.setMsg("该产品小类无对应规格");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 报表统计筛选：查询产品单位
     *
     * @param
     * @return
     */
    @RequestMapping("/getUnitIdFromStock.json")
    @ResponseBody
    public Json getUnitIdFromStock
    (@RequestParam(name = "materialArray") Long[] materialArray,
     @RequestParam(name = "productTypeArray") Long[] productTypeArray,
     @RequestParam(name = "productIdArray") Long[] productIdArray,
     @RequestParam(name = "productSpecArray") String[] productSpecArray) {
        Json json = new Json();
        try {
            PZsStock zsStock = new PZsStock();
            zsStock.setMaterialArray(materialArray);
            zsStock.setProductTypeArray(productTypeArray);
            zsStock.setProductIdArray(productIdArray);
            zsStock.setProductSpecArray(productSpecArray);
            List<PZsStock> stockList
                    = zsStockService.getUnitIdFromStock(zsStock);
            if (stockList != null && !stockList.isEmpty()) {
                json.setObj(stockList);
                json.setSuccess(true);
            } else {
                json.setMsg("该产品小类无对应单位");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 根据生产任务ID获取工序信息
     *
     * @param workFlowId
     * @return
     */
    @RequestMapping("/getWorkProcessListByWorkFlowIdWithStock.json")
    @ResponseBody
    public Json getWorkProcessListByWorkFlowIdWithStock(Long workFlowId) {
        Json json = new Json();
        try {
            List<ZsWorkProcess> workFlowList = zsWorkProcessService.getWorkProcessByWorkFlowIdForStock(workFlowId);
            if (workFlowList != null && !workFlowList.isEmpty()) {
                json.setObj(workFlowList);
                json.setSuccess(true);
            } else {
                json.setMsg("无工序信息");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 报表统计数据导出
     */
    @RequestMapping("/reportCountExportData.json")
    @ResponseBody
    public Object reportCountExportData(HttpServletRequest request, HttpServletResponse response, PZsStock zsStock) {
        Json json = new Json();
        Boolean res = zsStockService.reportCountExport(request, response, zsStock);
        if (res) {
            json.setMsg("导出成功");
            json.setSuccess(true);
        } else {
            json.setMsg("服务器异常");
        }
        return json;
    }
}
