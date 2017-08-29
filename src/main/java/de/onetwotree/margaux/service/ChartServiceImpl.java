package de.onetwotree.margaux.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.onetwotree.margaux.chartData.json.Datum;
import de.onetwotree.margaux.chartData.json.PlotLy;
import de.onetwotree.margaux.entity.Harvest;
import de.onetwotree.margaux.entity.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by SebUndefined on 29/08/17.
 */
@Service("chartService")
public class ChartServiceImpl implements ChartService {

    @Override
    public String buildLineChartByDate(Map<Resource, List<Harvest>> resourceWithHarvests)
    {
        List<String> x = new ArrayList<>();
        List<BigDecimal> y = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Datum> data = new ArrayList<>();
        Datum datum = new Datum();
        for (Map.Entry<Resource, List<Harvest>> entry : resourceWithHarvests.entrySet()) {
                List<Harvest> harvestResource = entry.getValue();
                for(Harvest harvest : harvestResource) {
                    x.add(harvest.getDate().format(formatter));
                    y.add(harvest.getQuantity());
                }
                datum = new Datum(x, y, "scatter", entry.getKey().getName());
                data.add(datum);
                x = new ArrayList<>();
                y = new ArrayList<>();
        }
        PlotLy plotLyChart = new PlotLy(data);
        System.out.println(plotLyChart.toString());
        ObjectMapper mapper = new ObjectMapper();
        String myGraphData = "";
        try {
            myGraphData = mapper.writeValueAsString(plotLyChart);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(myGraphData.toString());
        return myGraphData;
    }
}