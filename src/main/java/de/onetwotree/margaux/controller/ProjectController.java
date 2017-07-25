package de.onetwotree.margaux.controller;

import de.onetwotree.margaux.application.StringToMainCompany;
import de.onetwotree.margaux.entity.Company;
import de.onetwotree.margaux.entity.Project;
import de.onetwotree.margaux.entity.User;
import de.onetwotree.margaux.service.CompanyService;
import de.onetwotree.margaux.service.ProjectService;
import de.onetwotree.margaux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/")
    public String mainProjectIndex(Model model) {
        List<Project> projects = projectService.getAllProject();
        model.addAttribute("projects", projects);
        return "project";
    }

    @RequestMapping(value = "/add")
    public String addProjectForm(Model model) {
        List<User> users = userService.getAllUsers();
        List<Company> companies = companyService.getAllCompanies();
        Project project = new Project();
        model.addAttribute("users", users);
        model.addAttribute("companies", companies);
        model.addAttribute("project", project);
        return "editProject";
    }

    @PostMapping(value="/add")
    public String addProjectSubmit(@ModelAttribute("Project") Project project, BindingResult result) {
        System.out.println("Date Begin:" + project.getBeginDate());
        System.out.println("Date End:" + project.getEndDate());
        projectService.addProject(project);
        return "redirect:/project/";

    }


}
