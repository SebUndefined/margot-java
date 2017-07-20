package de.onetwotree.margaux.temp;

import de.onetwotree.margaux.Utils.MargauxException;
import de.onetwotree.margaux.entity.MainCompany;

import java.util.List;

/**
 * Created by SebUndefined on 18/07/17.
 */
public interface MainCompanyDAO {
    List<MainCompany> findAll();

    void addMainCompany(MainCompany mainCompany);

    MainCompany findOnebyId(long id) throws MargauxException;
}
