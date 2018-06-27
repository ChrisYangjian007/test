package com.dalian.sea.config.freemarker;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author 杨文波
 * @date 2017/12/26
 */
@Component
public class ShiroTagFreeMarkerConfigurer implements InitializingBean {

    @Autowired
    @Qualifier("freeMarkerConfiguration")
    private Configuration configuration;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 加上这句后，可以在页面上使用shiro标签
        configuration.setSharedVariable("shiro", new ShiroTags());
    }
}
