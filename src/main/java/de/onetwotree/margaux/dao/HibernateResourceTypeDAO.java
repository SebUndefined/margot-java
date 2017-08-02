package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Harvest;
import de.onetwotree.margaux.entity.Resource;
import de.onetwotree.margaux.entity.ResourceType;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SebUndefined on 02/08/17.
 */
@Repository
public class HibernateResourceTypeDAO implements  ResourceTypeDAO {

    @Autowired
    SessionFactory sessionFactory;

    /*public List<ResourceType> getAllTypeWithHarvest() {
        List<ResourceType> resourceTypes = (List<ResourceType>) sessionFactory.getCurrentSession().createCriteria(ResourceType.class).list();

        for(ResourceType resourceType: resourceTypes) {
            Hibernate.initialize(resourceType.getResources());
            for (Resource resource: resourceType.getResources()) {
                Hibernate.initialize(resource.getHarvests());
            }
        }

        return (List<ResourceType>) cr.setProjection(projectionList).list();

    }*/

}
