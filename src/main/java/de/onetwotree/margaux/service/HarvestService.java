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


    String getSumByResourceWhereResourceTypeId(Long resourceTypeId);


    String findAllGroupByResourceTypeJson(Long idResourceType);


    String findAllHarvestWherePlotIdAndResourceTypeIdGroupByYearAsJson(Long idPlot, Long idResourceType);


    Harvest addHarvest(Harvest harvest) throws AddHarvestException;
}
