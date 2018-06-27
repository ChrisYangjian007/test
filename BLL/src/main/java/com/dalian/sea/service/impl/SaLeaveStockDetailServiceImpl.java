package com.dalian.sea.service.impl;

import com.alibaba.fastjson.JSON;
import com.dalian.sea.json.AttributeLogJson;
import com.dalian.sea.json.Json;
import com.dalian.sea.mapper.*;
import com.dalian.sea.model.*;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.SaLeaveStockDetailService;
import com.dalian.sea.service.ZsProduceTaskService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/3/1
 */
@Service("SaLeaveStockDetailService")
@Slf4j
public class SaLeaveStockDetailServiceImpl implements SaLeaveStockDetailService {
    @Autowired
    private SaLeaveStockDetailMapper saLeaveStockDetailMapper;

    @Autowired
    private SaLeaveStockMapper saLeaveStockMapper;

    @Autowired
    private zsProduceTaskDetailMapper produceTaskDetailMapper;

    @Autowired
    private ZsProduceTaskMapper zsProduceTaskMapper;

    @Autowired
    private ZsProduceTaskService zsProduceTaskService;

    @Autowired
    private ZsStockMapper zsStockMapper;

    @Autowired
    private ZsTaskDetailValueMapper zsTaskDetailValueMapper;

    @Autowired
    private ZsQrCodeMapper zsQrCodeMapper;

    @Autowired
    private ZsQrCodeUseMapper zsQrCodeUseMapper;

    @Autowired
    private BaFormAttributeValueMapper baFormAttributeValueMapper;

    @Autowired
    private ZsWarehouseUserMapper zsWarehouseUserMapper;

    @Autowired
    private ZsWarehouseMapper zsWarehouseMapper;

    @Override
    public List<SaLeaveStockDetailPara> getAllLeaveStockDetail(SaLeaveStockDetailPara saLeaveStockDetailPara, int page, int rows, Long userId, Long companyId) {
        List<SaLeaveStockDetailPara> saLeaveStockDetailParas = null;
        try {
            //获取当前用户管理的仓库id
            List<Long> ids = new ArrayList<>();
            if (companyId != 0) {
                ids = zsWarehouseUserMapper.getWarehouseIdByUserId(userId);
            } else {
                List<ZsWarehouse> list = zsWarehouseMapper.getAllWarehouse();
                for (ZsWarehouse zsWarehouse : list) {
                    ids.add(zsWarehouse.getWarehouseId());
                }
            }
            PageHelper.startPage(page, rows);
            saLeaveStockDetailParas = saLeaveStockDetailMapper.getAllSaLeaveStockDetail(saLeaveStockDetailPara, ids);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return saLeaveStockDetailParas;
    }

    @Override
    public List<SaLeaveStockDetailPara> getAllLossDetail(SaLeaveStockDetailPara saLeaveStockDetailPara, int page, int rows, Long userId, Long companyId) {
        List<SaLeaveStockDetailPara> saLeaveStockDetailParas = null;
        try {
            //获取当前用户管理的仓库id
            List<Long> ids = new ArrayList<>();
            if (companyId != 0) {
                ids = zsWarehouseUserMapper.getWarehouseIdByUserId(userId);
            } else {
                List<ZsWarehouse> list = zsWarehouseMapper.getAllWarehouse();
                for (ZsWarehouse zsWarehouse : list) {
                    ids.add(zsWarehouse.getWarehouseId());
                }
            }
            PageHelper.startPage(page, rows);
            saLeaveStockDetailParas = saLeaveStockDetailMapper.getAllSaLeaveStockDetail(saLeaveStockDetailPara, ids);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return saLeaveStockDetailParas;
    }

    @Override
    public List<SaLeaveStockDetailPara> getStockIsMaterial(Long userId, Long companyId) {
        List<Long> ids = new ArrayList<>();
        //获取当前用户管理的仓库id
        if (companyId != 0) {
            ids = zsWarehouseUserMapper.getWarehouseIdByUserId(userId);
        } else {
            List<ZsWarehouse> list = zsWarehouseMapper.getAllWarehouse();
            for (ZsWarehouse zsWarehouse : list) {
                ids.add(zsWarehouse.getWarehouseId());
            }
        }
        List<SaLeaveStockDetailPara> list = saLeaveStockDetailMapper.getStockIsMaterial(ids);
        return list;
    }

    @Override
    public List<SaLeaveStockDetailPara> getLossIsMaterial(Long userId, Long companyId) {
        List<Long> ids = new ArrayList<>();
        //获取当前用户管理的仓库id
        if (companyId != 0) {
            ids = zsWarehouseUserMapper.getWarehouseIdByUserId(userId);
        } else {
            List<ZsWarehouse> list = zsWarehouseMapper.getAllWarehouse();
            for (ZsWarehouse zsWarehouse : list) {
                ids.add(zsWarehouse.getWarehouseId());
            }
        }
        return saLeaveStockDetailMapper.getLossIsMaterial(ids);
    }

    @Override
    public List<SaLeaveStockDetailPara> getStockProductStatus(Long userId, Long companyId) {
        List<Long> ids = new ArrayList<>();
        //获取当前用户管理的仓库id
        if (companyId != 0) {
            ids = zsWarehouseUserMapper.getWarehouseIdByUserId(userId);
        } else {
            List<ZsWarehouse> list = zsWarehouseMapper.getAllWarehouse();
            for (ZsWarehouse zsWarehouse : list) {
                ids.add(zsWarehouse.getWarehouseId());
            }
        }
        return saLeaveStockDetailMapper.getStockProductStatus(ids);
    }

    @Override
    public List<SaLeaveStockDetailPara> getLossProductStatus(Long userId, Long companyId) {
        List<Long> ids = new ArrayList<>();
        //获取当前用户管理的仓库id
        if (companyId != 0) {
            ids = zsWarehouseUserMapper.getWarehouseIdByUserId(userId);
        } else {
            List<ZsWarehouse> list = zsWarehouseMapper.getAllWarehouse();
            for (ZsWarehouse zsWarehouse : list) {
                ids.add(zsWarehouse.getWarehouseId());
            }
        }
        return saLeaveStockDetailMapper.getLossProductStatus(ids);
    }

    /**
     * 通过produceTaskId获取出库记录
     *
     * @param produceTaskId
     * @return
     */
    @Override
    public List<SaLeaveStockDetailPara> getLeaveStockDetailByProduceId(Long produceTaskId) {
        return saLeaveStockDetailMapper.getLeaveStockDetailByProduceId(produceTaskId);
    }

    @Override
    @Transactional
    public Json newLeaveStock(YhLeaveStock yhLeaveStock, Long userId, String userName, List<ZsQrCode> qrCodes) {
        Json json = new Json();
        try {
            Integer integer;
            //获取生产任务id
            Long taskId = yhLeaveStock.getProduceTaskId();
            if (null == taskId) {
                /**
                 * 新建生产任务，返回主键
                 */
                zsProduceTaskMapper.newProduceTask(yhLeaveStock, new Date(), userId);
                Long produceTaskId = yhLeaveStock.getProduceTaskId();
                //set生产任务id
                yhLeaveStock.setProduceTaskId(produceTaskId);
                /**
                 * 绑定二维码
                 */
                if (null != qrCodes && 0 != qrCodes.size()) {
                    for (ZsQrCode code : qrCodes) {
                        code.setCurrentProduceTaskId(produceTaskId);
                        code.setCurrentProduceTaskCode(yhLeaveStock.getProduceTaskNo());
                        code.setCurrentProduceTaskName(yhLeaveStock.getProduceTaskName());
                        code.setUseNumber(code.getUseNumber() + 1);
                        code.setUseStatus((byte) 2);
                        code.setBindDate(new Date());
                        code.setBindUserId(userId);
                        code.setBindUserName(userName);
                        code.setUpdateUserId(userId);
                        integer = zsQrCodeMapper.updateZsQrCode(code);
                        if (0 != integer) {
                            ZsQrCodeUse codeUse = new ZsQrCodeUse();
                            codeUse.setQrCodeId(code.getQrCodeId());
                            codeUse.setProduceTaskId(code.getCurrentProduceTaskId());
                            codeUse.setProduceTaskCode(code.getCurrentProduceTaskCode());
                            codeUse.setProduceTaskName(code.getCurrentProduceTaskName());
                            codeUse.setBindDate(code.getBindDate());
                            codeUse.setBindUserId(code.getBindUserId());
                            codeUse.setBindUserName(code.getBindUserName());
                            codeUse.setCreateUserId(code.getBindUserId());
                            integer = zsQrCodeUseMapper.addQrCodeUse(codeUse);
                        }
                    }
                }

                /**
                 * 新建生产任务工艺信息
                 */
                zsTaskDetailValueMapper.newTaskDetailValue(yhLeaveStock, new Date(), userId, produceTaskId);
                /**
                 *新建出库单,返回主键
                 */
                saLeaveStockMapper.InsertLeaveStock(yhLeaveStock, new Date(), userId);
                Long leaveStockId = yhLeaveStock.getLeaveStockId();
                //获取出库单详情list
                List<YhLeaveStockDetail> leaveStockDetailList = yhLeaveStock.getSaLeaveStockDetails();
                for (YhLeaveStockDetail lsd : leaveStockDetailList) {
                    /**
                     * 新建出库单详情
                     * set出库单id
                     */
                    lsd.setLeaveId(leaveStockId);
                    saLeaveStockDetailMapper.InsertLeaveStockDetail(lsd, new Date(), userId, yhLeaveStock.getProduceTaskId());
                    /**
                     * 修改库存
                     */
                    PZsStock stock = new PZsStock();
                    stock.setProductId(lsd.getProductId());
                    stock.setIsMaterial(lsd.getIsMaterial());
                    stock.setBatchNo(lsd.getBatchNo());
                    stock.setProductStatus(lsd.getProductStatus());
                    stock.setProductSpecName(lsd.getProductSpecName());
                    stock.setWarehouseId(lsd.getWarehouseId());
                    //多条件查询库存记录
                    ZsStock zs = zsStockMapper.getOneZsStock(stock);
                    //获取当前记录剩余库存(weight)
                    BigDecimal weight = zs.getWeight();
                    //获取当前记录剩余出库数量(outWeight)
                    BigDecimal oWeight = zs.getOutWeight();
                    //获取lsd出库数量
                    BigDecimal outWeight = lsd.getOutWeight();
                    //如果库存减为负数,rollback
                    if (weight.subtract(outWeight).compareTo(BigDecimal.valueOf(0)) == -1) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        json.setSuccess(false);
                        return json;
                    } else {
                        zsStockMapper.updateWeight(weight.subtract(outWeight), oWeight.add(outWeight), zs.getStockId());
                    }
                    //再获取修改后的库存
                    BigDecimal restWeight = zsStockMapper.getStockById(zs.getStockId()).getWeight();
                    if (restWeight.compareTo(BigDecimal.valueOf(0)) == 0) {
                        zsStockMapper.updateRestStatus(zs.getStockId());
                    }
                    /**
                     * 新建生产任务对应产品明细
                     * 如果重复就叠加
                     */
                    if ("否".equals(lsd.getLoss())) {
                        zsProduceTaskDetail ptd = new zsProduceTaskDetail();
                        ptd.setUnitId(lsd.getUnitId());
                        ptd.setUnitName(lsd.getUnitName());
                        ptd.setProductSpecName(lsd.getProductSpecName());
                        ptd.setProductId(lsd.getProductId());
                        ptd.setProductName(lsd.getProductName());
                        ptd.setWarehouseId(lsd.getWarehouseId());
                        ptd.setIsMaterial(lsd.getIsMaterial());
                        ptd.setProductStatus(lsd.getProductStatus());
                        ptd.setBatchNo(lsd.getBatchNo());
                        ptd.setStockId(zs.getStockId());
                        //判断是否要叠加
                        ptd.setProduceTaskId(yhLeaveStock.getProduceTaskId());
                        //多条件查询生产任务对应产品明细
                        zsProduceTaskDetail produceTaskDetail = produceTaskDetailMapper.selectByZsProduceTaskDetail(ptd);
                        if (null == produceTaskDetail) {
                            //新建生产任务对应产品详情
                            ptd.setWeight(lsd.getOutWeight());
                            ptd.setProduceTaskId(yhLeaveStock.getProduceTaskId());
                            produceTaskDetailMapper.newProduceTaskDetail(ptd, new Date(), userId);
                        } else {
                            //修改生产任务对应产品明细
                            //修改生产任务对应产品详情
                            //修改重量，备注
                            BigDecimal zsweight = produceTaskDetail.getWeight().add(lsd.getOutWeight());
                            Long produceTaskDetailId = produceTaskDetail.getProduceTaskDetailId();
                            produceTaskDetailMapper.updateProduceTaskDetail(zsweight, produceTaskDetailId, new Date(), userId);
                        }
                    }
                }
                json.setSuccess(true);
                List<YhLeaveStockDetail> list = yhLeaveStock.getSaLeaveStockDetails();
                List<YhLeaveStockDetail> list1=new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if ("否".equals(list.get(i).getLoss())) {
                        list1.add(list.get(i));
                    }
                }
                yhLeaveStock.setSaLeaveStockDetails(list1);
                json.setObj(yhLeaveStock);
            } else {
                /**
                 * 绑定二维码
                 */
                if (null != qrCodes && 0 != qrCodes.size()) {
                    for (ZsQrCode code : qrCodes) {
                        code.setCurrentProduceTaskId(yhLeaveStock.getProduceTaskId());
                        code.setCurrentProduceTaskCode(yhLeaveStock.getProduceTaskNo());
                        code.setCurrentProduceTaskName(yhLeaveStock.getProduceTaskName());
                        code.setUseNumber(code.getUseNumber() + 1);
                        code.setUseStatus((byte) 2);
                        code.setBindDate(new Date());
                        code.setBindUserId(userId);
                        code.setBindUserName(userName);
                        code.setUpdateUserId(userId);
                        integer = zsQrCodeMapper.updateZsQrCode(code);
                        if (0 != integer) {
                            ZsQrCodeUse codeUse = new ZsQrCodeUse();
                            codeUse.setQrCodeId(code.getQrCodeId());
                            codeUse.setProduceTaskId(code.getCurrentProduceTaskId());
                            codeUse.setProduceTaskCode(code.getCurrentProduceTaskCode());
                            codeUse.setProduceTaskName(code.getCurrentProduceTaskName());
                            codeUse.setBindDate(code.getBindDate());
                            codeUse.setBindUserId(code.getBindUserId());
                            codeUse.setBindUserName(code.getBindUserName());
                            codeUse.setCreateUserId(code.getBindUserId());
                            integer = zsQrCodeUseMapper.addQrCodeUse(codeUse);
                        }
                    }
                }
                //获取出库单详情list
                List<YhLeaveStockDetail> leaveStockDetailList = yhLeaveStock.getSaLeaveStockDetails();
                /**
                 *新建出库单,返回主键
                 */
                saLeaveStockMapper.InsertLeaveStock(yhLeaveStock, new Date(), userId);
                Long leaveStockId = yhLeaveStock.getLeaveStockId();
                for (YhLeaveStockDetail lsd : leaveStockDetailList) {
                    /**
                     * 新建出库单详情
                     * set出库单id
                     */
                    lsd.setLeaveId(leaveStockId);
                    saLeaveStockDetailMapper.InsertLeaveStockDetail(lsd, new Date(), userId, yhLeaveStock.getProduceTaskId());
                    /**
                     * 修改库存
                     */
                    PZsStock stock = new PZsStock();
                    stock.setProductId(lsd.getProductId());
                    stock.setIsMaterial(lsd.getIsMaterial());
                    stock.setBatchNo(lsd.getBatchNo());
                    stock.setProductStatus(lsd.getProductStatus());
                    stock.setProductSpecName(lsd.getProductSpecName());
                    stock.setWarehouseId(lsd.getWarehouseId());
                    //多条件查询库存记录
                    ZsStock zs = zsStockMapper.getOneZsStock(stock);
                    //获取当前记录剩余库存(weight)
                    BigDecimal weight = zs.getWeight();
                    //获取当前记录剩余出库数量(outWeight)
                    BigDecimal oWeight = zs.getOutWeight();
                    //获取lsd出库数量
                    BigDecimal outWeight = lsd.getOutWeight();
                    //如果库存减为负数,rollback
                    if (weight.subtract(outWeight).compareTo(BigDecimal.valueOf(0)) == -1) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        json.setSuccess(false);
                        return json;
                    } else {
                        zsStockMapper.updateWeight(weight.subtract(outWeight), oWeight.add(outWeight), zs.getStockId());
                    }
                    //再获取修改后的库存
                    BigDecimal restWeight = zsStockMapper.getStockById(zs.getStockId()).getWeight();
                    if (restWeight.compareTo(BigDecimal.valueOf(0)) == 0) {
                        zsStockMapper.updateRestStatus(zs.getStockId());
                    }
                    /**
                     * 新建生产任务对应产品明细
                     * 如果重复就叠加
                     */
                    if ("否".equals(lsd.getLoss())) {
                        zsProduceTaskDetail ptd = new zsProduceTaskDetail();
                        ptd.setUnitId(lsd.getUnitId());
                        ptd.setUnitName(lsd.getUnitName());
                        ptd.setProductSpecName(lsd.getProductSpecName());
                        ptd.setProductId(lsd.getProductId());
                        ptd.setProductName(lsd.getProductName());
                        ptd.setWarehouseId(lsd.getWarehouseId());
                        ptd.setIsMaterial(lsd.getIsMaterial());
                        ptd.setProductStatus(lsd.getProductStatus());
                        ptd.setBatchNo(lsd.getBatchNo());
                        ptd.setStockId(zs.getStockId());
                        //判断是否要叠加
                        ptd.setProduceTaskId(yhLeaveStock.getProduceTaskId());
                        //多条件查询生产任务对应产品明细
                        PZsProduceTaskDetail produceTaskDetail = produceTaskDetailMapper.selectByZsProduceTaskDetail(ptd);
                        if (null == produceTaskDetail) {
                            //新建生产任务对应产品详情
                            ptd.setWeight(lsd.getOutWeight());
                            ptd.setProduceTaskId(yhLeaveStock.getProduceTaskId());
                            produceTaskDetailMapper.newProduceTaskDetail(ptd, new Date(), userId);
                        } else {
                            //修改生产任务对应产品明细
                            //修改生产任务对应产品详情
                            //修改重量，备注
                            BigDecimal aweight = produceTaskDetail.getWeight();
                            BigDecimal bWeight = lsd.getOutWeight();
                            BigDecimal zsweight = aweight.add(bWeight);
                            Long produceTaskDetailId = produceTaskDetail.getProduceTaskDetailId();
                            produceTaskDetailMapper.updateProduceTaskDetail(zsweight, produceTaskDetailId, new Date(), userId);

                        }
                    }
                }
                json.setSuccess(true);
                List<YhLeaveStockDetail> list = yhLeaveStock.getSaLeaveStockDetails();
                List<YhLeaveStockDetail> list1=new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if ("否".equals(list.get(i).getLoss())) {
                        list1.add(list.get(i));
                    }
                }
                yhLeaveStock.setSaLeaveStockDetails(list1);
                json.setObj(yhLeaveStock);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            json.setSuccess(false);
            json.setMsg("false");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return json;
    }

    @Override
    public List<SaLeaveStockDetail> getReturnProduct(Long isMaterial) {
        return saLeaveStockDetailMapper.getReturnProduct(isMaterial);
    }

    @Override
    public List<SaLeaveStockDetail> getReturnProductSpecName(Long productId) {
        return saLeaveStockDetailMapper.getReturnProductSpecName(productId);
    }

    @Override
    public List<SaLeaveStockDetailPara> getSpec(SaLeaveStockDetailPara saLeaveStockDetailPara) {
        return saLeaveStockDetailMapper.getSpec(saLeaveStockDetailPara);
    }

    @Override
    public List<SaLeaveStockDetail> getLossProduct(Long isMaterial) {
        return saLeaveStockDetailMapper.getLossProduct(isMaterial);
    }

    @Override
    public List<SaLeaveStockDetailPara> getLossSpec(SaLeaveStockDetailPara saLeaveStockDetailPara) {
        return saLeaveStockDetailMapper.getLossSpec(saLeaveStockDetailPara);
    }

    @Override
    public Json addProcedureAndFormAttributeValue(SeaCucumberProcedure seaCucumberProcedure, Long userId, String userName) {
        Json json = new Json();
        try {
            Integer integer;
            Long produceTaskId = 0L;
            //获取生产任务id
            Long taskId = seaCucumberProcedure.getProduceTaskId();
            if (null == taskId) {
                /**
                 * 新建生产任务，返回主键
                 */
                zsProduceTaskMapper.newProduceTask(seaCucumberProcedure, new Date(), userId);
                produceTaskId = seaCucumberProcedure.getProduceTaskId();
                //set生产任务id
                seaCucumberProcedure.setProduceTaskId(produceTaskId);
                /**
                 * 新建生产任务工艺信息
                 */
                zsTaskDetailValueMapper.newTaskDetailValue(seaCucumberProcedure, new Date(), userId, produceTaskId);
                /**
                 *新建出库单,返回主键
                 */
                saLeaveStockMapper.InsertLeaveStock(seaCucumberProcedure, new Date(), userId);
                Long leaveStockId = seaCucumberProcedure.getLeaveStockId();
                //获取出库单详情list
                List<YhLeaveStockDetail> leaveStockDetailList = seaCucumberProcedure.getSaLeaveStockDetails();
                for (YhLeaveStockDetail lsd : leaveStockDetailList) {
                    /**
                     * 新建出库单详情
                     * set出库单id
                     */
                    lsd.setLeaveId(leaveStockId);
                    saLeaveStockDetailMapper.InsertLeaveStockDetail(lsd, new Date(), userId, seaCucumberProcedure.getProduceTaskId());
                    /**
                     * 修改库存
                     */
                    PZsStock stock = new PZsStock();
                    stock.setProductId(lsd.getProductId());
                    stock.setIsMaterial(lsd.getIsMaterial());
                    stock.setBatchNo(lsd.getBatchNo());
                    stock.setProductStatus(lsd.getProductStatus());
                    stock.setProductSpecName(lsd.getProductSpecName());
                    stock.setWarehouseId(lsd.getWarehouseId());
                    //多条件查询库存记录
                    ZsStock zs = zsStockMapper.getOneZsStock(stock);
                    //获取当前记录剩余库存(weight)
                    BigDecimal weight = zs.getWeight();
                    //获取当前记录剩余出库数量(outWeight)
                    BigDecimal oWeight = zs.getOutWeight();
                    //获取lsd出库数量
                    BigDecimal outWeight = lsd.getOutWeight();
                    //如果库存减为负数,rollback
                    if (weight.subtract(outWeight).compareTo(BigDecimal.valueOf(0)) == -1) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    } else {
                        zsStockMapper.updateWeight(weight.subtract(outWeight), oWeight.add(outWeight), zs.getStockId());
                    }
                    //再获取修改后的库存
                    BigDecimal restWeight = zsStockMapper.getStockById(zs.getStockId()).getWeight();
                    if (restWeight.compareTo(BigDecimal.valueOf(0)) == 0) {
                        zsStockMapper.updateRestStatus(zs.getStockId());
                    }
                    /**
                     * 新建生产任务对应产品明细
                     * 如果重复就叠加
                     */
                    if ("否".equals(lsd.getLoss())) {
                        zsProduceTaskDetail ptd = new zsProduceTaskDetail();
                        ptd.setUnitId(lsd.getUnitId());
                        ptd.setUnitName(lsd.getUnitName());
                        ptd.setProductSpecName(lsd.getProductSpecName());
                        ptd.setProductId(lsd.getProductId());
                        ptd.setProductName(lsd.getProductName());
                        ptd.setWarehouseId(lsd.getWarehouseId());
                        ptd.setIsMaterial(lsd.getIsMaterial());
                        ptd.setProductStatus(lsd.getProductStatus());
                        ptd.setBatchNo(lsd.getBatchNo());
                        ptd.setStockId(zs.getStockId());
                        //判断是否要叠加
                        ptd.setProduceTaskId(seaCucumberProcedure.getProduceTaskId());
                        //多条件查询生产任务对应产品明细
                        zsProduceTaskDetail produceTaskDetail = produceTaskDetailMapper.selectByZsProduceTaskDetail(ptd);
                        if (null == produceTaskDetail) {
                            //新建生产任务对应产品详情
                            ptd.setWeight(lsd.getOutWeight());
                            ptd.setProduceTaskId(seaCucumberProcedure.getProduceTaskId());
                            produceTaskDetailMapper.newProduceTaskDetail(ptd, new Date(), userId);
                        } else {
                            //修改生产任务对应产品明细
                            //修改生产任务对应产品详情
                            //修改重量，备注
                            BigDecimal zsweight = produceTaskDetail.getWeight().add(lsd.getOutWeight());
                            Long produceTaskDetailId = produceTaskDetail.getProduceTaskDetailId();
                            produceTaskDetailMapper.updateProduceTaskDetail(zsweight, produceTaskDetailId, new Date(), userId);
                        }
                    }
                }
                json.setSuccess(true);
            } else {
                //获取出库单详情list
                List<YhLeaveStockDetail> leaveStockDetailList = seaCucumberProcedure.getSaLeaveStockDetails();
                /**
                 *新建出库单,返回主键
                 */
                saLeaveStockMapper.InsertLeaveStock(seaCucumberProcedure, new Date(), userId);
                Long leaveStockId = seaCucumberProcedure.getLeaveStockId();
                for (YhLeaveStockDetail lsd : leaveStockDetailList) {
                    /**
                     * 新建出库单详情
                     * set出库单id
                     */
                    lsd.setLeaveId(leaveStockId);
                    saLeaveStockDetailMapper.InsertLeaveStockDetail(lsd, new Date(), userId, seaCucumberProcedure.getProduceTaskId());
                    /**
                     * 修改库存
                     */
                    PZsStock stock = new PZsStock();
                    stock.setProductId(lsd.getProductId());
                    stock.setIsMaterial(lsd.getIsMaterial());
                    stock.setBatchNo(lsd.getBatchNo());
                    stock.setProductStatus(lsd.getProductStatus());
                    stock.setProductSpecName(lsd.getProductSpecName());
                    stock.setWarehouseId(lsd.getWarehouseId());
                    //多条件查询库存记录
                    ZsStock zs = zsStockMapper.getOneZsStock(stock);
                    //获取当前记录剩余库存(weight)
                    BigDecimal weight = zs.getWeight();
                    //获取当前记录剩余出库数量(outWeight)
                    BigDecimal oWeight = zs.getOutWeight();
                    //获取lsd出库数量
                    BigDecimal outWeight = lsd.getOutWeight();
                    //如果库存减为负数,rollback
                    if (weight.subtract(outWeight).compareTo(BigDecimal.valueOf(0)) == -1) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        json.setSuccess(false);
                        return json;
                    } else {
                        zsStockMapper.updateWeight(weight.subtract(outWeight), oWeight.add(outWeight), zs.getStockId());
                    }
                    //再获取修改后的库存
                    BigDecimal restWeight = zsStockMapper.getStockById(zs.getStockId()).getWeight();
                    if (restWeight.compareTo(BigDecimal.valueOf(0)) == 0) {
                        zsStockMapper.updateRestStatus(zs.getStockId());
                    }
                    /**
                     * 新建生产任务对应产品明细
                     * 如果重复就叠加
                     */
                    if ("否".equals(lsd.getLoss())) {
                        zsProduceTaskDetail ptd = new zsProduceTaskDetail();
                        ptd.setUnitId(lsd.getUnitId());
                        ptd.setUnitName(lsd.getUnitName());
                        ptd.setProductSpecName(lsd.getProductSpecName());
                        ptd.setProductId(lsd.getProductId());
                        ptd.setProductName(lsd.getProductName());
                        ptd.setWarehouseId(lsd.getWarehouseId());
                        ptd.setIsMaterial(lsd.getIsMaterial());
                        ptd.setProductStatus(lsd.getProductStatus());
                        ptd.setBatchNo(lsd.getBatchNo());
                        ptd.setStockId(zs.getStockId());
                        //判断是否要叠加
                        ptd.setProduceTaskId(seaCucumberProcedure.getProduceTaskId());
                        //多条件查询生产任务对应产品明细
                        PZsProduceTaskDetail produceTaskDetail = produceTaskDetailMapper.selectByZsProduceTaskDetail(ptd);
                        if (null == produceTaskDetail) {
                            //新建生产任务对应产品详情
                            ptd.setWeight(lsd.getOutWeight());
                            ptd.setProduceTaskId(seaCucumberProcedure.getProduceTaskId());
                            produceTaskDetailMapper.newProduceTaskDetail(ptd, new Date(), userId);
                        } else {
                            //修改生产任务对应产品明细
                            //修改生产任务对应产品详情
                            //修改重量，备注
                            BigDecimal aweight = produceTaskDetail.getWeight();
                            BigDecimal bWeight = lsd.getOutWeight();
                            BigDecimal zsweight = aweight.add(bWeight);
                            Long produceTaskDetailId = produceTaskDetail.getProduceTaskDetailId();
                            produceTaskDetailMapper.updateProduceTaskDetail(zsweight, produceTaskDetailId, new Date(), userId);
                        }
                    }
                }
                json.setSuccess(true);
                json.setObj(seaCucumberProcedure);
            }
            if (json.isSuccess()) {
                List<PBaFormAttributeValue> formAttributeValueList = seaCucumberProcedure.getFormAttributeValueList();
                for (PBaFormAttributeValue baFormAttributeValue : formAttributeValueList) {
                    baFormAttributeValue.setTypeIndex(1);
                    String name = "";
                    String typeName = "";
                    if (baFormAttributeValue.getHandleType() == 1) {
                        typeName += "操作记录";
                    } else if (baFormAttributeValue.getHandleType() == 2) {
                        typeName += "审核记录";
                    } else if (baFormAttributeValue.getHandleType() == 3) {
                        typeName += "巡检记录";
                    }
                    if (baFormAttributeValue.getWorkProcessName() != null) {
                        name = baFormAttributeValue.getWorkProcessName();
                    }
                    List<AttributeLogJson> logJsonList = new ArrayList<>();
                    AttributeLogJson logJson = new AttributeLogJson();
                    logJson.setUserId(userId);
                    logJson.setContext("新增-" + name + "-" + typeName);
                    logJson.setCreateDate(new Date());
                    logJsonList.add(logJson);
                    String jsonText = JSON.toJSONString(logJsonList, true);
                    baFormAttributeValue.setLoggerJson(jsonText);
                    baFormAttributeValue.setProduceTaskId(produceTaskId);
                    baFormAttributeValue.setCreateUserId(userId);
                    Integer integer1 = baFormAttributeValueMapper.addBaFormAttributeValue(baFormAttributeValue);
                    if (integer1 == 0) {
                        json.setSuccess(false);
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    }
                }
            } else {
                json.setSuccess(false);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
            if (json.isSuccess()) {
                Boolean boo = zsProduceTaskService.updateTaskStatusById(produceTaskId, userId);
                if (boo) {
                    json.setSuccess(true);
                } else {
                    json.setSuccess(false);
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            json.setSuccess(false);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return json;
    }

    @Override
    public YhLeaveStock getLeaveStockDetailObj(SaLeaveStockDetailPara saLeaveStockDetailPara) {
        YhLeaveStock yhLeaveStock = new YhLeaveStock();
        List<SaLeaveStockDetailPara> allSaLeaveStockDetail = saLeaveStockDetailMapper.getAllSaLeaveStockDetail(saLeaveStockDetailPara, null);
        BeanUtils.copyProperties(allSaLeaveStockDetail.get(0).getSaLeaveStock(), yhLeaveStock);
        YhLeaveStockDetail yh = new YhLeaveStockDetail();
        List<YhLeaveStockDetail> saLeaveStockDetails = new ArrayList<>();
        BeanUtils.copyProperties(allSaLeaveStockDetail.get(0), yh);
        saLeaveStockDetails.add(yh);
        yhLeaveStock.setSaLeaveStockDetails(saLeaveStockDetails);
        return yhLeaveStock;
    }

}
