package de.onetwotree.margaux.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan({"de.onetwotree.margaux.controller", "de.onetwotree.margaux.dao", "de.onetwotree.margaux.appConfig"})
@EntityScan(basePackages = "de.onetwotree.margaux.entity")
public class MargauxOnetwotreeApplication {

	public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(MargauxOnetwotreeApplication.class, args);
    }
}
