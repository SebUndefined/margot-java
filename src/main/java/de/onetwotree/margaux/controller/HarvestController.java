package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.dao.HarvestRepository;
import de.onetwotree.margaux.dao.PlotRepository;
import de.onetwotree.margaux.dao.ResourceRepository;
import de.onetwotree.margaux.dao.ResourceTypeRepository;
import de.onetwotree.margaux.entity.Harvest;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.Resource;
import de.onetwotree.margaux.entity.ResourceType;
import de.onetwotree.margaux.service.HarvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by SebUndefined on 02/08/17.
 */
@Controller
@RequestMapping(value = "harvest")
public class HarvestController {
    private final HarvestRepository harvestRepository;
    private final HarvestService harvestService;
    private final PlotRepository plotRepository;
    private final ResourceRepository resourceRepository;

    @Autowired
    public HarvestController(HarvestRepository harvestRepository, HarvestService harvestService, PlotRepository plotRepository, ResourceRepository resourceRepository) {
        this.harvestRepository = harvestRepository;
        this.harvestService = harvestService;
        this.plotRepository = plotRepository;
        this.resourceRepository = resourceRepository;
    }

    @RequestMapping(value = "/")
    public String harvestIndex(Model model,
                               @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                               @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
                               @RequestParam(name = "sort", defaultValue = "id", required = false) String sort,
                               @RequestParam(name = "direction", defaultValue = "DESC", required = false) Sort.Direction direction)
    {
        PageRequest pageRequest = new PageRequest(page - 1, size, new Sort(direction, sort));
        Page<Harvest> harvestPage = harvestRepository.findAll(pageRequest);
        model.addAttribute("harvests", harvestPage);
        model.addAttribute("page", page);
        return "Harvest/harvest";
    }
    @GetMapping(value = "/graph-total/")
    public String harvestIndexGraphTotal(Model model) {
        String myGraphDataAlimentary = harvestService.getSumByResourceWhereResourceTypeId(2L);
        String myGraphDataWood = harvestService.getSumByResourceWhereResourceTypeId(1L);
        model.addAttribute("myGraphDataWood", myGraphDataWood);
        model.addAttribute("myGraphDataAlimentary", myGraphDataAlimentary);

        return "Harvest/harvestTotal";
    }

    /**
     *
     * @param model
     * @param resourceType
     * @return
     */
    @GetMapping(value = "/graph-timeline/")
    public String harvestIndexTimeLine(Model model,
                                       @RequestParam(name = "resourcetype", defaultValue = "wood", required = false) String resourceType) {
        Long resourceTypeId;
        if (resourceType.equals("wood")) {
            resourceTypeId = Long.valueOf(1);
        }
        else if (resourceType.equals("alimentary")){
            resourceTypeId = Long.valueOf(2);
        }
        else {
            resourceTypeId = Long.valueOf(1);
        }
        String allGroupByResourceType = harvestService.findAllGroupByResourceTypeJson(resourceTypeId);
        model.addAttribute("myGraphData", allGroupByResourceType);
        return "Harvest/harvestTimeline";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add")
    public String addPlotForm(Model model) {
        Harvest harvest = new Harvest();
        List<Plot> plots = plotRepository.findAll();
        List<Resource> resources = resourceRepository.findAll();
        model.addAttribute("harvest", harvest);
        model.addAttribute("plots", plots);
        model.addAttribute("resources", resources);
        return "Harvest/editHarvest";
    }

    @PostMapping(value="/add")
    public String addHarvestSubmit(RedirectAttributes redirectAttributes,
                                @ModelAttribute("Harvest") Harvest harvest,
                                BindingResult result) {
        Harvest harvestSaved = harvestService.addHarvest(harvest);
        String url = "";
        String message = "";
        if (harvestSaved != null) {
            message = "The Harvest has been saved !";
            redirectAttributes.addFlashAttribute("info", message);
            url = "redirect:/harvest/";
        }
        return url;
    }
}
