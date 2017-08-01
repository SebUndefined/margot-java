package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.application.StringToMainCompany;
import de.onetwotree.margaux.entity.Company;
import de.onetwotree.margaux.service.CompanyService;
import de.onetwotree.margaux.service.MainCompanyService;
import de.onetwotree.margaux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.List;

/**
 * Created by SebUndefined on 18/07/17.
 */
@Controller
@RequestMapping(value = "company")
public class CompanyController {

    @Autowired
    CompanyService companyService;
    @Autowired
    UserService userService;
    @Autowired
    MainCompanyService mainCompanyService;

    @GetMapping(value = "/")
    public String indexCompany(Model model) {
        List<Company> companies = companyService.getAllCompaniesWithManagerAndMainCompany();
        model.addAttribute("companies", companies);
        return "company";
    }
    @GetMapping(value = "{id}")
    public String viewCompany(@PathVariable(value = "id") String id, Model model) {
        Long idCompany = Long.valueOf(id);
        model.addAttribute("company", companyService.getCompanyWithProjects(idCompany));
        return "viewCompany";
    }
    @GetMapping(value = "/add")
    public String addCompanyForm(Model model) {
        model.addAttribute("mainCompanies", mainCompanyService.getAllMainCompany());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("company", new Company());
        return "editCompany";
    }

    @PostMapping(value = "/add")
    public String addCompanySubmit(@ModelAttribute("Company") Company company,
                                   BindingResult result){
        System.out.println("First name of manager ==>" + company.getManager().getFirstname());
        System.out.println("First name of manager ==>" + company.getMainCompany().toString());
        companyService.addCompany(company);
        return "redirect:/company/";
    }
}
