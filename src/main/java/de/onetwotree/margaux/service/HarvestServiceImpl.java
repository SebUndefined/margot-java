package de.onetwotree.margaux.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.onetwotree.margaux.chartData.json.Datum;
import de.onetwotree.margaux.chartData.json.PlotLy;
import de.onetwotree.margaux.dao.HarvestDAO;
import de.onetwotree.margaux.dao.HarvestRepository;
import de.onetwotree.margaux.entity.Harvest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List getAllHarvest() {
        return harvestDAO.getAllHarvest();
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
    public boolean addHarvest(Harvest harvest) {
        if (harvest.getPlot().getPlotResources().contains(harvest.getResource())) {
            return true;
        }
        //harvestRepository.saveAndFlush(harvest);
        return false;
    }

}
