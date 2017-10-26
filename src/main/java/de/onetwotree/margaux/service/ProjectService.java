package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.Harvest;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
public interface ProjectService {

    List<Project> findAll();

    Page<Project> findAllPaginated(Pageable pageable);

    Project findOne(Long idProject);

    @Transactional
    Project saveProject(Project project);

    @Transactional
    Project updateProject(Project project);

    List<Plot> findPlots(Long idProject);

    Page<Harvest> findHarvestsPaginated(Long ididProject, Pageable pageable);

    String findHarvestsByResourcesForGraph(Long idProject, Long idResourceType);
}
