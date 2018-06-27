package com.dalian.sea.service.impl;

import com.dalian.sea.ExcelUtil.ExcelUtil;
import com.dalian.sea.mapper.PuReceiveDetailMapper;
import com.dalian.sea.mapper.PuReceiveTestMapper;
import com.dalian.sea.mapper.ReceiveTestDetailedMapper;
import com.dalian.sea.model.PuReceiveDetail;
import com.dalian.sea.model.PuReceiveTest;
import com.dalian.sea.model.ReceiveTestDetailed;
import com.dalian.sea.parameter.PPuReceiveTestMapper;
import com.dalian.sea.parameter.PuReceiveDetailPa;
import com.dalian.sea.service.PuReceiveTestService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */
@Service
@Slf4j
public class PuReceiveTestServiceImpl implements PuReceiveTestService {
    @Autowired
    private PuReceiveTestMapper puReceiveTestMapper;

    @Autowired
    private PuReceiveDetailMapper puReceiveDetailMapper;
    @Autowired
    private ReceiveTestDetailedMapper receiveTestDetailedMapper;

    /**
     * 显示所有数据
     *
     * @param puReceiveTest
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<PPuReceiveTestMapper> puReceiveTestList(PPuReceiveTestMapper puReceiveTest, int page, int rows) {
        PageHelper.startPage(page, rows);
        return puReceiveTestMapper.puReceiveTestList(puReceiveTest);
    }

    /**
     * 根据id查询货品
     *
     * @param id
     * @return
     */
    @Override
    public PuReceiveTest uploadingReports(Long id) {

        return puReceiveTestMapper.uploadingReports(id);
    }

    /**
     * 显示检验方式
     *
     * @return
     */
    @Override
    public List<PPuReceiveTestMapper> dataDictionary() {

        return puReceiveTestMapper.dataDictionary();
    }

    /**
     * 添加检验结果
     *
     * @return
     */
    @Override
    public Boolean addPuReceiveTest(PuReceiveTest puReceiveTest) {
        Boolean bl = false;
        try {
            Integer ct = puReceiveTestMapper.addPuReceiveTest(puReceiveTest);
            Integer integer = puReceiveDetailMapper.updateResult(puReceiveTest);
            if (ct > 0 && integer > 0) {
                bl = true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return bl;
    }

    /**
     * 显示货品类型
     *
     * @return
     */
    @Override
    public List<PPuReceiveTestMapper> TypeOfGoods() {
        return puReceiveTestMapper.TypeOfGoods();
    }

    /**
     * 数据导出
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    @Transactional
    public Boolean updateEnterpriseModalservice(HttpServletRequest request, HttpServletResponse response) {
        Boolean boo = false;
        try {
            List<String> name = new ArrayList<>();
            List<List<?>> view = new ArrayList<>();
            List<String> str = new ArrayList<>();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<PPuReceiveTestMapper> pPuReceiveTestList = puReceiveTestMapper.getPuReceiveTest(null);
            String title = "收货信息";
            name.add("批次号");
            for (PPuReceiveTestMapper pPuReceiveTest : pPuReceiveTestList) {
                str.add(pPuReceiveTest.getBatchNo());
            }
            view.add(str);

            name.add("货物类型");
            str = new ArrayList<>();
            for (PPuReceiveTestMapper pPuReceiveTest : pPuReceiveTestList) {
                str.add(pPuReceiveTest.getGoodsType());
            }
            view.add(str);

            name.add("产品名称");
            str = new ArrayList<>();
            for (PPuReceiveTestMapper pPuReceiveTest : pPuReceiveTestList) {
                str.add(pPuReceiveTest.getProductName());
            }
            view.add(str);

            name.add("规格");
            str = new ArrayList<>();
            for (PPuReceiveTestMapper pPuReceiveTest : pPuReceiveTestList) {
                str.add(pPuReceiveTest.getProductSpecName());
            }
            view.add(str);

            name.add("供应商");
            str = new ArrayList<>();
            for (PPuReceiveTestMapper pPuReceiveTest : pPuReceiveTestList) {
                if (pPuReceiveTest.getEnterpriseName() == null) {
                    str.add("");
                } else {
                    str.add(pPuReceiveTest.getEnterpriseName());
                }

            }
            view.add(str);

            name.add("数量");
            str = new ArrayList<>();
            for (PPuReceiveTestMapper pPuReceiveTest : pPuReceiveTestList) {
                if (pPuReceiveTest.getWeight() == null) {
                    str.add("");
                } else {
                    str.add(pPuReceiveTest.getWeight().toString());
                }

            }
            view.add(str);

            name.add("单位");
            str = new ArrayList<>();
            for (PPuReceiveTestMapper pPuReceiveTest : pPuReceiveTestList) {
                if (pPuReceiveTest.getUnitName() == null) {
                    str.add("");
                } else {
                    str.add(pPuReceiveTest.getUnitName());
                }
            }
            view.add(str);

            name.add("收货人");
            str = new ArrayList<>();
            for (PPuReceiveTestMapper pPuReceiveTest : pPuReceiveTestList) {
                if (pPuReceiveTest.getDeliverName() == null) {
                    str.add("");
                } else {
                    str.add(pPuReceiveTest.getDeliverName());
                }
            }
            view.add(str);

            name.add("收货时间");
            str = new ArrayList<>();
            for (PPuReceiveTestMapper pPuReceiveTest : pPuReceiveTestList) {
                if (pPuReceiveTest.getDeliverDate() == null) {
                    str.add("");
                } else {
                    str.add(formatter.format(pPuReceiveTest.getDeliverDate()).toString());
                }
            }
            view.add(str);

            name.add("检验人");
            str = new ArrayList<>();
            for (PPuReceiveTestMapper pPuReceiveTest : pPuReceiveTestList) {
                if (pPuReceiveTest.getInspectors() == null) {
                    str.add("");
                } else {
                    str.add(pPuReceiveTest.getInspectors());
                }
            }
            view.add(str);

            name.add("检验方式");
            str = new ArrayList<>();
            for (PPuReceiveTestMapper pPuReceiveTest : pPuReceiveTestList) {
                if (pPuReceiveTest.getTestCName() == null) {
                    str.add("");
                } else {
                    str.add(pPuReceiveTest.getTestCName());
                }
            }
            view.add(str);

            name.add("检验时间");
            str = new ArrayList<>();
            for (PPuReceiveTestMapper pPuReceiveTest : pPuReceiveTestList) {
                if (pPuReceiveTest.getInspectionDate() == null) {
                    str.add("");
                } else {
                    str.add(formatter.format(pPuReceiveTest.getInspectionDate()).toString());
                }
            }
            view.add(str);

            name.add("检验结果");
            str = new ArrayList<>();
            for (PPuReceiveTestMapper pPuReceiveTest : pPuReceiveTestList) {
                if (pPuReceiveTest.getTestResult() == null) {
                    str.add("待检查");
                } else if (pPuReceiveTest.getTestResult() == 2) {
                    str.add("合格");
                } else if (pPuReceiveTest.getTestResult() == 3) {
                    str.add("不合格");
                } else {
                    str.add("待检查");
                }
            }
            view.add(str);
            ExcelUtil.xsl(title, name, view, request, response);

            boo = true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }


    @Override
    @Transactional
    public Boolean checkReceiveDetail(List<PuReceiveDetailPa> puReceiveDetail) {
        Boolean res = false;
        Integer count;
        try {
            for (PuReceiveDetailPa receiveDetail : puReceiveDetail) {
                //获取记录数量
                count = puReceiveTestMapper.selectCountById(receiveDetail.getReceiveDetailId());
                if (0 == count) {
                    //报送
                    PuReceiveDetail receiveDetailById = puReceiveDetailMapper.getReceiveDetailById(receiveDetail.getReceiveDetailId());
                    ReceiveTestDetailed receiveTestDetailed = new ReceiveTestDetailed();
                    receiveTestDetailed.setCreateDate(receiveDetailById.getCreateDate());
                    receiveTestDetailed.setCreateUserId(receiveDetailById.getCreateUserId());
                    receiveTestDetailed.setGoodsTypeId(receiveDetailById.getGoodsTypeId());
                    receiveTestDetailed.setProductId(receiveDetailById.getProductId());
                    receiveTestDetailed.setReceiveDetailId(receiveDetailById.getReceiveDetailId());
                    receiveTestDetailed.setGoodsType(receiveDetailById.getGoodsType());
                    receiveTestDetailed.setBatchNo(receiveDetailById.getBatchNo());
                    receiveTestDetailed.setProductName(receiveDetailById.getProductName());
                    receiveTestDetailed.setProductSpecName(receiveDetailById.getProductSpecName());
                    receiveTestDetailed.setWeight(receiveDetailById.getWeight());
                    receiveTestDetailed.setUnitName(receiveDetailById.getUnitName());
                    receiveTestDetailed.setReceiveId(receiveDetailById.getReceiveId());
                    receiveTestDetailedMapper.addreceiveTestDetailed(receiveTestDetailed);
                    puReceiveTestMapper.addReceiveDetailId(receiveDetail.getReceiveDetailId());
                    puReceiveDetailMapper.updateTestResult(receiveDetail.getReceiveDetailId());
                    res = true;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return res;
    }

    @Override
    public String getImageById(Long receiveDetailId) {
        return puReceiveTestMapper.getImageById(receiveDetailId);
    }
}
