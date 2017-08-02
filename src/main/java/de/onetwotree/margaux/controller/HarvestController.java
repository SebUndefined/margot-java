package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.entity.Harvest;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.service.HarvestService;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by SebUndefined on 02/08/17.
 */
@Controller
@RequestMapping(value = "harvest")
public class HarvestController {
    @Autowired
    HarvestService harvestService;

    @RequestMapping(value = "/")
    public String harvestIndex(Model model) {
        List harvests = harvestService.getAllHarvest();
        model.addAttribute("harvestByResource", harvests);
        return "harvest";
    }
}
