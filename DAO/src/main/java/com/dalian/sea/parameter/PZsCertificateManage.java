package com.dalian.sea.parameter;

import com.dalian.sea.json.ImageJson;
import com.dalian.sea.model.ZsCertificateManage;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */
@Data
public class PZsCertificateManage extends ZsCertificateManage{

    /**
     * 产品大类名称
     */
    private String bigProductTypeName;

    /**
     * 产品小类名称
     */
    private String smallProductTypeName;

    /**
     * 产品生产过程名称
     */
    private String productionProcessName;

    /**
     * 证书图片
     */
    private List<ImageJson> imageJsonList;

    /**
     * 是否上传图片 1-已上传 2-未上传
     */
    private Integer isHaveIamge;
}
