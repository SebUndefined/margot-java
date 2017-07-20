package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.Utils.MargauxException;
import de.onetwotree.margaux.dao.MainCompanyDAO;
import de.onetwotree.margaux.dao.UserDao;
import de.onetwotree.margaux.entity.MainCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


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
    @PostMapping(value = "add/")
    public String addMainCompanySubmit(@ModelAttribute("MainCompany") MainCompany mainCompany, BindingResult result) {
        mainCompanyDAO.addMainCompany(mainCompany);
        return "redirect:/maincompany/";
    }
    @GetMapping(value = "{id}")
    public String viewMainCompany(@PathVariable(value = "id") String id, Model model){
        try {
            Long idMainCompany = Long.valueOf(id);
            System.out.println(idMainCompany);
            MainCompany mainCompany = mainCompanyDAO.findOnebyId(idMainCompany);
            model.addAttribute("maincompany", mainCompany);
            return "viewMainCompany";
        } catch (MargauxException e) {
            model.addAttribute("exception", e);
            return "error";
        }
    }
}
