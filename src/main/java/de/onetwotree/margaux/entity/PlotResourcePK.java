package de.onetwotree.margaux.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by SebUndefined on 23/08/17.
 */
@Embeddable
public class PlotResourcePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "plot_id")
    private Long idPlot;
    @Basic(optional = false)
    @Column(name = "resource_id")
    private Long idResource;

    public PlotResourcePK() {
    }

    public PlotResourcePK(Long idPlot, Long idResource) {
        this.idPlot = idPlot;
        this.idResource = idResource;
    }

    public Long getIdPlot() {
        return idPlot;
    }

    public void setIdPlot(Long idPlot) {
        this.idPlot = idPlot;
    }

    public Long getIdResource() {
        return idResource;
    }

    public void setIdResource(Long idResource) {
        this.idResource = idResource;
    }
}
