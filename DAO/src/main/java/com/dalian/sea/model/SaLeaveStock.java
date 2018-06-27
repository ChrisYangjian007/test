package com.dalian.sea.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "sa_leave_stock")
@Data
public class SaLeaveStock implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "leave_stock_id")
    private Long leaveStockId;

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
     * 出库时间
     */
    @Column(name = "leave_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date leaveDate;

    /**
     * 仓库ID
     */
    @Column(name = "warehouse_id")
    private Long warehouseId;

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
     * 生产任务标签
     */
    @Column(name = "produce_task_tag")
    private String produceTaskTag;

    /**
     * 出库编号
     */
    @Column(name = "leave_no")
    private String leaveNo;

    /**
     * 出库申请人
     */
    @Column(name = "leave_person")
    private String leavePerson;

    /**
     * 经手人
     */
    private String brokerage;

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
        sb.append(", leaveStockId=").append(leaveStockId);
        sb.append(", createDate=").append(createDate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", companyId=").append(companyId);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", leaveDate=").append(leaveDate);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", produceTaskName=").append(produceTaskName);
        sb.append(", produceTaskNo=").append(produceTaskNo);
        sb.append(", produceTaskId=").append(produceTaskId);
        sb.append(", leaveNo=").append(leaveNo);
        sb.append(", leavePerson=").append(leavePerson);
        sb.append(", brokerage=").append(brokerage);
        sb.append(", relatedId=").append(relatedId);
        sb.append(", relatedType=").append(relatedType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}