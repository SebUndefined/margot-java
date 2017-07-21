package de.onetwotree.margaux.service;


import de.onetwotree.margaux.entity.Company;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
public interface CompanyService {
    Company getCompany(Long id);

    void addCompany(Company company);

    List<Company> getAllCompanies();
}
