package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.dao.MainCompanyDAO;
import de.onetwotree.margaux.entity.MainCompany;
import de.onetwotree.margaux.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by SebUndefined on 18/07/17.
 */
@Controller
@RequestMapping(value = "maincompany")
public class MainCompanyController {

    @Autowired
    private MainCompanyDAO mainCompanyDAO;

    @GetMapping(value = "/")
    public String MainCompanyIndex(Model model) {
        model.addAttribute("MainCompanies", mainCompanyDAO.findAll());
        return "mainCompany";
    }
    @GetMapping(value = "add/")
    public String addMainCompanyForm(Model model) {
        model.addAttribute("MainCompany", new MainCompany());
        return "editMainCompany";
    }
    @PostMapping(value = "add/")
    public String addMainCompanySubmit(@ModelAttribute MainCompany mainCompany) {
        User user = new User();
        user.setEmail("st");
        user.setUserName("st");
        user.setFirstname("st");
        user.setLastname("st");
        mainCompany.setManager(user);
        mainCompanyDAO.addMainCompany(mainCompany);
        return "redirect:/maincompany/";
    }
}
