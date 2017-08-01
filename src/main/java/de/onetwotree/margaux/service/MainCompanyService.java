package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.MainCompany;

import java.util.List;

/**
 * Created by SebUndefined on 20/07/17.
 */
public interface MainCompanyService {
    public MainCompany getMainCompany(long id);

    MainCompany getMainCompanyForView(long id);

    void addMainCompany(MainCompany mainCompany);

    public List<MainCompany> getAllMainCompany();

    List<MainCompany> getAllMainCompanyWithManager();
}
