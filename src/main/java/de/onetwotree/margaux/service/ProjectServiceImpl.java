package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.ProjectRepository;
import de.onetwotree.margaux.entity.Project;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;

/**
 * Created by SebUndefined on 02/08/17.
 */
@Service("projectService")
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Override
    @Transactional
    public void updateProject(Project project, Project projectOrigin) {
        System.out.println("Plots" +projectOrigin.getPlots().toString());
        BeanUtils.copyProperties(project, projectOrigin, "plots", "budgets");
        try {
            projectRepository.saveAndFlush(projectOrigin);
        }catch (ConstraintViolationException e) {
            e.printStackTrace();
        }


    }
}
