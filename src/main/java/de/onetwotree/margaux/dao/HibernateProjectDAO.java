package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Project;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
@Repository("projectDao")
public class HibernateProjectDAO implements ProjectDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Project> getAllProjects() {
        return (List<Project>)sessionFactory.getCurrentSession().createCriteria(Project.class).list();
    }
}
