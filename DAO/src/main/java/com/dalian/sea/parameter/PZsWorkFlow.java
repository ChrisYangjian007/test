package com.dalian.sea.parameter;

import com.dalian.sea.model.ZsWorkFlow;
import lombok.Data;

import java.io.Serializable;

/**
 * PZsWorkFlow
 *
 * @author TONE
 * @date 2018/2/26.
 */
@Data
public class PZsWorkFlow extends ZsWorkFlow implements Serializable {

    private String parentName;


}
