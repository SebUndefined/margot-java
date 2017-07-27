package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Project;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
public interface ProjectDAO {

    Project getProject(Long id);

    List<Project> getAllProjects();

    void addProject(Project project);
}
