package de.onetwotree.margaux.application;

import de.onetwotree.margaux.entity.Project;
import de.onetwotree.margaux.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by SebUndefined on 21/07/17.
 */
public class StringToProject implements Converter<String, Project> {

    @Autowired
    ProjectService projectService;

    public Project convert(String id) {
        Project project = projectService.getProject(Long.valueOf(id));
        return project;
    }
}
