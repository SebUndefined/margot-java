package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.MainCompany;
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
@Repository
@Transactional
public class HibernateMainCompanyDAO implements MainCompanyDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MainCompany> findAll() {
        List<MainCompany> mainCompanies = new ArrayList<MainCompany>();
        Query query = entityManager.createQuery("FROM MainCompany");
        mainCompanies = query.getResultList();
        return mainCompanies;
    }
    @Override
    public void addMainCompany(MainCompany mainCompany) {
        System.out.println(mainCompany.getName());
        entityManager.persist(mainCompany);

    }
}
