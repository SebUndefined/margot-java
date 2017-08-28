package de.onetwotree.margaux.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.onetwotree.margaux.chartData.json.Datum;
import de.onetwotree.margaux.chartData.json.PlotLy;
import de.onetwotree.margaux.dao.HarvestRepository;
import de.onetwotree.margaux.dao.PlotRepository;
import de.onetwotree.margaux.dao.ResourceRepository;
import de.onetwotree.margaux.dao.ResourceTypeRepository;
import de.onetwotree.margaux.entity.Harvest;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.Resource;
import de.onetwotree.margaux.entity.ResourceType;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    ResourceTypeRepository resourceTypeRepository;

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
    @GetMapping(value = "/graph-total/")
    public String harvestIndexGraphTotal(Model model) {
        List<ResourceType> resourceTypeList = resourceTypeRepository.findAll();

        String myGraphDataAlimentary = harvestService.getSumByResourceWhereResourceTypeId(2L);
        String myGraphDataWood = harvestService.getSumByResourceWhereResourceTypeId(1L);
        model.addAttribute("myGraphDataWood", myGraphDataWood);
        model.addAttribute("myGraphDataAlimentary", myGraphDataAlimentary);

        return "Harvest/harvestTotal";
    }
    @GetMapping(value = "/graph-timeline/")
    public String harvestIndexTimeLine(Model model) {
        HashMap<String, HashMap<String, List<Harvest>>> allGroupByResourceType = harvestService.getAllGroupByResourceType();
        List<String> x = new ArrayList<String>();
        List<BigDecimal> y = new ArrayList<BigDecimal>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Datum> data = new ArrayList<>();
        Datum datum = new Datum();
        for (Map.Entry<String, HashMap<String, List<Harvest>>> entry : allGroupByResourceType.entrySet()) {
            HashMap<String, List<Harvest>> value = entry.getValue();
            for (Map.Entry<String, List<Harvest>> childEntry : value.entrySet()) {
                List<Harvest> harvestList = childEntry.getValue();
                for(Harvest harvest : harvestList) {
                    x.add(harvest.getDate().format(formatter));
                    y.add(harvest.getQuantity());
                }
                datum = new Datum(x, y, "scatter");
                data.add(datum);
                x = new ArrayList<>();
                y = new ArrayList<>();
            }
        }
        System.out.println(data.toString());
        ObjectMapper mapper = new ObjectMapper();
        String myGraphData = "";
        PlotLy plotLyChart = new PlotLy(data);
        try {
            myGraphData = mapper.writeValueAsString(plotLyChart);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        /*allGroupByResourceType.forEach((keyResourceType, hashMap) -> {
            hashMap.forEach((keyResource, list) -> {
                for (Harvest harvest : list) {
                    y.add(harvest.getQuantity());
                    x.add(harvest.getDate().format(formatter));
                }
                datum.setY(y);
                datum.setX(x);
                datum.setType("scatter");
                data.add(datum);
            });

        });
        System.out.println(y.toString());
        */
        model.addAttribute("myGraphData", myGraphData);
        System.out.println(myGraphData.toString());

        return "Harvest/harvestTimeline";
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
        String url = "";
        String message = "";
        if (!isExist) {
            message = "The Plot " + harvest.getPlot().getName()
                    + "</br> does not have " + harvest.getResource().getName();
            redirectAttributes.addFlashAttribute("alert", message);
            url = "redirect:";
        }
        else {
            message = "The Harvest has been saved !";
            redirectAttributes.addFlashAttribute("info", message);
            url = "redirect:/harvest/";
        }
        return url;
    }
}
