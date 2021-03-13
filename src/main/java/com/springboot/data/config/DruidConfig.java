package com.springboot.data.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 后台监控
     * 因为 SpringBoot 内置了 servlet 容器，所以没有 web.xml，替代方法：ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        // 后台需要有人登录账号、密码

        // 设置初始化参数
        Map<String, String> params = new HashMap<>();
        // 增加配置
        params.put("loginUsername", "admin");
        params.put("loginPassword", "123456");

        // 允许谁能访问
        params.put("allow", "");

        //禁止谁能访问
//        params.put("")

        bean.setInitParameters(params);
        return bean;
    }

    /**
     * filter
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        // 可以过滤哪些请求？
        Map<String, String> initParameters = new HashMap<>();
        // 这些不进行统计
        initParameters.put("exclusions", "*.js, *.css, /druid/*");
        bean.setInitParameters(initParameters);
        return bean;
    }

}
