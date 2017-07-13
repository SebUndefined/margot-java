package de.onetwotree.margaux.entity;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by SebUndefined on 10/07/17.
 */
public class Project extends MainEntity {

    private GregorianCalendar beginDate;
    private GregorianCalendar endDate;
    private Company company;
    private List<Plot> plots = new ArrayList<Plot>();
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

    public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }
}
