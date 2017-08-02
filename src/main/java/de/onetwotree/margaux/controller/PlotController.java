package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.application.StringToCompany;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.Project;
import de.onetwotree.margaux.entity.User;
import de.onetwotree.margaux.service.PlotService;
import de.onetwotree.margaux.service.ProjectService;
import de.onetwotree.margaux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    ProjectService projectService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/")
    public String plotIndex(Model model) {
        List<Plot> plots = plotService.getAllPlot();
        model.addAttribute("plots", plots);
        return "plot";
    }

    @RequestMapping(value = "/add")
    public String addPlotForm(Model model) {
        Plot plot = new Plot();
        List<User> users = userService.getAllUsers();
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("plot", plot);
        model.addAttribute("projects", projects);
        model.addAttribute("users", users);
        return "editPlot";
    }

    @PostMapping(value="/add")
    public String addPlotSubmit(@ModelAttribute("Plot") Plot plot, BindingResult result) {
        System.out.println("Size:" + plot.getSize());
        System.out.println("Project:" + plot.getProject().getName());
        plotService.addPlot(plot);
        return "redirect:/plot/add/";

    }
}
