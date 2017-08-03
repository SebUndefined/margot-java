package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.Harvest;

import java.util.List;

/**
 * Created by SebUndefined on 02/08/17.
 */
public interface HarvestService {
    List getAllHarvest();

    List getAllHarvestByMainCompany(Long idMainCompany, Long idResource);
}
