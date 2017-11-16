package de.onetwotree.margaux.entity;


import com.fasterxml.jackson.annotation.*;
import de.onetwotree.margaux.entityJson.MainEntityView;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SebUndefined on 10/07/17.
 */
@Entity
@Table(name = "db_main_company")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Holding extends MainEntity {

    @OneToMany(
            mappedBy = "holding",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Company> companies = new ArrayList<Company>();

    public Holding() {

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
