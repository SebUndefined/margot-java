package de.onetwotree.margaux.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by SebUndefined on 22/08/17.
 */
@Embeddable
public class PlotResourcePK implements Serializable {

    @Column(name = "plot_id")
    @Basic(optional = false)
    private Long idPlot;
    @Column(name = "resource_id")
    @Basic(optional = false)
    private Long resourceId;

    public PlotResourcePK() {
    }

    public PlotResourcePK(Long idPlot, Long resourceId) {
        this.idPlot = idPlot;
        this.resourceId = resourceId;
        System.out.println(this.toString());
    }

    public Long getIdPlot() {
        return idPlot;
    }

    public void setIdPlot(Long idPlot) {
        this.idPlot = idPlot;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PlotResourcePK that = (PlotResourcePK) o;
        return Objects.equals(idPlot, that.idPlot) &&
                Objects.equals(resourceId, that.resourceId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idPlot, resourceId);
    }
}
