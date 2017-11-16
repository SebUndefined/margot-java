package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.dao.ResourceTypeRepository;
import de.onetwotree.margaux.entity.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by SebUndefined on 23/08/17.
 */
@Controller
@RequestMapping(value = "resource-type")
public class ResourceTypeController {

    private final
    ResourceTypeRepository resourceTypeRepository;

    @Autowired
    public ResourceTypeController(ResourceTypeRepository resourceTypeRepository) {
        this.resourceTypeRepository = resourceTypeRepository;
    }

    @RequestMapping(value = "/")
    public String mainResourceTypeIndex(Model model) {
        List<ResourceType> resourceTypes = resourceTypeRepository.findAll();
        model.addAttribute("resourceTypes", resourceTypes);
        return "ResourceType/resourceType";
    }
}
