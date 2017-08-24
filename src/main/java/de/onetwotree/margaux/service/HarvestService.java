package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.Harvest;

import java.util.List;

/**
 * Created by SebUndefined on 02/08/17.
 */
public interface HarvestService {

    List getAllHarvest();

    List getAllHarvestByMainCompanyByResource(long idMainCompany, long idResource);

    String getSumHarvestByMCompanyByResourceJson(Long idMainCompany, Long idResource);

    String getHarvestsbyMainCompanyPlotLy(long idMainCompany);

    boolean addHarvest(Harvest harvest);
}
