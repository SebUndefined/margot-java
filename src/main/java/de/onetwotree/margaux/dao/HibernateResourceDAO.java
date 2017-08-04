package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Harvest;
import de.onetwotree.margaux.entity.Resource;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SebUndefined on 04/08/17.
 */
@Repository(value = "resourceDao")
public class HibernateResourceDAO implements ResourceDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Resource> getAllResourceByMainCompanyByResIdWithHarvest(long idMainCompany, long idResourceType) {
        Session session = sessionFactory.getCurrentSession();
        Criteria myCriteria = session.createCriteria(Harvest.class, "harvest");
        myCriteria.createAlias("resource", "resource");
        myCriteria.createAlias("resource.resourceType", "resourceType");
        myCriteria.add(Restrictions.eq("resourceType.id", idResourceType));
        myCriteria.setProjection( Projections.projectionList().add(Projections.groupProperty("resource")));
        myCriteria.createAlias("plot", "plot");
        myCriteria.createAlias("plot.project", "project");
        myCriteria.createAlias("project.company", "company");
        myCriteria.createAlias("company.mainCompany", "mainCompany");
        myCriteria.add(Restrictions.eq("mainCompany.id", idMainCompany));
        List<Resource> resources = myCriteria.list();
        return resources;
    }
}
