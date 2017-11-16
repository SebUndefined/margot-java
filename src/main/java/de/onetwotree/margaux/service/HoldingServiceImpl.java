package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.*;
import de.onetwotree.margaux.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class HoldingServiceImpl implements HoldingService {
    /**
     * Repository
     */

    private final HoldingRepository holdingRepository;
    private final CompanyRepository companyRepository;
    private final ProjectRepository projectRepository;
    private final PlotRepository plotRepository;
    private final HarvestRepository harvestRepository;

    /**
     * Services
     */
    private final ChartService chartService;

    @Autowired
    public HoldingServiceImpl(HoldingRepository holdingRepository, CompanyRepository companyRepository, ProjectRepository projectRepository, PlotRepository plotRepository, HarvestRepository harvestRepository, ChartService chartService) {
        this.holdingRepository = holdingRepository;
        this.companyRepository = companyRepository;
        this.projectRepository = projectRepository;
        this.plotRepository = plotRepository;
        this.harvestRepository = harvestRepository;
        this.chartService = chartService;
    }


    @Override
    public List<Holding> findAll() {
        return holdingRepository.findAll();
    }


    @Override
    public Page<Holding> findAllPaginated(Pageable pageable) {
        Page<Holding> mainCompanyPage = holdingRepository.findAll(pageable);
        return mainCompanyPage;
    }

    @Override
    public Holding findOne(Long idMainCompany){
        Holding holding = holdingRepository.findOne(idMainCompany);
        return holding;
    }

    @Override
    @Transactional
    public Holding saveHolding(Holding holding) {
        try {
            holdingRepository.save(holding);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return holding;
    }
    @Override
    @Transactional
    public Holding updateHolding(Holding holding) {
        Holding holdingOrigin = holdingRepository.findOne(holding.getId());
        BeanUtils.copyProperties(holding, holdingOrigin, "alerts","companies");
        try {
            holdingRepository.save(holdingOrigin);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        return holding;
    }

    @Override
    public Page<Company> findCompaniesPaginated(Long holdingId, Pageable pageable) {
        Page<Company> companyPage = companyRepository.findByHoldingId(holdingId, pageable);
        return companyPage;

    }

    @Override
    public Page<Project> findProjectsPaginated(Long holdingId, Pageable pageable) {
        Page<Project> projectPage = projectRepository.findByHoldingId(holdingId, pageable);
        return projectPage;
    }

    @Override
    public List<Plot> findPlots(Long idMainCompany) {
        List<Plot> plotList = plotRepository.findAllByHoldingId(idMainCompany);
        return plotList;
    }

    @Override
    public Page<Harvest> findHarvestsPaginated(Long holdingId, Pageable pageable) {
        Page<Harvest> harvestPage = harvestRepository.findAllByHoldingId(holdingId, pageable);
        return harvestPage;
    }

    @Override
    public String findHarvestsByResourcesForGraph(Long holdingId, Long idResourceType) {
        List<Harvest> harvestList = harvestRepository.findAllByHoldingIdAndResourceTypeId(holdingId, idResourceType);
        Map<Resource, Map<Integer, BigDecimal>> resourceWithSumHarvestPerYear;
        resourceWithSumHarvestPerYear = harvestList.stream()
                .collect(Collectors.groupingBy(Harvest::getResource,
                        Collectors.groupingBy(Harvest::getYear,
                                Collectors.mapping(Harvest::getQuantityPerHa, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)))));
        String myGraphData = chartService.buildLineChartHarvestWithYear(resourceWithSumHarvestPerYear);
        return myGraphData;
    }
}
