package de.onetwotree.margaux.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.GregorianCalendar;

/**
 * Created by SebUndefined on 10/07/17.
 */
@Entity
@Table(name = "db_harvest")
public class Harvest extends MainEntity{

    @Column(name = "harvest_begin_date")
    private LocalDate date;
    @Column(name = "harvest_year")
    private Integer year;
    @Column(name = "harvest_quantity")
    private BigDecimal quantity;
    @Column(name = "harvest_quantity_per_ha")
    private BigDecimal quantityPerHa;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plot_id")
    private Plot plot;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    public Harvest() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getQuantityPerHa() {
        return quantityPerHa;
    }

    public void setQuantityPerHa(BigDecimal quantityPerHa) {
        this.quantityPerHa = quantityPerHa;
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
