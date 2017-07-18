package de.onetwotree.margaux.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Created by SebUndefined on 10/07/17.
 */
@Entity
@Table(name = "db_harvest")
public class Harvest extends MainEntity {
    @Column(name = "harvest_begin_date")
    private GregorianCalendar beginDate;
    @Column(name = "harvest_end_date")
    private GregorianCalendar endDate;
    @Column(name = "harvest_quantity")
    private BigDecimal quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plot_id")
    private Plot plot;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    public Harvest() {
    }

    public GregorianCalendar getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(GregorianCalendar beginDate) {
        this.beginDate = beginDate;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
