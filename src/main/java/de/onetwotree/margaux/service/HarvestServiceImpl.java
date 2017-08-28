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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    ResourceRepository resourceRepository;
    @Autowired
    ResourceTypeRepository resourceTypeRepository;

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
    public HashMap<String, HashMap<String,List<Harvest>>> getAllGroupByResourceType() {
        String graphData ="";
        List<ResourceType> resourceTypeList = resourceTypeRepository.findAll();
        List<Resource> resourceList = resourceRepository.findAll();
        List<Harvest> harvestList = harvestRepository.findAll(new Sort(Sort.Direction.ASC, "date"));
        HashMap<String, HashMap<String, List<Harvest>>> resourceTypeWithResource = new HashMap<>();
        Map<Resource, List<Harvest>> resourceWithHarvest = new HashMap<>();

        resourceWithHarvest = harvestList.stream().collect(Collectors.groupingBy(Harvest::getResource));

        System.out.println(resourceWithHarvest.toString());


//        for (ResourceType resourceType : resourceTypeList) {
//            for (Resource resource : resourceType.getResources()) {
//                resourceWithHarvest.put(resource.getName(), resource.getHarvests());
//                resourceTypeWithResource.put(resourceType.getDescription(), resourceWithHarvest);
//            }
//            resourceWithHarvest = new HashMap<String, List<Harvest>>();
//        }
        return resourceTypeWithResource;
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
    public boolean addHarvest(Harvest harvest) {
        Resource resource = harvest.getResource();
        List<PlotResource> resourcePlotList = harvest.getPlot().getPlotResources();
        List<Resource> resourceList = new ArrayList<>();
        for (PlotResource item : resourcePlotList) {
            resourceList.add(item.getResource());
        }
        if (resourceList.contains(resource)) {
            harvestRepository.saveAndFlush(harvest);
            return true;
        }
        return false;
    }

}
