package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.application.StringToCompany;
import de.onetwotree.margaux.dao.PlotRepository;
import de.onetwotree.margaux.dao.ProjectRepository;
import de.onetwotree.margaux.dao.ResourceRepository;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.Project;
import de.onetwotree.margaux.entity.Resource;
import de.onetwotree.margaux.entity.User;
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
        System.out.println("Size:" + plot.getSize());
        System.out.println("Project:" + plot.getProject().getName());
        plotRepository.saveAndFlush(plot);
        return "redirect:/plot/add/";

    }
    @GetMapping(value = "/view/{id}/add-resource")
    public String addResourceToPlot(Model model, @PathVariable(value = "id") String id) {
        Resource acacia = resourceRepository.findOne(2L);
        Plot plot = plotRepository.findOne(Long.valueOf(id));
        plot.addResource(acacia, 20);
        System.out.println(acacia.toString());
        plotRepository.save(plot);
        return "yep";
    }
}
