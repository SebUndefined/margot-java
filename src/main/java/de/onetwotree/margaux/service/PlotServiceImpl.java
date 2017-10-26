package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.HarvestRepository;
import de.onetwotree.margaux.dao.PlotRepository;
import de.onetwotree.margaux.dao.PlotResourceRepository;
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
 * Created by SebUndefined on 29/07/17.
 */
@Service("plotService")
@Transactional(readOnly = true)
public class PlotServiceImpl implements PlotService {

    @Autowired
    private PlotRepository plotRepository;
    @Autowired
    private HarvestRepository harvestRepository;
    @Autowired
    private PlotResourceRepository plotResourceRepository;
    @Autowired
    private ChartService chartService;


    @Override
    public List<Plot> findAll() {
        return plotRepository.findAll();
    }

    /**
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<Plot> findAllPaginated(Pageable pageable) {
        return plotRepository.findAll(pageable);
    }

    @Override
    public Plot findOne(Long id) {
        return plotRepository.findOne(id);
    }

    @Transactional
    @Override
    public Plot savePlot(Plot plot) {
        return plotRepository.saveAndFlush(plot);
    }

    @Override
    public Page<Harvest> findHarvestsPaginated(Long idPlot, Pageable pageable) {
        return harvestRepository.findAllByPlotId(idPlot, pageable);
    }
    @Override
    public String findHarvestsByResourcesForGraph(Long idPlot, Long idResourceType) {
        List<Harvest> harvestList = harvestRepository.findAllByPlotIdAndResourceTypeId(idPlot, idResourceType);
        Map<Resource, Map<Integer, BigDecimal>> resourceWithSumHarvestPerYear;
        resourceWithSumHarvestPerYear = harvestList.stream()
                .collect(Collectors.groupingBy(Harvest::getResource,
                        Collectors.groupingBy(Harvest::getYear,
                                Collectors.mapping(Harvest::getQuantityPerHa, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)))));
        String myGraphData = chartService.buildLineChartHarvestWithYear(resourceWithSumHarvestPerYear);
        return myGraphData;
    }
    /**
     *
     * @param id
     * @param plotResource
     * @return boolean true|false
     */
    @Override
    @Transactional
    public boolean addResourceToPlot(Long id, PlotResource plotResource) {
        PlotResourcePK plotResourcePK = new PlotResourcePK(id, plotResource.getResource().getId());
        PlotResource isExist = plotResourceRepository.findOne(plotResourcePK);
        BigDecimal total = plotResourceRepository.findSumOfProportionOfPlot(id);
        if(total == null) {
            total = BigDecimal.valueOf(0);
        }
        if (isExist == null) {
            Plot plot = plotRepository.findOne(id);
            plotResource.setPlotResourcePK(plotResourcePK);
            plotResource.setPlot(plot);
            BigDecimal difference = plotResource.getPlot().getSize().subtract(total);
            if (difference.compareTo(plotResource.getProportion()) == 0
                    || difference.compareTo(plotResource.getProportion()) == 1) {
                plotResourceRepository.saveAndFlush(plotResource);
            }
            else {
                return false;
            }
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    @Transactional
    public void updatePlot(Plot plot, Plot plotOrigin) {
        System.out.println(plot.getId());
        BeanUtils.copyProperties(plot, plotOrigin, "plotResources","alerts");
        System.out.println(plotOrigin.getId());
        try {
            plotRepository.saveAndFlush(plotOrigin);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
    }

}
