package com.dalian.sea.parameter;

import com.dalian.sea.json.ContentJson;
import com.dalian.sea.json.ImageJson;
import com.dalian.sea.model.BaCompany;
import lombok.Data;

import java.util.List;

/**
 * Created by TONE on 2018/2/5.
 */
@Data
public class PBaCompany extends BaCompany {

    private List<ImageJson> seaAreaImagesJson;
    private List<ImageJson> enterpriseImagesJson;
    private List<ImageJson> panoramaJson;
    private List<ImageJson> detectionCenterImagesJson;
    private List<ContentJson> addContentJson;


}
