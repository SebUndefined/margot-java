package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.PlotRepository;
import de.onetwotree.margaux.entity.Plot;
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

}
