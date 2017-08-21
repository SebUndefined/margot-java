package de.onetwotree.margaux.entity;

import javax.persistence.Entity;

/**
 * Created by SebUndefined on 21/08/17.
 */


public class PlotResource {

    private Plot plot;
    private Resource resource;
    private Integer proportion;

    public PlotResource() {
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

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }
}
