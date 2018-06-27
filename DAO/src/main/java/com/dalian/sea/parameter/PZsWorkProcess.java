package com.dalian.sea.parameter;

import com.dalian.sea.model.BaFormAttribute;
import com.dalian.sea.model.BaFormAttributeValue;
import com.dalian.sea.model.ZsWorkProcess;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * PZsWorkProcess
 *
 * @author TONE
 * @date 2018/2/26.
 */
@Data
public class PZsWorkProcess extends ZsWorkProcess implements Serializable {
    /**
     * 模块ID
     */
    private Long resourceId;

    private String createUserName;

    private String updateUserName;

    private List<PBaFormAttribute> formAttributeList;

    private List<BaFormAttributeValue> formAttributeValueList=new ArrayList<>();

    private List<PBaFormAttribute> tableField1=new ArrayList<>();
    private List<PBaFormAttribute> tableField2=new ArrayList<>();
    private List<PBaFormAttribute> tableField3=new ArrayList<>();

    private List<AttributeValueData> valueDataList;

    /**
     * 最新的value
     */
    private BaFormAttributeValue baFormAttributeValue;

    private List<AttributeValueData> valueDataList1 = new ArrayList<>();
    private List<AttributeValueData> valueDataList2 = new ArrayList<>();
    private List<AttributeValueData> valueDataList3 = new ArrayList<>();

    private List<PBaFormAttributeValue> pBaFormAttributeValueList;

    private List<PBaFormAttributeValue> pBaFormAttributeValueList1=new ArrayList<>();
    private List<PBaFormAttributeValue> pBaFormAttributeValueList2=new ArrayList<>();
    private List<PBaFormAttributeValue> pBaFormAttributeValueList3=new ArrayList<>();

    private List<PAttributeValueData> pValueDataList1 = new ArrayList<>();
    private List<PAttributeValueData> pValueDataList2 = new ArrayList<>();
    private List<PAttributeValueData> pValueDataList3 = new ArrayList<>();

//    审核人id 名字
    private Long reviewerId;
    private String reviewerName;
}
