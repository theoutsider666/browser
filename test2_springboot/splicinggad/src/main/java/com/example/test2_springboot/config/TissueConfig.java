package com.example.test2_springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class TissueConfig implements WebMvcConfigurer {
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor(){
//        MybatisPlusInterceptor interceptor=new MybatisPlusInterceptor();
////        添加具体拦截器 PaginationInnerInterceptor专门用来做分页
////        想添加其他的就用addInnerInterceptor方法
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
//        return interceptor;
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有接口
                .allowCredentials(true) // 是否发送 Cookie
                .allowedOriginPatterns("*") // 支持域
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"}) // 支持方法
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/data/**").addResourceLocations("file:C://Users//MI//Desktop//bishe//singcell_spring//data//");
    }


}
