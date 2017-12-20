package de.onetwotree.margaux.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.onetwotree.margaux.dao.*;
import de.onetwotree.margaux.dto.PlotMapDTO;
import de.onetwotree.margaux.entity.*;
import de.onetwotree.margaux.exception.ItemNotFoundException;
import de.onetwotree.margaux.exception.PlotResourceException;
import de.onetwotree.margaux.form.PlotResourceForm;
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

import javax.jws.WebParam;
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

    private final
    PlotService plotService;
    private final
    ResourceService resourceService;
    private final
    ResourceTypeService resourceTypeService;
    private final
    ProjectService projectService;
    private final
    PlotResourceService plotResourceService;
    private final
    HarvestService harvestService;
    private final
    CountryService countryService;


    @Autowired
    public PlotController(PlotService plotService,
                          ResourceService resourceService,
                          ResourceTypeService resourceTypeService,
                          ProjectService projectService,
                          PlotResourceService plotResourceService,
                          HarvestService harvestService,
                          CountryService countryService) {
        this.plotService = plotService;
        this.resourceService = resourceService;
        this.resourceTypeService = resourceTypeService;
        this.projectService = projectService;
        this.plotResourceService = plotResourceService;
        this.harvestService = harvestService;
        this.countryService = countryService;
    }

    @RequestMapping(value = "/")
    public String plotIndex(Model model,
                            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
                            @RequestParam(name = "sort", defaultValue = "id", required = false) String sort,
                            @RequestParam(name = "direction", defaultValue = "DESC", required = false) Sort.Direction direction)
    {
        PageRequest pageable = new PageRequest(page - 1, size, new Sort(direction, sort));
        Page<Plot> plotPage = plotService.findAllPaginated(pageable);
        model.addAttribute("plots", plotPage);
        return "Plot/plot";
    }
    @RequestMapping(value = "/map/")
    public String plotIndexMap(Model model)
    {
        List<Plot> plotList = plotService.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String result = null;

        List<PlotMapDTO> plotMapDTOList = new ArrayList<>();
        for (Plot plot : plotList) {
            PlotMapDTO plotMapDTO = new PlotMapDTO(plot.getProject().getId(),
                    plot.getId(),
                    plot.getLatitude(),
                    plot.getLongitude(),
                    plot.getName(),
                    plot.getSize());
            plotMapDTOList.add(plotMapDTO);
        }
        try {
            result = mapper.writeValueAsString(plotMapDTOList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        model.addAttribute("plots", result);
        return "Plot/plotMap";
    }

    @RequestMapping(value = "/view/{id}")
    public  String viewPlot(@PathVariable(value = "id") Plot plot, Model model) throws ItemNotFoundException {

        //if (plot == null) throw new ItemNotFoundException(plotId, "plot/");
        List<PlotResource> resourcePlotList = plot.getPlotResources();
        String myGraphData = plotResourceService.getPlotResourceAsJson(resourcePlotList);
        //Creating JSON DATA for Localisation
        ObjectMapper mapper = new ObjectMapper();
        PlotMapDTO plotMapDTO = new PlotMapDTO(
                plot.getProject().getId(),
                plot.getId(),
                plot.getLatitude(),
                plot.getLongitude(),
                plot.getName(),
                plot.getSize());
        List<PlotMapDTO> plotMapDTOList = new ArrayList<>();
        plotMapDTOList.add(plotMapDTO);
        String plotJson = "";
        try {
            plotJson = mapper.writeValueAsString(plotMapDTOList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        model.addAttribute("plots", plotJson);
        model.addAttribute("graphResource", myGraphData);
        model.addAttribute("urlId", plot.getId());
        model.addAttribute("plot", plot);
        return "Plot/viewPlot";
    }
    @RequestMapping(value = "/view/{id}/get-resources/")
    public String getResourcesofPlot(@PathVariable(value = "id") String id, Model model) {
        Long plotId = Long.valueOf(id);
        Plot plot = plotService.findOne(plotId);
        List<PlotResource> resourcePlotList = plot.getPlotResources();
        String myGraphData = plotResourceService.getPlotResourceAsJson(resourcePlotList);
        model.addAttribute("graphPlotResource", myGraphData);
        return "common/plotResourceGraph :: graphResourcesPlot";
    }

    @RequestMapping(value = "/view/{id}/resources/")
    public String viewResourceOfPlot(@PathVariable(value = "id") String id) throws ItemNotFoundException {
        Long plotId = Long.valueOf(id);

        Plot plot = plotService.findOne(plotId);
        if (plot == null) throw new ItemNotFoundException(plotId, "plot/");
        List<PlotResource> resourcePlotList = plot.getPlotResources();
        String myGraphData = plotResourceService.getPlotResourceAsJson(resourcePlotList);
        return myGraphData;
    }

    @RequestMapping(value = "/add")
    public String addPlotForm(Model model) {
        Plot plot = new Plot();
        List<Project> projects = projectService.findAll();
        List<Country> countries = countryService.findAll();
        model.addAttribute("plot", plot);
        model.addAttribute("projects", projects);
        model.addAttribute("countries", countries);
        return "Plot/editPlot";
    }
    @PostMapping(value="/add")
    public String addPlotSubmit(@Valid @ModelAttribute("Plot") Plot plot, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("alerts", result.getAllErrors());
            return "redirect:/plot/add/";
        }
        try {
            plotService.savePlot(plot);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return "redirect:/plot/";

    }

    @GetMapping(value = "update/{plotId}")
    public String updatePlot(@ModelAttribute("plotId") Plot plot,
                             Model model,
                             @PathVariable(value = "plotId") String idPlot) throws ItemNotFoundException {
        plot = plotService.findOne(Long.valueOf(idPlot));
        if (plot == null) throw new ItemNotFoundException(Long.valueOf(idPlot), "plot/");
        model.addAttribute("plot", plot);
        model.addAttribute("projects", projectService.findAll());
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
        Plot plotOrigin = plotService.findOne(Long.valueOf(id));
        if (plotOrigin == null) {
            throw new ItemNotFoundException(Long.valueOf(id), "plot/");
        }
        plotService.updatePlot(plot, plotOrigin);
        redirectAttributes.addFlashAttribute("info", "Plot " + plot.getName() + " has been updated !");
        return "redirect:/plot/view/" + id;
    }
    @GetMapping(value = "/view/{id}/add-resource")
    public String addResourceToPlotForm(Model model, @PathVariable(value = "id") String id) {
        List<Resource> resources = resourceService.findAll();
        PlotResource plotResource = new PlotResource();
        plotResource.setPlot(plotService.findOne(Long.valueOf(id)));
        model.addAttribute("resources", resources);
        model.addAttribute("plotResource", plotResource);

        return "Plot/addResourceToPlot";
    }
    @PostMapping(value="/view/{id-plot}/add-resource")
    public String addResourceToPlotSubmit(RedirectAttributes redirectAttributes,
                                          @PathVariable(value = "id-plot") Plot plot,
                                          @ModelAttribute("PlotResource") PlotResource plotResource,
                                          BindingResult result) {
        if (plot.hasResource(plotResource.getResource())) {
            throw new PlotResourceException("Error: You cannot add this resource to this plot. " +
                    "This resource" + plotResource.getResource().getName() + " is already on the plot ", plot.getId().toString());
        }
        Boolean isExist = plotService.addResourceToPlot(plot, plotResource);
        if (!isExist) {
            throw new PlotResourceException("Error: You cannot add this resource to this plot. " +
                    "The size of the plot is not big enough", plot.getId().toString());
        }
        else {
            String message = "Resource " + plotResource.getResource().getName()
                    + " has been added to plot.";
            redirectAttributes.addFlashAttribute("info", message);
        }

        return "redirect:/plot/view/" + plot.getId().toString() + "/";

    }

    @GetMapping(value = "view/{id}/harvests/")
    public String viewHarvestsOfPlot(Model model,
                                     @PathVariable(value = "id") String idPlot,
                                     @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                     @RequestParam(name = "size", defaultValue = "10", required = false) Integer size)
    {
        Pageable pageable = new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, "id"));
        Page<Harvest> harvestPage = plotService.findHarvestsPaginated(Long.valueOf(idPlot), pageable);
        model.addAttribute("resourceTypeList", resourceTypeService.findAll());
        model.addAttribute("urlId", idPlot);
        model.addAttribute("harvests", harvestPage);
        return "Plot/viewHarvestByPlot";
    }
    @GetMapping(value = "view/{idPlot}/harvests/{idResourceType}")
    public String viewHarvestsOfProjectAjax(@PathVariable(value = "idPlot") String idPlot,
                                                @PathVariable(value = "idResourceType") String idResourceType, Model model){

        String graphHarvestsPlot = plotService.findHarvestsByResourcesForGraph(Long.valueOf(idPlot), Long.valueOf(idResourceType));
        model.addAttribute("myGraphData", graphHarvestsPlot);
        return "common/graphHarvest";

    }

    @GetMapping(value = "view/{idPlot}/add-harvest")
    public String addHarvestToPlot(@PathVariable(value = "idPlot") String idPlot,
                                   Model model) throws ItemNotFoundException {
        Long plotId = Long.valueOf(idPlot);
        Plot plot = plotService.findOne(plotId);
        if (plot == null) throw new ItemNotFoundException(plotId, "plot/");
        Harvest harvest = new Harvest();
        harvest.setPlot(plot);
        model.addAttribute("harvest", harvest);
        return "Plot/addHarvestToPlot";
    }
    @PostMapping(value = "view/{idPlot}/add-harvest")
    public String addHarvestToPlotSubmit(RedirectAttributes redirectAttributes,
                                         @ModelAttribute("Harvest") @Valid Harvest harvest,
                                         BindingResult result,
                                         @PathVariable("idPlot") String idPlot) throws ItemNotFoundException {
        Long plotId = Long.valueOf(idPlot);
        Plot plot = plotService.findOne(plotId);
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

    @GetMapping(value = "view/{plot}/edit-resources/")
    public String editResourcesOfPlot(@PathVariable Plot plot, Model model) {
        PlotResourceForm plotResourceForm = new PlotResourceForm(plot.getPlotResources());
        model.addAttribute("plot", plot);
        model.addAttribute("plotResourceForm", plotResourceForm);
        model.addAttribute("resources", resourceService.findAll());
        return "Plot/editPlotResources :: editPlotResourceForm";
    }

    @PostMapping(value = "view/{plot}/edit-resources/")
    @ResponseBody
    public String editResourceOfPlotSubmit(@PathVariable Plot plot, @ModelAttribute PlotResourceForm plotResourceForm) {
        plotService.updateResourceOfPlot(plot, plotResourceForm);
        return "Request done";
    }
}
