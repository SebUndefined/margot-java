package de.onetwotree.margaux.controller;

import com.fasterxml.classmate.Annotations;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.onetwotree.margaux.Utils.MargauxException;
import de.onetwotree.margaux.chartData.json.*;
import de.onetwotree.margaux.dao.*;
import de.onetwotree.margaux.entity.*;
import de.onetwotree.margaux.entityJson.PlotView;
import de.onetwotree.margaux.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by SebUndefined on 18/07/17.
 */
@Controller
@RequestMapping(value = "maincompany")
public class MainCompanyController {


    @Autowired
    private MainCompanyRepository mainCompanyRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PlotRepository plotRepository;
    @Autowired
    private HarvestService harvestService;
    @Autowired
    private ResourceService resourceService;

    @GetMapping(value = "/")
    public String MainCompanyIndex(Model model) {
        List<MainCompany> mainCompanies = mainCompanyRepository.findAll();
        model.addAttribute("MainCompanies", mainCompanies);
        return "MainCompany/mainCompany";
    }
    @GetMapping(value = "add/")
    public String addMainCompanyForm(Model model) {
        model.addAttribute("MainCompany", new MainCompany());
        return "MainCompany/editMainCompany";
    }
    @PostMapping(value = "add/")
    public String addMainCompanySubmit(RedirectAttributes redirectAttributes,
                                       @ModelAttribute("MainCompany") MainCompany mainCompany,
                                       BindingResult result) {
        MainCompany mainCompanySaved = mainCompanyRepository.saveAndFlush(mainCompany);
        if (mainCompanySaved != null) {
            String message = "The MainCompany " + mainCompanySaved.getName()
                    + "</br> has been saved";
            redirectAttributes.addFlashAttribute("info", message);
        }
        return "redirect:/maincompany/";
    }
    @GetMapping(value = "view/{id}")
    public String viewMainCompany(@PathVariable(value = "id") String id, Model model){
        Long idMainCompany = Long.valueOf(id);
        MainCompany mainCompany1 = mainCompanyRepository.findOne(idMainCompany);
        model.addAttribute("urlId", id);
        model.addAttribute("maincompany", mainCompany1);
        return "MainCompany/viewMainCompany";
    }
    @GetMapping(value = "view/{id}/companies/")
    public String viewCompaniesOfMainCompany(@PathVariable(value = "id") String id, Model model){
        Long idMainCompany = Long.valueOf(id);
        List<Company> companyList = companyRepository.findCompaniesByMainCompanyId(idMainCompany);
        model.addAttribute("urlId", id);
        model.addAttribute("companies", companyList);
        return "MainCompany/viewCompanyofMainCompany";
    }
    @GetMapping(value = "view/{id}/projects/")
    public String viewProjectsOfMainCompany(@PathVariable(value = "id") String id, Model model){
        Long idMainCompany = Long.valueOf(id);
        //List<Project> projects= projectService.getAllProjectsForMainCompany(idMainCompany);
        List<Project> projects= projectRepository.findAllByMainCompanyId(idMainCompany);
        model.addAttribute("urlId", id);
        model.addAttribute("projects", projects);
        return "MainCompany/viewProjectsofMainCompany";
    }
    @GetMapping(value = "view/{id}/plots/")
    public String viewPlotsOfMainCompany(@PathVariable(value = "id") String id, Model model) {
        Long idMainCompany = Long.valueOf(id);
        List<Plot> plotList = plotRepository.findAllByMainCompanyId(idMainCompany);
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writerWithView(PlotView.PlotWithUserAndCompany.class).writeValueAsString(plotList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        model.addAttribute("urlId", id);
        model.addAttribute("plots", result);
        return "MainCompany/viewPlotsofMainCompany";
    }
    @GetMapping(value = "view/{id}/harvests/")
    public String viewHarvestsOfMainCompany(
            @PathVariable(value = "id") String id,
            @RequestParam(value = "typeOrNull", required = false)String typeOrNull,
            Model model) {
        model.addAttribute("urlId", id);
        long idMainCompany = Long.parseLong(id);
        if (typeOrNull !=null && !typeOrNull.isEmpty()) {
            if (typeOrNull.equals("date")) {
               //resourceService.getAllResourceByMainCompanyWithHarvestPlotLy(idMainCompany);
                String resources= resourceService.getAllResourceByMainCompanyByResIdWithHarvestPlotLy(idMainCompany, (long)1);
                model.addAttribute("myGraphDataWood", resources);
            }
        }
        else {
            String myGraphDataWood = harvestService.getSumHarvestByMCompanyByResourceJson(idMainCompany, (long) 1);
            String myGraphDataCocoa = harvestService.getSumHarvestByMCompanyByResourceJson(idMainCompany, (long) 2);
            model.addAttribute("myGraphDataCocoa", myGraphDataCocoa);
            model.addAttribute("myGraphDataWood", myGraphDataWood);
        }
        return "viewHarvestByEntity";
    }
    public String viewHarvestsOfMainCompanyInLineChart(Long id, Model model) {
        System.out.println(id);
        model.addAttribute("urlId", id);
        model.addAttribute("myGraphData", "prout");
        return "viewHarvestByEntity";
    }
}
