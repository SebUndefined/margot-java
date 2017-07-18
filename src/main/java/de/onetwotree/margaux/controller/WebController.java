package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.dao.MainCompanyDAO;
import de.onetwotree.margaux.dao.UserDao;
import de.onetwotree.margaux.entity.MainCompany;
import de.onetwotree.margaux.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



/**
 * Created by SebUndefined on 12/07/17.
 */
@Controller
@RequestMapping("/")
public class WebController {
    @Autowired
    private UserDao userDao;

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
