package com.dalian.sea.parameter;

import com.dalian.sea.model.BaResource;
import lombok.Data;

import java.util.List;

/**
 * PBaResource
 *
 * @author TONE
 * @date 2018/3/13.
 */
@Data
public class PBaResource extends BaResource {

    private Long roleId;
    private List<BaResource> resourceList;
}
