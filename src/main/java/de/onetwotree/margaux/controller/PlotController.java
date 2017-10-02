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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
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
    public String addPlotSubmit(@Valid @ModelAttribute("Plot") Plot plot, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError error : errors) {
                redirectAttributes.addFlashAttribute("alert", "Error on " + error.getObjectName() + ". " + error.getDefaultMessage());
            }
            return "redirect:/plot/add/";
        }
        try {
            plotRepository.saveAndFlush(plot);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return "redirect:/project/";

    }

    @GetMapping(value = "update/{plotId}")
    public String updatePlot(@ModelAttribute("plot") Plot plot,
                             Model model,
                             @PathVariable(value = "plotId") String idPlot) throws ItemNotFoundException {
        plot = plotRepository.findOne(Long.valueOf(idPlot));
        if (plot == null) throw new ItemNotFoundException(Long.valueOf(idPlot), "plot/");
        model.addAttribute("plot", plot);
        model.addAttribute("projects", projectRepository.findAll());
        return "Plot/updatePlot";

    }
    @PostMapping(value = "/update/{id}")
    public String updatePlotSubmit(@Valid @ModelAttribute("plot") Plot plot, BindingResult result,
                                      @PathVariable(value = "id") String id,
                                      RedirectAttributes redirectAttributes) throws ItemNotFoundException {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError error : errors) {
                redirectAttributes.addFlashAttribute("alert", "Error on " + error.getObjectName() + ". " + error.getDefaultMessage());
            }
            return "redirect:/plot/update/" + id;
        }
        Plot plotOrigin = plotRepository.findOne(Long.valueOf(id));
        if (plotOrigin == null) {
            throw new ItemNotFoundException(Long.valueOf(id), "plot/");
        }
        plotService.updatePlot(plot, plotOrigin);
        redirectAttributes.addFlashAttribute("info", "Plot " + plot.getName() + " has been updated !");
        return "redirect:/plot/view/" + id;
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

    @GetMapping(value = "view/{idPlot}/add-harvest")
    public String addHarvestToPlot(@PathVariable(value = "idPlot") String idPlot,
                                   Model model) throws ItemNotFoundException {
        Long plotId = Long.valueOf(idPlot);
        Plot plot = plotRepository.findOne(plotId);
        if (plot == null) throw new ItemNotFoundException(plotId, "plot/");
        Harvest harvest = new Harvest();
        harvest.setPlot(plot);
        List<Resource> resourceAvailables = new ArrayList<>();
        for (PlotResource plotResource : plot.getPlotResources()) {
            resourceAvailables.add(plotResource.getResource());
        }
        model.addAttribute("harvest", harvest);
        model.addAttribute("resources", resourceAvailables);
        return "Plot/addHarvestToPlot";
    }
    @PostMapping(value = "view/{idPlot}/add-harvest")
    public String addHarvestToPlotSubmit(RedirectAttributes redirectAttributes,
                                         @ModelAttribute("Harvest") @Valid Harvest harvest,
                                         BindingResult result,
                                         @PathVariable("idPlot") String idPlot) throws ItemNotFoundException {
        Long plotId = Long.valueOf(idPlot);
        Plot plot = plotRepository.findOne(plotId);
        if (plot == null) throw new ItemNotFoundException(plotId, "plot/");
        String message;
        String url = "";
        if (!result.hasErrors()) {
            harvest.setPlot(plot);
            Harvest harvestSaved = harvestService.addHarvest(harvest);
            if (harvestSaved != null) {
                message = "The Harvest has been saved !";
                redirectAttributes.addFlashAttribute("info", message);
                url = "redirect:/plot/view/"+ harvest.getPlot().getId() +"/harvests/";
            }

        } else {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError error : errors) {
                redirectAttributes.addFlashAttribute("alert", "Error on " + error.getObjectName() + ". " + error.getDefaultMessage());
            }
            return "redirect:/plot/view/" + idPlot + "/add-harvest/";

        }
        return url;
    }
}
