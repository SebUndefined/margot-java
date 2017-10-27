package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.dao.ResourceRepository;
import de.onetwotree.margaux.dao.ResourceTypeRepository;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.Resource;
import de.onetwotree.margaux.entity.ResourceType;
import de.onetwotree.margaux.exception.ItemNotFoundException;
import de.onetwotree.margaux.service.ResourceService;
import de.onetwotree.margaux.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by SebUndefined on 23/08/17.
 */
@Controller
@RequestMapping(value = "resource")
public class ResourceController {

    @Autowired
    ResourceService resourceService;
    @Autowired
    ResourceTypeService resourceTypeService;

    @RequestMapping(value = "/")
    public String mainResourceIndex(Model model) {
        List<Resource> resources = resourceService.findAll();
        model.addAttribute("resources", resources);
        return "Resource/resource";
    }

    @RequestMapping(value = "/view/{resourceId}")
    public String viewResource(@PathVariable(value = "resourceId") String idResource, Model model) throws ItemNotFoundException {
        Resource resource = resourceService.findOne(Long.valueOf(idResource));
        if (resource == null) throw new ItemNotFoundException(Long.valueOf(idResource), "resource/");
        model.addAttribute("resource", resource);
        return "Resource/viewResource";
    }

    @RequestMapping(value = "/add")
    public String addResourceForm(Model model) {
        List<ResourceType> resourceTypes = resourceTypeService.findAll();
        Resource resource = new Resource();
        model.addAttribute("resource", resource);
        model.addAttribute("resourceTypes", resourceTypes);
        return "Resource/editResource";
    }
    @PostMapping(value="/add")
    public String addResourceSubmit(@ModelAttribute("Resource") Resource resource, BindingResult result) {
        resourceService.saveResource(resource);
        return "redirect:/resource/";

    }
}
