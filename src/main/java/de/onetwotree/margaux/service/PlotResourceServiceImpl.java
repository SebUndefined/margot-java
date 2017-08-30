package de.onetwotree.margaux.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.onetwotree.margaux.chartData.json.Layout;
import de.onetwotree.margaux.chartData.plotLyJs.PlotLyJsPie;
import de.onetwotree.margaux.chartData.plotLyJs.datum.DatumPie;
import de.onetwotree.margaux.chartData.plotLyJs.plotLyLayout.PlotLyLayout;
import de.onetwotree.margaux.entity.PlotResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SebUndefined on 29/08/17.
 */
@Service("plotResourceService")
public class PlotResourceServiceImpl implements PlotResourceService {

    @Override
    public String getPlotResourceAsJson(List<PlotResource> plotResourceList) {
        PlotLyLayout layout = new PlotLyLayout();
        layout.setTitle("Resources of this plot");
        layout.setAutosize(true);
        List<String> labels = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (PlotResource plotResource : plotResourceList) {
            labels.add(plotResource.getResource().getName());
            values.add(plotResource.getProportion().toString());
        }
        DatumPie datumPie = DatumPie.createSimpleDataForPie(
                labels, false, values, "label+value", 0.1);
        List<DatumPie> data = new ArrayList<>();
        data.add(datumPie);
        PlotLyJsPie plotLyJsPie = new PlotLyJsPie(layout, data);
        ObjectMapper mapper = new ObjectMapper();
        String myGraphData = "";
        try {
            myGraphData = mapper.writeValueAsString(plotLyJsPie);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return myGraphData;
    }
}
