package com.dalian.sea.service.impl;

import Utils.GenerateUtils;
import com.dalian.sea.json.Json;
import com.dalian.sea.mapper.*;
import com.dalian.sea.model.*;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;

import javax.annotation.Resource;
import java.beans.Transient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by wxt60 on 2018/1/30.
 */
@Slf4j
@Service("AsyncTaskService")
@Transactional(rollbackFor = Exception.class)
public class AsyncTaskServiceImpl implements AsyncTaskService {
    @Autowired
    private ZsCompanyProductMapper companyProductMapper;
    @Autowired
    private BaDataDictionaryDetailsMapper dataDictionaryDetailsMapper;
    @Autowired
    private SysProductTypeMapper productTypeMapper;
    @Autowired
    private SysUnitMapper sysUnitMapper;
    @Autowired
    private SaOrderMapper orderMapper;
    @Autowired
    private SaOrderDetailMapper orderDetailMapper;
    @Autowired
    private BoxOrderTempMapper boxOrderTempMapper;
    @Autowired
    private SaErrorMapper errorMapper;
    @Autowired
    private BoxOrderMapper boxOrderMapper;
    @Autowired
    private SaOrderDetailService orderDetailService;
    @Autowired
    private SaOrderService orderService;
    @Autowired
    private ZsBoxOrderTempService zsBoxOrderTempService;
    @Autowired
    private ZsBatchService zsBatchService;
    @Autowired
    private ZsBatchMapper batchMapper;
    @Autowired
    private BoxBatchTempMapper boxBatchTempMapper;
    @Autowired
    private TagGenerateMapper tagGenerateMapper;
    @Autowired
    private TagRangeMapper tagRangeMapper;
    /**
     * 默认构造方法
     */
    Random random = new Random();

    /**
     * 表明是异步方法 无返回值
     *
     * @param i
     */
    @Async
    public void executeAsyncTask(Integer i) {
        System.out.println("执行异步任务：" + i);
    }

    /**
     * 异常调用 返回Future
     *
     * @param i
     * @return
     * @throws InterruptedException
     */
    @Async
    @Override
    public Future<String> asyncInvokeReturnFuture(int i) throws InterruptedException {
        System.out.println("input is " + i);
        Thread.sleep(1000);
        // Future接收返回值，这里是String类型，可以指明其他类型
        Future<String> future = new AsyncResult<>("success:" + i);
        return future;
    }


    /**
     * 产品导入
     *
     * @param objects
     * @param userId
     * @param index
     * @param size
     * @return
     * @throws InterruptedException
     */
    @Override
    @Async
    public Future<List<Json>> productImport(List<List<Object>> objects, List<List<Object>> lists, int size, int index, Long userId) throws InterruptedException {
        List<Json> jsonList = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            String str = "";
            String error = "";
            List<Object> object = objects.get(i);
            int objectIndex = (index) * size + (i + 2);
            PZsCompanyProduct companyProduct = new PZsCompanyProduct();
            if (7 <= object.size()) {
                companyProduct.setProductNo(String.valueOf(object.get(0)));
                if (null != String.valueOf(object.get(1)) && !"".equals(String.valueOf(object.get(1)))) {
                    companyProduct.setCName(String.valueOf(object.get(1)));
                } else {
                    error += "产品名称为空！";
                }
                if (null != String.valueOf(object.get(2)) && !"".equals(String.valueOf(object.get(2)))) {
                    String type = String.valueOf(object.get(2));
                    BaDataDictionaryDetails dataDictionaryDetails = dataDictionaryDetailsMapper.getBaDataDictionaryDetailsByDataNameAndDataDetailsName("货物类型", type);
                    if (null != dataDictionaryDetails) {
                        companyProduct.setType(dataDictionaryDetails.getDataDictionaryDetailsId());
                        companyProduct.setTypeName(type);
                    } else {
                        error += "无 (" + type + ") 类别！";
                    }
                } else {
                    error += "类别为空！";
                }
                String productLine = "";
                if (null != String.valueOf(object.get(3)) && !"".equals(String.valueOf(object.get(3)))) {
                    productLine = String.valueOf(object.get(3));
                    companyProduct.setProductLine(productLine);
                } else {
                    error += "产品线为空！";
                }
                String productCategory = "";
                if (null != String.valueOf(object.get(4)) && !"".equals(String.valueOf(object.get(4)))) {
                    productCategory = String.valueOf(object.get(4));
                    companyProduct.setProductCategory(productCategory);
                } else {
                    error += "产品大类为空！";
                }
                if (null != String.valueOf(object.get(5)) && !"".equals(String.valueOf(object.get(5)))) {
                    String productTypeName = String.valueOf(object.get(5));
                    companyProduct.setProductTypeName(productTypeName);
                    SysProductType productType = productTypeMapper.getSysProductTypeByNameAndParentName(companyProduct);
                    if (null == productType) {
                        error += "无 (" + productLine + "-" + productCategory + "-" + productTypeName + ") 产品类别！";
                    } else {
                        companyProduct.setProductTypeId(productType.getProductTypeId());
                    }
                } else {
                    error += "产品小类为空！";
                }
                companyProduct.setProductSpecification(String.valueOf(object.get(6)));
                if (7 < object.size()) {
                    String unitName = String.valueOf(object.get(7));
                    if (null != unitName && !"".equals(unitName)) {
                        companyProduct.setSysUnitName(unitName);
                        SysUnit unit = sysUnitMapper.getMaxAlevelByName(unitName);
                        if (null == unit) {
                            error += "无 (" + unitName + ") 单位！";
                        } else {
                            companyProduct.setSysUnitId(unit.getUnitId());
                        }
                    }
                    if (8 < object.size()) {
                        companyProduct.setNetWeight(String.valueOf(object.get(8)));
                    } else {
                        companyProduct.setNetWeight(null);
                    }
                } else {
                    companyProduct.setSysUnitId(null);
                    companyProduct.setSysUnitName(null);
                    companyProduct.setNetWeight(null);
                }
                companyProduct.setCreateUserId(userId);
                PZsCompanyProduct product = companyProductMapper.getZsCompanyProductByNoAndNameAndSpec(companyProduct);
                if (null != product) {
                    error += "该产品已存在！";
                }
                for (int t = objectIndex - 1; t < lists.size(); t++) {
                    if (null != object.get(0)) {
                        if (object.get(0).equals(lists.get(t).get(0)) && object.get(1).equals(lists.get(t).get(1)) && object.get(6).equals(lists.get(t).get(6))) {
                            error += "与第 " + (t + 2) + " 行,编码、名称和规格相同！";
                        }
                    } else {
                        if (object.get(1).equals(lists.get(t).get(1)) && object.get(6).equals(lists.get(t).get(6))) {
                            error += "与第 " + (t + 2) + " 行,名称和规格数据相同！";
                        }
                    }
                }
            } else {
                error += "数据有误,请检查(删除,请右键删除行)！";
            }
            Json json = new Json();
            if ("".equals(error)) {
                json.setObj(companyProduct);
                json.setSuccess(true);
            } else {
                if (!"".equals(str)) {
                    str += "，";
                }
                str += "<第 " + objectIndex + " 行>--( ";
                str += error;
                str += " )";
                json.setMsg(str);
            }
            jsonList.add(json);
        }
        Thread.sleep(1000);
        return new AsyncResult<>(jsonList);
    }

    @Override
    @Async
    public Future<Boolean> productSynchronization(List<ProductInfo> objects) {
        try {
            for (int i = 0; i < objects.size(); i++) {
                ProductInfo productInfo = objects.get(i);
                PZsCompanyProduct companyProduct = new PZsCompanyProduct();
                //产品编号
                companyProduct.setProductNo(productInfo.getName());
                PZsCompanyProduct productByNo = companyProductMapper.getCompanyProductByNo(companyProduct);

                //产品名称
                companyProduct.setCName(productInfo.getCpmc());
                //产品规格
                if (productInfo.getCpgg() != null) {
                    companyProduct.setProductSpecification(productInfo.getCpgg());
                } else {
                    companyProduct.setProductSpecification("--");
                }
                if (null != productInfo.getLb() && !"".equals(productInfo.getLb())) {
                    BaDataDictionaryDetails dataDictionaryDetails = dataDictionaryDetailsMapper.getBaDataDictionaryDetailsByDataNameAndDataDetailsName("货物类型", productInfo.getLb());
                    if (null != dataDictionaryDetails) {
                        //产品类别
                        companyProduct.setType(dataDictionaryDetails.getDataDictionaryDetailsId());
                        companyProduct.setTypeName(productInfo.getLb());
                    }
                }
                //产品线
                companyProduct.setProductLine(productInfo.getCpx());
                //产品大类
                companyProduct.setProductCategory(productInfo.getCpdl());
                if (null != productInfo.getCpxil() && !"".equals(productInfo.getCpxil())) {
                    //产品小类
                    companyProduct.setProductTypeName(productInfo.getCpxil());
                    SysProductType productType = productTypeMapper.getSysProductTypeByNameAndParentName(companyProduct);
                    if (null != productType) {
                        companyProduct.setProductTypeId(productType.getProductTypeId());
                    } else {
                        SysProductType type = new SysProductType();
                        SysProductType maxListIndex = productTypeMapper.getSysProductTypeMaxListIndex();
                        Integer listIndex = maxListIndex.getListIndex();
                        SysProductType productTypeOne = productTypeMapper.getSysProductTypeByNameAndParentIdAndALevel(productInfo.getCpx(), null, 1);
                        Long productTypeOneId;
                        Long productTypeTwoId;
                        if (null != productTypeOne) {
                            productTypeOneId = productTypeOne.getProductTypeId();
                        } else {
                            listIndex++;
                            type.setCName(productInfo.getCpx());
                            type.setMemo("产品同步(" + productInfo.getCpx() + ")产品线");
                            type.setALevel(1);
                            type.setParentId(maxListIndex.getFirstId());
                            type.setFirstId(maxListIndex.getFirstId());
                            type.setListIndex(listIndex);
                            productTypeMapper.addSysProductType(type);
                            productTypeOneId = type.getProductTypeId();
                        }
                        SysProductType productTypeTwo = productTypeMapper.getSysProductTypeByNameAndParentIdAndALevel(productInfo.getCpdl(), productTypeOneId, 2);
                        if (null != productTypeTwo) {
                            productTypeTwoId = productTypeTwo.getProductTypeId();
                        } else {
                            listIndex++;
                            type = new SysProductType();
                            type.setCName(productInfo.getCpdl());
                            type.setMemo("产品同步(" + productInfo.getCpdl() + ")产品大类");
                            type.setALevel(2);
                            type.setParentId(productTypeOneId);
                            type.setFirstId(maxListIndex.getFirstId());
                            type.setListIndex(listIndex);
                            productTypeMapper.addSysProductType(type);
                            productTypeTwoId = type.getProductTypeId();
                        }
                        SysProductType productTypeThree = productTypeMapper.getSysProductTypeByNameAndParentIdAndALevel(productInfo.getCpxil(), productTypeTwoId, 3);
                        if (null != productTypeThree) {
                            companyProduct.setProductTypeId(productTypeThree.getProductTypeId());
                        } else {
                            listIndex++;
                            type = new SysProductType();
                            type.setCName(productInfo.getCpxil());
                            type.setMemo("产品同步(" + productInfo.getCpxil() + ")产品小类");
                            type.setALevel(3);
                            type.setParentId(productTypeTwoId);
                            type.setFirstId(maxListIndex.getFirstId());
                            type.setListIndex(listIndex);
                            productTypeMapper.addSysProductType(type);
                            companyProduct.setProductTypeId(type.getProductTypeId());
                        }
                    }
                }
                if (null != productInfo.getDw() && !"".equals(productInfo.getDw())) {
                    //单位
                    companyProduct.setSysUnitName(productInfo.getDw());
                    SysUnit unit = sysUnitMapper.getMaxAlevelByName(productInfo.getDw());
                    if (null != unit) {
                        companyProduct.setSysUnitId(unit.getUnitId());
                    }
                }
                if (productByNo != null) {
                    companyProduct.setUpdateDate(new Date());
                    companyProduct.setProductId(productByNo.getProductId());
                    companyProductMapper.updateZsCompanyProduct(companyProduct);
                } else {
                    companyProduct.setCreateDate(new Date());
                    companyProduct.setStatus((byte) 1);
                    companyProductMapper.addZsCompanyProduct(companyProduct);
                }

            }
            return new AsyncResult<Boolean>(true);
        } catch (Exception e) {
            log.error("这里错误了", e.getMessage());
            return new AsyncResult<Boolean>(false);
        }

    }


    @Override
    public List<Future<Boolean>> orderAndDetailSynchronization(List<List<OrderInfo>> lists, List<List<OrderDetailInfo>> lists1) {
        try {
            List<Future<Boolean>> futureList = new ArrayList<>();
            for (int i = 0; i < lists.size(); i++) {
                Future<Boolean> future = orderService.orderSynchronization(lists.get(i));
                futureList.add(future);
            }
            for (int i = 0; i < lists1.size(); i++) {
                Future<Boolean> future = orderDetailService.orderDetailSynchronization(lists1.get(i));
                futureList.add(future);
            }
            return futureList;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public Boolean importOrder(List<SaOrder> orderList, List<PBoxOrderTemp> orderTempList, List<SaError> errorList, String rom) {
        try {
            List<List<SaOrder>> lists1 = new ArrayList<>();
            List<List<PBoxOrderTemp>> lists2 = new ArrayList<>();
            List<List<SaError>> lists3 = new ArrayList<>();
            int count = 50000;
            int rows = 5;
            if (orderTempList.size() > count) {
                rows = 10;
            }
            for (int i = 0; i < rows; i++) {
                if (i != (rows - 1)) {
                    lists1.add(orderList.subList(((orderList.size() / rows) * i), ((orderList.size() / rows) * (i + 1))));
                } else {
                    lists1.add(orderList.subList(((orderList.size() / rows) * i), orderList.size()));
                }
            }
            for (int i = 0; i < rows; i++) {
                if (i != (rows - 1)) {
                    lists2.add(orderTempList.subList(((orderTempList.size() / rows) * i), ((orderTempList.size() / rows) * (i + 1))));
                } else {
                    lists2.add(orderTempList.subList(((orderTempList.size() / rows) * i), orderTempList.size()));
                }
            }
            for (int i = 0; i < rows; i++) {
                if (i != (rows - 1)) {
                    lists3.add(errorList.subList(((errorList.size() / rows) * i), ((errorList.size() / rows) * (i + 1))));
                } else {
                    lists3.add(errorList.subList(((errorList.size() / rows) * i), errorList.size()));
                }
            }

            List<Future<Boolean>> futureList = orderAndDetailBox(lists1, lists2, lists3);
            Thread.sleep(2000);
            for (int i = 0; i < futureList.size(); i++) {
                Future<Boolean> future = futureList.get(i);
                future.get();
            }
            PBoxOrderTemp pBoxOrderTemp = new PBoxOrderTemp();
            pBoxOrderTemp.setRandom(rom);
            //批量更新订单明细
            List<POrderDetail> details = boxOrderTempMapper.selectListBoxOrderTempByGroupDetail(pBoxOrderTemp);
            if (details != null && details.size() > 0) {
                List<List<POrderDetail>> lists4 = new ArrayList<>();
                for (int i = 0; i < rows; i++) {
                    if (i != (rows - 1)) {
                        lists4.add(details.subList(((details.size() / rows) * i), ((details.size() / rows) * (i + 1))));
                    } else {
                        lists4.add(details.subList(((details.size() / rows) * i), details.size()));
                    }
                }
                if (lists4.size() > 0) {
                    for (int i = 0; i < lists4.size(); i++) {
                        orderDetailService.updateOrderdetailByBatch(lists4.get(i));
                    }
                }
            }
            //批量插入boxorder
            List<BoxOrder> boxOrders = boxOrderTempMapper.selectListBoxOrderTempByGroupBox(pBoxOrderTemp);
            if (boxOrders != null && boxOrders.size() > 0) {
                List<List<BoxOrder>> lists5 = new ArrayList<>();
                for (int i = 0; i < rows; i++) {
                    if (i != (rows - 1)) {
                        lists5.add(boxOrders.subList(((boxOrders.size() / rows) * i), ((boxOrders.size() / rows) * (i + 1))));
                    } else {
                        lists5.add(boxOrders.subList(((boxOrders.size() / rows) * i), boxOrders.size()));
                    }
                }
                if (lists5.size() > 0) {
                    for (int i = 0; i < lists5.size(); i++) {
                        insertBoxOrderByBatch(lists5.get(i));
                    }
                }
            }
            return true;


        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }

    }


    @Override
    public void insertBoxOrderByBatch(List<BoxOrder> orderList) {
        if (orderList.size() > 0) {
            boxOrderMapper.insertBoxOrderByBatch(orderList);
        }
    }


    public List<Future<Boolean>> orderAndDetailBox(List<List<SaOrder>> lists1, List<List<PBoxOrderTemp>> lists2, List<List<SaError>> lists3) throws ExecutionException, InterruptedException {
        List<Future<Boolean>> futureList = new ArrayList<>();
        if (lists1.size() > 0) {
            for (int i = 0; i < lists1.size(); i++) {
                Future<Boolean> future = orderService.updateOrderByList(lists1.get(i));
                futureList.add(future);
            }
        }
        if (lists2.size() > 0) {
            for (int i = 0; i < lists2.size(); i++) {
                Future<Boolean> future = zsBoxOrderTempService.insertBoxOrderListByBatch(lists2.get(i));
                futureList.add(future);
            }
        }
        if (lists3.size() > 0) {
            for (int i = 0; i < lists3.size(); i++) {
                Future<Boolean> future = zsBoxOrderTempService.insertErrorListByBatch(lists3.get(i));
                futureList.add(future);
            }
        }

        return futureList;

    }

    @Override
    @Transactional
    public Boolean importBatchByList(ZsBatch zsBatch, List<BoxBatchTemp> batchTempList, List<SaError> errorList, String rom) {
        try {
            List<List<BoxBatchTemp>> lists1 = new ArrayList<>();
            List<List<SaError>> lists2 = new ArrayList<>();
            int count = 50000;
            int rows = 5;
            if (batchTempList.size() > count) {
                rows = 10;
            }
            for (int i = 0; i < rows; i++) {
                if (i != (rows - 1)) {
                    lists1.add(batchTempList.subList(((batchTempList.size() / rows) * i), ((batchTempList.size() / rows) * (i + 1))));
                } else {
                    lists1.add(batchTempList.subList(((batchTempList.size() / rows) * i), batchTempList.size()));
                }
            }
            for (int i = 0; i < rows; i++) {
                if (i != (rows - 1)) {
                    lists2.add(errorList.subList(((errorList.size() / rows) * i), ((errorList.size() / rows) * (i + 1))));
                } else {
                    lists2.add(errorList.subList(((errorList.size() / rows) * i), errorList.size()));
                }
            }
//            List<Future<Boolean>> futureList = boxAndBatch(lists1, lists2);
//            Thread.sleep(3000);
//            for (int i = 0; i < futureList.size(); i++) {
//                Future<Boolean> future = futureList.get(i);
//                future.get();
//            }
//            CountDownLatch threadSignal = new CountDownLatch(lists1.size());//初始化countDown
            ExecutorService exe = Executors.newFixedThreadPool(5);
            for (int i = 0; i < lists1.size(); i++) {
//                Thread t = new Thread(new RunThread(lists1.get(i),threadSignal));
//                t.start();
                exe.execute(new RunThread(lists1.get(i)));
            }
            exe.shutdown();
            while (true) {
                if (exe.isTerminated()) {
                    //当所有任务完成 跳出
                    break;
                }
                Thread.sleep(200);
            }
//            threadSignal.await();//等待所有子线程执行完
            BoxBatchTemp boxBatchTemp = new BoxBatchTemp();
            boxBatchTemp.setRandom(rom);
            //根据箱码分组查询该批次有多少箱数
            List<BoxBatchTemp> boxTemps = boxBatchTempMapper.selectBoxBatchGroupByBox(boxBatchTemp);
            //二维码数
            zsBatch.setLabelNumber(batchTempList.size());
            //箱数
            zsBatch.setBoxNumber(boxTemps.size());
            zsBatch.setCreateDate(new Date());
            batchMapper.insertBatchSelective(zsBatch);
            //根据标签规则分组
            List<BoxBatchTemp> batchTemps = boxBatchTempMapper.selectBoxBatchGroupByRuleName(boxBatchTemp);
            if (batchTemps != null) {
                for (int i = 0; i < batchTemps.size(); i++) {
                    //查到相同标签规则的的二维码明码和对应的批次号
                    List<BoxBatchTemp> list = boxBatchTempMapper.selectBoxBatchByRuleNameAndRom(batchTemps.get(i));
                    List<TagRange> tagRangeList = insertRangeByList(list, zsBatch);
                    tagRangeMapper.insertTagRangeByBatch(tagRangeList);
                }
            }
            return true;

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    public List<Future<Boolean>> boxAndBatch(List<List<BoxBatchTemp>> lists1, List<List<SaError>> lists2) {
        List<Future<Boolean>> futureList = new ArrayList<>();
        if (lists1.size() > 0) {
            for (int i = 0; i < lists1.size(); i++) {
//                Future<Boolean> future = zsBatchService.insertBoxBatchByBatch(lists1.get(i));
//                boxBatchTempMapper.insertBoxBatchTempByBatch(lists1.get(i));
                Future<Boolean> future = insertBoxBatchByBatch(lists1.get(i));
                System.out.println("线程" + i);
                futureList.add(future);
            }
        }
//        if (lists2.size() > 0) {
//            for (int i = 0; i < lists2.size(); i++) {
//                Future<Boolean> future = zsBoxOrderTempService.insertErrorListByBatch(lists2.get(i));
//                futureList.add(future);
//            }
//        }
        return futureList;
    }

    public List<TagRange> insertRangeByList(List<BoxBatchTemp> list, ZsBatch zsBatch) {
        BoxBatchTemp temp = list.get(0);
        List<TagRange> tagRanges = new ArrayList<>();
        List<Integer> longs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            longs.add(Integer.valueOf(list.get(i).getQrClearCode()));
            Collections.sort(longs);
        }
        //集合合转码段
        if (longs.size() > 0) {
            String s = GenerateUtils.listToCodeString(longs);
            //列子 s=20-22;26-26;34-60;
            String[] split = s.split(";");
            for (int i = 0; i < split.length; i++) {
                TagRange range = new TagRange();
                String[] clearCode = split[i].split("-");
                range.setStartNo(Long.valueOf(clearCode[0]));
                range.setEndNo(Long.valueOf(clearCode[1]));
                range.setCreateDate(new Date());
                range.setRuleName(temp.getQrRuleName());
                range.setBatchId(zsBatch.getBatchId());
                range.setTaskCode(zsBatch.getTaskCode());
                tagRanges.add(range);
            }
            try {
                //更新库存
                generateUpdate(longs, temp.getQrRuleName());
            } catch (Exception e) {

            }
        }
        return tagRanges;
    }

    /**
     * 库存更新
     *
     * @param ruleName
     */
    public void generateUpdate(List<Integer> integers, String ruleName) {
        TagGenerate generate = new TagGenerate();
        generate.setRuleName(ruleName);
        generate.setStartNo(integers.get(0));
        generate.setEndNo(integers.get(integers.size() - 1));
        List<TagGenerate> tagGenerates = tagGenerateMapper.selectTagGenerateBy(generate);
        if (tagGenerates != null) {
            for (int i = 0; i < tagGenerates.size(); i++) {
                TagGenerate tagGenerate = tagGenerates.get(i);
                //当前库存已使用的量
                Integer alreadyCount = tagGenerate.getAlreadyCount();
                //当前库存量
                Integer remainCount = tagGenerate.getRemainCount();
                //已分配码段
                String allotedCode = tagGenerate.getAllotedCode();
                //获取可用码段(未分配码段)
                String unUserCode = tagGenerate.getUnAllotCode();
                if (unUserCode != null && unUserCode != "") {
                    //码段转集合
                    List<Integer> unCodeList = GenerateUtils.codeToList(unUserCode);
                    //获取集合的交集
                    List<Integer> jiaoList = GenerateUtils.receiveCollectionList(unCodeList, integers);
                    //再取差集
                    List<Integer> newUnUserCode = GenerateUtils.chaCollectionList(unCodeList, jiaoList);
                    //将差集转码段，就是更新后的可用码段
                    String newUnUserCodeString = GenerateUtils.listToCodeString(newUnUserCode);
                    //再将交集转码段,代表本次使用了多少码段
                    String jiaoCode = GenerateUtils.listToCodeString(jiaoList);
                    tagGenerate.setUnAllotCode(newUnUserCodeString);
                    if (allotedCode != null) {
                        tagGenerate.setAllotedCode(allotedCode + jiaoCode);
                    } else {
                        tagGenerate.setAllotedCode(jiaoCode);
                    }
                    if (alreadyCount != null) {
                        tagGenerate.setAlreadyCount(alreadyCount + jiaoList.size());
                    } else {
                        tagGenerate.setAlreadyCount(jiaoList.size());
                    }
                    tagGenerate.setRemainCount(remainCount - jiaoList.size());
                }
                tagGenerateMapper.updateGenerateByGenerate(tagGenerate);

            }
        }


    }

    @Async
    public Future<Boolean> insertBoxBatchByBatch(List<BoxBatchTemp> boxBatchTempList) {
        if (boxBatchTempList.size() > 0) {
            boxBatchTempMapper.insertBoxBatchTempByBatch(boxBatchTempList);
        }
        return new AsyncResult<Boolean>(true);
    }

    class RunThread implements Runnable {
        private List<BoxBatchTemp> list;

        //       private CountDownLatch threadsSignal;
        public RunThread(List<BoxBatchTemp> list) {
            this.list = list;
//           this.threadsSignal = threadsSignal;
        }


        @Override
        public void run() {
            if (list.size() > 0) {
                boxBatchTempMapper.insertBoxBatchTempByBatch(list);
            }
//           System.out.println(Thread.currentThread().getName() + "结束"+Thread.currentThread().getId());
//           threadsSignal.countDown();//线程结束时计数器减1
        }
    }


}
