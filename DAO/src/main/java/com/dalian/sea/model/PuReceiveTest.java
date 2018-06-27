package com.dalian.sea.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;
import javax.persistence.*;

@Table(name = "pu_receive_test")
@Data
public class PuReceiveTest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "create_user_id")
    private Long createUserId;

    @Column(name = "update_user_id")
    private Long updateUserId;

    /**
     * receive_detail的id
     */
    @Column(name = "receive_detail_id")
    private Long receiveDetailId;

    /**
     * 检验人员
     */
    private String inspectors;

    /**
     * 检验方式，关联数据字典详情
     */
    @Column(name = "inspection_method")
    private Long inspectionMethod;

    /**
     * 数据字典详情id
     */
    @Column(name = "inspec_data_dic_id")
    private Long inspecDataDicId;

    /**
     * 检验时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inspectionDate;

    /**
     * 1合格，2不合格
     */
    @Column(name = "test_result")
    private Byte testResult;

    /**
     * 附件
     */
    private String iamges;

    /**
     * 备注
     */
    private String remarks;

    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", receiveDetailId=").append(receiveDetailId);
        sb.append(", inspectors=").append(inspectors);
        sb.append(", inspectionMethod=").append(inspectionMethod);
        sb.append(", inspecDataDicId=").append(inspecDataDicId);
        sb.append(", inspectionDate=").append(inspectionDate);
        sb.append(", testResult=").append(testResult);
        sb.append(", iamges=").append(iamges);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}