package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.PlotResource;

import java.util.List;

/**
 * Created by SebUndefined on 29/08/17.
 */
public interface PlotResourceService {
    String getPlotResourceAsJson(List<PlotResource> plotResourceList);
}
