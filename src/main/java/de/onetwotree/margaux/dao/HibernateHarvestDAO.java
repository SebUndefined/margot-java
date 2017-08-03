package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Harvest;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
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

    @Override
    public List getAllHarvestByMainCompany(Long idMainCompany, Long idResource) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT margaux2.db_resource_type.resource_type_name, margaux2.db_resource.resource_name, sum(harvest_quantity) " +
                        "From margaux2.db_harvest, margaux2.db_resource, margaux2.db_resource_type, margaux2.db_plot, margaux2.db_project, margaux2.db_company, margaux2.db_main_company " +
                        "Where db_harvest.resource_id = db_resource.resource_id " +
                        "And db_resource.resource_type_id = db_resource_type.resource_type_id " +
                        "And db_harvest.plot_id = db_plot.main_entity_id " +
                        "And db_plot.project_id = db_project.main_entity_id " +
                        "And db_project.company_id = db_company.main_entity_id " +
                        "And db_company.main_company_id = db_main_company.main_entity_id " +
                        "AND db_resource_type.resource_type_id = :idResourceType "+
                        "And db_main_company.main_entity_id = :idMainCompany " +
                        "Group by db_harvest.resource_id,db_resource.resource_type_id");
        query.setLong("idResourceType", idResource);
        query.setLong("idMainCompany", idMainCompany);
        List result = query.list();
        return result;
    }
}
