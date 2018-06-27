package com.dalian.sea.parameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author 杨文博
 * @date 2018/4/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoxLabel {
    private String batchNo;
    private String materialName;
    private String productName;
    private String productStatusName;
    private String productSpecName;
    private BigDecimal inWeight;
    private String unitName;
    private Timestamp createDate;
    private BigDecimal every;
    private Integer num;
    private String qrcode;
}
