package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Company;
import de.onetwotree.margaux.entity.Project;
import de.onetwotree.margaux.entity.User;
import org.hibernate.Session;
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

    @Override
    public void addProject(Project project) {
        Session session = sessionFactory.getCurrentSession();
        User user = project.getManager();
        if(! session.contains(user)) {
            session.refresh(user);
        }
        System.out.println(project.getBeginDate().toString());
        session.save(project);
    }
}
