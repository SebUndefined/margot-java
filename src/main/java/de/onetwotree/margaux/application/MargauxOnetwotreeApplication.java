package de.onetwotree.margaux.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@ComponentScan({"de.onetwotree.margaux.controller"})
public class MargauxOnetwotreeApplication {

	public static void main(String[] args) {

		SpringApplication.run(MargauxOnetwotreeApplication.class, args);
	}
}
