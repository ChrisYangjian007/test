package com.dalian.sea.py4j;

import com.dalian.sea.FileUpload.OSSClientUtil;
import com.dalian.sea.FileUpload.OssConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class PingYing4JConfig {


    @Bean(name = "py4j")
    public Py4jDictionary Py4jDictionary(){
        return new Py4jDictionary();
    }

    @Bean
    public PinyinConverter pinyinConverter(Py4jDictionary py4jDictionary){
        return new PinyinConverter(py4jDictionary);
    }

}
