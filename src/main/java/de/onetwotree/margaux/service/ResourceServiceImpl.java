package de.onetwotree.margaux.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.onetwotree.margaux.chartData.json.Datum;
import de.onetwotree.margaux.chartData.json.PlotLy;
import de.onetwotree.margaux.dao.ResourceDAO;
import de.onetwotree.margaux.entity.Harvest;
import de.onetwotree.margaux.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SebUndefined on 04/08/17.
 */
@Service("resourceService")
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Transactional(readOnly = true)
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceDAO resourceDAO;

    @Override
    public String getAllResourceByMainCompanyByResIdWithHarvestPlotLy(long idMainCompany, long idResourceType){
        List<Resource> resources =
                resourceDAO.getAllResourceByMainCompanyByResIdWithHarvest(idMainCompany, idResourceType);
        List<String> x = new ArrayList<>();
        List<BigDecimal> y = new ArrayList<>();
        Datum datum = new Datum();
        List<Datum> data = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for(Resource resource:resources) {
            y = new ArrayList<BigDecimal>();
            x = new ArrayList<String>();
            /*for (Harvest harvest : resource.getHarvests()) {
                y.add(harvest.getQuantity());
                x.add(harvest.getBeginDate().format(formatter));
            }*/
            data.add(new Datum(x, y, "lines"));
        }

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
}
