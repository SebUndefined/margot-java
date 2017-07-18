package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.dao.UserDao;
import de.onetwotree.margaux.entity.MainCompany;
import de.onetwotree.margaux.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.transaction.Transactional;


/**
 * Created by SebUndefined on 12/07/17.
 */
@Controller
public class WebController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    @Transactional
    public String homeAction() {
        User user = userDao.findUser((long) 1);
        System.out.println(user.getEmail());
        return "home";
    }
    @RequestMapping("/test")
    public String testAction() {
        System.out.println("prout");
        return "test";
    }
    @RequestMapping(value = "/maincompany/add", method = RequestMethod.GET)
    public String addMainCompany(Model model) {
        model.addAttribute("MainCompany", new MainCompany());
        return "editMainCompany";
    }

}
