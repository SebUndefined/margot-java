package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.Utils.MargauxException;
import de.onetwotree.margaux.dao.MainCompanyDAO;
import de.onetwotree.margaux.dao.UserDao;
import de.onetwotree.margaux.entity.Company;
import de.onetwotree.margaux.entity.MainCompany;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.Project;
import de.onetwotree.margaux.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by SebUndefined on 18/07/17.
 */
@Controller
@RequestMapping(value = "maincompany")
public class MainCompanyController {

//    @Autowired
//    private MainCompanyDAO mainCompanyDAO;
//
    //@Autowired
    //private UserDao userDao;
    @Autowired
    private MainCompanyService mainCompanyService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PlotService plotService;

    @GetMapping(value = "/")
    public String MainCompanyIndex(Model model) {
        List<MainCompany> mainCompanies = mainCompanyService.getAllMainCompanyWithManager();
        model.addAttribute("MainCompanies", mainCompanies);
        return "mainCompany";
    }
    @GetMapping(value = "add/")
    public String addMainCompanyForm(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("MainCompany", new MainCompany());
        return "editMainCompany";
    }
    @PostMapping(value = "add/")
    public String addMainCompanySubmit(@ModelAttribute("MainCompany") MainCompany mainCompany,
                                       BindingResult result) {
        mainCompanyService.addMainCompany(mainCompany);
        return "redirect:/maincompany/";
    }
    @GetMapping(value = "{id}")
    public String viewMainCompany(@PathVariable(value = "id") String id, Model model){
        Long idMainCompany = Long.valueOf(id);
        MainCompany mainCompany = mainCompanyService.getMainCompanyForView(idMainCompany);
        model.addAttribute("urlId", id);
        model.addAttribute("maincompany", mainCompany);
        return "viewMainCompany";
    }
    @GetMapping(value = "{id}/companies/")
    public String viewCompaniesOfMainCompany(@PathVariable(value = "id") String id, Model model){
        Long idMainCompany = Long.valueOf(id);
        List<Company> companies = companyService.getAllCompaniesForMainCompany(idMainCompany);
        model.addAttribute("urlId", id);
        model.addAttribute("companies", companies);
        return "viewCompanyofMainCompany";
    }
    @GetMapping(value = "{id}/projects/")
    public String viewProjectsOfMainCompany(@PathVariable(value = "id") String id, Model model){
        Long idMainCompany = Long.valueOf(id);
        List<Project> projects= projectService.getAllProjectsForMainCompany(idMainCompany);
        model.addAttribute("urlId", id);
        model.addAttribute("projects", projects);
        return "viewProjectsofMainCompany";
    }
    @GetMapping(value = "{id}/plots/")
    public String viewPlotsOfMainCompany(@PathVariable(value = "id") String id, Model model){
        Long idMainCompany = Long.valueOf(id);
        List<Plot> plots= plotService.getAllPlotForMainCompany(idMainCompany);
        model.addAttribute("urlId", id);
        model.addAttribute("plots", plots);
        return "viewPlotsofMainCompany";
    }
}
