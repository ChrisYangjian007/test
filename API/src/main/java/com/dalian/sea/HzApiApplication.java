package com.dalian.sea;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 杨文波
 * @date 2017/12/27
 */
@SpringBootApplication
@ServletComponentScan  //spring boot里面提供了该注解起到注册作用
@EnableTransactionManagement  //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
@EnableAspectJAutoProxy
@MapperScan("com.dalian.sea.mapper")
public class HzApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HzApiApplication.class, args);
	}
}
