package com.dalian.sea.config.freemarker;


import com.google.common.annotations.Beta;
import com.google.common.collect.Maps;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 杨文波
 * @date 2018/1/3
 */
@Configuration
public class StaticResourceUrl{
    @Bean
    public CommandLineRunner customFreemarker(FreeMarkerViewResolver resolver) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                //增加视图
                resolver.setViewClass(MyFreemarkerView.class);
                //添加自定义解析器，具体实现请自行百度！
                /*Map map = resolver.getAttributesMap();
                map.put("conver", new MyConver());*/
            }
        };
    }
}
