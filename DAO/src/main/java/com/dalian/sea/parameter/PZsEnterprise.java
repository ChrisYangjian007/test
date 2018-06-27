package com.dalian.sea.parameter;

import com.dalian.sea.json.ImageJson;
import com.dalian.sea.model.ZsEnterprise;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */
@Data
public class PZsEnterprise extends ZsEnterprise implements Serializable {
    /**
     *创建人名称
     */
    private String createUserName;

    /**
     *修改人名称
     */
    private String updateUserName;

    /**
     * 其他证书图片
     */
    private List<ImageJson> otherLicenseImageList;
}
