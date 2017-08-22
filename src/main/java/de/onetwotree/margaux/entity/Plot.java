package de.onetwotree.margaux.entity;


import com.fasterxml.jackson.annotation.*;
import de.onetwotree.margaux.entityJson.PlotView;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by SebUndefined on 10/07/17.
 */
@Entity
@Table(name = "db_plot")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Plot extends MainEntity {

    @Column(name = "plot_name")
    @JsonView(PlotView.PlotBasic.class)
    private String name;
    @Column(name = "plot_size")
    @JsonView(PlotView.PlotBasic.class)
    private double size;
    @Column(name = "plot_latitude")
    @JsonView(PlotView.PlotBasic.class)
    private double latitude;
    @Column(name = "plot_longitude")
    @JsonView(PlotView.PlotBasic.class)
    private double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonView(PlotView.PlotWithUserAndCompany.class)
    private Project project;

    @OneToMany(mappedBy = "plot")
    @JsonIgnore
    private List<PlotResource> resources = new ArrayList<>();

    public Plot() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    public List<PlotResource> getResources() {
        return resources;
    }
    public void setResources(List<PlotResource> resources) {
        this.resources = resources;
    }

    public void addResource(Resource resource, int proportion) {
        PlotResource plotResource = new PlotResource();
        plotResource.setPlots(this);
        plotResource.setResource(resource);
        resources.add(plotResource);
        resource.getPlots().add(plotResource);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plot plot = (Plot) o;
        return Objects.equals(name, plot.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
