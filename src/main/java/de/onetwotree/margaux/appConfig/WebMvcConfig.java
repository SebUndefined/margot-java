package de.onetwotree.margaux.appConfig;

import de.onetwotree.margaux.application.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;

/**
 * Created by SebUndefined on 19/07/17.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        System.out.println("###################Register Converter##################");
        registry.addConverter(getStringToUser());
        registry.addConverter(getStringToMainCompany());
        registry.addConverter(getStringToCompany());
        registry.addConverter(getStringToProject());
        registry.addConverter(getLocalDateConverter());
    }
    @Bean
    public StringToUser getStringToUser(){
        return new StringToUser();
    }
    @Bean
    public StringToMainCompany getStringToMainCompany(){
        return new StringToMainCompany();
    }
    @Bean
    public StringToCompany getStringToCompany() {
        return new StringToCompany();
    }
    @Bean
    public StringToProject getStringToProject() {
        return new StringToProject();
    }
    @Bean
    public LocalDateConverter getLocalDateConverter(){return new LocalDateConverter("dd-MM-yyyy");}
    //Test with Service
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}
