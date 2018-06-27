package com.dalian.sea.parameter;

import com.dalian.sea.json.ImageJson;
import com.dalian.sea.model.ZsWarehouseImages;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * PZsWarehouseImages
 *
 * @author TONE
 * @date 2018/5/29
 */
@Data
public class PZsWarehouseImages extends ZsWarehouseImages implements Serializable {

    private String createUserName;
    private String updateUserName;

    private List<ImageJson> warehouseImagesJson;



}
