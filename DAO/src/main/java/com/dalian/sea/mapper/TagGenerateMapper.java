package com.dalian.sea.mapper;

import com.dalian.sea.model.TagGenerate;
import com.dalian.sea.parameter.PTagGenerate;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 杨建
 */
public interface TagGenerateMapper extends Mapper<TagGenerate> {
    /**
     * 根据获取标签库存
     *
     * @param tagGenerate
     * @return
     */
    List<PTagGenerate> getTagGenerateByGrid(PTagGenerate tagGenerate);

    /**
     * 根据id删除
     *
     * @param tagGenerate
     * @return
     */
    Integer deleteGenerateById(TagGenerate tagGenerate);

    /**
     * 根据id获取详情
     *
     * @param id
     * @return
     */
    PTagGenerate getTagGenerateById(Long id);

    /**
     * 根据标签规则名称获取详情
     *
     * @param ruleName
     * @return
     */
    PTagGenerate getTagGenerateByRule(String ruleName);

    /**
     * 添加
     *
     * @param tagGenerate
     * @return
     */
    Integer addTagGenerate(TagGenerate tagGenerate);

    /**
     * @param tagGenerate
     * @return
     */
    List<TagGenerate> selectTagGenerateBy(TagGenerate tagGenerate);


    Integer updateGenerateByGenerate(TagGenerate tagGenerate);


    /**
     * 总生成量
     *
     * @return
     */
    Integer getTagCountTotal();

    /**
     * 当月生成量
     *
     * @return
     */
    Integer getMonthTagCount();

    /**
     * 根据日期
     * 查询生成量
     *
     * @param date
     * @return
     */
    Integer getGenerateCountByDate(String date);


    /**
     * 根据年月
     * 查询生成量
     * @param date
     * @return
     */
    Integer getGenerateCountByYearMonth(String date);
}