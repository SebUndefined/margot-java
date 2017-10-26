package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.Harvest;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.PlotResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by SebUndefined on 29/07/17.
 */
public interface PlotService {


    List<Plot> findAll();

    Page<Plot> findAllPaginated(Pageable pageable);

    Plot findOne(Long id);

    Plot savePlot(Plot plot);

    Page<Harvest> findHarvestsPaginated(Long idPlot, Pageable pageable);

    String findHarvestsByResourcesForGraph(Long idPlot, Long idResourceType);

    @Transactional
    boolean addResourceToPlot(Long id, PlotResource plotResource);

    @Transactional
    void updatePlot(Plot plot, Plot plotOrigin);
}
