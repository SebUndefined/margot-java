package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.dao.*;
import de.onetwotree.margaux.entity.*;
import de.onetwotree.margaux.exception.ItemNotFoundException;
import de.onetwotree.margaux.exception.PlotResourceException;
import de.onetwotree.margaux.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by SebUndefined on 31/07/17.
 */
@Controller
@RequestMapping(value = "plot")
public class PlotController {

    @Autowired
    PlotService plotService;
    @Autowired
    PlotRepository plotRepository;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    HarvestRepository harvestRepository;
    @Autowired
    ResourceTypeRepository resourceTypeRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    PlotResourceService plotResourceService;
    @Autowired
    HarvestService harvestService;

    @RequestMapping(value = "/")
    public String plotIndex(Model model,
                            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size)
    {
        PageRequest pageRequest = new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, "id"));
        Page<Plot> plotPage = plotRepository.findAll(pageRequest);
        model.addAttribute("plots", plotPage);
        model.addAttribute("page", page);
        return "Plot/plot";
    }
    @RequestMapping(value = "/map/")
    public String plotIndexMap(Model model)
    {
        List<Plot> plots = plotRepository.findAll();
        model.addAttribute("plots", plots);
        return "Plot/plotMap";
    }

    @RequestMapping(value = "/view/{id}")
    public  String viewPlot(@PathVariable(value = "id") String id, Model model) throws ItemNotFoundException {
        Long plotId = Long.valueOf(id);

        Plot plot = plotRepository.findOne(plotId);
        if (plot == null) throw new ItemNotFoundException(plotId, "plot/");
        List<PlotResource> resourcePlotList = plot.getPlotResources();
        String myGraphData = plotResourceService.getPlotResourceAsJson(resourcePlotList);
        model.addAttribute("graphResource", myGraphData);
        model.addAttribute("urlId", id);
        model.addAttribute("plot", plot);
        return "Plot/viewPlot";
    }

    @RequestMapping(value = "/add")
    public String addPlotForm(Model model) {
        Plot plot = new Plot();
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("plot", plot);
        model.addAttribute("projects", projects);
        return "Plot/editPlot";
    }
    @PostMapping(value="/add")
    public String addPlotSubmit(@ModelAttribute("Plot") Plot plot, BindingResult result) {
        plotRepository.saveAndFlush(plot);
        return "redirect:/plot/add/";

    }
    @GetMapping(value = "/view/{id}/add-resource")
    public String addResourceToPlotForm(Model model, @PathVariable(value = "id") String id) {
        List<Resource> resources = resourceRepository.findAll();
        PlotResource plotResource = new PlotResource();
        plotResource.setPlot(plotRepository.findOne(Long.valueOf(id)));
        model.addAttribute("resources", resources);
        model.addAttribute("plotResource", plotResource);

        return "Plot/addResourceToPlot";
    }
    @PostMapping(value="/view/{id}/add-resource")
    public String addResourceToPlotSubmit(RedirectAttributes redirectAttributes,
                                          @PathVariable(value = "id") String id,
                                          @ModelAttribute("PlotResource") PlotResource plotResource,
                                          BindingResult result) {
        Boolean isExist = plotService.addResourceToPlot(Long.valueOf(id), plotResource);
        if (!isExist) {
            throw new PlotResourceException("Error: You cannot add this resource to this plot. " +
                    "Already here, or the size of the plot is not big enough", id);
        }
        else {
            String message = "Resource " + plotResource.getResource().getName()
                    + " has been added to plot.";
            redirectAttributes.addFlashAttribute("info", message);
        }
        String url = "redirect:/plot/view/" + id + "/";
        return url;

    }

    @GetMapping(value = "view/{id}/harvests/")
    public String viewHarvestsOfPlot(Model model,
                                     @PathVariable(value = "id") String idPlot,
                                     @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                     @RequestParam(name = "size", defaultValue = "10", required = false) Integer size)
    {
        Pageable pageRequest = new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, "id"));
        Page<Harvest> harvestPage = harvestRepository.findAllByPlotId(Long.valueOf(idPlot), pageRequest);
        model.addAttribute("resourceTypeList", resourceTypeRepository.findAll());
        model.addAttribute("urlId", idPlot);
        model.addAttribute("harvests", harvestPage);
        model.addAttribute("page", page);
        return "Plot/viewHarvestByPlot";
    }
    @GetMapping(value = "view/{idPlot}/harvests/{idResourceType}")
    public String viewHarvestsOfProjectAjax(@PathVariable(value = "idPlot") String idPlot,
                                                @PathVariable(value = "idResourceType") String idResourceType, Model model){

        String graphHarvestsPlot = harvestService
                .findAllHarvestWherePlotIdAndResourceTypeIdGroupByYearAsJson(Long.valueOf(idPlot)
                        , Long.valueOf(idResourceType));
        model.addAttribute("myGraphData", graphHarvestsPlot);
        return "common/graphHarvest";

    }
}
