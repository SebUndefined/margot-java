package de.onetwotree.margaux.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.onetwotree.margaux.dao.CompanyRepository;
import de.onetwotree.margaux.dao.PlotRepository;
import de.onetwotree.margaux.dao.ProjectRepository;
import de.onetwotree.margaux.entity.Company;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.Project;
import de.onetwotree.margaux.entity.User;
import de.onetwotree.margaux.entityJson.PlotView;
import de.onetwotree.margaux.exception.ItemNotFoundException;
import de.onetwotree.margaux.service.CompanyService;
import de.onetwotree.margaux.service.ProjectService;
import de.onetwotree.margaux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
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
    ProjectRepository projectRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    PlotRepository plotRepository;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/")
    public String mainProjectIndex(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "Project/project";
    }

    @RequestMapping(value = "/view/{id}")
    public  String viewProject(@PathVariable(value = "id") String id, Model model) {
        Long projectId = Long.valueOf(id);
        Project project = projectRepository.findOne(projectId);
        model.addAttribute("project", project);
        model.addAttribute("urlId", id);
        return "Project/viewProject";
    }

    @RequestMapping(value = "/add")
    public String addProjectForm(Model model) {
        List<User> users = userService.getAllUsers();
        List<Company> companies = companyRepository.findAll();
        Project project = new Project();
        model.addAttribute("users", users);
        model.addAttribute("companies", companies);
        model.addAttribute("project", project);
        return "Project/editProject";
    }

    @PostMapping(value="/add")
    public String addProjectSubmit(@Valid @ModelAttribute("project")Project project, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError error : errors) {
                redirectAttributes.addFlashAttribute("alert", "Error on " + error.getObjectName() + ". " + error.getDefaultMessage());
            }
            return "redirect:/project/add/";
        }
        try {
            projectRepository.saveAndFlush(project);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return "redirect:/project/";
    }
    @GetMapping(value = "/update/{id}")
    public String updateProject(@ModelAttribute("project") Project project,
                                Model model,
                                @PathVariable(value = "id") String id) throws ItemNotFoundException {
        project = projectRepository.findOne(Long.valueOf(id));
        if (project == null){
            throw new ItemNotFoundException(Long.valueOf(id), "project/");
        }

        model.addAttribute("project", project);
        model.addAttribute("companies", companyRepository.findAll());
        return "Project/updateProject";
    }
    @PostMapping(value = "/update/{id}")
    public String updateProjectSubmit(@Valid @ModelAttribute("project") Project project, BindingResult result,
                                      @PathVariable(value = "id") String id,
                                      RedirectAttributes redirectAttributes) throws ItemNotFoundException {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError error : errors) {
                redirectAttributes.addFlashAttribute("alert", "Error on " + error.getObjectName() + ". " + error.getDefaultMessage());
            }
            return "redirect:/project/update/" + id;
        }
        Project projectOrigin = projectRepository.findOne(Long.valueOf(id));
        if (projectOrigin == null) {
            throw new ItemNotFoundException(Long.valueOf(id), "project/");
        }
        projectService.updateProject(project, projectOrigin);
        redirectAttributes.addFlashAttribute("info", "Project " + project.getName() + " has been updated !");
        return "redirect:/project/view/" + id;
    }

    /**
     * View plots of Company
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "view/{id}/plots/")
    public String viewPlotsOfProject(@PathVariable(value = "id") String id, Model model) {
        Long idProject = Long.valueOf(id);
        List<Plot> plotList = plotRepository.findAllByProjectId(idProject);
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writerWithView(PlotView.PlotWithUserAndCompany.class).writeValueAsString(plotList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        model.addAttribute("urlId", id);
        model.addAttribute("plots", result);
        return "Project/viewPlotsofProject";
    }



}
