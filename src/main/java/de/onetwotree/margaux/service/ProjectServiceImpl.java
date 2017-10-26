package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.HarvestRepository;
import de.onetwotree.margaux.dao.PlotRepository;
import de.onetwotree.margaux.dao.ProjectRepository;
import de.onetwotree.margaux.entity.Harvest;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.Project;
import de.onetwotree.margaux.entity.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by SebUndefined on 02/08/17.
 */
@Service("projectService")
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    HarvestRepository harvestRepository;
    @Autowired
    PlotRepository plotRepository;
    @Autowired
    ChartService chartService;


    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Page<Project> findAllPaginated(Pageable pageable) {
        Page<Project> projectPage = projectRepository.findAll(pageable);
        return projectPage;
    }

    @Override
    public Project findOne(Long idProject){
        Project project = projectRepository.findOne(idProject);
        return project;
    }

    @Override
    @Transactional
    public Project saveProject(Project project) {
        try {
            projectRepository.save(project);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    @Transactional
    public Project updateProject(Project project) {
        Project projectOrigin = projectRepository.findOne(project.getId());
        BeanUtils.copyProperties(project, projectOrigin, "alerts","plots", "budgets");
        try {
            projectRepository.save(projectOrigin);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return projectOrigin;
    }

    @Override
    public List<Plot> findPlots(Long idProject) {
        List<Plot> plotList = plotRepository.findAllByProjectId(idProject);
        return plotList;
    }

    @Override
    public Page<Harvest> findHarvestsPaginated(Long idProject, Pageable pageable) {
        return harvestRepository.findAllByProjectId(idProject, pageable);
    }


    @Override
    public String findHarvestsByResourcesForGraph(Long idProject, Long idResourceType) {
        List<Harvest> harvestList = harvestRepository.findAllByProjectIdAndResourceTypeId(idProject, idResourceType);
        Map<Resource, Map<Integer, BigDecimal>> resourceWithSumHarvestPerYear;
        resourceWithSumHarvestPerYear = harvestList.stream()
                .collect(Collectors.groupingBy(Harvest::getResource,
                        Collectors.groupingBy(Harvest::getYear,
                                Collectors.mapping(Harvest::getQuantityPerHa, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)))));
        String myGraphData = chartService.buildLineChartHarvestWithYear(resourceWithSumHarvestPerYear);
        return myGraphData;
    }
}
