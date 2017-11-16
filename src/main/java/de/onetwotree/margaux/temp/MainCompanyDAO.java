package de.onetwotree.margaux.temp;

import de.onetwotree.margaux.Utils.MargauxException;
import de.onetwotree.margaux.entity.Holding;

import java.util.List;

/**
 * Created by SebUndefined on 18/07/17.
 */
public interface MainCompanyDAO {
    List<Holding> findAll();

    void addMainCompany(Holding holding);

    Holding findOnebyId(long id) throws MargauxException;
}
