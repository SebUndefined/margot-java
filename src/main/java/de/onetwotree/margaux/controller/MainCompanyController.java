package de.onetwotree.margaux.controller;

import com.fasterxml.classmate.Annotations;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.onetwotree.margaux.Utils.MargauxException;
import de.onetwotree.margaux.chartData.json.*;
import de.onetwotree.margaux.dao.MainCompanyDAO;
import de.onetwotree.margaux.dao.UserDao;
import de.onetwotree.margaux.entity.Company;
import de.onetwotree.margaux.entity.MainCompany;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.Project;
import de.onetwotree.margaux.entityJson.PlotView;
import de.onetwotree.margaux.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    private MainCompanyService mainCompanyService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PlotService plotService;
    @Autowired
    private HarvestService harvestService;

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
    public String viewPlotsOfMainCompany(@PathVariable(value = "id") String id, Model model) {
        Long idMainCompany = Long.valueOf(id);
        List<Plot> plots = plotService.getAllPlotForMainCompany(idMainCompany);
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writerWithView(PlotView.PlotWithUserAndCompany.class).writeValueAsString(plots);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        model.addAttribute("urlId", id);
        model.addAttribute("plots", result);
        return "viewPlotsofMainCompany";
    }
    @GetMapping(value = "{id}/harvests/")
    public String viewHarvestsOfMainCompany(
            @PathVariable(value = "id") String id,
            @RequestParam(value = "typeOrNull", required = false)String typeOrNull,
            Model model) {
        Long idMainCompany = Long.valueOf(id);
        String param = typeOrNull;
        if (param !=null && !param.isEmpty()) {
            if (typeOrNull.equals("date")) {
                viewHarvestsOfMainCompanyInLineChart(idMainCompany, model);
            }
        }
        else {
            List<Object[]> results = harvestService.getAllHarvestByMainCompany(idMainCompany, (long) 1);
            List<String> x = new ArrayList<String>();
            List<BigDecimal> y = new ArrayList<BigDecimal>();
            for (Object[] row: results) {
                x.add(row[1].toString());
                y.add((BigDecimal) row[2]);
            }
            Datum datum = new Datum(x, y, "bar");
            List<Datum> data = new ArrayList<>();
            data.add(datum);
            PlotLy plotLyChart = new PlotLy(data);
            ObjectMapper mapper = new ObjectMapper();
            String myGraphData = "";
            try {
                myGraphData = mapper.writeValueAsString(plotLyChart);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            model.addAttribute("urlId", id);
            model.addAttribute("myGraphData", myGraphData);
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
