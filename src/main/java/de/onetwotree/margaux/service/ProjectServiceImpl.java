package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.ProjectDAO;
import de.onetwotree.margaux.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SebUndefined on 02/08/17.
 */
@Service("projectService")
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectDAO projectDAO;
    @Override
    public Project getProject(Long id) {
        return projectDAO.getProject(id);
    }
    @Override
    public List<Project> getAllProjects() {
        return projectDAO.getAllProjects();
    }

    @Override
    public List<Project> getAllProjectsForMainCompany(Long idMainCompany) {
        return projectDAO.getAllProjectsForMainCompany(idMainCompany);
    }
    @Override
    public void addProject(Project project) {
        projectDAO.addProject(project);
    }
}
