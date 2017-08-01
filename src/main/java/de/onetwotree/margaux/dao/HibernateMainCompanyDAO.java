package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.Utils.MargauxException;
import de.onetwotree.margaux.entity.Company;
import de.onetwotree.margaux.entity.MainCompany;
import de.onetwotree.margaux.entity.User;
import org.hibernate.*;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SebUndefined on 18/07/17.
 */
@Repository("mainCompanyDao")
public class HibernateMainCompanyDAO implements MainCompanyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public MainCompany getMainCompany(long id) {
        return (MainCompany) sessionFactory.getCurrentSession().get(MainCompany.class, id);
    }

    @Override
    public MainCompany getMainCompanyForView(long id) {
        Session session = sessionFactory.getCurrentSession();
        MainCompany mainCompany = session.get(MainCompany.class, id);
        Hibernate.initialize(mainCompany.getCompanies());
        for (Company company: mainCompany.getCompanies()) {
            Hibernate.initialize(company.getManager());
        }
        Hibernate.initialize(mainCompany.getManager());
        return mainCompany;
    }

    @Override
    public void addMainCompany(MainCompany mainCompany){
        Session session = sessionFactory.getCurrentSession();
        User user = mainCompany.getManager();
        if (!session.contains(user)) {
            session.refresh(user);
        }
        System.out.println("Avant persist");
        session.save(mainCompany);
        System.out.println(mainCompany.getManager().getFirstname());
    }

    @Override
    public List<MainCompany> getAllMainCompany() {
        return (List<MainCompany>)sessionFactory.getCurrentSession().createCriteria(MainCompany.class).list();
    }

    @Override
    public List<MainCompany> getAllMainCompanyWithManager() {
        Session session = sessionFactory.getCurrentSession();
        List<MainCompany> mainCompanies = (List<MainCompany>)session.createCriteria(MainCompany.class).list();
        for(MainCompany mainCompany:mainCompanies) {
            Hibernate.initialize(mainCompany.getManager());
        }
        return mainCompanies;
    }

}
