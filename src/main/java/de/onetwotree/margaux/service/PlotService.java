package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.PlotResource;

import java.util.List;

/**
 * Created by SebUndefined on 29/07/17.
 */
public interface PlotService {


    boolean addResourceToPlot(Long id, PlotResource plotResource);
}
