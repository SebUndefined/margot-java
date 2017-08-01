package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Company;
import de.onetwotree.margaux.entity.Project;
import de.onetwotree.margaux.entity.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
@Repository("projectDao")
public class HibernateProjectDAO implements ProjectDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Project getProject(Long id) {
        Project project = (Project)sessionFactory.getCurrentSession().get(Project.class, id);
        System.out.println("##################");
        System.out.println("##################");
        System.out.println("##################");
        System.out.println("##################");
        System.out.println(sessionFactory.getCurrentSession().getTransaction().toString());
        return project;
    }
    @Override
    public List<Project> getAllProjects() {
        System.out.println("##################");
        System.out.println("##################");
        System.out.println("##################");
        System.out.println("##################");
        System.out.println(sessionFactory.getCurrentSession().getTransaction().toString());
        return (List<Project>)sessionFactory.getCurrentSession().createCriteria(Project.class).list();
    }

    @Override
    public List<Project> getAllProjectsForMainCompany(Long idMainCompany) {
        Session session = sessionFactory.getCurrentSession();
        System.out.println(idMainCompany.getClass());
        List<Project> projects = (List<Project>)session.createCriteria(Project.class, "project")
                .createAlias("project.company", "company")
                .createAlias("company.mainCompany", "mainCompany")
                .add(Restrictions.eq("mainCompany.id", idMainCompany))
                .list();
        for (Project project:projects) {
            Hibernate.initialize(project.getCompany());
            Hibernate.initialize(project.getManager());
        }
        return projects;
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
