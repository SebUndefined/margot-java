
package de.onetwotree.margaux.entity;



import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Created by SebUndefined on 21/08/17.
 */


@Entity
@Table(name = "db_plot_resource")
public class PlotResource {

    @EmbeddedId
    private PlotResourcePK plotResourcePK;
    @ManyToOne(optional = false)
    @JoinColumn(name = "plot_id", referencedColumnName = "main_entity_id",
    insertable = false, updatable = false)
    private Plot plot;

    @ManyToOne(optional = false)
    @JoinColumn(name = "resource_id", referencedColumnName = "resource_id",
    insertable = false, updatable = false)
    private Resource resource;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "proportion")
    private BigDecimal proportion;

    public PlotResource() {
    }

    public PlotResource(PlotResourcePK plotResourcePK) {
        this.plotResourcePK = plotResourcePK;
    }

    public PlotResource(Long idPlot, Long idResource) {
        this.plotResourcePK = new PlotResourcePK(idPlot, idResource);
    }

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    public PlotResourcePK getPlotResourcePK() {
        return plotResourcePK;
    }

    public void setPlotResourcePK(PlotResourcePK plotResourcePK) {
        this.plotResourcePK = plotResourcePK;
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

