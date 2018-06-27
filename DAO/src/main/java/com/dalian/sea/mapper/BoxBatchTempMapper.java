package com.dalian.sea.mapper;

import com.dalian.sea.model.BoxBatchTemp;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BoxBatchTempMapper extends Mapper<BoxBatchTemp> {
    /**批量插入
     * @param list
     * @return
     */
    Integer insertBoxBatchTempByBatch(List<BoxBatchTemp> list);

    /**根据箱码分组查询
     * @param boxBatchTemp
     * @return
     */
    List<BoxBatchTemp> selectBoxBatchGroupByBox(BoxBatchTemp boxBatchTemp);

    /**根据标签规则分组
     * @param boxBatchTemp
     * @return
     */
    List<BoxBatchTemp> selectBoxBatchGroupByRuleName(BoxBatchTemp boxBatchTemp);

    /**根据标签规则和随机码查询列表
     * @param boxBatchTemp
     * @return
     */
    List<BoxBatchTemp> selectBoxBatchByRuleNameAndRom(BoxBatchTemp boxBatchTemp);
}