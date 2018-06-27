package com.dalian.sea.service.impl;

import Utils.GenerateUtils;
import com.dalian.sea.mapper.TagGenerateMapper;
import com.dalian.sea.mapper.ZsBatchMapper;
import com.dalian.sea.model.TagGenerate;
import com.dalian.sea.parameter.CodeHelp;
import com.dalian.sea.parameter.PTagGenerate;
import com.dalian.sea.parameter.TagEcharts;
import com.dalian.sea.service.TagGenerateService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 杨建
 * @date 2018/3/6
 */
@Service("TagGenerateService")
public class TagGenerateServiceImpl implements TagGenerateService {
    @Autowired
    private TagGenerateMapper generateMapper;

    @Autowired
    private ZsBatchMapper zsBatchMapper;


    @Override
    public List<PTagGenerate> getTagGenerateByGrid(PTagGenerate tagGenerate, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return generateMapper.getTagGenerateByGrid(tagGenerate);
    }

    @Override
    public void deleteGenerateById(TagGenerate tagGenerate) {
        generateMapper.deleteGenerateById(tagGenerate);
    }

    @Override
    public PTagGenerate getTagGenerateById(Long id) {
        return generateMapper.getTagGenerateById(id);
    }

    @Override
    public TagGenerate addGenerate(TagGenerate tagGenerate) {
        generateMapper.addTagGenerate(tagGenerate);
        return tagGenerate;
    }

    @Override
    public PTagGenerate getTagGenerateByRule(String rule) {
        return generateMapper.getTagGenerateByRule(rule);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateTagGenerateLabelCode(List<CodeHelp> helps) {
          /*2、分组算法**/
        Map<String, List<CodeHelp>> helpMap = new HashMap<>();
        for (CodeHelp help : helps) {
            List<CodeHelp> tempList = helpMap.get(help.getRuleName());
            /*如果取不到数据,那么直接new一个空的ArrayList**/
            if (tempList == null) {
                tempList = new ArrayList<>();
                tempList.add(help);
                helpMap.put(help.getRuleName(), tempList);
            } else {
                /*某个help之前已经存放过了,则直接追加数据到原来的List里**/
                tempList.add(help);
            }
        }
        try {
            for (String skuId : helpMap.keySet()) {
                List<CodeHelp> codeHelps = helpMap.get(skuId);
                List<Integer> longList = new ArrayList<>();
                for (int i = 0; i < codeHelps.size(); i++) {
                    CodeHelp help = codeHelps.get(i);
                    longList.add(help.getCode());
                }
                Collections.sort(longList);
//                kuCunUpdate(longList, skuId);
                generateUpdate(longList, skuId);
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public Integer getTagCountTotal() {
        return generateMapper.getTagCountTotal();
    }

    @Override
    public Integer getMonthTagCount() {
        return generateMapper.getMonthTagCount();
    }

    @Override
    public Integer totalLabelNumber() {
        return zsBatchMapper.totalLabelNumber();
    }

    @Override
    public Integer monthLabelNumber() {
        return zsBatchMapper.monthLabelNumber();
    }

    @Override
    public TagEcharts weekTag() {
        TagEcharts tagEcharts = new TagEcharts();
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
        List<Integer> generateList = new ArrayList<>();
        List<Integer> bindList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            Calendar calendar = Calendar.getInstance();
            //本周每天的日期
            calendar.set(Calendar.DAY_OF_MONTH, date + i - n);
            String format = sdf.format(calendar.getTime());
            Integer generateCount = generateMapper.getGenerateCountByDate(format);
            if (null == generateCount) {
                generateCount = 0;
            }
            Integer bindCount = zsBatchMapper.getBindCount(format);
            if (null == bindCount) {
                bindCount = 0;
            }
            generateList.add(generateCount);
            bindList.add(bindCount);
        }
        tagEcharts.setGenerateList(generateList);
        tagEcharts.setBindList(bindList);
        return tagEcharts;
    }

    @Override
    public TagEcharts monthTag() {
        TagEcharts tagEcharts = new TagEcharts();
        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Integer> generateList = new ArrayList<>();
        List<Integer> bindList = new ArrayList<>();
        for (int i = 1; i <= date; i++) {
            //本月每天的日期
            cal.set(Calendar.DAY_OF_MONTH, date + i - date);
            String format = sdf.format(cal.getTime());
            Integer generateCount = generateMapper.getGenerateCountByDate(format);
            if (null == generateCount) {
                generateCount = 0;
            }
            Integer bindCount = zsBatchMapper.getBindCount(format);
            if (null == bindCount) {
                bindCount = 0;
            }
            generateList.add(generateCount);
            bindList.add(bindCount);
        }
        tagEcharts.setGenerateList(generateList);
        tagEcharts.setBindList(bindList);
        return tagEcharts;
    }

    @Override
    public TagEcharts yearTag() {
        TagEcharts tagEcharts = new TagEcharts();
        Calendar cal = Calendar.getInstance();
        //当前月份 3=4月
        int date = cal.get(Calendar.MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        List<Integer> generateList = new ArrayList<>();
        List<Integer> bindList = new ArrayList<>();
        for (int i = 0; i <= date; i++) {
            //本年的每月
            cal.set(Calendar.MONTH, date + i - date);
            String format = sdf.format(cal.getTime());
            Integer generateCount = generateMapper.getGenerateCountByYearMonth(format);
            if (null == generateCount) {
                generateCount = 0;
            }
            Integer bindCount = zsBatchMapper.getBindByYearMonth(format);
            if (null == bindCount) {
                bindCount = 0;
            }
            generateList.add(generateCount);
            bindList.add(bindCount);
        }
        tagEcharts.setGenerateList(generateList);
        tagEcharts.setBindList(bindList);
        return tagEcharts;
    }

    public void kuCunUpdate(List<Long> longs, String ruleName) {
        //集合合转码段
//        String s = GenerateUtils.listToCodeString(longs);
        //列子 s=20-22;26-26;34-60;
//        String[] split = s.split(";");
//        for (int i = 0; i < split.length; i++) {
//            //更新库存
////            generateUpdate(split[i], ruleName);
//        }
    }

    public void kuCunUpdate1(List<Long> longs, String ruleName) {
        Long start = longs.get(0);
        Long end = longs.get(longs.size() - 1);

    }

    /**
     * 库存更新
     *
     * @param ruleName
     */
    public void generateUpdate(List<Integer> integers, String ruleName) {
//        String[] split = code.split("-");
        TagGenerate generate = new TagGenerate();
        generate.setRuleName(ruleName);
        generate.setIsPrint((byte) 0);
        generate.setStartNo(integers.get(0));
        generate.setEndNo(Integer.valueOf(integers.get(integers.size() - 1)));
        List<TagGenerate> tagGenerates = generateMapper.selectTagGenerateBy(generate);
        if (tagGenerates != null) {
            for (int i = 0; i < tagGenerates.size(); i++) {
                TagGenerate tagGenerate = tagGenerates.get(i);
                //当前库存已使用的量
                Integer alreadyCount = tagGenerate.getAlreadyCount();
                //全码段
                String allCode = tagGenerate.getAllCode();
                //当前库存量
                Integer remainCount = tagGenerate.getRemainCount();
                //已分配码段
                String allotedCode = tagGenerate.getAllotedCode();
                //获取可用码段(未分配码段)
                String unUserCode = tagGenerate.getUnAllotCode();
//                //码段转集合
                List<Integer> allList = GenerateUtils.codeToList(allCode);
//               //码段转集合
//                List<Long> notuserList = GenerateUtils.codeToList(code);
//                //取交集
//                List<Long> jiaoList = GenerateUtils.listToList(code, unUserCode);
//                List<Long> jiaoList = getJiaojiList(userList, notuserList);


//                //再将交集转码段,代表本次使用了多少码段
//                String jiaoCode = GenerateUtils.listToCodeString(jiaoList);
//                //交集码段 和 库存码段 进行比较
//                List<Long> longsUnUserList = GenerateUtils.jiaoListToList(jiaoCode, unUserCode);

                //等到的就是最新库存码段
//                String unAllot = GenerateUtils.listToCodeString(longsUnUserList);
//                tagGenerate.setUnAllotCode(jiaoCode);
//                tagGenerate.setAllotedCode(allotedCode + jiaoCode);
//                tagGenerate.setRemainCount(jiaoList.size());
//                generate.set
//                generate.setRemainCount(userList.size());
//                List<Integer> jiaoList = getJiaojiList(integers, allList);
                //取交集
                List<Integer> jiaoList = GenerateUtils.receiveCollectionList(allList, integers);
                String jiaoCode = GenerateUtils.listToCodeString(jiaoList);
                tagGenerate.setUnAllotCode(jiaoCode);
                tagGenerate.setRemainCount(jiaoList.size());
                tagGenerate.setLossCount(allList.size() - jiaoList.size());
                tagGenerate.setIsPrint((byte) 1);
                tagGenerate.setImportCount(jiaoList.size());
                tagGenerate.setImportDate(new Date());
                generateMapper.updateGenerateByGenerate(tagGenerate);

            }
        }


    }

    public List<Integer> getJiaojiList(List<Integer> longs, List<Integer> notUserList) {
        List<Integer> jiaoList = new ArrayList<>();
        List<List<Integer>> lists1 = new ArrayList<>();
        int count = 50000;
        int rows = 5;
        if (longs.size() > count) {
            rows = 10;
        }
        for (int j = 0; j < rows; j++) {
            if (j != (rows - 1)) {
                lists1.add(longs.subList(((longs.size() / rows) * j), ((longs.size() / rows) * (j + 1))));
            } else {
                lists1.add(longs.subList(((longs.size() / rows) * j), longs.size()));
            }
        }
        List<Integer> testList = new ArrayList<>();
        for (int i = 0; i < lists1.size(); i++) {
            testList = notUserList;
            List<Integer> integers = ListRetainList(lists1.get(i), testList);
            jiaoList.addAll(integers);
        }
        return jiaoList;
    }

    @Async
    public List<Integer> ListRetainList(List<Integer> longs, List<Integer> notUserList) {
        //取交集
        longs.retainAll(notUserList);
        return longs;
    }
}
