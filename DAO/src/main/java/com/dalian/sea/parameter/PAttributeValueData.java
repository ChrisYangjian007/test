package com.dalian.sea.parameter;

import lombok.Data;

/**
 * Created by Administrator on 2018/3/14.
 */
@Data
public class PAttributeValueData extends AttributeValueData{

    /**
     * 值的名称
     */
    private String attribute;

    private PBaFormAttribute pBaFormAttribute;

    private Integer typeIndex;

    private Long attributeValueId;

    private Byte handleType;

    private Long formAttributeValueId;
}
