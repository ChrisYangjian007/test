package com.dalian.sea.parameter;

import com.dalian.sea.model.ReturnGoods;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author YH
 */
@Data
public class ReturnGoodsPara extends ReturnGoods{

    private Long isMaterial;

    private String materialName;

    private String updateUserName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 资源id
     */
    Long resourceId;
}
