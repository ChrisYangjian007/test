package com.dalian.sea.service.impl;

import com.dalian.sea.ExcelUtil.ExcelUtil;
import com.dalian.sea.json.Json;
import com.dalian.sea.mapper.PuReceiveDetailMapper;
import com.dalian.sea.mapper.PuReceiveMapper;
import com.dalian.sea.model.PuReceive;
import com.dalian.sea.model.PuReceiveDetail;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.PuReceiveDetailService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 杨文波
 * @date 2018/1/24
 */
@Service
@Slf4j
public class PuReceiveDetailServiceImpl implements PuReceiveDetailService {

    @Autowired
    private PuReceiveDetailMapper puReceiveDetailMapper;

    @Autowired
    private PuReceiveMapper puReceiveMapper;

    @Autowired
    private MapperFactory mapperFactory;

    @Override
    public List<PuReceiveDetaildPara> getReceiveCarryOut(PuReceiveDetaildPara puReceiveDetail, int page, int rows) {
        List<PuReceiveDetaildPara> puReceiveDetailList = null;
        try {
            PageHelper.startPage(page, rows);
            puReceiveDetailList = puReceiveDetailMapper.getReceiveCarryOut(puReceiveDetail);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return puReceiveDetailList;
    }

    /**
     * 根据receiveId获取收货单
     */
    @Override
    public List<PuReceiveDetaildPara> getReceiveByReceiveId(List<Long> idList) {
        return puReceiveDetailMapper.getReceiveByReceiveId(idList);

    }

    /**
     * 根据receiveDetailId获取收货单
     *
     * @param idList
     * @returne
     */
    @Override
    public List<PuReceiveDetaildPara> getReceiveByReceiveDetailId(List<Long> idList) {
        return puReceiveDetailMapper.getReceiveByReceiveDetailId(idList);
    }

    /**
     * 根据receiveDetailId批量删除
     *
     * @param puReceiveDetail
     * @param userId
     * @return
     */
    @Override
    public Boolean deleteReceiveDetailById(PuReceiveDetail puReceiveDetail, Long userId) {
        Boolean res = false;
        Integer result = puReceiveDetailMapper.deleteReceiveDetailById(puReceiveDetail, new Date(), userId);
        if (result > 0) {
            res = true;
        }
        return res;
    }

    @Override
    @Transactional
    public Boolean deleteReceiveDetailAndReceive(PuReceiveDetail puReceiveDetail) {
        boolean result = false;
        try {
            Integer detailRes = puReceiveDetailMapper.deleteReceiveDetailById(puReceiveDetail, puReceiveDetail.getUpdateDate(), puReceiveDetail.getUpdateUserId());
            if (detailRes > 0) {
                List<PuReceiveDetail> puReceiveDetails = puReceiveDetailMapper.getDetailByReceiveId(puReceiveDetail.getReceiveId());
                if (null != puReceiveDetails && !puReceiveDetails.isEmpty()) {
                    Integer res = puReceiveMapper.deleteReceiveById(puReceiveDetail.getReceiveId(), puReceiveDetail.getUpdateUserId(), puReceiveDetail.getUpdateDate());
                    if (res > 0) {
                        result = true;
                    } else {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    }
                } else {
                    result = true;
                }
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            throw e;
        }
        return result;
    }


    @Override
    @Transactional
    public Long insertReceiveDetail(PuReceivePara puReceivePara, Long userId) {
        boolean res = false;
        try {
            PuReceive receive = puReceiveMapper.getReceiveByReceiveNo(puReceivePara);
            if (null != receive) {
                return null;
            }
            Integer integer = puReceiveMapper.insertReceive(puReceivePara, userId, new Date());
            Long id;
            for (PuReceiveDetail detail : puReceivePara.getReceiveDetails()) {
                if (integer > 0) {
                    id = puReceivePara.getReceiveId();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
                    Date now = new Date();
                    StringBuilder sb = new StringBuilder();
                    sb.append("SHXM.");
                    sb.append(sdf.format(now));
                    int randNum = new Random().nextInt(99);
                    sb.append(randNum<10?"0"+randNum:randNum);
                    detail.setQrcode(sb.toString());
                    Integer end = puReceiveDetailMapper.insertReceiveDetail(detail, id, new Date(), userId);
                    if (end > 0) {
                        res = true;
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        if (res) {
            return puReceivePara.getReceiveId();
        }
        return null;
    }

    @Override
    public String getDetailForInsertReceive(Long receiveId) {
        List<PuReceiveDetail> puReceiveDetails = null;
        StringBuilder sb = new StringBuilder();
        try {
            puReceiveDetails = puReceiveDetailMapper.getDetailForInsertReceive(receiveId);
            if (null != puReceiveDetails && !puReceiveDetails.isEmpty()) {
                int i = 0;
                for (PuReceiveDetail detail : puReceiveDetails) {
                    if (i == 0) {
                        sb.append(detail.getReceiveDetailId());
                    } else {
                        sb.append(",");
                        sb.append(detail.getReceiveDetailId());
                    }
                    i++;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return sb.toString();
    }

    @Override
    @Transactional
    public boolean insertReceiveForApi(PuReceiveApi puReceiveApi) {
        boolean res = false;
        try {
            Integer integer = puReceiveMapper.insertReceiveApi(puReceiveApi, puReceiveApi.getUserId(), new Date());
            Long id;
            for (PuReceiveDetail detail : puReceiveApi.getReceiveDetails()) {
                if (integer > 0) {
                    id = puReceiveApi.getReceiveId();
                    Integer end = puReceiveDetailMapper.insertReceiveDetail(detail, id, new Date(), puReceiveApi.getUserId());
                    if (end > 0) {
                        res = true;
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        }
        return res;
    }

    @Override
    @Transactional
    public boolean insertReceiveDetailForApi(PuReceiveDetail puReceiveDetail) {
        boolean res = false;
        try {
            Integer end = puReceiveDetailMapper.insertReceiveDetail(puReceiveDetail, puReceiveDetail.getReceiveId(), new Date(), puReceiveDetail.getCreateUserId());
            if (end > 0) {
                res = true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        }
        return res;
    }

    /**
     * 数据导出
     *
     * @param puReceiveDetail
     * @return
     */
    @Override
    public Boolean exportData(PuReceiveDetaildPara puReceiveDetail, HttpServletRequest request, HttpServletResponse response, Integer[] arr) {
        Boolean boo;
        try {
            List<String> name = new ArrayList<>();
            List<String> namea = new ArrayList<>();
            List<List<?>> view = new ArrayList<>();
            List<String> str = new ArrayList<>();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<String> titles = new ArrayList<>();
            List<List<String>> names = new ArrayList<>();
            List<List<List<?>>> views = new ArrayList<>();
            if (Arrays.asList(arr).contains(1)) {
                puReceiveDetail.setMethod(new Byte("1"));
                List<PuReceiveDetaildPara> puReceiveDetailList = puReceiveDetailMapper.getReceiveCarryOut(puReceiveDetail);
                String title = "已收货";
                name.add("收货编号");
                name.add("货物类型");
                name.add("批次号");
                name.add("产品名称");
                name.add("规格");
                name.add("数量");
                name.add("单位");
                name.add("供应商");
                name.add("发货人");
                name.add("收货人");
                name.add("收货时间");
                name.add("检验结果");
                name.add("货物状态");
                name.add("备注");

                if (puReceiveDetailList.size() > 0) {
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList) {
                        if (null == puReceive.getPuReceive().getReceiveNo()) {
                            str.add("");
                        } else {
                            str.add(puReceive.getPuReceive().getReceiveNo());
                        }
                    }
                    view.add(str);

                    str = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList) {
                        if (null == puReceive.getGoodsType()) {
                            str.add("");
                        } else {
                            str.add(puReceive.getGoodsType());
                        }
                    }
                    view.add(str);

                    str = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList) {
                        if (null == puReceive.getBatchNo()) {
                            str.add("");
                        } else {
                            str.add(puReceive.getBatchNo());
                        }
                    }
                    view.add(str);

                    str = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList) {
                        if (null == puReceive.getProductName()) {
                            str.add("");
                        } else {
                            str.add(puReceive.getProductName());
                        }
                    }
                    view.add(str);

                    str = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList) {
                        if (null == puReceive.getProductSpecName()) {
                            str.add("");
                        } else {
                            str.add(puReceive.getProductSpecName());
                        }
                    }
                    view.add(str);

                    str = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList) {
                        if (null == puReceive.getWeight()) {
                            str.add("");
                        } else {
                            str.add(puReceive.getWeight().toString());
                        }
                    }
                    view.add(str);

                    str = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList) {
                        if (null == puReceive.getUnitName()) {
                            str.add("");
                        } else {
                            str.add(puReceive.getUnitName());
                        }
                    }
                    view.add(str);

                    str = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList) {
                        if (null == puReceive.getPuReceive().getEnterpriseName()) {
                            str.add("");
                        } else {
                            str.add(puReceive.getPuReceive().getEnterpriseName());
                        }

                    }
                    view.add(str);

                    str = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList) {
                        if (null == puReceive.getPuReceive().getDeliverName()) {
                            str.add("");
                        } else {
                            str.add(puReceive.getPuReceive().getDeliverName());
                        }
                    }
                    view.add(str);

                    str = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList) {
                        if (null == puReceive.getPuReceive().getConsignee()) {
                            str.add("");
                        } else {
                            str.add(puReceive.getPuReceive().getConsignee());
                        }
                    }
                    view.add(str);

                    str = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList) {
                        if (null == puReceive.getPuReceive().getDeliverDate()) {
                            str.add("");
                        } else {
                            str.add(formatter.format(puReceive.getPuReceive().getDeliverDate()).toString());
                        }
                    }
                    view.add(str);

                    str = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList) {
                        if (puReceive.getTestResult() == 0) {
                            str.add("未报送");
                        } else if (puReceive.getTestResult() == 1) {
                            str.add("待检验");
                        } else if (puReceive.getTestResult() == 2) {
                            str.add("合格");
                        } else {
                            str.add("不合格");
                        }
                    }
                    view.add(str);

                    str = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList) {
                        if (puReceive.getReceiptStatus() == 1) {
                            str.add("待处理");
                        } else if (puReceive.getReceiptStatus() == 2) {
                            str.add("已退货");
                        } else if (puReceive.getReceiptStatus() == 3) {
                            str.add("已入库");
                        } else {
                            str.add("未打印");
                        }
                    }
                    view.add(str);

                    str = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList) {
                        if (null != puReceive.getPuReceive().getRemark() && "" != puReceive.getPuReceive().getRemark()) {
                            str.add(puReceive.getPuReceive().getRemark());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);
                }
                titles.add(title);
                names.add(name);
                views.add(view);
            }

            if (Arrays.asList(arr).contains(2)) {
                namea.add("收货编号");
                namea.add("单据编号");
                namea.add("货物类型");
                namea.add("批次号");
                namea.add("产品名称");
                namea.add("规格");
                namea.add("数量");
                namea.add("单位");
                namea.add("供应商");
                namea.add("发货人");
                namea.add("收货人");
                namea.add("收货时间");
                namea.add("检验结果");
                namea.add("货物状态");
                namea.add("退货原因");
                namea.add("处理方案");
                namea.add("经手人");
                namea.add("退货时间");
                namea.add("备注1");
                namea.add("备注2");

                List<List<?>> view1 = new ArrayList<>();
                List<String> str1 = new ArrayList<>();
                puReceiveDetail.setMethod(new Byte("2"));
                List<PuReceiveDetaildPara> puReceiveDetailList2 = puReceiveDetailMapper.getReceiveCarryOut(puReceiveDetail);
                String titlet = "已退货";
                if (puReceiveDetailList2.size() > 0) {
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (null == puReceive.getPuReceive().getReceiveNo()) {
                            str1.add("");
                        } else {
                            str1.add(puReceive.getPuReceive().getReceiveNo());
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (null == puReceive.getReturnNo()) {
                            str1.add("");
                        } else {
                            str1.add(puReceive.getReturnNo());
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (null == puReceive.getGoodsType()) {
                            str1.add("");
                        } else {
                            str1.add(puReceive.getGoodsType());
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (null == puReceive.getBatchNo()) {
                            str1.add("");
                        }
                        str1.add(puReceive.getBatchNo());
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (null == puReceive.getProductName()) {
                            str1.add("");
                        } else {
                            str1.add(puReceive.getProductName());
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (null == puReceive.getProductSpecName()) {
                            str1.add("");
                        } else {
                            str1.add(puReceive.getProductSpecName());
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (puReceive.getWeight() == null) {
                            str1.add("");
                        } else {
                            str1.add(puReceive.getWeight().toString());
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (puReceive.getUnitName() == null) {
                            str1.add("");
                        } else {
                            str1.add(puReceive.getUnitName());
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (puReceive.getPuReceive().getEnterpriseName() == null) {
                            str1.add("");
                        } else {
                            str1.add(puReceive.getPuReceive().getEnterpriseName());
                        }

                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (puReceive.getPuReceive().getDeliverName() == null) {
                            str1.add("");
                        } else {
                            str1.add(puReceive.getPuReceive().getDeliverName());
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (puReceive.getPuReceive().getDeliverName() == null) {
                            str1.add("");
                        } else {
                            str1.add(puReceive.getPuReceive().getDeliverName());
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (puReceive.getPuReceive().getDeliverDate() == null) {
                            str1.add("");
                        } else {
                            str1.add(formatter.format(puReceive.getPuReceive().getDeliverDate()));
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (puReceive.getTestResult() == 0) {
                            str1.add("未报送");
                        } else if (puReceive.getTestResult() == 1) {
                            str1.add("待检验");
                        } else if (puReceive.getTestResult() == 2) {
                            str1.add("合格");
                        } else {
                            str1.add("不合格");
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (puReceive.getReceiptStatus() == 1) {
                            str1.add("待处理");
                        } else if (puReceive.getReceiptStatus() == 2) {
                            str1.add("已退货");
                        } else if (puReceive.getReceiptStatus() == 3) {
                            str1.add("已入库");
                        } else {
                            str1.add("未打印");
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (null != puReceive.getReturnReason()) {
                            str1.add(puReceive.getReturnReason());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (null != puReceive.getDisposePlan()) {
                            str1.add(puReceive.getDisposePlan());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (null != puReceive.getHandler()) {
                            str1.add(puReceive.getHandler());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (null != puReceive.getReturnDate()) {
                            str1.add(formatter.format(puReceive.getReturnDate()));
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (null != puReceive.getRemark()) {
                            str1.add(puReceive.getRemark());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    str1 = new ArrayList<>();
                    for (PuReceiveDetaildPara puReceive : puReceiveDetailList2) {
                        if (null != puReceive.getNote()) {
                            str1.add(puReceive.getNote());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);
                }
                titles.add(titlet);
                names.add(namea);
                views.add(view1);
            }
            ExcelUtil.xslPro("收货信息", titles, names, views, request, response);
            boo = true;
        } catch (Exception e) {
            log.error(e.getMessage());
            boo = false;
        }
        return boo;
    }

    @Override
    @Transactional
    public Boolean editReceive(List<PuReceiveDetailPa> puReceiveDetailPas, Long userId) {
        Boolean res = false;
        try {
            for (PuReceiveDetailPa receive : puReceiveDetailPas) {
                Integer a = puReceiveDetailMapper.updateReceiveDetail(receive, new Date(), userId);
                Integer b = puReceiveMapper.updateReceive(receive, userId, new Date());
                if (a > 0 && b > 0) {
                    res = true;
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return res;
    }

    @Override
    @Transactional
    public Json returnGoods(List<PuReceiveDetailPa> puReceiveDetailPas, Long userId) {
        Json json = new Json();
        try {
            String idStr = "";
            for (PuReceiveDetailPa pa : puReceiveDetailPas) {
                /**
                 *如果全部退货
                 */
                if (pa.getWeight().equals(pa.getCount())) {
                    //修改状态
                    pa.setReceiptStatus(new Byte("2"));
                    //原来id
                    pa.setOriginalId(pa.getReceiveDetailId());
                    Integer a = puReceiveDetailMapper.updateReceiveDetail(pa, new Date(), userId);
                    if (a > 0) {
                        json.setSuccess(true);
                        idStr += pa.getReceiveDetailId() + ",";
                    }
                } else {
                    /**
                     *部分退货
                     */
                    //查出receive
                    PuReceive pr = puReceiveMapper.queryReceive(pa);
                    //新建一条receive
                    puReceiveMapper.copyOne(pr, userId, new Date());
                    //查出receiveDetail
                    PuReceiveDetail pu = puReceiveDetailMapper.queryOne(pa.getReceiveDetailId());
                    PuReceiveDetailPa puReceiveDetail = new PuReceiveDetailPa();
                    //receiveId
                    puReceiveDetail.setReceiveId(pr.getReceiveId());
                    //批次号
                    puReceiveDetail.setBatchNo(pu.getBatchNo());
                    //产品id
                    puReceiveDetail.setProductId(pu.getProductId());
                    //产品名称
                    puReceiveDetail.setProductName(pu.getProductName());
                    //货物类型id
                    puReceiveDetail.setGoodsTypeId(pu.getGoodsTypeId());
                    //货物类型
                    puReceiveDetail.setGoodsType(pu.getGoodsType());
                    //规格
                    puReceiveDetail.setProductSpecName(pu.getProductSpecName());
                    //单位id
                    puReceiveDetail.setUnitId(pu.getUnitId());
                    //单位名称
                    puReceiveDetail.setUnitName(pu.getUnitName());
                    //货物状态
                    puReceiveDetail.setReceiptStatus(new Byte("2"));
                    //检验结果
                    puReceiveDetail.setTestResult(pu.getTestResult());
                    //退货时间
                    puReceiveDetail.setReturnDate(pa.getReturnTime());
                    //原始Id
                    puReceiveDetail.setOriginalId(pa.getReceiveDetailId());
                    //退货数量
                    puReceiveDetail.setCount(pa.getCount());
                    //退货编号
                    puReceiveDetail.setReturnNo(pa.getReturnNo());
                    //退货时间
                    puReceiveDetail.setReturnDate(pa.getReturnTime());
                    //退货原因
                    puReceiveDetail.setReturnReason(pa.getReturnReason());
                    //处理方案
                    puReceiveDetail.setDisposePlan(pa.getDisposePlan());
                    //经手人
                    puReceiveDetail.setHandler(pa.getHandler());
                    //备注1
                    puReceiveDetail.setRemark(pa.getRemark());
                    //备注2
                    puReceiveDetail.setNote(pa.getNote());
                    //新建一条receiveDetail
                    Integer c = puReceiveDetailMapper.newOne(puReceiveDetail, new Date(), userId);
                    Integer d = puReceiveDetailMapper.updateWeight(pa, new Date(), userId);
                    if (c > 0 && d > 0) {
                        idStr += puReceiveDetail.getReceiveDetailId() + ",";
                        json.setSuccess(true);
                    }
                }
            }
            json.setObj(idStr);
        } catch (Exception e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return json;
    }

    @Override
    public PuReceiveDetail getByReceiveDetailId(Long receiveDetailId) {
        PuReceiveDetail puReceiveDetail = puReceiveDetailMapper.getReceiveDetailById(receiveDetailId);
        return puReceiveDetail;
    }

    @Override
    public boolean updateByReceiveDetailId(PuReceiveDetail puReceiveDetail) {
        boolean result = false;
        Integer res = puReceiveDetailMapper.updateByReceiveDetailId(puReceiveDetail);
        if (res > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public List<PuReceiveDetail> getDetailByReceiveId(Long receiveId) {
        return puReceiveDetailMapper.getDetailByReceiveId(receiveId);
    }

    @Override
    public List<PuReceiveDetaildPara> getReceiveDetailByReceiveNo(String receiveNo) {
        return puReceiveDetailMapper.getReceiveDetailByReceiveNo(receiveNo);
    }

    @Override
    public List<PuReceiveDetaildPara> getReceiveDetailByBatchNo(PuReceiveDetaildPara receiveDetaildPara) {
        return puReceiveDetailMapper.getReceiveDetailByBatchNo(receiveDetaildPara);
    }

    @Override
    public List<PuReceiveDetail> getGoodsTypeByReceiveBatchNo(PuReceiveDetail puReceiveDetail) {
        return puReceiveDetailMapper.getGoodsTypeByReceiveBatchNo(puReceiveDetail);
    }

    @Override
    @Transactional
    public List<JasperReportData> getPrintReceivePdf(String[] receiveDetailId) {
        List<Long> receiveDetailIds = new ArrayList<>();
        List<JasperReportData> jasperReportDatas = null;
        for (int i = 0; i < receiveDetailId.length; i++) {
            receiveDetailIds.add(new Long(receiveDetailId[i]));
        }
        try {
            List<PuReceiveDetaildPara> details = puReceiveDetailMapper.getReceiveByReceiveDetailId(receiveDetailIds);
            if (null != details && !details.isEmpty()) {
                jasperReportDatas = new ArrayList<>();
                Long i = new Long("1");
                for (PuReceiveDetaildPara para : details) {
                    JasperReportData data = new JasperReportData(i, details.size(), para.getPuReceive().getEnterpriseName(),
                            new Timestamp(para.getPuReceive().getDeliverDate().getTime()), para.getPuReceive().getReceiveNo(), para.getPuReceive().getDeliverName(),
                            para.getPuReceive().getConsignee(), para.getPuReceive().getRemark(), para.getBatchNo(), para.getGoodsType(), para.getProductName(),
                            para.getWeight(), para.getUnitName(), para.getNote(), para.getProductSpecName());
                    jasperReportDatas.add(data);
                    i += 1;
                }
                puReceiveDetailMapper.updateIsPrint(receiveDetailIds);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return jasperReportDatas;
    }

    @Override
    public List<JasperReturnData> getPrintReceiveReturn(String[] receiveDetailId) {
        List<Long> receiveDetailIds = new ArrayList<>();
        List<JasperReturnData> jasperReturnData = null;
        for (int i = 0; i < receiveDetailId.length; i++) {
            receiveDetailIds.add(new Long(receiveDetailId[i]));
        }
        try {
            List<PuReceiveDetaildPara> details = puReceiveDetailMapper.getReceiveByReceiveDetailId(receiveDetailIds);
            if (null != details && !details.isEmpty()) {
                jasperReturnData = new ArrayList<>();
                Long i = new Long("1");
                for (PuReceiveDetaildPara para : details) {
                    JasperReturnData data = new JasperReturnData(i, details.size(), para.getPuReceive().getEnterpriseName(), new Timestamp(para.getReturnDate().getTime()), para.getReturnNo(), para.getDisposePlan(), para.getReturnReason()
                            , para.getHandler(), para.getNote(), para.getBatchNo(), para.getGoodsType(), para.getProductName(), para.getWeight(), para.getUnitName(), para.getNote(), para.getProductSpecName());
                    jasperReturnData.add(data);
                    i += 1;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return jasperReturnData;
    }

    @Override
    public ReceiveEcharts getWeekReceive() {
        ReceiveEcharts re = new ReceiveEcharts();
        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int n = cal.get(Calendar.DAY_OF_WEEK);
        if (n == 1) {
            n = 7;
        } else {
            n = n - 1;
        }
        //今天是周n
        // 日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<BigDecimal> hsList = new ArrayList<>();
        List<BigDecimal> byList = new ArrayList<>();
        List<BigDecimal> xrList = new ArrayList<>();
        List<BigDecimal> bcList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            Calendar calendar = Calendar.getInstance();
            //本周每天的日期
            calendar.set(Calendar.DAY_OF_MONTH, date + i - n);
            String format = sdf.format(calendar.getTime());
            //海参
            BigDecimal hsCount = puReceiveDetailMapper.getHsWeightByDate(format);
            if (null == hsCount) {
                hsCount = BigDecimal.valueOf(0);
            }
            //鲍鱼
            BigDecimal byCount = puReceiveDetailMapper.getByWeightByDate(format);
            if (null == byCount) {
                byCount = BigDecimal.valueOf(0);
            }
            //虾仁
            BigDecimal xrCount = puReceiveDetailMapper.getXrWeightByDate(format);
            if (null == xrCount) {
                xrCount = BigDecimal.valueOf(0);
            }
            //包材
            BigDecimal bcCount = puReceiveDetailMapper.getBcWeightByDate(format);
            if (null == bcCount) {
                bcCount = BigDecimal.valueOf(0);
            }
            hsList.add(hsCount);
            byList.add(byCount);
            xrList.add(xrCount);
            bcList.add(bcCount);
        }
        re.setHsList(hsList);
        re.setByList(byList);
        re.setXrList(xrList);
        re.setBcList(bcList);
        return re;
    }

    @Override
    public ReceiveEcharts getMonthReceive() {
        ReceiveEcharts re = new ReceiveEcharts();
        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<BigDecimal> hsList = new ArrayList<>();
        List<BigDecimal> byList = new ArrayList<>();
        List<BigDecimal> xrList = new ArrayList<>();
        List<BigDecimal> bcList = new ArrayList<>();
        for (int i = 1; i <= date; i++) {
            //本月每天的日期
            cal.set(Calendar.DAY_OF_MONTH, date + i - date);
            String format = sdf.format(cal.getTime());
            //海参
            BigDecimal hsCount = puReceiveDetailMapper.getHsWeightByDate(format);
            if (null == hsCount) {
                hsCount = BigDecimal.valueOf(0);
            }
            //鲍鱼
            BigDecimal byCount = puReceiveDetailMapper.getByWeightByDate(format);
            if (null == byCount) {
                byCount = BigDecimal.valueOf(0);
            }
            //虾仁
            BigDecimal xrCount = puReceiveDetailMapper.getXrWeightByDate(format);
            if (null == xrCount) {
                xrCount = BigDecimal.valueOf(0);
            }
            //包材
            BigDecimal bcCount = puReceiveDetailMapper.getBcWeightByDate(format);
            if (null == bcCount) {
                bcCount = BigDecimal.valueOf(0);
            }
            hsList.add(hsCount);
            byList.add(byCount);
            xrList.add(xrCount);
            bcList.add(bcCount);
        }
        re.setHsList(hsList);
        re.setByList(byList);
        re.setXrList(xrList);
        re.setBcList(bcList);
        return re;
    }

    @Override
    public ReceiveEcharts getYearReceive() {
        ReceiveEcharts re = new ReceiveEcharts();
        Calendar cal = Calendar.getInstance();
        //当前月份 3=4月
        int date = cal.get(Calendar.MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        List<BigDecimal> hsList = new ArrayList<>();
        List<BigDecimal> byList = new ArrayList<>();
        List<BigDecimal> xrList = new ArrayList<>();
        List<BigDecimal> bcList = new ArrayList<>();
        for (int i = 0; i <= date; i++) {
            //本年的每月
            cal.set(Calendar.MONTH, date + i - date);
            String format = sdf.format(cal.getTime());
            //海参
            BigDecimal hsCount = puReceiveDetailMapper.getHsWeightByMonth(format);
            if (null == hsCount) {
                hsCount = BigDecimal.valueOf(0);
            }
            //鲍鱼
            BigDecimal byCount = puReceiveDetailMapper.getByWeightByMonth(format);
            if (null == byCount) {
                byCount = BigDecimal.valueOf(0);
            }
            //虾仁
            BigDecimal xrCount = puReceiveDetailMapper.getXrWeightByMonth(format);
            if (null == xrCount) {
                xrCount = BigDecimal.valueOf(0);
            }
            //包材
            BigDecimal bcCount = puReceiveDetailMapper.getBcWeightByMonth(format);
            if (null == bcCount) {
                bcCount = BigDecimal.valueOf(0);
            }
            hsList.add(hsCount);
            byList.add(byCount);
            xrList.add(xrCount);
            bcList.add(bcCount);
        }
        re.setHsList(hsList);
        re.setByList(byList);
        re.setXrList(xrList);
        re.setBcList(bcList);
        return re;
    }

    @Override
    public List<PuReceiveDetaildPara> getByReceiveNoAndReceiptStatus(PuReceiveDetaildPara receiveDetaildPara) {
        return puReceiveDetailMapper.getAllReceiveByReceiveNo(receiveDetaildPara);
    }
}
