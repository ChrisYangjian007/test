package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.BaAreaIpMapper;
import com.dalian.sea.mapper.TagSweepMapper;
import com.dalian.sea.model.BaAreaIp;
import com.dalian.sea.model.TagSweep;
import com.dalian.sea.parameter.PTagSweep;
import com.dalian.sea.parameter.ScanAreaDistribution;
import com.dalian.sea.parameter.TagSweepPara;
import com.dalian.sea.service.TagSweepService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author 杨建
 * @date 2018/3/15
 */
@Slf4j
@Service("TagSweepService")
public class TagSweepServiceImpl implements TagSweepService {
    @Autowired
    private TagSweepMapper tagSweepMapper;
    @Autowired
    private BaAreaIpMapper baAreaIpMapper;

    @Override
    public List<PTagSweep> selectTagSweepList(PTagSweep tagSweep, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return tagSweepMapper.selectTagSweepListByTagSweep(tagSweep);
    }

    @Override
    public Integer getSweepTotal() {
        return tagSweepMapper.getSweepTotal();
    }

    @Override
    public Integer getMonthSweep() {
        return tagSweepMapper.getMonthSweep();
    }

    @Override
    public Integer sweepExceptionTotal() {
        return tagSweepMapper.sweepExceptionTotal();
    }

    @Override
    public Integer todaySweepException() {
        return tagSweepMapper.todaySweepException();
    }

    @Override
    public List<ScanAreaDistribution> getScanAreaDistribution() {
        List<String> areaList = tagSweepMapper.getUpdateArea();
        List<ScanAreaDistribution> list = new ArrayList<>();
        Integer total = tagSweepMapper.getSweepTotal();
        if (0 != total && null != total) {
            for (String s : areaList) {
                ScanAreaDistribution sa = new ScanAreaDistribution();
                Integer count = tagSweepMapper.countByArea(s);
                sa.setName(s);
                sa.setValue((long)count);
                list.add(sa);
            }
        }
        return list;
    }

    @Override
    public List<Integer> weekSweep() {
        List<Integer> list = new ArrayList<>();
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
        for (int i = 1; i <= n; i++) {
            Calendar calendar = Calendar.getInstance();
            //本周每天的日期
            calendar.set(Calendar.DAY_OF_MONTH, date + i - n);
            String format = sdf.format(calendar.getTime());
            Integer count = tagSweepMapper.getCountByDate(format);
            list.add(count);
        }
        return list;
    }

    @Override
    public List<Integer> monthSweep() {
        List<Integer> list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 1; i <= date; i++) {
            //本月每天的日期
            cal.set(Calendar.DAY_OF_MONTH, date + i - date);
            String format = sdf.format(cal.getTime());
            Integer count = tagSweepMapper.getCountByDate(format);
            list.add(count);
        }
        return list;
    }

    @Override
    public List<Integer> yearSweep() {
        List<Integer> list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        //当前月份 3=4月
        int date = cal.get(Calendar.MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        for (int i = 0; i <= date; i++) {
            //本年的每月
            cal.set(Calendar.MONTH, date + i - date);
            String format = sdf.format(cal.getTime());
            Integer count = tagSweepMapper.getCountByYearMonth(format);
            list.add(count);
        }
        return list;
    }

    @Override
    public List<TagSweepPara> getGridTable(int page, int rows) {
        List<TagSweepPara> list = new ArrayList<>();
        try {
            PageHelper.startPage(page, rows);
            list = tagSweepMapper.getGridTable();
            for (TagSweepPara tag : list) {
                Integer count = tagSweepMapper.getCountBycode(tag.getQrCode());
                tag.setSweepCount(count);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return list;
    }

    /**
     * 根据batchNo获取次数
     * @param batchNo
     * @return
     */
    @Override
    public Integer getSweepCountByBatchNo(String batchNo) {
        return tagSweepMapper.getSweepCountByBatchNo(batchNo);
    }

    /**
     * 添加扫码记录
     * @param tagSweep
     * @return
     */
    @Override
    public Boolean addSweep(TagSweep tagSweep) {
        Boolean boo = false;
        try {
            BaAreaIp areaIp = baAreaIpMapper.getAreaIpByIp(tagSweep.getUpdateIp());
            if (null != areaIp) {
                if (null != areaIp.getDistrict()&&!"".equals(areaIp.getDistrict())) {
                    tagSweep.setUpdateArea(areaIp.getDistrict());
                } else {
                    if (null != areaIp.getCity()&&!"".equals(areaIp.getCity())) {
                        tagSweep.setUpdateArea(areaIp.getCity());
                    } else {
                        if (null != areaIp.getProvince()&&!"".equals(areaIp.getProvince())) {
                            tagSweep.setUpdateArea(areaIp.getProvince());
                        } else {
                            if (null != areaIp.getCountry()&&!"".equals(areaIp.getCountry())) {
                                tagSweep.setUpdateArea(areaIp.getCountry());
                            } else {
                                if (null != areaIp.getContinent()&&!"".equals(areaIp.getContinent())) {
                                    tagSweep.setUpdateArea(areaIp.getContinent());
                                } else {
                                    tagSweep.setUpdateArea("--");
                                }
                            }
                        }
                    }
                }
            } else {
                tagSweep.setUpdateArea("--");
            }
        Integer result = tagSweepMapper.addSweep(tagSweep);
        if(0<result){
            boo = true;
        }
        }catch (Exception e){

        }
        return boo;
    }


}
