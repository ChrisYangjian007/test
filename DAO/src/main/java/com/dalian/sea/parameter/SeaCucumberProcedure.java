package com.dalian.sea.parameter;

import com.dalian.sea.model.SaLeaveStock;
import lombok.Data;

import java.util.List;

/**
 * Created by 陈逸文 on 2018/4/2.
 */
@Data
public class SeaCucumberProcedure extends YhLeaveStock{

    /**
     * 参数Json
     */
   private List<PAttributeValueData> attributeValueDataList;

   private List<PBaFormAttributeValue> formAttributeValueList;
}
