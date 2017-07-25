package de.onetwotree.margaux.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;


@SpringBootApplication
@ComponentScan({"de.onetwotree.margaux.controller", "de.onetwotree.margaux.dao", "de.onetwotree.margaux.appConfig",
        "de.onetwotree.margaux.service"})
@EntityScan(basePackages = "de.onetwotree.margaux.entity")
public class MargauxOnetwotreeApplication {


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MargauxOnetwotreeApplication.class, args);
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

}
