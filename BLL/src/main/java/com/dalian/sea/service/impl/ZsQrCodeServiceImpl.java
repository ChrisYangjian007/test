package com.dalian.sea.service.impl;

import com.dalian.sea.ExcelUtil.ExcelUtil;
import com.dalian.sea.json.Json;
import com.dalian.sea.mapper.ZsQrCodeIntervalMapper;
import com.dalian.sea.mapper.ZsQrCodeMapper;
import com.dalian.sea.mapper.ZsQrCodeUseMapper;
import com.dalian.sea.model.ZsQrCode;
import com.dalian.sea.model.ZsQrCodeUse;
import com.dalian.sea.parameter.PZsQrCode;
import com.dalian.sea.parameter.PZsQrCodeInterval;
import com.dalian.sea.service.ZsQrCodeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨文波
 * @date 2018/3/8
 */
@Service("ZsQrCodeService")
public class ZsQrCodeServiceImpl implements ZsQrCodeService {

    @Autowired
    private ZsQrCodeMapper zsQrCodeMapper;

    @Autowired
    private ZsQrCodeIntervalMapper zsQrCodeIntervalMapper;

    @Autowired
    private ZsQrCodeUseMapper zsQrCodeUseMapper;

    /**
     * 通过二维码编号获取二维码数据
     *
     * @param qrCode
     * @return
     */
    @Override
    public ZsQrCode getQrCodeByQrCode(String qrCode) {
        return zsQrCodeMapper.getQrCodeByQrCode(qrCode);
    }

    /**
     * 判断是否存在和使用
     * @param qrCodes
     * @return
     */
    @Override
    public Json isExistenceAndUse(String[] qrCodes) {
        Json json = new Json();
        List<ZsQrCode> qrCodeList = new ArrayList<>();
        String error = "";
        for (String code:qrCodes){
            ZsQrCode qrCode = zsQrCodeMapper.getQrCodeByQrCode(code);
            if (null==qrCode){
                if ("".equals(error)){
                    error += "("+code+"),编码不存在";
                }else {
                    error += ",("+code+"),编码不存在";
                }
            }else {
                if (2==qrCode.getUseStatus()){
                    if ("".equals(error)){
                        error += "("+code+"),编码已绑定";
                    }else {
                        error += ",("+code+"),编码已绑定";
                    }
                }
                qrCodeList.add(qrCode);
            }
        }
        if ("".equals(error)){
            json.setObj(qrCodeList);
            json.setSuccess(true);
        }else {
            json.setMsg(error);
        }
        return json;
    }

    /**
     * 通过produceTaskId获取二维码
     *
     * @param produceTaskId
     * @return
     */
    @Override
    public List<ZsQrCode> getQrCodeByProduceTaskId(Long produceTaskId) {
        return zsQrCodeMapper.getQrCodeByProduceTaskId(produceTaskId);
    }

    /**
     * 通过间隔获取二维码
     *
     * @param intervalId
     * @return
     */
    @Override
    public List<PZsQrCode> getQrCodeByIntervalId(Long intervalId, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return zsQrCodeMapper.getQrCodeByIntervalId(intervalId);
    }

    /**
     * 下载区间内的编码
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    @Transactional
    public Boolean downQRCode(HttpServletRequest request, HttpServletResponse response, PZsQrCodeInterval qrCodeInterval) {
        Boolean boo = false;
        try {
            List<String> name = new ArrayList<>();
            name.add("二维码编码");
            String title = "二维码编码(" + qrCodeInterval.getStartCode() + "-" + qrCodeInterval.getEndCode() + ")";
            List<List<?>> view = new ArrayList<>();
            List<String> str = new ArrayList<>();
            Long start = Long.valueOf(qrCodeInterval.getStartCode());
            Long end = Long.valueOf(qrCodeInterval.getEndCode());
            for (Long i = start; i <= end; i++) {
                String s = String.format("%010d", i);
                str.add(s);
            }
            view.add(str);
            ExcelUtil.xsl(title, name, view, request, response);
            if (2 != qrCodeInterval.getDownloadStatus()) {
                Integer integer = zsQrCodeIntervalMapper.updateQrCodeIntervalByIntervalIdForDownStatus(qrCodeInterval);
                if (0 != integer) {
                    boo = true;
                }
            } else {
                boo = true;
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

    /**
     * 添加编码
     *
     * @param qrCodeInterval
     * @return
     */
    @Override
    @Transactional
    public Boolean addQRCode(PZsQrCodeInterval qrCodeInterval) {
        Boolean boo = false;
        try {
            Long start;
            Long end;
            PZsQrCodeInterval interval = zsQrCodeIntervalMapper.getQrCodeIntervalForNew();
            if (null != interval) {
                start = Long.valueOf(interval.getEndCode()) + 1;
                end = Long.valueOf(interval.getEndCode()) + qrCodeInterval.getIntervalNumber();
            } else {
                start = new Long("1");
                end = qrCodeInterval.getIntervalNumber();
            }
            qrCodeInterval.setStartCode(String.format("%010d", start));
            qrCodeInterval.setEndCode(String.format("%010d", end));
            List<PZsQrCode> qrCodeList = new ArrayList<>();
            for (Long i = start; i <= end; i++) {
                PZsQrCode qrCode = new PZsQrCode();
                qrCode.setQrCode(String.format("%010d", i));
                qrCode.setCreateUserId(qrCodeInterval.getCreateUserId());
                qrCodeList.add(qrCode);
            }
            Integer integer = zsQrCodeMapper.addQrCodeByStartCodeAndEndCode(qrCodeList);
            if (0 != integer) {
                integer = zsQrCodeIntervalMapper.addQrCodeInterval(qrCodeInterval);
                if (0 != integer) {
                    boo = true;
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

    /**
     * 修改编码
     *
     * @param qrCode
     * @return
     */
    @Override
    public Boolean updateZsQrCode(ZsQrCode qrCode) {
        Boolean boo = false;
        Integer integer = zsQrCodeMapper.updateZsQrCode(qrCode);
        if (0 != integer) {
            boo = true;
        }
        return boo;
    }

    /**
     * 绑定
     *
     * @param qrCode
     * @return
     */
    @Override
    @Transactional
    public Boolean bindCode(ZsQrCode qrCode) {
        Boolean boo = false;
        try {
            Integer integer = zsQrCodeMapper.updateZsQrCode(qrCode);
            if (0 != integer) {
                ZsQrCodeUse codeUse = new ZsQrCodeUse();
                codeUse.setQrCodeId(qrCode.getQrCodeId());
                codeUse.setProduceTaskId(qrCode.getCurrentProduceTaskId());
                codeUse.setProduceTaskCode(qrCode.getCurrentProduceTaskCode());
                codeUse.setProduceTaskName(qrCode.getCurrentProduceTaskName());
                codeUse.setBindDate(qrCode.getBindDate());
                codeUse.setBindUserId(qrCode.getBindUserId());
                codeUse.setBindUserName(qrCode.getBindUserName());
                codeUse.setCreateUserId(qrCode.getBindUserId());
                integer = zsQrCodeUseMapper.addQrCodeUse(codeUse);
                if (0 != integer) {
                    boo = true;
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return boo;
    }

}
