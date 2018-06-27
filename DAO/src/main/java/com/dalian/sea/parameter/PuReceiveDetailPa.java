package com.dalian.sea.parameter;

import com.dalian.sea.model.PuReceiveDetail;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author YH
 */
@Data
public class PuReceiveDetailPa extends PuReceiveDetail {

    //供应商id
    private Long enterpriseId;

    //供应商名称
    private String enterpriseName;

    /**
     * 退货数量
     */
    private BigDecimal count;

    /**
     * 收货时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deliverDate;

    /**
     * 退货时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returnTime;

    /**
     * 发货人
     */
    private String deliverName;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 资源id
     */
    private Long resourceId;
}
