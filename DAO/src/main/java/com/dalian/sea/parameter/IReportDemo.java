package com.dalian.sea.parameter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 杨文波
 * @date 2018/3/13
 */
@Data
public class IReportDemo {
    private String demo;
    private Integer hello;

    public static List<IReportDemo> init(){
        List<IReportDemo> demos = new ArrayList<>();
        IReportDemo demo = new IReportDemo();
        demo.setDemo("Wahaha");
        demo.setHello(1);
        demos.add(demo);
        demos.add(demo);
        return demos;
    }
}
