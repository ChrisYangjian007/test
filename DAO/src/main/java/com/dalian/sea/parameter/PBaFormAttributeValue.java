package com.dalian.sea.parameter;

import com.dalian.sea.model.BaFormAttributeValue;
import com.dalian.sea.model.ZsWorkProcess;
import lombok.Data;

import java.util.List;

import javax.persistence.Column;

/**
 *
 * @author 杨文波
 * @date 2018/3/9
 */
@Data
public class PBaFormAttributeValue  extends BaFormAttributeValue{

    private List<AttributeValueData> attributeValueDataList;
    /**
     * 参数Json
     */
    private String objectParameterJsonTwo;

    /**
     * 工序名称
     */
    private String workProcessName;

    /**
     * 工序字段
     */
    private List<PBaFormAttribute> pBaFormAttributeList;
}
