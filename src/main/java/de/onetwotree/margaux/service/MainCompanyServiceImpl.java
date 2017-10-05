package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.*;
import de.onetwotree.margaux.entity.*;
import de.onetwotree.margaux.exception.ItemNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by SebUndefined on 20/07/17.
 */
@Service("mainCompanyService")
@Transactional(readOnly = true)
public class MainCompanyServiceImpl implements MainCompanyService {
    /**
     * Repository
     */

    @Autowired
    private MainCompanyRepository mainCompanyRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PlotRepository plotRepository;
    @Autowired
    private HarvestRepository harvestRepository;

    /**
     * Services
     */
    @Autowired
    ChartService chartService;


    @Override
    public List<MainCompany> findAll() {
        return mainCompanyRepository.findAll();
    }


    @Override
    public Page<MainCompany> findAllPaginated(Pageable pageable) {
        Page<MainCompany> mainCompanyPage = mainCompanyRepository.findAll(pageable);
        return mainCompanyPage;
    }

    @Override
    public MainCompany findOne(Long idMainCompany){
        MainCompany mainCompany = mainCompanyRepository.findOne(idMainCompany);
        return mainCompany;
    }

    @Override
    @Transactional
    public MainCompany saveMainCompany(MainCompany mainCompany) {
        try {
            mainCompanyRepository.save(mainCompany);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return mainCompany;
    }
    @Override
    @Transactional
    public MainCompany updateMainCompany(MainCompany mainCompany) {
        MainCompany mainCompanyOrigin = mainCompanyRepository.findOne(mainCompany.getId());
        BeanUtils.copyProperties(mainCompany, mainCompanyOrigin, "alerts","companies");
        try {
            mainCompanyRepository.save(mainCompanyOrigin);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return mainCompany;
    }

    @Override
    public Page<Company> findCompaniesPaginated(Long idMainCompany, Pageable pageable) {
        Page<Company> companyPage = companyRepository.findByMainCompanyId(idMainCompany, pageable);
        return companyPage;

    }

    @Override
    public Page<Project> findProjectsPaginated(Long idMainCompany, Pageable pageable) {
        Page<Project> projectPage = projectRepository.findByMainCompanyId(idMainCompany, pageable);
        return projectPage;
    }

    @Override
    public List<Plot> findPlots(Long idMainCompany) {
        List<Plot> plotList = plotRepository.findAllByMainCompanyId(idMainCompany);
        return plotList;
    }

    @Override
    public Page<Harvest> findHarvestsPaginated(Long idMainCompany, Pageable pageable) {
        Page<Harvest> harvestPage = harvestRepository.findAllByMainCompanyId(idMainCompany, pageable);
        return harvestPage;
    }

    @Override
    public String findHarvestsByResourcesForGraph(Long idMainCompany, Long idResourceType) {
        List<Harvest> harvestList = harvestRepository.findAllByMainCompanyIdAndResourceTypeId(idMainCompany, idResourceType);
        Map<Resource, Map<Integer, BigDecimal>> resourceWithSumHarvestPerYear;
        resourceWithSumHarvestPerYear = harvestList.stream()
                .collect(Collectors.groupingBy(Harvest::getResource,
                        Collectors.groupingBy(Harvest::getYear,
                                Collectors.mapping(Harvest::getQuantityPerHa, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)))));
        String myGraphData = chartService.buildLineChartHarvestWithYear(resourceWithSumHarvestPerYear);
        return myGraphData;
    }
}
