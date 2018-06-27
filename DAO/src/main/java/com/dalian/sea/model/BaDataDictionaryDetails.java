package com.dalian.sea.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "ba_data_dictionary_details")
@Data
public class BaDataDictionaryDetails implements Serializable {
    /**
     * 数据字段明细主键
     */
    @Id
    @Column(name = "data_dictionary_details_id")
    private Long dataDictionaryDetailsId;

    /**
     * 数据字典ID
     */
    @Column(name = "data_dictionary_id")
    private Long dataDictionaryId;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    @Column(name = "c_name")
    private String cName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    @Column(name = "list_index")
    private Integer listIndex;

    /**
     * 标识层
     */
    private String flage;

    /**
     * 标识层记录名称
     */
    @Column(name = "flage_name")
    private String flageName;

    /**
     * 所在层
     */
    @Column(name = "a_level")
    private Integer aLevel;

    /**
     * 上一层 ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 第一层ID
     */
    @Column(name = "first_id")
    private Long firstId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 创建者ID
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 更新者ID
     */
    @Column(name = "update_user_id")
    private Long updateUserId;

    /**
     * 状态（1-正常，2-禁用，6-删除）
     */
    private Byte status;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dataDictionaryDetailsId=").append(dataDictionaryDetailsId);
        sb.append(", dataDictionaryId=").append(dataDictionaryId);
        sb.append(", code=").append(code);
        sb.append(", cName=").append(cName);
        sb.append(", remark=").append(remark);
        sb.append(", listIndex=").append(listIndex);
        sb.append(", flage=").append(flage);
        sb.append(", flageName=").append(flageName);
        sb.append(", aLevel=").append(aLevel);
        sb.append(", parentId=").append(parentId);
        sb.append(", firstId=").append(firstId);
        sb.append(", createDate=").append(createDate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}