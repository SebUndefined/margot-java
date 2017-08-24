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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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

    /*@RequestMapping(value = "/")
    public String plotIndex(Model model) {
        List<Plot> plots = plotRepository.findAll();
        model.addAttribute("plots", plots);
        return "Plot/plot";
    }*/
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
        System.out.println(isExist);
        if (!isExist) {
            String message = "This plot has already the " + plotResource.getResource().getName()
                    + "resource.</br> Please use the update function.";
            redirectAttributes.addFlashAttribute("alert", message);
        }
        String url = "redirect:/plot/view/" + id + "/";
        return url;

    }

}
