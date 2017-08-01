package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Plot;

import java.util.List;

/**
 * Created by SebUndefined on 29/07/17.
 */
public interface PlotDAO {
    List<Plot> getAllPlot();

    List<Plot> getAllPlotForMainCompany(Long idMainCompany);

    void addPlot(Plot plot);
}
