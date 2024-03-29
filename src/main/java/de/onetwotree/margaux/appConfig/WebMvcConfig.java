package de.onetwotree.margaux.appConfig;

import de.onetwotree.margaux.application.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
        registry.addConverter(getStringToRole());
    }
    @Bean
    public StringToUser getStringToUser(){
        return new StringToUser();
    }
    @Bean
    public StringToHolding getStringToMainCompany(){
        return new StringToHolding();
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

    @Bean
    public StringToRole getStringToRole() {
        return new StringToRole();
    }
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
