package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Company;
import de.onetwotree.margaux.entity.MainCompany;
import de.onetwotree.margaux.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
@Repository("companyDao")
public class HibernateCompanyDAO implements CompanyDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Company getCompany(Long id) {
        return (Company) sessionFactory.getCurrentSession().get(Company.class, id);
    }
    @Override
    public void addCompany(Company company) {
        Session session = sessionFactory.getCurrentSession();
        User user = company.getManager();
        if (!session.contains(user)) {
            session.refresh(user);
        }
        System.out.println("refresh done");
        session.save(company);
    }
    @Override
    public List<Company> getAllCompanies() {
        return (List<Company>)sessionFactory.getCurrentSession().createCriteria(Company.class).list();
    }
}
