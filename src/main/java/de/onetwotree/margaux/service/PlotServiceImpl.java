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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
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
    @Transactional
    public boolean addResourceToPlot(Long id, PlotResource plotResource) {
        PlotResourcePK plotResourcePK = new PlotResourcePK(id, plotResource.getResource().getId());
        PlotResource isExist = plotResourceRepository.findOne(plotResourcePK);
        BigDecimal total = plotResourceRepository.findSumOfProportionOfPlot(id);
        if(total == null) {
            total = BigDecimal.valueOf(0);
        }
        if (isExist == null) {
            Plot plot = plotRepository.findOne(id);
            plotResource.setPlotResourcePK(plotResourcePK);
            plotResource.setPlot(plot);
            BigDecimal difference = plotResource.getPlot().getSize().subtract(total);
            if (difference.compareTo(plotResource.getProportion()) == 0
                    || difference.compareTo(plotResource.getProportion()) == 1) {
                plotResourceRepository.saveAndFlush(plotResource);
            }
            else {
                return false;
            }
            return true;
        }
        else {
            return false;
        }
    }

}
