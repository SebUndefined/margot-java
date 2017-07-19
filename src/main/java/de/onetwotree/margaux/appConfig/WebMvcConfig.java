package de.onetwotree.margaux.appConfig;

import de.onetwotree.margaux.application.StringToUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
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
    }
    @Bean
    public StringToUser getStringToUser(){
        return new StringToUser();
    }
}
