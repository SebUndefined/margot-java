package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.exception.ItemNotFoundException;
import de.onetwotree.margaux.dao.CompanyRepository;
import de.onetwotree.margaux.dao.MainCompanyRepository;
import de.onetwotree.margaux.entity.Company;
import de.onetwotree.margaux.service.CompanyService;
import de.onetwotree.margaux.service.UserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    CompanyRepository companyRepository;
    @Autowired
    UserService userService;
    @Autowired
    MainCompanyRepository mainCompanyRepository;

    @GetMapping(value = "/")
    public String indexCompany(Model model) {
        List<Company> companies = companyRepository.findAll();
        model.addAttribute("companies", companies);
        return "Company/company";
    }
    @GetMapping(value = "view/{id}")
    public String viewCompany(@PathVariable(value = "id") String id, Model model) throws ItemNotFoundException {
        Long idCompany = Long.valueOf(id);
        Company company = companyRepository.findOne(idCompany);
        if (company == null) throw new ItemNotFoundException(idCompany, "company/");
        model.addAttribute("company", company);
        return "Company/viewCompany";
    }
    @GetMapping(value = "/add")
    public String addCompanyForm(Model model) {
        model.addAttribute("mainCompanies", mainCompanyRepository.findAll());
        model.addAttribute("company", new Company());
        return "Company/editCompany";
    }

    @PostMapping(value = "/add")
    public String addCompanySubmit(@ModelAttribute("Company") Company company,
                                   BindingResult result){
        //System.out.println("First name of manager ==>" + company.getManager().getFirstname());
        System.out.println("First name of manager ==>" + company.getMainCompany().toString());
        try {
            companyRepository.saveAndFlush(company);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return "redirect:/company/";
    }


}
