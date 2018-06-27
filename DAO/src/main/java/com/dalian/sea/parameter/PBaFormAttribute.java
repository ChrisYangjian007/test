package com.dalian.sea.parameter;

import com.dalian.sea.model.BaFormAttribute;
import com.dalian.sea.model.BaFormAttributeValue;
import com.dalian.sea.model.BaUser;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/3/4.
 */
@Data
public class PBaFormAttribute extends BaFormAttribute{


    private BaFormAttributeValue baFormAttributeValue;

    /**
     * 数据源(数据详情）
     */
    private List<PBaDataDictionaryDetails> dictionaryDetails;

    /**
     * PDA用户
     */
    private List<BaUser> pdaUser;

    /**
     * value的值
     */
    private Object value;

    /**
     * 工艺详情名称
     */
    private String workProcessName;

    /**
     * 默认的用户名
     */
    private String defaultUserName;

    private Long baFormAttributeValueId;

    private Integer typeIndex;
}
