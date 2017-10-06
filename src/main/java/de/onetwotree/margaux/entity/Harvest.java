package de.onetwotree.margaux.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.GregorianCalendar;

/**
 * Created by SebUndefined on 10/07/17.
 */
@Entity
@Table(name = "db_harvest")
public class Harvest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "harvest_id")
    private Long id;
    @NotNull(message = "Date Cannot be null !")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "harvest_begin_date")
    private LocalDate date;
    @Column(name = "harvest_year")
    private Integer year;
    @NotNull(message = "Quantity Cannot be null !")
    @DecimalMin("0.01")
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

    public Harvest(BigDecimal value, Integer key, Resource e1Key) {
        this.year = year;
        this.quantity = quantity;
        this.quantityPerHa = quantityPerHa;
        this.resource = resource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
