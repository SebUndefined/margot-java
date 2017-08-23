package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.dao.ResourceRepository;
import de.onetwotree.margaux.dao.ResourceTypeRepository;
import de.onetwotree.margaux.entity.Resource;
import de.onetwotree.margaux.entity.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    ResourceRepository resourceRepository;
    @Autowired
    ResourceTypeRepository resourceTypeRepository;

    @RequestMapping(value = "/")
    public String mainResourceIndex(Model model) {
        List<Resource> resources = resourceRepository.findAll();
        model.addAttribute("resources", resources);
        return "Resource/resource";
    }

    @RequestMapping(value = "/add")
    public String addResourceForm(Model model) {
        List<ResourceType> resourceTypes = resourceTypeRepository.findAll();
        Resource resource = new Resource();
        model.addAttribute("resource", resource);
        model.addAttribute("resourceTypes", resourceTypes);
        return "Resource/editResource";
    }
    @PostMapping(value="/add")
    public String addResourceSubmit(@ModelAttribute("Resource") Resource resource, BindingResult result) {
        resourceRepository.saveAndFlush(resource);
        return "redirect:/resource/";

    }
}
