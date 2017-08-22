package de.onetwotree.margaux.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by SebUndefined on 21/08/17.
 */

@Entity
public class PlotResource {

    @EmbeddedId
    protected PlotResourcePK plotResourcePK;

    @Column(name = "proportion")
    private Integer proportion;

    @JoinColumn(name = "plot_id", referencedColumnName = "main_entity_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Plot plot;

    @JoinColumn(name = "resource_id", referencedColumnName = "resource_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Resource resource;

    public PlotResource() {
    }

    public PlotResource(PlotResourcePK plotResourcePK) {
        this.plotResourcePK = plotResourcePK;
    }
    public PlotResource(Long idPlot, Long idResource) {
        this.plotResourcePK = new PlotResourcePK(idPlot, idResource);
    }
    public PlotResource(Plot plot, Resource resource) {
        this.plot = plot;
        this.resource = resource;
        this.plotResourcePK = new PlotResourcePK(plot.getId(), resource.getId());
    }

    public PlotResourcePK getPlotResourcePK() {
        return plotResourcePK;
    }

    public void setPlotResourcePK(PlotResourcePK plotResourcePK) {
        this.plotResourcePK = plotResourcePK;
    }

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    public Plot getPlots() {
        return plot;
    }

    public void setPlots(Plot plot) {
        this.plot = plot;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PlotResource that = (PlotResource) o;
        return Objects.equals(plot, that.plot) &&
                Objects.equals(resource, that.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plot, resource);
    }
}
