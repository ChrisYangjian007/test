package com.dalian.sea.service.impl;

import com.dalian.sea.ExcelUtil.ExcelUtil;
import com.dalian.sea.mapper.*;
import com.dalian.sea.model.PuEnterStockDetail;
import com.dalian.sea.model.ZsWarehouse;
import com.dalian.sea.parameter.*;
import com.dalian.sea.service.PuEnterStockDetailService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/2/28
 */
@Service("PuEnterStockDetailService")
@Slf4j
public class PuEnterStockDetailServiceImpl implements PuEnterStockDetailService {

    @Autowired
    private PuEnterStockDetailMapper puEnterStockDetailMapper;

    @Autowired
    private SaLeaveStockDetailMapper saLeaveStockDetailMapper;

    @Autowired
    private ReturnGoodsMapper returnGoodsMapper;

    @Autowired
    private ZsWarehouseUserMapper zsWarehouseUserMapper;

    @Autowired
    private ZsWarehouseMapper zsWarehouseMapper;

    @Override
    public List<PuEnterStockDetailPara> getAllEnterStockDetail(PuEnterStockDetailPara puEnterStockDetailPara, int page, int rows, Long userId,Long companyId) {
        List<PuEnterStockDetailPara> puEnterStockDetailParaList = new ArrayList<>();
        List<Long> ids=new ArrayList<>();
        try {
            //获取当前用户管理的仓库id
            if(companyId!=0){
                ids= zsWarehouseUserMapper.getWarehouseIdByUserId(userId);
            }else{
                List<ZsWarehouse> list= zsWarehouseMapper.getAllWarehouse();
                for (ZsWarehouse zsWarehouse : list) {
                    ids.add(zsWarehouse.getWarehouseId());
                }
            }
            PageHelper.startPage(page, rows);
            puEnterStockDetailParaList = puEnterStockDetailMapper.getAllEnterStockDetail(puEnterStockDetailPara, ids);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return puEnterStockDetailParaList;
    }

    @Override
    public List<PuEnterStockDetailPara> getStockIsMaterial(Long userId,Long companyId) {
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
        return puEnterStockDetailMapper.getStockIsMaterial(ids);
    }

    @Override
    public List<PuEnterStockDetailPara> getStockProductStatus(Long userId,Long companyId) {
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
        return puEnterStockDetailMapper.getStockProductStatus(ids);
    }

    /**
     * 通过produceTaskId获取入库详情信息
     *
     * @param produceTaskId
     * @return
     */
    @Override
    public List<PuEnterStockDetailPara> getEnterStockDetailByProduceTaskId(Long produceTaskId) {
        return puEnterStockDetailMapper.getEnterStockDetailByProduceTaskId(produceTaskId);
    }

    @Override
    public List<PuEnterStockDetailPara> getProductByIsMaterial(Long isMaterial) {
        return puEnterStockDetailMapper.getProductByIsMaterial(isMaterial);
    }

    @Override
    public List<PuEnterStockDetailPara> getSpec(PuEnterStockDetailPara puEnterStockDetailPara) {
        return puEnterStockDetailMapper.getSpec(puEnterStockDetailPara);
    }

    @Override
    public Boolean exportData(HttpServletRequest request, HttpServletResponse response, Long userId, Integer[] arr,Long companyId) {
        Boolean boo = false;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取当前用户管理的仓库id
            List<Long> ids=new ArrayList<>();
            if(companyId!=0){
                ids= zsWarehouseUserMapper.getWarehouseIdByUserId(userId);
            }else{
                List<ZsWarehouse> list= zsWarehouseMapper.getAllWarehouse();
                for (ZsWarehouse zsWarehouse : list) {
                    ids.add(zsWarehouse.getWarehouseId());
                }
            }
            List<String> titles = new ArrayList<>();
            List<List<String>> names = new ArrayList<>();
            List<List<List<?>>> views = new ArrayList<>();
            //入库记录
            if (Arrays.asList(arr).contains(1)) {
                List<String> name = new ArrayList<>();
                List<List<?>> view = new ArrayList<>();
                List<String> str = new ArrayList<>();
                List<PuEnterStockDetailPara> list = puEnterStockDetailMapper.getAllEnterStockDetail(null, ids);
                String title = "入库记录";
                name.add("入库编号");
                name.add("入库仓库");
                name.add("批次号");
                name.add("货物类型");
                name.add("产品名称");
                name.add("规格");
                name.add("原料数量");
                name.add("单位");
                name.add("产品状态");
                name.add("入库规格");
                name.add("入库数量");
                name.add("单位");
                name.add("入库时间");
                name.add("入库申请人");
                name.add("经手人");
                name.add("成品批次号");
                name.add("记入活参库");
                name.add("时间预警");
                name.add("库存预警");
                name.add("备注1");
                name.add("备注2");

                if (list.size() > 0) {
                    //入库编号
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getEnterNo() && !"".equals(pu.getEnterNo())) {
                            str.add(pu.getEnterNo());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //入库仓库
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getZsWarehouse().getCName() && !"".equals(pu.getZsWarehouse().getCName())) {
                            str.add(pu.getZsWarehouse().getCName());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //批次号
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getBatchNo() && !"".equals(pu.getBatchNo())) {
                            str.add(pu.getBatchNo());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //货物类型
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getBaDataDictionaryDetails().getCName() && !"".equals(pu.getBaDataDictionaryDetails().getCName())) {
                            str.add(pu.getBaDataDictionaryDetails().getCName());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //产品名称
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getProductName() && !"".equals(pu.getProductName())) {
                            str.add(pu.getProductName());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    str = new ArrayList<>();
                    //规格
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getEnterProductSpecName() && !"".equals(pu.getEnterProductSpecName())) {
                            str.add(pu.getEnterProductSpecName());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //原料数量
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getMaterialWeight()) {
                            str.add(pu.getMaterialWeight().toString());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //单位
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getEnterUnitName() && !"".equals(pu.getEnterUnitName())) {
                            str.add(pu.getEnterUnitName());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //产品状态
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getBaDataDictionaryDetailsTwo().getCName() && !"".equals(pu.getBaDataDictionaryDetailsTwo().getCName())) {
                            str.add(pu.getBaDataDictionaryDetailsTwo().getCName());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //入库规格
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getProductSpecName() && !"".equals(pu.getProductSpecName())) {
                            str.add(pu.getProductSpecName());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //入库数量
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getInWeight()) {
                            str.add(pu.getInWeight().toString());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //单位
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getUnitName() && !"".equals(pu.getUnitName())) {
                            str.add(pu.getUnitName());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //入库时间
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getPuEnterStock().getEnterDate()) {
                            str.add(formatter.format(pu.getPuEnterStock().getEnterDate()));
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //入库申请人
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getPuEnterStock().getEnterPerson()) {
                            str.add(pu.getPuEnterStock().getEnterPerson());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //经手人
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getPuEnterStock().getBrokerage()) {
                            str.add(pu.getPuEnterStock().getBrokerage());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //成品批次号
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getGoodsBatchNo()) {
                            str.add(pu.getGoodsBatchNo());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //是否记入活参库
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getIsSeaCucumber()) {
                            if (pu.getIsSeaCucumber() == 1) {
                                str.add("是");
                            } else {
                                str.add("否");
                            }
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //时间预警
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getPuEnterStock().getTimeWarn()) {
                            str.add(String.valueOf(pu.getPuEnterStock().getTimeWarn()));
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //库存预警
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getPuEnterStock().getStockWarn()) {
                            str.add(String.valueOf(pu.getPuEnterStock().getStockWarn()));
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //备注1
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getPuEnterStock().getRemark()) {
                            str.add(pu.getPuEnterStock().getRemark());
                        } else {
                            str.add("");
                        }
                    }
                    view.add(str);

                    //备注2
                    str = new ArrayList<>();
                    for (PuEnterStockDetailPara pu : list) {
                        if (null != pu.getRemark()) {
                            str.add(pu.getRemark());
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


            //出库记录
            if (Arrays.asList(arr).contains(2)) {
                List<List<?>> view1 = new ArrayList<>();
                List<String> namea = new ArrayList<>();
                List<String> str1 = new ArrayList<>();
                SaLeaveStockDetailPara saLeaveStockDetailPara = new SaLeaveStockDetailPara();
                saLeaveStockDetailPara.setMethod((byte) 1);
                List<SaLeaveStockDetailPara> list2 = saLeaveStockDetailMapper.getAllSaLeaveStockDetail(saLeaveStockDetailPara, ids);
                String titlet = "出库记录";
                namea.add("出库编号");
                namea.add("生产任务");
                namea.add("生产编号");
                namea.add("生产任务标签");
                namea.add("仓库");
                namea.add("批次号");
                namea.add("货物类型");
                namea.add("产品名称");
                namea.add("产品状态");
                namea.add("出库规格");
                namea.add("出库数量");
                namea.add("单位");
                namea.add("出库时间");
                namea.add("出库申请人");
                namea.add("经手人");
                namea.add("备注1");
                namea.add("备注2");
                if (list2.size() > 0) {
                    //出库编号
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getSaLeaveStock().getLeaveNo()) {
                            str1.add(sa.getSaLeaveStock().getLeaveNo());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    //生产任务
                    str1 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getSaLeaveStock().getProduceTaskName()) {
                            str1.add(sa.getSaLeaveStock().getProduceTaskName());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    //生产编号
                    str1 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getSaLeaveStock().getProduceTaskNo()) {
                            str1.add(sa.getSaLeaveStock().getProduceTaskNo());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    //生产任务标签
                    str1 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getSaLeaveStock().getProduceTaskTag()) {
                            str1.add(sa.getSaLeaveStock().getProduceTaskTag());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    //仓库
                    str1 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getZsWarehouse().getCName()) {
                            str1.add(sa.getZsWarehouse().getCName());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    //批次号
                    str1 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getBatchNo()) {
                            str1.add(sa.getBatchNo());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    //货物类型
                    str1 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getBaDataDictionaryDetails().getCName()) {
                            str1.add(sa.getBaDataDictionaryDetails().getCName());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    //产品名称
                    str1 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getProductName()) {
                            str1.add(sa.getProductName());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    //产品状态
                    str1 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getBaDataDictionaryDetailsTwo().getCName()) {
                            str1.add(sa.getBaDataDictionaryDetailsTwo().getCName());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    //出库规格
                    str1 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getProductSpecName()) {
                            str1.add(sa.getProductSpecName());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    //出库数量
                    str1 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getOutWeight()) {
                            str1.add(String.valueOf(sa.getOutWeight()));
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    //单位
                    str1 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getUnitName()) {
                            str1.add(sa.getUnitName());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    //出库时间
                    str1 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getSaLeaveStock().getLeaveDate()) {
                            str1.add(formatter.format(sa.getSaLeaveStock().getLeaveDate()));
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    //出库申请人
                    str1 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getSaLeaveStock().getLeavePerson()) {
                            str1.add(sa.getSaLeaveStock().getLeavePerson());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    //经手人
                    str1 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getSaLeaveStock().getBrokerage()) {
                            str1.add(sa.getSaLeaveStock().getBrokerage());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    //备注1
                    str1 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getSaLeaveStock().getRemark()) {
                            str1.add(sa.getSaLeaveStock().getRemark());
                        } else {
                            str1.add("");
                        }
                    }
                    view1.add(str1);

                    //备注2
                    str1 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list2) {
                        if (null != sa.getRemark()) {
                            str1.add(sa.getRemark());
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

            //损耗记录
            if (Arrays.asList(arr).contains(3)) {
                List<List<?>> view2 = new ArrayList<>();
                List<String> name2 = new ArrayList<>();
                List<String> str2 = new ArrayList<>();
                SaLeaveStockDetailPara saLeaveStockDetailPara2 = new SaLeaveStockDetailPara();
                saLeaveStockDetailPara2.setMethod((byte) 2);
                List<SaLeaveStockDetailPara> list3 = saLeaveStockDetailMapper.getAllSaLeaveStockDetail(saLeaveStockDetailPara2, ids);
                String title2 = "损耗记录";
                name2.add("仓库");
                name2.add("批次号");
                name2.add("货物类型");
                name2.add("产品名称");
                name2.add("产品状态");
                name2.add("出库规格");
                name2.add("损耗类型");
                name2.add("损耗数量");
                name2.add("单位");
                name2.add("备注1");
                name2.add("备注2");
                name2.add("出库时间");
                name2.add("出库申请人");
                name2.add("经手人");
                if (list3.size() > 0) {
                    for (SaLeaveStockDetailPara sa : list3) {
                        if (null != sa.getZsWarehouse().getCName()) {
                            str2.add(sa.getZsWarehouse().getCName());
                        } else {
                            str2.add("");
                        }
                    }
                    view2.add(str2);

                    str2 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list3) {
                        if (null != sa.getBatchNo()) {
                            str2.add(sa.getBatchNo());
                        } else {
                            str2.add("");
                        }
                    }
                    view2.add(str2);

                    str2 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list3) {
                        if (null != sa.getBaDataDictionaryDetails().getCName()) {
                            str2.add(sa.getBaDataDictionaryDetails().getCName());
                        } else {
                            str2.add("");
                        }
                    }
                    view2.add(str2);


                    str2 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list3) {
                        if (null != sa.getProductName()) {
                            str2.add(sa.getProductName());
                        } else {
                            str2.add("");
                        }
                    }
                    view2.add(str2);

                    str2 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list3) {
                        if (null != sa.getBaDataDictionaryDetailsTwo().getCName()) {
                            str2.add(sa.getBaDataDictionaryDetailsTwo().getCName());
                        } else {
                            str2.add("");
                        }
                    }
                    view2.add(str2);

                    str2 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list3) {
                        if (null != sa.getProductSpecName()) {
                            str2.add(sa.getProductSpecName());
                        } else {
                            str2.add("");
                        }
                    }
                    view2.add(str2);

                    str2 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list3) {
                        if (null != sa.getLoss()) {
                            str2.add(sa.getLoss());
                        } else {
                            str2.add("");
                        }
                    }
                    view2.add(str2);


                    str2 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list3) {
                        if (null != sa.getOutWeight()) {
                            str2.add(String.valueOf(sa.getOutWeight()));
                        } else {
                            str2.add("");
                        }
                    }
                    view2.add(str2);

                    str2 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list3) {
                        if (null != sa.getUnitName()) {
                            str2.add(sa.getUnitName());
                        } else {
                            str2.add("");
                        }
                    }
                    view2.add(str2);

                    str2 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list3) {
                        if (null != sa.getSaLeaveStock().getRemark()) {
                            str2.add(sa.getSaLeaveStock().getRemark());
                        } else {
                            str2.add("");
                        }
                    }
                    view2.add(str2);

                    str2 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list3) {
                        if (null != sa.getRemark()) {
                            str2.add(sa.getRemark());
                        } else {
                            str2.add("");
                        }
                    }
                    view2.add(str2);

                    str2 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list3) {
                        if (null != sa.getSaLeaveStock().getLeaveDate()) {
                            str2.add(formatter.format(sa.getSaLeaveStock().getLeaveDate()));
                        } else {
                            str2.add("");
                        }
                    }
                    view2.add(str2);

                    str2 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list3) {
                        if (null != sa.getSaLeaveStock().getLeavePerson()) {
                            str2.add(sa.getSaLeaveStock().getLeavePerson());
                        } else {
                            str2.add("");
                        }
                    }
                    view2.add(str2);

                    str2 = new ArrayList<>();
                    for (SaLeaveStockDetailPara sa : list3) {
                        if (null != sa.getSaLeaveStock().getBrokerage()) {
                            str2.add(sa.getSaLeaveStock().getBrokerage());
                        } else {
                            str2.add("");
                        }
                    }
                    view2.add(str2);
                }
                titles.add(title2);
                names.add(name2);
                views.add(view2);
            }

            //返货记录
            if (Arrays.asList(arr).contains(4)) {
                List<List<?>> view3 = new ArrayList<>();
                List<String> name3 = new ArrayList<>();
                List<String> str3 = new ArrayList<>();
                List<ReturnGoodsPara> list4 = returnGoodsMapper.getReturnGoodsDetails(null);
                String title3 = "返货记录";
                name3.add("产品名称");
                name3.add("批次号");
                name3.add("规格");
                name3.add("顾客姓名");
                name3.add("提货日期");
                name3.add("卡号");
                name3.add("顾客地址");
                name3.add("返货日期");
                name3.add("快递单号");
                name3.add("返货种类");
                name3.add("上传图片");
                name3.add("操作人");
                name3.add("操作时间");
                name3.add("不合格描述");
                name3.add("检查员1");
                name3.add("检查日期1");
                name3.add("不合格措施");
                name3.add("检察员2");
                name3.add("检查日期2");
                name3.add("不合格性质");
                name3.add("处置方式");
                name3.add("主持人");
                name3.add("参加人员");
                name3.add("备注");

                if (list4.size() > 0) {
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getProductName()) {
                            str3.add(re.getProductName());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getBatchNo()) {
                            str3.add(re.getBatchNo());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getProductSpecName()) {
                            str3.add(re.getProductSpecName());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getCustomerName()) {
                            str3.add(re.getCustomerName());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getTakeDate()) {
                            str3.add(formatter.format(re.getTakeDate()));
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getCardNumber()) {
                            str3.add(re.getCardNumber());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getCustomerAddress()) {
                            str3.add(re.getCustomerAddress());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getReturnDate()) {
                            str3.add(formatter.format(re.getReturnDate()));
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getExpressNumber()) {
                            str3.add(re.getExpressNumber());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getGoodsType()) {
                            str3.add(re.getGoodsType());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if ("" != re.getIamges()) {
                            str3.add("已上传");
                        } else {
                            str3.add("未上传");
                        }
                    }
                    view3.add(str3);

                    //操作人
                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getUpdateUserName()) {
                            str3.add(re.getUpdateUserName());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getUpdateDate()) {
                            str3.add(formatter.format(re.getUpdateDate()));
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getUnqualifiedDescription()) {
                            str3.add(re.getUnqualifiedDescription());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getInspectorOne()) {
                            str3.add(re.getInspectorOne());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getCheckDateOne()) {
                            str3.add(formatter.format(re.getCheckDateOne()));
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getTreatmentMeasures()) {
                            str3.add(re.getTreatmentMeasures());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getInspectorTwo()) {
                            str3.add(re.getInspectorTwo());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getCheckDateTwo()) {
                            str3.add(formatter.format(re.getCheckDateTwo()));
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getNonconformance()) {
                            str3.add(re.getNonconformance());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getDisposalWay()) {
                            str3.add(re.getDisposalWay());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getHost()) {
                            str3.add(re.getHost());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getParticipants()) {
                            str3.add(re.getParticipants());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);

                    str3 = new ArrayList<>();
                    for (ReturnGoodsPara re : list4) {
                        if (null != re.getRemark()) {
                            str3.add(re.getRemark());
                        } else {
                            str3.add("");
                        }
                    }
                    view3.add(str3);
                }
                titles.add(title3);
                names.add(name3);
                views.add(view3);
            }
            if (arr.length > 0) {
                String titleExcel = "出入库记录";
                ExcelUtil.xslPro(titleExcel, titles, names, views, request, response);
                boo = true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return boo;
    }

    @Override
    public PEnterStockAndStock getEnterStockObj(PuEnterStockDetailPara PuEnterStockDetailPara) {
        List<PuEnterStockDetailPara> list = puEnterStockDetailMapper.getAllEnterStockDetail(PuEnterStockDetailPara, null);
        PEnterStockAndStock pe = new PEnterStockAndStock();
        //set入库单
        pe.setEnterStock(list.get(0).getPuEnterStock());
        //set入库详情
        List<PuEnterStockDetail> enterStockDetailList = new ArrayList<>();
        enterStockDetailList.add(list.get(0));
        pe.setEnterStockDetailList(enterStockDetailList);
        //set stockList
        List<PZsStock> stockList = new ArrayList<>();
        PZsStock pz = new PZsStock();
        BeanUtils.copyProperties(list.get(0), pz);
        stockList.add(pz);
        pe.setStockList(stockList);
        return pe;
    }

    @Override
    public List<PuEnterStockDetailPara> getReportCountList(PuEnterStockDetailPara puEnterStockDetailPara, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        Long[] materialArray = puEnterStockDetailPara.getMaterialArray();
        Long[] productTypeArray = puEnterStockDetailPara.getProductTypeArray();
        Long[] productIdArray = puEnterStockDetailPara.getProductIdArray();
        String[] productSpecArray = puEnterStockDetailPara.getProductSpecArray();
        String str = "pesd.is_material";
        if (materialArray != null && materialArray.length > 0) {
            str = "pesd.is_material";
        }
        if (productTypeArray != null && productTypeArray.length > 0) {
            str = "spt2.product_type_id";
        }
        if (productIdArray != null && productIdArray.length > 0) {
            str = "spt1.product_type_id";
        }
        if (productSpecArray != null && productIdArray.length > 0) {
            str = "pesd.product_spec_name";
        }
        puEnterStockDetailPara.setRemark(str);
        return puEnterStockDetailMapper.getReportCountList(puEnterStockDetailPara);
    }

    @Override
    public List<PuEnterStockDetailPara> getIsMaterialOptionForReportCount(PuEnterStockDetailPara enterStockDetailPara) {
        String str = "pesd.is_material";
        enterStockDetailPara.setRemark(str);
        return puEnterStockDetailMapper.getReportCountList(enterStockDetailPara);
    }

    @Override
    public List<PuEnterStockDetailPara> getProductTypeListByEnterDetail(Long[] array) {
        return puEnterStockDetailMapper.getProductTypeListByEnterDetail(array);
    }

    @Override
    public List<PuEnterStockDetailPara> getProductIdListByEnterDetail(PuEnterStockDetailPara enterStockDetailPara) {
        return puEnterStockDetailMapper.getProductIdListByEnterDetail(enterStockDetailPara);
    }

    @Override
    public List<PuEnterStockDetailPara> getProductSpecNameByEnterDetail(PuEnterStockDetailPara enterStockDetailPara) {
        return puEnterStockDetailMapper.getProductSpecNameByEnterDetail(enterStockDetailPara);
    }

    @Override
    public List<PuEnterStockDetailPara> getUnitIdByEnterDetail(PuEnterStockDetailPara enterStockDetailPara) {
        return puEnterStockDetailMapper.getUnitIdByEnterDetail(enterStockDetailPara);
    }

    @Override
    public Boolean reportCountExport(HttpServletRequest request, HttpServletResponse response, PuEnterStockDetailPara puEnterStockDetailPara) {
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
            Long[] materialArray = puEnterStockDetailPara.getMaterialArray();
            Long[] productTypeArray = puEnterStockDetailPara.getProductTypeArray();
            Long[] productIdArray = puEnterStockDetailPara.getProductIdArray();
            String[] productSpecArray = puEnterStockDetailPara.getProductSpecArray();
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
            if (puEnterStockDetailPara.getUnitId() != null) {
                name.add("单位");
            }
            name.add("总数量");
            name.add("新增数量");
            puEnterStockDetailPara.setRemark(str);
            List<PuEnterStockDetailPara> stockDetailParaList = puEnterStockDetailMapper.getReportCountList(puEnterStockDetailPara);
            List<String> strList = new ArrayList<>();
            if (stockDetailParaList != null && !stockDetailParaList.isEmpty()) {
                //获取货物类型
                for (PuEnterStockDetailPara psp : stockDetailParaList) {
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
                    for (PuEnterStockDetailPara psp : stockDetailParaList) {
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
                    for (PuEnterStockDetailPara psp : stockDetailParaList) {
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
                    for (PuEnterStockDetailPara psp : stockDetailParaList) {
                        if (psp.getProductSpecName() == null) {
                            strList.add("");
                        } else {
                            strList.add(psp.getProductSpecName());
                        }
                    }
                    view.add(strList);
                }
                if (puEnterStockDetailPara.getUnitId() != null) {
                    //获取单位
                    strList = new ArrayList<>();
                    for (PuEnterStockDetailPara psp : stockDetailParaList) {
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
                for (PuEnterStockDetailPara psp : stockDetailParaList) {
                    if (psp.getMaterialWeightSum() == null) {
                        strList.add("");
                    } else {
                        strList.add(psp.getMaterialWeightSum().toString());
                    }
                }
                view.add(strList);
                //获取新增数量
                strList = new ArrayList<>();
                for (PuEnterStockDetailPara psp : stockDetailParaList) {
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
