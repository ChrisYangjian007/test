package com.dalian.sea.parameter;

import com.dalian.sea.json.ImageJson;
import com.dalian.sea.model.PuReceiveTest;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */
@Data
public class PPuReceiveTestMapper extends PuReceiveTest{
    /**
     * goods_type 货品类型
     */
    private String goodsType;
    /**
     *货品类型Id
     */
    private Long goodsTypeId;
    /**
     * batch_no 批次号
     */
    private String batchNo;
    /**
     * product_name 产品名称
     */
    private String productName;
    /**
     *产品Id
     */
    private Long productId;
    /**
     * product_spec_name 规格
     */
    private String productSpecName;
    /**
     * enterprise_name 供应商名称
     */
    private String enterpriseName;
    /**
     * weight 数量
     */
    private BigDecimal weight;
    /**
     * unit_name 单位
     */
    private String unitName;
    /**
     * deliver_name 收货人
     */
    private String deliverName;
    /**
     * deliver_date 收货时间
     */
    private Date deliverDate;
    /**
     * c_name 字典名称
     */
    private String dataDictionaryDetailscName;
    /**
     * 字典id
     */
    private Long dataDictionaryDetailsId;
    /**
     * 检验方式
     */
    private String testCName;
    /**
     * 收货时间开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date StartTime;
    /**
     * 收货时间结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date TerminalTime;

}
