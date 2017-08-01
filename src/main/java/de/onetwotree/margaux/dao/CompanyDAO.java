package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Company;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
public interface CompanyDAO {

    Company getCompany(Long id);

    Company getCompanyWithProjects(Long id);

    void addCompany(Company company);

    List<Company> getAllCompanies();

    List<Company> getAllCompaniesWithManagerAndMainCompany();

    List<Company> getAllCompaniesForMainCompany(Long idMainCompany);
}
