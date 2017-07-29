package de.onetwotree.margaux.entity;



import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebby on 10/07/17.
 */
@Entity
@Table(name = "db_company")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Company extends MainEntity {
    @Column(name = "company_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_company_id")
    @JsonManagedReference
    private MainCompany mainCompany;

    @OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonBackReference
    private List<Project> projects = new ArrayList<Project>();

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MainCompany getMainCompany() {
        return mainCompany;
    }

    public void setMainCompany(MainCompany mainCompany) {
        this.mainCompany = mainCompany;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
