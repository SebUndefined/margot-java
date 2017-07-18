package de.onetwotree.margaux.entity;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by SebUndefined on 10/07/17.
 */
@Entity
@Table(name = "db_project")
public class Project extends MainEntity {

    @Column(name = "project_begin_date")
    private GregorianCalendar beginDate;
    @Column(name = "project_end_date")
    private GregorianCalendar endDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Plot> plots = new ArrayList<Plot>();
    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Budget> budgets = new ArrayList<Budget>();


    public Project() {
    }

    public Calendar getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(GregorianCalendar beginDate) {
        this.beginDate = beginDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    /*public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }*/
}
