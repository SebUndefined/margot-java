package de.onetwotree.margaux.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.onetwotree.margaux.chartData.json.Datum;
import de.onetwotree.margaux.chartData.json.PlotLy;
import de.onetwotree.margaux.dao.*;
import de.onetwotree.margaux.entity.*;
import de.onetwotree.margaux.exception.AddHarvestException;
import de.onetwotree.margaux.exception.ItemNotFoundException;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by SebUndefined on 02/08/17.
 */
@Service("harvest")
@Transactional(readOnly = true)
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final ChartService chartService;
    private final PlotResourceRepository plotResourceRepository;

    @Autowired
    public HarvestServiceImpl(HarvestRepository harvestRepository, ChartService chartService, PlotResourceRepository plotResourceRepository) {
        this.harvestRepository = harvestRepository;
        this.chartService = chartService;
        this.plotResourceRepository = plotResourceRepository;
    }


    @Override
    public String getSumByResourceWhereResourceTypeId(Long resourceTypeId) {
        List<Object[]> harvests = harvestRepository.sumByResourceWhereIdResourceType(resourceTypeId);
        List<String> x = new ArrayList<String>();
        List<BigDecimal> y = new ArrayList<BigDecimal>();
        for (Object[] row: harvests) {
            x.add(row[0].toString());
            y.add((BigDecimal) row[1]);
        }
        Datum datum = new Datum(x, y, "bar");
        List<Datum> data = new ArrayList<>();
        data.add(datum);
        PlotLy plotLyChart = new PlotLy(data);
        ObjectMapper mapper = new ObjectMapper();
        String myGraphData = "";
        try {
            myGraphData = mapper.writeValueAsString(plotLyChart);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(myGraphData);
        return myGraphData;
    }
    @Override
    public String findAllGroupByResourceTypeJson(Long idResourceType) {
        List<Harvest> harvestList = harvestRepository.findAllWhereIdResourceTypeOrderByDate(idResourceType);
        Map<Resource, List<Harvest>> resourceWithHarvests;
        resourceWithHarvests = harvestList.stream()
                .collect(Collectors.groupingBy(Harvest::getResource));
        String myGraphData = chartService.buildLineChartByDate(resourceWithHarvests);
        return myGraphData;
    }

    @Override
    @Transactional
    public Harvest addHarvest(Harvest harvest) throws AddHarvestException {
        Resource resource = harvest.getResource();
        List<PlotResource> resourcePlotList = harvest.getPlot().getPlotResources();
        List<Resource> resourceList = new ArrayList<>();
        for (PlotResource item : resourcePlotList) {
            resourceList.add(item.getResource());
        }
        if (resourceList.contains(resource)) {
            LocalDate dateBeginHarvest = harvest.getDate();
            LocalDate dateCreationPlot = harvest.getPlot().getCreationDate();
            Period period = Period.between(dateCreationPlot, dateBeginHarvest);
            if (dateCreationPlot.isAfter(dateBeginHarvest)) {
                throw new AddHarvestException("You cannot add an harvest before the plot was created.");
            }
            harvest.setYear(period.getYears());
            PlotResource plotResource = plotResourceRepository.findOne(
                    new PlotResourcePK(harvest.getPlot().getId(), harvest.getResource().getId()));
            System.out.println("harvest qté " + harvest.getQuantity() + " Proportion " + plotResource.getProportion());
            harvest.setQuantityPerHa(harvest.getQuantity().divide(plotResource.getProportion(), 2, RoundingMode.HALF_UP));
            Harvest harvestSaved =  harvestRepository.saveAndFlush(harvest);
            return harvestSaved;
        }
        throw new AddHarvestException("This plot does not have " + harvest.getResource().getName());
    }

}
