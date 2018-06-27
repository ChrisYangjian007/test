package com.dalian.sea.service;

import com.dalian.sea.model.TagGenerate;
import com.dalian.sea.parameter.CodeHelp;
import com.dalian.sea.parameter.PTagGenerate;
import com.dalian.sea.parameter.TagEcharts;

import java.util.List;

/**
 *
 * @author 杨建
 * @date 2018/3/6
 */
public interface TagGenerateService {

    /**加载到表格
     * @param tagGenerate
     * @param page
     * @param rows
     * @return
     */
    List<PTagGenerate> getTagGenerateByGrid(PTagGenerate tagGenerate, Integer page, Integer rows);

    /**
     * 根据id删除
     * @param tagGenerate
     */
    void deleteGenerateById(TagGenerate tagGenerate);

    /**根据id 查询
     * @param id
     * @return
     */
    PTagGenerate getTagGenerateById(Long id);

    /** 添加
     * @param tagGenerate
     */
    TagGenerate  addGenerate(TagGenerate tagGenerate);

    /** 根据标签规则查询详情
     * @param rule
     * @return
     */
    PTagGenerate  getTagGenerateByRule(String rule);

   boolean updateTagGenerateLabelCode(List<CodeHelp> helps);

    /**
     * 总生成量
     * @return
     */
    Integer getTagCountTotal();

    /**
     * 当月生成量
     * @return
     */
    Integer getMonthTagCount();

    /**
     * 总绑定量
     *
     * @return 总绑定量
     */
    Integer totalLabelNumber();

    /**
     * 当月绑定量
     * @return
     */
    Integer monthLabelNumber();

    /**
     * 本周标签生成、绑定图
     * @return
     */
    TagEcharts weekTag();

    /**
     * 本月标签生成、绑定图
     * @return
     */
    TagEcharts monthTag();

    /**
     * 本年标签生成、绑定量
     * @return
     */
    TagEcharts yearTag();
}
