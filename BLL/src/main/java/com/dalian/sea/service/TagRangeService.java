package com.dalian.sea.service;

import com.dalian.sea.model.TagRange;
import com.dalian.sea.parameter.PTagRange;

/**
 * Created by 杨建 on 2018/4/1.
 */
public interface TagRangeService {
    TagRange getTagRangeByRange(PTagRange tagRange);
}
