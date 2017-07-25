package de.onetwotree.margaux.appConfig;

import de.onetwotree.margaux.application.LocalDateConverter;
import de.onetwotree.margaux.application.StringToCompany;
import de.onetwotree.margaux.application.StringToMainCompany;
import de.onetwotree.margaux.application.StringToUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

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
    public LocalDateConverter getLocalDateConverter(){return new LocalDateConverter("dd-MM-yyyy");}
    //Test with Service
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }
}
