package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.MainCompany;

import java.util.List;

/**
 * Created by SebUndefined on 18/07/17.
 */
public interface MainCompanyDAO {
    List<MainCompany> findAll();

    void addMainCompany(MainCompany mainCompany);
}
