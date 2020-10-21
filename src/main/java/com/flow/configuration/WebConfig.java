package com.flow.configuration;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * @author 蔡小蔚
 */
@Configuration
@ComponentScan(basePackages = {"com.flow"})
public class WebConfig {

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public PropertiesFactoryBean getPropertiesFactoryBean(){
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("/freemarker.properties"));
        return bean;
    }

    @Bean
    public FreeMarkerViewResolver getFreeMarkerViewResolver(){
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
//        resolver.setPrefix("/WEB-INF/html/");
        resolver.setSuffix(".html");
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setCache(true);

        return resolver;
    }

    @Bean
    public FreeMarkerConfig getFreeMarkerConfig(){
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
//        configurer.setResourceLoader();
        return configurer;
    }

}
