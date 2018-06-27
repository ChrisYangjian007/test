package com.dalian.sea.parameter;

import com.dalian.sea.json.ImageJson;
import com.dalian.sea.model.ZsCompanyCulture;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 */
@Data
public class PZsCompanyCulture extends ZsCompanyCulture{

    private List<ImageJson> imageJsonList;

    /**
     * 是否上传图片 1-已经上传 2-未上传
     */
    private Integer IsHaveIamge;
}
