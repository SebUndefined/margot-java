package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.PlotResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by SebUndefined on 29/07/17.
 */
public interface PlotService {


    @Transactional
    boolean addResourceToPlot(Long id, PlotResource plotResource);

    @Transactional
    void updatePlot(Plot plot, Plot plotOrigin);
}
