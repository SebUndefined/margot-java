package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.dao.MainCompanyDAO;
import de.onetwotree.margaux.dao.UserDao;
import de.onetwotree.margaux.entity.MainCompany;
import de.onetwotree.margaux.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created by SebUndefined on 18/07/17.
 */
@Controller
@RequestMapping(value = "maincompany")
public class MainCompanyController {

    @Autowired
    private MainCompanyDAO mainCompanyDAO;

    @Autowired
    private UserDao userDao;


    @GetMapping(value = "/")
    public String MainCompanyIndex(Model model) {
        model.addAttribute("MainCompanies", mainCompanyDAO.findAll());
        return "mainCompany";
    }
    @GetMapping(value = "add/")
    public String addMainCompanyForm(Model model) {
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("MainCompany", new MainCompany());
        return "editMainCompany";
    }
    //public String addMainCompanySubmit(@ModelAttribute MainCompany mainCompany) {
    @PostMapping(value = "add/")
    public String addMainCompanySubmit(@ModelAttribute("MainCompany") MainCompany mainCompany, BindingResult result) {
        System.out.println(result.toString());
        mainCompanyDAO.addMainCompany(mainCompany);
        return "redirect:/maincompany/";
    }
}
