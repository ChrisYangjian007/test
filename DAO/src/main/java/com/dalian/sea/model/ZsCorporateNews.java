package com.dalian.sea.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "zs_corporate_news")
@Data
public class ZsCorporateNews implements Serializable {
    @Id
    @Column(name = "corporate_news_id")
    private Long corporateNewsId;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 链接
     */
    @Column(name = "link")
    private String link;

    /**
     * 时间
     */
    @Column(name = "corporate_news_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date corporateNewsDate;

    /**
     * 图片
     */
    @Column(name = "image")
    private String image;

    /**
     * 名称
     */
    @Column(name = "corporate_news_name")
    private String corporateNewsName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
        sb.append(", corporateNewsId=").append(corporateNewsId);
        sb.append(", title=").append(title);
        sb.append(", link=").append(link);
        sb.append(", corporateNewsDate=").append(corporateNewsDate);
        sb.append(", image=").append(image);
        sb.append(", corporateNewsName=").append(corporateNewsName);
        sb.append(", remark=").append(remark);
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