package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_qr_code_interval")
@Data
public class ZsQrCodeInterval implements Serializable {
    /**
     * 二维码编码间隔ID
     */
    @Id
    @Column(name = "interval_id")
    private Long intervalId;

    /**
     * 开始编码
     */
    @Column(name = "start_code")
    private String startCode;

    /**
     * 结束编码
     */
    @Column(name = "end_code")
    private String endCode;

    /**
     * 间隔数量
     */
    @Column(name = "interval_number")
    private Long intervalNumber;

    /**
     * 下载状态 1 未下载 2 已下载
     */
    @Column(name = "download_status")
    private Byte downloadStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 操作人员ID
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 修改用户主键
     */
    @Column(name = "update_user_id")
    private Long updateUserId;

    /**
     * 状态(1-正常,6-删除,2-禁用)
     */
    private Byte status;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", intervalId=").append(intervalId);
        sb.append(", startCode=").append(startCode);
        sb.append(", endCode=").append(endCode);
        sb.append(", intervalNumber=").append(intervalNumber);
        sb.append(", downloadStatus=").append(downloadStatus);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}