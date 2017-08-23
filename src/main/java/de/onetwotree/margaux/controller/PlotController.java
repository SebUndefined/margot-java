package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.application.StringToCompany;
import de.onetwotree.margaux.dao.PlotRepository;
import de.onetwotree.margaux.dao.PlotResourceRepository;
import de.onetwotree.margaux.dao.ProjectRepository;
import de.onetwotree.margaux.dao.ResourceRepository;
import de.onetwotree.margaux.entity.*;
import de.onetwotree.margaux.service.PlotService;
import de.onetwotree.margaux.service.ProjectService;
import de.onetwotree.margaux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    ProjectRepository projectRepository;
    @Autowired
    PlotResourceRepository plotResourceRepository;
    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/")
    public String plotIndex(Model model) {
        List<Plot> plots = plotRepository.findAll();
        model.addAttribute("plots", plots);
        return "Plot/plot";
    }

    @RequestMapping(value = "/view/{id}")
    public  String viewPlot(@PathVariable(value = "id") String id, Model model) {
        Long plotId = Long.valueOf(id);
        Plot plot = plotRepository.findOne(plotId);
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
    public String addResourceToPlot(Model model, @PathVariable(value = "id") String id) {
        List<Resource> resources = resourceRepository.findAll();
        PlotResource plotResource = new PlotResource();
        plotResource.setPlot(plotRepository.findOne(Long.valueOf(id)));
        model.addAttribute("resources", resources);
        model.addAttribute("plotResource", plotResource);

        return "Plot/addResourceToPlot";
    }
    @PostMapping(value="/view/{id}/add-resource")
    public String addResourceToPlotSubmit(@ModelAttribute("PlotResource") PlotResource plotResource, BindingResult result) {
        System.out.println("Submit");
        //plotResourceRepository.save(plotResource);
        return "redirect:/plot/";

    }

    /*Resource resource = resourceRepository.findOne(Long.valueOf(2));
        System.out.println("€€€€€€€€€€€€€€€€€€" + resource.getName());
    Plot plot = plotRepository.findOne(Long.valueOf(12));
        System.out.println("€€€€€€€€€€€€€€€€€€" + plot.getName());
    PlotResource plotResource = new PlotResource();
    PlotResourcePK plotResourcePK = new PlotResourcePK(plot.getId(), resource.getId());
        plotResource.setPlotResourcePK(plotResourcePK);
        plotResource.setResource(resource);
        plotResource.setPlot(plot);
        plotResource.setProportion(45);
        plot.getPlotResources().add(plotResource);
        resource.getPlotResources().add(plotResource);
        System.out.println(resource.getId().toString());
        plotResourceRepository.save(plotResource);
        return "yep";*/
}
