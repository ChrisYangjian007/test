package com.dalian.sea.parameter;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/2.
 */
@Data
public class PFormValueData implements Serializable{

    private Long produceTaskId;

    private Long resourceId;

    private List<PBaFormAttributeValue> pBaFormAttributeValueList;
}
