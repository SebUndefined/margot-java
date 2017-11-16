package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.CompanyRepository;
import de.onetwotree.margaux.dao.HarvestRepository;
import de.onetwotree.margaux.dao.PlotRepository;
import de.onetwotree.margaux.dao.ProjectRepository;
import de.onetwotree.margaux.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by SebUndefined on 21/07/17.
 */
@Service("companyService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ProjectRepository projectRepository;
    private final PlotRepository plotRepository;
    private final HarvestRepository harvestRepository;
    private final ChartService chartService;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ProjectRepository projectRepository, PlotRepository plotRepository, HarvestRepository harvestRepository, ChartService chartService) {
        this.companyRepository = companyRepository;
        this.projectRepository = projectRepository;
        this.plotRepository = plotRepository;
        this.harvestRepository = harvestRepository;
        this.chartService = chartService;
    }


    @Override
    public List<Company> findAll() {
        List<Company> companies = companyRepository.findAll();
        return companies;
    }
    @Override
    public Page<Company> findAllPaginated(Pageable pageable) {
        Page<Company> companyPage = companyRepository.findAll(pageable);
        return companyPage;
    }
    @Override
    public Company findOne(Long idCompany){
        Company company = companyRepository.findOne(idCompany);
        return company;
    }
    @Override
    @Transactional
    public Company saveCompany(Company company) {
        try {
            companyRepository.save(company);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return company;
    }
    @Override
    @Transactional
    public Company updateCompany(Company company) {
        Company companyOrigin = companyRepository.findOne(company.getId());
        BeanUtils.copyProperties(company, companyOrigin, "alerts","projects");
        try {
            companyRepository.save(companyOrigin);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return companyOrigin;
    }
    @Override
    public Page<Project> findProjectsPaginated(Long idCompany, Pageable pageable) {
        Page<Project> projectPage = projectRepository.findAllByCompanyId(idCompany, pageable);
        return projectPage;
    }

    @Override
    public List<Plot> findPlots(Long idCompany) {
        List<Plot> plotList = plotRepository.findAllByCompanyId(idCompany);
        return plotList;
    }

    @Override
    public Page<Harvest> findHarvestsPaginated(Long idCompany, Pageable pageable) {
        Page<Harvest> harvestPage = harvestRepository.findAllByCompanyId(idCompany, pageable);
        return harvestPage;
    }

    @Override
    public String findHarvestsByResourcesForGraph(Long idCompany, Long idResourceType) {
        List<Harvest> harvestList = harvestRepository.findAllByCompanyIdAndResourceTypeId(idCompany, idResourceType);
        Map<Resource, Map<Integer, BigDecimal>> resourceWithSumHarvestPerYear;
        resourceWithSumHarvestPerYear = harvestList.stream()
                .collect(Collectors.groupingBy(Harvest::getResource,
                        Collectors.groupingBy(Harvest::getYear,
                                Collectors.mapping(Harvest::getQuantityPerHa, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)))));
        String myGraphData = chartService.buildLineChartHarvestWithYear(resourceWithSumHarvestPerYear);
        return myGraphData;
    }
}
