package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.HarvestDAO;
import de.onetwotree.margaux.entity.Harvest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SebUndefined on 02/08/17.
 */
@Service("harvest")
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Transactional(readOnly = true)
public class HarvestServiceImpl implements HarvestService {
    @Autowired
    HarvestDAO harvestDAO;

    @Override
    public List getAllHarvest() {
        return harvestDAO.getAllHarvest();
    }
}
