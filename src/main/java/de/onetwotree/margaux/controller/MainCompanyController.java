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
    private MainCompanyService mainCompanyService;
    @Autowired
    private AlertService alertService;
    @Autowired
    private ResourceTypeService resourceTypeService;

    /**
     * Index of main Company. Pagination activated
     * @param model
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/")
    public String MainCompanyIndex(Model model, @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                   @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, "id"));
        Page<MainCompany> mainCompanyPage = mainCompanyService.findAllPaginated(pageable);
        model.addAttribute("mainCompanyPage", mainCompanyPage);
        return "MainCompany/mainCompany";
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
        MainCompany mainCompany = mainCompanyService.findOne(idMainCompany);
        if (mainCompany == null) throw new ItemNotFoundException(idMainCompany, "maincompany/");
        model.addAttribute("alertsMainCompany", alertService.findLast10ByMainEntityId(idMainCompany, AlertStatus.OPEN));
        model.addAttribute("urlId", id);
        model.addAttribute("maincompany", mainCompany);
        return "MainCompany/viewMainCompany";
    }

    /**
     * Add a MainCompany GET ACTION
     * @param model
     * @return
     */
    @GetMapping(value = "add/")
    public String addMainCompanyForm(Model model) {
        model.addAttribute("mainCompany", new MainCompany());
        return "MainCompany/editMainCompany";
    }

    /**
     * Add a mainCompany POST ACTION
     * @param mainCompany
     * @param result
     * @return
     */
    @PostMapping(value = "/add/")
    public String saveMainCompany(@ModelAttribute("mainCompany") @Valid MainCompany mainCompany,
                                       BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError error : errors) {
                redirectAttributes.addFlashAttribute("alert", "Error on " + error.getObjectName() + ". " + error.getDefaultMessage());
            }
            return "redirect:/maincompany/add/";
        }
        mainCompanyService.saveMainCompany(mainCompany);
        return "redirect:/maincompany/view/" + mainCompany.getId();
    }
    /**
     * Update a mainCompany GET ACTION
     * @param model
     * @param id
     * @return
     */
    @GetMapping(value = "/update/{id}")
    public String updateMainCompany(Model model,
                                    @PathVariable(value = "id") String id) throws ItemNotFoundException {
        Long idMainCompany = Long.valueOf(id);
        MainCompany mainCompany = mainCompanyService.findOne(idMainCompany);
        if (mainCompany == null) {
            throw new ItemNotFoundException(idMainCompany, "maincompany/");
        }
        model.addAttribute("mainCompany", mainCompany);
        return "MainCompany/updateMainCompany";
    }

    /**
     *
     * @param mainCompany
     * @param result
     * @param id
     * @param redirectAttributes
     * @return
     */
    @PostMapping(value = "/update/{id}")
    public String updateMainCompanySubmit(@Valid MainCompany mainCompany, BindingResult result,
                                    @PathVariable(value = "id") String id,
                                           RedirectAttributes redirectAttributes){
        if (result.hasErrors())
        {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError error : errors) {
                redirectAttributes.addFlashAttribute("alert", "Error on " + error.getObjectName() + ". " + error.getDefaultMessage());
            }
            return "redirect:/maincompany/update/" + id;
        }
        mainCompanyService.updateMainCompany(mainCompany);
        redirectAttributes.addFlashAttribute("info", "Company " + mainCompany.getName() + " updated !");
        return "redirect:/maincompany/view/"+ mainCompany.getId();
    }


    /**
     * View Companies of MainCompany
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "view/{id}/companies/")
    public String viewCompaniesOfMainCompany(@PathVariable(value = "id") String id, Model model,
                                             @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                             @RequestParam(name = "size", defaultValue = "10", required = false) Integer size){
        Long idMainCompany = Long.valueOf(id);
        Pageable pageable = new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, "id"));
        Page<Company> companyPage = mainCompanyService.findCompaniesPaginated(idMainCompany, pageable);
        model.addAttribute("urlId", id);
        model.addAttribute("companies", companyPage);
        return "MainCompany/viewCompanyofMainCompany";
    }

    /**
     * View Projects of Main Company
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "view/{id}/projects/")
    public String viewProjectsOfMainCompany(@PathVariable(value = "id") String id, Model model,
                                            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size){
        Long idMainCompany = Long.valueOf(id);
        Pageable pageable = new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, "id"));
        Page<Project> projectPage = mainCompanyService.findProjectsPaginated(idMainCompany, pageable);
        model.addAttribute("urlId", id);
        model.addAttribute("projects", projectPage);
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
        List<Plot> plotList = mainCompanyService.findPlots(idMainCompany);
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
    public String viewHarvestOfMainCompany(Model model,
                               @PathVariable(value = "id") String id,
                               @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                               @RequestParam(name = "size", defaultValue = "10", required = false) Integer size)
    {
        Long idMainCompany = Long.valueOf(id);
        Pageable pageRequest = new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, "id"));
        Page<Harvest> harvestPage = mainCompanyService.findHarvestsPaginated(idMainCompany, pageRequest);
        model.addAttribute("resourceTypeList", resourceTypeService.findAll());
        model.addAttribute("urlId", id);
        model.addAttribute("harvests", harvestPage);
        model.addAttribute("page", page);
        return "MainCompany/viewHarvestByMainCompany";
    }

    @GetMapping(value = "view/{idMainCompany}/harvests/{idResourceType}")
    public String viewHarvestsOfMainCompanyAjax(@PathVariable(value = "idMainCompany") String idMainCompany,
                                                @PathVariable(value = "idResourceType") String idResourceType, Model model){

        String graphHarvestsPlot = mainCompanyService.findHarvestsByResourcesForGraph(Long.valueOf(idMainCompany), Long.valueOf(idResourceType));
        model.addAttribute("myGraphData", graphHarvestsPlot);
        return "common/graphHarvest";

    }

    @GetMapping(value = "view/{idMainCompany}/alerts")
    public String viewAlertsOfProject(@PathVariable(value = "idMainCompany") MainCompany mainCompany, Model model) {
        model.addAttribute("alertItems", mainCompany.getAlerts());
        return "common/sheetAlert";
    }

}
