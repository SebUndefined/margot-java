package de.onetwotree.margaux.entity;



import com.fasterxml.jackson.annotation.*;
import de.onetwotree.margaux.application.StringToMainCompany;
import org.apache.tomcat.jni.Local;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by SebUndefined on 10/07/17.
 */
@Entity
@Table(name = "db_project")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Project extends MainEntity {

   /* @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 4, max = 155, message = "Name size should be between 4 and 155 characteres")
    @Column(name = "project_name", nullable = false, unique = true)
    private String name;*/
    @NotNull(message = "Begin date cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "project_begin_date", nullable = false)
    private LocalDate beginDate;
    @Column(name = "project_end_date", nullable = true)
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonManagedReference
    private Company company;
    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonBackReference
    private List<Plot> plots = new ArrayList<Plot>();
    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Budget> budgets = new ArrayList<Budget>();


    public Project() {
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    /*public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/


    public List<Plot> getPlots() {
        return plots;
    }

    public void setPlots(List<Plot> plots) {
        this.plots = plots;
    }
    public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }
}
