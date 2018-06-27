package com.dalian.sea.service.impl;

import Utils.GenerateUtils;
import com.dalian.sea.mapper.*;
import com.dalian.sea.model.BoxOrder;
import com.dalian.sea.model.SaOrder;
import com.dalian.sea.model.SaOrderDetail;
import com.dalian.sea.model.TagRange;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.SaOrderService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author 杨建
 * @date 2018/3/23
 */
@Service("orderService")
public class SaOrderServiceImpl implements SaOrderService {
    @Autowired
    private SaOrderMapper orderMapper;
    @Autowired
    private BoxOrderMapper boxOrderMapper;
    @Autowired
    private SaOrderDetailMapper orderDetailMapper;
    @Autowired
    private TagRangeMapper tagRangeMapper;
    @Autowired
    private ZsBatchMapper batchMapper;


    @Override
    public List<PSaOrder> selectListOrderByOrder(PSaOrder order, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return orderMapper.selectListOrderByOrder(order);
    }

    @Override
    public List<PSaOrder> selectListOrderdown(PSaOrder order) {

        return orderMapper.selectListOrderByOrder(order);
    }


    @Override
    public PSaOrder selectSaOrderByName(PSaOrder order) {
        return orderMapper.getOrderByOrderName(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteOrderByQrBox(String boxCode, String qrCode) {
        try {
            BoxOrder boxOrder = new BoxOrder();
            boxOrder.setBoxCode(boxCode);
            BoxOrder boxOrderByBoxCode = boxOrderMapper.getBoxOrderByBoxCode(boxCode);
            //获取该箱里的所有订单明细
            String orderDetailCode = boxOrderByBoxCode.getOrderDetailCode();
            //明细数组
            String[] array = orderDetailCode.split(",");
            //订单明细转集合
            List detailListex = Arrays.asList(array);
            List detailList;
            detailList = new ArrayList(detailListex);
            //获取该箱的所有二维码信息
            String orderDetailQr = boxOrderByBoxCode.getOrderDetailQr();
            //明细数组
            String[] arrayqr = orderDetailQr.split(",");
            //订单明细转集合
            List<String> qrListex = Arrays.asList(arrayqr);
            List qrList;
            qrList = new ArrayList(qrListex);
            String[] split = qrCode.split(",");
            for (int i = 0; i < split.length; i++) {
                POrderDetail pOrderDetail1 = new POrderDetail();
                pOrderDetail1.setOrderDetailQrcode(split[i]);
                POrderDetail p = orderDetailMapper.getOrderDetailByQrCode(pOrderDetail1);
                for (int j = 0; j < detailList.size(); j++) {
                    if (detailList.get(j).equals(p.getOrderDetailName())) {
                        detailList.remove(j);
                        break;
                    }
                }
                //删除箱码中的二维码
                qrList.remove(split[i]);
                //获取当前订单明细的所有二维码
                String qr = p.getOrderDetailQrcode();
                String[] s = qr.split(",");
                List asListex = Arrays.asList(s);
                List asList;
                asList = new ArrayList(asListex);
                for (int j = 0; j < asList.size(); j++) {
                    if (asList.get(j).equals(split[i])) {
                        asList.remove(j);
                    }
                }
                pOrderDetail1.setOrderDetailQrcode(StringUtils.join(asList, ","));
                pOrderDetail1.setOrderDetailId(p.getOrderDetailId());
                orderDetailMapper.updateByOrderDetailSelective(pOrderDetail1);
            }
            boxOrderByBoxCode.setOrderDetailCode(StringUtils.join(detailList, ","));
            boxOrderByBoxCode.setOrderDetailQr(StringUtils.join(qrList, ","));
            boxOrderMapper.updateBoxOrderByOrder(boxOrderByBoxCode);
            return true;

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addOrderQrBox(String boxCode, String orderCode, String qrCode) {
        try {
            BoxOrder box = boxOrderMapper.getBoxOrderByBoxCode(boxCode);
            String detailCode = box.getOrderDetailCode();
            String orderDetailQr = box.getOrderDetailQr();
            StringBuilder sb = new StringBuilder();
            StringBuilder sb1 = new StringBuilder();
            POrderDetail pOrderDetail = new POrderDetail();
            pOrderDetail.setOrderDetailDhsq(orderCode);
            List<POrderDetail> pOrderDetails = orderDetailMapper.selectListOrderDetailByOrder(pOrderDetail);
            String code = qrCode.substring(qrCode.length() - 14, qrCode.length());
            //获取明码
            String clearCode = GenerateUtils.zstoCode(code);
            //获取标签规则
            String ruleName = code.substring(0, 4);
            PTagRange range = new PTagRange();
            range.setClearCode(Integer.valueOf(clearCode));
            range.setRuleName(ruleName);
            TagRange tagRangeByRange = tagRangeMapper.getTagRangeByRange(range);
            if (tagRangeByRange != null) {
                PZsBatch batch = new PZsBatch();
                batch.setBatchId(tagRangeByRange.getBatchId());
                PZsBatch batchByBatch = batchMapper.selectBatchByBatchUnique(batch);

                for (int j = 0; j < pOrderDetails.size(); j++) {
                    POrderDetail d = pOrderDetails.get(j);
                    if (batchByBatch.getProductCode().equals(d.getOrderDetailCpbh())) {
                        String qrcode = d.getOrderDetailQrcode();
                        String[] array = qrcode.split(",");
                          List asListex = Arrays.asList(array);
                          List asList=new ArrayList(asListex);
                        //当已经绑定的二维码小于订单明细个数，才允许添加二维码
                        if (asList.size() < Integer.valueOf(d.getOrderDetailSl())) {
                            asList.add(qrCode);
                            String join = StringUtils.join(asList, ",");
                            SaOrderDetail detail = new SaOrderDetail();
                            detail.setOrderDetailId(d.getOrderDetailId());
                            detail.setOrderDetailQrcode(join);
                            orderDetailMapper.updateByOrderDetailSelective(detail);
                            //每次往订单明细添加一个二维码 向箱码添加一个订单明细
                            sb.append(d.getOrderDetailName());
                            sb.append(",");
                            //每次往订单明细添加一个二维码 向箱码添加一个二维码
                            sb1.append(qrCode);
                            sb1.append(",");
                            sb.append(detailCode);
                            sb1.append(orderDetailQr);
                            BoxOrder boxOrder = new BoxOrder();
                            boxOrder.setBoxOrderId(box.getBoxOrderId());
                            boxOrder.setOrderDetailCode(sb.toString());
                            boxOrder.setOrderDetailQr(sb1.toString());
                            boxOrderMapper.updateBoxOrderByOrder(boxOrder);
                            return "添加成功";
                        } else {
                            return "当前订单二维码已达上限,不可添加";
                        }
                    }
                }
            }
            return "二维码信息不匹配订单信息";
        } catch (Exception e) {
            return "服务器异常";
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOrderQrBox(String boxCode, String oldQrCode, String newQrCode) {
        try {
            BoxOrder box = boxOrderMapper.getBoxOrderByBoxCode(boxCode);
            String detailQr = box.getOrderDetailQr();
            String[] array = detailQr.split(",");
            List asListex = Arrays.asList(array);
            List asList=new ArrayList<>(asListex);
            asList.remove(oldQrCode);
            asList.add(newQrCode);
            box.setOrderDetailQr(StringUtils.join(asList, ","));
            boxOrderMapper.updateBoxOrderByOrder(box);
            POrderDetail detail = new POrderDetail();
            detail.setOrderDetailQrcode(oldQrCode);
            POrderDetail detailOrder = orderDetailMapper.getOrderDetailByQrCode(detail);
            String detailQrcode = detailOrder.getOrderDetailQrcode();
            String[] arrayCode = detailQrcode.split(",");
            List asListDetailex = Arrays.asList(arrayCode);
            List asListDetail =new ArrayList(asListDetailex);
            asListDetail.remove(oldQrCode);
            asListDetail.add(newQrCode);
            SaOrderDetail saOrderDetail = new SaOrderDetail();
            saOrderDetail.setOrderDetailId(detailOrder.getOrderDetailId());
            saOrderDetail.setOrderDetailQrcode(StringUtils.join(asListDetail, ","));
            orderDetailMapper.updateByOrderDetailSelective(saOrderDetail);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public List<PSaOrder> selectListOrderByOrder(PSaOrder order) {
        return orderMapper.selectListOrderByOrder(order);
    }

    @Override
    public List<PSaOrder> getTodayOrder() {
        return orderMapper.getTodayOrder();
    }

    @Override
    public Integer todayOrderCount() {
        return orderMapper.todayOrderCount();
    }

    @Override
    public Integer todayFinishedCount() {
        return orderMapper.todayFinishedCount();
    }

    @Override
    public Future<Boolean> updateOrderByList(List<SaOrder> orderList) {
        if (orderList.size() > 0) {
            orderMapper.updateOrderByList(orderList);
        }
        return new AsyncResult<Boolean>(true);
    }

    @Override
    @Async
    public Future<Boolean> orderSynchronization(List<OrderInfo> objects) {
        try {
            for (int i = 0; i < objects.size(); i++) {
                OrderInfo orderInfo = objects.get(i);
                PSaOrder saOrder = new PSaOrder();
                //订货申请编号
                saOrder.setOrderName(orderInfo.getName());
                PSaOrder orderByOrderName = orderMapper.getOrderByOrderName(saOrder);
                //申请人
                if (orderInfo.getOwneridccname() != null) {
                    saOrder.setOrderOwnerid(orderInfo.getOwneridccname());
                }
                //订货日期
                if (orderInfo.getDhrq() != null) {
                    try {
                        saOrder.setOrderDhrq(new SimpleDateFormat("yyyy-MM-dd").parse(orderInfo.getDhrq()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                //订货人
                if (orderInfo.getShr() != null) {
                    saOrder.setOrderShr(orderInfo.getShr());
                }
                //收货地址
                if (orderInfo.getShdz() != null) {
                    saOrder.setOrderShdz(orderInfo.getShdz());
                }
                //联系电话
                if (orderInfo.getLxdh() != null) {
                    saOrder.setOrderLxdh(orderInfo.getLxdh());
                }
                //审批状态
                if (orderInfo.getSpzt() != null) {
                    saOrder.setOrderSpzt(orderInfo.getSpzt());
                }
                //所属门店
                if (orderInfo.getSsmdccname() != null) {
                    saOrder.setOrderSsmd(orderInfo.getSsmdccname());
                }
                //经销商
                if (orderInfo.getJxsccname() != null) {
                    saOrder.setOrderIsjxs((byte) 1);
                    saOrder.setOrderJxs(orderInfo.getJxsccname());
                } else {
                    saOrder.setOrderIsjxs((byte) 0);
                }
                if (orderByOrderName != null) {
                    saOrder.setOrderId(orderByOrderName.getOrderId());
                    orderMapper.updateByOrderSelective(saOrder);
                } else {
                    orderMapper.insertOrderSelective(saOrder);
                }

            }
            return new AsyncResult<Boolean>(true);

        } catch (Exception e) {
            throw e;
        }
    }
}
