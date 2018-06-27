package com.dalian.sea.FileUpload;

import com.dalian.sea.FileUpload.OSSClientUtil;
import com.dalian.sea.FileUpload.OssConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author 杨文波
 * @date 2018/3/2
 */
@Configuration
public class AliOssConf {

    @Bean(name = "config")
    public OssConfig config(){
        return new OssConfig();
    }

    @Bean
    public OSSClientUtil ossClientUtil(OssConfig ossConfig){
        return new OSSClientUtil(ossConfig);
    }
}
