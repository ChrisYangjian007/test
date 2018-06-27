package com.dalian.sea.parameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author 杨文波
 * @date 2018/3/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JasperReportData {
    private Long receiveId;
    private Integer listIndex;
    private String enterpriseName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp deliverDate;
    private String receiveNo;
    private String deliverName;
    private String consignee;

    private String remark;
    private String batchNo;
    private String goodsType;
    private String productName;
    private BigDecimal weight;
    private String unitName;
    private String note;
    private String productSpecName;


}
