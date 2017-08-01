package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Company;
import de.onetwotree.margaux.entity.MainCompany;
import de.onetwotree.margaux.entity.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
    public Company getCompanyWithProjects(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Company company = (Company) session.createCriteria(Company.class)
                .add(Restrictions.idEq(id))
                .uniqueResult();
        Hibernate.initialize(company.getProjects());
        return company;
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
    @Override
    public List<Company> getAllCompaniesWithManagerAndMainCompany() {
        Session session = sessionFactory.getCurrentSession();
        List<Company> companies = session.createCriteria(Company.class).list();
        for (Company company:companies) {
            Hibernate.initialize(company.getMainCompany());
            Hibernate.initialize(company.getManager());
        }
        return companies;
    }
    @Override
    public List<Company> getAllCompaniesForMainCompany(Long idMainCompany) {
        Session session = sessionFactory.getCurrentSession();
        System.out.println(idMainCompany.getClass());
        List<Company> companies = (List<Company>)session.createCriteria(Company.class, "company")
                .createAlias("company.mainCompany", "mainCompany")
                .add(Restrictions.eq("mainCompany.id", idMainCompany))
                .list();
        for (Company company:companies) {
            Hibernate.initialize(company.getMainCompany());
            Hibernate.initialize(company.getManager());
        }
        return companies;
    }
}
