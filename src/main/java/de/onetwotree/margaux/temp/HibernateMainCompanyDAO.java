package de.onetwotree.margaux.temp;

import de.onetwotree.margaux.Utils.MargauxException;
import de.onetwotree.margaux.entity.Holding;
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
    public List<Holding> findAll() {
        List<Holding> mainCompanies = new ArrayList<Holding>();
        Query query = entityManager.createQuery("FROM MainCompany");
        mainCompanies = query.getResultList();
        return mainCompanies;
    }
    @Override
    public void addMainCompany(Holding holding) {
        System.out.println(holding.getName());
        entityManager.persist(holding);

    }
    @Override
    public Holding findOnebyId(long id) throws MargauxException {

        Holding holding = entityManager.find(Holding.class,id);
        if (holding == null) {
            throw new MargauxException("Error no company with the id: " + id);
        }
        return holding;


    }
}
