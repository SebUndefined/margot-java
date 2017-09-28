package de.onetwotree.margaux.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.onetwotree.margaux.Enum.AlertStatus;
import de.onetwotree.margaux.dao.*;
import de.onetwotree.margaux.entity.*;
import de.onetwotree.margaux.entityJson.PlotView;
import de.onetwotree.margaux.exception.ItemNotFoundException;
import de.onetwotree.margaux.service.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private HarvestRepository harvestRepository;
    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private HarvestService harvestService;
    @Autowired
    private ResourceTypeRepository resourceTypeRepository;

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
                                       BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError error : errors) {
                redirectAttributes.addFlashAttribute("alert", "Error on " + error.getObjectName() + ". " + error.getDefaultMessage());
            }
            return "redirect:/maincompany/add/";
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
    public String updateMainCompanySubmit(@Valid MainCompany mainCompany, BindingResult result,
                                    @PathVariable(value = "id") String id,
                                           RedirectAttributes redirectAttributes) throws ItemNotFoundException {
        System.out.println(mainCompany.getName());
        if (result.hasErrors())
        {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError error : errors) {
                redirectAttributes.addFlashAttribute("alert", "Error on " + error.getObjectName() + ". " + error.getDefaultMessage());
            }
            return "redirect:/maincompany/update/" + id;
        }
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
        model.addAttribute("alertsMainCompany", alertRepository.findFirst10ByMainEntityIdAndStatusOrderByDateDesc(idMainCompany, AlertStatus.OPEN));
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


    @RequestMapping(value = "view/{id}/harvests/")
    public String harvestIndex(Model model,
                               @PathVariable(value = "id") String idMainCompany,
                               @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                               @RequestParam(name = "size", defaultValue = "10", required = false) Integer size)
    {
        Pageable pageRequest = new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, "id"));
        Page<Harvest> harvestPage = harvestRepository.findAllByMainCompanyId(Long.valueOf(idMainCompany), pageRequest);
        model.addAttribute("resourceTypeList", resourceTypeRepository.findAll());
        model.addAttribute("urlId", idMainCompany);
        model.addAttribute("harvests", harvestPage);
        model.addAttribute("page", page);
        return "MainCompany/viewHarvestByMainCompany";
    }

    @GetMapping(value = "view/{idMainCompany}/harvests/{idResourceType}")
    public String viewHarvestsOfMainCompanyAjax(@PathVariable(value = "idMainCompany") String idMainCompany,
                                                @PathVariable(value = "idResourceType") String idResourceType, Model model){

        String graphHarvestsPlot = harvestService
                .findAllHarvestWhereMainCompanyidAndResourceTypeIdGroupByYearAsJson(Long.valueOf(idMainCompany)
                        , Long.valueOf(idResourceType));
        model.addAttribute("myGraphData", graphHarvestsPlot);
        return "common/graphHarvest";

    }


}
