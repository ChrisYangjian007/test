package com.dalian.sea.orika;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author 杨文波
 * @date 2018/3/28
 */
@Configuration
public class OrikaConfig {
    @Bean(name = "mapperFactory")
    public MapperFactory getFactory(){
        return new DefaultMapperFactory.Builder().build();
    }
}
