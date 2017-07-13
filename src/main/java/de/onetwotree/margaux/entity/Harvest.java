package de.onetwotree.margaux.entity;


import java.util.GregorianCalendar;

/**
 * Created by SebUndefined on 10/07/17.
 */
public class Harvest extends MainEntity {
    private GregorianCalendar beginDate;
    private GregorianCalendar endDate;
    private float quantity;
    private Plot plot;
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

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
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
