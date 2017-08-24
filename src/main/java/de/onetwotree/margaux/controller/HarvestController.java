package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.dao.HarvestRepository;
import de.onetwotree.margaux.dao.PlotRepository;
import de.onetwotree.margaux.dao.ResourceRepository;
import de.onetwotree.margaux.entity.Harvest;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.Resource;
import de.onetwotree.margaux.service.HarvestService;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by SebUndefined on 02/08/17.
 */
@Controller
@RequestMapping(value = "harvest")
public class HarvestController {
    @Autowired
    HarvestRepository harvestRepository;
    @Autowired
    HarvestService harvestService;
    @Autowired
    PlotRepository plotRepository;
    @Autowired
    ResourceRepository resourceRepository;

    @RequestMapping(value = "/")
    public String harvestIndex(Model model,
                            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size)
    {
        PageRequest pageRequest = new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, "id"));
        Page<Harvest> harvestPage = harvestRepository.findAll(pageRequest);
        model.addAttribute("harvests", harvestPage);
        model.addAttribute("page", page);
        return "Harvest/harvest";
    }

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
    public String addPlotSubmit(RedirectAttributes redirectAttributes,
                                @ModelAttribute("Harvest") Harvest harvest,
                                BindingResult result) {
        Boolean isExist = harvestService.addHarvest(harvest);

        System.out.println(isExist);

        return "Harvest/editHarvest";

    }
}
