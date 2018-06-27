package com.dalian.sea.parameter;

import com.dalian.sea.model.ZsStock;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 陈逸文 on 2018/3/5.
 */
@Data
public class PZsStock extends ZsStock implements Serializable{

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 仓库名称
     */
    private String warehouseName;

    private String materialName;//货物类型名称

    private String productStatusName;//产品状态名称

    private Integer isSeaCucumber;//是否入活参库

    private Integer warningDay;//仓库预警天数

    private Byte isWarning;//是否预警（1.即将过期 2.未过期）

    private Byte isStockWarning;//库存是否预警（1.已预警 2.未预警）

    private Integer overdueCount;//时间预警总数

    private Integer stockWarningCount;//库存预警总数

    private Long goodsTypeId;//货物类型（联动时使用）

    private BigDecimal weightSum;//总数量

    private BigDecimal inWeightSum;//新入库总数量

    private Long productTypeId;//产品大类ID

    private String productTypeName;//产品大类名称

    private Long stockReceiveId;//入库收货单详情ID
    /**
     * 货物类型数组
     */
    private Long[] materialArray;

    /**
     * 产品大类数组
     */
    private Long[] productTypeArray;

    /**
     * 产品小类数组
     */
    private Long[] productIdArray;

    /**
     * 规格数组
     */
    private String[] productSpecArray;

    /**
     *  时间预警与当前时间天数差
     */
    private Long dateLast;
}
