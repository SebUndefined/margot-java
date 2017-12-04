package de.onetwotree.margaux.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.onetwotree.margaux.chartData.json.Layout;
import de.onetwotree.margaux.chartData.plotLyJs.PlotLyJsPie;
import de.onetwotree.margaux.chartData.plotLyJs.datum.DatumPie;
import de.onetwotree.margaux.chartData.plotLyJs.plotLyLayout.PlotLyLayout;
import de.onetwotree.margaux.entity.PlotResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SebUndefined on 29/08/17.
 */
@Service("plotResourceService")
public class PlotResourceServiceImpl implements PlotResourceService {

    private final
    ChartService chartService;

    @Autowired
    public PlotResourceServiceImpl(ChartService chartService) {
        this.chartService = chartService;
    }

    @Override
    public String getPlotResourceAsJson(List<PlotResource> plotResourceList) {
        List<String> labels = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (PlotResource plotResource : plotResourceList) {
            labels.add(plotResource.getResource().getName());
            values.add(plotResource.getProportion().toString());
        }
        String myGraphData = chartService.buildPieChart(labels, values);
        return myGraphData;
    }
}
