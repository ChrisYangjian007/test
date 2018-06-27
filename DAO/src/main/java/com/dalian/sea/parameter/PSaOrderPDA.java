package com.dalian.sea.parameter;

import com.dalian.sea.model.BoxOrder;
import com.dalian.sea.model.SaOrder;
import lombok.Data;

import java.util.List;

/**
 * PSaOrderPDA
 *
 * @author TONE
 * @date 2018/3/28.
 */
@Data
public class PSaOrderPDA {

    /**
     * 箱码信息
     */
    private BoxOrder boxOrder;

    /**
     * 订单详情
     */
    private List<PSaOrderDetailPDA> orderDetailPDAList;



}
