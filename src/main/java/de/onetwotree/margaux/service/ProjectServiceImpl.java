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
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    @Override
    public List<Project> getAllProject() {
        List<Project> projects = projectDAO.getAllProjects();
        return projects;
    }
}
