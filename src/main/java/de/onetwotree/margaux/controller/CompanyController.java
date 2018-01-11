package de.onetwotree.margaux.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.onetwotree.margaux.Enum.AlertStatus;
import de.onetwotree.margaux.dto.PlotMapDTO;
import de.onetwotree.margaux.entity.*;
import de.onetwotree.margaux.entityJson.PlotView;
import de.onetwotree.margaux.exception.ItemNotFoundException;
import de.onetwotree.margaux.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SebUndefined on 18/07/17.
 */
@Controller
@RequestMapping(value = "company")
public class CompanyController {

    private final CompanyService companyService;
    private final HoldingService holdingService;
    private final AlertService alertService;
    private final ResourceTypeService resourceTypeService;
    private final CountryService countryService;

    @Autowired
    public CompanyController(CompanyService companyService,
                             HoldingService holdingService,
                             AlertService alertService,
                             ResourceTypeService resourceTypeService,
                             CountryService countryService) {
        this.companyService = companyService;
        this.holdingService = holdingService;
        this.alertService = alertService;
        this.resourceTypeService = resourceTypeService;
        this.countryService = countryService;
    }

    /**
     * Show all company
     * @param model
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/")
    public String indexCompany(Model model,
                               @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                               @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
                               @RequestParam(name = "sort", defaultValue = "id", required = false) String sort,
                               @RequestParam(name = "direction", defaultValue = "DESC", required = false) Sort.Direction direction) {
        PageRequest pageRequest = new PageRequest(page - 1, size, new Sort(direction, sort));
        Page<Company> companyPage = companyService.findAllPaginated(pageRequest);
        model.addAttribute("companies", companyPage);
        return "Company/company";
    }

    /**
     * Show a company
     * @param id
     * @param model
     * @return
     * @throws ItemNotFoundException
     */
    @GetMapping(value = "view/{id}")
    public String viewCompany(@PathVariable(value = "id") String id, Model model) throws ItemNotFoundException {
        Long idCompany = Long.valueOf(id);
        Company company = companyService.findOne(idCompany);
        if (company == null) throw new ItemNotFoundException(idCompany, "company/");
        model.addAttribute("alertsCompany", alertService.findLast10ByMainEntityId(idCompany, AlertStatus.OPEN));
        model.addAttribute("urlId", id);
        model.addAttribute("company", company);
        return "Company/viewCompany";
    }

    /**
     * Add Company GET ACTION
     * @param model
     * @return
     */
    @GetMapping(value = "/add")
    public String addCompanyForm(Model model) {
        model.addAttribute("holdings", holdingService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("company", new Company());
        return "Company/editCompany";
    }

    /**
     * Add a company POST ACTION
     * @param company
     * @param result
     * @param redirectAttributes
     * @return
     */
    @PostMapping(value = "/add")
    public String addCompanySubmit(@ModelAttribute("Company") @Valid Company company,
                                   BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("alerts", result.getAllErrors());
            return "redirect:/company/add/";
        }
        company = companyService.saveCompany(company);
        if (company.getId() != null) {
            redirectAttributes.addFlashAttribute("info", "Company " + company.getName() + " has been saved !");
        }
        return "redirect:/company/view/" + company.getId();
    }

    /**
     * Show form for compan update
     * @param model
     * @param id
     * @return
     * @throws ItemNotFoundException
     */
    @GetMapping(value = "/update/{id}")
    public String updateCompany(Model model,
                                    @PathVariable(value = "id") String id) throws ItemNotFoundException {
        Company company = companyService.findOne(Long.valueOf(id));
        if (company == null){
            throw new ItemNotFoundException(Long.valueOf(id), "company/");
        }
        model.addAttribute("company", company);
        model.addAttribute("holdings", holdingService.findAll());
        return "Company/updateCompany";
    }

    /**
     * Submit form for update
     * @param company
     * @param result
     * @param id
     * @param redirectAttributes
     * @return
     * @throws ItemNotFoundException
     */
    @PostMapping(value = "/update/{id}")
    public String updateCompanySubmit(@Valid Company company, BindingResult result,
                                          @PathVariable(value = "id") String id,
                                      RedirectAttributes redirectAttributes) throws ItemNotFoundException {
        if (result.hasErrors())
        {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError error : errors) {
                redirectAttributes.addFlashAttribute("alert", "Error on " + error.getObjectName() + ". " + error.getDefaultMessage());
            }
            return "redirect:/company/update/" + id;
        }
        companyService.updateCompany(company);
        redirectAttributes.addFlashAttribute("info", "Company " + company.getName() + " has been updated !");
        return "redirect:/company/view/"+ company.getId();
    }

    @GetMapping(value = "/view/{id}/projects/")
    public String viewProjectsOfCompany(@PathVariable(value = "id") String id,
                                        Model model,
                                        @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                        @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
                                        @RequestParam(name = "sort", defaultValue = "id", required = false) String sort,
                                        @RequestParam(name = "direction", defaultValue = "DESC", required = false) Sort.Direction direction) {
        PageRequest pageRequest = new PageRequest(page - 1, size, new Sort(direction, sort));
        Page<Project> projectPage = companyService.findProjectsPaginated(Long.valueOf(id), pageRequest);
        model.addAttribute("projectPage", projectPage);
        model.addAttribute("urlId", id);
        return "Company/viewProjectsofCompany";
    }

    /**
     * View plots of Company
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "view/{id}/plots/")
    public String viewPlotsOfCompany(@PathVariable(value = "id") String id, Model model) {
        Long idCompany = Long.valueOf(id);
        List<Plot> plotList = companyService.findPlots(idCompany);
        ObjectMapper mapper = new ObjectMapper();
        String result = null;

        List<PlotMapDTO> plotMapDTOList = new ArrayList<>();
        for (Plot plot : plotList) {
            PlotMapDTO plotMapDTO = new PlotMapDTO(plot.getProject().getId(),
                    plot.getId(),
                    plot.getLatitude(),
                    plot.getLongitude(),
                    plot.getName(),
                    plot.getSize());
            plotMapDTOList.add(plotMapDTO);
        }
        try {
            result = mapper.writeValueAsString(plotMapDTOList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        model.addAttribute("urlId", id);
        model.addAttribute("plots", result);
        return "Company/viewPlotsofCompany";
    }

    /**
     * View harvests of a particular company
     * @param model
     * @param idMainCompany
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "view/{id}/harvests/")
    public String viewHarvestsOfCompany(Model model,
                                        @PathVariable(value = "id") String idMainCompany,
                                        @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                        @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
                                        @RequestParam(name = "sort", defaultValue = "id", required = false) String sort,
                                        @RequestParam(name = "direction", defaultValue = "DESC", required = false) Sort.Direction direction)
    {
        Pageable pageRequest = new PageRequest(page - 1, size, new Sort(direction, sort));
        Page<Harvest> harvestPage = companyService.findHarvestsPaginated(Long.valueOf(idMainCompany), pageRequest);
        model.addAttribute("resourceTypeList", resourceTypeService.findAll());
        model.addAttribute("urlId", idMainCompany);
        model.addAttribute("harvests", harvestPage);
        model.addAttribute("page", page);
        return "Company/viewHarvestByCompany";
    }

    /**
     *
     * @param idCompany
     * @param idResourceType
     * @param model
     * @return
     */
    @GetMapping(value = "view/{idCompany}/harvests/{idResourceType}")
    public String viewHarvestsOfMainCompanyAjax(@PathVariable(value = "idCompany") String idCompany,
                                                @PathVariable(value = "idResourceType") String idResourceType, Model model){

        String graphHarvestsPlot = companyService.findHarvestsByResourcesForGraph(Long.valueOf(idCompany), Long.valueOf(idResourceType));
        model.addAttribute("myGraphData", graphHarvestsPlot);
        return "common/graphHarvest";

    }


    /**
     * Showing Alerts for a company
     * @param company
     * @param model
     * @return
     */
    @GetMapping(value = "view/{idCompany}/alerts")
    public String viewAlertsOfCompany(@PathVariable(value = "idCompany") Company company, Model model) {
        model.addAttribute("alertItems", company.getAlerts());
        return "common/sheetAlert";
    }

}
