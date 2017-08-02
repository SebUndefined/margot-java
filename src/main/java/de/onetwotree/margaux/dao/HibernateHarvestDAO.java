package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Harvest;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SebUndefined on 02/08/17.
 */
@Repository("harvestDAO")
public class HibernateHarvestDAO implements HarvestDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List getAllHarvest() {
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Harvest.class);
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.sum(("quantity")));
        projectionList.add(Projections.groupProperty("resource"));
        //projectionList.add(Projections.property("testtt"));

        return (List<Object[]>) cr.setProjection(projectionList).list();
    }
}
