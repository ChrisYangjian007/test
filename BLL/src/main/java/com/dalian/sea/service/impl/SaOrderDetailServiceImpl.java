package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BoxOrderMapper;
import com.dalian.sea.mapper.SaOrderDetailMapper;
import com.dalian.sea.mapper.ZsExpressMapper;
import com.dalian.sea.model.BoxOrder;
import com.dalian.sea.model.ZsExpress;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.SaOrderDetailService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 *
 * @author 杨建
 * @date 2018/3/25
 */
@Service("orderDetailService")
public class SaOrderDetailServiceImpl implements SaOrderDetailService {
   @Autowired
   private SaOrderDetailMapper orderDetailMapper;
   @Autowired
   private BoxOrderMapper boxOrderMapper;
   @Autowired
   private ZsExpressMapper zsExpressMapper;

    @Override
    public List<POrderDetail> selectListOrderDetailByOrder(POrderDetail orderDetail, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<POrderDetail> pOrderDetails = orderDetailMapper.selectListOrderDetailByOrder(orderDetail);
        //查询箱码
        for (POrderDetail detail: pOrderDetails
             ) {
            BoxOrder code = boxOrderMapper.getBoxCodeByOrderDetailCode(detail.getOrderDetailName());
            if (code!=null){
                detail.setBoxCode(code.getBoxCode());
//                ZsExpress zsExpress = new ZsExpress();
//                zsExpress.setBoxCode(code.getBoxCode());
//                ZsExpress zsExpressByBoxCode = zsExpressMapper.getZsExpressByBoxCode(zsExpress);
//                if (zsExpressByBoxCode!=null){
//                    detail.setExpressForm(zsExpressByBoxCode.getExpressCode());
//                }else {
//                    detail.setExpressForm();
//                }

            }
        }
        return pOrderDetails;
    }

    @Override
    public List<POrderDetail> selectListOrderDetailDown(POrderDetail orderDetail) {
        return orderDetailMapper.selectListOrderDetailByOrder(orderDetail);
    }

    @Override
    public POrderDetail selectOrderDetailByQrCode(POrderDetail orderDetail) {
        return orderDetailMapper.getOrderDetailByQrCode(orderDetail);
    }

    @Override
    public List<POrderDetail> getOrderDetailCountToday() {
        return orderDetailMapper.selectListOrderDetailCountToday();
    }

    @Override
    public List<POrderDetail> getOrderDetailCountbyday(POrderDetail orderDetail, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        return orderDetailMapper.selectListOrderDetailCountbyday(orderDetail);
    }

    @Override
    @Async
    public Future<Boolean> updateOrderdetailByBatch(List<POrderDetail> orderDetailList) {
        if (orderDetailList.size() > 0) {
            orderDetailMapper.updateOrderDetailByBatch(orderDetailList);
        }
        return new AsyncResult<Boolean>(true);
    }
    @Override
    @Async
    public Future<Boolean> orderDetailSynchronization(List<OrderDetailInfo> objects) {
        try {

            for (int i = 0; i < objects.size(); i++) {
                OrderDetailInfo orderDetailInfo = objects.get(i);
                POrderDetail orderDetail = new POrderDetail();
                //订货申请编号
                orderDetail.setOrderDetailName(orderDetailInfo.getName());
                POrderDetail orderDetailByOrderName = orderDetailMapper.getOrderDetailByOrderName(orderDetail);
                if (orderDetailInfo.getDhsqccname() != null) {
                    orderDetail.setOrderDetailDhsq(orderDetailInfo.getDhsqccname());
                }
                if (orderDetailInfo.getCpbmccname() != null) {
                    orderDetail.setOrderDetailCpbh(orderDetailInfo.getCpbmccname());
                }
                if (orderDetailInfo.getCpmc() != null) {
                    orderDetail.setProductName(orderDetailInfo.getCpmc());
                }
                if (orderDetailInfo.getGg() != null) {
                    orderDetail.setOrderDetailGg(orderDetailInfo.getGg());
                }
                if (orderDetailInfo.getDw() != null) {
                    orderDetail.setOrderDetailDw(orderDetailInfo.getDw());
                }
                if (orderDetailInfo.getCbj() != null) {
                    orderDetail.setOrderDetailCbj(orderDetailInfo.getCbj());
                }
                if (orderDetailInfo.getSl() != null) {
                    orderDetail.setOrderDetailSl(orderDetailInfo.getSl());
                }
                if (orderDetailInfo.getZk() != null) {
                    orderDetail.setOrderDetailZk(orderDetailInfo.getZk());
                }
                if (orderDetailInfo.getBz() != null) {
                    orderDetail.setRemark(orderDetailInfo.getBz());
                }
                if (orderDetailInfo.getCreatedate() != null) {
                    try {
                        orderDetail.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderDetailInfo.getCreatedate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (orderDetailByOrderName != null) {
                    orderDetail.setOrderDetailId(orderDetailByOrderName.getOrderDetailId());
                    orderDetailMapper.updateByOrderDetailSelective(orderDetail);
                } else {
                    orderDetailMapper.insertOrderDetailSelective(orderDetail);
                }
            }
            return new AsyncResult<Boolean>(true);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 通过二维码获取
     * @param qrCode
     * @return
     */
    @Override
    public PSaOrderDetail getOrderAndOrderDetailByQrCode(String qrCode) {
        return orderDetailMapper.getOrderAndOrderDetailByQrCode(qrCode);
    }

    @Override
    public List<OrderDetailQr> selectOrderQrBy(PSaOrder order) {
        return  orderDetailMapper.selectOrderDetailQrList(order);
    }

    @Override
    public List<OrderDetailBoxCode> selectOrderBoxBy(PSaOrder saOrder) {
        List<OrderDetailBoxCode> list = orderDetailMapper.selectOrderDetailBoxList(saOrder);
        for (OrderDetailBoxCode box:list
             ) {
            String orderDetailList = box.getOrderDetailString();
            if (orderDetailList!=null && orderDetailList.length()>0){
                String[] split = orderDetailList.split(",");
                 /*2、分组算法**/
                Map<String, List<String>> helpMap = new HashMap<>();

                for (String s:split
                     ) {
                    List<String> tempList = helpMap.get(s);
                      /*如果取不到数据,那么直接new一个空的ArrayList**/
                    if (tempList == null) {
                        tempList = new ArrayList<>();
                        tempList.add(s);
                        helpMap.put(s, tempList);
                    } else {
                /*某个help之前已经存放过了,则直接追加数据到原来的List里**/
                        tempList.add(s);
                    }
                }
                List<OrderDetailNum> orderDetailNums=new ArrayList<>();
                // 遍历方法四 treemap keySet()遍历
                for (String o : helpMap.keySet()) {
                    OrderDetailNum orderDetailNum = new OrderDetailNum();
                    orderDetailNum.setOrderDetailName(o);
                    orderDetailNum.setNumber(helpMap.get(o).size());
                    orderDetailNums.add(orderDetailNum);
                }
                box.setOrderDetailList(orderDetailNums);
            }
        }
        return list;
    }

    @Override
    public List<POrderDetail> selectOrderDetailByOrderList(List<String> list) {

        return orderDetailMapper.selectOrderDetailListByOrderList(list);
    }


}
