package de.onetwotree.margaux.service;

import de.onetwotree.margaux.chartData.json.Datum;
import de.onetwotree.margaux.chartData.json.Domain;
import de.onetwotree.margaux.chartData.json.Layout;
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
        Layout layout = new Layout();
        layout.setTitle("Defined here");
        layout.setAutosize(true);
        layout.setHeight(Long.valueOf(400));
        Domain domain = new Domain();
        domain.setX(new ArrayList<>());
        domain.setY(new ArrayList<>());
        Datum datum = new Datum();
        datum.setDomain(domain);

        for (PlotResource plotResource : plotResourceList) {
            System.out.println(plotResource.getResource().getName());
        }
        return "prout";
    }
}
