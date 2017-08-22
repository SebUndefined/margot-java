package de.onetwotree.margaux.entity;


import com.fasterxml.jackson.annotation.*;
import de.onetwotree.margaux.entityJson.MainEntityView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SebUndefined on 10/07/17.
 */
@Entity
@Table(name = "db_main_company")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MainCompany extends MainEntity {

    @Column(
            name = "main_company_name",
            nullable = false
    )
    private String name;
    @OneToMany(
            mappedBy = "mainCompany",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonBackReference
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
