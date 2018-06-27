package com.dalian.sea.service.impl;

import com.dalian.sea.ExcelUtil.ExcelUtil;
import com.dalian.sea.json.Json;
import com.dalian.sea.mapper.*;
import com.dalian.sea.model.PuEnterStock;
import com.dalian.sea.model.PuEnterStockDetail;
import com.dalian.sea.model.ZsStock;
import com.dalian.sea.model.ZsWarehouse;
import com.dalian.sea.parameter.PEnterStockAndStock;
import com.dalian.sea.parameter.PZsStock;
import com.dalian.sea.parameter.PZsWarehouse;
import com.dalian.sea.parameter.PuEnterStockDetailPara;
import com.dalian.sea.service.ZsStockService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 陈逸文 on 2018/3/5.
 */
@Service("ZsStockService")
@Slf4j
public class ZsStockServiceImpl implements ZsStockService {

    @Autowired
    private ZsStockMapper zsStockMapper;

    @Autowired
    private PuEnterStockMapper puEnterStockMapper;

    @Autowired
    private PuEnterStockDetailMapper puEnterStockDetailMapper;

    @Autowired
    private ZsWarehouseMapper zsWarehouseMapper;

    @Autowired
    private PuReceiveDetailMapper puReceiveDetailMapper;

    @Autowired
    private ZsWarehouseUserMapper zsWarehouseUserMapper;

    @Override
    public List<PZsStock> getZsStockByGrid(PZsStock stock, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<PZsStock> stockList = zsStockMapper.getZsStockByGrid(stock);
        Integer overdueCount = 0;
        Integer stockWarningCount = 0;
        if (stockList != null && !stockList.isEmpty()) {
            Long warehouseId = stockList.get(0).getWarehouseId();
            if (warehouseId != null) {
                PZsStock zsStock = zsStockMapper.getTimeAndStockWaringCount(warehouseId);
                if (zsStock != null) {
                    if (zsStock.getOverdueCount() != null) {
                        overdueCount = zsStock.getOverdueCount();
                    }
                    if (zsStock.getStockWarningCount() != null) {
                        stockWarningCount = zsStock.getStockWarningCount();
                    }
                }
            }
        }
        for (PZsStock stock1 : stockList) {
            stock1.setOverdueCount(overdueCount);
            stock1.setStockWarningCount(stockWarningCount);
        }
        return stockList;
    }

    @Override
    public List<PZsStock> getZsStockList(PZsStock stock) {
        return zsStockMapper.getZsStockByGrid(stock);
    }

    @Override
    public List<PZsStock> getWarehouse(Long userId,Long companyId) {
        List<Long> ids=new ArrayList<>();
        //获取当前用户管理的仓库id
        if(companyId!=0){
            ids= zsWarehouseUserMapper.getWarehouseIdByUserId(userId);
        }else{
            List<ZsWarehouse> list= zsWarehouseMapper.getAllWarehouse();
            for (ZsWarehouse zsWarehouse : list) {
                ids.add(zsWarehouse.getWarehouseId());
            }
        }
        return zsStockMapper.getWarehouse(ids);
    }

    @Override
    public List<PZsStock> getGoodsTypeByWarehouseId(Long warehouseId) {
        return zsStockMapper.getGoodsTypeByWarehouseId(warehouseId);
    }

    @Override
    public List<PZsStock> getProductName(PZsStock pZsStock) {
        return zsStockMapper.getProductName(pZsStock);
    }

    @Override
    public List<PZsStock> getProductStatus(PZsStock pZsStock) {
        return zsStockMapper.getProductStatus(pZsStock);
    }

    @Override
    public List<PZsStock> getProductSpec(PZsStock pZsStock) {
        return zsStockMapper.getProductSpec(pZsStock);
    }

    @Override
    public List<PZsStock> getBatchNo(PZsStock pZsStock) {
        return zsStockMapper.getBatchNo(pZsStock);
    }

    @Override
    public PZsStock getUnitAndWeight(PZsStock pZsStock) {
        return zsStockMapper.getUnitAndWeight(pZsStock);
    }

    @Override
    public List<PZsStock> getStockIsMaterial() {
        return zsStockMapper.getStockIsMaterial();
    }

    @Override
    public List<PZsStock> getStockProductStatus() {
        return zsStockMapper.getStockProductStatus();
    }

    @Transactional
    @Override
    public Json addStockAndEnterStock(PEnterStockAndStock enterStockAndStock, Long id) {
        Json json = new Json();
        Integer integer = 0;
        try {
            String warehouseName = "活参库";
            ZsWarehouse warehouse = new ZsWarehouse();
            warehouse.setCName(warehouseName);
            warehouse = zsWarehouseMapper.getWarehouseByName(warehouse);//根据仓库名称查询仓库信息
            //新建入库单
            PuEnterStock enterStock = enterStockAndStock.getEnterStock();
            Date createdDate = new Date();
            Byte status = new Byte("1");
            enterStock.setCreateUserId(id);//操作人ID
            enterStock.setCreateDate(createdDate);//创建日期
            enterStock.setStatus(status);//状态
            integer = puEnterStockMapper.insertEnterStock(enterStock);//新建入库单
            Long enterStockId = enterStock.getEnterStockId();//入库单ID
            //新建入库详情
            List<PuEnterStockDetail> enterStockDetailList = enterStockAndStock.getEnterStockDetailList();
            List<PuEnterStockDetail> seacucumeberList =new ArrayList<>();
            if (enterStockDetailList != null && !enterStockDetailList.isEmpty()) {
                for (PuEnterStockDetail stockDetail : enterStockDetailList) {
                    SimpleDateFormat enterTime =new SimpleDateFormat("yyyyMMddHHmm");
                    String x=(int)(Math.random()*100)+"";
                    if(x.length()<2){
                        x ="0"+x;
                    }
                    String boxCode ="RKXM."+enterTime.format(createdDate)+x;
                    stockDetail.setBoxCode(boxCode);
                    stockDetail.setCreateUserId(id);
                    stockDetail.setCreateDate(createdDate);
                    stockDetail.setStatus(status);
                    stockDetail.setEnterStockId(enterStockId);
                    if (enterStock.getEnterNo() != null && !enterStock.getEnterNo().equals("")) {
                        stockDetail.setEnterNo(enterStock.getEnterNo());
                    }
                    integer = puEnterStockDetailMapper.insertPuEnterStockDetail(stockDetail);
                    if (stockDetail.getIsSeaCucumber() != null && stockDetail.getIsSeaCucumber() == 1) {
                        if (warehouse != null) {
                            PuEnterStockDetail seaEnterStockDetail = new PuEnterStockDetail();
                            BeanUtils.copyProperties(stockDetail, seaEnterStockDetail);
                            seaEnterStockDetail.setWarehouseId(warehouse.getWarehouseId());
                            seacucumeberList.add(seaEnterStockDetail);
                            integer = puEnterStockDetailMapper.insertPuEnterStockDetail(seaEnterStockDetail);
                        }
                    }
                }
                if(!seacucumeberList.isEmpty()){
                    for(PuEnterStockDetail seacucumber:seacucumeberList){
                        enterStockDetailList.add(seacucumber);
                    }
                }
            }
            //新建入库
            List<PZsStock> enterStockList = enterStockAndStock.getStockList();
            Set<Long> receiveIdList =new TreeSet<>();//创建收货单ID集合
            if (enterStockList != null && !enterStockList.isEmpty()) {
                for (PZsStock enterStockAndRecord : enterStockList) {
                    Long receiveId =enterStockAndRecord.getStockReceiveId();
                    if(receiveId !=null){
                        receiveIdList.add(receiveId);
                    }
                    PZsStock stock = new PZsStock();
                    BeanUtils.copyProperties(enterStockAndRecord, stock);
                    stock.setCreateUserId(id);
                    stock.setCreateDate(createdDate);
                    stock.setUpdateDate(createdDate);
                    stock.setUpdateUserId(id);
                    stock.setInCreateDate(createdDate);
                    stock.setStatus(status);
                    stock.setWeight(stock.getInWeight());
                    stock.setRestStatus(status);
                    BigDecimal weight = stock.getWeight();
                    if (enterStock.getTimeWarn() != null) {
                        stock.setTimeWarn(enterStock.getTimeWarn());
                    }
                    if (enterStock.getStockWarn() != null) {
                        stock.setStockWarn(enterStock.getStockWarn());
                    }
                    List<ZsStock> zsStocks = zsStockMapper.getZsStockByMuchOption(stock);
                    if (zsStocks != null && !zsStocks.isEmpty()) {
                        ZsStock lastStock = zsStocks.get(0);
                        stock.setStockId(lastStock.getStockId());
                        stock.setWeight(lastStock.getWeight().add(weight));
                        stock.setInWeight(lastStock.getWeight().add(weight));
                        stock.setStockWarn(enterStockAndStock.getEnterStock().getStockWarn());
                        integer = zsStockMapper.updateWeightByInsert(stock);
                    } else {
                        integer = zsStockMapper.insertZsStock(stock);
                    }
                    if (stock.getIsSeaCucumber() != null && stock.getIsSeaCucumber() == 1) {//判断是否入活参库（1 为是）

                        PZsStock seaCucumberStock = new PZsStock();
                        if (stock.getWarehouseId() != warehouse.getWarehouseId()) {//判断传入的库存选择的不是活参库
                            stock.setWarehouseId(warehouse.getWarehouseId());
                            stock.setWarehouseName(warehouse.getCName());
                            List<ZsStock> lastStcokList = zsStockMapper.getZsStockByMuchOption(stock);//判断活参库是否有重复数据
                            if (lastStcokList != null && !lastStcokList.isEmpty()) {//如有则更改库存信息
                                ZsStock lastStock = lastStcokList.get(0);
                                seaCucumberStock.setStockId(lastStock.getStockId());
                                seaCucumberStock.setWeight(lastStock.getWeight().add(weight));
                                seaCucumberStock.setInWeight(lastStock.getInWeight().add(weight));
                                seaCucumberStock.setCreateDate(createdDate);
                                seaCucumberStock.setRestStatus(stock.getRestStatus());
                                seaCucumberStock.setUpdateUserId(id);
                                integer = zsStockMapper.updateWeightByInsert(seaCucumberStock);
                            } else {//如没有则新建库存信息
                                integer = zsStockMapper.insertZsStock(stock);
                            }
                        }

                    }
                }
                if(!receiveIdList.isEmpty()){//收货单ID集合不为空则修改收货单状态
                    puReceiveDetailMapper.updateReceiptStatus(receiveIdList);
                }
            } else {
                integer = 0;
            }
            if (integer != 0) {
                json.setSuccess(true);
                json.setObj(enterStockAndStock);
            } else {
                json.setSuccess(false);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            json.setSuccess(false);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return json;
    }

    @Override
    public Boolean warehouseExport(HttpServletRequest request, HttpServletResponse response, Long userId) {
        Boolean res = false;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            PZsStock pZsStock = new PZsStock();
            //获取当前用户管理的仓库id
            List<Long> ids=zsWarehouseUserMapper.getWarehouseIdByUserId(userId);
            //获取仓库
            List<PZsStock> warehouses = zsStockMapper.getWarehouseByIds(ids);
            List<String> titles = new ArrayList<>();
            List<List<String>> names = new ArrayList<>();
            List<List<List<?>>> views = new ArrayList<>();
            String titleExcel = "库存信息";
            for (int i = 0; i < warehouses.size(); i++) {
                //获取每个仓库id
                Long warehouseId = warehouses.get(i).getWarehouseId();
                //获取每个仓库名称
                String warehouseName = warehouses.get(i).getWarehouseName();
                //按仓库查询记录
                pZsStock.setWarehouseId(warehouseId);
                List<PZsStock> pZsStockList = zsStockMapper.getZsStockByGrid(pZsStock);
                List<String> name = new ArrayList<>();
                List<List<?>> view = new ArrayList<>();
                name.add("批次号");
                name.add("货物类型");
                name.add("产品名称");
                name.add("产品状态");
                name.add("库存数量");
                name.add("入库规格");
                name.add("单位");
                name.add("最新入库时间");
                name.add("时间预警");
                name.add("库存预警");

                List<String> str = new ArrayList<>();

                if (pZsStockList.size() > 0) {
                    //添加批次号
                    for (PZsStock pz : pZsStockList) {
                        if (null == pz.getBatchNo()) {
                            str.add("");
                        } else {
                            str.add(pz.getBatchNo());
                        }
                    }
                    view.add(str);

                    //获取货物类型
                    str = new ArrayList<>();
                    for (PZsStock pz : pZsStockList) {
                        if (null == pz.getMaterialName()) {
                            str.add("");
                        } else {
                            str.add(pz.getMaterialName());
                        }
                    }
                    view.add(str);

                    //获取产品名称
                    str = new ArrayList<>();
                    for (PZsStock pz : pZsStockList) {
                        if (null == pz.getProductName()) {
                            str.add("");
                        } else {
                            str.add(pz.getProductName());
                        }
                    }
                    view.add(str);

                    //获取产品状态
                    str = new ArrayList<>();
                    for (PZsStock pz : pZsStockList) {
                        if (null == pz.getProductStatusName()) {
                            str.add("");
                        } else {
                            str.add(pz.getProductStatusName());
                        }
                    }
                    view.add(str);

                    //获取库存数量
                    str = new ArrayList<>();
                    for (PZsStock pz : pZsStockList) {
                        if (null == pz.getWeight()) {
                            str.add("");
                        } else {
                            str.add(pz.getWeight().toString());
                        }
                    }
                    view.add(str);

                    //获取入库规格
                    str = new ArrayList<>();
                    for (PZsStock pz : pZsStockList) {
                        if (null == pz.getProductSpecName()) {
                            str.add("");
                        } else {
                            str.add(pz.getProductSpecName());
                        }
                    }
                    view.add(str);

                    //获取单位
                    str = new ArrayList<>();
                    for (PZsStock pz : pZsStockList) {
                        if (null == pz.getUnitName()) {
                            str.add("");
                        } else {
                            str.add(pz.getUnitName());
                        }
                    }
                    view.add(str);

                    //获取入库时间
                    str = new ArrayList<>();
                    for (PZsStock pz : pZsStockList) {
                        if (null == pz.getUpdateDate()) {
                            str.add("");
                        } else {
                            str.add(formatter.format(pz.getUpdateDate()).toString());
                        }
                    }
                    view.add(str);

                    //获取时间预警
                    str = new ArrayList<>();
                    for (PZsStock pz : pZsStockList) {
                        if (null == pz.getTimeWarn()) {
                            str.add("");
                        } else {
                            str.add(pz.getTimeWarn().toString());
                        }
                    }
                    view.add(str);

                    //获取库存预警
                    str = new ArrayList<>();
                    for (PZsStock pz : pZsStockList) {
                        if (null == pz.getStockWarn()) {
                            str.add("");
                        } else {
                            str.add(pz.getStockWarn().toString());
                        }
                    }
                    view.add(str);
                }
                titles.add(warehouseName);
                names.add(name);
                views.add(view);
            }
            res = true;
            ExcelUtil.xslPro(titleExcel, titles, names, views, request, response);
        } catch (Exception e) {
            res = false;
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public List<PZsStock> getStockByStockIDs(String[] stockIDs) {
        return zsStockMapper.getStockByStockIDs(stockIDs);
    }

    @Override
    public List<PZsStock> getStockByStockIds(String[] stockIDs) {

        return zsStockMapper.getStockByStockIds(stockIDs);
    }

    @Override
    @Transactional
    public Boolean updateStockBySeaCucumber(List<PZsStock> stockList) {
        Boolean boo = false;
        try {
            Integer integer = 0;
            for (PZsStock stock : stockList) {
                stock.setInWeight(stock.getWeight());
                Integer r = stock.getWeight().compareTo(BigDecimal.ZERO);
                if (r == 0) {
                    stock.setRestStatus(new Byte("0"));
                }
                integer = zsStockMapper.updateStockBySeaCucumber(stock);
            }
            if (integer != 0) {
                boo = true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

    @Override
    public List<PZsStock> getProductSpecForStock(PZsStock zsStock) {
        return zsStockMapper.getProductSpecForStock(zsStock);
    }

    @Override
    public Integer countDetail() {
        return zsStockMapper.countDetail();
    }

    @Override
    public List<PZsStock> getStockListByBatchForEnterStock(PZsStock stock) {
        return zsStockMapper.getStockListByBatchForEnterStock(stock);
    }

    @Override
    public List<PZsStock> getReportCountListFromStock(PZsStock stock, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        Long[] materialArray = stock.getMaterialArray();
        Long[] productTypeArray = stock.getProductTypeArray();
        Long[] productIdArray = stock.getProductIdArray();
        String[] productSpecArray = stock.getProductSpecArray();
        String str = "pesd.is_material";
        PuEnterStockDetailPara enterStockDetailPara =new PuEnterStockDetailPara();
        if (materialArray != null && materialArray.length > 0) {
            str = "pesd.is_material";
            enterStockDetailPara.setMaterialArray(materialArray);
        }
        if (productTypeArray != null && productTypeArray.length > 0) {
            str = "spt2.product_type_id";
            enterStockDetailPara.setProductTypeArray(productTypeArray);
        }
        if (productIdArray != null && productIdArray.length > 0) {
            str = "spt1.product_type_id";
            enterStockDetailPara.setProductIdArray(productIdArray);
        }
        if (productSpecArray != null && productIdArray.length > 0) {
            str = "pesd.product_spec_name";
            enterStockDetailPara.setProductSpecArray(productSpecArray);
        }
        stock.setRemark(str);
        List<PZsStock> zsStockList =zsStockMapper.getReportCountListFromStock(stock);
        if (materialArray == null || materialArray.length == 0
                &&zsStockList !=null&&!zsStockList.isEmpty()) {
            materialArray =new Long[zsStockList.size()];
            for(int i =0;i<zsStockList.size();i++){
                Long isMaterial =zsStockList.get(i).getIsMaterial();
                materialArray[i] =isMaterial;
            }
        }
        enterStockDetailPara.setMaterialArray(materialArray);
        enterStockDetailPara.setRemark(str);
        List<PuEnterStockDetailPara> enterStockDetailParaList =puEnterStockDetailMapper.getInWeightSumForStock(enterStockDetailPara);
        if(enterStockDetailParaList !=null&&!enterStockDetailParaList.isEmpty()){
            for(PuEnterStockDetailPara enterStockDetail:enterStockDetailParaList){
                BigDecimal inWightSum =enterStockDetail.getInWeightSum();
                for(PZsStock pZsStock:zsStockList){
                    if(str.equals("pesd.is_material")
                            &&enterStockDetail.getIsMaterial().equals(pZsStock.getIsMaterial())){
                        pZsStock.setInWeightSum(inWightSum);
                    }else if(str.equals("spt2.product_type_id")
                            &&enterStockDetail.getProductTypeId().equals(pZsStock.getProductTypeId())){
                        pZsStock.setInWeightSum(inWightSum);
                    }else if(str.equals("spt1.product_type_id")
                            &&enterStockDetail.getProductId().equals(pZsStock.getProductId())){
                        pZsStock.setInWeightSum(inWightSum);
                    }else if(str.equals("pesd.product_spec_name")
                            &&enterStockDetail.getProductSpecName().equals(pZsStock.getProductSpecName())){
                        pZsStock.setInWeightSum(inWightSum);
                    }
                }
            }
        }
        return zsStockList;
    }

    @Override
    public List<PZsStock> getIsMaterialOptionForReportCountFromStock(PZsStock zsStock) {
        String str = "pesd.is_material";
        zsStock.setRemark(str);
        return zsStockMapper.getReportCountListFromStock(zsStock);
    }

    @Override
    public List<PZsStock> getProductTypeListFromStock(Long[] array) {
        return zsStockMapper.getProductTypeListFromStock(array);
    }

    @Override
    public List<PZsStock> getProductIdListFromStock(PZsStock zsStock) {
        return zsStockMapper.getProductIdListFromStock(zsStock);
    }

    @Override
    public List<PZsStock> getProductSpecNameFromStock(PZsStock zsStock) {
        return zsStockMapper.getProductSpecNameFromStock(zsStock);
    }

    @Override
    public List<PZsStock> getUnitIdFromStock(PZsStock zsStock) {
        return zsStockMapper.getUnitIdFromStock(zsStock);
    }

    @Override
    public Boolean reportCountExport(HttpServletRequest request, HttpServletResponse response, PZsStock zsStock) {
        Boolean res = false;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<String> titles = new ArrayList<>();
            List<List<String>> names = new ArrayList<>();
            List<List<List<?>>> views = new ArrayList<>();
            List<String> name = new ArrayList<>();
            List<List<?>> view = new ArrayList<>();
            String titleExcel = "报表统计";
            titles.add(titleExcel);
            Long[] materialArray = zsStock.getMaterialArray();
            Long[] productTypeArray = zsStock.getProductTypeArray();
            Long[] productIdArray = zsStock.getProductIdArray();
            String[] productSpecArray = zsStock.getProductSpecArray();
            String str = "pesd.is_material";
            name.add("货物类型");
            if (productTypeArray != null && productTypeArray.length > 0) {
                str = "spt2.product_type_id";
                name.add("产品大类");
            }
            if (productIdArray != null && productIdArray.length > 0) {
                str = "spt1.product_type_id";
                name.add("产品小类");
            }
            if (productSpecArray != null && productSpecArray.length > 0) {
                str = "pesd.product_spec_name";
                name.add("规格");
            }
            if (zsStock.getUnitId() != null) {
                name.add("单位");
            }
            name.add("总数量");
            name.add("新增数量");
            zsStock.setRemark(str);
            List<PZsStock> stockList = zsStockMapper.getReportCountListFromStock(zsStock);
            List<String> strList = new ArrayList<>();
            if (stockList != null && !stockList.isEmpty()) {
                //获取货物类型
                for (PZsStock psp : stockList) {
                    if (psp.getMaterialName() == null) {
                        strList.add("");
                    } else {
                        strList.add(psp.getMaterialName());
                    }
                }
                view.add(strList);
                if (productTypeArray != null && productTypeArray.length > 0) {
                    //获取产品大类
                    strList = new ArrayList<>();
                    for (PZsStock psp : stockList) {
                        if (psp.getProductTypeName() == null) {
                            strList.add("");
                        } else {
                            strList.add(psp.getProductTypeName());
                        }
                    }
                    view.add(strList);
                }
                if (productIdArray != null && productIdArray.length > 0) {
                    //获取产品小类
                    strList = new ArrayList<>();
                    for (PZsStock psp : stockList) {
                        if (psp.getProductName() == null) {
                            strList.add("");
                        } else {
                            strList.add(psp.getProductName());
                        }
                    }
                    view.add(strList);
                }
                if (productSpecArray != null && productSpecArray.length > 0) {
                    //获取规格
                    strList = new ArrayList<>();
                    for (PZsStock psp : stockList) {
                        if (psp.getProductSpecName() == null) {
                            strList.add("");
                        } else {
                            strList.add(psp.getProductSpecName());
                        }
                    }
                    view.add(strList);
                }
                if (zsStock.getUnitId() != null) {
                    //获取单位
                    strList = new ArrayList<>();
                    for (PZsStock psp : stockList) {
                        if (psp.getUnitName() == null) {
                            strList.add("");
                        } else {
                            strList.add(psp.getUnitName());
                        }
                    }
                    view.add(strList);
                }
                //获取总数量
                strList = new ArrayList<>();
                for (PZsStock psp : stockList) {
                    if (psp.getWeightSum() == null) {
                        strList.add("");
                    } else {
                        strList.add(psp.getWeightSum().toString());
                    }
                }
                view.add(strList);
                //获取新增数量
                strList = new ArrayList<>();
                for (PZsStock psp : stockList) {
                    if (psp.getInWeightSum() == null) {
                        strList.add("");
                    } else {
                        strList.add(psp.getInWeightSum().toString());
                    }
                }
                view.add(strList);
                names.add(name);
                views.add(view);
            }
            res = true;
            ExcelUtil.xslPro(titleExcel, titles, names, views, request, response);
        } catch (Exception e) {
            res = false;
            e.printStackTrace();
        }
        return res;
    }

}
