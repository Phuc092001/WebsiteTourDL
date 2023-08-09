/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.configs;

import com.ldp.validator.NguoiDungValidator;
import com.ldp.validator.WebAppValidator;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author ACER
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement // cai nay để nó tu dong/mo sessionFactory
@ComponentScan(basePackages = {
        "com.ldp.controllers",
        "com.ldp.repository",
        "com.ldp.service",
        "com.ldp.validator" 
})// de no biet controler o dau
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver(){
        //tao Ioc
        InternalResourceViewResolver resource = new InternalResourceViewResolver();
        
        resource.setViewClass(JstlView.class);
        resource.setPrefix("/WEB-INF/jsp/");
        resource.setSuffix(".jsp");
        return resource;
    }
    
    //chi dinh vi tri chua tai nguyen(ngan hon)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/resources/css/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("/resources/images/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/resources/js/");
        
    }
    
    //label Property Files
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        
        source.setBasename("messages");
        
        return source;
    }
    
    //muon nhan được multipartResolver gửi lên server
    //upload dang nao cung can thang nay
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    //Bean Validator
    @Override
    public Validator getValidator() {
        return validator();
    }
    
    //Bean Validator
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean b = new LocalValidatorFactoryBean();
        
        b.setValidationMessageSource(messageSource());
        return b;
    }
    
    @Bean
    public WebAppValidator nguoiDungValidator() {
        Set<Validator> springValidators = new HashSet<>();
        
        springValidators.add((Validator) new NguoiDungValidator()); 
        
        WebAppValidator v = new WebAppValidator();
        v.setSpringValidators(springValidators);
        
        return v;
    }
}
