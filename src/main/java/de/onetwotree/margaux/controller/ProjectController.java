package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.entity.Project;
import de.onetwotree.margaux.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
@Controller
@RequestMapping(value = "project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/")
    public String mainProjectIndex(Model model) {
        List<Project> projects = projectService.getAllProject();
        model.addAttribute("projects", projects);
        return "project";
    }

}
