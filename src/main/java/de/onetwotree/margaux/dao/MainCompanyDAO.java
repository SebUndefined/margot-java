package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.Utils.MargauxException;
import de.onetwotree.margaux.entity.MainCompany;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SebUndefined on 18/07/17.
 */
public interface MainCompanyDAO {

    public MainCompany getMainCompany(long id);

    MainCompany getMainCompanyForView(long id);

    void addMainCompany(MainCompany mainCompany);

    public List<MainCompany> getAllMainCompany();

    List<MainCompany> getAllMainCompanyWithManager();
}
