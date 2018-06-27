package com.dalian.sea.parameter;

import com.dalian.sea.json.ImageJson;
import com.dalian.sea.model.ZsXqKitchen;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9.
 */
@Data
public class PXqKitchen extends ZsXqKitchen {
    private List<ImageJson> seaAreaImagesJson;
    private List<ImageJson> imagesJson;

    /**
     * 是否已经上传图片 1-已上传 2-为上传
     */
    private Integer isHaveImage;
}
