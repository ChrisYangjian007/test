package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "pu_enter_stock")
@Data
public class PuEnterStock implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "enter_stock_id")
    private Long enterStockId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 操作人员ID
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 修改用户主键
     */
    @Column(name = "update_user_id")
    private Long updateUserId;

    /**
     * 状态(1-正常,6-删除,2-禁用)
     */
    private Byte status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 公司ID
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 排序
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 入库时间
     */
    @Column(name = "enter_date")
    private Date enterDate;

    /**
     * 仓库ID
     */
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**
     * 入库编号
     */
    @Column(name = "enter_no")
    private String enterNo;

    /**
     * 入库申请人
     */
    @Column(name = "enter_person")
    private String enterPerson;

    /**
     * 经手人
     */
    private String brokerage;

    /**
     * 生产任务名称
     */
    @Column(name = "produce_task_name")
    private String produceTaskName;

    /**
     * 生产任务编号
     */
    @Column(name = "produce_task_no")
    private String produceTaskNo;

    /**
     * 任务主键
     */
    @Column(name = "produce_task_id")
    private Long produceTaskId;

    /**
     * 时间预警
     */
    @Column(name = "time_warn")
    private Integer timeWarn;

    /**
     * 库存预警
     */
    @Column(name = "stock_warn")
    private Integer stockWarn;

    /**
     * 相关ID
     */
    @Column(name = "related_id")
    private Long relatedId;

    /**
     * 1-采购,2-销售,3-入库,4-出库,5-生产任务
     */
    @Column(name = "related_type")
    private Byte relatedType;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", enterStockId=").append(enterStockId);
        sb.append(", createDate=").append(createDate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", companyId=").append(companyId);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", enterDate=").append(enterDate);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", enterNo=").append(enterNo);
        sb.append(", enterPerson=").append(enterPerson);
        sb.append(", brokerage=").append(brokerage);
        sb.append(", produceTaskName=").append(produceTaskName);
        sb.append(", produceTaskNo=").append(produceTaskNo);
        sb.append(", produceTaskId=").append(produceTaskId);
        sb.append(", timeWarn=").append(timeWarn);
        sb.append(", stockWarn=").append(stockWarn);
        sb.append(", relatedId=").append(relatedId);
        sb.append(", relatedType=").append(relatedType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}