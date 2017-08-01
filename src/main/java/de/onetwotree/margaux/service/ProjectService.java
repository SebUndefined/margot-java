package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.Project;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
public interface ProjectService {
    Project getProject(Long id);

    List<Project> getAllProject();

    List<Project> getAllProjectsForMainCompany(Long idMainCompany);

    void addProject(Project project);
}
