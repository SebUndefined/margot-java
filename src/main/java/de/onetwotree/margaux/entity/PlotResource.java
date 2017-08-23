
package de.onetwotree.margaux.entity;



import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by SebUndefined on 21/08/17.
 */


@Entity
@Table(name = "db_plot_resource")
public class PlotResource {

   @EmbeddedId
   protected PlotResourcePK plotResourcePK;
    @ManyToOne(optional = false)
    @JoinColumn(name = "plot_id", referencedColumnName = "main_entity_id",
    insertable = false, updatable = false)
    private Plot plot;

    @ManyToOne(optional = false)
    @JoinColumn(name = "resource_id", referencedColumnName = "resource_id",
    insertable = false, updatable = false)
    private Resource resource;

    @Column(name = "proportion")
    private Integer proportion;

    public PlotResource() {
    }

    public PlotResource(PlotResourcePK plotResourcePK) {
        this.plotResourcePK = plotResourcePK;
    }

    public PlotResource(Long idPlot, Long idResource) {
        this.plotResourcePK = new PlotResourcePK(idPlot, idResource);
    }

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
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

