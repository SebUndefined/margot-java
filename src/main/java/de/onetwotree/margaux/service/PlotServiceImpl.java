package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.PlotDAO;
import de.onetwotree.margaux.entity.Plot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SebUndefined on 29/07/17.
 */
@Service("plotService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PlotServiceImpl implements PlotService {

    @Autowired
    private PlotDAO plotDAO;

    @Override
    public List<Plot> getAllPlot() {
        return plotDAO.getAllPlot();
    }
}
