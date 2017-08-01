package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.ProjectDAO;
import de.onetwotree.margaux.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
@Service("projectService")
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    @Override
    public Project getProject(Long id) {
        Project project = projectDAO.getProject(id);
        return project;
    }
    @Override
    public List<Project> getAllProject() {
        List<Project> projects = projectDAO.getAllProjects();
        return projects;
    }
    @Override
    public List<Project> getAllProjectsForMainCompany(Long idMainCompany) {
        return projectDAO.getAllProjectsForMainCompany(idMainCompany);
    }

    @Override
    @Transactional
    public void addProject(Project project) {
        projectDAO.addProject(project);
    }
}
