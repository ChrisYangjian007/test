package com.dalian.sea.parameter;

import com.dalian.sea.model.SaOrder;
import lombok.Data;

import java.util.List;

/**
 * PSaOrderDetailsPDA
 *
 * @author TONE
 * @date 2018/3/28.
 */
@Data
public class PSaOrderDetailsPDA {
    /**
     * 订单信息
     */
    private SaOrder saOrder;
    /**
     * 订单信息
     */
    private List<PSaOrderPDA> saOrderPDAList;

}
