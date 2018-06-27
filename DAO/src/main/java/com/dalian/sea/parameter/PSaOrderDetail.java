package com.dalian.sea.parameter;

import com.dalian.sea.model.SaOrder;
import com.dalian.sea.model.SaOrderDetail;
import lombok.Data;

/**
 * Created by Administrator on 2018/4/18.
 */
@Data
public class PSaOrderDetail extends SaOrderDetail{

    private SaOrder saOrder;
}
