package de.onetwotree.margaux.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.onetwotree.margaux.chartData.json.Datum;
import de.onetwotree.margaux.chartData.json.PlotLy;
import de.onetwotree.margaux.dao.HarvestDAO;
import de.onetwotree.margaux.dao.HarvestRepository;
import de.onetwotree.margaux.dao.ResourceRepository;
import de.onetwotree.margaux.dao.ResourceTypeRepository;
import de.onetwotree.margaux.entity.*;
import de.onetwotree.margaux.exception.AddHarvestException;
import de.onetwotree.margaux.exception.ItemNotFoundException;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by SebUndefined on 02/08/17.
 */
@Service("harvest")
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Transactional(readOnly = true)
public class HarvestServiceImpl implements HarvestService {
    @Autowired
    HarvestDAO harvestDAO;
    @Autowired
    HarvestRepository harvestRepository;
    @Autowired
    ChartService chartService;

    @Override
    public List getAllHarvest() {
        return harvestDAO.getAllHarvest();
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
    public String getAllGroupByResourceTypeJson(Long idResourceType) {
        List<Harvest> harvestList = harvestRepository.findAllByResourceTypeOrderByDate(idResourceType);
        Map<Resource, List<Harvest>> resourceWithHarvests;
        resourceWithHarvests = harvestList.stream()
                .collect(Collectors.groupingBy(Harvest::getResource));
        String myGraphData = chartService.buildLineChartByDate(resourceWithHarvests);
        return myGraphData;
    }

    @Override
    public String findAllHarvestWherePlotIdAsJson(Long idPlot) throws ItemNotFoundException {
        List<Harvest> harvestList = harvestRepository.findAllByPlotId(idPlot);
        System.out.println(harvestList.size());
        Map<Resource, List<Harvest>> resourceWithHarvest = harvestList.stream()
                .collect(Collectors.groupingBy(Harvest::getResource));
        return "prout";
    }



    @Override
    public List getAllHarvestByMainCompanyByResource(long idMainCompany, long idResource) {
        return harvestDAO.getAllHarvestByMainCompanyByResource(idMainCompany, idResource);
    }

    @Override
    public String getSumHarvestByMCompanyByResourceJson(Long idMainCompany, Long idResource) {
        List<Object[]> results = harvestDAO.getAllHarvestByMainCompanyByResource(idMainCompany, idResource);
        List<String> x = new ArrayList<String>();
        List<BigDecimal> y = new ArrayList<BigDecimal>();
        for (Object[] row: results) {
            x.add(row[1].toString());
            y.add((BigDecimal) row[2]);
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
        return myGraphData;
    }
    @Override
    public String getHarvestsbyMainCompanyPlotLy(long idMainCompany)
    {
        System.out.println(harvestDAO.getAllHarvestByMainCompanyByResourceWithDate(idMainCompany, (long) 1));
        return "prout";

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
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(currentDate, dateBeginHarvest);
            if (currentDate.isAfter(dateBeginHarvest)) {
                throw new AddHarvestException("You cannot add an harvest before the plot was created.");
            }
            System.out.println("The diff is ======>" + period.getYears());
            Harvest harvestSaved =  harvestRepository.saveAndFlush(harvest);
            return harvestSaved;
        }
        throw new AddHarvestException("This plot does not have " + harvest.getResource().getName());
    }

}
