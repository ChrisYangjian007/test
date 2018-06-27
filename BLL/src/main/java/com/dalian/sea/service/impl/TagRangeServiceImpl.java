package com.dalian.sea.service.impl;

import com.dalian.sea.mapper.TagRangeMapper;
import com.dalian.sea.model.TagRange;
import com.dalian.sea.parameter.PTagRange;
import com.dalian.sea.service.TagRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 杨建 on 2018/4/1.
 */
@Service("tagRangeService")
public class TagRangeServiceImpl implements TagRangeService {
    @Autowired
    private TagRangeMapper tagRangeMapper;
    @Override
    public TagRange getTagRangeByRange(PTagRange tagRange) {
        return tagRangeMapper.getTagRangeByRange(tagRange);
    }
}
