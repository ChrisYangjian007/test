package com.dalian.sea.config.jasper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

/**
 *
 * @author 杨文波
 * @date 2018/3/19
 */
@Configuration
public class AdditionalConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public JasperReportsViewResolver getJasperReportsViewResolver() {

        JasperReportsViewResolver resolver = new JasperReportsViewResolver();
        resolver.setPrefix("classpath:jasper/");
        resolver.setSuffix(".jasper");

        resolver.setReportDataKey("datasource");
        resolver.setViewNames("*report*");
        resolver.setViewClass(JasperReportsMultiFormatView.class);
        resolver.setOrder(6);
        return resolver;
    }

}
