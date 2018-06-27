package com.dalian.sea.mapper;

import com.dalian.sea.model.TagRange;
import com.dalian.sea.parameter.PTagRange;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TagRangeMapper extends Mapper<TagRange> {

    /**批量测试
     * @param list
     * @return
     */
 Integer insertTagRangeByBatch(List<TagRange> list);

    TagRange getTagRangeByRange(PTagRange tagRange);
}