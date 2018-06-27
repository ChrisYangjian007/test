package com.dalian.sea.parameter;

import com.dalian.sea.model.BaDataDictionaryDetails;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/18.
 */
@Data
public class PBaDataDictionaryDetails extends BaDataDictionaryDetails implements Serializable{

    /**
     *创建人姓名
     */
    private String createUserName;

    /**
     *修改人姓名
     */
    private String updateUserName;

}
