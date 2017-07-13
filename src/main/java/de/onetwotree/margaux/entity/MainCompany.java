package de.onetwotree.margaux.entity;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by SebUndefined on 10/07/17.
 */
public class MainCompany extends MainEntity {

    private String name;
    private List<Company> companies = new ArrayList<Company>();

    public MainCompany() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
    public void addCompany(Company company) {
        this.companies.add(company);
    }
    public void removeCompany(Company company) {
        this.companies.remove(company);
    }
}
