package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "zs_task_qr")
@Data
public class ZsTaskQr implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "task_qr_id")
    private Long taskQrId;

    /**
     * 生产任务ID
     */
    @Column(name = "produce_task_id")
    private Long produceTaskId;

    /**
     * 二维码编号id
     */
    @Column(name = "qr_code_id")
    private Long qrCodeId;

    /**
     * 二维码编号
     */
    private String code;

    private static final long serialVersionUID = 1L;

}