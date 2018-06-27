package com.dalian.sea.parameter;

import com.dalian.sea.model.SysSysLog;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YH
 */
@Data
public class PSysLogSys extends SysSysLog implements Serializable {

    private String userName;
    private String resourceName;

    /**
     * 操作时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

}
