package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Harvest;

import java.util.List;

/**
 * Created by SebUndefined on 02/08/17.
 */
public interface HarvestDAO {
    List getAllHarvest();

    List getAllHarvestByMainCompanyByResource(Long idMainCompany, Long idResource);

    List getAllHarvestByMainCompanyByResourceWithDate(long idMainCompany, long idResource);
}
