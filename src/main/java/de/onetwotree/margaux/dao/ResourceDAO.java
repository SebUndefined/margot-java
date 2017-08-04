package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Resource;

import java.util.List;

/**
 * Created by SebUndefined on 04/08/17.
 */
public interface ResourceDAO {

    List<Resource> getAllResourceByMainCompanyByResIdWithHarvest(long idMainCompany, long idResourceType);
}
