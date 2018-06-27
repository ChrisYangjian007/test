package com.dalian.sea.api;

import Utils.GenerateUtils;
import com.dalian.sea.json.JqGridParam;
import com.dalian.sea.json.Json;
import com.dalian.sea.model.BoxOrder;
import com.dalian.sea.model.TagRange;
import com.dalian.sea.model.ZsProduceTask;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * AndroidOrderApi
 *
 * @author TONE
 * @date 2018/3/28.
 */
@RestController
@RequestMapping("/order")
public class AndroidOrderApi {
    @Autowired
    private SaOrderService saOrderService;
    @Autowired
    private ZsBoxOrderService boxOrderService;
    @Autowired
    private SaOrderDetailService orderDetailService;
    @Autowired
    private ZsBatchService zsBatchService;
    @Autowired
    private TagRangeService tagRangeService;
    @Autowired
    private SaLeaveStockDetailService saLeaveStockDetailService;
    @Autowired
    private BaFormAttributeValueService baFormAttributeValueService;
    @Autowired
    private ZsProduceTaskService zsProduceTaskService;

    /**
     * 查询箱码
     */
    @PostMapping("/queryOrder.json")
    public Object queryOrder(String boxCode) {
        Json json = new Json();
        try {
            if (boxCode.length()<17){
                json.setMsg("箱码不符合规则");
                return json;
            }
            String sb = boxCode.substring(boxCode.length() - 17, boxCode.length());
            if (null != sb) {
                BoxOrder boxOrder = boxOrderService.getBoxOrderByBoxCode(sb);
                if (null != boxOrder) {
                    PSaOrderPDA saOrderPDA = boxOrderService.getBoxOrderByBoxCodeForPDA(boxOrder);
                    saOrderPDA.setBoxOrder(boxOrder);
                    json.setObj(saOrderPDA);
                } else {
                    json.setMsg("无当前箱码数据！");
                }
            } else {
                json.setMsg("缺少箱码，无法获取信息!");
            }
            json.setSuccess(true);
        } catch (Exception e) {
            json.setMsg("服务器异常!");
        }
        return json;
    }

    /**
     * 查询订单详情
     */
    @PostMapping("/queryOrderDetails.json")
    public Object queryOrderDetails(PSaOrder saOrder, Integer page, Integer rows) {
        Json json = new Json();
        try {
            if (null != saOrder.getOrderName()) {
                if (null != page && null != rows) {
                    saOrder = saOrderService.selectSaOrderByName(saOrder);
                    if (null != saOrder) {
                        PSaOrderDetailsPDA saOrderDetailsPDA = new PSaOrderDetailsPDA();
                        saOrderDetailsPDA.setSaOrder(saOrder);
                        List<BoxOrder> boxOrderList = boxOrderService.getBoxOrderByOrderCodeForPDA(saOrder.getOrderName(), page, rows);
                        if (null != boxOrderList && 0 != boxOrderList.size()) {
                            PageInfo<BoxOrder> pageInfo = new PageInfo<>(boxOrderList);
                            List<PSaOrderPDA> saOrderPDAList = boxOrderService.getBoxOrderByBoxCodeListForPDA(boxOrderList);
                            saOrderDetailsPDA.setSaOrderPDAList(saOrderPDAList);
                            json.setObj(new JqGridParam(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), saOrderDetailsPDA));
                        } else {
                            json.setMsg("当前订单编码无箱码数据");
                        }
                    } else {
                        json.setMsg("当前订单编码数据无");
                    }
                } else {
                    json.setMsg("无分页参数,无法获取数据！");
                }
            } else {
                json.setMsg("无订单编码,无法获取数据！");
            }
            json.setSuccess(true);
        } catch (Exception e) {
            json.setMsg("服务器异常!");
        }
        return json;
    }


    /**
     * 查询订单详情
     */
    @PostMapping("/queryOrderIsCanUse.json")
    public Object queryOrderIsCanUse(String orderCode, String boxCode, Integer type) {
        Json json = new Json();
        try {
            if (null != orderCode && !"".equals(orderCode)) {
                if (null != type) {
                    if (null != boxCode && !"".equals(boxCode)) {

                    } else {
                        json.setMsg("无箱码，无法获取数据!");
                    }
                } else {
                    json.setMsg("无编码类型，无法获取数据!");
                }
            } else {
                json.setMsg("无订单编码，无法获取数据!");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常!");
        }
        return json;
    }

    /**
     * 查询二维码是否绑定订单
     *
     * @return
     */
    @PostMapping("/queryQrCodeIsOrder.json")
    public Object queryQrCode(POrderDetail orderDetail) {
        Json json = new Json();
        try {
            //获取二维码
            String orderDetailQrcode = orderDetail.getOrderDetailQrcode();
            //截取后十四位
            String substring = orderDetailQrcode.substring(orderDetailQrcode.length() - 14, orderDetailQrcode.length());
            POrderDetail pOrderDetail = orderDetailService.selectOrderDetailByQrCode(orderDetail);
            if (pOrderDetail == null) {
                //获取明码
                String clearCode = GenerateUtils.zstoCode(substring);
                //获取标签规则
                String ruleName = substring.substring(0, 4);
                PTagRange range = new PTagRange();
                range.setClearCode(Integer.valueOf(clearCode));
                range.setRuleName(ruleName);
                TagRange tagRangeByRange = tagRangeService.getTagRangeByRange(range);
                if (tagRangeByRange != null) {
                    PZsBatch pZsBatch = new PZsBatch();
                    pZsBatch.setBatchId(tagRangeByRange.getBatchId());
                    PZsBatch batchByBatch = zsBatchService.getBatchByBatch(pZsBatch);
                    if (batchByBatch != null) {
                        json.setObj(batchByBatch);
                        json.setMsg("查询成功");
                        json.setSuccess(true);
                    } else {
                        json.setMsg("该二维码无批次信息");
                        json.setSuccess(false);
                    }
                } else {
                    json.setMsg("暂无信息");
                }

            } else {
                json.setMsg("该货品已绑定订单");
            }
        } catch (Exception e) {
            json.setMsg("二维码无效!");
        }
        return json;
    }

    /**
     * 查询二维码是否是该箱的
     *
     * @return
     */
    @PostMapping("/queryQrCodeIsBox.json")
    public Object queryQrCodeBox(String boxCode, String qrCode) {
        Json json = new Json();
        try {
            if (qrCode.length() > 18) {
                String substring = qrCode.substring(qrCode.length() - 18, qrCode.length());
                BoxOrder boxOrder = new BoxOrder();
                boxOrder.setBoxCode(boxCode);
                BoxOrder box = boxOrderService.getBoxOrderByBoxCode(boxCode);
                String qr = box.getOrderDetailQr();
                String[] array = qr.split(",");
                List<String> asList = Arrays.asList(array);
                if (asList.contains(substring)) {
                    POrderDetail detail = new POrderDetail();
                    detail.setOrderDetailQrcode(substring);
                    POrderDetail orderDetail = orderDetailService.selectOrderDetailByQrCode(detail);
                    json.setMsg("查询成功");
                    json.setObj(orderDetail);
                    json.setSuccess(true);
                } else {
                    json.setMsg("该箱无该货");
                }
            }else {
                json.setMsg("二维码规则不正确");
            }

        } catch (Exception e) {
            json.setMsg("服务器异常!");
        }
        return json;
    }

    /**
     * 订单二维码删除
     *
     * @return
     */
    @PostMapping("/deleteQrCodeBox.json")
    public Object deleteQrCodeBox(String boxCode, String qrCode) {
        Json json = new Json();
        try {
            if (qrCode.length() > 18) {
                //截取18位
                String substring = qrCode.substring(qrCode.length() - 18, qrCode.length());
                boolean b = saOrderService.deleteOrderByQrBox(boxCode, substring);
                if (b) {
                    json.setSuccess(true);
                    json.setMsg("删除成功");
                } else {
                    json.setMsg("删除失败");
                }
            } else {
                json.setMsg("二维码无效");
            }

        } catch (Exception e) {
            json.setMsg("服务器异常!");
        }
        return json;
    }

    /**
     * 订单二维吗添加
     *
     * @return
     */
    @PostMapping("/addQrCodeBox.json")
    public Object addQrCodeBox(String boxCode, String orderCode, String qrCode) {
        Json json = new Json();
        if (qrCode.length() > 18) {
            //截取十八位
            String substring = qrCode.substring(qrCode.length() - 18, qrCode.length());
            String s = saOrderService.addOrderQrBox(boxCode, orderCode, substring);
            if (s.equals("添加成功")) {
                json.setSuccess(true);
                json.setMsg(s);
            } else {
                json.setMsg(s);

            }
        } else {
            json.setMsg("二维码不符合");
        }

        return json;
    }

    /**
     * 二维码替换
     *
     * @return
     */
    @PostMapping("/updateQrCodeBox.json")
    public Object updateQrCodeBox(String boxCode, String oldQrCode, String newQrCode) {
        Json json = new Json();
        if (oldQrCode.length() > 18 && newQrCode.length() > 18) {
            String old = oldQrCode.substring(oldQrCode.length() - 18, oldQrCode.length());
            String news = newQrCode.substring(newQrCode.length() - 18, newQrCode.length());
            boolean b = saOrderService.updateOrderQrBox(boxCode, old, news);
            if (b) {
                json.setSuccess(true);
                json.setMsg("替换成功");
            } else {
                json.setMsg("替换失败");
            }
        } else {
            json.setMsg("二维码不符合规则");
        }

        return json;
    }

    /**
     * 成品扫描
     *
     * @param qrCode
     * @return
     */
    @PostMapping("/batchScan.json")
    public Object batchScan(String qrCode) {
        Json json = new Json();
        try {
            //截取后十四位
            if (qrCode != null && qrCode.length() > 18) {
                String substring = qrCode.substring(qrCode.length() - 14, qrCode.length());
                //获取明码
                String clearCode = GenerateUtils.zstoCode(substring);
                //获取标签规则
                String ruleName = substring.substring(0, 4);
                PTagRange range = new PTagRange();
                range.setClearCode(Integer.valueOf(clearCode));
                range.setRuleName(ruleName);
                TagRange tagRangeByRange = tagRangeService.getTagRangeByRange(range);
                if (tagRangeByRange != null) {
                    PZsBatch pZsBatch = new PZsBatch();
                    pZsBatch.setBatchId(tagRangeByRange.getBatchId());
                    PZsBatch batchByBatch = zsBatchService.getBatchByBatch(pZsBatch);
                    if (batchByBatch != null) {
                        //获取批次编号
                        String batchCode = batchByBatch.getBatchCode();
                        //通过生产任务编号获取批次编号
                        PZsBatch batch = new PZsBatch();
                        batch.setBatchCode(batchCode);
                        //查询当前批次号下所有的生产任务
                        List<PZsBatch> batchList = zsBatchService.selectZsBatchByBatch1(batch);
                        json.setMsg("查询成功");
                        json.setObj(batchList);
                        json.setSuccess(true);
                    }
                } else {
                    json.setMsg("无成品信息");
                }
            } else {
                json.setMsg("二维码无效");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }

    /**
     * 任务详情
     *
     * @param taskCode
     * @return
     */
    @PostMapping("/taskDetail.json")
    public Object taskDetail(String taskCode) {
        Json json = new Json();
        try {
            ZsProduceTask zsProduceTask = zsProduceTaskService.getProduceTaskByNo(taskCode);
            if (null != zsProduceTask) {
                TaskDetailHeadPDA taskDetailHeadPDA = new TaskDetailHeadPDA();
                List<PBaFormAttributeValue> taskDetailHead = baFormAttributeValueService.getBaFormAttributeValueByWorkProcessIdForPDA(zsProduceTask.getProduceTaskId(), 2);
                taskDetailHeadPDA.setTaskDetailHead(taskDetailHead);
                List<SaLeaveStockDetailPara> paraList = saLeaveStockDetailService.getLeaveStockDetailByProduceId(zsProduceTask.getProduceTaskId());
                taskDetailHeadPDA.setSaLeaveStockDetailParaList(paraList);
                json.setObj(taskDetailHeadPDA);
                json.setSuccess(true);
            } else {
                json.setMsg("传入数据有误");
            }
        } catch (Exception e) {
            json.setMsg("服务器异常");
        }
        return json;
    }
}
