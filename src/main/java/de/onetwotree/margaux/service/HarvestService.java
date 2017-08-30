package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.Harvest;
import de.onetwotree.margaux.entity.Resource;
import de.onetwotree.margaux.entity.ResourceType;
import de.onetwotree.margaux.exception.AddHarvestException;
import de.onetwotree.margaux.exception.ItemNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SebUndefined on 02/08/17.
 */
public interface HarvestService {

    List getAllHarvest();

    String getSumByResourceWhereResourceTypeId(Long resourceTypeId);


    String getAllGroupByResourceTypeJson(Long idResourceType);

    String findAllHarvestWherePlotIdAsJson(Long idPlot);

    List getAllHarvestByMainCompanyByResource(long idMainCompany, long idResource);

    String getSumHarvestByMCompanyByResourceJson(Long idMainCompany, Long idResource);

    String getHarvestsbyMainCompanyPlotLy(long idMainCompany);

    Harvest addHarvest(Harvest harvest) throws AddHarvestException;
}
