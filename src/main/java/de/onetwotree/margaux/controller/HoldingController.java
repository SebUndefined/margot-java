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
@RequestMapping(value = "holding")
public class HoldingController {

    private final HoldingService holdingService;
    private final AlertService alertService;
    private final ResourceTypeService resourceTypeService;
    private final CountryService countryService;

    @Autowired
    public HoldingController(HoldingService holdingService, AlertService alertService, ResourceTypeService resourceTypeService, CountryService countryService) {
        this.holdingService = holdingService;
        this.alertService = alertService;
        this.resourceTypeService = resourceTypeService;
        this.countryService = countryService;
    }

    /**
     * Index of main Company. Pagination activated
     * @param model
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/")
    public String holdingIndex(Model model, @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                   @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, "id"));
        Page<Holding> holdingPage = holdingService.findAllPaginated(pageable);
        model.addAttribute("holdingPage", holdingPage);
        return "Holding/holding";
    }

    /**
     * View Holding
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "view/{id}")
    public String viewHolding(@PathVariable(value = "id") String id, Model model) throws ItemNotFoundException {
        Long idMainCompany = Long.valueOf(id);
        Holding holding = holdingService.findOne(idMainCompany);
        if (holding == null) throw new ItemNotFoundException(idMainCompany, "holding/");
        model.addAttribute("alertsHolding", alertService.findLast10ByMainEntityId(idMainCompany, AlertStatus.OPEN));
        model.addAttribute("urlId", id);
        model.addAttribute("holding", holding);
        return "Holding/viewHolding";
    }

    /**
     * Add a Holding GET ACTION
     * @param model
     * @return
     */
    @GetMapping(value = "add/")
    public String addHoldingForm(Model model) {
        model.addAttribute("holding", new Holding());
        model.addAttribute("countries", countryService.findAll());
        return "Holding/editHolding";
    }

    /**
     * Add a holding POST ACTION
     * @param holding
     * @param result
     * @return
     */
    @PostMapping(value = "/add/")
    public String saveHolding(@ModelAttribute("holding") @Valid Holding holding,
                                       BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("alerts", result.getAllErrors());
            return "redirect:/holding/add/";
        }
        holding = holdingService.saveHolding(holding);
        if (holding.getId()!= null) {
            redirectAttributes.addFlashAttribute("info", "Holding " + holding.getName() + " has been saved !");
        }
        return "redirect:/holding/view/" + holding.getId();
    }
    /**
     * Update a holding GET ACTION
     * @param model
     * @param id
     * @return
     */
    @GetMapping(value = "/update/{id}")
    public String updateHolding(Model model,
                                    @PathVariable(value = "id") String id) throws ItemNotFoundException {
        Long holdingId = Long.valueOf(id);
        Holding holding = holdingService.findOne(holdingId);
        if (holding == null) {
            throw new ItemNotFoundException(holdingId, "holding/");
        }
        model.addAttribute("holding", holding);
        return "Holding/updateHolding";
    }

    /**
     *
     * @param holding
     * @param result
     * @param id
     * @param redirectAttributes
     * @return
     */
    @PostMapping(value = "/update/{id}")
    public String updateHoldingSubmit(@Valid Holding holding, BindingResult result,
                                          @PathVariable(value = "id") String id,
                                          RedirectAttributes redirectAttributes){
        if (result.hasErrors())
        {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError error : errors) {
                redirectAttributes.addFlashAttribute("alert", "Error on " + error.getObjectName() + ". " + error.getDefaultMessage());
            }
            return "redirect:/holding/update/" + id;
        }
        holdingService.updateHolding(holding);
        redirectAttributes.addFlashAttribute("info", "Holding " + holding.getName() + " updated !");
        return "redirect:/holding/view/"+ holding.getId();
    }


    /**
     * View Companies of Holding
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "view/{id}/companies/")
    public String viewCompaniesOfHolding(@PathVariable(value = "id") String id, Model model,
                                             @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                             @RequestParam(name = "size", defaultValue = "10", required = false) Integer size){
        Long holdingId = Long.valueOf(id);
        Pageable pageable = new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, "id"));
        Page<Company> companyPage = holdingService.findCompaniesPaginated(holdingId, pageable);
        model.addAttribute("urlId", id);
        model.addAttribute("companies", companyPage);
        return "Holding/viewCompanyofHolding";
    }

    /**
     * View Projects of Main Company
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "view/{id}/projects/")
    public String viewProjectsOfHolding(@PathVariable(value = "id") String id, Model model,
                                            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size){
        Long holdingId = Long.valueOf(id);
        Pageable pageable = new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, "id"));
        Page<Project> projectPage = holdingService.findProjectsPaginated(holdingId, pageable);
        model.addAttribute("urlId", id);
        model.addAttribute("projects", projectPage);
        return "Holding/viewProjectsofHolding";
    }

    /**
     * View plots of Holding
     * @param holding
     * @param model
     * @return
     */
    @GetMapping(value = "view/{id}/plots/")
    public String viewPlotsOfHolding(@PathVariable(value = "id") Holding holding, Model model) {
        List<Plot> plotList = holdingService.findPlots(holding.getId());
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
        model.addAttribute("urlId", holding.getId());
        model.addAttribute("plots", result);
        return "Holding/viewPlotsofHolding";
    }


    /**
     *
     * @param model
     * @param id
     * @param page
     * @param size
     * @param sort
     * @param direction
     * @return
     */
    @RequestMapping(value = "view/{id}/harvests/")
    public String viewHarvestOfHolding(Model model,
                                       @PathVariable(value = "id") String id,
                                       @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                       @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
                                       @RequestParam(name = "sort", defaultValue = "id", required = false) String sort,
                                       @RequestParam(name = "direction", defaultValue = "DESC", required = false) Sort.Direction direction)
    {
        Long holdingId = Long.valueOf(id);
        PageRequest pageRequest = new PageRequest(page - 1, size, new Sort(direction, sort));
        Page<Harvest> harvestPage = holdingService.findHarvestsPaginated(holdingId, pageRequest);
        model.addAttribute("resourceTypeList", resourceTypeService.findAll());
        model.addAttribute("urlId", id);
        model.addAttribute("harvests", harvestPage);
        model.addAttribute("page", page);
        return "Holding/viewHarvestByHolding";
    }

    @GetMapping(value = "view/{id}/harvests/{idResourceType}")
    public String viewHarvestsOfHoldingAjax(@PathVariable(value = "id") String id,
                                                @PathVariable(value = "idResourceType") String idResourceType, Model model){

        String graphHarvestsPlot = holdingService.findHarvestsByResourcesForGraph(Long.valueOf(id), Long.valueOf(idResourceType));
        model.addAttribute("myGraphData", graphHarvestsPlot);
        return "common/graphHarvest";

    }

    @GetMapping(value = "view/{id}/alerts")
    public String viewAlertsOfProject(@PathVariable(value = "id") Holding holding, Model model) {
        model.addAttribute("alertItems", holding.getAlerts());
        return "common/sheetAlert";
    }

}
