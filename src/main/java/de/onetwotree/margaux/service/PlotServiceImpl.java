package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.PlotRepository;
import de.onetwotree.margaux.dao.PlotResourceRepository;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.PlotResource;
import de.onetwotree.margaux.entity.PlotResourcePK;
import de.onetwotree.margaux.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SebUndefined on 29/07/17.
 */
@Service("plotService")
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Transactional(readOnly = true)
public class PlotServiceImpl implements PlotService {

    @Autowired
    private PlotRepository plotRepository;
    @Autowired
    private PlotResourceRepository plotResourceRepository;

    /**
     *
     * @param id
     * @param plotResource
     * @return boolean true|false
     */
    @Override
    public boolean addResourceToPlot(Long id, PlotResource plotResource) {
        PlotResourcePK plotResourcePK = new PlotResourcePK(id, plotResource.getResource().getId());
        if (plotResourceRepository.findOne(plotResourcePK) == null) {
            Plot plot = plotRepository.findOne(Long.valueOf(id));
            plotResource.setPlotResourcePK(plotResourcePK);
            plotResource.setPlot(plot);
            plotResourceRepository.saveAndFlush(plotResource);
            return true;
        }
        else {
            return false;
        }
    }
}
