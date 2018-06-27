package com.dalian.sea.parameter;

import com.dalian.sea.model.TagSweep;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author YH
 */
@Data
public class TagSweepPara extends TagSweep {

    /**
     * 扫码次数
     */
    private Integer sweepCount;

    /**
     * 最新扫码时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date latestTime;
}
