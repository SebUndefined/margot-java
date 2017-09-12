package de.onetwotree.margaux.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.onetwotree.margaux.dao.*;
import de.onetwotree.margaux.entity.*;
import de.onetwotree.margaux.entityJson.PlotView;
import de.onetwotree.margaux.exception.ItemNotFoundException;
import de.onetwotree.margaux.service.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    /**
     * View All MainCompany
     * @param model
     * @return
     */
    @GetMapping(value = "/")
    public String MainCompanyIndex(Model model) {
        List<MainCompany> mainCompanies = mainCompanyRepository.findAll();
        model.addAttribute("MainCompanies", mainCompanies);
        return "MainCompany/mainCompany";
    }

    /**
     * Add a MainCompany GET ACTION
     * @param model
     * @return
     */
    @GetMapping(value = "add/")
    public String addMainCompanyForm(Model model) {
        model.addAttribute("MainCompany", new MainCompany());
        return "MainCompany/editMainCompany";
    }

    /**
     * Add a mainCompany POST ACTION
     * @param mainCompany
     * @param result
     * @return
     */
    @PostMapping(value = "add/")
    public String addMainCompanySubmit(@ModelAttribute("MainCompany") @Valid MainCompany mainCompany,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return "MainCompany/editMainCompany";
        }
        try {
            mainCompanyRepository.saveAndFlush(mainCompany);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return "redirect:/maincompany/";
    }
    /**
     * Update a mainCompany GET ACTION
     * @param mainCompany
     * @param model
     * @param id
     * @return
     */
    @GetMapping(value = "/update/{id}")
    public String updateMainCompany(@ModelAttribute("MainCompany") MainCompany mainCompany,
                                    Model model,
                                    @PathVariable(value = "id") String id) {
        model.addAttribute("mainCompany", mainCompanyRepository.findOne(Long.valueOf(id)));
        return "MainCompany/updateMainCompany";
    }
    @PostMapping(value = "/update/{id}")
    public String updateMainCompanySubmit(@ModelAttribute("MainCompany") MainCompany mainCompany,
                                    Model model,
                                    @PathVariable(value = "id") String id) throws ItemNotFoundException {
        mainCompany.setId(Long.valueOf(id));
        MainCompany mainCompanyOrigin = mainCompanyRepository.findOne(Long.valueOf(id));
        if (mainCompanyOrigin == null) {
            throw new ItemNotFoundException(Long.valueOf(id), "maincompany/");
        }

        try {
            mainCompanyRepository.saveAndFlush(mainCompany);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return "redirect:/maincompany/";
    }
    /**
     * View MainCompany
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "view/{id}")
    public String viewMainCompany(@PathVariable(value = "id") String id, Model model) throws ItemNotFoundException {
        Long idMainCompany = Long.valueOf(id);
        MainCompany mainCompany = mainCompanyRepository.findOne(idMainCompany);
        if (mainCompany == null) throw new ItemNotFoundException(idMainCompany, "maincompany/");
        model.addAttribute("urlId", id);
        model.addAttribute("maincompany", mainCompany);
        return "MainCompany/viewMainCompany";
    }

    /**
     * View Companies of MainCompany
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "view/{id}/companies/")
    public String viewCompaniesOfMainCompany(@PathVariable(value = "id") String id, Model model){
        Long idMainCompany = Long.valueOf(id);
        List<Company> companyList = companyRepository.findCompaniesByMainCompanyId(idMainCompany);
        model.addAttribute("urlId", id);
        model.addAttribute("companies", companyList);
        return "MainCompany/viewCompanyofMainCompany";
    }

    /**
     * View Projects of Main Company
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "view/{id}/projects/")
    public String viewProjectsOfMainCompany(@PathVariable(value = "id") String id, Model model){
        Long idMainCompany = Long.valueOf(id);
        List<Project> projects= projectRepository.findAllByMainCompanyId(idMainCompany);
        model.addAttribute("urlId", id);
        model.addAttribute("projects", projects);
        return "MainCompany/viewProjectsofMainCompany";
    }

    /**
     * View plots of MainCompany
     * @param id
     * @param model
     * @return
     */
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

    /**
     * View Harvest of main Company
     * @param id
     * @param graphType
     * @param model
     * @return
     */
    @GetMapping(value = "view/{id}/harvests/")
    public String viewHarvestsOfMainCompany(
            @PathVariable(value = "id") String id,
            @RequestParam(value = "graphType", required = false)String graphType,
            Model model) {
        model.addAttribute("urlId", id);
        String graphHarvestsPlot = harvestService.findAllHarvestWhereMainCompanyidAndResourceTypeIdGroupByYearAsJson(Long.valueOf(id), Long.valueOf(1));
        model.addAttribute("myGraphData", graphHarvestsPlot);
        return "viewHarvestByEntity";
    }

}
