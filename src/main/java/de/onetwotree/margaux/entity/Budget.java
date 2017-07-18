package de.onetwotree.margaux.entity;


import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;

import javax.money.MonetaryAmount;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Created by SebUndefined on 10/07/17.
 */
@Entity
@Table(name = "db_budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private int id;
    @Column(name = "budget_begin_date")
    private GregorianCalendar begindate;
    @Column(name = "budget_end_date")
    private GregorianCalendar endDate;
    @Column(name = "budget_ammount")
    private BigDecimal $ammount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public Budget() {
    }

    public GregorianCalendar getBegindate() {
        return begindate;
    }

    public void setBegindate(GregorianCalendar begindate) {
        this.begindate = begindate;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }

    public BigDecimal get$ammount() {
        return $ammount;
    }

    public void set$ammount(BigDecimal $ammount) {
        this.$ammount = $ammount;
    }
}
