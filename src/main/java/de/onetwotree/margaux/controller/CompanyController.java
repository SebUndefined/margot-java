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
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.validation.Valid;
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
        model.addAttribute("urlId", id);
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
    public String addCompanySubmit(@ModelAttribute("Company") @Valid Company company,
                                   BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("company", new Company());
            return "Company/editCompany";
        }
        try {
            companyRepository.saveAndFlush(company);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return "redirect:/company/";
    }
    @GetMapping(value = "/update/{id}")
    public String updateCompany(@ModelAttribute("company") Company company,
                                    Model model,
                                    @PathVariable(value = "id") String id) {
        model.addAttribute("company", companyRepository.findOne(Long.valueOf(id)));
        model.addAttribute("mainCompanies", mainCompanyRepository.findAll());
        return "Company/updateCompany";
    }
    @PostMapping(value = "/update/{id}")
    public String updateCompanySubmit(@ModelAttribute("company") Company company,
                                          Model model,
                                          @PathVariable(value = "id") String id) throws ItemNotFoundException {
        Company companyOrigin = companyRepository.findOne(Long.valueOf(id));
        if (companyOrigin == null) {
            throw new ItemNotFoundException(Long.valueOf(id), "company/");
        }
        try {
            companyOrigin.setName(company.getName());
            companyOrigin.setMainCompany(company.getMainCompany());
            companyRepository.saveAndFlush(companyOrigin);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return "redirect:/company/";
    }


}
