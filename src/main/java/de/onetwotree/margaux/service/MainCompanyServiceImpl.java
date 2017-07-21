package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.MainCompanyDAO;
import de.onetwotree.margaux.entity.MainCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SebUndefined on 20/07/17.
 */
@Service("mainCompanyService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MainCompanyServiceImpl implements MainCompanyService {
    @Autowired
    private MainCompanyDAO mainCompanyDAO;


    @Override
    public MainCompany getMainCompany(long id) {
        return mainCompanyDAO.getMainCompany(id);
    }

    @Override
    public void addMainCompany(MainCompany mainCompany){
        mainCompanyDAO.addMainCompany(mainCompany);
    }

    @Override
    public List<MainCompany> getAllMainCompany() {
        return mainCompanyDAO.getAllMainCompany();
    }
}
