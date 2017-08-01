package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Company;
import de.onetwotree.margaux.entity.Project;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
public interface ProjectDAO {

    Project getProject(Long id);

    List<Project> getAllProjects();

    List<Project> getAllProjectsForMainCompany(Long idMainCompany);

    void addProject(Project project);
}
