package de.onetwotree.margaux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by SebUndefined on 12/07/17.
 */
@Controller
public class WebController {

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping("/")
    public String homeAction() {
        return "home";
    }
    @RequestMapping("/test")
    public String testAction() {
        System.out.println("prout");
        return "test";
    }
}
