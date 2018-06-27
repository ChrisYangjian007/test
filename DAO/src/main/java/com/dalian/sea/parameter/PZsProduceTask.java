package com.dalian.sea.parameter;

import com.dalian.sea.model.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/26.
 */
@Data
public class PZsProduceTask extends ZsProduceTask{

    /**
     * 出库次数
     */
    private Integer leaveStockNumber;

    /**
     * 入库次数
     */
    private Integer enterStockNumber;

    /**
     * 当前工序检查人
     */
    private String currentChecker;

    /**
     * 生产任务当前操作工序
     */
    private String currentOperatingProcedures;

    /**
     * 最新出库信息
     */
    private PSaLeaveStock pSaLeaveStock;

    /**
     * 最新入库信息
     */
    private PPuEnterStock pPuEnterStock;

    private String leaveUser;

    private Date leaveDates;

    private String warehouse;

    private String enterWarehouse;

    private Date enterDates;

    private String enterUser;

    /**
     * 工艺流程
     */
    private ZsWorkFlow workFlow;

    /**
     * 当前工序
     */
    private ZsWorkProcess zsWorkProcess;

    /**
     * 出库单信息
     */
    private SaLeaveStock saLeaveStock;

    /**
     * 当前工艺字段
     */
    private BaFormAttribute formAttribute;

    /**
     * 当前工艺value
     */
    private List<BaFormAttributeValue> formAttributeValueList;

    /**
     * 所有工序
     */
    private List<ZsWorkProcess> workProcessList;

    private List<PZsWorkProcess> pZsWorkProcessList;

    /**
     * 任务完成百分比
     */
    private BigDecimal percent=new BigDecimal(0.0000).setScale(4,BigDecimal.ROUND_HALF_UP);


    private List<PBaFormAttributeValue> pBaFormAttributeValueList;

    private Long lastUserId;
    private String lastUserName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLeaveDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    private Integer auditStatus;

    private Long resourceId;

    /**
     * 结束人
     */
    private String endUserName;
 }
