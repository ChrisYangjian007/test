package com.dalian.sea.parameter;

import lombok.Data;

import java.util.List;

/**
 * @author YH
 */
@Data
public class TagEcharts {

    /**
     * 生成量list
     */
    List<Integer> generateList;

    /**
     * 绑定量list
     */
    List<Integer> bindList;
}
