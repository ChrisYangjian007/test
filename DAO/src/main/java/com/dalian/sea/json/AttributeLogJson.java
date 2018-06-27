package com.dalian.sea.json;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * AttributeLogJson
 *
 * @author TONE
 * @date 2018/3/12.
 */
@Data
public class AttributeLogJson {

    private Long userId;

    private String userName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private String context;

}
