package de.onetwotree.margaux.entity;



import com.fasterxml.jackson.annotation.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebby on 10/07/17.
 */
@Entity
@Table(name = "db_company")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Company extends MainEntity {

    @NotNull(message = "Please choose")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_company_id", nullable = false)
    @JsonIgnore
    private MainCompany mainCompany;

    @OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Project> projects = new ArrayList<Project>();

    public Company() {
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
